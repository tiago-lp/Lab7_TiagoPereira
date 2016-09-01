package usuario;

import java.util.HashSet;
import java.util.Set;

import excecoes.LojaException;
import excecoes.StringInvalidaException;
import excecoes.ValorInvalidoException;
import jogo.Jogabilidade;
import jogo.Jogo;

public class Noob implements TipoDeUsuario {
	public static final double DESCONTO_NOOB = 0.9;
	public static final String FIM_DE_LINHA = System.lineSeparator();
	private int bonus;
	
	public Noob(){
		this.bonus = 10;
	}

	@Override
	public double compraJogo(Jogo jogo) throws LojaException{
		double custo = jogo.getPreco() * DESCONTO_NOOB;
		return custo;
	}
	
	
	public int getBonus(){
		return this.bonus;
	}
	
	@Override
	public String toString() {
		return "Jogador Noob: ";
	}

	@Override
	public int recompensar(Jogo jogo)
			throws LojaException {
		int recompensa = 0;
		if(jogo.getJogabilidades().contains(Jogabilidade.OFFLINE)){
			recompensa += 30;
		}
		if(jogo.getJogabilidades().contains(Jogabilidade.MULTIPLAYER)){
			recompensa += 10;
		}
		return recompensa;
		
	}

	@Override
	public int punir(Jogo jogo)
			throws LojaException {
		int punicao = 0;
		if(jogo.getJogabilidades().contains(Jogabilidade.ONLINE)){
			punicao -= 10;
		}
		if(jogo.getJogabilidades().contains(Jogabilidade.COMPETITIVO)){
			punicao -= 20;
		}
		if(jogo.getJogabilidades().contains(Jogabilidade.COOPERATIVO)){
			punicao -= 50;
		}
		return punicao;
		
	}

}