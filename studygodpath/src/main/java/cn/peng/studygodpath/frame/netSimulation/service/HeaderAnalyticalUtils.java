package cn.peng.studygodpath.frame.netSimulation.service;

import cn.peng.studygodpath.frame.netSimulation.service.bean.RequestHeaderData;
import cn.peng.studygodpath.frame.netSimulation.service.bean.RequestInfo;
import cn.peng.studygodpath.frame.netSimulation.service.bean.ResponseInfo;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class HeaderAnalyticalUtils {
	
	static int bufsize = 8096;
	/**
	 * find http header
	 **/
	public static int findHeaderEnd(final byte[] buf, int length) {
		int splitbyte = 0;
		while (splitbyte + 3 < length) {
			if (buf[splitbyte] == '\r' && buf[splitbyte + 1] == '\n'
					&& buf[splitbyte + 2] == '\r'
					&& buf[splitbyte + 3] == '\n')
				return splitbyte + 4;
			splitbyte++;
		}
		return -1;
	}
	
	public static int findHeaderEnd(InputStream is, ByteArrayOutputStream baos) throws IOException {
		int len = 0;
		byte[] buf = new byte[1024];
		while((len=is.read(buf)) != -1) {
			baos.write(buf, 0, len);
			if (HeaderAnalyticalUtils.findHeaderEnd(buf, len) > 0) {
				break;
			}
		}
		return HeaderAnalyticalUtils.findHeaderEnd(baos.toByteArray(), baos.size());
	}
	/**
	 * 解析HttpRequest头文件信息
	 * @param requestInfo
	 * @param bfr
	 * @param param
	 * @return
	 */
	public static Boolean headerDataParser(RequestInfo requestInfo, BufferedReader bfr, String param){
		String string;
		boolean flag = false;
		try {
			while ((string = bfr.readLine()) != null) {
				String lowerCase = string.toLowerCase();
				if (lowerCase.startsWith("get")
						||lowerCase.startsWith("post")
						||lowerCase.startsWith("head")
						||lowerCase.startsWith("put")
						||lowerCase.startsWith("options")
						||lowerCase.startsWith("delete") ){
					String[] requestStr = string.split(" ");
					String requestUrl = StringUtils.substring(string, requestStr[0].length(),
							string.length()-requestStr[requestStr.length-1].length()).trim();
					requestInfo.setRequest_URL(requestUrl);
					requestInfo.setRequest_Method(requestStr[0]);
					requestInfo.setTransfer_Version(requestStr[requestStr.length-1]);
				}else if (lowerCase.startsWith("host:")) {
					requestInfo.setHost(string);
					int start = requestInfo.getHost().indexOf(": ");
					if (start == -1)
						return false;
					int next = requestInfo.getHost().indexOf(':', start + 2);
					if (next == -1) {
						requestInfo.setPort(80);
						requestInfo.setAddress(string.substring(start + 2));
					} else {
						requestInfo.setPort(Integer.valueOf(string.substring(next + 1)));
						requestInfo.setAddress(string.substring(start + 2, next));
					}
					flag = true;
				}else if(lowerCase.startsWith("referer:")){
					requestInfo.setReferer(string.substring(8).trim());
				}else if(lowerCase.startsWith("cookie:")){
					requestInfo.setCookie(string.substring(7).trim());
				}else if (lowerCase.startsWith("range:")) {
					requestInfo.setRange(string);
				}
			}
			requestInfo.setPostParams(param);
		} catch (NumberFormatException | IOException e) {
			System.out.println(e.getLocalizedMessage());
		}
		return flag;
	}
	
	/**
	 * 解析HttpResponse头文件信息
	 * @param responseInfo
	 * @param bfr
	 */
	public static void responseHeaderDataParser(ResponseInfo responseInfo, BufferedReader bfr){
		String string;
		try {
			while ((string = bfr.readLine()) != null) {
				String lowerCase = string.toLowerCase();
				if(lowerCase.startsWith("http")){
					responseInfo.setStatus(string);
				}else if(lowerCase.startsWith("content-type")){
					responseInfo.setContent_Type(string);
				}else if(lowerCase.startsWith("accpect")){
					responseInfo.setAccpect(string);
				}else if(lowerCase.startsWith("content_encoding")){
					responseInfo.setContent_encoding(string);
				}else if(lowerCase.startsWith("transfer_encoding")){
					responseInfo.setTransfer_Encoding(string);
				}
			}
		} catch (NumberFormatException | IOException e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
	
	/**
	 * 解析HttpRequest头文件信息
	 * @param requestInfo
	 * @param bfr
	 * @param param
	 * @return
	 */
	public static RequestHeaderData parseHeader(InputStream is) throws IOException {
		RequestHeaderData data = new RequestHeaderData();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		StringBuffer param = new StringBuffer();
		String header = "";
		int i = -1;
		if (is != null) {
			int len = -1;
			byte[] buf = new byte[1];
			// 0x3A 冒号
			byte[] outBuf = new byte[] { 0x3A };
			int count = 0;
			while ((len = is.read(buf, 0, buf.length)) > -1) {
				baos.write(buf, 0, buf.length);
				int outLength = outBuf.length + len;
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
			if (outBuf.length == 1) {
				return null;
			}
			header = new String(outBuf, 0, outBuf.length);
			String[] splitLine = header.trim().split("\r\n");
			try {
				for (String statusLine : splitLine) {
					String[] nameValue = statusLine.split(":");
					if ("Content-Length".equalsIgnoreCase(nameValue[0])) {
						i = Integer.valueOf(nameValue[1].trim());
						byte[] buff = new byte[i];
						is.read(buff, 0, i);
						baos.write(buff);
						String buffStr = new String(buff,"utf8");
						header+=buffStr;
						param.append(buffStr);
						header = header.substring(1, header.length());
						break;
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println(header);
				e.printStackTrace();
			}
		}
		data.setHeader(header.substring(1, header.length()));
		data.setBytes(baos.toByteArray());
		data.setPostParams(param.toString());
		return data;
	}
	
}
