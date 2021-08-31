package kr.co.hajun.retrofit2andmvvm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RetrofitHelper retrofitHelper;
    private RetrofitInterface retrofitInterface;
    private String API_KEY = "9360456627a07318b99e81a37090e875";

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text);

        retrofitHelper = RetrofitHelper.getInstance();
        retrofitInterface = RetrofitHelper.getRetrofitInterface();

        retrofitInterface.getBoxOffice(API_KEY,"20210830").enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();
                BoxOfficeResult boxOfficeResult = result.getBoxOfficeResult();
                List<DailyBoxOffice> dailyBoxOfficeList = boxOfficeResult.getDailyBoxOfficeList();
                for(int i = 0 ; i < 10 ; i++){
                    textView.append(dailyBoxOfficeList.get(i).getRank()+dailyBoxOfficeList.get(i).getMovieNm());
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

            }
        });
    }
}