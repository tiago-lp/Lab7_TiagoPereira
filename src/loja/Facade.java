package loja;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import easyaccept.EasyAccept;
import excecoes.PrecoInvalidoException;
import excecoes.StringInvalidaException;
import excecoes.UpgradeInvalidoException;
import excecoes.ValorInvalidoException;
import jogo.Jogabilidade;
import jogo.Jogo;
import jogo.Luta;
import jogo.Plataforma;
import jogo.Rpg;
import usuario.Noob;
import usuario.Usuario;
import usuario.Veterano;

public class Facade {
	public static final String FIM_DE_LINHA = System.lineSeparator();
	private List<Usuario> meusUsuarios;
	private HashMap<String, Jogabilidade> mapJogabildades;

	public Facade() {
		this.meusUsuarios = new ArrayList<Usuario>();
		this.initializeMap();
	}

	public void adicionaUsuario(String nome, String login) {
		try {
			Usuario novoUser = new Noob(nome, login);
			meusUsuarios.add(novoUser);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void vendeJogo(String jogoNome, double preco, String jogabilidades, String estiloJogo, String loginUser) {

		try {
			Usuario buscado = this.buscaUsuario(loginUser);
			Set<Jogabilidade> tiposJogabilidades = this.createJogabilidades(jogabilidades);
			Jogo jogoVendido = this.criaJogo(jogoNome, preco, tiposJogabilidades, estiloJogo);
			buscado.compraJogo(jogoVendido);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void registraJogada(String login, String nomeJogo, int score, boolean venceu) {
		try {
			Usuario usr = this.buscaUsuario(login);
			usr.registradaJogada(nomeJogo, score, venceu);
		} catch (Exception e) {
			e.getMessage();
		}

	}

	public void adicionaCredito(String login, double credito) {
		try {
			if (credito < 0) {
				throw new ValorInvalidoException("Credito nao pode ser negativo");
			}
			Usuario user = this.buscaUsuario(login);
			user.setCredito(user.getCredito() + credito);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public Usuario buscaUsuario(String login) {
		Usuario buscado = null;

		try {
			for (int i = 0; i < meusUsuarios.size(); i++) {
				if (meusUsuarios.get(i).getLogin().equals(login)) {
					buscado = meusUsuarios.get(i);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return buscado;
	}

	public void upgrade(String login) throws Exception {
		Usuario antigo = this.buscaUsuario(login);
		if (antigo instanceof Veterano) {
			throw new UpgradeInvalidoException("Upgrade impossivel de ser realizado, usuario ja eh veterano");
		} else if (antigo.getXp2() < 1000) {
			throw new UpgradeInvalidoException("Impossivel realizar upgrade, quantidade de x2p insuficiente!");
		}
		Usuario novo = new Veterano(antigo.getNome(), antigo.getLogin());
		novo.setCredito(antigo.getCredito());
		novo.setXp2(antigo.getXp2());
		novo.setMeusJogos(antigo.getMeusJogos());
		int index = meusUsuarios.indexOf(antigo);
		meusUsuarios.set(index, novo);

	}

	public double confereCredito(String login) {
		try {
			Usuario procurado = this.buscaUsuario(login);
			return procurado.getCredito();
		} catch (Exception e) {
			e.getMessage();
		}
		return 0;
	}

	public String informacaoUsuarios() {
		String myString = "=== Central P2-CG ===" + FIM_DE_LINHA + FIM_DE_LINHA;
		for (int i = 0; i < meusUsuarios.size(); i++) {
			myString += meusUsuarios.get(i).toString() + FIM_DE_LINHA;
		}
		return myString;
	}

	public int getX2p(String login) {
		Usuario buscado = this.buscaUsuario(login);
		return buscado.getXp2();
	}

	private Jogo criaJogo(String jogoNome, double preco, Set<Jogabilidade> tiposJogabilidades, String estiloJogo)
			throws StringInvalidaException, PrecoInvalidoException {

		String estilo = estiloJogo.toLowerCase();
		if (estilo.equals("rpg")) {
			return new Rpg(jogoNome, preco, tiposJogabilidades);
		} else if (estilo.equals("plataforma")) {
			return new Plataforma(jogoNome, preco, tiposJogabilidades);
		} else if (estilo.equals("luta")) {
			return new Luta(jogoNome, preco, tiposJogabilidades);
		} else {
			return null;
		}
	}

	private Set<Jogabilidade> createJogabilidades(String names1) {
		Set<Jogabilidade> jogabilidades = new HashSet<Jogabilidade>();

		String[] listofNames = names1.split(",");

		for (int i = 0; i < listofNames.length; i++) {
			String element = listofNames[i].toUpperCase();
			if (element != null) {
				Jogabilidade tipojogabilidade = mapJogabildades.get(element);
				jogabilidades.add(tipojogabilidade);
			}
		}

		return jogabilidades;

	}

	private void initializeMap() {
		this.mapJogabildades = new HashMap<String, Jogabilidade>();
		mapJogabildades.put("ONLINE", Jogabilidade.ONLINE);
		mapJogabildades.put("OFFLINE", Jogabilidade.OFFLINE);
		mapJogabildades.put("COMPETITIVO", Jogabilidade.COMPETITIVO);
		mapJogabildades.put("COOPERATIVO", Jogabilidade.COOPERATIVO);
		mapJogabildades.put("MULTIPLAYER", Jogabilidade.MULTIPLAYER);

	}

	public static void main(String[] args) {
		args = new String[] { "loja.Facade", "acceptance_test/us1.txt", "acceptance_test/us2.txt",  "acceptance_test/us3.txt" };
		EasyAccept.main(args);

	}

}
