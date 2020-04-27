package com.android.rxjavaproject.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.android.rxjavaproject.data.repository.Repository;

import okhttp3.ResponseBody;

public class HomeViewModel extends ViewModel {

    private Repository repository;

    public HomeViewModel() {
        repository = Repository.getInstance();
    }

    public LiveData<ResponseBody> makeQuery(){
        return repository.makeReactiveQuery();
    }
}
