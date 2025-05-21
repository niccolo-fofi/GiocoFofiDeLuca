package it.edu.iisgubbio.giocoFofiDeLuca;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Fantasma extends ImageView {
	int larghezzaMappa=52;
	int altezzaMappa=32;
    private double velocita = 2.7;
    private String direzione = ""; 
    private Gioco gioco;

        public Fantasma(Image image, double startX, double startY, Gioco gioco) {
            super(image);
            this.gioco = gioco;
            this.setFitWidth(35);
            this.setFitHeight(35);
            setLayoutX(startX);
            setLayoutY(startY);
        }

    public void setDirezione(String direzione) {
        this.direzione = direzione;
    }

    public void aggiornaPosizioneFantasma() {
        double x = getLayoutX();
        double y = getLayoutY();
        
        switch (direzione) {
            case "UP":
                y -= velocita;
                break;
            case "DOWN":
                y += velocita;
                break;
            case "LEFT":
                x -= velocita;
                break;
            case "RIGHT":
                x += velocita;
                break;
        }

        int colonna = (int)Math.round(x / 32.0);  
        int riga = (int)Math.round(y / 32.0);


        if (gioco.calpestabileFantasma(colonna, riga)) {
            setLayoutX(x);
            setLayoutY(y);
        }
    }
}