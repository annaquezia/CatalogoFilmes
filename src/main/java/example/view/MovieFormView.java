package example.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MovieFormView {
    private VBox root = new VBox(12);

    private TextField titleField = new TextField();
    private TextField genreField = new TextField();
    private TextField yearField  = new TextField();
    private TextField timeField  = new TextField();
    private TextArea descriptionField  = new TextArea();

    private Button submitButton = new Button("Salvar");
    private Button cancelButton  = new Button("Voltar");

    public void show(){
        root.setPadding(new Insets(18));

        Label pageTitle = new Label("Adicionar novo filme");
        pageTitle.getStyleClass().add("label-titulo");

        Label titleLabel = new Label("Título:");
        titleField.setPromptText("Titulo do filme");
        titleLabel.getStyleClass().add("label-campo");

        Label genreLabel = new Label("Gênero:");
        genreField.setPromptText("Gênero do filme");
        genreLabel.getStyleClass().add("label-campo");


        Label yearLabel = new Label("Ano:");
        yearField.setPromptText("Ano do filme");
        yearLabel.getStyleClass().add("label-campo");

        Label timeLabel = new Label("Duração:");
        timeField.setPromptText("Duração do filme em minutos");
        timeLabel.getStyleClass().add("label-campo");


        Label descriptionLabel = new Label("Descrição: ");
        descriptionField.setPrefColumnCount(5);
        descriptionLabel.getStyleClass().add("label-campo");


        HBox actions = new HBox(10, submitButton, cancelButton);
        actions.setSpacing(10);

        submitButton.getStyleClass().add("button-salvar");
        cancelButton.getStyleClass().add("button-voltar");

        root.getChildren().addAll(pageTitle, titleLabel, titleField, genreLabel, genreField, yearLabel, yearField, timeLabel, timeField, descriptionLabel, descriptionField, actions);
    }

    public void clearForm() {
        titleField.clear();
        genreField.clear();
        timeField.clear();
        yearField.clear();
        descriptionField.clear();

        titleField.getStyleClass().remove("error-field");
        genreField.getStyleClass().remove("error-field");
        timeField.getStyleClass().remove("error-field");
        yearField.getStyleClass().remove("error-field");
        descriptionField.getStyleClass().remove("error-field");
    }

    public VBox getRoot() {
        return root;
    }

    public Button getSubmitButton() {
        return submitButton;
    }

    public Button getCancelButton() {
        return cancelButton;
    }

    public TextField getTitleField() {
        return titleField;
    }

    public TextField getGenreField() {
        return genreField;
    }

    public TextField getYearField() {
        return yearField;
    }

    public TextField getTimeField() {
        return timeField;
    }

    public TextArea getDescriptionField() {
        return descriptionField;
    }

    public String getTextTitle() {
        return titleField.getText();
    }

    public String getTextGenre() {
        return genreField.getText();
    }

    public String getTextYear() {
        return yearField.getText();
    }

    public String getTextTime() {
        return timeField.getText();
    }

    public String getTextDescription() {
        return descriptionField.getText();
    }
}
