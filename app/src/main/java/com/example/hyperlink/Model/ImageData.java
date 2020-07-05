package com.example.hyperlink.Model;

import com.felipecsl.asymmetricgridview.library.model.AsymmetricItem;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridView;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageData  {
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("large_url")
    @Expose
    private String largeUrl;
    @SerializedName("source_id")
    @Expose
    private Object sourceId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLargeUrl() {
        return largeUrl;
    }

    public void setLargeUrl(String largeUrl) {
        this.largeUrl = largeUrl;
    }

    public Object getSourceId() {
        return sourceId;
    }

    public void setSourceId(Object sourceId) {
        this.sourceId = sourceId;
    }

    @Override
    public String toString() {
        return "ImageData{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", largeUrl='" + largeUrl + '\'' +
                ", sourceId=" + sourceId +
                '}';
    }
}
