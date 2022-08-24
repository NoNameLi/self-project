package cn.peng.studygodpath.frame.netSimulation.service.bean;

public class ResponseInfo {

	private String accpect;
	private String content_encoding;
	private String status;
	private String content_Type;
	private String transfer_Encoding;
	public String getAccpect() {
		return accpect;
	}
	public void setAccpect(String accpect) {
		this.accpect = accpect;
	}
	public String getContent_encoding() {
		return content_encoding;
	}
	public void setContent_encoding(String content_encoding) {
		this.content_encoding = content_encoding;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getContent_Type() {
		return content_Type;
	}
	public void setContent_Type(String content_Type) {
		this.content_Type = content_Type;
	}
	public String getTransfer_Encoding() {
		return transfer_Encoding;
	}
	public void setTransfer_Encoding(String transfer_Encoding) {
		this.transfer_Encoding = transfer_Encoding;
	}
	@Override
	public String toString() {
		return "ResponseInfo [accpect=" + accpect + ", content_encoding=" + content_encoding + ", status=" + status
				+ ", content_Type=" + content_Type + ", transfer_Encoding=" + transfer_Encoding + "]";
	}
}
