package se.iths.group3.app.storage;

import com.fasterxml.jackson.databind.ObjectMapper;
import se.iths.group3.app.model.DramaMovie;
import java.nio.file.Path;
import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import java.nio.file.Files;
import java.util.ArrayList;

public class DramaMovieStorage implements MovieStorage<DramaMovie> {
    private final Path path;
    private final ObjectMapper mapper = new ObjectMapper();

    public DramaMovieStorage(Path path) {
        this.path = path;
    }

    @Override
    public void save(DramaMovie movie) throws IOException {

        if (findById(movie.getID()) != null) {
            return;
        }

        List<DramaMovie> dramaMovies = findAll();

        dramaMovies.add(movie);
        mapper.writeValue(path.toFile(), dramaMovies);



    }
    @Override
    public DramaMovie findById(int id) throws IOException {
return findAll().stream().filter(movie -> movie.getID() == id).findFirst()
        .orElse(null);
    }
    @Override
    public List<DramaMovie> findAll() throws IOException {
        if (Files.notExists(path)) {
            return new ArrayList<>();
        }

        return mapper.readValue(
                path.toFile(),
                new TypeReference<List<DramaMovie>>() {}
        );
    }
}