package com.example.hyperlink.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllImages {
    @SerializedName("images")
    @Expose
    private List<ImageData> images = null;

    public List<ImageData> getImages() {
        return images;
    }

    public void setImages(List<ImageData> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "AllImages{" +
                "images=" + images +
                '}';
    }
}
