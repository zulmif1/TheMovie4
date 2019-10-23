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
import id.ac.iainpekalongan.themovie4.model.MovieItems;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<MovieItems> movieLists;
    private Context context;

    public MovieAdapter(List<MovieItems> movieLists, Context context) {

        this.movieLists = movieLists;
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
        final MovieItems movList = movieLists.get(position);
        holder.title.setText(movList.getMovTitle());
        holder.overview.setText(movList.getMovOverview());

        String release_date = movList.getMovDateRelease();
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = date_format.parse(release_date);

            SimpleDateFormat new_date_format = new SimpleDateFormat("E, dd MMM yyyy");
            String date_of_release = new_date_format.format(date);
            holder.date.setText(date_of_release);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Picasso.get()
                .load("http://image.tmdb.org/t/p/w342/" + movList.getMovImage())
                .into(holder.poster);



        holder.cvMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovieItems movieList = movieLists.get(position);
                Intent Intent = new Intent(context, ActivityDetail.class);
                Intent.putExtra(ActivityDetail.EXTRA_TITLE, movieList.getMovTitle());
                Intent.putExtra(ActivityDetail.EXTRA_OVERVIEW, movieList.getMovOverview());
                Intent.putExtra(ActivityDetail.EXTRA_IMAGE_MOVIE, movieList.getMovImage());
                Intent.putExtra(ActivityDetail.EXTRA_DATE_RELEASE, movieList.getMovDateRelease());

                context.startActivity(Intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieLists.size();
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