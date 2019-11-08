package id.ac.iainpekalongan.themovie4.api;

import id.ac.iainpekalongan.themovie4.model.MoviesModel;
import id.ac.iainpekalongan.themovie4.model.TVModel;
import id.ac.iainpekalongan.themovie4.model.detail.DetailModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface APICall {

    @GET("movie/now_playing")
    Call<MoviesModel> getMovie(@Query("language") String language);

    @GET("movie/{movie_id}")
    Call<DetailModel> getDetailMovie(@Path("movie_id") String movie_id, @Query("language") String language);

    @GET("movie/upcoming")
    Call<TVModel> getTVShow(@Query("language") String language);
}
