package ru.mvrlrd.smokersinstagram.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;
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

    public void setWebformatURL(String webformatURL) {
        this.webformatURL = webformatURL;
    }

    @NonNull
    @Override
    public String toString() {
        return id+" "+webformatURL;
    }

}
