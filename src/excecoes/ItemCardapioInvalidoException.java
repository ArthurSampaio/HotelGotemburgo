package excecoes;

public class ItemCardapioInvalidoException extends Exception{
	private static final long serialVersionUID = 1L;
	private static final String MENSAGEM = "Item Cardapio Invalido.";
	
	public ItemCardapioInvalidoException(){
		super(MENSAGEM);
	}
	
	public ItemCardapioInvalidoException(String mensagem){
		super(mensagem);
	}
}
