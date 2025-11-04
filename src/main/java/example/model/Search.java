package example.model;

import java.util.ArrayList;

public class Search {
    private Catalogue catalogue;
    private MovieComparator movieComparator;

    public Search() {}

    public void bubbleSort(Catalogue catalogue, MovieComparator movieComparator, ComparatorType type) {
        ArrayList<Movie> movies = catalogue.getMovies();
        int size = movies.size();

        for (int i = 0; i < size; i++) {
            for (int j = 1; j < (size - i); j++) {
                if (movieComparator.forType(type, movies.get(j - 1), movies.get(j)) > 0) {
                    Movie temp = movies.get(j - 1);
                    movies.set(j - 1, movies.get(j));
                    movies.set(j, temp);
                }
            }
        }
    }

    public void insertionSort(Catalogue catalogue, MovieComparator movieComparator, ComparatorType type) {
        ArrayList<Movie> movies = catalogue.getMovies();
        int size = movies.size();

        for (int i = 0; i < size; i++) {
            Movie currentMovie = movies.get(i);
            int j = i - 1;
            while (j >= 0 && movieComparator.forType(type, movies.get(j), currentMovie) > 0) {
                movies.set(j + 1, movies.get(j));
                j--;
            }
            movies.set(j + 1, currentMovie);
        }
    }

    public void quickSort(Catalogue catalogue, MovieComparator movieComparator, ComparatorType type, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(catalogue, movieComparator, type,  left, right);
            quickSort(catalogue, movieComparator, type, left, pivotIndex - 1);
            quickSort(catalogue, movieComparator, type, pivotIndex + 1, right);
        }
    }

    public int partition(Catalogue catalogue, MovieComparator movieComparator, ComparatorType type, int left, int right) {
        ArrayList<Movie> movies = catalogue.getMovies();
        Movie pivot = movies.get(right);
        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (movieComparator.forType(type, movies.get(j), pivot) <= 0) {
                i++;
                swap(movies, i, j);
            }
        }
        swap(movies, i + 1, right);
        return i + 1;
    }

    private void swap(ArrayList<Movie> movies, int i, int j) {
        Movie temp = movies.get(i);
        movies.set(i, movies.get(j));
        movies.set(j, temp);
    }
}
