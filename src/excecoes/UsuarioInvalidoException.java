package excecoes;
/*115210912 - Tiago Lima Pereira: LAB 7 - Turma 3*/
/**
 * SubClasse de SteamException para tratamento de erros relacionados a Usuario.
 * 
 * @author Tiago Pereira
 */
public class UsuarioInvalidoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsuarioInvalidoException(){
		super("Usuario invalido.");
	}
	
	public UsuarioInvalidoException(String msg){
		super(msg);
	}
}