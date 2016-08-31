package excecoes;

import jogo.*;
import usuario.*;
public class Main {

	public static void main(String[] args) throws LojaException {
		
		Jogo mario = new Plataforma("Super Mario", 15);
		mario.setMaiorScore(5000);
		mario.setVezesJogadas(4);
		mario.setVezesConcluidas(2);
		System.out.println();
		Jogo joreuj = new Rpg("FF", 23);
		
		Usuario a = new Veterano("jeuj","ji.ja");
		a.cadastraJogo(mario);
		a.cadastraJogo(joreuj);
		System.out.println(a);
	}

}
