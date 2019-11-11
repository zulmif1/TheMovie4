package id.ac.iainpekalongan.themovie4.model.detail;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TVDetailModel {

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("name")
    private String title;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("genres")
    private List<GenresItem> genres;

    @SerializedName("popularity")
    private double popularity;

    @SerializedName("id")
    private int id;

    @SerializedName("vote_count")
    private int voteCount;

    @SerializedName("overview")
    private String overview;

    @SerializedName("original_name")
    private String originalName;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("production_companies")
    private List<ProductionCompaniesItem> productionCompanies;

    @SerializedName("first_air_date")
    private String first_air_date;

    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("status")
    private String status;

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setGenres(List<GenresItem> genres) {
        this.genres = genres;
    }

    public List<GenresItem> getGenres() {
        return genres;
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

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOverview() {
        return overview;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setProductionCompanies(List<ProductionCompaniesItem> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public List<ProductionCompaniesItem> getProductionCompanies() {
        return productionCompanies;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return
                "TVDetailModel{" +
                        "original_language = '" + originalLanguage + '\'' +
                        ",title = '" + title + '\'' +
                        ",backdrop_path = '" + backdropPath + '\'' +
                        ",genres = '" + genres + '\'' +
                        ",popularity = '" + popularity + '\'' +
                        ",id = '" + id + '\'' +
                        ",vote_count = '" + voteCount + '\'' +
                        ",overview = '" + overview + '\'' +
                        ",original_title = '" + originalName + '\'' +
                        ",poster_path = '" + posterPath + '\'' +
                        ",production_companies = '" + productionCompanies + '\'' +
                        ",release_date = '" + first_air_date + '\'' +
                        ",vote_average = '" + voteAverage + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}