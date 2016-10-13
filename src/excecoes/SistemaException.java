package excecoes;

public class SistemaException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8166211673685485696L;
	private static final String MENSAGEM = "Erro no sistema.";
	
	public SistemaException(){
		super(MENSAGEM);
	}
	
	public SistemaException(String mensagem){
		super(mensagem);
	}
}
