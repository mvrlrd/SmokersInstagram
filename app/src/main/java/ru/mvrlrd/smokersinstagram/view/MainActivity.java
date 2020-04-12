package ru.mvrlrd.smokersinstagram.view;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import ru.mvrlrd.smokersinstagram.R;
import ru.mvrlrd.smokersinstagram.presenter.MainPresenter;
import ru.mvrlrd.smokersinstagram.view.fullsizeview.FullPictureActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

public class MainActivity extends MvpAppCompatActivity implements MoxyView {
    public static final String keyForIntentExtra = "ru.mvrlrd.smokersinstagram.view.MainActivity.URL";
    private static final String TAG = "MainActivity";
    @BindView(R.id.recycle_view)
    RecyclerView recyclerView;
    private OnClickInterface onClickInterface;
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
        onClickInterface = new OnClickInterface() {
            @Override
            public void setClick(int abc, String url) {
                Log.e(TAG, abc + "    это и есть позиция нажатой картинки");
                Intent intent = new Intent(MainActivity.this, FullPictureActivity.class);
                intent.putExtra(keyForIntentExtra, url);
                startActivity(intent);
            }

        };
        initRecyclerView();



    }

    private void initRecyclerView() {
        //LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        int spanCountForGridLayout;
        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            spanCountForGridLayout = 2;
        } else {
            spanCountForGridLayout = 3;
        }

        GridLayoutManager layoutManager = new GridLayoutManager(this, spanCountForGridLayout);
        recyclerView.setLayoutManager(layoutManager);
        mainAdapter = new MainAdapter(mainPresenter.getRecyclerMain(), onClickInterface);
        recyclerView.setAdapter(mainAdapter);

    }


    @Override
    public void updateRecyclerView() {
        Log.d(TAG, "updateRecyclerView: ");
        mainAdapter.notifyDataSetChanged();
    }
    @OnClick(R.id.button)
    public void getData(){
//        mainPresenter.getData();
        mainPresenter.deleteAll();
    }



}