package excecoes;

public class RemocaoException extends SistemaException {
	private static final long serialVersionUID = 1L;
	
	
	
	
	public RemocaoException(String erro){
		super("Erro na remocao do Hospede." + erro);
	}

}
