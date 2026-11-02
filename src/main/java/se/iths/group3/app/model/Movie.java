package se.iths.group3.app.model;

public abstract class Movie {
    protected int id;
    protected String title;

    public Movie(String title, int id) {
        this.title = title;
        this.id = id;
    }

    public Movie() {
        this.id = 0;
        this.title = "Sample";
    }

    public int getID() {
        return this.id;
    }

    public void setID(int i) {
        this.id = i;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String s) {
        this.title = s;
    }

    public abstract String category();
}
