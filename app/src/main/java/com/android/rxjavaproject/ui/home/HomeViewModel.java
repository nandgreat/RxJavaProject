package com.android.rxjavaproject.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.android.rxjavaproject.data.repository.Repository;

import java.util.concurrent.Future;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class HomeViewModel extends ViewModel {

    private Repository repository;

    public HomeViewModel() {
        repository = Repository.getInstance();
    }

    public Future<Observable<ResponseBody>> makeFutureQuery(){
        return repository.makeFutureQuery();
    }
}
