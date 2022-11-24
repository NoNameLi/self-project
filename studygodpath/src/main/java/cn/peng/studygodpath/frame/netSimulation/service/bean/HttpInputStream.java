package cn.peng.studygodpath.frame.netSimulation.service.bean;

import org.apache.commons.httpclient.ChunkedInputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class HttpInputStream extends InputStream {

	private InputStream hostStream; // 要解析的input流
	private boolean isChunked = false;
	private boolean isGzip = false;
	private int readMetaSize = 0; // 读取的实际总长度
	private long contentLength = -1; // 读取到的内容长度
	private int canReadLength = 0;
	private String headers;
	private ChunkedInputStream chunked;
	
	public boolean isChunked() {
		return isChunked;
	}

	public boolean isGzip() {
		return isGzip;
	}

	public HttpInputStream(InputStream inputStream) throws IOException {
		this.hostStream = inputStream;
		chunked = new ChunkedInputStream(this.hostStream);
		parseHeader();
	}
	
	public String getHeaders(){
		String headers = this.headers.substring(1, this.headers.length());
		return headers;
	}

	public int getReadMetaSize() {
		return readMetaSize;
	}

	public long getContentLength() {
		return contentLength;
	}

	/**
	 * 解析响应头
	 * 
	 * @throws IOException
	 */
	private void parseHeader() throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		if (this.hostStream != null) {
			int len = -1;
			byte[] buf = new byte[1];
			// 0x3A 冒号
			byte[] outBuf = new byte[] { 0x3A };
			int count = 0;
			while ((len = this.hostStream.read(buf, 0, buf.length)) > -1) {
				int outLength = outBuf.length + len;
				baos.write(buf, 0, buf.length);
				byte[] tempBuf = outBuf;
				outBuf = new byte[outLength];
				System.arraycopy(tempBuf, 0, outBuf, 0, tempBuf.length);
				System.arraycopy(buf, 0, outBuf, outBuf.length - 1, len);
				if (buf[0] == 0x0D || buf[0] == 0x0A) {
					count++;
					if (count == 4) {
						break;
					}
				} else {
					count = 0;
				}
			}
			headers = new String(outBuf, 0, outBuf.length);
			String[] splitLine = headers.trim().split("\r\n");
			for (String statusLine : splitLine) {
				String[] nameValue = statusLine.split(":");
				if("Content-Length".equalsIgnoreCase(nameValue[0])){
					contentLength = Integer.valueOf(nameValue[1].trim());
					canReadLength = (int) contentLength;
				}
				if ("Transfer-Encoding".equalsIgnoreCase(nameValue[0])) {
					String value = nameValue[1].trim();
					isChunked = value.equalsIgnoreCase("chunked");
				}else if ("Content-Encoding".equalsIgnoreCase(nameValue[0])){
					String value = nameValue[1].trim();
					isGzip = value.toLowerCase().contains("gzip");
				}
			}
		}
	}

	/**
	 * 字节流读取
	 */
	@Override
	public int read() throws IOException {
		if (!isChunked) {
			/**
			 * 非chunked编码字节流解析
			 */
			if(contentLength==-1){
				return -1;
			}else{
				canReadLength--;
				if(canReadLength<0){
					return -1;
				}else{
					return this.hostStream.read();
				}
			}
		}
		/**
		 * chunked编码字节流解析
		 */
		return chunked.read();
	}

	@Override
	public long skip(long n) throws IOException {
		return hostStream.skip(n);
	}

	@Override
	public boolean markSupported() {
		return hostStream.markSupported();
	}

	@Override
	public int available() throws IOException {
		return hostStream.available();
	}

	@Override
	public synchronized void mark(int readlimit) {
		hostStream.mark(readlimit);
	}

	@Override
	public void close() throws IOException {
		hostStream.close();
	}

	@Override
	public synchronized void reset() throws IOException {
		hostStream.reset();
	}
}