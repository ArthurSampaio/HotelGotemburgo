package excecoes;

public class DescricaoItemCardapioException extends SistemaUncheckedException {
	
private static final long serialVersionUID = 1L;
	
	
	public DescricaoItemCardapioException(){
		super("Descricao do prato esta vazia.");
	}


}
