package cn.peng.studygodpath.spider;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ChenBuWanSpider {
    public static void main(String[] args) throws Exception {
//        first();
//        second();
        third();
    }

    public static void third() throws Exception {
        String tmp = "F:\\test";
        File parent = new File(tmp), pdfDir = new File(tmp, "pdf");
        if (!pdfDir.exists()) {
            pdfDir.mkdir();
        }

        File[] dirs = parent.listFiles((file, name) -> !name.contains("."));
        assert dirs != null;
        for (File dir : dirs) {
            String fileName = dir.getName();
            try (PDDocument document = new PDDocument()) {
                File[] images = dir.listFiles();
                if (!Objects.isNull(images)) {
                    Arrays.stream(images).forEach(item -> {
                        try {
                            PDImageXObject image = PDImageXObject.createFromFileByExtension(item, document);
                            PDPage page = new PDPage(new PDRectangle(image.getWidth(), image.getHeight()));
                            document.addPage(page);
                            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                                contentStream.drawImage(image, 0, 0);
                            }
                        } catch (Exception e) {
                            System.out.println(String.format("文件：{%s},分片：{%s}合成失败", fileName, item));
                        }
                    });
                    File file = new File(pdfDir, fileName + ".pdf");
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    document.save(file);
                    System.out.println("保存文件：" + fileName);
                }
            }
        }
    }

    public static void second() throws FileNotFoundException, InterruptedException {
        String tmp = "F:\\test";
        File file = new File(tmp, "tmp.txt");
        String string = FileUtil.readString(file, StandardCharsets.UTF_8);
        JSONObject obj = JSONUtil.parseObj(string);

        ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 20, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<>(100000000));
        for (String name : obj.keySet()) {
            File dir = new File(tmp, name.trim());
            if (!dir.exists()) {
                dir.mkdir();
            }
            List<String> list = obj.getJSONArray(name).toList(String.class);
            for (Object item : list) {
                String url = item.toString();
                pool.execute(() -> {
                    File image = FileUtil.file(dir, StrUtil.subSuf(url, url.lastIndexOf('/') + 1));
                    if (!image.exists()) {
                        try {
                            HttpUtil.downloadFile(url, image);
                            System.out.println("下载完成:" + url);
                        } catch (Exception e) {
                            System.out.println("------------------------------------------------下载失败：" + url);
                        }
                    }
                });
            }
        }
        pool.shutdown();
        pool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
    }

    public static void first() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("Host", "chenbuwan.com");
        map.put("Connection", "keep-alive");
        map.put("xweb_xhr", "1");
        map.put("openid", "o_KyM6yTKYDzvWKY0_TqPA2E1e0I");
        map.put("token", "o_KyM6yTKYDzvWKY0_TqPA2E1e0I");
        map.put("platform", "mini");
        map.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36 MicroMessenger/7.0.20.1781(0x6700143B) NetType/WIFI MiniProgramEnv/Windows WindowsWechat/WMPF WindowsWechat(0x6309080f)XWEB/8519");
        map.put("unionid", "");
        map.put("Content-Type", "application/json");
        map.put("Accept", "*/*");
        map.put("Accept-Language", "*");
        map.put("Sec-Fetch-Site", "cross-site");
        map.put("Sec-Fetch-Mode", "cors");
        map.put("Sec-Fetch-Dest", "empty");
        map.put("Referer", "https://servicewechat.com/wxe0e3e946bf14cbdd/5/page-frame.html");
        map.put("Accept-Encoding", "gzip, deflate, br");

        String codeJson = "[{\"name\":\"格力\",\"pic_url\":\"https://chenbuwan.com/KF/info/格力.png\",\"url\":\"210\"},{\"name\":\"美的\",\"pic_url\":\"https://chenbuwan.com/KF/info/美的.png\",\"url\":\"220\"},{\"name\":\"大金\",\"pic_url\":\"https://chenbuwan.com/KF/info/大金.png\",\"url\":\"230\"},{\"name\":\"海尔\",\"pic_url\":\"https://chenbuwan.com/KF/info/海尔.png\",\"url\":\"240\"},{\"name\":\"奥克斯\",\"pic_url\":\"https://chenbuwan.com/KF/info/奥克斯.png\",\"url\":\"250\"},{\"name\":\"海信\",\"pic_url\":\"https://chenbuwan.com/KF/info/海信.png\",\"url\":\"260\"},{\"name\":\"约克\",\"pic_url\":\"https://chenbuwan.com/KF/info/约克.png\",\"url\":\"270\"},{\"name\":\"富士通\",\"pic_url\":\"https://chenbuwan.com/KF/info/富士通.png\",\"url\":\"280\"},{\"name\":\"三菱\",\"pic_url\":\"https://chenbuwan.com/KF/info/三菱.png\",\"url\":\"290\"},{\"name\":\"日立\",\"pic_url\":\"https://chenbuwan.com/KF/info/日立.png\",\"url\":\"300\"},{\"name\":\"开利\",\"pic_url\":\"https://chenbuwan.com/KF/info/开利.png\",\"url\":\"310\"},{\"name\":\"东芝\",\"pic_url\":\"https://chenbuwan.com/KF/info/东芝.png\",\"url\":\"320\"},{\"name\":\"松下\",\"pic_url\":\"https://chenbuwan.com/KF/info/松下.png\",\"url\":\"330\"},{\"name\":\"麦克维尔\",\"pic_url\":\"https://chenbuwan.com/KF/info/麦克维尔.png\",\"url\":\"340\"},{\"name\":\"特灵\",\"pic_url\":\"https://chenbuwan.com/KF/info/特灵.png\",\"url\":\"350\"},{\"name\":\"更多\",\"pic_url\":\"https://chenbuwan.com/KF/info/更多.png\",\"url\":\"360\"},{\"name\":\"螺杆机\",\"pic_url\":\"https://chenbuwan.com/KF/info/螺杆机.png\",\"url\":\"370\"},{\"name\":\"模块机\",\"pic_url\":\"https://chenbuwan.com/KF/info/模块机.png\",\"url\":\"380\"},{\"name\":\"冷库机\",\"pic_url\":\"https://chenbuwan.com/KF/info/冷库.png\",\"url\":\"390\"},{\"name\":\"国产机\",\"pic_url\":\"https://chenbuwan.com/KF/info/国产机.png\",\"url\":\"400\"}]";

        Pattern pattern = Pattern.compile("(?<=src=\")[^\"]+");
        String tmp = "C:\\Users\\Administrator\\Desktop\\test";

        Map<String, List<String>> nameImageUrl = new HashMap<>();

        List<String> codeList = JSONUtil.parseArray(codeJson).stream().map(item -> ((JSONObject) item).getStr("url")).collect(Collectors.toList());
        for (String code : codeList) {
            for (int page = 1; true; page++) {
                System.out.println(String.format("code:{%s},page:{%d}", code, page));
                String body = HttpRequest.get(String.format("https://chenbuwan.com/mini/index/grid?bid=%s&keyword=&page=%d&limit=24", code, page))
                        .addHeaders(map).execute().body();
                JSONObject obj = JSONUtil.parseObj(body);
                if (obj.getInt("code") == 1) {
                    JSONArray array = obj.getJSONObject("data").getJSONArray("list");
                    if (array.isEmpty()) {
                        break;
                    }
                    for (int j = 0; j < array.size(); j++) {
                        JSONObject item = array.getJSONObject(j);

                        String name = item.getStr("name");
                        List<String> list = ReUtil.findAllGroup0(pattern, item.getStr("alias"));
                        list.addAll(ReUtil.findAllGroup0(pattern, item.getStr("link")));
                        nameImageUrl.put(name, list);
                    }
                } else {
                    System.out.println(String.format("{%s}请求失败，响应：%s", code, body));

                }
            }
        }
        File file = new File(tmp, "tmp.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        byte[] bytes = JSONUtil.toJsonStr(nameImageUrl).getBytes(StandardCharsets.UTF_8);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }


}
