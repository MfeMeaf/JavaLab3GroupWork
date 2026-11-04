package se.iths.group3.app.storage;

import se.iths.group3.app.model.Movie;

import java.io.IOException;
import java.util.List;

public class HorrorMovieStorage implements MovieStorage<HorrorMovie> {
    private final path;
    @Override
    public void save(HorrorMovie movie) throws IOException {

    }

    @Override
    public HorrorMovie findById(int id) throws IOException {
        return null;
    }

    @Override
    public List<HorrorMovie> findAll() throws IOException {
        return List.of();
    }
}
