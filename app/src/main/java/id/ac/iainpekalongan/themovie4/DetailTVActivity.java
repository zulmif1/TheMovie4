package id.ac.iainpekalongan.themovie4;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import id.ac.iainpekalongan.themovie4.api.APIClient;
import id.ac.iainpekalongan.themovie4.database.FavoriteHelper;
import id.ac.iainpekalongan.themovie4.model.ResultsTVItem;
import id.ac.iainpekalongan.themovie4.model.detail.TVDetailModel;
import id.ac.iainpekalongan.themovie4.provider.FavoriteColumns;
import id.ac.iainpekalongan.themovie4.util.DateTime;
import id.ac.iainpekalongan.themovie4.util.Language;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static id.ac.iainpekalongan.themovie4.provider.DatabaseContract.CONTENT_URI;

public class DetailTVActivity extends AppCompatActivity {

    public static final String TV_ITEM = "tv_item";

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsing_toolbar;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_name)
    TextView tv_title;

    @BindView(R.id.img_backdrop)
    ImageView img_backdrop;

    @BindView(R.id.img_poster)
    ImageView img_poster;

    @BindView(R.id.tv_firs_air_date)
    TextView tv_first_air_date;

    @BindView(R.id.tv_vote)
    TextView tv_vote;

    @BindViews({
            R.id.img_star1,
            R.id.img_star2,
            R.id.img_star3,
            R.id.img_star4,
            R.id.img_star5
    })
    List<ImageView> img_vote;

    @BindView(R.id.tv_genres)
    TextView tv_genres;

    @BindView(R.id.tv_overview)
    TextView tv_overview;

    @BindView(R.id.iv_fav)
    ImageView iv_fav;

    private Call<TVDetailModel> apiCall;
    private APIClient apiClient = new APIClient();
    private Gson gson = new Gson();

    private ResultsTVItem item;
    private FavoriteHelper favoriteHelper;
    private Boolean isFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_detail);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsing_toolbar.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

        String json = getIntent().getStringExtra(TV_ITEM);
        item = gson.fromJson(json, ResultsTVItem.class);
        loadData();

        iv_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFavorite) FavoriteRemove();
                else FavoriteSave();

                isFavorite = !isFavorite;
                favoriteSet();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (apiCall != null) apiCall.cancel();
        if (favoriteHelper != null) favoriteHelper.close();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void favoriteSet() {
        if (isFavorite) iv_fav.setImageResource(R.drawable.ic_favorite);
        else iv_fav.setImageResource(R.drawable.ic_favorite_border);
    }

    private void loadData() {
        loadDataSQLite();
        loadDataInServer(String.valueOf(item.getId()));

        getSupportActionBar().setTitle(item.getOriginalName());
        tv_title.setText(item.getName());

        Glide.with(this)
                .load(BuildConfig.BASE_URL_IMG + "w342" + item.getBackdropPath())
                .into(img_backdrop);

        Glide.with(this)
                .load(BuildConfig.BASE_URL_IMG + "w154" + item.getPosterPath())
                .into(img_poster);

        tv_first_air_date.setText(DateTime.getLongDate(item.getFirstAirDate()));
        tv_vote.setText(String.valueOf(item.getVoteAverage()));
        tv_overview.setText(item.getOverview());

        double userRating = item.getVoteAverage() / 2;
        int integerPart = (int) userRating;

        // Fill stars
        for (int i = 0; i < integerPart; i++) {
            img_vote.get(i).setImageResource(R.drawable.ic_star_black_24dp);
        }

        // Fill half star
        if (Math.round(userRating) > integerPart) {
            img_vote.get(integerPart).setImageResource(R.drawable.ic_star_half_black_24dp);
        }
    }

    private void loadDataSQLite() {
        favoriteHelper = new FavoriteHelper(this);
        favoriteHelper.open();

        Cursor cursor = getContentResolver().query(
                Uri.parse(CONTENT_URI + "/" + item.getId()),
                null,
                null,
                null,
                null
        );

        if (cursor != null) {
            if (cursor.moveToFirst()) isFavorite = true;
            cursor.close();
        }
        favoriteSet();
    }

    private void loadDataInServer(String tv_item) {
        apiCall = apiClient.getService().getDetailTV(tv_item, Language.getCountry());
        apiCall.enqueue(new Callback<TVDetailModel>() {
            @Override
            public void onResponse(Call<TVDetailModel> call, Response<TVDetailModel> response) {
                if (response.isSuccessful()) {
                    TVDetailModel item = response.body();

                    int size = 0;

                    String genres = "";
                    size = item.getGenres().size();
                    for (int i = 0; i < size; i++) {
                        genres += "√ " + item.getGenres().get(i).getName() + (i + 1 < size ? "\n" : "");
                    }
                    tv_genres.setText(genres);

                } else loadFailed();
            }

            @Override
            public void onFailure(Call<TVDetailModel> call, Throwable t) {
                loadFailed();
            }
        });
    }

    private void loadFailed() {
        Toast.makeText(this, R.string.err_load_failed, Toast.LENGTH_SHORT).show();
    }

    private void FavoriteSave() {
        //Log.d("TAG", "FavoriteSave: " + item.getId());
        ContentValues cv = new ContentValues();
        cv.put(FavoriteColumns.COLUMN_ID, item.getId());
        cv.put(FavoriteColumns.COLUMN_TITLE, item.getName());
        cv.put(FavoriteColumns.COLUMN_BACKDROP, item.getBackdropPath());
        cv.put(FavoriteColumns.COLUMN_POSTER, item.getPosterPath());
        cv.put(FavoriteColumns.COLUMN_RELEASE_DATE, item.getFirstAirDate());
        cv.put(FavoriteColumns.COLUMN_VOTE, item.getVoteAverage());
        cv.put(FavoriteColumns.COLUMN_OVERVIEW, item.getOverview());

        getContentResolver().insert(CONTENT_URI, cv);
        Toast.makeText(this, R.string.cv_save, Toast.LENGTH_SHORT).show();
    }

    private void FavoriteRemove() {
        getContentResolver().delete(
                Uri.parse(CONTENT_URI + "/" + item.getId()),
                null,
                null
        );
        Toast.makeText(this, R.string.cv_remove, Toast.LENGTH_SHORT).show();
    }
}
