package ru.mvrlrd.smokersinstagram.presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ru.mvrlrd.smokersinstagram.App;
import ru.mvrlrd.smokersinstagram.model.Hit;
import ru.mvrlrd.smokersinstagram.model.room.HitDao;

public class RoomPresenter {

    private static final String TAG = "RoomPresenter";
    private HitDao hitDao;


    public RoomPresenter(){
        hitDao = App.getInstance().getDatabase().hitDao();
    }

    public void putData(Hit hit){
        Disposable disposable = hitDao.insert(hit).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(id ->{
            Log.d(TAG,"putData: "+id);
        },throwable -> {
            Log.e(TAG,"onError: "+"putData "+throwable);
        });
    }
    public void updateData(List<Hit> hitList){
        Disposable disposable = hitDao.updateAll(hitList).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(id ->{
            Log.d(TAG,"putListData: "+id);
        },throwable -> {
            Log.e(TAG,"onError: "+"updateData "+throwable);
        });
    }

    public void putListData(List<Hit> hitList){
        Disposable disposable = hitDao.insertList(hitList).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(id ->{
            Log.d(TAG,"putListData");
        },throwable -> {
            Log.e(TAG,"onError: "+"putListData "+throwable);
        });
    }

    public void getData(){
        Disposable disposable = hitDao.getAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(hits ->{
            Log.d(TAG,"getData: "+hits.toString()+" "+Thread.currentThread().getName());
        }, throwable -> {
            Log.e(TAG,"onError: "+"getData "+throwable);
        });
    }

    public void deleteAll(){
        Disposable disposable = hitDao.deleteAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(hits ->{

            Log.d(TAG,"getData: "+hits.toString()+" "+Thread.currentThread().getName());
        }, throwable -> {
            Log.e(TAG,"onError: "+"deleteAll "+throwable);
        });
    }

    public HitDao getHitDao() {
        return hitDao;
    }
}