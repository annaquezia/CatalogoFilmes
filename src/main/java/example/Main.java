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
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    // Componentes de "estado" da aplicação
    private Catalogue catalogue = new Catalogue();
    private MovieComparator comparator = new MovieComparator();
    private Search search = new Search();

    // Stage principal (janela)
    private Stage primaryStage;

    private static final double WINDOW_WIDTH = 900;
    private static final double WINDOW_HEIGHT = 600;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("🎬 Catálogo de Filmes");

        // Aplica um CSS básico (opcional)
        Scene scene = new Scene(new Pane(), WINDOW_WIDTH, WINDOW_HEIGHT); // Scene inicial vazia
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        primaryStage.setScene(scene);

        // Inicia a aplicação na tela inicial (Home)
        showHomeView();

        primaryStage.show();
    }

    /**
     * Exibe a tela inicial (Home).
     */
    private void showHomeView() {
        Home homeView = new Home();

        // Configura as ações dos botões
        homeView.setOnNewMovie(this::showMovieFormView);
        homeView.setOnOpenList(this::showMovieTableView);

        // Muda o conteúdo da janela
        primaryStage.getScene().setRoot(homeView.getRoot());
        primaryStage.centerOnScreen();
    }

    /**
     * Exibe a tela de cadastro de novo filme (MovieFormView).
     */
    private void showMovieFormView() {
        MovieFormView formView = new MovieFormView();
        formView.show();
        new MovieFormController(formView, catalogue, this::showHomeView);

        // Mantenha o tamanho FIXO
        primaryStage.getScene().setRoot(formView.getRoot());
        // >>> REMOVA: primaryStage.sizeToScene();
        primaryStage.centerOnScreen();
    }

    /**
     * Exibe a tela de listagem de filmes (MovieTableView).
     */
    private void showMovieTableView() {
        MovieTableView tableView = new MovieTableView();

        // 1. Instancia e injeta as dependências no Controller
        new MovieTableController(
                tableView,
                catalogue,
                search,
                comparator,
                this::showHomeView // Função de callback para voltar
        );

        // O controller já chamou tableView.show() internamente,
        // então a view está pronta para ser exibida.
        primaryStage.getScene().setRoot(tableView.getRoot());
        primaryStage.centerOnScreen();
    }
    /**
     * Método main para lançar a aplicação JavaFX.
     */
    public static void main(String[] args) {
        launch(args);
    }
}