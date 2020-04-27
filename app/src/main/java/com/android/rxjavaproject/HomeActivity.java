package com.android.rxjavaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.rxjavaproject.data.DataSource;
import com.android.rxjavaproject.model.Task;

import java.util.List;

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

        ////***********Single Task Observable (Optional Maximum of 10)
//        final  Task task = new Task("Walk the dog", false, 3);
//
//        Observable<Task> taskObservable = Observable
//                .just(task)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())


        //************ Array list Observable
/*        final List<Task> tasks = DataSource.createTaskList();

        Observable<Task> taskObservable = Observable
                .create(new ObservableOnSubscribe<Task>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<Task> emitter) throws Throwable {

                        for (Task task : tasks) {
                            if (!emitter.isDisposed()) {
                                emitter.onNext(task);
                            }
                        }
                        if(!emitter.isDisposed())
                            emitter.onComplete();


                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        taskObservable.subscribe(new Observer<Task>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Task task) {
                Log.d(TAG, "onNext: " + task.getDescription());
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });*/


    // Range operators
//    Observable<Task> observable = Observable
//            .range(0,9)
//            .subscribeOn(Schedulers.io())
//            .map((Function<Integer, Task>) integer -> {
//                Log.d(TAG, "apply: " + Thread.currentThread().getName());
//                return new Task("Priority Task is "+ String.valueOf(integer), false, integer );
//            })
//            .takeWhile(new Predicate<Task>() {
//                @Override
//                public boolean test(Task task) throws Throwable {
//                    return task.getPriority() < 9;
//                }
//            })
//            .observeOn(AndroidSchedulers.mainThread());
//
//    observable.subscribe(new Observer<Task>() {
//        @Override
//        public void onSubscribe(@NonNull Disposable d) {
//
//        }
//
//        @Override
//        public void onNext(@NonNull Task task) {
//            Log.d(TAG, "onNext: " + task.getPriority());
//        }
//
//        @Override
//        public void onError(@NonNull Throwable e) {
//
//        }
//
//        @Override
//        public void onComplete() {
//
//        }
//    });

        // Repeat operators
        Observable<Integer> observable = Observable
            .range(0,4)
            .subscribeOn(Schedulers.io())
            .repeat(3)
            .observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Integer integer) {
                Log.d(TAG, "onNext: " + integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


    }
}
