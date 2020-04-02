package ru.mvrlrd.smokersinstagram.model.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.mvrlrd.smokersinstagram.model.Photo;

public class ApiHelper {
    private static final String KEY = "9250926-552b631cddef606bad3e807d2";
    private static final String BASIC_URL = "https://pixabay.com";

    public Observable<Photo> requestServer() {

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

        return api.getPhoto(KEY).subscribeOn(Schedulers.io());
    }
}
