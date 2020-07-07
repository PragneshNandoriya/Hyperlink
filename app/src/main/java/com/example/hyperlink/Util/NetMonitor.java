package com.example.hyperlink.Util;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.hyperlink.DataActivity;
import com.google.gson.Gson;


public class NetMonitor extends Worker {


    public NetMonitor(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Log.e("Uploadwork","Dowork()");
        return Result.success();
    }

}
