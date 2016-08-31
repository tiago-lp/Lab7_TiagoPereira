package excecoes;

public class TipoException extends LojaException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TipoException(){
		super("Ocorreu um erro de tipo.");
	}
	
	public TipoException(String msg){
		super(msg);
	}
}
