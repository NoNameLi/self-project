package cn.peng.studygodpath.frame.rxjava;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class First {

    public static void first() throws InterruptedException {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
            }

            @Override
            public void onNext(@NonNull String s) {
                System.out.println(s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        };
        Observable<String> observable = new Observable<String>() {
            @Override
            protected void subscribeActual(@NonNull Observer<? super String> observer) {
                observer.onNext("first");
            }
        };
        observable.subscribe(observer);

        Disposable subscribe = observable.subscribe(System.out::println);
        Disposable subscribe1 = Observable.fromArray(new String[]{"sss", "aaaa"}).subscribe(System.out::println);
        Disposable subscribe2 = Observable.fromRunnable(() -> {
            try {
                System.out.println(Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException ignored) {
            }
        }).subscribe(System.out::println);

        Disposable subscribe3 = Observable.fromCallable(() -> {
            try {
                System.out.println(Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException ignored) {
            }
            return "callable";
        }).subscribeOn(Schedulers.newThread()).observeOn(Schedulers.newThread())
                .subscribe((data) -> {
                    System.out.println(Thread.currentThread().getName());
                    System.out.println(data);
                });

        TimeUnit.SECONDS.sleep(10);
    }

    public static void second() {
        Disposable hello_world = Flowable.just("Hello world").subscribe(System.out::println);

    }


    public static void main(String[] args) throws InterruptedException {
//        first();
        second();
    }


}
