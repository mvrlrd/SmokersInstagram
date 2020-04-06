package ru.mvrlrd.smokersinstagram.presenter.secondpresenter;


import android.util.Log;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import ru.mvrlrd.smokersinstagram.view.fullsizeview.SecondView;

@InjectViewState
public class DetailedPresenter extends MvpPresenter<SecondView> {

    private static final String TAG = "DetailedPresenter: ";
    private String url;

    public DetailedPresenter() {
        Log.e(TAG,"constructor");
    }

    @Override
    protected void onFirstViewAttach() {
        if (!"".equals(url)) {
            getViewState().setImage(url);
        }
    }

    public void setUrl(String url){
        this.url = url;
    }
}
