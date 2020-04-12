package ru.mvrlrd.smokersinstagram.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import ru.mvrlrd.smokersinstagram.PicassoLoader;
import ru.mvrlrd.smokersinstagram.R;
import ru.mvrlrd.smokersinstagram.presenter.I2RecyclerMain;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
    private static final String TAG = "MainAdapter";
    private OnClickInterface onClickInterface;
    private I2RecyclerMain i2RecyclerMain;

    public MainAdapter(I2RecyclerMain i2RecyclerMain, OnClickInterface onClickInterface) {
        this.i2RecyclerMain = i2RecyclerMain;
        this.onClickInterface = onClickInterface;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder mainViewHolder, int position) {
//  https://medium.com/@noureldeen.abouelkassem/difference-between-position-getadapterposition-and-getlayoutposition-in-recyclerview-80279a2711d1
        //https://www.tutorialspoint.com/get-clicked-item-and-its-position-in-recyclerview
        //https://stackoverflow.com/questions/26682277/how-do-i-get-the-position-selected-in-a-recyclerview
        mainViewHolder.position = mainViewHolder.getAdapterPosition();
        mainViewHolder.imageView.setOnClickListener(view ->
                onClickInterface.setClick(position,
                        i2RecyclerMain.getPhoto().getHits().get(mainViewHolder.getLayoutPosition()).webformatURL));
        Log.e(TAG, "adapterPosition: " + mainViewHolder.getAdapterPosition() + " layoutPosition: " + mainViewHolder.getLayoutPosition());
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

        MainViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setImage(String url) {
            PicassoLoader.loadImage(url, imageView);
        }

        @Override
        public int getPos() {
            return position;
        }
    }
}
