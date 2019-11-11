package id.ac.iainpekalongan.themovie4.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import id.ac.iainpekalongan.themovie4.R;
import id.ac.iainpekalongan.themovie4.model.ResultsMovieItem;
import id.ac.iainpekalongan.themovie4.model.ResultsTVItem;


public class TVAdapter extends RecyclerView.Adapter<TVViewHolder> {

    private List<ResultsTVItem> list = new ArrayList<>();

    public TVAdapter() {
    }

    public void clearAll() {
        list.clear();
        notifyDataSetChanged();
    }

    public void replaceAll(List<ResultsTVItem> items) {
        list.clear();
        list = items;
        notifyDataSetChanged();
    }

    public void updateData(List<ResultsTVItem> items) {
        list.addAll(items);
        notifyDataSetChanged();
    }


    @Override
    public TVViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TVViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.activity_tv_detail_item, parent, false
                )
        );
    }


    @Override
    public void onBindViewHolder(TVViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
