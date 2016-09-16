package excecoes;


/**
 * Classe para exce��o de Strings. Herda de Exception
 * 
 * @author Arthur Sampaio
 *
 */
public class ValorInvalidoException extends Exception{
	
	private static final long serialVersionUID = 1L;

	private static final String MENSAGEM = "Valor invalido";
	
	public ValorInvalidoException(){
		super(MENSAGEM);
	}
	
	public ValorInvalidoException(String mensagem){
		super(mensagem);
	}

}
