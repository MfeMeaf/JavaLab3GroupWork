package se.iths.group3.app;

import se.iths.group3.app.model.*;
import se.iths.group3.app.storage.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static void main() {

        MovieStorage<ComedyMovie> comedyMovies = new ComedyMovieStorage(
                Path.of(("data"), "comedy-movies.json"));

        MovieStorage<HorrorMovie> horrorMovies = new HorrorMovieStorage(Path.of(("data"), "horror-movies.json"));

        MovieStorage<DramaMovie> dramaMovies = new DramaMovieStorage(Path.of(("data"), "drama-movies.json"));

        MovieStorage<ActionMovie> actionMovies = new ActionMovieStorage(Path.of(("data"), "action-movies.json"));


        try {
            saveMovies(comedyMovies, "Comedymovie", new String[]
                    {"Austin Powers", "Ace Ventura", "Dumb and Dumber", "Step Brothers", "Elf"});

            saveMovies(horrorMovies, "Horrormovie", new String[]{"IT", "Scary Movie 4", "In the mouth of madness", "The Shining", "The Thing"});

            saveMovies(actionMovies, "Actionmovie", new String[]
                    {"John Wick" , "Skyfall", "Spider-Man", "Batman Begins", "Transformers"});
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

        while (true) {


            String choice = IO.readln("""
                    
                    1. Visa filmer i en viss kategori
                    2. Visa alla filmer
                    0. Avsluta
                    
                    """);

            switch (choice) {
                case "1" -> {

                    String categoryChoice = IO.readln("""
                            
                            1. Action
                            2. Comedy
                            3. Drama
                            4. Horror
                            
                            """);

                    try {
                        switch (categoryChoice) {
                            case "1" -> actionMovies.findAll()
                                    .forEach(movie -> IO.println("Id: " + movie.getID()
                                    + "\nTitel: " + movie.getTitle() + "\nKategori: " + movie.category()
                                    + "\n---------------------------------"));
                            case "2" -> comedyMovies.findAll()
                                    .forEach(movie -> IO.println("Id: " + movie.getID()
                                    + "\nTitel: " + movie.getTitle() + "\nKategori: " + movie.category()
                                    + "\n---------------------------------"));
                            case "3" -> dramaMovies.findAll()
                                    .forEach(movie -> IO.println("Id: " + movie.getID()
                                    + "\nTitel: " + movie.getTitle() + "\nKategori: " + movie.category()
                                    + "\n---------------------------------"));
                            case "4" -> horrorMovies.findAll()
                                    .forEach(movie -> IO.println("Id: " + movie.getID()
                                    + "\nTitel: " + movie.getTitle() + "\nKategori: " + movie.category()
                                    + "\n---------------------------------"));
                        }

                    } catch (IOException e) {
                        System.err.println("Filmerna kunde inte hämtas: " + e.getMessage());
                    }

                }
                case "2" -> {

                    List<Movie> allMovies = new ArrayList<>();

                    try {
                        allMovies.addAll(comedyMovies.findAll());
                        allMovies.addAll(horrorMovies.findAll());
                        allMovies.addAll(dramaMovies.findAll());
                        allMovies.addAll(actionMovies.findAll());

                        allMovies.forEach(movie -> IO.println("Id: " + movie.getID()
                                + "\nTitel: " + movie.getTitle() + "\nKategori: " + movie.category()
                        + "\n---------------------------------"));

                    } catch (IOException e) {
                        System.err.println("Listan kunde inte läsas: " + e.getMessage());
                    }

                }
                case "0" -> {
                    return;
                }
                default -> IO.readln("Fel! mata in korrekt val i menyn.");
            }

        }

    }

    static <T> void saveMovies(MovieStorage<T> storage,String movieGenre, String[] movieList) throws IOException {
        for (int i = 0; i < movieList.length; i++) {

            int id = i + 1;
            String movie = movieList[i];

            switch (movieGenre.toLowerCase()) {
                case "comedymovie" -> {
                    if (storage instanceof ComedyMovieStorage comedyMovieStorage) {
                        comedyMovieStorage.save(new ComedyMovie(movie, id));
                    }
                }
                case "horrormovie" -> {
                    if (storage instanceof HorrorMovieStorage horrorMovieStorage) {
                        horrorMovieStorage.save(new HorrorMovie(movie, id));
                    }
                }
                case "dramamovie" -> {
                    if (storage instanceof DramaMovieStorage dramaMovieStorage) {
                        dramaMovieStorage.save(new DramaMovie(movie, id));
                    }
                }
                case "actionmovie" -> {
                    if (storage instanceof ActionMovieStorage actionMovieStorage) {
                        actionMovieStorage.save(new ActionMovie(id, movie));
                    }
                }
                default -> System.err.println("Unknown genre");

            }
        }
    }

}
