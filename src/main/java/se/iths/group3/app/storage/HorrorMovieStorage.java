package se.iths.group3.app.storage;

import se.iths.group3.app.model.HorrorMovie;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

public class HorrorMovieStorage implements MovieStorage<HorrorMovie> {
    private final Path path;
    private final ObjectMapper mapper = new ObjectMapper();

    public HorrorMovieStorage(Path path) {
        this.path = path;
    }

    @Override
    public void save(HorrorMovie movie) throws IOException {
        if(findById(movie.getID()) != null){return;}
        List<HorrorMovie> movies = findAll();
        movies.add(movie);
        mapper.writerWithDefaultPrettyPrinter()
                .writeValue(path.toFile(),movies);
    }

    @Override
    public HorrorMovie findById(int id) throws IOException {
        return findAll().stream().filter(horrorMovie -> horrorMovie.getID() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<HorrorMovie> findAll() throws IOException {
        if(Files.notExists(path)){
            return new ArrayList<>();
        }
        return mapper.readValue(
                path.toFile(), new TypeReference<List<HorrorMovie>>() {}
        );
    }
}
