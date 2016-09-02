package excecoes;
/*115210912 - Tiago Lima Pereira: LAB 7 - Turma 3*/
/**
 * Classe para excecoes relacionados a Usuario invalido.
 * 
 * @author Tiago Pereira
 */
public class UsuarioInvalidoException extends LojaException{

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