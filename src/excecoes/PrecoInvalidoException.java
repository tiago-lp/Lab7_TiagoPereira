package excecoes;
/*115210912 - Tiago Lima Pereira: LAB 7 - Turma 3*/

/**
 * Classe para excecoes de preco.
 * @author Tiago Pereira
 *
 */
public class PrecoInvalidoException extends LojaException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PrecoInvalidoException(){
		super("Preco invalido.");
	}

	public PrecoInvalidoException(String msg) {
		super(msg);
	}

}
