package com.study.study_module.rx;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.study.R;
import com.study.adapter.TestAdapter;
import com.study.base.BaseActivity;
import com.study.config.AppConfig;
import com.study.userful.util.L;
import com.study.utils.volley.HttpRequest;
import com.study.utils.volley.ResponseListener;

import java.io.File;
import java.util.List;

import androidx.annotation.Nullable;
import butterknife.BindView;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Action3;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 说明：$
 * <p>
 * date: 2019/12/16 18:10
 *
 * @author syd
 * @version 1.0
 */
public class RxJavaTest extends BaseActivity {
    @BindView(R.id.gv)
    GridView gv;
    @BindView(R.id.et_params)
    EditText etParams;
    @BindView(R.id.tv_show)
    TextView tvShow;
    String[] command = new String[]{"testThread", "mapTest"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_test_common;
    }

    @Override
    public void initView() {
        super.initView();
        TestAdapter adapter = new TestAdapter(this, command);
        gv.setAdapter(adapter);
        gv.setOnItemClickListener((parent, view, position, id) -> {
            switch (command[position]) {
                case "testThread":
                    testThread();
                    break;
                case "mapTest":
                    mapTest();
                    break;
            }

        });
    }


    public void mapTest() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("test");
            }
        }).map(new Func1<String, String>() {
            @Override
            public String  call(String s) {
                return s + "----";
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                L.e("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                L.e("onError");
            }

            @Override
            public void onNext(String s) {
                L.e(s);
            }
        });
    }

    /**
     * 一般情况和使用 RxJava 情况对比
     */
    public void compare() {
        // 需求：有一个自定义组件 ImageCollectorView，它的作用就是显示多张图片，使用方法 add() 向里面添加显示的图片。
        // 先在需要将目录数组 folders 下的每个文件中的 png 图片都显示在 ImageCollectorView中。读取图片是一个耗时操作
        // 因此需要在子线程中，显示图片要在 UI 中

        // 单纯演示，如果不使用 RxJava 的时候使用如何处理
        List<File> folders = null;
        ImageView imageView = null;
        new Thread() {
            @Override
            public void run() {
                super.run();
                for (File folder : folders) {
                    File[] files = folder.listFiles();
                    for (File file : files) {
                        if (file.getName().endsWith(".png")) {
                            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    imageView.setImageBitmap(bitmap);
                                }
                            });
                        }
                    }

                }
            }
        }.start();

        // 使用 RxJava
        Observable.from(folders)
                .flatMap(new Func1<File, Observable<File>>() {
                    @Override
                    public Observable<File> call(File file) {
                        return Observable.from(file.listFiles());
                    }
                })
                .filter(new Func1<File, Boolean>() {
                    @Override
                    public Boolean call(File file) {
                        return file.getName().endsWith(".png");
                    }
                })
                .map(new Func1<File, Bitmap>() {
                    @Override
                    public Bitmap call(File file) {
                        return BitmapFactory.decodeFile(file.getAbsolutePath());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                    }
                });
        // 可能会说，明明代码变多了，没有简洁啊。这里的简洁不要太固化，简洁指的是逻辑上简洁，没有任何嵌套。使用了一条上到下的链式调用
        // 就算代码量再多，阅读性也好，上面没有使用 Rx 的时候就是用了嵌套，一层一层。试想如何层级更多呢？
        // 如果需求发生变化了，需要只显示前十张图片，那么是不是还得去嵌套里面一层层扒拉一下，再写逻辑。时间久了，肯定要
        // 在读一遍代码逻辑，捋一遍思路。
        // 而这种链式结构，看起来就很清晰了。
        // 如果有一段逻辑非常复杂，包含多次内存操作、本地文件操作和网络操作。对象分分合合等。如果使用常规的方法实现那写起来代码阅读性
        // 肯定不好，各种嵌套等等。如果使用 RxJava 的情况下，仍然是一套链子就完成了，代码很长，但是很清晰。

        // RxJava 好在哪里？简洁！就是能够把什么复杂逻辑都穿成一条线的简洁


    }

    public void shabile() {


        // RxJava 作为一个工具库，使用的就是通用形式的观察者模式


        // RxJava 的观察者模式

        // RxJava 有四个基本概念：Observable(可观察者、即被观察者)、Observer（观察者）、subscribe（订阅）
        // 、事件 Observable 和 Observer 通过 subscribe() 方法实现订阅关系从而 Observable 可以在需要的时候发出事件
        // 来通知 observer。
        // 与传统的观察者模式不同，RxJava 的事件回调方法除了普通事件 onNext() (相当于onClick/onEvent())外还有两个
        // 特殊的事件 onCompleted() 和 onError()
        // RxJava 会把事件看做一个队列，当事件队列完结的时候触发 onCompleted。RxJava 规定当不再有新的 onNext() 发出
        // 的时候，需要触发  onCompleted() 方法作为标志
        // onError() 事件出现异常的时候触发，触发后，队列会自动终止，不允许再有事件发出。
        // 一个正确运行的事件序列中，onCompleted 和 onError 有且只有一个。并且是事件序列中的最后一个。注意
        // onCompleted 和 onError 两者是互斥的，即在队列中调用了其中一个，就不应该再调用另一个。

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.e(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ");
            }

            @Override
            public void onNext(String s) {
                Log.e(TAG, "onNext: " + s);
            }
        };

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext: " + s);
            }
        };
        //

        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                subscriber.onNext("Hi");
                subscriber.onNext("RxJava");
                subscriber.onCompleted();
            }
        });

        Observable observable11 = Observable.just("Hello", "Hi", "Just");

        String[] words = {"Hello", "Hi", "String[]"};
        Observable<String> observable1 = Observable.from(words);

        Action3<String, String, String> action3 = new Action3<String, String, String>() {
            @Override
            public void call(String s, String s2, String s3) {
                Log.d(TAG, "call: " + s);
                Log.d(TAG, "call: " + s2);
                Log.d(TAG, "call: " + s3);
            }
        };


        // 虽然有点不符合常理，但是如果 observer.subscribe 的话对流式 API 的设计有造成影响了，这样明显得不偿失
        // observer 只是用来做最后的处理的，当被观察者完成一系列操作的时候来触发观察者完成最后的动作。
        observable1.subscribe(subscriber);

        // 除了 subscribe(Observer)  和 subscribe(Subscriber) subscribe 还支持不完整的回调，RxJava 会自动根据定义创建
        // Subscriber


        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                // Error hanling
            }
        };

        Action0 onCompleteAction = new Action0() {
            @Override
            public void call() {
                // onCompleted()
                Log.d(TAG, "call: completed");
            }
        };


        // 上面的例子中创建出 Observable 和 Subscribe 再用 subscribe() 将它们串联起来，一次 RxJava 基本完成了，非常简单

        // 但是这仅仅是演示，没有什么真实的意义。在RxJava 中默认情况下，事件的发出和消费都是在同一个线程的。也就是仅仅用
        // 上面的方法实现的仅仅是一个同步的观察者模式。而观察者模式本身的目的是 后台处理，前台回调的异步机制。

        // 要实现异步就需要用到 Scheduler 了

    }

    // 线程控制---Scheduler(一)
    public void scheduler() {
        // 在不指定线程的情况下，RxJava 默认在那个线程执行 subscribe() 就会在哪个线程产生事件；在哪个线程产生事件，就会在
        // 哪个线程消费事件。如果需要切换线程，就需要使用 Scheduler（调度器）

        // Scheduler 的 api（一）

        // Scheduler---调度器。相对于线程控制器，RxJava 中使用它来控制每一段代码应该运行在什么样的线程中。RxJava 中内置了几个
        // Scheduler，可以适合大多数使用场景

        // Scheduler.immediate()

        // Scheduler.newThread()

        // Scheduler.io()  实现是用一个无数量上限的线程池，可以重用空闲的线程。在大多数情况下 io 比 newThread 效率要高，不要把计算放在 io
        // 中，可以避免创建不必要的线程

        // Scheduler.computation() 使用固定的线程池。大小为 CPU 核数。 不要把 io 操作放在 computation 中，否则 I/O
        // 操作的等待时间会浪费 CPU

        // Android 专用的 AndroidSchedulers.mainThread() 在Android主线程运行

        // 有了这几个 scheduler 就可以使用 subscribeOn() 和 observeOn() 来对线程进行控制了

        Observable.just(1, 2, 3, 4)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.d(TAG, "call: " + integer);
                    }
                });


    }


    // 注意：这不是 subscribe() 的源码，而是将源码中与性能、兼容性、扩展性有关的代码剔除后的核心代码。
    // 如果需要看源码，可以去 RxJava 的 GitHub 仓库下载。
