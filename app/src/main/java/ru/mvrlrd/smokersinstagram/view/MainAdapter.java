package ru.mvrlrd.smokersinstagram.view;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import ru.mvrlrd.smokersinstagram.PicassoLoader;
import ru.mvrlrd.smokersinstagram.R;

import ru.mvrlrd.smokersinstagram.presenter.recycleview.I2RecyclerMain;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder>{
    private static final String TAG = "MainAdapter";

    private I2RecyclerMain i2RecyclerMain;
    private PicassoLoader picassoLoader;

    public MainAdapter( I2RecyclerMain i2RecyclerMain) {
        this.i2RecyclerMain = i2RecyclerMain;
        picassoLoader = new PicassoLoader();
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder mainViewHolder, int position) {
        mainViewHolder.position = position;
        i2RecyclerMain.bindView(mainViewHolder);
    }

    @Override
    public int getItemCount() {
        return i2RecyclerMain.getItemCount();
    }

    class MainViewHolder extends RecyclerView.ViewHolder implements IViewHolder {

        private int position = 0;

        @BindView(R.id.image_view_item_main)
        ImageView imageView;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setImage(String url) {
            picassoLoader.loadImage(url, imageView);
        }

        @Override
        public int getPos() {
            return position;
        }
    }
}
