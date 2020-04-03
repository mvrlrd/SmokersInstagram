package ru.mvrlrd.smokersinstagram.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hit {
    @Expose
    @SerializedName("webformatURL")
    public String webformatURL;

    private int countOfClicks=0;

    public int getCountOfClicks() {
        return countOfClicks;
    }

    public void incrementCountOfClicks() {
        this.countOfClicks++;
    }
}
