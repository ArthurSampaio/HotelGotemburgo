package excecoes;

public class QuartoInvalidoException extends SistemaException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6136829500992075958L;
	/**
	 * 
	 */
	private static final String MENSAGEM = "Quarto inválido.";
	
	public QuartoInvalidoException(){
		super(MENSAGEM);
	}
	
	public QuartoInvalidoException(String mensagem){
		super(mensagem);
	}
	

}
