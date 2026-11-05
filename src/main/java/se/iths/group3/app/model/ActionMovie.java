package se.iths.group3.app.model;

public class ActionMovie extends Movie {

    public ActionMovie() {
        super();
    }

    public ActionMovie(int id, String title){
        super(title, id);
    }

    @Override
    public String category() {
        return "Action";
    }



    public Object getId() {
        return id;
    }

}
