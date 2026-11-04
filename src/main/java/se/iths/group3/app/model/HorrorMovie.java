package se.iths.group3.app.model;

public class HorrorMovie extends Movie{
    public HorrorMovie(){}

    public HorrorMovie(String title, int id){
        super(title,id);
    }

    @Override
    public String category() {
        return "Horror";
    }
}
