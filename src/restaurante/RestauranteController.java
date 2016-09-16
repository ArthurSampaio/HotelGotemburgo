package restaurante;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import excecoes.AtributoInvalidoException;
import prato.ItemCardapio;
import prato.Prato;
import prato.Refeicao;

/**Classe representando Controller do Restaurante.
 * 
 * @author marianams
 *
 */
public class RestauranteController {
	private Map<String, ItemCardapio> cardapio;

	private FactoryPrato fabrica;

	/**
	 * Construtor de Restaurante Controller.
	 */
	public RestauranteController() {
		this.cardapio = new HashMap<String, ItemCardapio>();
		this.fabrica = new FactoryPrato();
	}
	
	
	/**
	 * 
	 * @param nomeDoItem
	 * @return Objeto que eh um item do cardapio
	 */
	public ItemCardapio getItem(String nomeDoItem) {
		return cardapio.get(nomeDoItem);
	}
	/**
	 * 
	 * @param item
	 */
	public void addItem(ItemCardapio item) {
		cardapio.put(item.getNome(), item);
	}

	public void cadastraPrato(String nome, double preco, String descricao) throws Exception {
		Prato prato = fabrica.criaPrato(nome, preco, descricao);
		addItem(prato);
	}

	public void cadastraRefeicao() {

	}

	public String consultaRestaurante(String nome, String info) throws Exception {
		if (info.equalsIgnoreCase("preco")) {
			return getPrecoItem(nome);
		} else if (info.equalsIgnoreCase("descricao")) {
			return getItem(nome).toString();
		} else {
			throw new AtributoInvalidoException();
		}
	}

	private String getPrecoItem(String nome) {
		ItemCardapio item = this.cardapio.get(nome);
		String preco = String.format("R$%.2f", item.getPreco());
		return preco;

	}

	public void cadastraRefeicao(String nome, String descricao, String componente) throws Exception {

		String[] pratos = componente.split(";");
		if (pratos.length < 3 || pratos.length > 4) {
			throw new Exception(
					"Erro no cadastro de refeicao completa. Uma refeicao completa deve possuir no minimo 3 e no maximo 4 pratos.");
		}
		ArrayList<Prato> novosPratos = new ArrayList<Prato>();

		Refeicao novaRefeicao = new Refeicao(nome, descricao, novosPratos);

		for (int i = 0; i < pratos.length; i++) {
			if (!cardapio.containsKey(pratos[i])) {
				throw new Exception(pratos[i] + "nao existe no cardapio");
			}
			
			Prato pratoNovo = (Prato) cardapio.get(pratos[i]);

			novosPratos.add(pratoNovo);

		}
		
		cardapio.put(nome, novaRefeicao);

	}

}
