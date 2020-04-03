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

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends MvpAppCompatActivity implements MoxyView {
    private static final String TAG = "MainActivity";
    @BindView(R.id.recycle_view)
    RecyclerView recyclerView;




    private MainAdapter mainAdapter;


@InjectPresenter
    MainPresenter mainPresenter;

@ProvidePresenter
public MainPresenter getMainPresenter(){
    return new MainPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainPresenter.getAllPhoto();

        initRecyclerView();

    }

    private void initRecyclerView(){

        //LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        mainAdapter = new MainAdapter(  mainPresenter.getRecyclerMain());
        recyclerView.setAdapter(mainAdapter);
    }

    @Override
    public void updateRecyclerView() {
        Log.d(TAG, "updateRecyclerView: ");
        mainAdapter.notifyDataSetChanged();
    }



    public void onClick(View view) {

        Log.e(TAG,view.getDisplay()+"\n"
        );
    }
}
