package excecoes;

public class EmailInexistenteException extends SistemaException{
	
	private static final long serialVersionUID = 1L;
	private static final String MENSAGEM = "Email nao cadastrado.";
	
	public EmailInexistenteException(){
		super(MENSAGEM);
	}
	
	public EmailInexistenteException(String mensagem){
		super(mensagem);
	}

}
