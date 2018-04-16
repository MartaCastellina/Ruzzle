package it.polito.tdp.Ruzzle.model;

import java.util.ArrayList;
import java.util.List;

public class Ricerca {

	public List<Pos> trovaParola(String parola, Board board) {
		
		//Qui bisogna far partire la ricorsione (Ho 16 possibili punti di inizio della ricorsione, uno per ognuna delle caselle iniziali)
		for(Pos p:board.getPositions()) {
			if (board.getCellValueProperty(p).get().charAt(0)==parola.charAt(0)) {
				//Inizio potenziale della parola:faccio ricorsione
				//Richiamo cerca, con livello che sarà 1, liv 0 è già sistemato
				List<Pos> percorso=new ArrayList<>();
				percorso.add(p);
				if(cerca(parola,1,percorso,board))
					return percorso;
				
			}
		}
		
		//Ritorna null se alla fine non trova nulla
		
		return null;
	}
	
	private boolean cerca(String parola, int livello, List<Pos> percorso,Board board) {
		//Questa è misura ricorsiva
		
		if (livello==parola.length()) {
			return true;
			//Perchè sono nel caso terminale, tutti i caratteri sono già stati posizionati nel livello sopra al mio, non ho più nulla da fare
			
		}
		//Caso intermedio
		Pos ultima=percorso.get(percorso.size()-1);
		List<Pos> adiacenti=board.getAdiacenti(ultima);
		//Chiedo alla board quali siano le lettere adiacenti a me
		for(Pos nuova:adiacenti) {
			if ((board.getCellValueProperty(nuova).get().charAt(0)==parola.charAt(livello)) 
				&& (!percorso.contains(nuova)))
				{
				//Faccio ricorsione
				percorso.add(nuova);
				if(cerca(parola,livello+1,percorso,board))
					return true; //uscita rapida in caso di soluzione
				//Sennò continua a cercare
				percorso.remove(percorso.size()-1); //è diverso da quello iniziale perchè nel frattempo ne ho aggiunto uno
				
			}
				
		}
		
		return false;

	}
}
