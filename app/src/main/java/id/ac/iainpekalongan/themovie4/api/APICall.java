package id.ac.iainpekalongan.themovie4.api;

import id.ac.iainpekalongan.themovie4.model.MoviesModel;
import id.ac.iainpekalongan.themovie4.model.TVModel;
import id.ac.iainpekalongan.themovie4.model.detail.MovieDetailModel;

import id.ac.iainpekalongan.themovie4.model.detail.TVDetailModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface APICall {

    @GET("discover/movie")
    Call<MoviesModel> getMovie(@Query("language") String language);

    @GET("movie/{movie_id}")
    Call<MovieDetailModel> getDetailMovie(@Path("movie_id") String movie_id, @Query("language") String language);

    @GET("discover/tv")
    Call<TVModel> getTVShow(@Query("language") String language);

    @GET("tv/{tv_id}")
    Call<TVDetailModel> getDetailTV(@Path("tv_id") String tv_id, @Query("language") String language);
}
