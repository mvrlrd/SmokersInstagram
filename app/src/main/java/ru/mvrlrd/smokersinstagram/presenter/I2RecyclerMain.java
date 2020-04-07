package ru.mvrlrd.smokersinstagram.presenter;

import com.squareup.picasso.Picasso;

import ru.mvrlrd.smokersinstagram.model.Photo;
import ru.mvrlrd.smokersinstagram.view.IViewHolder;

public interface I2RecyclerMain {
    void bindView(IViewHolder iViewHolder);
    int getItemCount();
    Photo getPhoto();
}
