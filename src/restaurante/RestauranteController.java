package restaurante;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import excecoes.AtributoInvalidoException;
import excecoes.StringInvalidaException;
import prato.ItemCardapio;
import prato.Prato;
import prato.Refeicao;

/**
 * Classe representando Controller do Restaurante.
 * 
 * @author marianams
 *
 */
public class RestauranteController {
	private Map<String, ItemCardapio> cardapio;

	private FactoryItemCardapio fabrica;

	/**
	 * Construtor de Restaurante Controller.
	 */
	public RestauranteController() {
		this.cardapio = new HashMap<String, ItemCardapio>();
		this.fabrica = new FactoryItemCardapio();
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
	 *            Adiciona um novo item ao cardapio.
	 */
	public void addItem(ItemCardapio item) {
		cardapio.put(item.getNome(), item);
	}

	/**
	 * 
	 * @param nome
	 *            Nome do prato a ser cadastrado.
	 * @param preco
	 *            Preco do prato
	 * @param descricao
	 *            Texto de descricao do prato.
	 * @throws Exception
	 *             Lanca excecao do metodo cria prato.
	 */
	public void cadastraPrato(String nome, double preco, String descricao) throws Exception {
		Prato prato = fabrica.criaPrato(nome, preco, descricao);
		addItem(prato);
	}

	/**
	 * 
	 * @param nome
	 *            Nome da refeicao a ser cadastrada.
	 * @param descricao
	 *            Descricao da refeicao.
	 * @param componentes
	 *            Pratos que vao compor a refeicao.
	 * @throws Exception
	 *             Lanca execao do metodo cria refeicao.
	 */
	public void cadastraRefeicao(String nome, String descricao, String componentes) throws Exception {
		ArrayList<Prato> pratos = criaArrayList(componentes);
		Refeicao refeicao = fabrica.criaRefeicao(nome, descricao, pratos);
		cardapio.put(nome, refeicao);
	}

	/**
	 * 
	 * @param nome
	 *            Nome da refeicao procurada.
	 * @param info
	 *            Informacao desejada, preco ou descricao de um item do
	 *            cardapio.
	 * @return Informacao requerida do item.
	 * @throws AtributoInvalidoException
	 */
	public String consultaRestaurante(String nome, String info) throws Exception {
		if (nome.trim().isEmpty() || nome == null){
			throw new StringInvalidaException("Erro na consulta do restaurante. Nome do prato esto vazio.");
		}
		if (info.equalsIgnoreCase("preco")) {
			return getPrecoItem(nome);
		} else if (info.equalsIgnoreCase("descricao")) {
			return getItem(nome).toString();
		} else {
			throw new AtributoInvalidoException();
		}
	}

	/**
	 * 
	 * @param nome
	 *            Nome do item do cardapio.
	 * @return O preco de um item do cardapio em formato String.
	 */
	private String getPrecoItem(String nome) {
		ItemCardapio item = this.cardapio.get(nome);
		String preco = String.format("R$%.2f", item.getPreco());
		return preco;

	}

	/**
	 * Metodo privado usado apenas no cadastro de uma refeicao no cardapio.
	 * 
	 * @param componentes
	 *            Pratos que irao compor uma refeicao completa.
	 * @return 
	 * 			  Array contendo os Pratos que irao compor a refeicao completa.
	 * @throws Exception
	 */
	private ArrayList<Prato> criaArrayList(String componentes) throws Exception {
		if (componentes.trim().isEmpty()){
			throw new Exception ("Erro no cadastro de refeicao. Componente(s) esta(o) vazio(s).");
		}
		String[] pratos = componentes.split(";");
		ArrayList<Prato> novosPratos = new ArrayList<Prato>();

		for (int i = 0; i < pratos.length; i++) {
			if (!cardapio.containsKey(pratos[i])) {
				throw new StringInvalidaException("Erro no cadastro de refeicao. So eh possivel cadastrar refeicoes com pratos ja cadastrados.");
			}
			Prato pratoNovo = (Prato) cardapio.get(pratos[i]);
			novosPratos.add(pratoNovo);
		}
		return novosPratos;
	}

}