package cn.peng.studygodpath.frame.netSimulation.proxy;

import cn.peng.studygodpath.frame.netSimulation.service.DataPreparationService;
import cn.peng.studygodpath.frame.netSimulation.service.HeaderAnalyticalUtils;
import cn.peng.studygodpath.frame.netSimulation.service.bean.ConnectionInfo;
import cn.peng.studygodpath.frame.netSimulation.service.bean.RequestHeaderData;
import cn.peng.studygodpath.frame.netSimulation.service.bean.RequestInfo;
import cn.peng.studygodpath.frame.netSimulation.service.bean.ResponseInfo;
import org.apache.log4j.Logger;

import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class HTTPSession implements Runnable {

	private Logger logger = Logger.getLogger(getClass());
	private Socket mySocket;
	private PropertyChangeSupport eventSupport;
	static int bufsize = 8192;

	public HTTPSession(Socket socket, PropertyChangeSupport eventSupport) {
		this.eventSupport = eventSupport;
		mySocket = socket;
	}

	public void run() {
		try {
			InputStream is = mySocket.getInputStream();
			// 同一个socket 被多次利用发送httpRequest
			while(!mySocket.isInputShutdown()){ 
				// 解析读取头信息
				RequestHeaderData header = HeaderAnalyticalUtils.parseHeader(is);
				if (header == null) {
					break;
				}
				ConnectionInfo info = new ConnectionInfo();
				RequestInfo requestInfo = new RequestInfo();
				info.setRequestInfo(requestInfo);
				ResponseInfo responseInfo = new ResponseInfo();
				info.setResponseInfo(responseInfo);
				BufferedReader bfr = new BufferedReader(
						new InputStreamReader(new ByteArrayInputStream(header.getBytes())));
				if(!HeaderAnalyticalUtils.headerDataParser(requestInfo, bfr,header.getPostParams())){
					mySocket.getOutputStream().write("error!".getBytes());
					mySocket.close();
					return;
				}
				// 代理
				new DataPreparationService().pipe(header.getBytes(),mySocket,info,eventSupport);
			}
			mySocket.close();
			System.out.println("connection close");
		} catch (Exception e) {
			logger.error("Run Exception!" + e.getLocalizedMessage(), e);
		}
	}
}
