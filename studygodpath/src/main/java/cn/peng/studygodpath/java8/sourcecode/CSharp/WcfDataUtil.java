package cn.peng.studygodpath.java8.sourcecode.CSharp;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

/**
 * 
 * wcf 服务 数据传输 会将数据序列化 此工具类可以将数据序列化 和反序列化
 * 
 * 主要原理是 调用 exe（C#编译形成） 使用base64 字符串 进行之间的数据通信
 * 
 * @author lichaopeng
 *
 */
public class WcfDataUtil {
	/**
	 * @param params
	 *            要处理（序列化、反序列化）的数据
	 * @return 处理结果 
	 * 			注： 返回类型为byte[] 不能String化,除非之后不在使用返回结果掉CSharp
	 * 			否则，再次传入CSharp 会编码错误 CSharp和java的byte定义不一样
	 * @throws UnsupportedEncodingException 
	 */
	public static byte[] encodeWcfData(String params) throws UnsupportedEncodingException {
		//序列化数据 base64 加密
		String base64 = Base64.encodeBase64String(params.getBytes());
		// C# 数据序列化
		String base64Result = encodeDecodeWcf(mode.encode.name(), base64);
		//将C#返回结果 base 解密 
		return Base64.decodeBase64(base64Result);
	}

	/**
	 * @param data
	 *            要处理（序列化、反序列化）的数据
	 *            不能使用java String 转化的byte[] ，直接使用Http请求返回结果byte[]
	 * @return 处理结果 
	 * 			注： 返回类型为byte[] 不能String化,除非之后不在使用返回结果掉CSharp
	 * 			否则，再次传入CSharp 会编码错误 CSharp和java的byte定义不一样
	 * @throws UnsupportedEncodingException 
	 */
	public static byte[] decodeWcfData(byte[] data) throws UnsupportedEncodingException {
		//序列化数据 base64 加密
		String base64 = Base64.encodeBase64String(data);
		// C# 数据反序列化
		String base64Result = encodeDecodeWcf(mode.decode.name(), base64);
		//将C#返回结果 base 解密 
		return Base64.decodeBase64(base64Result);
	}
	
	
	
	
	/**
	 * @param strEncodeDecode
	 *            方式 加密 解密
	 * @param strBase64Content
	 *            base64 字符串
	 * @return 调用结果 base64形式
	 */
	private static String encodeDecodeWcf(String strEncodeDecode, String strBase64Content) {
		try {
			String[] commandWithArgs = { "src\\main\\resources\\WCFParseApp.exe", strEncodeDecode, strBase64Content };
			Process p = Runtime.getRuntime().exec(commandWithArgs);
			InputStream input = p.getInputStream();
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buffer = new byte[4096];
			int n = 0;
			while (-1 != (n = input.read(buffer))) {
				output.write(buffer, 0, n);
			}
			byte[] bytedata = output.toByteArray();
			String data = new String(bytedata, "utf-8");
			output.close();
			return data.toString();
		} catch (Exception err) {
			return err.getMessage();
		}
	}
	
	
	private WcfDataUtil() {}
	
	/**
	 * 处理方式
	 */
	private enum mode {
		/**
		 * 加密 序列化
		 */
		encode,
		/**
		 * 解密 反序列化
		 */
		decode;
	}

}
