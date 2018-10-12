package cn.peng.studygodpath.java8.lambda.streamplay;


import com.google.common.collect.Sets;
import org.joda.time.DateTime;
import org.testng.annotations.Test;

import java.util.*;

/**
 * Created by remote on 2018/3/16.
 */
public class MapStringListStream  implements listStreamInterface{

    private List<Map<String, String>> list;

    // String数据生产器
    public class StringProducer implements MapDataProducerInterface<String>{

        @Override
        public String production(int t) {
            return String.valueOf(t);
        }
    }
    // Date数据生产器
    public class DateProducer implements MapDataProducerInterface<Date>{

        @Override
        public Date production(int t) {
            try {Thread.sleep(1000);} catch (InterruptedException e) {}
            return DateTime.now().toDate();
        }
    }

    public void testProducer(MapDataProducerInterface instance){
        List<Map<String, Object>> map = getInit(instance);
        System.out.println(map);
    }

    @Test
    public void testStringProducer(){
       //testProducer(new StringProducer());
        System.out.println(new StringProducer().getTClass());

    }

    @Test
    public void testDateProducer(){
        //testProducer(new DateProducer());
        System.out.println(new DateProducer().getTClass());
    }

    @Test
    public void playStreamFromListMapGetNewestDate(){
        DateProducer dateProducer = new DateProducer();
        List<Map<String, Object>> list = getInit(dateProducer);
        // 必须强转 才能调用commpareTO方法 或者 Comparable接口
        Class t = dateProducer.getTClass();
        list.sort((m1, m2) -> ((Date)m2.get(getProdutObjKey())).compareTo((Date) m1.get(getProdutObjKey())));
        System.out.println(Arrays.toString(list.toArray()));
    }



    @Test
    public void play() {
        List<Map<String, Object>> map = getInit(new StringProducer());


        Set<Map<String,Object>> set = Sets.newHashSet();
        set.addAll(map);
        System.out.println(getFisrstMathByTradition(map));
        System.out.println(getFitstMatchByStream(map));
    }

    public Map<String, Object> getFitstMatchByStream(List<Map<String, Object>> list) {
        long start = System.currentTimeMillis();
        Map<String, Object> map1 = list.stream().filter(map -> map.containsValue("99999")).findFirst().get();
        System.out.println("stream:" + (System.currentTimeMillis() - start));
        return map1;
    }

    public Map<String, Object> getFisrstMathByTradition(List<Map<String, Object>> list) {
        long start = System.currentTimeMillis();
        Map<String, Object> map = null;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).containsValue("99999")) {
                map = list.get(i);
                break;
            }
        }
        System.out.println(System.currentTimeMillis() - start);
        return map;
    }


}
