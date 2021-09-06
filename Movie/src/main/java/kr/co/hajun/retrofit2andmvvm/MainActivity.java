package kr.co.hajun.retrofit2andmvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    private RetrofitHelper retrofitHelper;
    private RetrofitInterface retrofitInterface;

    private String API_KEY = "9360456627a07318b99e81a37090e875";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        retrofitHelper = RetrofitHelper.getInstance();
        retrofitInterface = RetrofitHelper.getRetrofitInterface();

        retrofitInterface.getBoxOffice(API_KEY,"20210830").enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();
                BoxOfficeResult boxOfficeResult = result.getBoxOfficeResult();
                mAdapter = new MovieAdapter(boxOfficeResult.getDailyBoxOfficeList());

                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }
}