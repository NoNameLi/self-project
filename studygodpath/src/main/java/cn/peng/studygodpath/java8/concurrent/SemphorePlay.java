package cn.peng.studygodpath.java8.concurrent;

import org.testng.annotations.Test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Semphore 计数信号量 控制访问共享资源的计数器 ，通常用于限制共享资源的访问线程数目
 */
public class SemphorePlay {
    static class Parking {
        Semaphore semaphore = null;

        public Parking(int size) {
            semaphore = new Semaphore(size);
        }

        public void park() {
            try {
                semaphore.acquire();
                int stopTime = ThreadLocalRandom.current().nextInt(0, 10);
                System.out.println(Thread.currentThread().getName() + "停车" + stopTime);
                TimeUnit.SECONDS.sleep(stopTime);
                System.out.println(Thread.currentThread().getName() + "开出停车场");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }

        static class Car implements Runnable {
            private Parking parking = null;

            public Car(Parking parking) {
                this.parking = parking;
            }

            @Override
            public void run() {
                parking.park();
            }
        }

        public static void main(String[] args) {
            Parking parking = new Parking(3);
            Car car = new Car(parking);

            for (int i = 0; i < 5; i++) {
                new Thread(car).start();
            }
        }

    }


}
