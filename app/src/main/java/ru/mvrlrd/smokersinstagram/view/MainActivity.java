package ru.mvrlrd.smokersinstagram.view;

import butterknife.ButterKnife;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import ru.mvrlrd.smokersinstagram.R;
import ru.mvrlrd.smokersinstagram.presenter.MainPresenter;

import android.os.Bundle;

public class MainActivity extends MvpAppCompatActivity implements MoxyView {

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

    }
}
