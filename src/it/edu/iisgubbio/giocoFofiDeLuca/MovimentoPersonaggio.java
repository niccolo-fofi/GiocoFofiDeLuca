package it.edu.iisgubbio.giocoFofiDeLuca;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class MovimentoPersonaggio extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Crea un personaggio con posizione iniziale (200, 200)
    	Sopravissuto personaggio = new Sopravissuto(200, 200);

        // Crea un layout Pane per la scena
        Pane root = new Pane();
        root.getChildren().add(personaggio.getRettangolo());

        // Crea la scena
        Scene scene = new Scene(root, 400, 400);

        // Gestione degli eventi da tastiera
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP) {
                personaggio.muoviSu();
            } else if (event.getCode() == KeyCode.DOWN) {
                personaggio.muoviGiu();
            } else if (event.getCode() == KeyCode.LEFT) {
                personaggio.muoviSinistra();
            } else if (event.getCode() == KeyCode.RIGHT) {
                personaggio.muoviDestra();
            }
        }
        );

        // Impostazioni della finestra
        primaryStage.setTitle("Movimento Personaggio con JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
