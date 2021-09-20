package Dados;

public class Carta {
	private String categoria;
	private String oQsou;
	private Dica dicas[] = new Dica[20];
	
	public Carta() {}
	
	//Inicializa a carta com o vetor de dicas, o que ele é, e seu tipo.
	public Carta(String c, String oq, Dica dicas[]) {
		categoria = c;
		oQsou = oq;
		this.dicas = dicas;
	}
	//Retorna a dica que corresponde a posição i do vetor de dicas.
	public Dica getDicaNoi(int i) {
		if(i >= 0 && i < dicas.length && dicas[i] != null) {
			return dicas[i];
		}
		return null;
	}
	//Retorna o que a carta é.
	public String getOqSou() {
		return oQsou;
	}
	//Retorna a categoria da carta.
	public String getCategoria() {
		return categoria;
	}
	//Seta o que é a carta.
	public void setOqSou(String str) {
		oQsou = str;
	}
	//Seta a categoria da carta.
	public void setCategoria(String str) {
		categoria = str;
	}
	//Insere uma dica na posição i do vetor de dicas.
	public void setDicaNoI(int i, Dica dic) {
		dicas[i] = dic;
	}
}





















