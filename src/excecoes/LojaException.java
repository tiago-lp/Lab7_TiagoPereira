package excecoes;

public class LojaException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LojaException(){
		super("Ocorreu um erro no sistema.");
	}
	
	public LojaException(String msg){
		super(msg);
	}

}

