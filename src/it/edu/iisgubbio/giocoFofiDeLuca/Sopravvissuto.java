package it.edu.iisgubbio.giocoFofiDeLuca;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sopravvissuto extends ImageView {

    private double speed = 1.5;
    private String direzione = ""; // "UP", "DOWN", ecc.

    public Sopravvissuto(Image image, double startX, double startY) {
        super(image);
        this.setFitWidth(32);
        this.setFitHeight(32);
        setLayoutX(startX);
        setLayoutY(startY);
    }

    public void setDirezione(String direzione) {
        this.direzione = direzione;
    }

    public void aggiornaPosizione() {
        switch (direzione) {
            case "UP":
                setLayoutY(getLayoutY() - speed);
                break;
            case "DOWN":
                setLayoutY(getLayoutY() + speed);
                break;
            case "LEFT":
                setLayoutX(getLayoutX() - speed);
                break;
            case "RIGHT":
                setLayoutX(getLayoutX() + speed);
                break;
        }
    }
}
