package excecoes;

public class CadastroVazioException extends SistemaException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public CadastroVazioException(String erro, String mensagem){
		super(erro + mensagem + " do(a) hospede nao pode ser vazio.");
	}

}
