package excecoes;

public class AtributoClienteException extends SistemaUncheckedException {
	
	private static final long serialVersionUID = 1L;
	
	
	public AtributoClienteException(String atributo){
		super(atributo + " do(a) hospede nao pode ser vazio.");
	}

}
