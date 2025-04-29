package it.edu.iisgubbio.giocoFofiDeLuca;

import java.awt.event.KeyAdapter;
import javafx.scene.input.KeyEvent;

public class Sopravissuto extends PrimaMappa{
	
	//dichiaro le variabili "x", "y", "velocità" che serve per far
	// muovere il sopravvissuto.
	
	private int velocità = 10;
	private int x;
	private int y;

	
	
	//Qui mi occupo del movimento del sopravvissuto.
	
	public void MovimentoPersonaggio() {
		
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int keyCode = e.getKeyCode();
				
				if (keyCode == KeyEvent.VK_DOWN) {
					y = velocità;  // Muovi verso il basso	
				}
				
				if (keyCode == KeyEvent.VK_UP) {
					y = velocità;  // Muovi verso l'alto
				}
				
				if (keyCode == KeyEvent.VK_LEFT) {
		    		x = velocità;  // Muovi verso la sinistra		
		    	}
				
				if (keyCode == KeyEvent.VK_RIGHT) {
		    		x = velocità;  // Muovi verso la destra		
		    	}
			}
		}
	}
	
	
	   
    
	public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
	
	
    
    //Nella funzione prendiDanno descrivo che quando il sopravvissuto viene
    //in contatto con lo zombie prende danno 
    
    public boolean prendiDanno() {
		
    }
    
    //Nella funzione cacciaFantasmi descrivo che quando il sopravvissuto prende
    //il punto rosa, puo cacciare gli zombie
    
    public boolean cacciaFantasmi() {
		
	}
	
 	//Nella funzione prendiDanno descrivo che quando il sopravvissuto viene
    //in contatto con lo zombie prende danno 
    
	

		
	
	
	
}
