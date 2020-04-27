package com.android.rxjavaproject.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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

    //ui
    private SearchView searchView;

    // vars
    private long timeSinceLastRequest; // for log printouts only. Not part of logic.



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        timeSinceLastRequest = System.currentTimeMillis();

        searchView = findViewById(R.id.search_view);


        // create the Observable
        Observable<String> observableQueryText = Observable
                .create((ObservableOnSubscribe<String>) emitter -> {

                    // Listen for text input into the SearchView
                    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String query) {
                            return false;
                        }

                        @Override
                        public boolean onQueryTextChange(final String newText) {
                            if(!emitter.isDisposed()){
                                emitter.onNext(newText); // Pass the query to the emitter
                            }
                            return false;
                        }
                    });
                })
                .debounce(1000, TimeUnit.MILLISECONDS) // Apply Debounce() operator to limit requests
                .subscribeOn(Schedulers.io());

        // Subscribe an Observer
        observableQueryText.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposables.add(d);
            }
            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext: time  since last request: " + (System.currentTimeMillis() - timeSinceLastRequest));
                Log.d(TAG, "onNext: search query: " + s);
                timeSinceLastRequest = System.currentTimeMillis();

                // method for sending a request to the server
                sendRequestToServer(s);
            }
            @Override
            public void onError(Throwable e) {
            }
            @Override
            public void onComplete() {
            }
        });
    }


    // Fake method for sending a request to the server
    private void sendRequestToServer(String query){
        // do nothing
    }

    // make sure to clear disposables when the activity is destroyed
    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.clear();
    }
}
