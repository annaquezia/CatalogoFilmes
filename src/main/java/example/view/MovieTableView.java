package example.view;

import example.model.Catalogue;
import example.model.ComparatorType;
import example.model.Movie;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class MovieTableView {
    private VBox root = new VBox(8);

    private TableView<Movie> tableView = new TableView<>();
    private TableColumn<Movie, String> titleColumn;
    private TableColumn<Movie, String> genreColumn;
    private TableColumn<Movie, Integer> yearColumn;
    private TableColumn<Movie, Integer> timeColumn;
    private TableColumn<Movie, String> descriptionColumn;
    private TableColumn<Movie, Void> deleteColumn;

    private Button backButton = new Button("Voltar");
    private Button bubbleButton = new Button("BubbleSort");
    private Button quickButton  = new Button("QuickSort");
    private Button insertButton =  new Button("InsertSort");

    private ComboBox<ComparatorType> comboBox = new ComboBox<>();

    private Catalogue catalogue;

    public void show() {
        Label pageLabel = new Label("Filmes cadastrados");
        pageLabel.getStyleClass().add("label-titulo");

        titleColumn = new TableColumn<>("Título");
        titleColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getTitle()));

        genreColumn = new TableColumn<>("Gênero");
        genreColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getGenre()));

        yearColumn = new TableColumn<>("Ano");
        yearColumn.setCellValueFactory(cell -> new javafx.beans.property.ReadOnlyObjectWrapper<>(cell.getValue().getYear()));

        timeColumn = new TableColumn<>("Duração (min)");
        timeColumn.setCellValueFactory(cell -> new javafx.beans.property.ReadOnlyObjectWrapper<>(cell.getValue().getTime()));

        descriptionColumn = new TableColumn<>("Descrição");
        descriptionColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getDescription()));

        deleteColumn = new TableColumn<>("Deletar");
        deleteColumn.setStyle("-fx-alignment: CENTER;");
        deleteColumn.setCellFactory(col -> new TableCell<>() {
            private final Button deleteButton = new Button("🗑️");

            {
                deleteButton.setStyle("-fx-background-color: #ffaba8;");
                deleteButton.setOnAction(event -> {
                    Movie selectedMovie = getTableView().getItems().get(getIndex());
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                            "Deseja realmente deletar o filme \"" + selectedMovie.getTitle() + "\"?",
                            ButtonType.YES, ButtonType.NO);
                    alert.setHeaderText("Deletar filme");
                    alert.showAndWait().ifPresent(response -> {
                        if (response == ButtonType.YES) {
                            catalogue.removeMovie(selectedMovie.getTitle());
                            tableView.getItems().remove(selectedMovie);
                        }
                    });
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        });


        root.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        VBox.setVgrow(tableView, Priority.ALWAYS);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        backButton.getStyleClass().add("button-voltar");
        bubbleButton.getStyleClass().add("button-sort");
        quickButton.getStyleClass().add("button-sort");
        insertButton.getStyleClass().add("button-sort");

        tableView.getColumns().addAll(titleColumn, genreColumn, yearColumn, timeColumn, descriptionColumn, deleteColumn);

        comboBox.getItems().addAll(ComparatorType.TITLE, ComparatorType.YEAR, ComparatorType.TIME);
        comboBox.setValue(ComparatorType.TITLE);

        Label organize = new Label("Ordenar por: ");
        organize.getStyleClass().add("label-campo");

        HBox hBox = new HBox(8);
        hBox.getChildren().addAll(organize, comboBox, new Separator(), bubbleButton, quickButton, insertButton, backButton);

        root.getChildren().addAll(pageLabel, hBox, tableView);
    }

    public Button getBackButton() {
        return backButton;
    }

    public Button getBubbleButton() {
        return bubbleButton;
    }

    public Button getInsertButton() {
        return insertButton;
    }

    public Button getQuickButton() {
        return quickButton;
    }

    public ComboBox<ComparatorType> getComboBox() {
        return comboBox;
    }

    public VBox getRoot() {
        return root;
    }

}