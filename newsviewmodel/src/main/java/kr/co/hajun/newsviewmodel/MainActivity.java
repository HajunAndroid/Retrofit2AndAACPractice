package kr.co.hajun.newsviewmodel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import kr.co.hajun.newsviewmodel.databinding.ActivityMainBinding;
import kr.co.hajun.newsviewmodel.databinding.ItemMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    MyListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyListAdapter(getApplicationContext());

        MyViewModel viewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        viewModel.getNews().observe(this,news->{
            adapter.submitList(news);
        });
        binding.recyclerView.setAdapter(adapter);
    }

    @BindingAdapter("bind:publishedAt")
    public static void publishedAt(TextView view, String date) {
        view.setText(AppUtils.getDate(date) + " at " + AppUtils.getTime(date));
    }

    @BindingAdapter("bind:urlToImage")
    public static void urlToImage(ImageView view, String url) {
        Glide.with(MyApplication.context).load(url).override(250, 200).into(view);
    }
}