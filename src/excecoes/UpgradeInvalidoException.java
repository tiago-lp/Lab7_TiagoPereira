package excecoes;
/*115210912 - Tiago Lima Pereira: LAB 7 - Turma 3*/

/**
 * Classe para excecoes no upgrade de usuario. 
 * @author Tiago Pereira
 *
 */
public class UpgradeInvalidoException extends LojaException {

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
