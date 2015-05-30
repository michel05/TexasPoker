package texas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.JOptionPane;


import control.Classificacao;



import model.Carta;
import model.Jogador;

/**
 * @author Michel
 *
 */
public class Game {

	
	public static void main(String[] args) {
		
		
		
		int x = Integer.parseInt(JOptionPane.showInputDialog("Quantos jogadores irão jogar?"));
		
		/**Lista auxiliar para cartas da mesa */
		List<Carta> cartaListaMesa = new ArrayList<Carta>();
		/**Lista principal dos jogadores*/
		List<Jogador> jogadorLista = new ArrayList<Jogador>();
		/**Lista auxiliar para os jogadores*/
		List<Jogador> jogadorfora = new ArrayList<Jogador>();
		Carta b = new Carta();
		String letrarand;
		String naiperand;
		
		
		/** Puxa duas cartas randômicas do baralho para cada jogador
		 * e imprime na tela*/
		StringBuilder sbjog = new StringBuilder();
		for(int i=1;i<=x;i++){
			
			Jogador jogador = new Jogador();
			jogadorLista.add(jogador);
				
			/** Sorteia duas cartas*/
			sbjog.append("\nJogador "+ i+ ", cartas: ");
			for(int j=1;j<=2;j++){
				
					Carta carta = new Carta();
					
					/** verifica se as novas cartas sorteadas são únicas*/
					do{
						letrarand = Character.toString(b.letraRandom());
						naiperand = Character.toString(b.naipeRandom());
						carta.setValor(letrarand);
						carta.setNaipe(naiperand);
						} while(b.verificaCarta(jogadorLista, letrarand, naiperand)==false);
					/** Adiciona a carta sorteada na lista de carta principal do jogador*/	
					jogador.addCarta(carta);					
					sbjog.append(carta.getValoreal()+carta.getNaipe()+ " ");
			}
		}
		/** Imprime as cartas dos jogadores*/
		JOptionPane.showMessageDialog(null, sbjog.toString());
		
			
		/** Sorteia mais cinco cartas para mesa, e para carta é feita uma verificacão
		 * se todos jogadores aceitaram as apostas, caso positivo a carta é puxada, senão
		 * é pedido qual jogador saiu da partida*/
		int n;
		for(int i=1;i<=5;i++){
			
			
			/** Verifica apostas*/
			if(jogadorLista.size()!=1){ 
				int m = JOptionPane.showConfirmDialog(null, "Todos aceitaram as apostas?"); 
			
			if(m!=0){
				do{
				int id = Integer.parseInt(JOptionPane.showInputDialog("Qual jogador saiu do jogo?"));
				/**Caso o jogador tenha saido do jogo, o jogador é adicionado em uma lista auxiliar
				 * "jogadorfora" para ser impresso posteriormente, também o jogador que saiu é excluido 
				 * da lista de jogadores principal */
				jogadorfora.add(jogadorLista.get(id-1));
				jogadorLista.remove(id-1);
				
				/**Mostra quantos Jogadores restaram */
				JOptionPane.showMessageDialog(null, "Jogadores restantes no jogo:" +jogadorLista.size());
				
				/** */
				if(jogadorLista.size()==1){ n=0; }
				else{
					n = JOptionPane.showConfirmDialog(null, "Todos aceitaram as apostas?");
				}
				
				}while(n!=0);
				
			}	
				
			}
			
			
				/** Puxa uma carta no baralho*/
				Carta c = new Carta();
				
				do{
					letrarand = Character.toString(b.letraRandom());
					naiperand = Character.toString(b.naipeRandom());
					
					c.setValor(letrarand);
					c.setNaipe(naiperand);
					} while(b.verificaCarta(jogadorLista, letrarand, naiperand)==false);
				
				/**Adiciona a carta sorteada em uma lista auxiliar "cartaListaMesa" para
				 * ser usada posteriormente*/
				cartaListaMesa.add(c);
				
				/** Mostra as cartas da Mesa de acordo que são sorteadas*/
				StringBuilder mesaCartas = new StringBuilder();
				
				mesaCartas.append("Cartas da Mesa:\n");
				for(Carta mesa : cartaListaMesa){
					mesaCartas.append(" " +mesa.getValor()+mesa.getNaipe());
				}
				
				JOptionPane.showMessageDialog(null, mesaCartas.toString());
				
				
				
				/** A carta sorteada da mesa é adicionada na lista de cartas principal de cada jogador,
				 * 	para que o mesmo tenha acesso a ela*/
				for(Jogador j : jogadorLista){	
					j.addCarta(c);							
				}
				
				
		}
		

		/**Submete todos jogadores para uma analise de suas cartas e os rankings que obtiveram */
		Classificacao rank = new Classificacao(jogadorLista);
		rank.pesquisar();
		
		/**Ordena a lista de Jogadores pelo Ranking/Status */
		Collections.sort(jogadorLista, new Comparator<Jogador>(){  
			public int compare(Jogador a, Jogador b){  
			         return a.getStatus() > b.getStatus() ? 1 :  
			                   ( a.getStatus() < b.getStatus() ? -1 : 0);                      
			}   
			});  
		

		/**Mostra na tela as cartas dos jogadores que sairam do jogo anteriormente*/
		StringBuilder statsfora = new StringBuilder();
		for(Jogador j : jogadorfora){
			
			for(Carta carta : j.getCarta()){
				statsfora.append(carta.getValor()+carta.getNaipe()+" ");
			}
			statsfora.append("\n");
			
		}
		
		
		/**Mostra na tela as cartas do jogadores juntamente com as especificas combinacoes e seus rankings*/
		StringBuilder stats = new StringBuilder();
		int aux = 0;
		for(Jogador j : jogadorLista){
			
			for(Carta carta : j.getCarta()){
				stats.append(carta.getValor()+carta.getNaipe()+" ");
			}
				stats.append(" "+j.getRank());
				
			if(aux==0){
				if(jogadorLista.size()>1){
					if(jogadorLista.get(0).getStatus()==jogadorLista.get(1).getStatus()){
						stats.append(" (Empate) ");
					}
					else{
						stats.append(" (Winner)");
					}
				}
				else{
					stats.append(" (Winner)");
				}
					
				
								
			}
			stats.append("\n");
			aux++;
	}
		
		JOptionPane.showMessageDialog(null, "Ranking dos jogadores : \n" +stats.toString()+ statsfora.toString());
	

	}
}
