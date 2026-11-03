package se.iths.group3.app.model;

public class ComedyMovie extends Movie {

    public ComedyMovie() {
    }

    public ComedyMovie(String title, int id) {
        super(title, id);
    }

    @Override
    public String category() {
        return "Komedi";
    }

}
