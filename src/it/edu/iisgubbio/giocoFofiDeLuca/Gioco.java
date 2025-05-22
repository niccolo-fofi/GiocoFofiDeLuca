package it.edu.iisgubbio.giocoFofiDeLuca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

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
    Image lineabluorizontale = new Image(getClass().getResourceAsStream("lineaAzzurra.png"));
    Image lineabluverticale = new Image(getClass().getResourceAsStream("lineaAzzurra.png"));
    Image pallinoGrande = new Image(getClass().getResourceAsStream("lineaAzzurra.png"));
    Image fantasmaImpaurito = new Image(getClass().getResourceAsStream("fantasmaImpaurito2.gif"));

    Pacman pacman;
    double pacmanX;
    double pacmanY;
    int altezzaMappa = 32;
    int larghezzaMappa = 52;
    int vite=3;
    char mappaCaratteri[][]=new char[larghezzaMappa][altezzaMappa];
    int punteggio=0;
    Label lPunteggio = new Label("punteggio: " +punteggio);
    Label lVite = new Label("vite:" + vite);
    GridPane g;
    Pane stratoPersonaggi;
    Fantasma fantasmaRosso;
    Fantasma fantasmaGiallo;
    Fantasma fantasmaRosa;
    Fantasma fantasmaAzzurro;
    StackPane strati;
    Scene giocoScena; 
    
    Image immagineVittoria = new Image(getClass().getResourceAsStream("haiVinto.png"));
    Scene scenaVittoria; 
    AnimationTimer timerGioco; 

    Image immagineGameOver = new Image(getClass().getResourceAsStream("haiPerso.jpeg"));
    Scene scenaGameOver;

    private MediaPlayer canzoneIntro;

    private boolean powerUpAttivo = false;
    private AnimationTimer timerPowerUp;
    private long powerUpStartTime;
    private final long durataPowerUp =  10 * 1_000_000_000L ; 

    @Override
    public void start(Stage primaryStage) throws Exception {

    	lPunteggio.setStyle(
    			"-fx-font-weight: bold;" +
    		    "-fx-text-fill: WHITE;"
    		    );
    	lVite.setStyle("-fx-font-weight: bold;" +
    		    "-fx-text-fill: WHITE;");
    	
    	StackPane.setAlignment(lPunteggio, Pos.TOP_LEFT);
    	StackPane.setAlignment(lVite, Pos.TOP_RIGHT);
    	
    	lPunteggio.setTranslateX(10); 
    	lPunteggio.setTranslateY(10); 

    	lVite.setTranslateX(-10); // 
    	lVite.setTranslateY(10);

    	String canzone = getClass().getResource("ZISO - Street Wisdom.mp3").toExternalForm();
        Media media = new Media(canzone);
        canzoneIntro = new MediaPlayer(media);

        canzoneIntro.setVolume(0.05); 
        canzoneIntro.setCycleCount(MediaPlayer.INDEFINITE);

        canzoneIntro.play();

        Image sfondo = new Image(getClass().getResourceAsStream("sfondoPacman.jpeg"));
        ImageView imageViewSfdondo = new ImageView(sfondo);

        imageViewSfdondo.setFitWidth(1650);
        imageViewSfdondo.setFitHeight(1002);

        VBox vBOx = new VBox(20);
        vBOx.setAlignment(Pos.CENTER);

        Image pacmanIntroAnimata= new Image(getClass().getResourceAsStream("pacmanSfondo.gif"));
        ImageView pacmanIntro = new ImageView(pacmanIntroAnimata);
        Image scrittaPacmanImage = new Image(getClass().getResourceAsStream("pacmanSfondo.png"));
        ImageView scrittaPacman = new ImageView(scrittaPacmanImage);


        Button start = new Button("INIZIA PARTITA");

        start.setStyle("-fx-font-size: 30px; -fx-padding: 15 30 15 30; -fx-background-color: yellow; -fx-text-fill: black; -fx-font-weight: bold; -fx-border-radius: 5; -fx-background-radius: 5;");
        start.setStyle(start.getStyle() + "; -fx-border-color: blue; -fx-border-width: 3;");

        vBOx.getChildren().addAll(scrittaPacman,pacmanIntro,  start);

        StackPane scenaIniziale = new StackPane();

        scenaIniziale.getChildren().addAll(imageViewSfdondo, vBOx);

        Scene startScene = new Scene(scenaIniziale, 1650, 1002);
        primaryStage.setTitle("Pacman");
        primaryStage.setScene(startScene);
        primaryStage.show();

        g = new GridPane();
        stratoPersonaggi = new Pane();
        fantasmaRosso = new Fantasma(new Image(getClass().getResourceAsStream("fantasma-rosso.gif")), 790, 460, this, "rosso"); 
        fantasmaGiallo = new Fantasma(new Image(getClass().getResourceAsStream("fantasma-giallo.gif")), 750, 460, this, "giallo"); 
        fantasmaRosa = new Fantasma(new Image(getClass().getResourceAsStream("fantasma-rosa.gif")), 830, 460, this, "rosa");
        fantasmaAzzurro = new Fantasma(new Image(getClass().getResourceAsStream("fantasma-Azzurro.gif")), 870, 460, this, "azzurro"); 
        
        pacman = new Pacman(new Image(getClass().getResourceAsStream("pacman.gif")), 32, 50, this);
        
        stratoPersonaggi.getChildren().add(pacman);
        stratoPersonaggi.getChildren().add(fantasmaRosso);
        stratoPersonaggi.getChildren().add(fantasmaRosa);
        stratoPersonaggi.getChildren().add(fantasmaGiallo);
        stratoPersonaggi.getChildren().add(fantasmaAzzurro);
        strati = new StackPane(g, stratoPersonaggi,lPunteggio,lVite); 

        mappa = new ImageView[larghezzaMappa][altezzaMappa];
        caricaMappa();

        giocoScena = new Scene(strati, 1650, 1002); 
        
        ImageView winImageView = new ImageView(immagineVittoria);
        winImageView.setFitWidth(1650);
        winImageView.setFitHeight(1002);
        StackPane winRoot = new StackPane(winImageView);
        scenaVittoria = new Scene(winRoot, 1650, 1002);

        ImageView gameOverImageView = new ImageView(immagineGameOver);
        gameOverImageView.setFitWidth(1650);
        gameOverImageView.setFitHeight(1002);
        StackPane gameOverRoot = new StackPane(gameOverImageView);
        scenaGameOver = new Scene(gameOverRoot, 1650, 1002);

        giocoScena.setOnKeyPressed(event -> {
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

        timerGioco = new AnimationTimer() { 
            @Override
            public void handle(long now) {
                pacman.aggiornaPosizionePacman();
                fantasmaRosso.aggiornaPosizioneFantasma();
                fantasmaRosa.aggiornaPosizioneFantasma();
                fantasmaGiallo.aggiornaPosizioneFantasma();
                fantasmaAzzurro.aggiornaPosizioneFantasma();
               
                pacmanX = pacman.getLayoutX();
                pacmanY = pacman.getLayoutY();
                
                haiVinto(primaryStage); 
                controllaCollisioneFantasmi(primaryStage);

                if (powerUpAttivo && (now - powerUpStartTime > durataPowerUp)) {
                    disattivaPowerUp();
                }
            }
        };

        timerPowerUp = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (powerUpAttivo && (now - powerUpStartTime > durataPowerUp)) {
                    disattivaPowerUp();
                    this.stop();
                }
            }
        };


        start.setOnAction(e -> {
        	canzoneIntro.stop();
            primaryStage.setScene(giocoScena); 
            timerGioco.start(); 
            giocoScena.getRoot().requestFocus();
        });
    }

    private void caricaMappa() {
        try (
            InputStream is = getClass().getResourceAsStream("PosizionePrimaMappa.txt");
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader lettore = new BufferedReader(isr);
        ) {
            int y = 0;
            String rigaLetta;

            while ((rigaLetta = lettore.readLine()) != null) {
                String[] caratteri = rigaLetta.split(",");

                for (int x = 0; x < caratteri.length; x++) {
                    String cella = caratteri[x].trim();
                    mappaCaratteri[x][y] = cella.charAt(0);
                    Image img = null;

                    switch (cella) {
                        case "s": img = spazio; break;
                        case "v": img = muroVerticale; break;
                        case "o": img = muroOrizzontale; break;
                        case "p": img = puntino; break;
                        case "aDA": img = angoloDestroAlto; break;
                        case "aSA": img = angoloSinistroAlto; break;
                        case "aDB": img = angoloDestroBasso; break;
                        case "aT": img = angoloT; break;
                        case "aTA": img = angoloTAlto; break;
                        case "aTS": img = angoloTSinistra; break;
                        case "aTD": img = angoloTDestra; break;
                        case "aSB": img = angoloSinistroBasso; break;
                        case "l": img = lineabluorizontale; break;
                        case "x": img = lineabluverticale; break;
                        case "z": img = pallinoGrande; break;

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

    public boolean calpestabilePacman(int x, int y) {
        if (x < 0 || x >= larghezzaMappa || y < 0 || y >= altezzaMappa) {
            return false;
        }
        return (mappaCaratteri[x][y] == 's' || mappaCaratteri[x][y] == 'p' || mappaCaratteri[x][y]=='z');

    }
    
    public boolean calpestabileFantasma(int x, int y) {
        if (x < 0 || x >= larghezzaMappa || y < 0 || y >= altezzaMappa) {
            return false;
        }
        return (mappaCaratteri[x][y] == 's' || mappaCaratteri[x][y] == 'p' || mappaCaratteri[x][y] == 'l' || mappaCaratteri[x][y] == 'x' || mappaCaratteri[x][y] == 'z');
    }

    public void raccogliPuntino(int x, int y) {
        if (mappaCaratteri[x][y] == 'p') {
            mappaCaratteri[x][y] = 's';
            mappa[x][y].setImage(spazio);
            punteggio = punteggio + 5;
            lPunteggio.setText("Punteggio: "+punteggio);
        } else if (mappaCaratteri[x][y] == 'z') {
            mappaCaratteri[x][y] = 's';
            mappa[x][y].setImage(spazio);
            attivaPowerUp();
        }
    }

    public boolean teletrasporta(double colonna) {
        if (colonna >= larghezzaMappa || colonna < 0) {
            return true;
        }
        return false;
    }
    
    public void haiVinto(Stage primaryStage) {
        if(punteggio >= 2860) { 
            primaryStage.setScene(scenaVittoria); 
            timerGioco.stop();
            if (timerPowerUp != null) timerPowerUp.stop(); 
        }
    }

    private void controllaCollisioneFantasmi(Stage primaryStage) {
        Fantasma[] fantasmi = {fantasmaRosso, fantasmaGiallo, fantasmaRosa, fantasmaAzzurro};

        for (Fantasma fantasma : fantasmi) {
            if (pacman.getColonnaPacman() == fantasma.getColonnaFantasma() && 
                pacman.getRigaPacman() == fantasma.getRigaFantasma()) {
                
                if (powerUpAttivo) {
                    respawnFantasma(fantasma);
                } else {
                	if(vite>1) {
                		vite--;
                		lVite.setText("vite: "+vite);
                		pacman.setLayoutX(50);
                		pacman.setLayoutY(50);
                        respawnFantasma(fantasma);
                	}else {
                		fermaGioco(primaryStage);
                        return;
                	}
                }
            }
        }
    }

    private void respawnFantasma(Fantasma fantasma) {
        if (fantasma == fantasmaRosso) {
            fantasma.setLayoutX(790);
            fantasma.setLayoutY(460);
            fantasma.setImage(new Image(getClass().getResourceAsStream("fantasma-rosso.gif")));
        } else if (fantasma == fantasmaGiallo) {
            fantasma.setLayoutX(750);
            fantasma.setLayoutY(460);
            fantasma.setImage(new Image(getClass().getResourceAsStream("fantasma-giallo.gif")));
        } else if (fantasma == fantasmaRosa) {
            fantasma.setLayoutX(830);
            fantasma.setLayoutY(460);
            fantasma.setImage(new Image(getClass().getResourceAsStream("fantasma-rosa.gif")));
        } else if (fantasma == fantasmaAzzurro) {
            fantasma.setLayoutX(870);
            fantasma.setLayoutY(460);
            fantasma.setImage(new Image(getClass().getResourceAsStream("fantasma-Azzurro.gif")));
        }
        fantasma.setDirezione("");
    }


    private void fermaGioco(Stage primaryStage) {
        timerGioco.stop();
        if (timerPowerUp != null) timerPowerUp.stop();
        primaryStage.setScene(scenaGameOver); 
    }

    private void attivaPowerUp() {
        powerUpAttivo = true;
        punteggio+=5;
        lPunteggio.setText("Punteggio: "+ punteggio);
        powerUpStartTime = System.nanoTime();
        fantasmaRosso.setImage(fantasmaImpaurito);
        fantasmaGiallo.setImage(fantasmaImpaurito);
        fantasmaRosa.setImage(fantasmaImpaurito);
        fantasmaAzzurro.setImage(fantasmaImpaurito);
        timerPowerUp.start();
    }

    private void disattivaPowerUp() {
        powerUpAttivo = false;
        fantasmaRosso.setImage(new Image(getClass().getResourceAsStream("fantasma-rosso.gif")));
        fantasmaGiallo.setImage(new Image(getClass().getResourceAsStream("fantasma-giallo.gif")));
        fantasmaRosa.setImage(new Image(getClass().getResourceAsStream("fantasma-rosa.gif")));
        fantasmaAzzurro.setImage(new Image(getClass().getResourceAsStream("fantasma-Azzurro.gif")));
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}