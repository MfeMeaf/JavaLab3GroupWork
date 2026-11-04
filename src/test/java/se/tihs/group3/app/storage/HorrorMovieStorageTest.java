package se.tihs.group3.app.storage;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import se.iths.group3.app.model.HorrorMovie;
import se.iths.group3.app.storage.HorrorMovieStorage;

import java.io.IOException;
import java.nio.file.Path;

public class HorrorMovieStorageTest {



    @TempDir
    Path tempDir;

    private HorrorMovieStorage horrorMovieStorage;

    @BeforeEach
    void setUp(){
        horrorMovieStorage = new HorrorMovieStorage(tempDir.resolve("temp.json"));
    }

    @Test
    @DisplayName("Save and read test")
    void saveAndReadbyID() throws IOException{
        HorrorMovie scaryMovie = new HorrorMovie("Scary Movie 4",123);
        horrorMovieStorage.save(scaryMovie);
        HorrorMovie result = horrorMovieStorage.findById(123);
        Assertions.assertEquals(scaryMovie.getTitle(),result.getTitle());
    }

    @Test
    @DisplayName("Check that code doesn't save doubles")
    void checkForDoubles() throws IOException{
        horrorMovieStorage.save( new HorrorMovie("Scary Movie 15",123));
        horrorMovieStorage.save( new HorrorMovie("Scary Movie 20", 123));

        int result = horrorMovieStorage.findAll().size();

        Assertions.assertEquals(1, result);
    }

    @Test
    @DisplayName("Check that there are no movies when calling findall without saving")
    void checkEmptyMovieList() throws IOException{

        int result = horrorMovieStorage.findAll().size();

        Assertions.assertEquals(0, result);
    }

    @Test
    @DisplayName("Check that findById returns null when wrong ID")
    void checkForNull() throws IOException{
        horrorMovieStorage.save( new HorrorMovie("Scary Movie 15",123));
        HorrorMovie result = horrorMovieStorage.findById(321);
        Assertions.assertEquals(null, result);
    }
}


