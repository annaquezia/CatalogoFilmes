package example.controller;

import example.model.Catalogue;
import example.model.Movie;
import example.view.MovieFormView;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputControl;

public class MovieFormController {
    private final MovieFormView view;
    private final Catalogue catalogue;
    private final Runnable onDone;

    public MovieFormController(MovieFormView view, Catalogue catalogue, Runnable onDone) {
        this.view = view;
        this.catalogue = catalogue;
        this.onDone = onDone;
        wire();
    }

    private void wire() {
        view.getSubmitButton().setOnAction(event -> {
            clearErrors();
            try {
                String title = safeTrim(view.getTextTitle());
                String genre = safeTrim(view.getTextGenre());
                String yearS = safeTrim(view.getTextYear());
                String timeS = safeTrim(view.getTextTime());
                String description = safeTrim(view.getTextDescription());

                if (title.isEmpty()) {
                    markError(view.getTitleField());
                    throw new IllegalArgumentException("Título é obrigatório");
                }
                if (genre.isEmpty()) {
                    markError(view.getGenreField());
                    throw new IllegalArgumentException("Gênero é obrigatório");
                }
                if (yearS.isEmpty()) {
                    markError(view.getYearField());
                    throw new IllegalArgumentException("Ano é obrigatório");
                }

                int year;
                try {
                    year = Integer.parseInt(yearS);
                } catch (NumberFormatException e) {
                    markError(view.getYearField());
                    throw new IllegalArgumentException("Ano deve ser numérico! ex: 1999");
                }

                if (year < 1888 || year > 2100) {
                    markError(view.getYearField());
                    throw new IllegalArgumentException("Ano fora do intervalo válido");
                }

                int time;
                try {
                    time = Integer.parseInt(timeS);
                } catch (NumberFormatException e) {
                    markError(view.getTimeField());
                    throw new IllegalArgumentException("A duração deve ser numérica! ex: 120");
                }
                if (time <= 0) {
                    markError(view.getTimeField());
                    throw new IllegalArgumentException("A duração deve ser um valor positivo");
                }

                if (description.isEmpty()) {
                    markError(view.getDescriptionField());
                    throw new IllegalArgumentException("A descrição é obrigatória");
                }

                Movie movie = new Movie(title, year, time, genre, description);
                catalogue.addMovie(movie);
                System.out.println("Filme cadastrado. Total de filmes no Catálogo: " + catalogue.getMovies().size());

                view.clearForm();
                alert("Sucesso", "Filme cadastrado com sucesso!", Alert.AlertType.INFORMATION);

            } catch (IllegalArgumentException ex) {
                alert("Erro de validação", ex.getMessage(), Alert.AlertType.WARNING);
            } catch (Exception ex) {
                alert("Erro", "Não foi possível salvar o filme: " + ex.getMessage(), Alert.AlertType.ERROR);
            }
        });

        view.getCancelButton().setOnAction(e -> onDone.run());
    }

    private void markError(TextInputControl c) {
        if (c != null && !c.getStyleClass().contains("error-field")) {
            c.getStyleClass().add("error-field");
        }
    }

    private void clearErrors() {
        view.getTitleField().getStyleClass().remove("error-field");
        view.getGenreField().getStyleClass().remove("error-field");
        view.getYearField().getStyleClass().remove("error-field");
        view.getTimeField().getStyleClass().remove("error-field");
        view.getDescriptionField().getStyleClass().remove("error-field");
    }

    private String safeTrim(String s) {
        return s == null ? "" : s.trim();
    }

    private void alert(String title, String msg, Alert.AlertType type) {
        Alert a = new Alert(type);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }
}
