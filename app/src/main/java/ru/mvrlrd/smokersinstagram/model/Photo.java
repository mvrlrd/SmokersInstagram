package ru.mvrlrd.smokersinstagram.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Photo {
    @Expose
    @SerializedName("hits")
    public List<Hit> hits;
}
