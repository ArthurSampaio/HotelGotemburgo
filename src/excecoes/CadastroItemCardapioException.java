package excecoes;

public class CadastroItemCardapioException extends SistemaException{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -7549269242575460603L;

	public CadastroItemCardapioException(String tipoItem, String erro){
		super("Erro no cadastro " + tipoItem + erro);
	}

}
