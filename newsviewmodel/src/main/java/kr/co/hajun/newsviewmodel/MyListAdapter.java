package kr.co.hajun.newsviewmodel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import kr.co.hajun.newsviewmodel.databinding.ItemMainBinding;

public class MyListAdapter extends PagedListAdapter<ItemModel, MyListAdapter.ItemViewHolder> {
    public static DiffUtil.ItemCallback<ItemModel> DIFF_CALLBACK = new DiffUtil.ItemCallback<ItemModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull ItemModel oldItem, @NonNull ItemModel newItem) {
            return oldItem.id == newItem.id;
        }


        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull ItemModel oldItem, @NonNull ItemModel newItem) {
            return oldItem.equals(newItem);
        }
    };
    public MyListAdapter(Context context){
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemMainBinding binding = ItemMainBinding.inflate(layoutInflater,parent,false);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        ItemModel article = getItem(position);
        holder.binding.setItem(article);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        ItemMainBinding binding;

        public ItemViewHolder(ItemMainBinding binding){
            super(binding.getRoot());
            this.binding = binding ;
        }
    }
}
