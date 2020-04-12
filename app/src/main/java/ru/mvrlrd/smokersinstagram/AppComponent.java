package ru.mvrlrd.smokersinstagram;

import dagger.Component;
import ru.mvrlrd.smokersinstagram.model.ModelModule;
import ru.mvrlrd.smokersinstagram.model.Photo;
import ru.mvrlrd.smokersinstagram.model.retrofit.ApiHelper;
import ru.mvrlrd.smokersinstagram.model.retrofit.RetrofitModule;
import ru.mvrlrd.smokersinstagram.presenter.RoomModule;
import ru.mvrlrd.smokersinstagram.presenter.RoomPresenter;


@Component(modules = {RetrofitModule.class, ModelModule.class, RoomModule.class})
public interface AppComponent {
    ApiHelper getApiHelper();
    Photo getPhoto();
    RoomPresenter getRoomPresenter();
}
