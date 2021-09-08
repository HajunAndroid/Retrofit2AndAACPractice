package kr.co.hajun.newsviewmodel;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ArticleDAO {
    @Query("SELECT * FROM article")
    DataSource.Factory<Integer, ItemModel> getAll();

    @Insert
    void insertAll(ItemModel... users);

    @Query("DELETE FROM article")
    void deleteAll();
}
