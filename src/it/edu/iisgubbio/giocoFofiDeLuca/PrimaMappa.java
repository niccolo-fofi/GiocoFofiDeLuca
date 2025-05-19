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
	Image muroOrizzontale = new Image(getClass().getResourceAsStream("orizontale.png"));
	Image puntino = new Image(getClass().getResourceAsStream("puntino.gif"));
	Image spazio = new Image(getClass().getResourceAsStream("nero.png"));
	Image angoloDestroAlto = new Image(getClass().getResourceAsStream("AngoloDestroAlto.png"));
	Image angoloSinistroAlto= new Image(getClass().getResourceAsStream("AngoloSinistroAlto.png"));
	Image angoloDestroBasso = new Image(getClass().getResourceAsStream("AngoloDestroBasso.png"));
	Image angoloSinistroBasso= new Image(getClass().getResourceAsStream("AngoloSinistroBasso.png"));
	Image angoloT = new Image(getClass().getResourceAsStream("AngoloT.png"));
	Image angoloTAlto = new Image(getClass().getResourceAsStream("AngoloTAlto.png"));
	int altezzaMappa = 27;
	int larghezzaMappa = 23;

	@Override
	public void start(Stage primaryStage) throws Exception {

		GridPane g = new GridPane();
		mappa = new ImageView[larghezzaMappa][altezzaMappa];

		Scene scena = new Scene(g);
		primaryStage.setTitle("Zombie-Land");
		primaryStage.setScene(scena);
		primaryStage.setWidth(750);  
        primaryStage.setHeight(900);  
		primaryStage.show();

		/*
		 * leggo dal file i caratteri e ad ogni
		 * carattere associo un immagine che sarà
		 * aggiunta alla GridPane
		 */
		try (
			    InputStream is = getClass().getResourceAsStream("posizionePrimaMappa.txt");
			    InputStreamReader isr = new InputStreamReader(is);
			    BufferedReader lettore = new BufferedReader(isr);
			) {
			    int y = 0;
			    String rigaLetta;
			    
			    while ((rigaLetta = lettore.readLine()) != null) {
			        String[] caratteri = rigaLetta.split(",");

			        for (int x = 0; x < caratteri.length; x++) {
			            Image img = null;
			            
			            switch (caratteri[x]) {
			                case "s":
			                    img = spazio;
			                    break;
			                case "v":
			                    img = muroVerticale;
			                    break;
			                case "o":
			                    img = muroOrizzontale;
			                    break;
			                case "p":
			                    img = puntino;
			                    break;
			                case "aDA":
			                    img = angoloDestroAlto;
			                    break;
			                case "aSA":
			                    img = angoloSinistroAlto;
			                    break;
			                case "aDB":
			                    img = angoloDestroBasso;
			                    break;
			                case "aT":
			                    img = angoloT;
			                    break;
			                case "aSB":
			                    img = angoloSinistroBasso;
			                    break;
			                case "aTA":
			                	img = angoloTAlto;
			                	break;
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

	public boolean puoAndarci() {
		try (
				InputStream is = getClass().getResourceAsStream("posizionePrimaMappa.txt");
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader lettore = new BufferedReader(isr);
				) {
			String rigaLetta; 
			while ((rigaLetta = lettore.readLine()) != null) {
				String[] caratteri = rigaLetta.split(",");
			}
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
