package excecoes;

public class FormatoInvalidoException extends AtributoClienteException {
	private static final long serialVersionUID = 1L;
	
	public FormatoInvalidoException(String atributo) {
		super(atributo + " do(a) hospede esta invalido.");
		
	}

	

	

}
