package example.controller;

import example.model.Catalogue;
import example.model.ComparatorType;
import example.model.Movie;
import example.model.MovieComparator;
import example.model.Search;
import example.view.MovieTableView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MovieTableController {
    private final MovieTableView view;
    private final Catalogue catalogue;
    private final Search search;
    private final MovieComparator comparator;
    private final Runnable onBack;

    private final ObservableList<Movie> tableData = FXCollections.observableArrayList();

    private TableView<Movie> table() {
        try {
            Field f = view.getClass().getDeclaredField("tableView");
            f.setAccessible(true);
            return (TableView<Movie>) f.get(view);
        } catch (Exception e) {
            throw new IllegalStateException("Não foi possível acessar tableView na MovieTableView", e);
        }
    }

    private void injectCatalogueIntoView() {
        try {
            Field f = view.getClass().getDeclaredField("catalogue");
            f.setAccessible(true);
            f.set(view, catalogue);
        } catch (Exception e) {
            throw new IllegalStateException("Não foi possível injetar Catalogue na MovieTableView", e);
        }
    }

    public MovieTableController(MovieTableView view,
                                Catalogue catalogue,
                                Search search,
                                MovieComparator comparator,
                                Runnable onBack) {
        this.view = view;
        this.catalogue = catalogue;
        this.search = search;
        this.comparator = comparator;
        this.onBack = onBack;

        this.view.show();

        injectCatalogueIntoView();
        table().setItems(tableData);

        wire();
        refreshFromCatalogue();
    }

    private void wire() {

        view.getBackButton().setOnAction(e -> onBack.run());

        view.getQuickButton().setOnAction(e -> resort(view.getComboBox().getValue(), SortAlgo.QUICK));
        view.getBubbleButton().setOnAction(e -> resort(view.getComboBox().getValue(), SortAlgo.BUBBLE));
        view.getInsertButton().setOnAction(e -> resort(view.getComboBox().getValue(), SortAlgo.INSERT));
    }

    private enum SortAlgo { QUICK, BUBBLE, INSERT }

    private void resort(ComparatorType type, SortAlgo algo) {
        List<Movie> base = new ArrayList<>(catalogue.getMovies());

        switch (algo) {
            case QUICK -> {
                if (base.size() > 1) {
                    TempCatalogue tmp = new TempCatalogue(base);
                    search.quickSort(tmp, comparator, type, 0, base.size() - 1);
                    base = tmp.movies;
                }
            }
            case BUBBLE -> {
                TempCatalogue tmp = new TempCatalogue(base);
                search.bubbleSort(tmp, comparator, type);
                base = tmp.movies;
            }
            case INSERT -> {
                TempCatalogue tmp = new TempCatalogue(base);
                search.insertionSort(tmp, comparator, type);
                base = tmp.movies;
            }
        }
        tableData.setAll(base);
    }

    private void refreshFromCatalogue() {
        tableData.setAll(catalogue.getMovies());

        Platform.runLater(() -> table().refresh());
    }


    private static class TempCatalogue extends Catalogue {
        private final ArrayList<Movie> movies;

        TempCatalogue(List<Movie> source) {
            this.movies = new ArrayList<>(source);
        }

        @Override
        public ArrayList<Movie> getMovies() {
            return movies;
        }
    }
}