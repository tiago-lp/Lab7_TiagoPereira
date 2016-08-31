package excecoes;

import jogo.*;
public class Main {

	public static void main(String[] args) throws StringInvalidaException, PrecoInvalidoException, ValorInvalidoException {
		
		Jogo mario = new Plataforma("Super Mario", 15);
		mario.setMaiorScore(5000);
		mario.setVezesJogadas(4);
		mario.setVezesConcluidas(2);
		System.out.println();
	}

}
