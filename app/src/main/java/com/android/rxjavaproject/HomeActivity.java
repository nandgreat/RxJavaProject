package com.android.rxjavaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.rxjavaproject.data.DataSource;
import com.android.rxjavaproject.model.Task;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivityTAG";

    private TextView text;

    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        // emit an observable every time interval
//        Observable<Long> intervalObservable = Observable
//                .interval(1, TimeUnit.SECONDS)
//                .subscribeOn(Schedulers.io())
//                .takeWhile(new Predicate<Long>() { // stop the process if more than 5 seconds passes
//                    @Override
//                    public boolean test(Long aLong) throws Exception {
//                        Log.d(TAG, "test: " + aLong +", thread: " + Thread.currentThread().getName());
//                        return aLong <= 5;
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread());
//
//        intervalObservable.subscribe(new Observer<Long>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//            }
//
//            @Override
//            public void onNext(@NonNull Long aLong) {
//                Log.d(TAG, "onNext: "+ aLong);
//
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });

        // Timer observable
        // emit single observable after a given delay
        Observable<Long> timeObservable = Observable
                .timer(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        timeObservable.subscribe(new Observer<Long>() {

            long time = 0; // variable for demonstating how much time has passed

            @Override
            public void onSubscribe(Disposable d) {
                time = System.currentTimeMillis() / 1000;
            }
            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "onNext: " + ((System.currentTimeMillis() / 1000) - time) + " seconds have elapsed." );
            }
            @Override
            public void onError(Throwable e) {

            }
            @Override
            public void onComplete() {

            }
        });
    }
}
