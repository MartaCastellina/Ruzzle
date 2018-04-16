package it.polito.tdp.Ruzzle.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Memorizza le lettere presenti nella scacchiera Ruzzle.
 * @author Fulvio
 *
 */

//Board sa lo stato del gioco

public class Board {
	private List<Pos> positions;
	private Map<Pos, StringProperty> cells; //Data una certa posizione qual Ë la stringa contenuta?
	//Uso string property cosÏ le lettere che aggiungo si aggiungono in automatico (invece di aggiornare ogni volta quando aggiungo lettera)
	

	private int size;

	/**
	 * Crea una nuova scacchiera della dimensione specificata
	 * @param size
	 */
	public Board(int size) {
		this.size = size;

		this.positions = new ArrayList<>();
		for (int row = 0; row < this.size; row++) {
			for (int col = 0; col < this.size; col++) {
				this.positions.add(new Pos(row, col));
			}
		}

		this.cells = new HashMap<>();

		for (Pos p : this.positions) {
			this.cells.put(p, new SimpleStringProperty());
		}
	}
	
	/**
	 * Fornisce la {@link StringProperty} corrispondente alla {@link Pos} specificata. <p>
	 * 
	 * Pu√≤ essere usata per sapere che lettera √® presente
	 * (es. {@code getCellValueProperty(p).get()}) oppure per fare un binding della propriet√† stessa sulla mappa visuale.
	 * @param p
	 * @return
	 */
	public StringProperty getCellValueProperty(Pos p) {
		return this.cells.get(p) ;
	}

	/**
	 * Restituisce la lista di oggetti {@link  Pos} che corrispondono alle posizioni lecite sulla scacchiera. Gli elementi sono ordinati per righe.
	 * @return
	 */
	public List<Pos> getPositions() {
		return positions;
	}

	/**
	 * Crea una nuova scacchiera generando tutte lettere casuali
	 */
	public void reset() {
		for(Pos p: this.positions) {
			int random = (int)(Math.random()*26) ;
			//Prende numeri da 0 a 25 e trasforma in stringa
			String letter = Character.toString((char)('A'+random)) ;
			this.cells.get(p).set(letter); 
		}
	}

	public List<Pos> getAdiacenti(Pos ultima) {

		List <Pos> result=new ArrayList<>();
		
		for (int r=-1;r<=1;r++) {
			for (int c=-1;c<=1;c++) {
				//Tutte le 9 posizioni nell'intorno della cella
				
				if (r!=0 || c!= 0) { //Esclusa la cella stessa(offset 0,0)
					Pos p=new Pos(ultima.getRow()+r, ultima.getCol()+c);
					if(positions.contains(p)) {
						result.add(p);
					}
					
				}
			}
		}
		
		
		return result;
	}

	
}
