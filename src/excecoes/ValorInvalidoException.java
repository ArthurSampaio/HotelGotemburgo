package excecoes;


/**
 * Classe para excecaoo de Strings. Herda de Exception
 * 
 * @author Arthur Sampaio
 *
 */
public class ValorInvalidoException extends SistemaException{
	
	private static final long serialVersionUID = 1L;

	private static final String MENSAGEM = "Valor invalido";
	
	public ValorInvalidoException(){
		super(MENSAGEM);
	}
	
	public ValorInvalidoException(String mensagem){
		super(mensagem);
	}

}
