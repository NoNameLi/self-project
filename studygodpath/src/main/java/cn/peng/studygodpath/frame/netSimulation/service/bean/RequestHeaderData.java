package cn.peng.studygodpath.frame.netSimulation.service.bean;

public class RequestHeaderData {
	
	private byte[] bytes;
	private String header;
	private String postParams;
	
	
	public String getPostParams() {
		return postParams;
	}
	public void setPostParams(String postParams) {
		this.postParams = postParams;
	}
	public byte[] getBytes() {
		return bytes;
	}
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	
}
