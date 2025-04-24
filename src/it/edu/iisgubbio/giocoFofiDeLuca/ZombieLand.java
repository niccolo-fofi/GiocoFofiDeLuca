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
		Canvas tela = new Canvas(400, 250);
		GraphicsContext gc = tela.getGraphicsContext2D();

		gc.setStroke(Color.RED);
		gc.strokeLine(10, 16, 10, 5);
		
		GridPane pannello = new GridPane();
		pannello.add(tela, 0, 0);
		
		Scene scene = new Scene(pannello);
	    primaryStage.setTitle("Zombie Land");
	    primaryStage.setScene(scene);
	    primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
