package ru.mvrlrd.smokersinstagram.model.retrofit;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.mvrlrd.smokersinstagram.model.Photo;

public interface IApiService {
    @GET("api")
    Observable<Photo> getPhoto(@Query("key") String key);
}
