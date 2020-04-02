package ru.mvrlrd.smokersinstagram.presenter;

import android.util.Log;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import ru.mvrlrd.smokersinstagram.model.retrofit.ApiHelper;
import ru.mvrlrd.smokersinstagram.model.Hit;
import ru.mvrlrd.smokersinstagram.model.Photo;
import ru.mvrlrd.smokersinstagram.view.MoxyView;

@InjectViewState
public class MainPresenter extends MvpPresenter<MoxyView> {
    private static final String TAG = "MainPresenter: ";
    private List<Hit> hitList;
    private ApiHelper apiHelper;

    public MainPresenter() {
        this.apiHelper = new ApiHelper();
    }

    public void getAllPhoto() {
        Observable<Photo> single = apiHelper.requestServer();
        Disposable disposable =
                single
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                photos -> {
                                    hitList=photos.hits;


                                    Log.d(TAG, "  all urls:  " + "   " + photos.hits.toString());
                                },
                                throwable -> {
                                    Log.e(TAG, "onError " + throwable + "   ");
                                });
    }
}
