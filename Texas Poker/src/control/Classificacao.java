package control;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Carta;
import model.Jogador;

/**
 * @author Michel
 *
 */
public class Classificacao {
	
	/** Lista de Jogadores auxiliar que recebe a lista principal de Jogadores*/
	List<Jogador> jogadorLista;
	/** Lista auxiliar que ir� conter as Cartas relacionadas ao tipo de sequencia que o jogador fez*/
	List<Carta>  auxCarta = new ArrayList<Carta>();
	/** Lista auxilar que ir� conter todos as Cartas de forma ordenada pela sequencia obtida*/
	List<Carta>  listCartaOrd = new ArrayList<Carta>();
	
	/** @param Lista de jogador principal*/
	public Classificacao(List<Jogador> jogadorLista){
		this.jogadorLista = jogadorLista;
		
		/**preenche o vetor com as Cartas da Lista para facilitar as comparac�es
		 * que s�o necess�rias nesta Classe*/
		for(Jogador j : this.jogadorLista){
			int index = 0;
				for(Carta allcarta : j.getCarta()){
					 j.setVetCarta(allcarta, index);
					 index++;
				}			
		}
		
		/**Ordena o vetor de Carta por valoreal*/
		for(Jogador j : this.jogadorLista){
			Arrays.sort(j.getVetCarta());
		}
	}
	
	
	/**	Ranking Royal FLush */
	public void royalFlush(){
		
		for(Jogador j : jogadorLista){
			Carta[] c = j.getVetCarta();
			
			/**	 Limpa a listaCartaOrd*/
			listCartaOrd.clear();
				
				/**	 percorre as �ltimas 4 Cartas da Lista*/
				for(int i=6;i>2;i--){
					/**	se algumas delas for o �s, ent�o seu valoreal � 14, a Carta na posic�o 3
					 * � sempre testada pois ela deve estar entre as cartas do Royal Flush */
					if(c[i].getValoreal() == 14  && c[3].getNaipe().equals(c[i].getNaipe())){
						int cont = 0;
							/**Percorre todas as cartas a procura de 10,J,Q e K dos mesmos naipes, caso
							 * ache a Carta � adicionada na lista auxiliar "auxCarta" e cont � incrementado*/
							for(int w=0;w<7;w++){
								if(c[w].getValoreal()==10 && c[i].getNaipe().equals(c[w].getNaipe()) && c[3].getNaipe().equals(c[w].getNaipe())){		
									auxCarta.add(c[w]);
									cont++;	}
								if(c[w].getValoreal()==11 && c[i].getNaipe().equals(c[w].getNaipe()) && c[3].getNaipe().equals(c[w].getNaipe())){		
									auxCarta.add(c[w]);
									cont++;	}
								if(c[w].getValoreal()==12 && c[i].getNaipe().equals(c[w].getNaipe()) && c[3].getNaipe().equals(c[w].getNaipe())){		
									auxCarta.add(c[w]);
									cont++;	}
								if(c[w].getValoreal()==13 && c[i].getNaipe().equals(c[w].getNaipe()) && c[3].getNaipe().equals(c[w].getNaipe())){	
									auxCarta.add(c[w]);
									cont++;	}
							}
							
							/** Se cont for igual a 4 ent�o ocorreu um Royal Flush, o status recebe 1*/
							if(cont==4){
								auxCarta.add(c[i]);
								j.setStatus(1);
								
								/** Ordena a lista*/
								ordena(auxCarta, j);
							}
					}	
				}
			}
	}
	
