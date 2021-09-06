package kr.co.hajun.retrofit2andmvvm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<DailyBoxOffice> items;

    public MovieAdapter(List<DailyBoxOffice> items){
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DailyBoxOffice item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView rank,name;

        public ViewHolder(View itemView){
            super(itemView);
            rank = itemView.findViewById(R.id.rank);
            name = itemView.findViewById(R.id.name);
        }

        public void setItem(DailyBoxOffice item){
            rank.setText(item.getRank());
            name.setText(item.getMovieNm());
        }
    }
}