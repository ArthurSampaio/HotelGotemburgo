package excecoes;

public class EmailInexistenteException extends SistemaException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4815902590969032752L;
	private static final String MENSAGEM = "Email nao cadastrado.";
	
	public EmailInexistenteException(){
		super(MENSAGEM);
	}
	
	
	
	public EmailInexistenteException(String mensagem){
		super(mensagem);
	}

}
