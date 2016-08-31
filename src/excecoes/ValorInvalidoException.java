package excecoes;

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
