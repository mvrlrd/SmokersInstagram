package ru.mvrlrd.smokersinstagram.model;

import dagger.Module;
import dagger.Provides;

@Module
public class ModelModule {
    @Provides
    Photo providePhoto() {
        return new Photo();
    }
}
