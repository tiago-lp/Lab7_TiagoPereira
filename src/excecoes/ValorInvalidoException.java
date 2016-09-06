package excecoes;
/*115210912 - Tiago Lima Pereira: LAB 7 - Turma 3*/

/**
 * Classe para excecoes de valores.
 * @author Tiago Pereira
 *
 */
public class ValorInvalidoException extends LojaException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ValorInvalidoException(){
		super("Ocorreu um erro de valor.");
	}

	public ValorInvalidoException(String msg) {
		super(msg);
	}

}
