package excecoes;

public class UpgradeInvalidoException extends OperacaoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UpgradeInvalidoException(){
		super("Ocorreu um erro no upgrade.");
	}
	
	public UpgradeInvalidoException(String msg) {
		super(msg); 
	}

}