	/** Ranking Straight Flush*/
	public void straightFlush(){
		
		
		for(Jogador j : jogadorLista){
			Carta[] c = j.getVetCarta();
						
			listCartaOrd.clear();
			int aux1 = 0;
			int aux2 = 0;
			int aux3 = 0;
			int aux0 = 0;
			
				/** Percorre todas as Cartas fazendo as verificac�es*/
				for(int w=0;w<7;w++){
					if(j.getStatus()!=2){
					
						auxCarta.add(c[w]);
						aux0 = 0;
						aux1 = 0;
						aux2 = 0;
						aux3 = 0;
								
						/**verifica se as proximas quatro cartas est�o na sequencia e se tem o mesmo naipe, ent�o 
						 * grava as cartas encontradas na lista "auxCarta"*/
						for(int z=0;z<7;z++){
							if(c[w].getValoreal() == (c[z].getValoreal() - 1) && c[w].getNaipe().equals(c[z].getNaipe())){ aux0++; if(aux0==1){ auxCarta.add(c[z]); }}
							if(c[w].getValoreal() == (c[z].getValoreal() - 2) && c[w].getNaipe().equals(c[z].getNaipe())){ aux1++; if(aux1==1){ auxCarta.add(c[z]); }}
							if(c[w].getValoreal() == (c[z].getValoreal() - 3) && c[w].getNaipe().equals(c[z].getNaipe())){ aux2++; if(aux2==1){ auxCarta.add(c[z]); }}
							if(c[w].getValoreal() == (c[z].getValoreal() - 4) && c[w].getNaipe().equals(c[z].getNaipe())){ aux3++; if(aux3==1){ auxCarta.add(c[z]); }}
						}
						
						/** Se a condic�o for valida ent�o ocorrer� um Straight Flush, o status recebe 2*/
						if( aux0 ==1 && aux1>=1 && aux2>=1 && aux3>=1 ){
							j.setStatus(2);
						
							/** Ordena a lista*/
							ordena(auxCarta, j);
						}
						
						/**Caso as primeiras cartas sejam sequencia e as demais n�o, a lista � limpada*/
						else{ 
							auxCarta.clear();
						}
					}
				}
		}
	}

	/** Ranking Quadra*/
	public void quadra(){
		for(Jogador j : jogadorLista){
			Carta[] c = j.getVetCarta();
			
			listCartaOrd.clear();
			auxCarta.clear();
			/** Percorre de as poss�veis possic�es onde h� uma quadra e verifica se as 
			 * pr�ximas 3 posic�es possue o mesmo valor*/
			for(int i=0;i<4;i++){
				int cont = 0;
				if(c[i].getValoreal() == c[i+1].getValoreal()){	cont++; }
				if(c[i].getValoreal() == c[i+2].getValoreal()){	cont++; }
				if(c[i].getValoreal() == c[i+3].getValoreal()){	cont++; }
				
				/** Se for encontrada as tres cartas ocorrer� uma quadra, status recebe 3*/
				if(cont==3){
					/** Grava as cartas encontradas na lista "auxCarta"*/
					auxCarta.add(c[i]);
					auxCarta.add(c[i+1]);
					auxCarta.add(c[i+2]);
					auxCarta.add(c[i+3]);
					j.setStatus(3);
						
					/** Ordena a lista*/
					ordena(auxCarta, j);
							
				}
			}
		}
	}
	
	/** Ranking Full House*/
	public void fullHouse(){
		
		/**Verifica se h� uma trinca */
		this.trinca();
		for(Jogador j : jogadorLista){
			/** Caso h� uma trinca, verificar� se h� uma dupla*/
			if(j.getStatus()==7){
				
				/** Transcreve a lista de Carta do jogador para o Vetor de Cartas
				 * para que possa fazer a comparac�o mais facilmente*/
				int index = 0;
				for(Carta allcarta : j.getCarta()){
					j.setVetCarta(allcarta, index);
					index++;
				}		
			
				Carta[] c = j.getVetCarta();
				
				/** Caso encontre uma dupla ocorrer� um Full House, status recebe 4*/
				for(int i=3;i<6;i++){
					if(c[i].getValoreal() == c[i+1].getValoreal()){
						j.setStatus(4);
					}
				}
			}	
		}
	}
	
