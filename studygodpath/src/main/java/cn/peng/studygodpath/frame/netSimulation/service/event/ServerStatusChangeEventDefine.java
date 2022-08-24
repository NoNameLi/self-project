package cn.peng.studygodpath.frame.netSimulation.service.event;

import java.awt.*;

public abstract class ServerStatusChangeEventDefine {
	public static String EVENT_NAME = "statusChange";
	
	public static Class<?> oldValueClass = Exception.class;
	public static Class<?> newValueClass = Status.class;
	
	public static enum Status {

		STARTING("启动中", Color.YELLOW.getRGB(), false),
		RUNNING("运行中", Color.GREEN.getRGB(), true),
		STOPPING("停止中", Color.YELLOW.getRGB(), false),
		STOPED("已停止", Color.RED.getRGB(), true);
		
		private String name;
		private int color;
		private boolean buttonEnable;
		/**
		 * @param name
		 * @param color
		 * @param buttonEnable
		 */
		private Status(String name, int color, boolean buttonEnable) {
			this.name = name;
			this.color = color;
			this.buttonEnable = buttonEnable;
		}
		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}
		/**
		 * @return the color
		 */
		public int getColor() {
			return color;
		}
		/**
		 * @return the buttonEnable
		 */
		public boolean isButtonEnable() {
			return buttonEnable;
		}
		
		
	}
}
