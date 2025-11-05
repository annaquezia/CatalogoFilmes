package example;

import example.controller.MovieFormController;
import example.controller.MovieTableController;
import example.model.Catalogue;
import example.model.MovieComparator;
import example.model.Search;
import example.view.Home;
import example.view.MovieFormView;
import example.view.MovieTableView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    private Catalogue catalogue = new Catalogue();
    private MovieComparator comparator = new MovieComparator();
    private Search search = new Search();

    private Stage primaryStage;

    private static final double WINDOW_WIDTH = 900;
    private static final double WINDOW_HEIGHT = 600;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("🎬 Catálogo de Filmes");

        Scene scene = new Scene(new Pane(), WINDOW_WIDTH, WINDOW_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        primaryStage.setScene(scene);

        showHomeView();

        primaryStage.show();
    }

    private void showHomeView() {
        Home homeView = new Home();

        homeView.setOnNewMovie(this::showMovieFormView);
        homeView.setOnOpenList(this::showMovieTableView);

        primaryStage.getScene().setRoot(homeView.getRoot());
        primaryStage.centerOnScreen();
    }

    private void showMovieFormView() {
        MovieFormView formView = new MovieFormView();
        formView.show();
        new MovieFormController(formView, catalogue, this::showHomeView);

        primaryStage.getScene().setRoot(formView.getRoot());
        primaryStage.centerOnScreen();
    }

    private void showMovieTableView() {
        MovieTableView tableView = new MovieTableView();

        new MovieTableController(
                tableView,
                catalogue,
                search,
                comparator,
                this::showHomeView
        );

        primaryStage.getScene().setRoot(tableView.getRoot());
        primaryStage.centerOnScreen();
    }

    public static void main(String[] args) {
        launch(args);
    }
}