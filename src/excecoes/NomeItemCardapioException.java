package excecoes;

public class NomeItemCardapioException extends SistemaUncheckedException {
	
	private static final long serialVersionUID = 1L;
	
	
	public NomeItemCardapioException(){
		super("Nome do prato esta vazio.");
	}


}