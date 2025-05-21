package it.edu.iisgubbio.giocoFofiDeLuca;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Pacman extends ImageView {
    int larghezzaMappa = 52;
    int altezzaMappa = 32;
    private double velocita = 2.4;
    private String direzione = "";
    private Gioco gioco;
    double startX;
    double startY;
    double x = getLayoutX();
    double y = getLayoutY();
    

    public Pacman(Image image, double startX, double startY, Gioco gioco) {
        super(image);
        this.gioco = gioco;
        this.setFitWidth(30);
        this.setFitHeight(30);
        this.startX = startX;
        this.startX = startY;
        setLayoutX(startX);
        setLayoutY(startY);
    }

    public void setDirezione(String direzione) {
        this.direzione = direzione;
    }
    
    

    public void aggiornaPosizionePacman() {
        double x = getLayoutX();
        double y = getLayoutY();


        switch (direzione) {
            case "UP":
                y -= velocita;
                setRotate(270);
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

        int colonna = (int) Math.round(x / 32.0);
        int riga = (int) Math.round(y / 32.0);

        if (gioco.teletrasporta(colonna)) {
        	if(colonna>=gioco.larghezzaMappa) {
        		 setLayoutX(0); 
                 setDirezione("RIGHT"); 
                 return; 
        	}else {
        		if(colonna<0) {
        			setLayoutX(1632); 
                    setDirezione("LEFT"); 
                    return; 
        		}
        	}
           
        }

        if (gioco.calpestabilePacman(colonna, riga)) {
            setLayoutX(x);
            setLayoutY(y);
            

            if (gioco.mappaCaratteri[colonna][riga] == 'p') {
                gioco.raccogliPuntino(colonna, riga);
            }
        }
    }
}