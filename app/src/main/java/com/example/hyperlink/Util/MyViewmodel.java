package com.example.hyperlink.Util;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hyperlink.Model.AllImages;

public class MyViewmodel extends ViewModel {
   private Repository repository;
    private MutableLiveData<AllImages> mutableLiveData;
    public MyViewmodel(){

    }

    public void init(){
        if(repository == null){
            repository = new Repository();
            repository.call_request();
            mutableLiveData = repository.getList();
        }
    }

    public MutableLiveData<AllImages> getMutableLiveData(){
        return mutableLiveData;
    }

}
