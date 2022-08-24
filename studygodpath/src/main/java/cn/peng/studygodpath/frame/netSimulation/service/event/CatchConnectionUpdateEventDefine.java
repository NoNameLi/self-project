package cn.peng.studygodpath.frame.netSimulation.service.event;


import cn.peng.studygodpath.frame.netSimulation.service.bean.ConnectionInfo;

public abstract class CatchConnectionUpdateEventDefine {
	public static String EVENT_NAME = "catchConnectionUpdate";
	
	public static Class<?> newValueClass = ConnectionInfo.class;
	
	
}
