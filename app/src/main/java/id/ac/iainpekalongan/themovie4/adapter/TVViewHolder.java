package id.ac.iainpekalongan.themovie4.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.ac.iainpekalongan.themovie4.BuildConfig;
import id.ac.iainpekalongan.themovie4.DetailTVActivity;
import id.ac.iainpekalongan.themovie4.R;
import id.ac.iainpekalongan.themovie4.model.ResultsTVItem;
import id.ac.iainpekalongan.themovie4.util.DateTime;

public class TVViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.img_poster)
    ImageView img_poster;

    @BindView(R.id.tv_name)
    TextView tv_title;

    @BindView(R.id.tv_overview)
    TextView tv_overview;

    @BindView(R.id.tv_firs_air_date)
    TextView tv_first_air_date;

    @BindView(R.id.btn_detail)
    Button btn_detail;

    public TVViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final ResultsTVItem item) {
        tv_title.setText(item.getOriginalName());
        tv_overview.setText(item.getOverview());
        tv_first_air_date.setText(DateTime.getLongDate(item.getFirstAirDate()));
        Glide.with(itemView.getContext())
                .load(BuildConfig.BASE_URL_IMG + "w154" + item.getPosterPath())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.placeholder)
                        .centerCrop()
                )
                .into(img_poster);

        btn_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(itemView.getContext(), DetailTVActivity.class);
                intent.putExtra(DetailTVActivity.TV_ITEM, new Gson().toJson(item));
                itemView.getContext().startActivity(intent);
            }
        });

    }
}
