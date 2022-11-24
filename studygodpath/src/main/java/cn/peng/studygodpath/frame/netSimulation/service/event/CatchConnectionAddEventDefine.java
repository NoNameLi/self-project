package cn.peng.studygodpath.frame.netSimulation.service.event;


import cn.peng.studygodpath.frame.netSimulation.service.bean.ConnectionInfo;

public abstract class CatchConnectionAddEventDefine {
	public static String EVENT_NAME = "catchConnectionAdd";
	
	public static Class<?> newValueClass = ConnectionInfo.class;
	
	
}
