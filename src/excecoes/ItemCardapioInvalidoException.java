package excecoes;

public class ItemCardapioInvalidoException extends SistemaException{
	private static final long serialVersionUID = 1L;
	private static final String MENSAGEM = "Item Cardapio Invalido.";
	
	public ItemCardapioInvalidoException(){
		super(MENSAGEM);
	}
	
	public ItemCardapioInvalidoException(String mensagem){
		super(mensagem);
	}
	
	
	public ItemCardapioInvalidoException(String atributo, String erro){
		super(atributo+ erro);
	}
}
