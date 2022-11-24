package cn.peng.studygodpath.frame.netSimulation.service.bean;

public class RequestInfo {
	private String request_URL;

	private String request_Method;
	
	private String transfer_Version;
	
	private String host;
	
	private String proxy_Connection;
	
	private String pragma;
	
	private String Cache_Control;
	
	private String user_Agent;
	
	private String accept;
	
	private String referer;
	
	private String accept_Encoding;
	
	private String accept_Language;
	
	private String cookie;
	
	private String address;
	
	private int port;

	private String postParams ;
	
	private String range;
	
	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public String getRequest_URL() {
		return request_URL;
	}

	public void setRequest_URL(String request_URL) {
		this.request_URL = request_URL;
	}

	public String getRequest_Method() {
		return request_Method;
	}

	public void setRequest_Method(String request_Method) {
		this.request_Method = request_Method;
	}

	public String getTransfer_Version() {
		return transfer_Version;
	}

	public void setTransfer_Version(String transfer_Version) {
		this.transfer_Version = transfer_Version;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getProxy_Connection() {
		return proxy_Connection;
	}

	public void setProxy_Connection(String proxy_Connection) {
		this.proxy_Connection = proxy_Connection;
	}

	public String getPragma() {
		return pragma;
	}

	public void setPragma(String pragma) {
		this.pragma = pragma;
	}

	public String getCache_Control() {
		return Cache_Control;
	}

	public void setCache_Control(String cache_Control) {
		Cache_Control = cache_Control;
	}

	public String getUser_Agent() {
		return user_Agent;
	}

	public void setUser_Agent(String user_Agent) {
		this.user_Agent = user_Agent;
	}

	public String getAccept() {
		return accept;
	}

	public void setAccept(String accept) {
		this.accept = accept;
	}

	public String getReferer() {
		return referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}

	public String getAccept_Encoding() {
		return accept_Encoding;
	}

	public void setAccept_Encoding(String accept_Encoding) {
		this.accept_Encoding = accept_Encoding;
	}

	public String getAccept_Language() {
		return accept_Language;
	}

	public void setAccept_Language(String accept_Language) {
		this.accept_Language = accept_Language;
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getPostParams() {
		return postParams;
	}

	public void setPostParams(String postParams) {
		this.postParams = postParams;
	}
	
	
}
