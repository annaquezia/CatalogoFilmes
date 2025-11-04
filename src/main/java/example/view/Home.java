package example.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Home {
    private final VBox root = new VBox(18);
    private final Button btnNew = new Button("Cadastrar novo filme");
    private final Button btnList = new Button("Ver filmes cadastrados");

    public Home() {
        Label title = new Label("🎬 Catálogo de Filmes");
        title.getStyleClass().add("label-titulo");
        title.setFont(Font.font(24));

        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(title, btnNew, btnList);
        root.setMinSize(800, 480);
        root.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        btnNew.getStyleClass().add("button-salvar");
        btnList.getStyleClass().add("button-salvar");
    }

    public VBox getRoot() { return root; }

    public void setOnNewMovie(Runnable r) { btnNew.setOnAction(e -> r.run()); }

    public void setOnOpenList(Runnable r) { btnList.setOnAction(e -> r.run()); }

}