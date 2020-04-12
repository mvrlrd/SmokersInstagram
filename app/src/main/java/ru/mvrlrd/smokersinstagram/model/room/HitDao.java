package ru.mvrlrd.smokersinstagram.model.room;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.Single;
import ru.mvrlrd.smokersinstagram.model.Hit;

@Dao
public interface HitDao {
        @Query("SELECT * FROM table_hits")
        Single<List<Hit>> getAll();

        @Query("SELECT * FROM table_hits WHERE id = :id")
        Single<Hit> getById(long id);

        @Insert
        Single<Long> insert(Hit hit);

        @Insert
        Single<List<Long>> insertList(List<Hit> hitList);

        @Update
        Single<Integer> update(Hit hit);

        @Update
        Single<Integer> updateAll(List<Hit> hitList);

        @Delete
        Single<Integer> delete(Hit hit);

        @Query("DELETE FROM table_hits")
        Single<Void> deleteAll();

        @Query("SELECT COUNT(id) FROM table_hits")
        Single<Integer> countOfRows();
    }
