package id.ac.iainpekalongan.themovie4.feature;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.ac.iainpekalongan.themovie4.R;
import id.ac.iainpekalongan.themovie4.adapter.TVAdapter;
import id.ac.iainpekalongan.themovie4.api.APIClient;
import id.ac.iainpekalongan.themovie4.model.ResultsMovieItem;
import id.ac.iainpekalongan.themovie4.model.ResultsTVItem;
import id.ac.iainpekalongan.themovie4.model.TVModel;
import id.ac.iainpekalongan.themovie4.util.Language;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static id.ac.iainpekalongan.themovie4.BaseFragment.KEY_MOVIES;
import static id.ac.iainpekalongan.themovie4.BaseFragment.KEY_TV;

/**
 * A simple {@link Fragment} subclass.
 */
public class TVFragment extends Fragment {

    private Context context;
    private Unbinder unbinder;

    @BindView(R.id.rv_tv_show)
    RecyclerView rv_upcoming;

    private TVAdapter adapter;

    private Call<TVModel> apiCall;
    private APIClient apiClient = new APIClient();
    private List<ResultsTVItem> tv = new ArrayList<>();

    public TVFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv, container, false);
        context = view.getContext();

        unbinder = ButterKnife.bind(this, view);

        setupList();
        if (savedInstanceState == null) {
            loadData();
        } else {
            tv = (List) savedInstanceState.getParcelableArrayList(KEY_TV);
            adapter.replaceAll(tv);
        }

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (apiCall != null) apiCall.cancel();
    }

    private void setupList() {
        adapter = new TVAdapter();
        rv_upcoming.setLayoutManager(new LinearLayoutManager(context));
        rv_upcoming.setAdapter(adapter);
    }

    private void loadData() {
        apiCall = apiClient.getService().getTVShow(Language.getCountry());
        apiCall.enqueue(new Callback<TVModel>() {
            @Override
            public void onResponse(Call<TVModel> call, Response<TVModel> response) {
                if (response.isSuccessful()) {
                    adapter.replaceAll(response.body().getResults());
                    tv=response.body().getResults();
                } else loadFailed();
            }

            @Override
            public void onFailure(Call<TVModel> call, Throwable t) {
                loadFailed();
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList(KEY_TV, (ArrayList) tv);
        super.onSaveInstanceState(outState);
    }

    private void loadFailed() {
        Toast.makeText(context, R.string.err_load_failed, Toast.LENGTH_SHORT).show();
    }
}
