package com.syd.study.rx;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.syd.study.R;
import com.syd.study.base.BaseActivity;

import androidx.annotation.Nullable;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
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
//    private static final String TAG = "RxJavaTest";
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_rxjava);
//        Button btPrint = findViewById(R.id.bt_print);
//        btPrint.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                printString();
//            }
//        });
//        Button btShowImg = findViewById(R.id.bt_img);
//        btShowImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showImg();
//            }
//        });
//        Button btScheduler = findViewById(R.id.bt_scheduler);
//        btScheduler.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                schedulerTest();
//            }
//        });
//    }
//
//    public void init() {
//
////        new Thread() {
////            @Override
////            public void run() {
////                super.run();
////                for (File folder : folders) {
////                    File[] files = folder.listFiles();
////                    for (File file : files) {
////                        if (file.getName().endsWith(".png")) {
////                            final Bitmap bitmap = getBitmapFromFile(file);
////                            getActivity().runOnUiThread(new Runnable() {
////                                @Override
////                                public void run() {
////                                    imageCollectorView.addImage(bitmap);
////                                }
////                            });
////                        }
////                    }
////                }
////            }
////        }.start();
////
////
////        Observable.from(folders).flatMap(new Func1<File, Observable<File>>() {
////            @Override
////            public Observable<File> call(File file) {
////                return Observable.from(file.listFiles());
////            }
////        }).filter(new Func1<File, Boolean>() {
////            @Override
////            public Boolean call(File file) {
////                return file.getName().endsWith(".png");
////            }
////        }).map(new Func1<File, Bitmap>() {
////            @Override
////            public Bitmap call(File file) {
////                return getBitmapFromFile(file);
////            }
////        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new
////        Action1<Bitmap>() {
////            @Override
////            public void call(Bitmap bitmap) {
////                imageCollectorView.addImage(bitmap);
////            }
////        });
////
////        Observable.from(folders).flatMap((Func1) (folder) -> {
////            Observable.from(file.listFiles())
////        }).filter((Func1) (file) -> {
////            file.getName().endsWith(".png")
////        }).map((Func1) (file) -> {
////            getBitmapFromFile(file)
////        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
////        (Action1) (bitmap) -> {
////            imageCollectorView.addImage(bitmap)
////        });
//
//
//        Observer<String> observer = new Observer<String>() {
//            @Override
//            public void onNext(String s) {
//                Log.e(TAG, "Item: " + s);
//            }
//
//            @Override
//            public void onCompleted() {
//                Log.e(TAG, "Completed!");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.e(TAG, "Error!");
//            }
//        };
//
//
//        Subscriber<String> subscriber = new Subscriber<String>() {
//            @Override
//            public void onNext(String s) {
//                Log.e(TAG, "Item: " + s);
//            }
//
//            @Override
//            public void onCompleted() {
//                Log.e(TAG, "Completed!");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.e(TAG, "Error!");
//            }
//        };
//
//
//        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onNext("Hello");
//                subscriber.onNext("Hi");
//                subscriber.onNext("Aloha");
//                subscriber.onCompleted();
//            }
//        });
//
//        Observable observabl1e = Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onNext("Hello");
//                subscriber.onNext("Hi");
//                subscriber.onNext("Aloha");
//                subscriber.onCompleted();
//            }
//        });
//
//        // just(T...): 将传入的参数依次发送出来。
//        Observable observable2 = Observable.just("Hello", "Hi", "Aloha");// 将会依次调用：// onNext
//        // ("Hello");// onNext("Hi");// onNext("Aloha");// onCompleted();
//
//        // 将传入的数组或 Iterable 拆分成具体对象后，依次发送出来
//        String[] words = {"Hello", "Hi", "Aloha"};
//        Observable observable3 = Observable.from(words);// 将会依次调用：// onNext("Hello");// onNext
//        // ("Hi");// onNext("Aloha");// onCompleted();
//
//        // 上面 just(T...) 的例子和 from(T[]) 的例子，都和之前的 create(OnSubscribe) 的例子是等价的。
//
//
//        // 创建了 Observable 和 Observer 之后，再用 subscribe() 方法将它们联结起来，整条链子就可以工作了。代码形式很简单：
//
//        observable.subscribe(observer);// 或者：observable.subscribe(subscriber);
//
////        Action1<String> onNextAction = new Action1<String>() {
////             onNext()    @Override
////             public
////             void call(String s) {        Log.d(tag, s);    }};Action1<Throwable> onErrorAction =
////             new Action1<Throwable>() {    // onError()    @Override    public void call
////             (Throwable
////             throwable) {        // Error handling    }};Action0 onCompletedAction = new
////             Action0()
////             {    // onCompleted()    @Override    public void call() {        Log.d(tag,
////             "completed");    }};// 自动创建 Subscriber ，并使用 onNextAction 来定义 onNext()observable
////             .subscribe(onNextAction);// 自动创建 Subscriber ，并使用 onNextAction 和 onErrorAction 来定义
////             onNext() 和 onError()observable.subscribe(onNextAction, onErrorAction);// 自动创建
////             Subscriber ，并使用 onNextAction、 onErrorAction 和 onCompletedAction 来定义 onNext()、 onError
////             () 和 onCompleted()observable.subscribe(onNextAction, onErrorAction,
////             onCompletedAction);
////        };
//
//        Action1<String> onNextAction1 = new Action1<String>() {
//
//            @Override
//            public void call(String s) {
//                Log.e(TAG, s);
//            }
//        };
//        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
//            // onError
//            @Override
//            public void call(Throwable throwable) {
//                // Error handling
//            }
//        };
//        Action0 onCompletedAction = new Action0() {
//            // onCompleted()
//            @Override
//            public void call() {
//                Log.e(TAG, "completed");
//            }
//        };
//        // 自动创建 Subscriber 并使用 OnNextAction 和OnErrorAction 来定义 onNext() 和 onError()
//        observable.subscribe(onNextAction1, onErrorAction);
//        // 自动创建 Subscriber 并使用 onNextAction 和 onErrorAction 和 onCompletedAction 来定义 onNext()
//        // 、onError()
//        // 和 onCompleted()
//        observable.subscribe(onNextAction1, onErrorAction, onCompletedAction);
//
//
//    }
//
//    // 注意：这不是 subscribe() 的源码，而是将源码中与性能、兼容性、扩展性有关的代码剔除后的核心代码。
//    // 如果需要看源码，可以去 RxJava 的 GitHub 仓库下载。
////    public Subscription subscribe(Subscriber subscriber) {
////        subscriber.onStart();
////        onSubscribe.call(subscriber);
////        return subscriber;
////    }
//
//    public void printString() {
//        String[] names = {"hello", "RxJava", "Android", "world"};
//        Observable.from(names).subscribe(new Action1<String>() {
//            @Override
//            public void call(String name) {
//                Log.d(TAG, name);
//            }
//        });
//    }
//
//    public void showImg() {
//        int drawableRes = R.drawable.arrow;
//        ImageView imageView = findViewById(R.id.iv);
//        Observable.create(new Observable.OnSubscribe<Drawable>() {
//            @Override
//            public void call(Subscriber<? super Drawable> subscriber) {
//                Drawable drawable = getTheme().getDrawable(drawableRes);
//                subscriber.onNext(drawable);
//                subscriber.onCompleted();
//            }
//        }).subscribe(new Observer<Drawable>() {
//            @Override
//            public void onNext(Drawable drawable) {
//                imageView.setImageDrawable(drawable);
//            }
//
//            @Override
//            public void onCompleted() {
//                Log.e(TAG, "completed()");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Toast.makeText(activity, "Error!", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    public void schedulerTest() {
//        Observable.just(1, 2, 3, 4)
//                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<Integer>() {
//                    @Override
//                    public void call(Integer number) {
//                        Log.d(TAG, "number:" + number);
//                    }
//                }); // 指定 Subscriber 的回调发生在主线程    .subscribe
//
//
//        // 指定 subscribe() 发生在 IO 线程.observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber
//        // 的回调发生在主线程
////        int drawableRes = 1;
////        ImageView imageView;
////        Observable.create(new Observable.OnSubscribe<Drawable>() {
////            @Override
////            public void call(Subscriber<? super Drawable> subscriber) {
////                Drawable drawable = getTheme().getDrawable(drawableRes);
////                subscriber.onNext(drawable);
////                subscriber.onCompleted();
////            }
////        })
////                .subscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(new Observer<Drawable>() {
////                    @Override
////                    public void onNext(Drawable drawable) {
////                        imageView.setImageDrawable(drawable);
////                    }
////
////                    @Override
////                    public void onCompleted() {
////                    }
////
////                    @Override
////                    public void onError(Throwable e) {
////                        Toast.makeText(activity, "Error!", Toast.LENGTH_SHORT).show();
////                    }
////                });
//
//        // throttleFirst()
//
//
//    }
//
//    public void conversion() {
//        // 输入类型 String
//        Observable.just("images/logo.png")
//                .map(new Func1<String, Bitmap>() {
//                    @Override
//                    // 参数类型 String
//                    public Bitmap call(String filePath) {
//                        return getBitmapFromPath(filePath); // 返回类型 Bitmap
//                    }
//                })
//                .subscribe(new Action1<Bitmap>() {
//                    @Override
//                    public void call(Bitmap bitmap) {
//                        // 参数类型 Bitmap
//                        showBitmap(bitmap);
//                    }
//                });
//    }
//
//    public void flatMap() {
//        Student[] students = {};
//        Subscriber<String> subscriber = new Subscriber<String>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(String name) {
//                Log.d(TAG, name);
//            }
//        };
//
//        Observable.from(students).map(new Func1<Student, String>() {
//            @Override
//            public String call(Student student) {
//                return student.getName();
//            }
//        }).subscribe(subscriber);
//
//        Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onNext();
//            }
//        });
//
//
//        Student[] students = {};
//        Subscriber<Student> subscriber = new Subscriber<Student>() {
//            @Override
//            public void onNext(Student student) {
//                List<Course> courses = student.getCourses();
//                for (int i = 0; i < courses.size(); i++) {
//                    Course course = courses.get(i);
//                    Log.d(tag, course.getName());
//                }
//            }
//        };
//        Observable.from(students).subscribe(subscriber);
//
//
//        Student[] students = {};
//        Subscriber<Course> subscriber = new Subscriber<Course>() {
//            @Override
//            public void onNext(Course course) {
//                Log.d(tag, course.getName());
//            }    ...
//        };
//        Observable.from(students).flatMap(new Func1<Student, Observable<Course>>() {
//            @Override
//            public Observable<Course> call(Student student) {
//                return Observable.from(student.getCourses());
//            }
//        }).subscribe(subscriber);
//
//
//        networkClient.token() // 返回 Observable<String>，在订阅时请求 token，并在响应后发送 token
//                .flatMap(new Func1<String, Observable<Messages>>() {
//                    @Override
//                    public Observable<Messages> call(String token) {            // 返回 Observable
//                        // <Messages>，在订阅时请求消息列表，并在响应后发送请求到的消息列表
//                        return networkClient.messages();
//                    }
//                })
//                .subscribe(new Action1<Messages>() {
//                    @Override
//                    public void call(Messages messages) {            //
//                        // 处理显示消息列表
//                        showMessages(messages);
//                    }
//                });
//
//        Observable observable;
//        observable.lift(new Observable.Operator<String, Integer>() {
//            @Override
//            public Subscriber<? super Integer> call(final Subscriber<? super String> subscriber) {
//
//                // 将事件序列中的 Integer 对象转换为 String 对象
//                return new Subscriber<Integer>() {
//                    @Override
//                    public void onNext(Integer integer) {
//                        subscriber.onNext("" + integer);
//                    }
//
//                    @Override
//                    public void onCompleted() {
//                        subscriber.onCompleted();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        subscriber.onError(e);
//                    }
//                };
//            }
//        }).subscribe(new Subscriber() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(Object o) {
//
//            }
//        });
//
//    }
//
//    // 注意：这不是 lift() 的源码，而是将源码中与性能、兼容性、扩展性有关的代码剔除后的核心代码。
//    // 如果需要看源码，可以去 RxJava 的 GitHub 仓库下载。
//    public <R> Observable<R> lift(Operator<? extends R, ? super T> operator) {
//        return Observable.create(new Observable.OnSubscribe<com.syd.study.R>() {
//            @Override
//            public void call(Subscriber subscriber) {
//                Subscriber newSubscriber = operator.call(subscriber);
//                newSubscriber.onStart();
//                onSubscribe.call(newSubscriber);
//            }
//        });
//    }
//
//
//    public void compose() {
//        observable1.lift1().lift2().lift3().lift4().subscribe(subscriber1);
//        observable2.lift1().lift2().lift3().lift4().subscribe(subscriber2);
//        observable3.lift1().lift2().lift3().lift4().subscribe(subscriber3);
//        observable4.lift1().lift2().lift3().lift4().subscribe(subscriber1);
//        liftAll(observable1).subscribe(subscriber1);
//        liftAll(observable2).subscribe(subscriber2);
//        liftAll(observable3).subscribe(subscriber3);
//        liftAll(observable4).subscribe(subscriber4);
//    }
//
//    private Observable liftAll(Observable observable) {
//        return observable.lift1().lift2().lift3().lift4();
//    }
//
}
