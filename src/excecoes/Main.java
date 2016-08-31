package excecoes;

import jogo.*;
import usuario.*;
import loja.*;
public class Main {

	public static void main(String[] args) throws LojaException {
		LojaController loja = new LojaController();
		
		Jogo mario = new Plataforma("Super Mario", 15);
		mario.setMaiorScore(5000);
		mario.setVezesJogadas(4);
		mario.setVezesConcluidas(2);
		System.out.println();
		Jogo joreuj = new Rpg("FF", 23);
		
		Usuario a = new Veterano("jeuj","ji.ja");
		a.cadastraJogo(mario);
		a.cadastraJogo(joreuj);
		
		loja.adicionaUsuario("Eu", "leonhart", "Veterano");
		loja.adicionaDinheiro("leonhart", 50000);
		loja.vendeJogo("leonhart", "Mario", 15, "offline, multiplayer, cooperativo", "plataforma");
		System.out.println(loja);
	}

}
