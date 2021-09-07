package kr.co.hajun.newsviewmodel;

import android.content.ClipData;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyViewModel extends ViewModel {
    private static final String QUERY = "kluane";
    private static final String API_KEY = "a3421825b09d4580a5898a4f11530648";
    RetrofitService networkService = RetrofitFactory.create();

    AppDatabase db = Room.databaseBuilder(MyApplication.getAppContext(),AppDatabase.class,"mydb").build();
    ArticleDAO dao = db.articleDAO();

    public MutableLiveData<List<ItemModel>> getNews(){

        ConnectivityManager connectivityManager = (ConnectivityManager) MyApplication.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null){
            return getNewsFromNetwork();
        }else{
            MutableLiveData<List<ItemModel>> liveData = new MutableLiveData<>();
            new GetAllThread(liveData).start();
            return liveData;
        }
    }
    private MutableLiveData<List<ItemModel>> getNewsFromNetwork(){
        MutableLiveData<List<ItemModel>> liveData = new MutableLiveData<>();
        networkService.getList(QUERY,API_KEY,1,10)
                .enqueue(new Callback<PageListModel>() {
                    @Override
                    public void onResponse(Call<PageListModel> call, Response<PageListModel> response) {
                        if(response.isSuccessful()){
                            liveData.postValue(response.body().articles);
                        }
                    }

                    @Override
                    public void onFailure(Call<PageListModel> call, Throwable t) {

                    }
                });
        return liveData;
    }

    class GetAllThread extends Thread{
        MutableLiveData<List<ItemModel>> liveData;
        public GetAllThread(MutableLiveData<List<ItemModel>>liveData){
            this.liveData = liveData;
        }

        @Override
        public void run() {
            List<ItemModel> daoData = dao.getAll();
            liveData.postValue(daoData);
        }
    }

    class InsertDataThread extends Thread{
        List<ItemModel> articles;
        public InsertDataThread(List<ItemModel> articles){
            this.articles = articles;
        }

        @Override
        public void run() {
            dao.deleteAll();
            dao.insertAll(articles.toArray(new ItemModel[articles.size()]));
        }
    }
}
