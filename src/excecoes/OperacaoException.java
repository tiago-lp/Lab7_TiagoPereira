package excecoes;

public class OperacaoException extends LojaException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public OperacaoException(){
		super("Erro em metodo.");
	}
	
	public OperacaoException(String msg){
		super(msg);
	}

}
