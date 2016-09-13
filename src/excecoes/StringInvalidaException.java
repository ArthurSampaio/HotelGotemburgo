package excecoes;

/**
 * Classe para exceção de Strings. Herda de Exception
 * 
 * @author Arthur Sampaio
 *
 */
public class StringInvalidaException extends Exception {
	

	private static final long serialVersionUID = 1L;

	private static final String MENSAGEM = "String nula ou vazia";
	
	public StringInvalidaException(){
		super(MENSAGEM);
	}
	
	public StringInvalidaException(String mensagem){
		super(mensagem);
	
	}
	
}
