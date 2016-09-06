package excecoes;
/*115210912 - Tiago Lima Pereira: LAB 7 - Turma 3*/

/**
 * Classe para excecoes de String.
 * @author Tiago Pereira
 *
 */
public class StringInvalidaException extends LojaException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public StringInvalidaException(){
		super("String invalida.");
	}

	public StringInvalidaException(String msg) {
		super(msg);
	}

}