//    public Subscription subscribe(Subscriber subscriber) {
//        subscriber.onStart();
//        onSubscribe.call(subscriber);
//        return subscriber;
//    }

    /**
     * 变换
     * <p>
     * 到了最牛B的地方了，要激动了
     * <p>
     * RxJava 提供了对事件序列进行变换的支持，这也是他的核心功能之一。所谓的变换就是，将事件序列中的对象或整个序列进行加工处理，转换成不同的事件或事件序列。
     */
    public void change() {
        // 例子
        Observable.just("images/logo.png") // 输入类型 String
                .map(new Func1<String, Bitmap>() {
                    @Override
                    public Bitmap call(String s) { // 参数类型 String
                        return BitmapFactory.decodeFile(s); // 返回类型 Bitmap
                    }
                }).subscribe(new Action1<Bitmap>() {
            @Override
            public void call(Bitmap bitmap) {
                Log.d(TAG, "call: 显示图片：" + bitmap.toString());
            }
        });


        // 变换原理 lift()

        // 实质上都是对事件序列的处理和再发送  基于同一个基础的变换方法：lift(Operator)
    }

    public void showImg() {
        int drawableRes = R.drawable.arrow;
        ImageView imageView = findViewById(R.id.iv);
        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
                Drawable drawable = getTheme().getDrawable(drawableRes);
                subscriber.onNext(drawable);
                subscriber.onCompleted();
            }
        }).subscribe(new Observer<Drawable>() {
            @Override
            public void onNext(Drawable drawable) {
                imageView.setImageDrawable(drawable);
            }

            @Override
            public void onCompleted() {
                Log.e(TAG, "completed()");
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(RxJavaTest.this, "Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 测试发生的线程
     */
    public void testThread() {


        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {

            }
        }).
                subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object o) {

                    }
                });


        L.e(Thread.currentThread().getName() + "" + Thread.currentThread().getId());


        Observable.create((Observable.OnSubscribe<String>) subscriber -> {
            HttpRequest.getInstance().getRequest(AppConfig.URL.url_get, null, null,
                    new ResponseListener() {
                        @Override
                        protected void onResponse(String main, Exception error) {
                            L.e(main);
                            L.e(Thread.currentThread() + "");
                        }
                    });

            L.e(Thread.currentThread() + "");
            L.e(Thread.currentThread().getName() + "" + Thread.currentThread().getId());
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        L.e("onCompleted" + Thread.currentThread());

                    }

                    @Override
                    public void onError(Throwable e) {
                        L.e("onError");
                    }

                    @Override
                    public void onNext(String s) {
                        L.e("onNext" + s);
                    }
                });
    }


    public void schedulerTest() {
        Observable.just(1, 2, 3, 4)
                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        return null;
                    }
                }); // 指定 Subscriber 的回调发生在主线程    .subscribe

        Course[] courses = {new Course(), new Course()};
        Observable.from(courses)
                .subscribe(new Subscriber<Course>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Course course) {

                    }
                });

        // 指定 subscribe() 发生在 IO 线程.observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber
        // 的回调发生在主线程
