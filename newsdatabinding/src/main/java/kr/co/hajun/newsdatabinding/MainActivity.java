package kr.co.hajun.newsdatabinding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import kr.co.hajun.newsdatabinding.databinding.ActivityMainBinding;
import kr.co.hajun.newsdatabinding.databinding.ItemMainBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String QUERY = "movies";
    private static final String API_KEY = "a3421825b09d4580a5898a4f11530648";
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RetrofitService networkService = RetrofitFactory.create();
        networkService.getList(QUERY,API_KEY,1,10)
                .enqueue(new Callback<PageListModel>() {
                    @Override
                    public void onResponse(Call<PageListModel> call, Response<PageListModel> response) {
                        if(response.isSuccessful()){
                            MyAdapter adapter = new MyAdapter(response.body().articles);
                            binding.recyclerView.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<PageListModel> call, Throwable t) {

                    }
                });
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        ItemMainBinding binding;
        public ItemViewHolder(ItemMainBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }
    }

    class MyAdapter extends RecyclerView.Adapter<ItemViewHolder>{
        List<ItemModel> articles;
        public MyAdapter(List<ItemModel> articles){
            this.articles = articles;
        }
        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ItemMainBinding binding = ItemMainBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
            return new ItemViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
            ItemModel model = articles.get(position);
            holder.binding.setItem(model);
        }

        @Override
        public int getItemCount() {
            return articles.size();
        }
    }

    @BindingAdapter("bind:publishedAt")
    public static void publishedAt(TextView view, String date){
        view.setText(AppUtils.getDate(date)+"at"+AppUtils.getTime(date));
    }

    @BindingAdapter("bind:urlToImage")
    public static void urlToImage(ImageView view, String url){
        Glide.with(view.getContext()).load(url).override(250,200).into(view);
    }
}