package cn.peng.studygodpath.algorithm.nowcoder;

import cn.hutool.crypto.digest.MD5;
import cn.hutool.json.JSONUtil;
import org.apache.commons.lang3.StringUtils;

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

    public static void main(String[] args) {
        Map<String, String> paraMapApp = new HashMap<>();
        //微信开放平台审核通过的应用APPID
        paraMapApp.put("appid", appid);
        //	微信支付分配的商户号
        paraMapApp.put("partnerid", mchid);
        paraMapApp.put("prepayid", "wx101738016773501a246e7a125e2def0000");
        paraMapApp.put("package", PACKAGE);
        paraMapApp.put("noncestr", "AIA3FgIkzKZ2eEbH");
        paraMapApp.put("timestamp", "1654853881");

        String stringSignTempApp = formatUrlMap(paraMapApp, false, false);
        stringSignTempApp = stringSignTempApp + "&key=" + key;
        //得到app支付签名
        String signApp = MD5Utils.MD5Encoding(stringSignTempApp).toUpperCase();
        paraMapApp.put("sign", signApp);
        System.out.println(JSONUtil.toJsonStr(paraMapApp));
    }

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
