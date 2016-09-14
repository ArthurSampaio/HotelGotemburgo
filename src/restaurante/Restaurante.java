package restaurante;

import java.util.HashMap;
import java.util.Map;

import prato.ItemCardapio;
import prato.Prato;

public class Restaurante {
	private Map<String, ItemCardapio> cardapio;
	private FactoryPrato fabrica;
	public Restaurante(){
		this.cardapio = new HashMap<String, ItemCardapio>();
		this.fabrica = new FactoryPrato();
	}
	
	public ItemCardapio getItem(String nomeDoItem){
		return cardapio.get(nomeDoItem);
	}

	public void addItem(ItemCardapio item){
		cardapio.put(item.getNome(), item);
	}
	
	public void criaPrato(String nome, String descricao, double preco, String tipo) throws Exception{
		Prato prato = fabrica.criaPrato(nome, descricao, preco, tipo);
		addItem(prato);
	}
}