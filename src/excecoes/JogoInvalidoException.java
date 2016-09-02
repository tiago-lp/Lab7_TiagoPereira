package excecoes;
/*115210912 - Tiago Lima Pereira: LAB 7 - Turma 3*/

/**
 * Classe que trata de excecoes para jogo invalido. 
 * @author Tiago Pereira
 */
public class JogoInvalidoException  extends LojaException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JogoInvalidoException(){
		super("Jogo invalido.");
	}
	
	public JogoInvalidoException(String msg){
		super(msg);
	}
}
