package it.edu.iisgubbio.giocoFofiDeLuca;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Pacman extends ImageView {
	int larghezzaMappa=52;
	int altezzaMappa=32;
    private double velocita = 2.5;
    private String direzione = ""; 
    private Gioco gioco;

        public Pacman(Image image, double startX, double startY, Gioco gioco) {
            super(image);
            this.gioco = gioco;
            this.setFitWidth(30);
            this.setFitHeight(30);
            setLayoutX(startX);
            setLayoutY(startY);
        }

    public void setDirezione(String direzione) {
        this.direzione = direzione;
    }

    public void aggiornaPosizione() {
        double x = getLayoutX();
        double y = getLayoutY();
        
        switch (direzione) {
            case "UP":
                y -= velocita;
                setRotate(270);
                System.out.println(x);
                System.out.println(y);

                break;
            case "DOWN":
                y += velocita;
                setRotate(90);
                break;
            case "LEFT":
                x -= velocita;
                setRotate(180);
                break;
            case "RIGHT":
                x += velocita;
                setRotate(0);
                break;
        }

        int colonna = (int)Math.round(x / 32.0);  
        int riga = (int)Math.round(y / 32.0);
        
        if (gioco.mappaCaratteri[colonna][riga] == 'p') {
            gioco.raccogliPuntino(colonna, riga);
        }

        if(gioco.teletrasporta(x)) {
        	setLayoutX(0);
        }
        
        if (gioco.calpestabile(colonna, riga)) {
            setLayoutX(x);
            setLayoutY(y);
        }
    }
}