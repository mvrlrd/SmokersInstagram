package ru.mvrlrd.smokersinstagram.presenter;

import android.util.Log;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
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
    public Photo photo;
    RoomPresenter roomPresenter;


    public MainPresenter() {
        roomPresenter = new RoomPresenter();
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
                                    Log.e(TAG,photos.getHits().get(3).toString());
                                    getViewState().updateRecyclerView();

                                    roomPresenter.putListData(photos.getHits());

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

        @Override
        public Photo getPhoto() {
            return photo;
        }
    }

//    public void getData(){
//            Disposable disposable = roomPresenter.getHitDao().getAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(hits ->{
//                Log.d(TAG,"getData: "+hits.toString()+" "+Thread.currentThread().getName());
//                getViewState().updateRecyclerView();
//            }, throwable -> {
//                Log.d(TAG,"getData: "+throwable);
//            });
//        }

    public void deleteAll(){
        roomPresenter.deleteAll();
    }


    public Photo getPhoto() {
        return photo;
    }

}




