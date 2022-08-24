package cn.peng.studygodpath.frame.netSimulation.service;


import cn.peng.studygodpath.frame.netSimulation.service.bean.Config;

public class ConfigService {
	
	private static Config config = new Config();
	
	public static Config getInstance() {
		return config;
	}
	
}
