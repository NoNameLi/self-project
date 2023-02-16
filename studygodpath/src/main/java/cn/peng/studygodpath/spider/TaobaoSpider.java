package cn.peng.studygodpath.spider;

import cn.hutool.core.map.MapBuilder;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import us.codecraft.webmagic.*;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.utils.UrlUtils;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: Administrator
 * @CreateTime:2023-02-16 09:46
 * QDescription:淘宝官博文章抓取 https://developer.aliyun.com/group/taobaotech/article/
 */
public class TaobaoSpider {

    public static void main(String[] args) {
        Spider.create(new TaobaoBlogPageProcessor())
                //从https://github.com/code4craft开始抓
                .addUrl("https://developer.aliyun.com/group/taobaotech/article/")
                //设置Scheduler，使用Redis来管理URL队列
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(10000000)))
                //设置Pipeline，将结果以json方式保存到文件
                .addPipeline(new ExcelPipeline("C:\\Users\\Administrator\\Desktop\\taobao.xlsx"))
                //开启5个线程同时执行
                .thread(5)
                //启动爬虫
                .run();
    }

    static class ExcelPipeline implements Pipeline {
        ExcelWriter writer = null;

        public ExcelPipeline(String filePath) {
            writer = ExcelUtil.getWriter(new File(filePath));
        }

        @Override
        public void process(ResultItems resultItems, Task task) {
            writer.addHeaderAlias("name", "标题").addHeaderAlias("time", "时间")
                    .addHeaderAlias("author", "作者").addHeaderAlias("url", "链接");
            List<Map<Object, Object>> list = resultItems.get("list");
            writer.write(list);
            writer.flush();
        }
    }


    static class TaobaoBlogPageProcessor implements PageProcessor {
        private final Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000)
                .addHeader("cookie", "cps=zwYSodrSlqxJewS%2FOzADFPKeu5slFNbfMkoj336m95cee3QRoRzPIreiDskCxDq7; cna=IyBcGeNIzWcCAXd7R5b2KR97; t=533eff581b78eae33d2791c412e3a702; c_csrf=c2c41e5e-50de-47fa-b53f-77d6699b344f; XSRF-TOKEN=43cf3143-cc5c-4cb1-b48b-2e74e7460f13; aliyun_choice=CN; firstVisit=1; _samesite_flag_=true; cookie2=14fdfc31ad8b0d14c7d4851efae42ed6; _tb_token_=e3e5db339efb1; _hvn_login=6; csg=e4e5e022; login_aliyunid_ticket=YnbN5hossqIZCr6t7SGxRigm2Cb4fGaCdBZWIzmgdHq6sXXZQg4KFWufyvpeV*0*Cm58slMT1tJw3_j$$NFf1Bojh83kDIRBU9QCT6kk8f1zmZsh1hARnNPnwCXof_BNpwU_TOTNChZBoeM1KJexdfz90; login_aliyunid_csrf=_csrf_tk_1887976517817505; login_aliyunid_pk=1802815026089029; hssid=1X8yNWbi8Qwt39CQ-1gLUyw1; hsite=6; aliyun_country=CN; aliyun_site=CN; aliyun_lang=zh; login_aliyunid=wa****@weixiuhui.cn; x5sec=7b22746f6e6779696a696572752d616c6979756e2d636f6d2d74616e6e65693b32223a226663363436346262336563626166393537326639386331333366323133663864434d696874353847454c7939395a6565714e435a73414577354c5769342f7a2f2f2f2f2f41554144227d; tfstk=cfMABtiVbUYDRKTgU-dl_gjqITfAZPTTPiaGBvPdBj5FZ8XOi9MnpeODGl_Ye3C..; l=fB_CrZGrg31oqA-EBOfZFurza779IIRAguPzaNbMi9fP9DW95zy1W6-IRz-pCnGVFs9kR3PZC7jvBeYBqBOInxvTyJYcO6MmnmOk-Wf..; isg=BEVFpVaLiiljnayh-MGI4qx7VIF_AvmUdOXpZ0eqA3yL3mVQD1LdZZ-27AIonhFM");

        @Override
        public void process(Page page) {
            Html html = page.getHtml();
            String urlPrefix = page.getRequest().getUrl();
            List<Map<Object, Object>> collect = html.xpath("div[@class='news-message']").nodes().stream().map(node -> {
                String url = node.xpath("a[@title]/@href").get();
                String name = node.xpath("p/text()").get();
                String time = node.xpath("span[@class='time']/text()").get();
                String author = node.xpath("a[@class='user']/text()").get();
                return MapBuilder.create().put("name", name).put("time", time).put("author", author)
                        .put("url", UrlUtils.canonicalizeUrl(url, urlPrefix)).build();
            }).collect(Collectors.toList());

            page.putField("list", collect);
            page.addTargetRequests(html.xpath("a[@class='pagination-num']/@href").all());
        }

        @Override
        public Site getSite() {
            return this.site;
        }
    }


}
