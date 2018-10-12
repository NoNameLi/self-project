package cn.peng.studygodpath.testng;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by remote on 2017/10/19.
 */
public class TestCase {

    @Test
    public void test() {
        long count = 1089;
        int num = 100;
        System.out.println(count / num + 1);
    }


    @Test
    public void testMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("xx", "11");
        System.out.println(map.toString());
    }

    @Test
    public void testDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.MILLISECOND, 0);
        String first = format.format(c.getTime());
        System.out.println("===============first:" + first);

        c.set(Calendar.HOUR_OF_DAY, 24);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.MILLISECOND, 0);
        first = format.format(c.getTime());
        System.out.println("===============first:" + first);


        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0,0);
        cal.set(Calendar.DAY_OF_MONTH,cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        first = format.format(cal.getTime());
        System.out.println("===============first:" + first);


        Calendar cal2 = Calendar.getInstance();
        cal2.set(cal2.get(Calendar.YEAR),cal2.get(Calendar.MONDAY), cal2.get(Calendar.DAY_OF_MONTH), 0, 0,0);
        cal2.set(Calendar.DAY_OF_MONTH,cal2.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal2.set(Calendar.HOUR_OF_DAY, 24);

        first = format.format(cal2.getTime());
        System.out.println("===============first:" + first);

    }
    @Test
    public void testDate2() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("===============first:" + format.format(getYesterdayStart()));
        System.out.println("===============first:" + format.format(getYesterdayEnd()));
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

    private Calendar getCalendar(){
        Calendar c = Calendar.getInstance();
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONDAY), c.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        return c;
    }


}