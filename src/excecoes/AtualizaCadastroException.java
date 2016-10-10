package excecoes;

public class AtualizaCadastroException extends SistemaException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public AtualizaCadastroException(String erro){
		super("Erro na atualizacao do cadastro de Hospede." + erro);
	}

}
