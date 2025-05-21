package it.edu.iisgubbio.giocoFofiDeLuca;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Fantasma extends ImageView {
    int larghezzaMappa = 52;
    int altezzaMappa = 32;
    private double velocita = 1.5;
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

        int colonnaFantasma = (int) Math.round(x / 32.0); 
        int rigaFantasma = (int) Math.round(y / 32.0);

        int colonnaPacman = (int) Math.round(gioco.pacmanX / 32.0); 
        int rigaPacman = (int) Math.round(gioco.pacmanY / 32.0); 

        String direzioneOttimale = "";
        double distanzaMinima = Double.MAX_VALUE; 

        String[] direzioni = {"UP", "DOWN", "LEFT", "RIGHT"};

        for (String stringa : direzioni) {
            int prossimaColonna = colonnaFantasma;
            int prossimaRiga = rigaFantasma;

            switch (stringa) {
                case "UP":    prossimaRiga--; break;
                case "DOWN":  prossimaRiga++; break;
                case "LEFT":  prossimaColonna--; break;
                case "RIGHT": prossimaColonna++; break;
            }

            if (gioco.calpestabileFantasma(prossimaColonna, prossimaRiga)) { 
                double distanza = Math.abs(prossimaColonna - colonnaPacman) + Math.abs(prossimaRiga - rigaPacman);
                
                if (distanza < distanzaMinima) {
                    distanzaMinima = distanza;
                    direzioneOttimale = stringa;
                }
            }
        }
        
        this.direzione = direzioneOttimale;

        switch (this.direzione) {
            case "UP":    
            	setLayoutY(getLayoutY() - velocita); 
            	break;
            case "DOWN":  
            	setLayoutY(getLayoutY() + velocita);
            	break;
            case "LEFT":  
            	setLayoutX(getLayoutX() - velocita); 
            	break;
            case "RIGHT": 
            	setLayoutX(getLayoutX() + velocita); 
            	break;
        }
    }
}