package ru.mvrlrd.smokersinstagram.presenter;

import android.util.Log;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import ru.mvrlrd.smokersinstagram.model.retrofit.ApiHelper;
import ru.mvrlrd.smokersinstagram.model.Photo;
import ru.mvrlrd.smokersinstagram.view.IViewHolder;
import ru.mvrlrd.smokersinstagram.view.MoxyView;

@InjectViewState
public class MainPresenter extends MvpPresenter<MoxyView> {
    private static final String TAG = "MainPresenter: ";
    private ApiHelper apiHelper;
    private RecyclerMain recyclerMain;
    private Photo photo;


    public MainPresenter() {
        recyclerMain = new RecyclerMain();
        this.apiHelper = new ApiHelper();
    }

    @Override
    protected void onFirstViewAttach() {
        getAllPhoto();
    }

    public void getAllPhoto() {
        Observable<Photo> single = apiHelper.requestServer();
        Disposable disposable =
                single
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                photos -> {
                                    photo = photos;
                                    getViewState().updateRecyclerView();
                                    Log.d(TAG, "  all urls:  " + "   " + photo.getHits().get(0).webformatURL);
                                },
                                throwable -> {
                                    Log.e(TAG, "onError " + throwable + "   ");
                                });
    }

    public RecyclerMain getRecyclerMain() {
        return recyclerMain;
    }


    private class RecyclerMain implements I2RecyclerMain {

        @Override
        public void bindView(IViewHolder iViewHolder) {
            String url = photo.getHits().get(iViewHolder.getPos()).webformatURL;
            iViewHolder.setImage(url);
        }

        @Override
        public int getItemCount() {
            if (photo != null) {
                return photo.getHits().size();
            }
            return 0;
        }
    }


    public Photo getPhoto() {
        return photo;
    }
}




