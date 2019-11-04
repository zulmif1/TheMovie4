package id.ac.iainpekalongan.themovie4.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ListView;

import id.ac.iainpekalongan.themovie4.R;
import id.ac.iainpekalongan.themovie4.model.ResultsItem;

import java.util.ArrayList;
import java.util.List;


public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private List<ResultsItem> list = new ArrayList<>();

    public MovieAdapter() {
    }

    public void clearAll() {
        list.clear();
        notifyDataSetChanged();
    }

    public void replaceAll(List<ResultsItem> items) {
        list.clear();
        list = items;
        notifyDataSetChanged();
    }

    public void updateData(List<ResultsItem> items) {
        list.addAll(items);
        notifyDataSetChanged();
    }


    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.activity_detail_item, parent, false
                )
        );
    }


    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
