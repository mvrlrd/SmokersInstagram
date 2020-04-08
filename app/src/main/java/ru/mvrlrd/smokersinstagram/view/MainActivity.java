package ru.mvrlrd.smokersinstagram.view;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import ru.mvrlrd.smokersinstagram.R;
import ru.mvrlrd.smokersinstagram.presenter.MainPresenter;
import ru.mvrlrd.smokersinstagram.view.fullsizeview.FullPictureActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends MvpAppCompatActivity implements MoxyView, View.OnClickListener {
    public static final String keyForIntentExtra = "ru.mvrlrd.smokersinstagram.view.MainActivity.URL";
    private static final String TAG = "MainActivity";
    @BindView(R.id.recycle_view)
    RecyclerView recyclerView;

    OnClickInterface onClickInterface;


    private MainAdapter mainAdapter;


    @InjectPresenter
    MainPresenter mainPresenter;

    @ProvidePresenter
    public MainPresenter getMainPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        mainPresenter.getAllPhoto();
onClickInterface = new OnClickInterface() {
    @Override
    public void setClick(int abc, String url) {
        Log.e(TAG,abc+"    это и есть позиция нажатой картинки");
        Intent intent = new Intent(MainActivity.this, FullPictureActivity.class);
        intent.putExtra(keyForIntentExtra, url);
        startActivity(intent);
    }

};
        initRecyclerView();

    }

    private void initRecyclerView() {

        //LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        mainAdapter = new MainAdapter(mainPresenter.getRecyclerMain(),onClickInterface);
        recyclerView.setAdapter(mainAdapter);
    }


    @Override
    public void updateRecyclerView() {
        Log.d(TAG, "updateRecyclerView: ");
        mainAdapter.notifyDataSetChanged();
    }


    public void onClick(View view) {
//    Log.e(TAG, mainAdapter.fff()+"");

        ;


        Intent intent = new Intent(MainActivity.this, FullPictureActivity.class);
        intent.putExtra(keyForIntentExtra, "https://pixabay.com/get/52e9dc47495aa414f1dc8460ce2a327e1c3dd8f85254794f74297cd69448_640.jpg");
        startActivity(intent);
    }
}
//}"https://pixabay.com/get/52e9dc47495aa414f1dc8460ce2a327e1c3dd8f85254794f74297cd69448_640.jpg"
