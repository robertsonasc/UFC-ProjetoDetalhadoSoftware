package Dados;

public class DicaNormal implements Dica {
	private String texto = new String();
	
	//Inicia dica com a string recebida
	public DicaNormal(String t) {
		texto = t;
	}
	
	public String getTexto() {
		return texto;
	}
	
	public boolean acao() {
		return false;
	}
}
