package usuario;

import jogo.Jogo;
import excecoes.LojaException;

public interface TipoDeUsuario {
	double compraJogo(Jogo jogo) throws LojaException;
	int recompensar(Jogo jogo) throws LojaException;
	int punir(Jogo jogo) throws LojaException;
	int getBonus();
}
