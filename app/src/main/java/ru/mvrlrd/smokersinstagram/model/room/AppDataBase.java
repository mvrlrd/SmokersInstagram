package ru.mvrlrd.smokersinstagram.model.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import ru.mvrlrd.smokersinstagram.model.Hit;

@Database(entities =  {Hit.class}, version = 5, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    public abstract HitDao hitDao();

}
