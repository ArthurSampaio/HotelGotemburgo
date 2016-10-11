package excecoes;

public class CadastroPratoException extends SistemaException{
private static final long serialVersionUID = 1L;
	

	public CadastroPratoException(String atributo){
		super("Erro no cadastro do prato. " + atributo);
	}

}
