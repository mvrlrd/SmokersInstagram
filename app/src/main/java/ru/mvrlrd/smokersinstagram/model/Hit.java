package ru.mvrlrd.smokersinstagram.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_hits")
public class Hit  {
    @PrimaryKey
    @Expose
    public long id;

    @Expose
    @SerializedName("webformatURL")
    public String webformatURL;



//    private int countOfClicks;
//
//    public int getCountOfClicks() {
//        return countOfClicks;
//    }
//
//    public void incrementCountOfClicks() {
//        this.countOfClicks++;
//    }

    public void setWebformatURL(String webformatURL) {
        this.webformatURL = webformatURL;
    }

    @NonNull
    @Override
    public String toString() {
        return id+" "+webformatURL;
    }

}
