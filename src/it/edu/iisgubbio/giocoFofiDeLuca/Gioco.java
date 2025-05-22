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
    Image lineabluorizontale = new Image(getClass().getResourceAsStream("LineaBluOrizontale.png"));
    Image lineabluverticale = new Image(getClass().getResourceAsStream("LineaBluVerticale.png"));
    Pacman pacman;
    double pacmanX;
    double pacmanY;

    int altezzaMappa = 32;
    int larghezzaMappa = 52;
    char mappaCaratteri[][]=new char[larghezzaMappa][altezzaMappa];
    int punteggio=0;
    Label lPunteggio = new Label(punteggio+"");

    GridPane g;
    Pane stratoPersonaggi;
    Fantasma fantasmaRosso;
    Fantasma fantasmaGiallo;
    Fantasma fantasmaRosa;
    Fantasma fantasmaAzzurro;
    StackPane strati;
    Scene giocoScena; 

    private MediaPlayer canzoneIntro;

    @Override
    public void start(Stage primaryStage) throws Exception {

    	lPunteggio.setStyle(
    		    "-fx-font-weight: bold;" +
    		    "-fx-text-fill: WHITE;");

    	String audioFilePath = getClass().getResource("ZISO - Street Wisdom.mp3").toExternalForm();
        Media introMedia = new Media(audioFilePath);
        canzoneIntro = new MediaPlayer(introMedia);

        canzoneIntro.setVolume(0.05); 
        canzoneIntro.setCycleCount(MediaPlayer.INDEFINITE);

        canzoneIntro.play();

        Image backgroundImage = new Image(getClass().getResourceAsStream("sfondoPacman.jpeg"));
        ImageView backgroundImageView = new ImageView(backgroundImage);

        backgroundImageView.setFitWidth(1650);
        backgroundImageView.setFitHeight(1002);

        VBox contentLayout = new VBox(20);
        contentLayout.setAlignment(Pos.CENTER);

        Image pacmanIntroImage = new Image(getClass().getResourceAsStream("pacmanSfondo.gif"));
        ImageView pacmanIntro = new ImageView(pacmanIntroImage);
        Image scrittaPacmanImage = new Image(getClass().getResourceAsStream("pacmanSfondo.png"));
        ImageView scrittaPacman = new ImageView(scrittaPacmanImage);


        Button start = new Button("INIZIA PARTITA");

        start.setStyle("-fx-font-size: 30px; -fx-padding: 15 30 15 30; -fx-background-color: yellow; -fx-text-fill: black; -fx-font-weight: bold; -fx-border-radius: 5; -fx-background-radius: 5;");
        start.setStyle(start.getStyle() + "; -fx-border-color: blue; -fx-border-width: 3;");

        contentLayout.getChildren().addAll(scrittaPacman,pacmanIntro,  start);

        StackPane startScreenStack = new StackPane();

        startScreenStack.getChildren().addAll(backgroundImageView, contentLayout);

        Scene startScene = new Scene(startScreenStack, 1650, 1002);
        primaryStage.setTitle("Pacman");
        primaryStage.setScene(startScene);
        primaryStage.show();

        g = new GridPane();
        g.add(lPunteggio, 1, 0); 

        stratoPersonaggi = new Pane();
        fantasmaRosso = new Fantasma(new Image(getClass().getResourceAsStream("fantasma-rosso.gif")), 790, 460, this);
        fantasmaGiallo = new Fantasma(new Image(getClass().getResourceAsStream("fantasma-giallo.gif")), 750, 460, this);
        fantasmaRosa = new Fantasma(new Image(getClass().getResourceAsStream("fantasma-rosa.gif")), 830, 460, this);
        fantasmaAzzurro = new Fantasma(new Image(getClass().getResourceAsStream("fantasma-Azzurro.gif")), 870, 460, this);
        pacman = new Pacman(new Image(getClass().getResourceAsStream("pacman.gif")), 32, 50, this);
        stratoPersonaggi.getChildren().add(pacman);
        stratoPersonaggi.getChildren().add(fantasmaRosso);
        stratoPersonaggi.getChildren().add(fantasmaRosa);
        stratoPersonaggi.getChildren().add(fantasmaGiallo);
        stratoPersonaggi.getChildren().add(fantasmaAzzurro);
        strati = new StackPane(g, stratoPersonaggi); 

        mappa = new ImageView[larghezzaMappa][altezzaMappa];
        caricaMappa();

        giocoScena = new Scene(strati, 1650, 1002); 

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

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                pacman.aggiornaPosizionePacman();
                fantasmaRosso.aggiornaPosizioneFantasma();
                fantasmaGiallo.aggiornaPosizioneFantasma();
                fantasmaRosa.aggiornaPosizioneFantasma();
                fantasmaAzzurro.aggiornaPosizioneFantasma();
                pacmanX = pacman.getLayoutX();
                pacmanY = pacman.getLayoutY();
            }
        };

        start.setOnAction(e -> {
        	canzoneIntro.stop();
            primaryStage.setScene(giocoScena); 
            timer.start(); 
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
                        case "bo": img = lineabluorizontale; break;
                        case "bv": img = lineabluverticale; break;
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
        return (mappaCaratteri[x][y] == 's' || mappaCaratteri[x][y] == 'p');
    }

    public boolean calpestabileFantasma(int x, int y) {
        if (x < 0 || x >= larghezzaMappa || y < 0 || y >= altezzaMappa) {
            return false;
        }
        return (mappaCaratteri[x][y] == 's' || mappaCaratteri[x][y] == 'p' || mappaCaratteri[x][y] == 'b');
    }

    public void raccogliPuntino(int x, int y) {
        mappaCaratteri[x][y] = 's';
        mappa[x][y].setImage(spazio);
        punteggio = punteggio + 5;
        lPunteggio.setText(punteggio + "");
    }

    public boolean teletrasporta(double colonna) {
        if (colonna >= larghezzaMappa || colonna < 0) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        launch(args);
    }
}