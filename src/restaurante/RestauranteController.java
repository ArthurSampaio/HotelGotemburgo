package restaurante;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import excecoes.AtributoInvalidoException;
import excecoes.ItemCardapioInvalidoException;
import excecoes.SistemaException;
import excecoes.StringInvalidaException;
import prato.Comparador;
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
	private List<ItemCardapio> cardapio;

	private FactoryItemCardapio fabrica;

	/**
	 * Construtor de Restaurante Controller.
	 */
	public RestauranteController() {
		this.cardapio = new ArrayList<ItemCardapio>();
		this.fabrica = new FactoryItemCardapio();
	}

	/**
	 * 
	 * @param nomeDoItem
	 * @return Objeto que eh um item do cardapio
	 * @throws ItemCardapioInvalidoException 
	 */
	public ItemCardapio getItem(String nomeDoItem) throws ItemCardapioInvalidoException {
		for (int i = 0; i < cardapio.size(); ++i){
			if (cardapio.get(i).getNome().equals(nomeDoItem)){
				return cardapio.get(i);
			}
		}
		throw new ItemCardapioInvalidoException("Item nao existe no cardapio.");
	}

	/**
	 * 
	 * @param item
	 *            Adiciona um novo item ao cardapio.
	 */
	public void addItem(ItemCardapio item) {
		cardapio.add(item);
	}

	/**
	 * 
	 * @param nome
	 *            Nome do prato a ser cadastrado.
	 * @param preco
	 *            Preco do prato
	 * @param descricao
	 *            Texto de descricao do prato.
	 * @throws SistemaException
	 *             Lanca excecao do metodo cria prato.
	 */
	public void cadastraPrato(String nome, double preco, String descricao)
			throws SistemaException {
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
	 * @throws SistemaException
	 *             Lanca execao do metodo cria refeicao.
	 */
	public void cadastraRefeicao(String nome, String descricao,
			String componentes) throws SistemaException {
		ArrayList<Prato> pratos = criaArrayList(componentes);
		Refeicao refeicao = fabrica.criaRefeicao(nome, descricao, pratos);
		cardapio.add(refeicao);
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
	public String consultaRestaurante(String nome, String info)
			throws SistemaException {
		if (nome.trim().isEmpty() || nome == null) {
			throw new StringInvalidaException(
					"Erro na consulta do restaurante. Nome do prato esto vazio.");
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
	 * @throws ItemCardapioInvalidoException 
	 */
	private String getPrecoItem(String nome) throws ItemCardapioInvalidoException {
		ItemCardapio item = this.getItem(nome);
		String preco = String.format("R$%.2f", item.getPreco());
		return preco;
	}
	
	public double getItemPreco(String nome) throws ItemCardapioInvalidoException {
		ItemCardapio item = this.getItem(nome);
		double preco = item.getPreco();
		return preco;
	}

	/**
	 * Metodo privado usado apenas no cadastro de uma refeicao no cardapio.
	 * 
	 * @param componentes
	 *            Pratos que irao compor uma refeicao completa.
	 * @return Array contendo os Pratos que irao compor a refeicao completa.
	 * @throws SistemaException
	 */
	private ArrayList<Prato> criaArrayList(String componentes) throws SistemaException {
		if (componentes.trim().isEmpty()) {
			throw new SistemaException(
					"Erro no cadastro de refeicao. Componente(s) esta(o) vazio(s).");
		}
		String[] pratos = componentes.split(";");
		ArrayList<Prato> novosPratos = new ArrayList<Prato>();

		for (int i = 0; i < pratos.length; i++) {
			if (!buscaItem(pratos[i])) {
				throw new StringInvalidaException(
						"Erro no cadastro de refeicao. So eh possivel cadastrar refeicoes com pratos ja cadastrados.");
			}
			Prato pratoNovo = (Prato) getItem(pratos[i]);
			novosPratos.add(pratoNovo);
		}
		return novosPratos;
	}

	/**
	 * 
	 * @param nome
	 *            Nome do item do cardapio a ser removido
	 * @throws ItemCardapioInvalidoException
	 *             ,se o item for inexistente no cardapio.
	 */
	public void removeItemCardapio(String nome)
			throws ItemCardapioInvalidoException {
		if (!buscaItem(nome)) {
			throw new ItemCardapioInvalidoException(
					"Esse item nao existe no cardapio");
		}
		cardapio.remove(nome);
	}
	
	private boolean buscaItem(String nome){
		for (int i = 0; i < cardapio.size(); ++i){
			if (cardapio.get(i).getNome().equalsIgnoreCase(nome)){
				return true;
			}
		}
		return false;
	}
	
	public void ordenaCardapio(String tipoOrdenacao) throws SistemaException{
		Collections.sort(this.cardapio, new Comparador(tipoOrdenacao));
		}
	
	public String imprimeCardapio(){
		String saida = "";
		for(int i = 0; i < this.cardapio.size(); i++){
			if(i < this.cardapio.size() - 1){
				saida += this.cardapio.get(i).getNome() + ";";
			}
			else{
				saida += this.cardapio.get(i).getNome();
			}	
		}
		return saida;
	}
}