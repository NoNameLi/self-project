package cn.peng.studygodpath.frame.netSimulation.service.io;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 输出流拷贝线程
 * @author Weicl
 *
 */
public class OutputPipeThread extends Thread {
	private Logger logger = Logger.getLogger(getClass());
	
	private InputStream inputStream;
	
	private List<OutputStream> outputStreams = new ArrayList<OutputStream>();

	private int skip = 0;
	
	private int readLen = 0;
	
	public OutputPipeThread setSkip(int skip) {
		this.skip = skip;
		return this;
	}


	public OutputPipeThread setReadLen(int readLen) {
		this.readLen = readLen;
		return this;
	}

	/**
	 * @param inputStream
	 * @param outputStreams
	 */
	public OutputPipeThread(InputStream inputStream,
                            OutputStream... outputStreams) {
		this.setInputStream(inputStream).addOutputStreams(outputStreams);
	}

	/**
	 * 
	 */
	public OutputPipeThread() {
	}
	
	public OutputPipeThread setInputStream(InputStream is) {
		this.inputStream = is;
		return this;
	}
	
	public OutputPipeThread setOutputStreams(List<OutputStream> outputStreams) {
		this.outputStreams = outputStreams;
		return this;
	}
	
	public OutputPipeThread addOutputStreams(OutputStream... outputStreamArray) {
		for (int i = 0; i < outputStreamArray.length; i++) {
			outputStreams.add(outputStreamArray[i]);
		}
		return this;
	}
	
	@Override
	public void run() {
		byte[] buff = new byte[32 * 1024];
		try {
			inputStream.skip(skip);
			while ((readLen = inputStream.read(buff)) != -1) {
				for (OutputStream os : outputStreams) {
					os.write(buff, 0, readLen);
				}
			}
			System.out.println("copy finish");
		} catch (IOException e) {
			logger.error("复制输入输出流出错  : "+e.getLocalizedMessage());
		}
		super.run();
	}
	
	public void close(){
		try {
			for (OutputStream outputStream : outputStreams) {
				if(outputStream!=null){
					outputStream.close();
				}
			}
			if(inputStream!=null){
				inputStream.close();
			}
		} catch (IOException e) {
			logger.error(e.getLocalizedMessage());
		}
	}
	
}
