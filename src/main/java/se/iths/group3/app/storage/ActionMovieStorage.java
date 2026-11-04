package se.iths.group3.app.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import se.iths.group3.app.model.ActionMovie;
import se.iths.group3.app.model.Movie;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ActionMovieStorage implements MovieStorage<ActionMovie> {
    private final Path filePath;
    private final ObjectMapper mapper;


    public ActionMovieStorage(Path filePath) {
        this.filePath = filePath;
        this.mapper = new ObjectMapper();


        try {
            if (filePath.getParent() != null && !Files.exists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }
            if (!Files.exists(filePath)) {
                Files.writeString(filePath, "[]");
            }
        } catch (IOException e) {
            System.err.println("Kunde inte initiera lagringsfilen: " + e.getMessage());
        }
    }

    @Override
    public List<ActionMovie> findAll() throws IOException {
        if (!Files.exists(filePath) || Files.size(filePath) == 0) {
            return new ArrayList<>();
        }
        try {

            return mapper.readValue(filePath.toFile(), new TypeReference<List<ActionMovie>>() {});
        } catch (IOException e) {

            return new ArrayList<>();
        }
    }

    @Override
    public void save(ActionMovie movie) throws IOException {
        List<ActionMovie> movies = findAll();


        for (ActionMovie m : movies) {
            if (m.getId() == movie.getId()) {
                System.out.println("En actionfilm med ID " + movie.getId() + " finns redan!");
                return;
            }
        }

        movies.add(movie);

        mapper.writerWithDefaultPrettyPrinter().writeValue(filePath.toFile(), movies);
    }

    @Override
    public ActionMovie findById(int id) throws IOException {
        return null;
    }
}
