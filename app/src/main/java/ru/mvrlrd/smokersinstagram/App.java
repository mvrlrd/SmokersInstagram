package ru.mvrlrd.smokersinstagram;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import androidx.room.Room;
import ru.mvrlrd.smokersinstagram.model.room.AppDataBase;

public class App extends Application {

    public static App instance;
    private AppDataBase database;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);

        instance = this;
        database = Room.databaseBuilder(this, AppDataBase.class, "database").fallbackToDestructiveMigration()
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public AppDataBase getDatabase() {
        return database;
    }
}