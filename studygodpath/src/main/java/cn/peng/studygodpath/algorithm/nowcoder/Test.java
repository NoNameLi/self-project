package cn.peng.studygodpath.algorithm.nowcoder;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.*;

/**
 * @Author: Administrator
 * @CreateTime:2022-06-10 17:47
 * QDescription:
 */
public class Test {
    /**
     * 公众账号ID
     */
    private static String appid = "wxe12043817d088b68";
    /**
     * 商户号
     */
    private static String mchid = "1618937171";
    /**
     * 合作key
     */
    private static String key = "EePtlFp07wKoI2nUbo1u3vUAvyQnjJnK";

    private static String PACKAGE = "Sign=WXPay";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            int num = Integer.valueOf(br.readLine());
            String text = br.readLine();
            int sort = Integer.valueOf(br.readLine());
            int[] numArr = new int[num];
            String[] textArr = text.split(" ");
            int length = textArr.length;
            if (textArr[length - 1] == "") {
                length--;
            }
            for (int i = 0; i < length; i++) {
                numArr[i] = Integer.valueOf(textArr[i]);
            }
            Arrays.sort(numArr);
            System.out.println(numArr[num - sort]);
        }
    }

//    public static void main(String[] args) {
//        Map<String, String> paraMapApp = new HashMap<>();
//        //微信开放平台审核通过的应用APPID
//        paraMapApp.put("appid", appid);
//        //	微信支付分配的商户号
//        paraMapApp.put("partnerid", mchid);
//        paraMapApp.put("prepayid", "wx111025042535348a562cc1393d88e60000");
//        paraMapApp.put("package", PACKAGE);
//        paraMapApp.put("noncestr", "y595gQHIN6ybVh05");
//        paraMapApp.put("timestamp", "1654914305");
//
//        String stringSignTempApp = formatUrlMap(paraMapApp, false, false);
//        stringSignTempApp = stringSignTempApp + "&key=" + key;
//        System.out.println(stringSignTempApp);
//        //得到app支付签名
//        String signApp = MD5Utils.MD5Encoding("appid=wxe12043817d088b68&nonceStr=y595gQHIN6ybVh05&package=Sign=WXPay&partnerid=1618937171&prepayid=wx111025042535348a562cc1393d88e60000&timestamp=1654914305&key=EePtlFp07wKoI2nUbo1u3vUAvyQnjJnK").toUpperCase();
//        System.out.println(signApp);
//        paraMapApp.put("sign", signApp);
//        System.out.println(JSONUtil.toJsonStr(paraMapApp));
//    }

    public static String formatUrlMap(Map<String, String> paraMap, boolean urlEncode, boolean keyToLower) {
        String buff = "";
        Map<String, String> tmpMap = paraMap;
        try {
            List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(tmpMap.entrySet());
            // 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
            Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {
                @Override
                public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                    return (o1.getKey()).toString().compareTo(o2.getKey());
                }
            });
            // 构造URL 键值对的格式
            StringBuilder buf = new StringBuilder();
            for (Map.Entry<String, String> item : infoIds) {
                if (StringUtils.isNotBlank(item.getKey())) {
                    String key = item.getKey();
                    String val = item.getValue();
                    if (urlEncode) {
                        val = URLEncoder.encode(val, "utf-8");
                    }
                    if (keyToLower) {
                        buf.append(key.toLowerCase() + "=" + val);
                    } else {
                        buf.append(key + "=" + val);
                    }
                    buf.append("&");
                }

            }
            buff = buf.toString();
            if (buff.isEmpty() == false) {
                buff = buff.substring(0, buff.length() - 1);
            }
        } catch (Exception e) {
            return null;
        }
        return buff;
    }
}
