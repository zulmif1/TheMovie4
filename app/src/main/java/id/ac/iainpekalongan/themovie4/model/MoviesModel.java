package id.ac.iainpekalongan.themovie4.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MoviesModel implements Parcelable {

    @SerializedName("dates")
    private Dates dates;

    @SerializedName("page")
    private int page;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("results")
    private List<ResultsMovieItem> results;

    @SerializedName("total_results")
    private int totalResults;

    public void setDates(Dates dates) {
        this.dates = dates;
    }

    public Dates getDates() {
        return dates;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setResults(List<ResultsMovieItem> results) {
        this.results = results;
    }

    public List<ResultsMovieItem> getResults() {
        return results;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalResults() {
        return totalResults;
    }

    @Override
    public String toString() {
        return
                "MoviesModel{" +
                        "dates = '" + dates + '\'' +
                        ",page = '" + page + '\'' +
                        ",total_pages = '" + totalPages + '\'' +
                        ",results = '" + results + '\'' +
                        ",total_results = '" + totalResults + '\'' +
                        "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.page);
        dest.writeInt(this.totalPages);
        dest.writeList(this.results);
        dest.writeInt(this.totalResults);
    }

    public MoviesModel() {
    }

    protected MoviesModel(Parcel in) {
        this.dates = in.readParcelable(Dates.class.getClassLoader());
        this.page = in.readInt();
        this.totalPages = in.readInt();
        this.results = new ArrayList<ResultsMovieItem>();
        in.readList(this.results, ResultsMovieItem.class.getClassLoader());
        this.totalResults = in.readInt();
    }

    public static final Parcelable.Creator<MoviesModel> CREATOR = new Parcelable.Creator<MoviesModel>() {
        @Override
        public MoviesModel createFromParcel(Parcel source) {
            return new MoviesModel(source);
        }

        @Override
        public MoviesModel[] newArray(int size) {
            return new MoviesModel[size];
        }
    };
}