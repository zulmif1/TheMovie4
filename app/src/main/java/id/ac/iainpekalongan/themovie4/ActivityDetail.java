package id.ac.iainpekalongan.themovie4;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.ac.iainpekalongan.themovie4.database.FavoriteHelper;
import id.ac.iainpekalongan.themovie4.model.ResultsItem;
import id.ac.iainpekalongan.themovie4.provider.FavoriteColumns;

import static android.provider.Settings.System.CONTENT_URI;

public class ActivityDetail extends AppCompatActivity {

    public static final String MOVIE_ITEM = "movie_item";
    public static String EXTRA_TITLE = "extra_title";
    public static String EXTRA_OVERVIEW = "extra_overview";
    public static String EXTRA_DATE_RELEASE = "extra_date_release";
    public static String EXTRA_IMAGE_MOVIE = "extra_image_movie";
    private Boolean isFavorite = false;
    private ResultsItem item;
    private Gson gson = new Gson();
    private FavoriteHelper favoriteHelper;


    @BindView(R.id.tv_detail_title)
    TextView tvTitle;
    @BindView(R.id.tv_detail_overview)
    TextView tvOverview;
    @BindView(R.id.tv_detail_date)
    TextView tvDateRelease;
    @BindView(R.id.image_movie)
    ImageView imgMovie;
    @BindView(R.id.iv_fav)
    ImageView iv_fav;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        //get intent
        String title = getIntent().getStringExtra(EXTRA_TITLE);
        String overview = getIntent().getStringExtra(EXTRA_OVERVIEW);
        String image = getIntent().getStringExtra(EXTRA_IMAGE_MOVIE);
        String dateRelease = getIntent().getStringExtra(EXTRA_DATE_RELEASE);


        //Format Date Release
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = date_format.parse(dateRelease);

            SimpleDateFormat new_date_format = new SimpleDateFormat("EEEE, dd MMM yyyy");
            String date_of_release = new_date_format.format(date);
            tvDateRelease.setText(date_of_release);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        //pass value using set function
        tvTitle.setText(title);
        tvOverview.setText(overview);
        Picasso.get().load("http://image.tmdb.org/t/p/original/" + image).into(imgMovie);

        String json = getIntent().getStringExtra(MOVIE_ITEM);
        item = gson.fromJson(json, ResultsItem.class);
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

    private void favoriteSet() {
        if (isFavorite) iv_fav.setImageResource(R.drawable.ic_favorite);
        else iv_fav.setImageResource(R.drawable.ic_favorite_border);
    }

    private void FavoriteSave() {
        //Log.d("TAG", "FavoriteSave: " + item.getId());
        ContentValues cv = new ContentValues();
        cv.put(FavoriteColumns.COLUMN_ID, item.getId());
        cv.put(FavoriteColumns.COLUMN_TITLE, item.getTitle());
        cv.put(FavoriteColumns.COLUMN_BACKDROP, item.getBackdropPath());
        cv.put(FavoriteColumns.COLUMN_POSTER, item.getPosterPath());
        cv.put(FavoriteColumns.COLUMN_RELEASE_DATE, item.getReleaseDate());
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

    private void loadData() {
        loadDataSQLite();
        loadDataInServer(String.valueOf(item.getId()));

        getSupportActionBar().setTitle(item.getTitle());
        tv_title.setText(item.getTitle());

        Glide.with(this)
                .load(BuildConfig.BASE_URL_IMG + "w342" + item.getBackdropPath())
                .into(img_backdrop);

        Glide.with(this)
                .load(BuildConfig.BASE_URL_IMG + "w154" + item.getPosterPath())
                .into(img_poster);

        tv_release_date.setText(DateTime.getLongDate(item.getReleaseDate()));
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

    private void loadDataInServer(String movie_item) {
        apiCall = apiClient.getService().getDetailMovie(movie_item, Language.getCountry());
        apiCall.enqueue(new Callback<DetailModel>() {
            @Override
            public void onResponse(Call<DetailModel> call, Response<DetailModel> response) {
                if (response.isSuccessful()) {
                    DetailModel item = response.body();

                    int size = 0;

                    String genres = "";
                    size = item.getGenres().size();
                    for (int i = 0; i < size; i++) {
                        genres += "√ " + item.getGenres().get(i).getName() + (i + 1 < size ? "\n" : "");
                    }
                    tv_genres.setText(genres);

                    if (item.getBelongsToCollection() != null) {
                        Glide.with(DetailActivity.this)
                                .load(BuildConfig.BASE_URL_IMG + "w92" + item.getBelongsToCollection().getPosterPath())
                                .into(img_poster_belongs);

                        tv_title_belongs.setText(item.getBelongsToCollection().getName());
                    }

                    tv_budget.setText("$ " + NumberFormat.getIntegerInstance().format(item.getBudget()));
                    tv_revenue.setText("$ " + NumberFormat.getIntegerInstance().format(item.getRevenue()));

                    String companies = "";
                    size = item.getProductionCompanies().size();
                    for (int i = 0; i < size; i++) {
                        companies += "√ " + item.getProductionCompanies().get(i).getName() + (i + 1 < size ? "\n" : "");
                    }
                    tv_companies.setText(companies);

                    String countries = "";
                    size = item.getProductionCountries().size();
                    for (int i = 0; i < size; i++) {
                        countries += "√ " + item.getProductionCountries().get(i).getName() + (i + 1 < size ? "\n" : "");
                    }
                    tv_countries.setText(countries);
                } else loadFailed();
            }

            @Override
            public void onFailure(Call<DetailModel> call, Throwable t) {
                loadFailed();
            }
        });
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
}
