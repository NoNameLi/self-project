package cn.peng.studygodpath.frame.jodaTime;

import org.joda.time.DateTime;
import org.testng.annotations.Test;


import java.util.Calendar;

/**
 * Created by remote on 2018/4/17.
 */
public class Week {

    @Test
    public void addWeek(){
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.add(Calendar.DAY_OF_MONTH,6);

        System.out.println(
                new DateTime(calendar.getTime()).toString(First.format_full)
        );
        calendar.add(Calendar.DAY_OF_WEEK_IN_MONTH,1);
        calendar.set(Calendar.DAY_OF_WEEK,4);
        System.out.println(
                new DateTime(calendar.getTime()).toString(First.format_full)
        );
    }

}
