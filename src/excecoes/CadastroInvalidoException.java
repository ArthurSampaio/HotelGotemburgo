package excecoes;

public class CadastroInvalidoException extends SistemaException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CadastroInvalidoException(String erro, String mensagem){
		super(erro + mensagem);
	}
	
}
