
package se.tihs.group3.app.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.*;

import se.iths.group3.app.model.DramaMovie;
import se.iths.group3.app.storage.DramaMovieStorage;
import se.iths.group3.app.storage.MovieStorage;

import java.io.IOException;
import java.nio.file.Path;

public class DramaMovieStorageTest {

    @TempDir
    Path tempDir;

    private MovieStorage<DramaMovie> dramaMovieStorage;

    @BeforeEach
    void setUp() {
        dramaMovieStorage = new DramaMovieStorage(tempDir.resolve("test.json"));
    }

    @Test
    @DisplayName("save() returns correct id and title")
    void saveNewMovie() throws IOException {
        dramaMovieStorage.save(new DramaMovie("The Shawshank Redemption", 1));

        assertEquals(1, dramaMovieStorage.findById(1).getID());
        assertEquals("The Shawshank Redemption",
                dramaMovieStorage.findById(1).getTitle());
    }

    @Test
    @DisplayName("save() doesnt save duplicates")
    void saveNoDuplicate() throws IOException {
        dramaMovieStorage.save(new DramaMovie("The Shawshank Redemption", 1));
        dramaMovieStorage.save(new DramaMovie("The Green Mile", 1));

        assertEquals(1, dramaMovieStorage.findAll().size());
        assertEquals("The Shawshank Redemption",
                dramaMovieStorage.findAll().get(0).getTitle());
    }

    @Test
    @DisplayName("findAll() returns empty list when nothing saved")
    void findAllEmptyList() throws IOException {
        assertTrue(dramaMovieStorage.findAll().isEmpty());
    }

    @Test
    @DisplayName("findById() returns null if not correct id")
    void findByIdNull() throws IOException {
        dramaMovieStorage.save(new DramaMovie("The Shawshank Redemption", 1));

        assertNull(dramaMovieStorage.findById(2));
    }
}

