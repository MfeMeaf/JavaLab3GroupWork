package se.iths.group3.app.storage;

import se.iths.group3.app.model.ComedyMovie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

public class ComedyMovieStorage implements MovieStorage<ComedyMovie> {
    private final Path path;
    private final ObjectMapper mapper = new ObjectMapper();

    public ComedyMovieStorage(Path path) {
        this.path = path;
    }

    @Override
    public void save(ComedyMovie movie) throws IOException {

        if (findById(movie.getID()) != null) {
            return;
        }

        List<ComedyMovie> comedyMovies = findAll();
        comedyMovies.add(movie);
        mapper.writeValue(path.toFile(), comedyMovies);

    }

    @Override
    public ComedyMovie findById(int id) throws IOException {
        return findAll().stream().filter(movie -> movie.getID() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<ComedyMovie> findAll() throws IOException {

        if (Files.notExists(path)) {
            return new ArrayList<>();
        }

        return mapper.readValue(path.toFile(),
                new TypeReference<List<ComedyMovie>>() {} );
    }

}
