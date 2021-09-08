package kr.co.hajun.newsviewmodel;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;


public class MyViewModel extends ViewModel {
   LiveData<PagedList<ItemModel>> itemLiveData;
   PagedList.Config pagedListConfig;
   public MyViewModel(){
       pagedListConfig = (new PagedList.Config.Builder())
               .setInitialLoadSizeHint(3)
               .setPageSize(3)
               .build();
   }
   public LiveData<PagedList<ItemModel>> getNews(){
       ConnectivityManager connectivityManager = (ConnectivityManager) MyApplication.context.getSystemService(Context.CONNECTIVITY_SERVICE);
       NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
       if(networkInfo != null){
           MyDataFactory dataFactory = new MyDataFactory();
           itemLiveData = (new LivePagedListBuilder(dataFactory,pagedListConfig)).build();
        return itemLiveData;
       }else{
           DataSource.Factory<Integer, ItemModel> factory= MyApplication.dao.getAll();
            itemLiveData=(new LivePagedListBuilder(factory,pagedListConfig)).build();
            return itemLiveData;
       }
   }
}
