package cn.peng.studygodpath.frame.netSimulation.service.bean;

import java.io.*;

/**
 * 一次连接的所有信息.
 *
 * @author Weicl
 */
public class ConnectionInfo {
	
	/**  请求信息. */
	private RequestInfo requestInfo = new RequestInfo();
	
	/**  响应信息. */
	private ResponseInfo responseInfo = new ResponseInfo();
	
	/**  请求是否已经结束. */
	private boolean finished;
	
	/** 本地文件状态 */
	private String localStatus;
	
	/** 是否已经保存 */
	private boolean saved;
	
	/** 缓存命中数 */
	private int cacheAimed = 0;
	
	/**  请求信息原始数据-临时文件. */
	private File tempFileReq;
	
	/**  响应信息原始数据-临时文件. */
	private File tempFileRes;
	
	public ConnectionInfo() throws IOException {
		tempFileReq = File.createTempFile("request", ".har");
		tempFileRes = File.createTempFile("response", ".har");
	}

	/**
	 * 获取 请求信息.
	 *
	 * @return the requestInfo
	 */
	public RequestInfo getRequestInfo() {
		return requestInfo;
	}

	/**
	 * 设置 请求信息.
	 *
	 * @param requestInfo the requestInfo to set
	 */
	public void setRequestInfo(RequestInfo requestInfo) {
		this.requestInfo = requestInfo;
	}

	/**
	 * 获取 响应信息.
	 *
	 * @return the responseInfo
	 */
	public ResponseInfo getResponseInfo() {
		return responseInfo;
	}

	/**
	 * 设置 响应信息.
	 *
	 * @param responseInfo the responseInfo to set
	 */
	public void setResponseInfo(ResponseInfo responseInfo) {
		this.responseInfo = responseInfo;
	}
	
	public void destroy() {
		tempFileReq.delete();
		tempFileRes.delete();
	}
	
	@Override
	protected void finalize() throws Throwable {
		destroy();
	}
	
	public void parseRequestInfo(InputStream requestIs) {
		
	}
	
	public InputStream getRequestInfoInputStream() throws FileNotFoundException {
		return new FileInputStream(tempFileReq);
	}
	
	public InputStream getResponseInfoinputStream() throws FileNotFoundException {
		return new FileInputStream(tempFileRes);
	}
	
	public OutputStream getRequestInfoOutputStream() throws FileNotFoundException {
		return new FileOutputStream(tempFileReq);
	}
	
	public OutputStream getResponseInfoOutputStream() throws FileNotFoundException {
		return new FileOutputStream(tempFileRes);
	}
	

	/**
	 * @return the finished
	 */
	public boolean isFinished() {
		return finished;
	}

	/**
	 * @param finished the finished to set
	 */
	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	/**
	 * @return the localStatus
	 */
	public String getLocalStatus() {
		return localStatus;
	}

	/**
	 * @param localStatus the localStatus to set
	 */
	public void setLocalStatus(String localStatus) {
		this.localStatus = localStatus;
	}

	/**
	 * @return the saved
	 */
	public boolean isSaved() {
		return saved;
	}

	/**
	 * @param saved the saved to set
	 */
	public void setSaved(boolean saved) {
		this.saved = saved;
	}

	/**
	 * @return the cacheAimed
	 */
	public int getCacheAimed() {
		return cacheAimed;
	}

	/**
	 * @param cacheAimed the cacheAimed to set
	 */
	public void setCacheAimed(int cacheAimed) {
		this.cacheAimed = cacheAimed;
	}

	/**
	 * @return the tempFileReq
	 */
	public File getTempFileReq() {
		return tempFileReq;
	}

	/**
	 * @return the tempFileRes
	 */
	public File getTempFileRes() {
		return tempFileRes;
	}
}
