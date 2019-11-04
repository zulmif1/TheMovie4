package id.ac.iainpekalongan.themovie4.feature;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import id.ac.iainpekalongan.themovie4.R;
import id.ac.iainpekalongan.themovie4.adapter.MovieAdapter;
import id.ac.iainpekalongan.themovie4.api.APIClient;
import id.ac.iainpekalongan.themovie4.model.SearchModel;
import id.ac.iainpekalongan.themovie4.util.Language;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    public static final String MOVIE_TITLE = "movie_title";

    @BindView(R.id.rv_search)
    RecyclerView rv_search;

    private MovieAdapter adapter;

    private Call<SearchModel> apiCall;
    private APIClient apiClient = new APIClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);

        setupList();

        String movie_title = getIntent().getStringExtra(MOVIE_TITLE);
        loadData(movie_title);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (apiCall != null) apiCall.cancel();
    }

    private void setupList() {
        adapter = new MovieAdapter();
        rv_search.setLayoutManager(new LinearLayoutManager(this));
        rv_search.setAdapter(adapter);
    }

    private void loadData(String movie_title) {
        apiCall = apiClient.getService().getSearchMovie(movie_title, Language.getCountry());
        apiCall.enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                if (response.isSuccessful()) {
                    adapter.replaceAll(response.body().getResults());
                } else loadFailed();
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {
                loadFailed();
            }
        });
    }

    private void loadFailed() {
        Toast.makeText(this, R.string.err_load_failed, Toast.LENGTH_SHORT).show();
    }
}
