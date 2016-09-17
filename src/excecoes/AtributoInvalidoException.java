package excecoes;

public class AtributoInvalidoException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private static final String MENSAGEM = "Atributo invalido.";
	
	public AtributoInvalidoException(){
		super(MENSAGEM);
	}
	
	public AtributoInvalidoException(String mensagem){
		super(mensagem);
	}

}