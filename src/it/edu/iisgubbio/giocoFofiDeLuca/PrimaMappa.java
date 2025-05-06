package it.edu.iisgubbio.giocoFofiDeLuca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class PrimaMappa extends Application {

	ImageView[][] mappa;
	Image muroVerticale = new Image(getClass().getResourceAsStream("verticale.png"));
	Image muroOrizzontale = new Image(getClass().getResourceAsStream("orizzontale.png"));
	Image puntino = new Image(getClass().getResourceAsStream("puntino.png"));
	Image angoloDestro = new Image(getClass().getResourceAsStream("AngoloDestro.png"));
	Image angoloSinistro= new Image(getClass().getResourceAsStream("AngoloSinistro.png"));
	Image angoloT = new Image(getClass().getResourceAsStream("AngoloT.png"));

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
				InputStream is = getClass().getResourceAsStream("posizioneMappa.txt");
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader lettore = new BufferedReader(isr);
				) {

			int y=0;
			String rigaLetta;
			while ((rigaLetta = lettore.readLine()) != null) {

				String caratteri[] = rigaLetta.split(",");

				for (int x = 0; x < caratteri.length; x++) {
					Image img=null;
					if (caratteri[x].equals("v")) {
					} else {
						if (caratteri[x].equals("mV")) {
							img = muroVerticale;
						}else {
							if (caratteri[x].equals("mO")) {
								img = muroOrizzontale;
							}else {
								if (caratteri[x].equals("p")) {
									img = puntino;
								}else {
									if (caratteri[x].equals("aD")) {
										img = angoloDestro;
									}else {
										if(caratteri[x].equals("aT")) {
											img = angoloT;
										}
									}
								}
							}
						}
					}

					ImageView immagine = new ImageView(img);
					immagine.setFitWidth(32);
					immagine.setFitHeight(32);
					immagine.setPreserveRatio(false);

					mappa[x][y] = immagine;
					g.add(immagine, x, y);
				}
				y++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
