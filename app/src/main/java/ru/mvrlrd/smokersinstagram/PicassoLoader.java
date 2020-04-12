package ru.mvrlrd.smokersinstagram;

import android.widget.ImageView;
import com.squareup.picasso.Picasso;


public class PicassoLoader {

    public static void loadImage(String url, ImageView imageView){
        Picasso
                .get()
                .load(url)
                .into(imageView);

    }

}