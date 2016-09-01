package usuario;

import jogo.Jogo;
import excecoes.LojaException;

public interface TipoDeUsuario {
	double compraJogo(Jogo jogo) throws LojaException;
	void recompensar(String nomeJogo, int scoreObtido, boolean zerou) throws LojaException;
	void punir(String nomeJogo, int scoreObtido, boolean zerou) throws LojaException;
	int getBonus();
}
