package it.edu.iisgubbio.giocoFofiDeLuca;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PrimaMappa extends Application {

	ImageView[][] mappa;
	Image muro = new Image(getClass().getResourceAsStream("nero.jpg"));
	Image vuoto = new Image(getClass().getResourceAsStream("bianco.jpg"));
	Image puntino = new Image(getClass().getResourceAsStream("puntino.png"));
	int altezzaMappa = 20;
	int larghezzaMappa = 50;

	@Override
	public void start(Stage primaryStage) throws Exception {

		GridPane g = new GridPane();
		mappa = new ImageView[larghezzaMappa][altezzaMappa];

		Scene scena = new Scene(g);
		primaryStage.setTitle("Zombie Land");
		primaryStage.setScene(scena);
		primaryStage.show();

		/*
		 * leggo il file e lo metto dentro l'array
		 */

		try (
				InputStream is = getClass().getResourceAsStream("posizioneMappa");
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader lettore = new BufferedReader(isr);
				) {

			String rigaLetta;
			while ((rigaLetta = lettore.readLine()) != null) {
				String caratteri[] = rigaLetta.split(" ");
				for(int i=0;i<caratteri.length;i++) {
					System.out.print(caratteri +" ");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
