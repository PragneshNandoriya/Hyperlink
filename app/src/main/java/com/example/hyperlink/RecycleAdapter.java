package com.example.hyperlink;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hyperlink.Model.ImageData;
import com.example.hyperlink.databinding.ListItemBinding;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {
    List<ImageData> list;
    Context context;
    Picasso picasso;
    public RecycleAdapter(List<ImageData> list, Context context) {
        this.list = list;
        this.context = context;
        picasso = Picasso.get();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemBinding listItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item, parent, false);
        return new MyViewHolder(listItemBinding);
    }

        @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ImageData imageData = list.get(position);
        Log.e("Adapter","url:"+imageData.getUrl());

        picasso.load(imageData.getUrl()).placeholder(R.drawable.ic_launcher_background).
                into(holder.binding.imgUrl, new Callback() {
            @Override
            public void onSuccess() {
                /*Log.e("picasso","Success");*/
            }

            @Override
            public void onError(Exception e) {
                Log.e("picasso","Error"+e.getMessage());
            }
        });
        picasso.load(imageData.getLargeUrl()).placeholder(R.drawable.ic_launcher_background).
                into(holder.binding.imgLargeurl, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ListItemBinding binding;

        public MyViewHolder(@NonNull ListItemBinding itemBinding) {
            super(itemBinding.getRoot());
            binding = itemBinding;
        }
    }
}
