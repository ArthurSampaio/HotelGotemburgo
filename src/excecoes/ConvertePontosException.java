package excecoes;


public class ConvertePontosException extends SistemaException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6644768488693266129L;
	/**
	 * 
	 */
	
	private static final String MESSAGE = "Conversao invalida.";
	
	public ConvertePontosException(){
		super(MESSAGE);
	}
	
	public ConvertePontosException(String message){
		super(message);
	}
	

}
