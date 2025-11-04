package example.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashMap;

public class Catalogue {
    private HashMap<String, Movie> catalogue = new HashMap<>();
    private ArrayList<Movie> movies = new ArrayList<>();
    private ObservableList<Movie> movieObservableList = FXCollections.observableArrayList();

    public Catalogue() {}

    public void addMovie(Movie movie) {
        Movie newMovie = catalogue.get(movie.getTitle());
        if (newMovie == null) {
            catalogue.put(movie.getTitle(), movie);
            movies.add(movie);
        } else {
            throw new IllegalArgumentException("Filme já foi adicionado na lista");
        }
    }

    public Movie getMovie(String title) {
        return catalogue.get(title);
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public Movie removeMovie(String title) {
        Movie movie = catalogue.get(title);
        if (movie != null) {
            int removed = movie.getTitle().compareToIgnoreCase(title);
            if (removed == 0) {
                catalogue.remove(title);
                movies.remove(movie);
            } else {
                throw new IllegalArgumentException("Filme não encontrado");
            }
        }
        return movie;
    }
}
