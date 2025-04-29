package it.edu.iisgubbio.giocoFofiDeLuca;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ZombieLand extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane principale = new GridPane(50,50);
		
		Scene scene = new Scene(principale);
	    primaryStage.setTitle("Zombie Land");
	    primaryStage.setScene(scene);
	    primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
