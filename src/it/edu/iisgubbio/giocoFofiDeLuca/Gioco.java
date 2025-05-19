package it.edu.iisgubbio.giocoFofiDeLuca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Gioco extends Application {

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
	Image angoloTDestra = new Image(getClass().getResourceAsStream("AngoloTDestro.png"));
	Image angoloTSinistra = new Image(getClass().getResourceAsStream("AngoloTSinistro.png"));
	int altezzaMappa = 32;
	int larghezzaMappa = 52;
	char mappaCaratteri[][]=new char[larghezzaMappa][altezzaMappa];

	@Override
	public void start(Stage primaryStage) throws Exception {

		GridPane g = new GridPane();
		Pane pacmanLayer = new Pane();
		Pacman pacman = new Pacman(new Image(getClass().getResourceAsStream("pacman.gif")), 32, 50, this);
		pacmanLayer.getChildren().add(pacman);
		StackPane strati = new StackPane(g, pacmanLayer);
		AnimationTimer timer = new AnimationTimer() {
		    @Override
		    public void handle(long now) {
		        pacman.aggiornaPosizione();
		    }
		};
		timer.start();
		mappa = new ImageView[larghezzaMappa][altezzaMappa];

		Scene scena = new Scene(strati,1650,1002);
		primaryStage.setTitle("Pacman");
		primaryStage.setScene(scena); 
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
			        	mappaCaratteri[x][y] = caratteri[x].charAt(0);
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
			                case "aTA":
			                    img = angoloTAlto;
			                    break;
			                case "aTS":
			                    img = angoloTSinistra;
			                    break;
			                case "aTD":
				                img = angoloTDestra;
				                break;
			                case "aSB":
			                    img = angoloSinistroBasso;
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
		
		scena.setOnKeyPressed(event -> {
		    switch (event.getCode()) {
		        case UP:
			        pacman.setDirezione("UP");
		        	break;
		        case DOWN:
		            pacman.setDirezione("DOWN");
		            break;
		        case LEFT:
		            pacman.setDirezione("LEFT");
		            break;
		        case RIGHT:
		            pacman.setDirezione("RIGHT");
		            break;
		    }
		});
	}
	public boolean calpestabile(int x, int y) {
	    if (x < 0 || x >= larghezzaMappa || y < 0 || y >= altezzaMappa) {
	        return false;
	    }
	    char a = mappaCaratteri[x][y];
	    return (a == 's' || a == 'p');
	}
	

	public static void main(String[] args) {
		launch(args);
	}
}