package Dados;

public class DicaAcao implements Dica {
	private String texto = new String();
	private char tipoAcao;
	
	//Inicia dica com seu tipo respectivo de ação, e também inicia seu texto
	public DicaAcao(String t) {
		tipoAcao = t.charAt(1);
		String s = t.substring(2);
		texto = s;
	}
	
	public String getTexto() {
		return texto;
	}
	
	public boolean acao() {
		return true;
	}
	//Retorna o tipo de ação que é essa DicaAcao
	public char getTipoAcao() {
		return tipoAcao;
	}
	//Retorna o número que está contido na estrução dessa DicaAcao
	public int numNaDica() {
		for(int i=0; i < texto.length(); i++) {
			String s = String.valueOf(texto.charAt(i));
			if(s.matches("[0-9]+")) {
				return Integer.parseInt(s);
			}
		}
		return 0;
	}
}
