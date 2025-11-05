package example.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;

public class Catalogue {
    private Movie[] movies;
    private String[] keys;
    private int size;            // quantidade de filmes
    private int capacity;        // tamanho atual da tabela
    private static final double FATOR_CARGA = 0.75;
    private final ArrayList<Movie> movieList = new ArrayList<>();
    private final ObservableList<Movie> movieObservableList = FXCollections.observableArrayList();

    public Catalogue() {
        this.capacity = 8;
        this.keys = new String[capacity];
        this.movies = new Movie[capacity];
        this.size = 0;
    }

    // hash compatível com equalsIgnoreCase
    private int hash(String title) {
        String t = title.toLowerCase();
        int soma = 0;
        for (int i = 0; i < t.length(); i++) {
            soma += t.charAt(i);
        }
        return Math.abs(soma) % capacity;
    }

    // ===== API =====

    public void addMovie(Movie movie) {
        if (movie == null || movie.getTitle() == null) {
            throw new IllegalArgumentException("Filme ou título inválido");
        }
        if ((double) size / capacity >= FATOR_CARGA) {
            resize(capacity * 2);
        }
        putInternal(movie);
        movieList.add(movie);
    }

    public Movie getMovie(String title) {
        if (title == null) return null;
        int index = hash(title);
        for (int i = 0; i < capacity; i++) {
            int pos = (index + i) % capacity;
            if (keys[pos] == null) return null;
            if (keys[pos].equalsIgnoreCase(title)) return movies[pos];
        }
        return null;
    }

    public Movie removeMovie(String title) {
        if (title == null) return null;
        int index = hash(title);
        for (int i = 0; i < capacity; i++) {
            int pos = (index + i) % capacity;
            if (keys[pos] == null) return null;
            if (keys[pos].equalsIgnoreCase(title)) {
                Movie removed = movies[pos];
                keys[pos] = null;
                movies[pos] = null;
                size--;
                // tira também da lista usada pelo Search
                movieList.remove(removed);
                // e da ObservableList, se estiver usando
                // movieObservableList.remove(removed);
                rehash();
                return removed;
            }
        }
        return null;
    }

    public ArrayList<Movie> getMovies() {
        return movieList; // mesma instância sempre, para ordenar in place
    }

    public ObservableList<Movie> getMoviesObservable() {
        movieObservableList.clear();
        movieObservableList.addAll(movieList);
        return movieObservableList;
    }

    private void putInternal(Movie movie) {
        String title = movie.getTitle();
        int index = hash(title);
        for (int i = 0; i < capacity; i++) {
            int pos = (index + i) % capacity;

            if (keys[pos] == null) {
                keys[pos] = title;
                movies[pos] = movie;
                size++;
                return;
            }
            if (keys[pos].equalsIgnoreCase(title)) { // duplicado
                throw new IllegalArgumentException("Filme já foi adicionado na lista");
            }
        }
        throw new IllegalStateException("Tabela cheia");
    }

    private void rehash() {
        String[] oldKeys = keys;
        Movie[] oldMovies = movies;

        keys = new String[capacity];
        movies = new Movie[capacity];
        int oldSize = size;
        size = 0;

        for (int i = 0; i < oldKeys.length; i++) {
            if (oldKeys[i] != null) {
                putInternal(oldMovies[i]);
            }
        }
        size = oldSize;
    }

    private void resize(int newCapacity) {
        String[] oldKeys = keys;
        Movie[] oldMovies = movies;

        capacity = newCapacity;
        keys = new String[capacity];
        movies = new Movie[capacity];
        int oldSize = size;
        size = 0;

        for (int i = 0; i < oldKeys.length; i++) {
            if (oldKeys[i] != null) {
                putInternal(oldMovies[i]);
            }
        }
        size = oldSize;
    }
}