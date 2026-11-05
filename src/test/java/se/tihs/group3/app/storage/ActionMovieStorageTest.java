package se.tihs.group3.app.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;
import se.iths.group3.app.model.ActionMovie;
import se.iths.group3.app.storage.ActionMovieStorage;
import se.iths.group3.app.storage.MovieStorage;

import java.io.IOException;
import java.nio.file.Path;

public class ActionMovieStorageTest {

    @TempDir
    Path tempDir;

    private MovieStorage<ActionMovie> actionMovieStorage;

    @BeforeEach
    void setUp() {
        actionMovieStorage = new ActionMovieStorage(tempDir.resolve("test.json"));
    }

    @Test
    @DisplayName("FindbyID returns correct title")
    void saveNewMovie() throws IOException {
        actionMovieStorage.save(new ActionMovie(1, "John Wick"));
        assertEquals(1, actionMovieStorage.findById(1).getID());
        assertEquals("Austin Powers", actionMovieStorage.findById(1).getTitle());
    }

    @Test
    @DisplayName("Don't save duplicates")
    void saveNoDuplicate() throws IOException {
        actionMovieStorage.save(new ActionMovie(1, "Skyfall"));
        actionMovieStorage.save(new ActionMovie(1, "John Wick"));
        assertEquals(1, actionMovieStorage.findAll().size());
        assertEquals("Austin Powers", actionMovieStorage.findAll().get(0).getTitle());
    }


    @Test
    @DisplayName("Nothing is saved, use findall()")
    void findAllEmptyList() throws IOException{
        assertTrue(actionMovieStorage.findAll().isEmpty());
    }

    @Test
    @DisplayName("Check if wrong ID is given")
    void findByIdNull() throws IOException{
        actionMovieStorage.save(new ActionMovie(1, "John Wick"));
        assertNull(actionMovieStorage.findById(2));
    }

}
