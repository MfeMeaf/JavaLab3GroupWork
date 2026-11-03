package se.tihs.group3.app.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;
import se.iths.group3.app.model.ComedyMovie;
import se.iths.group3.app.storage.ComedyMovieStorage;
import se.iths.group3.app.storage.MovieStorage;

import java.io.IOException;
import java.nio.file.Path;

public class ComedyMovieStorageTest {

    @TempDir
    Path tempDir;

    private MovieStorage<ComedyMovie> comedyMovieStorage;

    @BeforeEach
    void setUp() {
        comedyMovieStorage = new ComedyMovieStorage(tempDir.resolve("test.json"));
    }

    @Test
    @DisplayName("save() returns correct id and title")
    void saveNewMovie() throws IOException {
        comedyMovieStorage.save(new ComedyMovie("Austin Powers", 1));
        assertEquals(1, comedyMovieStorage.findById(1).getID());
        assertEquals("Austin Powers", comedyMovieStorage.findById(1).getTitle());
    }

    @Test
    @DisplayName("save() doesnt save duplicates")
    void saveNoDuplicate() throws IOException {
        comedyMovieStorage.save(new ComedyMovie("Austin Powers", 1));
        comedyMovieStorage.save(new ComedyMovie("Ace Ventura", 1));
        assertEquals(1, comedyMovieStorage.findAll().size());
        assertEquals("Austin Powers", comedyMovieStorage.findAll().get(0).getTitle());
    }

    @Test
    @DisplayName("findAll() returns empty list when nothing saved")
    void findAllEmptyList() throws IOException{
        assertTrue(comedyMovieStorage.findAll().isEmpty());
    }

    @Test
    @DisplayName("findById() returns null if not correct id")
    void findByIdNull() throws IOException{
        comedyMovieStorage.save(new ComedyMovie("Austin Powers", 1));
        assertNull(comedyMovieStorage.findById(2));
    }

}
