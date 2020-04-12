package ru.mvrlrd.smokersinstagram.presenter;

import android.util.Log;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.subscriptions.AsyncSubscription;
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
//        photo= new Photo();
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
                                    Disposable disposable2 = roomPresenter.getHitDao().countOfRows().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(num ->{
                                        Log.d(TAG,"getAllPhoto: "+num);
                                        if (num==0){
                                            photo = photos;
                                            getViewState().updateRecyclerView();
                                            roomPresenter.putListData(photos.getHits());
                                            Log.d(TAG, "getAllPhoto: "+"room was empty, data was put into room, photos' urls were downloaded from server.");
                                        }else{
                                            photo=new Photo();
                                            getData();
                                        }
                                    }, throwable -> {
                                        Log.e(TAG,"countOfRows: "+throwable);
                                    });

                                    Log.d(TAG, "getAllPhoto: "+"data has already been put into room");
                                },
                                throwable -> {
                                    Log.e(TAG, "onError "+"getAllPhoto " + throwable);
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

    public void getData(){
            Disposable disposable = roomPresenter.getHitDao().getAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(hits ->{
                photo.setHits(hits);
                Log.d(TAG,"getData: photos' urls were downloaded from Room. "+hits.toString()+" "+Thread.currentThread().getName());
                getViewState().updateRecyclerView();
            }, throwable -> {
                Log.e(TAG,"getData: "+throwable);
            });
        }


    public void deleteAll(){
        roomPresenter.deleteAll();
    }




}




