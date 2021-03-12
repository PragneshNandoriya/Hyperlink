package com.example.hyperlink;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ProgressBar;

import com.example.hyperlink.Model.AllImages;
import com.example.hyperlink.Model.ImageData;
import com.example.hyperlink.R;
import com.example.hyperlink.Util.MyViewmodel;
import com.example.hyperlink.Util.NetMonitor;
import com.felipecsl.asymmetricgridview.library.Utils;
import com.felipecsl.asymmetricgridview.library.model.AsymmetricItem;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridView;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridViewAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

//This isfor data Actviity
public class DataActivity extends AppCompatActivity {
    public  MyViewmodel myViewmodel;
    RecyclerView recyclerView;
    List<ImageData> list = new ArrayList<>();
    ProgressBar progressBar;
    public static  Application application;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        application = getApplication();
        Log.e("DataActivity","oncreate");
        progressBar = findViewById(R.id.progressbar);
        recyclerView = findViewById(R.id.recycleview);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
      //  recyclerView.setLayoutManager(new LinearLayoutManager(DataActivity.this));
        RecycleAdapter recycleAdapter = new RecycleAdapter(list, this);
        recyclerView.setAdapter(recycleAdapter);
        myViewmodel = new ViewModelProvider(this).get(MyViewmodel.class);
        myViewmodel.init(getApplication());
        myViewmodel.getMutableLiveData().observe(this, new Observer<AllImages>() {

            @Override
            public void onChanged(AllImages allImages) {
                progressBar.setVisibility(View.GONE);
                Log.e("DataActivity", "onchange" + allImages.getImages().toString());
                list.addAll(allImages.getImages());
                recycleAdapter.notifyDataSetChanged();
            }
        });

        if(!isInternetAvailable()){
            Gson gson = new Gson();
          //  String s =  gson.toJson(myViewmodel);

            Data.Builder builder = new Data.Builder();
            //builder.putString("data", s);
            Data data = builder.build();
            Log.e("dataactivity","No internet");
            Constraints constraints = new Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build();
            OneTimeWorkRequest uploadWorked = new OneTimeWorkRequest.Builder(NetMonitor.class)
                    .setInputData(data)
                    .setConstraints(constraints).build();
            WorkManager.getInstance(this).enqueue(uploadWorked);
            WorkManager.getInstance(this).getWorkInfoByIdLiveData(uploadWorked.getId())
                    .observe(DataActivity.this, new Observer<WorkInfo>() {
                        @Override
                        public void onChanged(@Nullable WorkInfo workInfo) {
                            Log.e("worker","get result");
                            if (workInfo != null && workInfo.getState() == WorkInfo.State.SUCCEEDED) {
                                Log.e("worker","get result success");
                                myViewmodel.requestdata(getApplication());
                                    }
                        }
                    });
        }
    }

    private boolean isInternetAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


     /*   @Override
        protected void onDestroy() {
            super.onDestroy();

        }
    */

}