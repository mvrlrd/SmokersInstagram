package ru.mvrlrd.smokersinstagram.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Photo {
    @Expose
    @SerializedName("hits")
    private List<Hit> hits;

    public List<Hit> getHits() {
        return hits;
    }

}
