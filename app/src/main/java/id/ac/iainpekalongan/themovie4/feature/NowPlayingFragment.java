package id.ac.iainpekalongan.themovie4.feature;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import id.ac.iainpekalongan.themovie4.R;
import id.ac.iainpekalongan.themovie4.adapter.MovieAdapter;
import id.ac.iainpekalongan.themovie4.api.APIClient;
import id.ac.iainpekalongan.themovie4.model.NowPlayingModel;
import id.ac.iainpekalongan.themovie4.util.Language;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NowPlayingFragment extends Fragment {

    private Context context;
    private Unbinder unbinder;

    @BindView(R.id.rv_now_playing)
    RecyclerView rv_now_playing;

    private MovieAdapter adapter;

    private Call<NowPlayingModel> apiCall;
    private APIClient apiClient = new APIClient();

    public NowPlayingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);
        context = view.getContext();

        unbinder = ButterKnife.bind(this, view);

        setupList();
        loadData();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (apiCall != null) apiCall.cancel();
    }

    private void setupList() {
        adapter = new MovieAdapter();
        rv_now_playing.setLayoutManager(new LinearLayoutManager(context));
        rv_now_playing.setAdapter(adapter);
    }

    private void loadData() {
        apiCall = apiClient.getService().getNowPlayingMovie(Language.getCountry());
        apiCall.enqueue(new Callback<NowPlayingModel>() {
            @Override
            public void onResponse(Call<NowPlayingModel> call, Response<NowPlayingModel> response) {
                if (response.isSuccessful()) {
                    adapter.replaceAll(response.body().getResults());
                } else loadFailed();
            }

            @Override
            public void onFailure(Call<NowPlayingModel> call, Throwable t) {
                loadFailed();
            }
        });
    }

    private void loadFailed() {
        Toast.makeText(context, R.string.err_load_failed, Toast.LENGTH_SHORT).show();
    }
}