	/** Ranking Flush*/
	public void flush(){
		for(Jogador j : jogadorLista){
			Carta[] c = j.getVetCarta();
			
			listCartaOrd.clear();
			
			int index=0;
			/** Percorre o vetor de Carta procurando Cartas de mesmo naipe,
			 * caso encontre adiciona ela na lista "AuxCarta" e incrementa "index" */
			for(int i=0;i<7;i++){
				
				if(index>=5) { 
					i=7; 
				}
				
				else{
					index=0;
					for(int k=0;k<7;k++){
						if(c[i].getNaipe().equals(c[k].getNaipe())){
							auxCarta.add(c[k]);
							index++;  }
					}
					/** Se encontrar 5 posicoes com mesmo naipe ocorrer� um Flush, status recebe 5*/
					if(index>=5){ 
						j.setStatus(5);
						
						/** Ordena a lista*/
						ordena(auxCarta, j);
					}
				}
				
				/** Limpa a lista "auxCarta"*/
				auxCarta.clear();
			}		
		}
	}
	
	/** Ranking Straight*/
	public void straight(){
		
		for(Jogador j : jogadorLista){
			Carta[] c = j.getVetCarta();
			listCartaOrd.clear();
			auxCarta.clear();
			int aux1 = 0;
			int aux2 = 0;
			int aux3 = 0;
			int aux0 = 0;
			
			/** Percorre todas as Cartas fazendo as verificac�es*/	
			for(int w=0;w<7;w++){
				if(j.getStatus()!=6){
						
					auxCarta.add(c[w]);
					aux0 = 0;
					aux1 = 0;
					aux2 = 0;
					aux3 = 0;
						
				    /**verifica se h� 4 cartas que est�o na sequencia e adiciona a carta na lista
				     * "auxCarta" caso encontre */
					for(int z=0;z<7;z++){
						if(c[w].getValoreal() == (c[z].getValoreal() - 1)){ aux0++; if(aux0==1){ auxCarta.add(c[z]); }}
						if(c[w].getValoreal() == (c[z].getValoreal() - 2)){ aux1++; if(aux1==1){ auxCarta.add(c[z]); }}
						if(c[w].getValoreal() == (c[z].getValoreal() - 3)){ aux2++; if(aux2==1){ auxCarta.add(c[z]); }}
						if(c[w].getValoreal() == (c[z].getValoreal() - 4)){ aux3++; if(aux3==1){ auxCarta.add(c[z]); }}
					}
					
					 /** Se a condic�o for valida ent�o ocorrer� um Straight, o status recebe 6*/
						if( aux0 ==1 && aux1>=1 && aux2>=1 && aux3>=1 ){
							j.setStatus(6);
								
							/** Ordena a lista*/
							ordena(auxCarta, j);
										
						}
							
						/**Caso as primeiras cartas sejam sequencia e as demais n�o */
						else{ 
							auxCarta.clear();
						}
				}
			}	
		}
	}
	
	
	/** Ranking Trinca*/
	public void trinca(){
		
		for(Jogador j : jogadorLista){
			Carta[] c = j.getVetCarta();
			
			listCartaOrd.clear();
				/** Percorre as poss�veis possic�es que ocorrer� a trinca */
				for(int i=0;i<5;i++){	
					auxCarta.clear();
					int cont = 0;
					
					/** Verifica se h� tres valores de Cartas iguais, e ent�o
					 * incrementa "cont"*/
					for(int k=i+1;k<i+3;k++){
						if(c[i].getValoreal() == c[k].getValoreal()){	cont++; }
					}	
						/** Se encontrar 3 valores iguais ocorrer� uma trinca, status recebe 7*/
						if(cont==2){
							/** � adicionado na lista "auxCarta" as Cartas da trinca*/
							auxCarta.add(c[i]);
							auxCarta.add(c[i+1]);
							auxCarta.add(c[i+2]);
							j.setStatus(7);
							
							/** Ordena a lista*/
							ordena(auxCarta, j);
						}
				}
		}
	}
	
