package excecoes;

public class QuartoInvalidoException extends SistemaException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String MENSAGEM = "Quarto inválido.";
	
	public QuartoInvalidoException(){
		super(MENSAGEM);
	}
	
	public QuartoInvalidoException(String mensagem){
		super(mensagem);
	}
	

}
