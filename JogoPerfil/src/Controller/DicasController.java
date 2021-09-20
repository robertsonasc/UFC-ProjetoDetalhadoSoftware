package Controller;

import Dados.Dica;
import Dados.DicaAcao;
import Dados.DicaNormal;

public class DicasController {
	//Cria dicas do tipo DicaAcao ou DicaNormal, possibilitando a utilização do polimorfismo no programa
	public static Dica criaDica(String str) {
		if(str.charAt(0) == '#') {
			return new DicaAcao(str);
		}
		return new DicaNormal(str);
	}
}
