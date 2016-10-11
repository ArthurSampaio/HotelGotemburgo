package excecoes;

public class FormatoInvalidoException extends SistemaUncheckedException {
	private static final long serialVersionUID = 1L;
	
	public FormatoInvalidoException(String atributo) {
		super(atributo + " do(a) hospede esta invalido.");
		
	}

	

	

}
