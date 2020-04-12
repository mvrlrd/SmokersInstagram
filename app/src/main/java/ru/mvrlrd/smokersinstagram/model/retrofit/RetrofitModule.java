package ru.mvrlrd.smokersinstagram.model.retrofit;

import dagger.Module;
import dagger.Provides;

@Module
public class RetrofitModule {
    @Provides
    ApiHelper provideApiHelper() {
        return new ApiHelper();
    }
}
