package kr.co.hajun.newsviewmodel;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

public class MyApplication extends Application {

    public static Context context;
    public static ArticleDAO dao;
    public static RetrofitService networkService;
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, "mydb").build();
        dao = db.articleDAO();
        networkService = RetrofitFactory.create();
    }
}

