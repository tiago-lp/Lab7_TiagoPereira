package usuario;

import jogo.Jogo;

public interface TipoDeUsuario {
	double compraJogo(Jogo jogo);
	int recompensar(Jogo jogo);
	int punir(Jogo jogo);
	int getBonus();
}
