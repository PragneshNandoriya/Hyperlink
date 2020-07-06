package com.example.hyperlink.Util;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hyperlink.Model.AllImages;

public class MyViewmodel extends ViewModel {
   private Repository repository;
    private MutableLiveData<AllImages> mutableLiveData;
    public MyViewmodel(){

    }

    public void init(Application application){
        if(repository == null){
            repository = new Repository(application);
            repository.call_request();
            mutableLiveData = repository.getList();
        }
    }
    public void requestdata(Application application){
        if(repository != null){
            repository.call_request();
            mutableLiveData = repository.getList();
        }else{
                repository = new Repository(application);
                repository.call_request();
                mutableLiveData = repository.getList();
        }
    }

    public MutableLiveData<AllImages> getMutableLiveData(){
        return mutableLiveData;
    }

}
