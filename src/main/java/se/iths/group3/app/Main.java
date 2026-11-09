package se.iths.group3.app;

import se.iths.group3.app.model.*;
import se.iths.group3.app.storage.*;

import java.io.IOException;
import java.nio.file.Path;

public class Main {
    static void main() {

        MovieStorage<ComedyMovie> comedyMovies = new ComedyMovieStorage(
                Path.of(("data"), "comedy-movies.json"));

        MovieStorage<HorrorMovie> horrorMovies = new HorrorMovieStorage(Path.of(("data"), "horror-movies.json"));

        try {
            saveMovies(comedyMovies, "Comedymovie", new String[]
                    {"Austin Powers", "Ace Ventura", "Dumb and Dumber", "Step Brothers", "Elf"});

            saveMovies(horrorMovies, "Horrormovie", new String[]{"IT", "Scary Movie 4", "In the mouth of madness", "The Shining", "The Thing"});
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
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
