package excecoes;
/*115210912 - Tiago Lima Pereira: LAB 7 - Turma 3*/

/**
 * Subclasse de SteamException para tratar exceptions relacionadas ao objeto jogo.
 * 
 * @author Tiago Pereira
 */
public class JogoInvalidoException  extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JogoInvalidoException(){
		super("Jogo invalido.");
	}
	
	public JogoInvalidoException(String msg){
		super(msg);
	}
}
