package id.ac.iainpekalongan.themovie4.model;

import org.json.JSONObject;

public class TVItems {
    private String tvTitle, tvOverview, tvDateRelease, tvImage;


    public TVItems(JSONObject obj) {

        try {
            String title = obj.getString("original_name");
            String overview = obj.getString("overview");
            String dateRelease = obj.getString("release_date");
            String image = obj.getString("poster_path");


            this.tvTitle = title;
            this.tvOverview = overview;
            this.tvDateRelease = dateRelease;
            this.tvImage = image;
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public TVItems() {

    }

    public String getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(String tvTitle) {
        this.tvTitle = tvTitle;
    }

    public String getTvOverview() {
        return tvOverview;
    }

    public void setTvOverview(String tvOverview) {
        this.tvOverview = tvOverview;
    }

    public String getTvDateRelease() {
        return tvDateRelease;
    }

    public void setTvDateRelease(String tvDateRelease) {
        this.tvDateRelease = tvDateRelease;
    }

    public String getTvImage() {
        return tvImage;
    }

    public void setTvImage(String tvImage) {
        this.tvImage = tvImage;
    }
}
