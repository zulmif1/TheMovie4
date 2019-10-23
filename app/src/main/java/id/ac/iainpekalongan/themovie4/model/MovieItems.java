package id.ac.iainpekalongan.themovie4.model;

import org.json.JSONObject;

public class MovieItems {
    private String movTitle, movOverview, movDateRelease, movImage;


    public MovieItems(JSONObject obj) {

        try {
            String title = obj.getString("title");
            String overview = obj.getString("overview");
            String dateRelease = obj.getString("release_date");
            String image = obj.getString("poster_path");


            this.movTitle = title;
            this.movOverview = overview;
            this.movDateRelease = dateRelease;
            this.movImage = image;

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public MovieItems() {

    }

    public String getMovTitle() {
        return movTitle;
    }

    public void setMovTitle(String movTitle) {
        this.movTitle = movTitle;
    }

    public String getMovOverview() {
        return movOverview;
    }

    public void setMovOverview(String movOverview) {
        this.movOverview = movOverview;
    }

    public String getMovDateRelease() {
        return movDateRelease;
    }

    public void setMovDateRelease(String movDateRelease) {
        this.movDateRelease = movDateRelease;
    }

    public String getMovImage() {
        return movImage;
    }

    public void setMovImage(String movImage) {
        this.movImage = movImage;
    }
}
