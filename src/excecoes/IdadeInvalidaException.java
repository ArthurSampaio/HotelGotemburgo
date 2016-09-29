package excecoes;

public class IdadeInvalidaException extends SistemaException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IdadeInvalidaException(String mensagem){
		super(mensagem + "A idade do(a) hospede deve ser maior que 18 anos.");
	}
	
	

}
