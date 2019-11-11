package id.ac.iainpekalongan.themovie4.model;

import android.database.Cursor;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import static android.provider.BaseColumns._ID;
import static id.ac.iainpekalongan.themovie4.provider.DatabaseContract.getColumnDouble;
import static id.ac.iainpekalongan.themovie4.provider.DatabaseContract.getColumnInt;
import static id.ac.iainpekalongan.themovie4.provider.DatabaseContract.getColumnString;
import static id.ac.iainpekalongan.themovie4.provider.FavoriteColumns.COLUMN_BACKDROP;
import static id.ac.iainpekalongan.themovie4.provider.FavoriteColumns.COLUMN_OVERVIEW;
import static id.ac.iainpekalongan.themovie4.provider.FavoriteColumns.COLUMN_POSTER;
import static id.ac.iainpekalongan.themovie4.provider.FavoriteColumns.COLUMN_RELEASE_DATE;
import static id.ac.iainpekalongan.themovie4.provider.FavoriteColumns.COLUMN_VOTE;

public class ResultsTVItem {

    @SerializedName("overview")
    private String overview;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("original_name")
    private String originalName;

    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @SerializedName("genre_ids")
    private List<Integer> genreIds;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("first_air_date")
    private String firstAirDate;

    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("popularity")
    private double popularity;

    @SerializedName("id")
    private int id;

    @SerializedName("vote_count")
    private int voteCount;

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOverview() {
        return overview;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public ResultsTVItem() {
    }

    public ResultsTVItem(Cursor cursor) {
        this.id = getColumnInt(cursor, _ID);
        this.backdropPath = getColumnString(cursor, COLUMN_BACKDROP);
        this.posterPath = getColumnString(cursor, COLUMN_POSTER);
        this.firstAirDate = getColumnString(cursor, COLUMN_RELEASE_DATE);
        this.voteAverage = getColumnDouble(cursor, COLUMN_VOTE);
        this.overview = getColumnString(cursor, COLUMN_OVERVIEW);
    }

    @Override
    public String toString() {
        return
                "ResultsTVItem{" +
                        "overview = '" + overview + '\'' +
                        ",original_language = '" + originalLanguage + '\'' +
                        ",original_title = '" + originalName + '\'' +
                        ",genre_ids = '" + genreIds + '\'' +
                        ",poster_path = '" + posterPath + '\'' +
                        ",backdrop_path = '" + backdropPath + '\'' +
                        ",first_air_date = '" + firstAirDate + '\'' +
                        ",vote_average = '" + voteAverage + '\'' +
                        ",popularity = '" + popularity + '\'' +
                        ",id = '" + id + '\'' +
                        ",vote_count = '" + voteCount + '\'' +
                        "}";
    }
}