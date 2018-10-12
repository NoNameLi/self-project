package cn.peng.studygodpath.frame.jodaTime;


import java.util.Map;

import org.joda.time.DateTime;
import org.testng.annotations.Test;

import com.google.common.collect.Maps;

/**
 * Created by remote on 2017/12/25.
 */
public class First {
    public final static String format_full = "yyyy-MM-DD HH:mm:ss";

    public static void main(String[] args){
        Map<String,String> map = Maps.newHashMap();
        map.put("1","1");
        System.out.println(""+map);
    }

    @Test
    public void testWith(){

                System.out.println(
                        DateTime.now().toString("yyyy-MM-dd HH:mm:ss:sss")
                );
//        System.out.println(DateTime.now().plusDays(1).withHourOfDay(7).toString(format_full));
//        System.out.println(
//                DateTime.now().withMonthOfYear(1).withDayOfMonth(1).toString(format_full)
//        );



//        System.out.println(DateTime.now().withHourOfDay(8).toString(format_full));
//        System.out.println(DateTime.now().plusDays(1).withHourOfDay(8).toString(format_full));
    }



}
