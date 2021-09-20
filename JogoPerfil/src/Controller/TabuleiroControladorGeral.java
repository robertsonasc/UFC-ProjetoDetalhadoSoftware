package Controller;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import Dados.Carta;
import Dados.Dica;

public class TabuleiroControladorGeral {
	private static int qtdJogadores;
	private static int jogadorDaVez = 1;
	
	private static Carta cartas[] = new Carta[5];
	private static int indiceCartaAtual = -1;
	
	private static int dicasJaSelec[] = new int[20];
	private static int indiceDicasJaSelec = 0;
	
	private static int peoes[] = new int[6];
	
	private static int mediador;
	
	private static int fimPartida = 30;
	
	//Deixa todos os jogadores no início.
	public static void zeraPosiJog() {
		for(int i=0; i < 3; i++)
			peoes[i] = 0;
		
	}
	//Seta quantidade de jogadores que irão participar da partida.
	public static boolean setQtdJogadores(String q) {
		q = q.trim();
		if(q.length() == 1 && q.matches("[1-6]+")) {
			if(Integer.parseInt(q) <= 6 && Integer.parseInt(q) >= 1) {
				qtdJogadores = Integer.parseInt(q);
				return true;
			}
		}
		
		System.out.println("Erro: informe um número de jogadores de 1 a 6.");
		return false;
	}
	//Simula a ação de puxar uma nova carta, aumentando em 1 o indice da carta atual (inciceCartaAtual). E rotorna essa carta.
	public static Carta puxarNovaCarta() {
		indiceCartaAtual++;
		if(indiceCartaAtual > 4)
			indiceCartaAtual = 0;
		return cartas[indiceCartaAtual];
	}
	//Inicializa dicas
	public static void inicializaDicasSelec(){
		dicasJaSelec = new int[20];
		indiceDicasJaSelec = 0;
		
	}
	//Inicializar posição do mediador
	public static void inicializaMediador() {
		mediador = 0;
	}
	//Monta o vetor de cartas, com base em uma arquivo txt.
	public static void inicializaCartas() {
		Path caminho = Paths.get("<Caminho do arquivo cartas.txt>"); //no meu computador ficou "C:/Users/rober/Documents/MEGAsync Downloads/1. UFC/SEMESTRE ATUAL/"
											//+ "Projeto Detalhado de Software/Trabalho final/Entrega 3/cartas.txt"
		try {

			byte[] texto = Files.readAllBytes(caminho);
			String palavras = new String(texto, StandardCharsets.UTF_8);
			
			for(int iPalavras = 0; iPalavras < palavras.length(); iPalavras++) {
				for(int iCartas = 0; iCartas < 5; iCartas++) {
					String oQSou = new String();
					while(palavras.charAt(iPalavras) != '-') {
						oQSou += palavras.charAt(iPalavras);
						iPalavras++;
					}
					iPalavras++;
					cartas[iCartas] = new Carta();
					cartas[iCartas].setOqSou(oQSou);
	
					
					String categoria = new String();
					while(palavras.charAt(iPalavras) != ':') {
						categoria += palavras.charAt(iPalavras);
						iPalavras++;
					}
					iPalavras += 3;
					cartas[iCartas].setCategoria(categoria);
					
					for(int j=0; j < 20; j++) {
						String strDica = new String();
						while(palavras.charAt(iPalavras) != ';') {
							strDica += palavras.charAt(iPalavras);
							iPalavras++;
						}
						iPalavras += 3;
						Dica dica = DicasController.criaDica(strDica);
						cartas[iCartas].setDicaNoI(j, dica);
					}
				}
			}
		
			
			
		}catch(Exception erro) {
			System.out.println("Erro: arquivo fonte de cartas não encontrado.");
			System.exit(-1);
		}
	}
	//Imprime as dicas que já foram selecionadas
	public static void dicasJaSelec() {
		String str[] = new String[22];
		if(indiceDicasJaSelec == 0) {
			System.out.println("[ Nenhuma dica foi escolhida ainda. ]");
			return;
		}
		str[0] = "[ ";
		for(int i=1; i < 21; i++) {
			if(i == 20)
				str[i] = "-";
			else
				str[i] = "- ";
		}
		str[21] = " ]";
		for(int i=0; i < indiceDicasJaSelec; i++) {
			if(dicasJaSelec[i] == 20)
				str[dicasJaSelec[i]] = Integer.toString(dicasJaSelec[i]);
			else	
				str[dicasJaSelec[i]] = Integer.toString(dicasJaSelec[i]) + " ";
		}
		String aux = new String();
		for(int i=0; i < 22; i++) {
			aux += str[i];
		}
		System.out.println(aux);
	}
	//Retorna o vetor de dicas já selecionadas
	public static int[] getDicasJaSelec() {
		return dicasJaSelec;
	}
	//Retorna a carta que está sendo utilizada na jogada
	public static Carta getCartaAtual() {
		return cartas[indiceCartaAtual];
	}
	//Adiciona um id de dica que já foi selecionada
	public static void addDicaJaSelec(int dicaInt) {
		dicasJaSelec[indiceDicasJaSelec] = dicaInt;
		indiceDicasJaSelec++;
	}
	//Retorna a quantidade de jogadores da partida
	public static int getQtdJogadores() {
		return qtdJogadores;
	}
	//Retorna o jogador da vez
	public static int getJogadorDaVez() {
		return jogadorDaVez;
	}
	//Seta o jogador da vez
	public static void setJogadorDaVez(int jog) {
		if(jog > qtdJogadores) {
			jogadorDaVez = 1;
			return;
		}
		jogadorDaVez = jog;
	}
	//Avança o peao do jogador da vez com o valor recebido. Retorna 1 se o jogador da vez ganhou a partida
	public static int avancaPeao(int valor) {
		peoes[jogadorDaVez-1] += valor;
		if(peoes[jogadorDaVez-1] >= fimPartida-1) {
			return 1;
		}
		return 0;
	}
	//Avanca o peao do jogador recebido, a quantidade de vezes do valor recebido. Retorna 1 se o jogador recebido ganhou a partida.
	public static int avancaPeaoDoJogadorNoi(int intJogador, int qtdAvancos) {
		peoes[intJogador-1] += qtdAvancos;
		if(peoes[intJogador-1] >= fimPartida-1) {
			return 1;
		}
		return 0;
	}
	//Recua o peao do jogador recebido, qtdRecuo vezes
	public static void voltaPeaoDoJogadorNoi(int intJogador, int qtdRecuo) {
		peoes[intJogador-1] -= qtdRecuo;
		if(peoes[intJogador-1] < 0) {
			peoes[intJogador-1] = 0;
		}
	}
	//Retorna a quantidade de dicas já selecionadas
	public static int getIndiceDicasJaSelec() {
		return indiceDicasJaSelec;
	}
	//Avança o mediador a quantidade de valor. Retorna 1 se o mediador ganhou a partida. 
	public static int avancaMediador(int valor) {
		mediador += valor;
		if(mediador >= fimPartida-1) {
			return 1;
		}
		return 0;
	}
	//Recua qtdRecuo vezes a posição de mediador na partida
	public static void voltaMediador(int qtdRecuo) {
		mediador -= qtdRecuo;
		if(mediador < 0) {
			mediador = 0;
		}
	}
	//Retorna onde está o peao do jogador recebido
	public static int getPeaoJogador(int indiceJogador) {
		return peoes[indiceJogador];
	}
	//Retorna o valor da linha de chegada do jogo.
	public static int getFimPartida() {
		return fimPartida;
	}
	//Retorna a posição que se encontra o mediador na partida.
	public static int getMediador() {
		return mediador;
	}
}
