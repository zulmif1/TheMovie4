package id.ac.iainpekalongan.themovie4.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.ac.iainpekalongan.themovie4.ActivityDetail;
import id.ac.iainpekalongan.themovie4.R;
import id.ac.iainpekalongan.themovie4.model.TVItems;

public class TVAdapter extends RecyclerView.Adapter<TVAdapter.ViewHolder> {

    private List<TVItems> tvLists;
    private Context context;

    public TVAdapter(List<TVItems> tvLists, Context context) {

        this.tvLists = tvLists;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.all_list, parent, false);
        ButterKnife.bind(this, v);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        //bind data to view Holder
        final TVItems tvList = tvLists.get(position);
        holder.title.setText(tvList.getTvTitle());
        holder.overview.setText(tvList.getTvOverview());

        String release_date = tvList.getTvDateRelease();
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = date_format.parse(release_date);

            SimpleDateFormat new_date_format = new SimpleDateFormat("E, MMM dd, yyyy");
            String date_of_release = new_date_format.format(date);
            holder.date.setText(date_of_release);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Picasso.get()
                .load("http://image.tmdb.org/t/p/w342/" + tvList.getTvImage())
                .into(holder.poster);



        holder.cvMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TVItems tvList = tvLists.get(position);
                Intent Intent = new Intent(context, ActivityDetail.class);
                Intent.putExtra(ActivityDetail.EXTRA_TITLE, tvList.getTvTitle());
                Intent.putExtra(ActivityDetail.EXTRA_OVERVIEW, tvList.getTvOverview());
                Intent.putExtra(ActivityDetail.EXTRA_IMAGE_MOVIE, tvList.getTvImage());
                Intent.putExtra(ActivityDetail.EXTRA_DATE_RELEASE, tvList.getTvDateRelease());

                context.startActivity(Intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tvLists.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        //init view objects use butterknife lib
        @BindView(R.id.tv_item_title)
        TextView title;
        @BindView(R.id.tv_item_overview)
        TextView overview;
        @BindView(R.id.tv_item_date)
        TextView date;
        @BindView(R.id.img_item_poster)
        ImageView poster;

        @BindView(R.id.cv_movie)
        LinearLayout cvMovie;


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }

    }
}