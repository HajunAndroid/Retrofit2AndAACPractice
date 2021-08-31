package kr.co.hajun.retrofit2andmvvm;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {
    private static RetrofitHelper instance = null;
    private static RetrofitInterface retrofitInterface;
    private static String baseUrl = "http://www.kobis.or.kr/";

    private RetrofitHelper(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);
    }

    public static RetrofitHelper getInstance(){
        if(instance == null){
            instance = new RetrofitHelper();
        }
        return instance;
    }

    public static RetrofitInterface getRetrofitInterface(){
        return retrofitInterface;
    }
}
