package ru.mvrlrd.smokersinstagram.view.fullsizeview;

import butterknife.BindView;
import butterknife.ButterKnife;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import ru.mvrlrd.smokersinstagram.PicassoLoader;
import ru.mvrlrd.smokersinstagram.R;
import ru.mvrlrd.smokersinstagram.presenter.secondpresenter.DetailedPresenter;
import ru.mvrlrd.smokersinstagram.view.MainActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class FullPictureActivity extends MvpAppCompatActivity implements SecondView {

    @BindView(R.id.fullSizeImageView)
    ImageView fullSzeImageView;
    @InjectPresenter
    DetailedPresenter detailedPresenter;

    @ProvidePresenter
    public DetailedPresenter getDetailedPresenter(){
        return new DetailedPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_picture);
        ButterKnife.bind(this);

        detailedPresenter.setUrl(getIntent().getStringExtra(MainActivity.keyForIntentExtra));
    }

    @Override
    public void setImage(String url) {
        PicassoLoader.loadImage(url, fullSzeImageView);
    }


}
