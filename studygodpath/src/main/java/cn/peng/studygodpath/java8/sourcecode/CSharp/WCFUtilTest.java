package cn.peng.studygodpath.java8.sourcecode.CSharp;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class WCFUtilTest {

	public static void main(String[] args) throws Exception {
		httpTest();
	}
	
	public static void base64Test() throws UnsupportedEncodingException {
//		//使用base64
//		String xml = "<getListAll xmlns=\"http://tempuri.org/\"><queryParam xmlns:a=\"http://schemas.datacontract.org/2004/07/ZyCRM.Web.Model\" xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\"><a:num1>0</a:num1><a:num2>0</a:num2><a:num3>0</a:num3><a:num4>0</a:num4><a:num5>0</a:num5><a:num6>0</a:num6><a:str1 i:nil=\"true\"></a:str1><a:str10 i:nil=\"true\"></a:str10><a:str11 i:nil=\"true\"></a:str11><a:str12 i:nil=\"true\"></a:str12><a:str13 i:nil=\"true\"></a:str13><a:str14 i:nil=\"true\"></a:str14><a:str15 i:nil=\"true\"></a:str15><a:str16 i:nil=\"true\"></a:str16><a:str17 i:nil=\"true\"></a:str17><a:str18 i:nil=\"true\"></a:str18><a:str19 i:nil=\"true\"></a:str19><a:str2 i:nil=\"true\"></a:str2><a:str20 i:nil=\"true\"></a:str20><a:str21 i:nil=\"true\"></a:str21><a:str22 i:nil=\"true\"></a:str22><a:str23 i:nil=\"true\"></a:str23><a:str24 i:nil=\"true\"></a:str24><a:str25 i:nil=\"true\"></a:str25><a:str26 i:nil=\"true\"></a:str26><a:str27 i:nil=\"true\"></a:str27><a:str28 i:nil=\"true\"></a:str28><a:str29 i:nil=\"true\"></a:str29><a:str3 i:nil=\"true\"></a:str3><a:str30 i:nil=\"true\"></a:str30><a:str4 i:nil=\"true\"></a:str4><a:str5 i:nil=\"true\"></a:str5><a:str6 i:nil=\"true\"></a:str6><a:str7 i:nil=\"true\"></a:str7><a:str8 i:nil=\"true\"></a:str8><a:str9 i:nil=\"true\"></a:str9></queryParam></getListAll>";
//		String cs64 = WcfDataUtil.encodeDecodeWcf(mode.encode.name(), Base64Util.getBase64(xml));
//		System.out.println(cs64);
//		System.out.println(Base64Util.getBase64(Base64Util.getFromBase64(cs64)));
	}
	
	public static void httpTest() throws Exception {
		
		String xml = "<getListAll xmlns=\"http://tempuri.org/\"><queryParam xmlns:a=\"http://schemas.datacontract.org/2004/07/ZyCRM.Web.Model\" xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\"><a:num1>0</a:num1><a:num2>0</a:num2><a:num3>0</a:num3><a:num4>0</a:num4><a:num5>0</a:num5><a:num6>0</a:num6><a:str1 i:nil=\"true\"></a:str1><a:str10 i:nil=\"true\"></a:str10><a:str11 i:nil=\"true\"></a:str11><a:str12 i:nil=\"true\"></a:str12><a:str13 i:nil=\"true\"></a:str13><a:str14 i:nil=\"true\"></a:str14><a:str15 i:nil=\"true\"></a:str15><a:str16 i:nil=\"true\"></a:str16><a:str17 i:nil=\"true\"></a:str17><a:str18 i:nil=\"true\"></a:str18><a:str19 i:nil=\"true\"></a:str19><a:str2 i:nil=\"true\"></a:str2><a:str20 i:nil=\"true\"></a:str20><a:str21 i:nil=\"true\"></a:str21><a:str22 i:nil=\"true\"></a:str22><a:str23 i:nil=\"true\"></a:str23><a:str24 i:nil=\"true\"></a:str24><a:str25 i:nil=\"true\"></a:str25><a:str26 i:nil=\"true\"></a:str26><a:str27 i:nil=\"true\"></a:str27><a:str28 i:nil=\"true\"></a:str28><a:str29 i:nil=\"true\"></a:str29><a:str3 i:nil=\"true\"></a:str3><a:str30 i:nil=\"true\"></a:str30><a:str4 i:nil=\"true\"></a:str4><a:str5 i:nil=\"true\"></a:str5><a:str6 i:nil=\"true\"></a:str6><a:str7 i:nil=\"true\"></a:str7><a:str8 i:nil=\"true\"></a:str8><a:str9 i:nil=\"true\"></a:str9></queryParam></getListAll>";
		String url = "http://qxy.yadu.com/ClientBin/ZyCRM-Web-Service-Pub-PpService.svc/binary/getListAll";
		String contentType = "application/msbin1";
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-type", contentType);
		headers.put("Expect", "100-continue");
		
		byte[] params = WcfDataUtil.encodeWcfData(xml);
		
//		HttpResEntity resEntity = HttpUtil.doPost(false, url, headers, params);
//
//		byte[] requestData = WcfDataUtil.decodeWcfData(resEntity.getByteData());
		
//		System.out.println(new String(requestData));
	}

	public static void utilTest() throws UnsupportedEncodingException {
//		String xml = "<getListAll xmlns=\"http://tempuri.org/\"><queryParam xmlns:a=\"http://schemas.datacontract.org/2004/07/ZyCRM.Web.Model\" xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\"><a:num1>0</a:num1><a:num2>0</a:num2><a:num3>0</a:num3><a:num4>0</a:num4><a:num5>0</a:num5><a:num6>0</a:num6><a:str1 i:nil=\"true\"></a:str1><a:str10 i:nil=\"true\"></a:str10><a:str11 i:nil=\"true\"></a:str11><a:str12 i:nil=\"true\"></a:str12><a:str13 i:nil=\"true\"></a:str13><a:str14 i:nil=\"true\"></a:str14><a:str15 i:nil=\"true\"></a:str15><a:str16 i:nil=\"true\"></a:str16><a:str17 i:nil=\"true\"></a:str17><a:str18 i:nil=\"true\"></a:str18><a:str19 i:nil=\"true\"></a:str19><a:str2 i:nil=\"true\"></a:str2><a:str20 i:nil=\"true\"></a:str20><a:str21 i:nil=\"true\"></a:str21><a:str22 i:nil=\"true\"></a:str22><a:str23 i:nil=\"true\"></a:str23><a:str24 i:nil=\"true\"></a:str24><a:str25 i:nil=\"true\"></a:str25><a:str26 i:nil=\"true\"></a:str26><a:str27 i:nil=\"true\"></a:str27><a:str28 i:nil=\"true\"></a:str28><a:str29 i:nil=\"true\"></a:str29><a:str3 i:nil=\"true\"></a:str3><a:str30 i:nil=\"true\"></a:str30><a:str4 i:nil=\"true\"></a:str4><a:str5 i:nil=\"true\"></a:str5><a:str6 i:nil=\"true\"></a:str6><a:str7 i:nil=\"true\"></a:str7><a:str8 i:nil=\"true\"></a:str8><a:str9 i:nil=\"true\"></a:str9></queryParam></getListAll>";
//		String result = WcfDataUtil.encodeWcfData(mode.encode, xml);
//		System.out.println(result);
//		System.out.println(WcfDataUtil.encodeWcfData(mode.decode, result));
	}

}
