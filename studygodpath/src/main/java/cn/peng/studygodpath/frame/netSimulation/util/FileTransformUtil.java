package cn.peng.studygodpath.frame.netSimulation.util;

import cn.peng.studygodpath.frame.netSimulation.service.bean.HttpInputStream;
import cn.peng.studygodpath.frame.netSimulation.service.io.OutputPipeThread;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.GZIPInputStream;

public class FileTransformUtil {
	
	public static void httpFileTransform(File fromFile , File toFile) throws Exception {
		FileUtil.makeDirectory(toFile);
		FileInputStream fis = new FileInputStream(fromFile);
		HttpInputStream his = new HttpInputStream(fis);
		OutputStream out = new FileOutputStream(toFile);
		String header = his.getHeaders();
		List<String> remove = new ArrayList<String>();
		if(his.isGzip()){
			remove.add("Content-Encoding");
		}
		if(his.isChunked()){
			remove.add("Transfer-Encoding");
		}
		remove.add("Date");
		remove.add("Etag");
		header = removeHeaderParam(header,remove.toArray());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		InputStream is;
		OutputPipeThread thread = new OutputPipeThread();
		if(!his.isGzip()){
			is = his;
		}else{
			is = new GZIPInputStream(his);
		}
		thread.setInputStream(is);
		thread.addOutputStreams(baos);
		thread.start();
		thread.join();
		if(his.isGzip()){
			header = removeHeaderParam(header,"Content-Length");
			header = addHeaderParam(header,"Content-Length: "+baos.size());
		}
		out.write(header.getBytes(), 0, header.getBytes().length);
		out.write(baos.toByteArray(),0,baos.toByteArray().length);
		his.close();
		is.close();
		out.close();
		fis.close();
	}
	
	public static String removeHeaderParam(String headers, Object... object){
		StringBuffer sb = new StringBuffer();
		Set<String> set = new HashSet<String>();
		for (Object name : object) {
			set.add(name.toString());
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
	
	public static String addHeaderParam(String headers, String param){
		StringBuffer sb = new StringBuffer();
		sb.append(headers.substring(0, StringUtils.lastIndexOf(headers, "\r\n")));
		sb.append(param).append("\r\n\r\n");
		return sb.toString();
	}

}
