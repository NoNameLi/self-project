package cn.peng.studygodpath.testng;

import com.beust.jcommander.internal.Maps;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * maven dependency scope test 只能在testz中依赖到jar包
 * Created by remote on 2017/9/28.
 */
public class TestNgTest {

    @Test
    public void one(){
        Assert.assertTrue(false);
        System.out.println("one");
    }

    @Test
    public void testStr(){
        String tar = "xxxx";
        System.out.println(tar.equals(null));
        System.out.println(String.format("params:%s",null));
    }

    @Test
    public void testStrNull(){
        String key = "test";
        Map<String,String> map = Maps.newHashMap();
        if(StringUtils.isNotBlank((CharSequence)map.get(key))){
            System.out.println(map.get(key));
        }else{
            System.out.println("error");
        }
    }

    @Test
    public void testCalendar(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(format.format(getYesterdayStart()));
        System.out.println(format.format(getYesterdayEnd()));

        System.out.println(format.format(getThisMonthStart()));
        System.out.println(format.format(getThisMonthEnd()));

    }



    private Calendar getCalendar() {
        Calendar c = Calendar.getInstance();
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONDAY), c.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        return c;
    }

    private Date getYesterdayStart() {
        Calendar c = getCalendar();
        c.add(Calendar.DAY_OF_MONTH, -1);
        return c.getTime();
    }

    private Date getYesterdayEnd() {
        Calendar c = getCalendar();
        c.add(Calendar.DAY_OF_MONTH, -1);
        c.set(Calendar.HOUR_OF_DAY, 24);
        return c.getTime();
    }

    private Date getThisMonthStart() {
        Calendar c = getCalendar();
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }

    private Date getThisMonthEnd() {
        Calendar c = getCalendar();
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        c.set(Calendar.HOUR_OF_DAY, 24);
        return c.getTime();
    }

}
