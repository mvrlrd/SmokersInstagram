package ru.mvrlrd.smokersinstagram.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHelper {
    private static final String KEY = "";
    private static final String BASIC_URL = "";

    public Observable<Photos> requestServer() {

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gson);

        IApiService api = new Retrofit.Builder()
                .baseUrl(BASIC_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(gsonConverterFactory)
                .build()
                .create(IApiService.class);

        return api.getPhotos(KEY).subscribeOn(Schedulers.io());
    }
}
