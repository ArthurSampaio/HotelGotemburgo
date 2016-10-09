package excecoes;

public class CadastroRefeicaoException extends SistemaException {

	private static final String MESSAGE = "Erro no cadastro de refeicao. ";
	
	public CadastroRefeicaoException(String m){
		super(MESSAGE + m);
	}
	
	public CadastroRefeicaoException(){
		super(MESSAGE);
	}
}
