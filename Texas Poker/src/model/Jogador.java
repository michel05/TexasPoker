package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michel
 *
 */
public class Jogador {
	
	/** Lista principal de cartas do Jogador*/
	private List<Carta> listaCarta = new ArrayList<Carta>();
	/** Vetor de cartas auxiliar*/
	private Carta[] vetCarta = new Carta[7];
	private int status;
	private String rank;
	
	/** @return Lista de Cartas do Jogador*/
	public List<Carta> getCarta() {
		return listaCarta;		
	}
	/** @param Carta */
	public void addCarta(Carta carta){
		listaCarta.add(carta);
	}
	
	/** @param Lista de Cartas*/
	public void addnewCartas(List<Carta> cartas){
		/** Limpa a lista atual de cartas e adiciona uma nova lista*/
		this.listaCarta.clear();
		listaCarta.addAll(cartas);
	}

	/** @return Vetor de Cartas do Jogador*/
	public Carta[] getVetCarta() {
		return vetCarta;
	}

	/** @param Carta,index */
	public void setVetCarta(Carta vetCarta, int index) {
		/** adiciona no vetor de Carta o Objeto recebido junto com seu indice */
		this.vetCarta[index] = vetCarta;
	}
	
	/** @return Status*/
	public int getStatus() {
		return status;
	}

	/** @param Status*/
	public void setStatus(int status) {
		/** De acordo com o status é adicionado o ranking referende na variável rank*/
		if(status==1) { this.setRank("Royal Flush");  }
		if(status==2) { this.setRank("Straight Flush");  }
		if(status==3) { this.setRank("Quadra");  }
		if(status==4) { this.setRank("Full House");  }
		if(status==5) { this.setRank("Flush");  }
		if(status==6) { this.setRank("Straight");  }
		if(status==7) { this.setRank("Trinca");  }
		if(status==8) { this.setRank("Two Pair");  }
		if(status==9) { this.setRank("One Pair");  }
		if(status==10){ this.setRank("High Card");  }
		if(status==11){ this.setRank(" ");  }
		
		this.status = status;
	}

	/** @return ranking do Jogador*/
	public String getRank() {
		return rank;
	}

	/** @param Ranking do Jogador*/
	public void setRank(String rank) {
		this.rank = rank;
	}
	
	
	
	
}
