package usuario;

import jogo.Jogabilidade;
import jogo.Jogo;

public class Veterano implements TipoDeUsuario {
	public static final double DESCONTO_VETERANO = 0.8;
	private int bonus;

	public Veterano() {
		this.bonus = 15;
	}

	@Override
	public double compraJogo(Jogo jogo) {
		double custo = jogo.getPreco() * DESCONTO_VETERANO;
		return custo;
	}

	@Override
	public int recompensar(Jogo jogo){
		int recompensa = 0;
		if(jogo.getJogabilidades().contains(Jogabilidade.ONLINE)){
			recompensa += 10;
		}
		if(jogo.getJogabilidades().contains(Jogabilidade.COOPERATIVO)){
			recompensa += 20;
		}
		return recompensa;
		
	}

	@Override
	public int punir(Jogo jogo) {
		int punicao = 0;
		if(jogo.getJogabilidades().contains(Jogabilidade.COMPETITIVO)){
			punicao -= 20;
		}
		if(jogo.getJogabilidades().contains(Jogabilidade.OFFLINE)){
			punicao -= 20;
		}
		return punicao;	
	}
	
	
	public int getBonus(){
		return this.bonus;
	}
	
	@Override
	public String toString() {
		return "Jogador Veterano: ";
	}

}