	/** Ranking Two Pairs*/
	public void twoPairs(){
		
		for(Jogador j : jogadorLista){
			Carta[] c = j.getVetCarta();
			listCartaOrd.clear();
			auxCarta.clear();
			int cont=0;
			
			/** Percorre o vetor verificando se h� um par*/
			for(int i=0;i<6 && cont!=2;i++){
						
				/** Caso encontre um par, o par e adicionado na lista "auxCarta",
				 * incrementa "cont" e pula uma posic�o no vetor para verificar se 
				 * h� outro par no restante do vetor*/
				if(c[i].getValoreal() == c[i+1].getValoreal()){	
					auxCarta.add(c[i]);
					auxCarta.add(c[i+1]);
					 cont++;
					 i++;	 
				}	
					
				/** Se for encontrado dois pares ocorrer� um Two Pairs, status recebe 8*/
				if(cont==2){
					j.setStatus(8);
				
					/** Ordena a lista*/
					ordena(auxCarta, j);
				}
			}
		}
	}
	
	/** Ranking One Pairs*/
	public void onePairs(){
		
		for(Jogador j : jogadorLista){
			Carta[] c = j.getVetCarta();
			listCartaOrd.clear();
			auxCarta.clear();
						
			/** Percorre o vetor verificando se h� um par*/
			for(int i=0;i<6;i++){
								
				/** Se for encontrado um par, ocorrer� um One Pair, status recebe 9*/
				if(c[i].getValoreal() == c[i+1].getValoreal()){	
					j.setStatus(9);
					auxCarta.add(c[i]);
					auxCarta.add(c[i+1]);					
					i=6;
					
					/** Ordena a lista*/		
					ordena(auxCarta, j);
				}
			}
		}
	}
	
	/** Ranking High Card*/
	public void highCard(){
		
		for(Jogador j : jogadorLista){
			Carta[] c = j.getVetCarta();
			
			/** Verifica se a lista de Cartas cont�m sete Cartas, caso positivo
			 * a lista "auxCarta" recebe o valor da �ltima Carta, caso negativo
			 * ocorre que o jogador saiu da partida*/
			if(j.getCarta().size()==7){
			
				listCartaOrd.clear();
				auxCarta.clear();
			
				auxCarta.add(c[6]);
			
				/** Caso jogador permaneca at� a �ltima rodada e n�o tenha conseguido
				 * nenhuma sequencia, ocorrer� um High Card, status recebe 10*/
				j.setStatus(10);

				/** Ordena a lista*/
				ordena(auxCarta, j);
			}
		
			else{
				/** Caso jogador n�o permaneca at� �ltima rodada, status recebe 11
				 * e suas Cartas ser�o apenas mostradas*/
				j.setStatus(11);
			}
		}
	}
	
	/** @param Lista de Cartas, Jogador
	 * O m�todo ordenar� as Cartas para que as primeiras cartas 
	 * sejam aquelas que o jogador fez a sequencia*/
	public void ordena(List<Carta> auxCarta, Jogador j){
		
		/**Adiciona todos obejtos de auxCarta na lista listCartaOrd*/
		listCartaOrd.addAll(auxCarta);
			
			/**Verifica os objetos em comum entre a lista de cartas do Jogador com a lista auxCarta, se
			 * n�o for encontrado o objeto � adicionado na lista "listCartaOrd" */
			for(Carta carta : j.getCarta()){
				int x= 0;
				for(Carta cartaux : auxCarta){
					//JOptionPane.showConfirmDialog(null, cartaux.getValoreal()+cartaux.getNaipe());
					if(carta.equals(cartaux)){	x=1;	}
				}
				if(x==0){ listCartaOrd.add(carta);	}
			}
			
		/**Subscreve a lista principal de Cartas do Jogador para a lista listCartaOrd*/
		j.addnewCartas(listCartaOrd);
	}
	
	/** Executa todos poss�veis tipos de ranking, adicionando o mesmo
	 * caso ocorrer  */
	public void pesquisar(){
		highCard();
		onePairs();
		twoPairs();
		trinca();
		straight();
		flush();
		fullHouse();
		quadra();		
		straightFlush();
		royalFlush();
	}
	

}
