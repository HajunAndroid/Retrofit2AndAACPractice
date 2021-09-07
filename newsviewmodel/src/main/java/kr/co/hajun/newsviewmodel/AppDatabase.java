package kr.co.hajun.newsviewmodel;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ItemModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ArticleDAO articleDAO();
}
