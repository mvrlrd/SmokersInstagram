package ru.mvrlrd.smokersinstagram.presenter;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {
    @Provides
    RoomPresenter provideRoomPresenter(){
        return new RoomPresenter();
    }
}
