package excecoes;

/**
 * Classe para excecao de Strings.
 * 
 * @author Arthur Sampaio
 *
 */
public class StringInvalidaException extends SistemaException {
	

	private static final long serialVersionUID = 1L;

	private static final String MENSAGEM = "String nula ou vazia";
	
	public StringInvalidaException(){
		super(MENSAGEM);
	}
	
	public StringInvalidaException(String mensagem){
		super(mensagem);
	
	}
	
}
