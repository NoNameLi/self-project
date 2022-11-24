package cn.peng.studygodpath.frame.netSimulation.service;

import cn.peng.studygodpath.frame.netSimulation.service.bean.ConnectionInfo;
import cn.peng.studygodpath.frame.netSimulation.service.bean.HttpInputStream;
import cn.peng.studygodpath.frame.netSimulation.service.bean.RequestInfo;
import cn.peng.studygodpath.frame.netSimulation.service.event.CatchConnectionAddEventDefine;
import cn.peng.studygodpath.frame.netSimulation.service.event.CatchConnectionUpdateEventDefine;
import cn.peng.studygodpath.frame.netSimulation.service.io.OutputPipeThread;
import cn.peng.studygodpath.frame.netSimulation.util.FileUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.beans.PropertyChangeSupport;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataPreparationService {
	
	private  Logger logger = Logger.getLogger(getClass());
	
	private Boolean isFilterURL = false;
	
	/**
	 * 代理http请求
	 * @param request
	 * @param client
	 * @param info
	 * @param eventSupport
	 */
	public  void  pipe(byte[] request, Socket client, ConnectionInfo info , PropertyChangeSupport eventSupport){
		RequestInfo requestInfo = info.getRequestInfo();
		isFilterURL = filterURL(requestInfo.getRequest_URL());
		// 连接厂商的socket
		try {
			OutputStream clientOS = client.getOutputStream();
			Socket socket = new Socket(requestInfo.getAddress(), requestInfo.getPort());
			try {
				OutputPipeThread responseThread = new OutputPipeThread();
				if(!isFilterURL) {
					info.setSaved(checkSavedFile(info));
					eventSupport.firePropertyChange(CatchConnectionAddEventDefine.EVENT_NAME, null, info);
					socket.setSoTimeout(80000);
				} else {
					socket.setSoTimeout(1000);
				}
				OutputStream os = socket.getOutputStream();
				InputStream is = socket.getInputStream();
				OutputStream requestOS = info.getRequestInfoOutputStream();
				
				// change data source 如果是规定请求，且是本地读取数据则切换数据源
				if(!isFilterURL	
						&& ConfigService.getInstance().isLocal
						&& info.isSaved()
						){
					String url = findLocalDataSource(requestInfo, ConfigService.getInstance().local);
					System.out.println("use : " + url);
					is = new FileInputStream(url);
					info.setCacheAimed(info.getCacheAimed()+1);
					eventSupport.firePropertyChange(CatchConnectionUpdateEventDefine.EVENT_NAME, null, info);
				}else{
					// 写入请求参数，将requestHeader写入
					os.write(request,0 ,request.length);
					requestOS.write(request,0 ,request.length);
				}
				try {
					// write response
					if (requestInfo.getRange() == null) {
						HttpInputStream httpIs = new HttpInputStream(is);
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						OutputStream responseOS = info.getResponseInfoOutputStream();
						responseThread.setInputStream(httpIs).addOutputStreams(baos);
						responseThread.run();
						String str = httpIs.getHeaders();
						str = removeHeaderParam(str,"Date");
						byte [] baosbyte = baos.toByteArray();
						if(httpIs.isChunked()){
							str = removeHeaderParam(str, "Transfer-Encoding");
							str = removeHeaderParam(str, "Content-Length");
							str = addHeaderParam(str,"Content-Length: " + baosbyte.length);
						}
						byte[] headers = str.getBytes();
						BufferedReader bfr = new BufferedReader(
								new InputStreamReader(new ByteArrayInputStream(headers)));
						HeaderAnalyticalUtils.responseHeaderDataParser(info.getResponseInfo(), bfr);
						clientOS.write(headers, 0, headers.length);
						responseOS.write(headers, 0, headers.length);
						
						clientOS.write(baosbyte);
						responseOS.write(baosbyte);
					} else {
						File tmpFile = File.createTempFile("biz", "range");
						OutputStream fos = new FileOutputStream(tmpFile);
						try {
							responseThread.setInputStream(is).addOutputStreams(fos).run();
							System.out.println("fetch file...");
							String[] ranges = requestInfo.getRange().split("[=-]");
							int starti = Integer.parseInt(ranges[1]);
							int endi = Integer.parseInt(ranges[2]);
							FileInputStream fis = new FileInputStream(tmpFile);
							OutputPipeThread filePipeThread = new OutputPipeThread();
							filePipeThread.addOutputStreams(clientOS).setInputStream(fis).setSkip(starti).setReadLen(endi-starti).run();
							filePipeThread.close();
							tmpFile.delete();
						} catch (Exception e) {
							logger.error("target Socket exception:" + e.getLocalizedMessage());
						}
					}
					requestOS.close();
					is.close();
					os.close();
					socket.close();
					info.setFinished(true);
					logger.info(info.getRequestInfo().getRequest_URL()+ " is finished");
					eventSupport.firePropertyChange(CatchConnectionUpdateEventDefine.EVENT_NAME, null, info);
				} catch (Exception e) {
					logger.error("client Socket exception:" + e.getLocalizedMessage(), e);
				}
			} catch (UnknownHostException e) {
				logger.error("UnknownHostException : "+e.getLocalizedMessage());
			} catch (IOException e) {
				logger.error("IOException : "+e.getLocalizedMessage());
			}
		} catch (IOException e) {
			logger.error("IOException : "+e.getLocalizedMessage());
		}
	}
	
	/**
	 * 准备请求保存时的文件路径及文件名
	 * @param requestInfo
	 * @param local
	 * @return
	 */
	public static String fileUrlPrepare(RequestInfo requestInfo, String local){
		String file = "";
		String host = requestInfo.getHost();
		if(host.toLowerCase().startsWith("host:")){
			host = host.replace("host:", "").trim();
		}
		String Request_URL = host+requestInfo.getRequest_URL();
		String[]str = Request_URL.split("\\?");
		file += (File.separatorChar + str[0]);
		file = StringUtils.replace(file, "//", "#"); // replace // to #
		file = StringUtils.replace(file, ":", "#"); // replace : to #
		file = StringUtils.replaceChars(file, '/', File.separatorChar );
		StringBuffer fileUrl = new StringBuffer();
		fileUrl.append(local).append(file).append(File.separatorChar).append(requestInfo.getRequest_Method());
		if(str.length==2){
			fileUrl.append(File.separatorChar).append(str[1].replaceAll("&", "_"));
		}
		fileUrl.append(".har");
		return fileUrl.toString();
	}
	
	/**
	 * 过滤url请求
	 * @param url
	 * @return
	 */
	private static Boolean filterURL(String url){
		try {
			Pattern pattern= Pattern.compile(ConfigService.getInstance().urlRegex);
		    Matcher m=pattern.matcher(url);
			return m.find();
		} catch (Exception e) {
			System.out.println("error :"+ url);
		}
		return false;
	}
	
	/**
	 * 检查请求信息是否被缓存
	 * @param info
	 * @return
	 */
	public static boolean checkSavedFile(ConnectionInfo info) {
		if(ConfigService.getInstance().isSave){
			String filePath = findLocalDataSource(info.getRequestInfo(),ConfigService.getInstance().local);
			if(filePath==null){
				return false;
			}
			File f = new File(filePath);
			return f.isFile();
		}else{
			return false;
		}
	}
	
	/**
	 * 通过request信息，查找缓存文件地址
	 * @param requestInfo
	 * @param local
	 * @return
	 */
	public static String findLocalDataSource(RequestInfo requestInfo, String local){
		String fileUrl = fileUrlPrepare(requestInfo,local);
		int last = fileUrl.lastIndexOf(File.separatorChar);
		String folderName = fileUrl.substring(0, last+1);
		List<String> files = FileUtil.findAllFileName(folderName,".har");
		Map<String, String> regExp = Name2RegExp(files);
		String name = matcherFile(fileUrl,regExp);
		if(name==null){
			return null;
		}
		return folderName+name;
	}
	
	/**
	 * 将文件名转化为相应的正则表达式
	 * @param names
	 * @return
	 */
	public static Map<String, String> Name2RegExp(List<String> names){
		Map<String, String> regs = new HashMap<String, String>();
		for (String string : names) {
			String[] params = string.substring(0, string.length()-4).split("_");
			StringBuffer reg = new StringBuffer();
			reg.append(".*");
			for (String string2 : params) {
				reg.append(string2).append(".*");
			}
			regs.put(string, reg.toString());
		}
		return regs;
	}
	
	/**
	 * 匹配相应文件
	 * @param url
	 * @param regs
	 * @return
	 */
	public static String matcherFile(String url, Map<String, String> regs){
		String fileName = null;
		for (String file : regs.keySet()) {
			try {
				Pattern pattern= Pattern.compile(regs.get(file));
			    Matcher m=pattern.matcher(url);
			    if(m.find()){
			    	fileName = file;
			    	break;
			    }
			} catch (Exception e) {
				System.out.println("error :"+ url);
			}
		}
		return fileName;
	}
	
	/**
	 * 查找头信息中的相关参数
	 * @param headers
	 * @param name
	 * @return
	 */
	public String findHeaderParam(String headers, String name){
		String str = ":"+headers;
		String[] splitLine = str.trim().split("\r\n");
		String value = null;
		for (String statusLine : splitLine) {
			String[] nameValue = statusLine.split(":");
			if(name.equals(nameValue[0].trim())){
				value = nameValue[1];
				break;
			}
		}
		return value;
	}
	
	/**
	 * 删除头信息中的相关参数
	 * @param headers
	 * @param removeName
	 * @return
	 */
	public String removeHeaderParam(String headers, String... removeName){
		StringBuffer sb = new StringBuffer();
		Set<String> set = new HashSet<String>();
		for (String name : removeName) {
			set.add(name);
		}
		String str = ":"+headers;
		String[] splitLine = str.trim().split("\r\n");
		for (String statusLine : splitLine) {
			String[] nameValue = statusLine.split(":");
			if(!set.contains(nameValue[0].trim())){
				sb.append(statusLine).append("\r\n");
			}
		}
		sb.append("\r\n");
		return sb.substring(1,sb.toString().length());
	}
	
	/**
	 * 向头信息中添加参数
	 * @param headers
	 * @param param
	 * @return
	 */
	public String addHeaderParam(String headers, String param){
		StringBuffer sb = new StringBuffer();
		sb.append(headers.substring(0, StringUtils.lastIndexOf(headers, "\r\n")));
		sb.append(param).append("\r\n\r\n");
		return sb.toString();
	}
}
