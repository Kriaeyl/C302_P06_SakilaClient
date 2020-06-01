package sg.edu.rp.dmsd.c302_p06_sakilaclient;

public class Film {
    private String title, rating, year;

    public Film(String title, String rating, String year) {
        this.title = title;
        this.rating = rating;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getRating() {
        return rating;
    }

    public String getYear() {
        return year;
    }
}