//        int drawableRes = 1;
//        ImageView imageView;
//        Observable.create(new Observable.OnSubscribe<Drawable>() {
//            @Override
//            public void call(Subscriber<? super Drawable> subscriber) {
//                Drawable drawable = getTheme().getDrawable(drawableRes);
//                subscriber.onNext(drawable);
//                subscriber.onCompleted();
//            }
//        })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Drawable>() {
//                    @Override
//                    public void onNext(Drawable drawable) {
//                        imageView.setImageDrawable(drawable);
//                    }
//
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Toast.makeText(activity, "Error!", Toast.LENGTH_SHORT).show();
//                    }
//                });

        // throttleFirst()


    }

    public void test() {
        /**
         *  RxJava 一个异步库，基于事件的、可观察序列
         *  四个基本概念
         *  observable
         *  observer
         *  subscribe 订阅
         *  事件
         *
         *  RxJava 的事件回调方法 onNext、onCompleted、onErroror
         *
         *  onCompleted 事件队列完结
         *
         *  onError
         *
         *
         *  observer 是一个接口，与传统的观察者不一样。这里有三个方法
         *  observable
         *  事件
         *  subscribe
         *
         *
         */
        // observable 可观察者
        // observer 观察者


        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                L.e("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                L.e(e.getMessage());
            }

            @Override
            public void onNext(String s) {
                L.e(s);
            }
        };


        // 内置了一个 实现了 Observer 的抽象类 Subscriber
        Subscriber<String> subscriber = new Subscriber<String>() {

            @Override
            public void onStart() {
                // 默认方法体为空
                L.e("onStart");
            }

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        };


        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {

            }
        });

        Observable.create((Observable.OnSubscribe<String>) subscriber1 -> {


        }).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        }).unsubscribe();

    }

    private static class Course {

    }

    class ImageCollectorView {
        void add(Bitmap bitmap) {
            Log.e("显示图片", bitmap.toString());
        }
    }


}
