package cn.peng.studygodpath.java8.date;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

/**
 * @Author: Administrator
 * @CreateTime:2021-11-19 16:42
 * QDescription:
 */
public class LocalDatePlay {

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    @Test
    public void tesTimeZone() {
        LocalDateTime now = LocalDateTime.now();
        ZoneId shanghai = ZoneId.of(ZoneId.SHORT_IDS.get("CTT"));
        ZoneId dongjing = ZoneId.of(ZoneId.SHORT_IDS.get("JST"));
        System.out.println("零时区：" + LocalDateTime.now(Clock.systemUTC()).format(dateTimeFormatter));
        System.out.println("默认时区：" + LocalDateTime.now().format(dateTimeFormatter));
        System.out.println("东八区：" + LocalDateTime.now(Clock.systemUTC()).atZone(shanghai).withZoneSameInstant(shanghai).format(dateTimeFormatter));
        System.out.println("东九区：" + now.atZone(shanghai).withZoneSameInstant(dongjing).format(dateTimeFormatter));

        System.out.println(ZonedDateTime.now().withZoneSameInstant(dongjing).format(dateTimeFormatter));
        System.out.println(ZonedDateTime.now().withZoneSameLocal(dongjing).format(dateTimeFormatter));
    }

    @Test
    public void testCal() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime afterDateTime = LocalDateTime.parse("2022-05-30 22:41:18", dateTimeFormatter);
        Period period = Period.between(afterDateTime.toLocalDate(), now.toLocalDate());
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
        System.out.println(period.getChronology());
        System.out.println(period.getUnits());
        System.out.println(afterDateTime.toLocalDate().toEpochDay() - now.toLocalDate().toEpochDay());
        System.out.print(ChronoUnit.MONTHS.between(now, afterDateTime));
    }

    @Test
    public void testChange() {
        LocalDateTime now = LocalDateTime.now();
        LocalDate date = LocalDate.now();
        System.out.println(date.plusDays(2).format(dateFormatter));
        System.out.println(now.plusYears(1).format(dateTimeFormatter));
        System.out.println(now.plus(2, ChronoUnit.WEEKS).format(dateTimeFormatter));
        System.out.println(now.withHour(0).format(dateTimeFormatter));
        System.out.println(now.minusDays(2).format(dateTimeFormatter));
        System.out.println(now.with(TemporalAdjusters.firstDayOfMonth()).format(dateTimeFormatter));
        System.out.println(now.with(TemporalAdjusters.next(DayOfWeek.THURSDAY)).format(dateTimeFormatter));
    }


    @Test
    public void testNow() {
        LocalDateTime localDateTime = LocalDateTime.now();// 日期+时间
        LocalDate localDate = LocalDate.now();// 日期
        LocalTime localTime = LocalTime.now();//时间
        System.out.println(localDateTime.toString());
        System.out.println(localDate.toString());
        System.out.println(localTime.toString());
    }

    public void testOf() {
        LocalDate.of(2021, 11, 12);
        LocalDate.parse("2021-02-12");
        LocalDateTime of = LocalDateTime.of(2021, 11, 19, 16, 47, 22);
        LocalDateTime.parse("2007-12-03T10:15:30");
        LocalTime.of(15, 53, 12);
        LocalTime.parse("16:54:12");
    }
}
