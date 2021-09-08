package kr.co.hajun.newsviewmodel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyDataSource extends PageKeyedDataSource<Long, ItemModel> {
    private static final String QUERY = "travel";
    private static final String API_KEY = "a3421825b09d4580a5898a4f11530648";

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<Long, ItemModel> callback) {
        MyApplication.networkService.getList(QUERY,API_KEY,1,params.requestedLoadSize)
                .enqueue(new Callback<PageListModel>() {
                    @Override
                    public void onResponse(Call<PageListModel> call, Response<PageListModel> response) {
                        if(response.isSuccessful()){
                            Log.d("yang",new Gson().toJson(response.raw()));
                            Log.d("yang",new Gson().toJson(response.body()));
                            callback.onResult(response.body().articles, null, 2L);

                            new InsertDataThread(response.body().articles).start();
                        }
                    }

                    @Override
                    public void onFailure(Call<PageListModel> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, ItemModel> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, ItemModel> callback) {
        MyApplication.networkService.getList(QUERY,API_KEY,params.key,params.requestedLoadSize)
                .enqueue(new Callback<PageListModel>() {
                    @Override
                    public void onResponse(Call<PageListModel> call, Response<PageListModel> response) {
                        if(response.isSuccessful()){
                            long nextKey = (params.key==response.body().totalResults) ? null : params.key + 1;
                            callback.onResult(response.body().articles,nextKey);
                        }
                    }

                    @Override
                    public void onFailure(Call<PageListModel> call, Throwable t) {

                    }
                });
    }

    class InsertDataThread extends Thread{
        List<ItemModel> articles;
        public InsertDataThread(List<ItemModel> articles){
            this.articles = articles;
        }

        @Override
        public void run() {
            MyApplication.dao.deleteAll();
            MyApplication.dao.insertAll(articles.toArray(new ItemModel[articles.size()]));
        }
    }
}
