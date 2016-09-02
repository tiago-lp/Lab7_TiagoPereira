package excecoes;

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
