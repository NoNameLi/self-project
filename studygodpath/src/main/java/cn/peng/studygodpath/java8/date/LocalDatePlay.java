package cn.peng.studygodpath.java8.date;

import cn.hutool.core.collection.CollectionUtil;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.TimerTask;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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
        LocalDateTime afterDateTime = LocalDateTime.parse("2022-09-30 22:41:18", dateTimeFormatter);
        Period period = Period.between(now.toLocalDate(), afterDateTime.toLocalDate());
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
        System.out.println(period.getChronology());
        System.out.println(period.getUnits());
        System.out.println(afterDateTime.toLocalDate().toEpochDay() - now.toLocalDate().toEpochDay());
        System.out.print(ChronoUnit.DAYS.between(now, afterDateTime));
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

    @Test
    public void testOf() {
        LocalDate.of(2021, 11, 12);
        LocalDate.parse("2021-02-12");
        LocalDateTime of = LocalDateTime.of(2021, 11, 19, 16, 47, 22);
        LocalDateTime.parse("2007-12-03T10:15:30");
        LocalTime.of(15, 53, 12);
        LocalTime.parse("16:54:12");

//        LocalDateTime time = LocalDateTime.of(LocalDate.of(2022, 9, 31), LocalTime.MAX);
//        System.out.print(time.format(dateTimeFormatter));
        LocalDateTime now = LocalDateTime.now();
        System.out.print(now.isAfter(now));
    }

    @Test
    public void testMethod() {
//        System.out.println(this.calNextDay(LocalDateTime.now().plusMonths(1), 31, LocalTime.of(16, 40, 0)));
        System.out.println(this.calNextWeek(LocalDateTime.now(), "1,2", LocalTime.of(18, 40, 0)));
    }

    private Long calNextWeek(LocalDateTime now, String weekStr, LocalTime execTime) {
        List<Integer> weeks = Arrays.stream(StringUtils.split(weekStr, ",")).map(Integer::valueOf).sorted()
                .filter(i -> i < 7).collect(Collectors.toList());
        if (CollectionUtil.isEmpty(weeks) || Objects.isNull(execTime)) {
            return -1L;
        }
        LocalDateTime executionTime = LocalDateTime.of(now.toLocalDate(), execTime);
        // 根据当前年、当前月 判断最大天 >= 天 时间 构造下次执行时间，执行时间在当前时间之前，继续后延
        while (true) {
            // 2 4 6
            int weekIndex = executionTime.getDayOfWeek().ordinal();
            Optional<Integer> nextWeekIndex = weeks.stream().filter(i -> i >= weekIndex).findFirst();
            if (nextWeekIndex.isPresent()) {
                int execDayDiff = nextWeekIndex.get() - weekIndex;
                executionTime = executionTime.plusDays(execDayDiff);
                if (now.isBefore(executionTime)) {
                    break;
                }
                executionTime = executionTime.plusDays(1);
            } else {
                executionTime = executionTime.plusWeeks(1).with(DayOfWeek.MONDAY);
            }
        }
        System.out.println(executionTime.format(dateTimeFormatter));
        return ChronoUnit.SECONDS.between(now, executionTime);
    }

    private Long calNextDay(LocalDateTime now, Integer execDay, LocalTime execTime) {
        LocalDateTime executionTime = now;
        // 根据当前年、当前月 判断最大天 >= 天 时间 构造下次执行时间，执行时间在当前时间之前，继续后延
        while (true) {
            LocalDate month = executionTime.toLocalDate();
            while (month.lengthOfMonth() < execDay) {
                month = month.plusMonths(1);
            }
            // 执行时间
            executionTime = LocalDateTime.of(LocalDate.of(month.getYear(), month.getMonth(), execDay), execTime);
            if (now.isBefore(executionTime)) {
                break;
            }
            executionTime = executionTime.plusMonths(1);
        }
        System.out.println(executionTime.format(dateTimeFormatter));
        return ChronoUnit.SECONDS.between(now, executionTime);
    }

    class TestTask implements TimerTask {
        private final HashedWheelTimer wheel;

        public TestTask(HashedWheelTimer wheel) {
            this.wheel = wheel;
        }

        @Override
        public void run(Timeout timeout) throws Exception {
            LocalDateTime now = LocalDateTime.now();
            Long delay = LocalDatePlay.this.calNextDay(now, 11, LocalTime.of(now.getHour(), now.getMinute(), now.getSecond()));
            System.out.println("执行，下次延时时间：" + delay);
            wheel.newTimeout(new TestTask(wheel), delay, TimeUnit.SECONDS);
        }
    }


    @Test
    public void testTimeW() throws InterruptedException {
//        HashedWheelTimer wheelTimer = new HashedWheelTimer();
//        wheelTimer.newTimeout(new TestTask(wheelTimer), 5, TimeUnit.SECONDS);
//        TimeUnit.SECONDS.sleep(1500);
        for (int i = 0; i < 10; i++) {
            LocalDateTime now = LocalDateTime.now();
            Long delay = calNextDay(now, 11, LocalTime.of(now.getHour(), now.getMinute(), now.getSecond()));
            System.out.println(delay);
        }
    }
}
