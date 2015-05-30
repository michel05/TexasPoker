package model;

import java.util.List;
import java.util.Random;

/**
 * @author Michel
 *
 */
public class Carta implements Comparable<Carta>{
	
	/** Variáveis de Carta*/
	String valor;
	Integer valoreal;
	String naipe;
	/** Cria uma instância de Random e cria duas variáveis
	 * para referenciar um baralho de Cartas */
	Random rand = new Random();  
	String letras = "123456789TJQK";
	String naipes = "SHCD";
	
	
	/** @return Valor em forma de caractere*/
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		
		if(valor.equals("2")){ this.setValoreal(2); }
		if(valor.equals("3")){ this.setValoreal(3); }
		if(valor.equals("4")){ this.setValoreal(4); }
		if(valor.equals("5")){ this.setValoreal(5); }
		if(valor.equals("6")){ this.setValoreal(6); }
		if(valor.equals("7")){ this.setValoreal(7); }
		if(valor.equals("8")){ this.setValoreal(8); }
		if(valor.equals("9")){ this.setValoreal(9); }
		if(valor.equals("t") || valor.equals("T")){ this.setValoreal(10); }
		if(valor.equals("j") || valor.equals("J")){ this.setValoreal(11); }
		if(valor.equals("q") || valor.equals("Q")){ this.setValoreal(12); }
		if(valor.equals("k") || valor.equals("K")){ this.setValoreal(13); }
		/**	O Ás recebece valor 14 pois é a Carta mais alta do jogo */
		if(valor.equals("1")){ this.setValoreal(14); }
		
		this.valor = valor;
	}
	
	/** @return naipe*/
	public String getNaipe() {
		return naipe;
	}
	
	/** @paramm naipe*/
	public void setNaipe(String naipe) {
		this.naipe = naipe;
	}
	
	/** @return valor da Carta em inteiro*/
	public int getValoreal() {
		return valoreal;
	}
	
	/** @param valoreal da Carta em inteiro*/
	public void setValoreal(int valoreal) {
		this.valoreal = valoreal;
	}
	
	/** método que auxilia na ordenacão do Vetor de Cartas na 
	 * Classe "Classificacao" pelo valoreal da Carta*/
	@Override  
    public int compareTo(Carta c) {  
        // TODO Auto-generated method stub  
        return this.valoreal.compareTo(c.valoreal);  
    }
	
	/** @return letra sorteada do "baralho"*/
	public char letraRandom () {  			         
	    return letras.charAt(rand.nextInt(letras.length()));      
	}
	
	/** @return naipe sorteado do "baralho"*/
	public char naipeRandom () {  
		return naipes.charAt(rand.nextInt(naipes.length()));	    
	}
	
	/** @return true/false 
	 * @param Lista dos jogadores, letra, naipe
	 * verifica se já existe a carta sorteada, caso positivo retorna false, caso contrario
	 * retorna true */
	public boolean verificaCarta(List<Jogador> jogadores, String letra, String naipe){
		
		for(Jogador j : jogadores){
			for(Carta allcarta : j.getCarta()){
					if(allcarta.getValor().equals(letra) && allcarta.getNaipe().equals(naipe)){
						return false;
					}
			}			
		}
		
		return true;
	}
		

}
