package com.android.rxjavaproject.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.rxjavaproject.R;
import com.android.rxjavaproject.data.DataSource;
import com.android.rxjavaproject.model.Task;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivityTAG";

    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Observable.fromIterable(DataSource.createTaskList())
                .map(new Function<Task, String>() {
                    @Override
                    public String apply(Task task) throws Exception {

                        return task.getDescription();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String task) {
                        Log.d(TAG, "onNext: " + task);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    Function<Task, String> extractDescriptionFunction = task -> {
        Log.d(TAG, "apply: doing work on thread: " + Thread.currentThread().getName());
        return task.getDescription();
    };
}
