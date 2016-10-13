package excecoes;

public class RemocaoException extends SistemaException {
	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6244235449030635448L;

	public RemocaoException(String erro){
		super("Erro na remocao do Hospede." + erro);
	}

}
