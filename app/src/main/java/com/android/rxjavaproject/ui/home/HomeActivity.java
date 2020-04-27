package com.android.rxjavaproject.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.android.rxjavaproject.R;
import com.android.rxjavaproject.data.DataSource;
import com.android.rxjavaproject.model.Task;
import com.jakewharton.rxbinding3.view.RxView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import kotlin.Unit;


public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivityTAG";

    private TextView text;

    // global disposables object
    CompositeDisposable disposables = new CompositeDisposable();

    Button button;

    // vars
    private long timeSinceLastRequest; // for log printouts only. Not part of logic.



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        timeSinceLastRequest = System.currentTimeMillis();


        button = findViewById(R.id.button);

        timeSinceLastRequest = System.currentTimeMillis();

        // Set a click listener to the button with RxBinding Library
        RxView.clicks(button)
                .throttleFirst(4000, TimeUnit.MILLISECONDS) // Throttle the clicks so 500 ms must pass before registering a new click
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Unit>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposables.add(d);
                    }
                    @Override
                    public void onNext(Unit unit) {
                        Log.d(TAG, "onNext: time since last clicked: " + (System.currentTimeMillis() - timeSinceLastRequest));
                        someMethod(); // Execute some method when a click is registered
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onComplete() {
                    }
                });    }


    private void someMethod(){
        timeSinceLastRequest = System.currentTimeMillis();
        Toast.makeText(this, "You clicked a button", Toast.LENGTH_SHORT).show();
        // do something
    }

    // make sure to clear disposables when the activity is destroyed
    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.clear();
    }
}
