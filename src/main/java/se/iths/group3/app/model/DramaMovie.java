package se.iths.group3.app.model;

public class DramaMovie extends Movie {
  public DramaMovie(){

  }
    public DramaMovie(String title, int id){
        super(title, id);
    }
    @Override
    public String category(){
        return "Drama";
}
}
