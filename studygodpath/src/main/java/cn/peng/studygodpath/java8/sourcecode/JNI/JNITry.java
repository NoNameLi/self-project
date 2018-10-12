package cn.peng.studygodpath.java8.sourcecode.JNI;

import java.util.Arrays;

/**
 * JNI本地接口
 java类中声明：native 方法
 javac XXX.java 生成class文件
 javah -classpath . -jni 包名.XXX
 注：javah 不要加class后缀名
 */
public class JNITry{
	
	static{
		//JNITry.class.getResource("/").getFile().substring(1)+"JNITry"
//		System.loadLibrary("JNITry");
		System.load(JNITry.class.getResource("/").getFile().substring(1)+"JNITry.dll");
	}
	
	public static native String hello();
	public static native int add(int... nums);

	public static void main(String[] args) {
		System.out.println(JNITry.add(1,2,3,4));
		JNITry.hello();
	}
	
}