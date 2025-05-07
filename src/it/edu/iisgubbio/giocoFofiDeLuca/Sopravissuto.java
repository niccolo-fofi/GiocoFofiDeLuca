package it.edu.iisgubbio.giocoFofiDeLuca;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Sopravissuto {
	
	/**
	 * dichiaro le variabili "x", "y", "velocità" che serve per far
	 * muovere il sopravvissuto.
	 */
	
	private Rectangle rettangolo;
	final private double velocità = 10;
	private double x;
	private double y;



	/**
	 * Qui mi occupo del movimento del sopravvissuto.
	 * @param x
	 * @param y
	 */
	
	 public Sopravissuto(int x, int y) {
	        this.x = x;
	        this.y = y;
	        // Crea un rettangolo per rappresentare il personaggio
	        Rectangle rettangolo = new Rectangle(x, y, 30, 30);
	        rettangolo.setFill(Color.BLUE);
	 }
	
	public Rectangle getRettangolo() {
        return rettangolo;
    }
	
	public void muoviSu(){
		y += velocità;
		aggiornaPosizione();
	}
	
	public void muoviGiu(){
		y -= velocità;
		aggiornaPosizione();
			
	}
	
	public void muoviDestra(){
		x += velocità;
		aggiornaPosizione();
		
	}
	
	public void muoviSinistra(){
		
		x -= velocità;
		aggiornaPosizione();
	}
	
	 private void aggiornaPosizione() {
			rettangolo.setX(x);
	        rettangolo.setY(y);
	 }
    
    /**
     * Nella funzione prendiDanno descrivo che quando il sopravvissuto viene
     * in contatto con lo zombie prende danno 
     */
    
    
    public boolean prendiDanno() {
		
    }
    
    /**
     * Nella funzione cacciaFantasmi descrivo che quando il sopravvissuto prende
     * il punto rosa, puo cacciare gli zombie
     */
    
    
    public boolean cacciaFantasmi() {
		
	}
	
 	/**
 	 * Nella funzione prendiDanno descrivo che quando il sopravvissuto viene
 	 *  in contatto con lo zombie prende danno 
 	 */
   
    
    
    public boolean collisione() {
    	
    	switch () {
        case "aDA":
        	
            break; 
        case "aSA":
        	
            break; 
        case "aDB":
        	
            break; 
            
        case "aSB":
            break;
            
        case "aT":
            break;
        
        case "aTA":
            break;
            
        case "o":
            break;
            
        case "v":
            break;
            
        default:
        	
        	
    	}
		
   	}

}
