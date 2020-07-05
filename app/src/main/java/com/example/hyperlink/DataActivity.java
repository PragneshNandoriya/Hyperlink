package com.example.hyperlink;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ProgressBar;

import com.example.hyperlink.Model.AllImages;
import com.example.hyperlink.Model.ImageData;
import com.example.hyperlink.R;
import com.example.hyperlink.Util.MyViewmodel;
import com.felipecsl.asymmetricgridview.library.Utils;
import com.felipecsl.asymmetricgridview.library.model.AsymmetricItem;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridView;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class DataActivity extends AppCompatActivity {
    MyViewmodel myViewmodel;
    RecyclerView recyclerView;
    List<ImageData> list = new ArrayList<>();
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressbar);
        recyclerView = findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(DataActivity.this));
        RecycleAdapter recycleAdapter = new RecycleAdapter(list, this);
        recyclerView.setAdapter(recycleAdapter);
        myViewmodel = new ViewModelProvider(this).get(MyViewmodel.class);
        myViewmodel.init();
        myViewmodel.getMutableLiveData().observe(this, new Observer<AllImages>() {

            @Override
            public void onChanged(AllImages allImages) {
                progressBar.setVisibility(View.GONE);
                Log.e("DataActivity", "onchange" + allImages.getImages().toString());
                list.addAll(allImages.getImages());
                recycleAdapter.notifyDataSetChanged();
            }
        });

    }
}