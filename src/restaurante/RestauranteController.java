package restaurante;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import excecoes.AtributoClienteException;
import excecoes.CadastroItemCardapioException;
import excecoes.ItemCardapioInvalidoException;
import excecoes.SistemaException;
import excecoes.ValorInvalidoException;
import prato.ItemCardapio;
import prato.Prato;
import prato.Refeicao;

/**
 * Classe representando Controller do Restaurante.
 * 
 * @author marianams
 * @author sampaio
 *
 */
public class RestauranteController {

	private List<ItemCardapio> cardapio;
	private FactoryItemCardapio fabrica;
	private FactoryComparador factoryComparador;
	private Comparator<ItemCardapio> comparador;

	/**
	 * Construtor de Restaurante Controller.
	 */
	public RestauranteController() {
		this.cardapio = new ArrayList<ItemCardapio>();
		this.fabrica = new FactoryItemCardapio();
		this.factoryComparador = new FactoryComparador();
		this.comparador = null;
	}

	/**
	 * 
	 * @param nomeDoItem
	 * @return Objeto que eh um item do cardapio
	 * @throws ItemCardapioInvalidoException
	 */
	public ItemCardapio getItem(String nomeDoItem) throws SistemaException {
		for (int i = 0; i < cardapio.size(); ++i) {
			if (cardapio.get(i).getNome().equals(nomeDoItem)) {
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
		if (this.comparador != null) {
			Collections.sort(this.cardapio, this.comparador);
		}
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
	 * @throws Exception
	 */
	public void cadastraPrato(String nome, double preco, String descricao) throws CadastroItemCardapioException {

		try {
			Prato prato = fabrica.criaPrato(nome, preco, descricao);
			addItem(prato);

		} catch (Exception e) {
			throw new CadastroItemCardapioException("do prato. ", e.getMessage());

		}

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
	 * @throws Exception
	 */
	public void cadastraRefeicao(String nome, String descricao, String componentes) throws SistemaException {
		try {
			ArrayList<Prato> pratos = criaArrayList(componentes);
			Refeicao refeicao = fabrica.criaRefeicao(nome, descricao, pratos);
			cardapio.add(refeicao);
		} catch (ValorInvalidoException e) {
			throw new CadastroItemCardapioException("de refeicao completa. ", e.getMessage());
		}catch(Exception e){
			throw new CadastroItemCardapioException("de refeicao. ", e.getMessage());

		}

	}

	/**
	 * 
	 * @param nome
	 *            Nome da refeicao procurada.
	 * @param info
	 *            Informacao desejada, preco ou descricao de um item do
	 *            cardapio.
	 * @return Informacao requerida do item.
	 * @throws Exception
	 * @throws AtributoClienteException
	 */
	public String consultaRestaurante(String nome, String info) throws Exception {
		if (nome.trim().isEmpty() || nome == null) {
			throw new Exception("Erro na consulta do restaurante. Nome do prato esto vazio.");
		}
		if (info.equalsIgnoreCase("preco")) {
			return getPrecoItem(nome);
		} else if (info.equalsIgnoreCase("descricao")) {
			return getItem(nome).toString();
		} else {
			throw new Exception();
		}
	}

	/**
	 * 
	 * @param nome
	 *            Nome do item do cardapio.
	 * @return O preco de um item do cardapio em formato String.
	 * @throws ItemCardapioInvalidoException
	 */
	private String getPrecoItem(String nome) throws SistemaException {
		ItemCardapio item = this.getItem(nome);
		String preco = String.format("R$%.2f", item.getPreco());
		return preco;
	}

	public double getItemPreco(String nome) throws SistemaException {
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
			throw new SistemaException("Componente(s) esta(o) vazio(s).");
		}
		String[] pratos = componentes.split(";");
		ArrayList<Prato> novosPratos = new ArrayList<Prato>();

		for (int i = 0; i < pratos.length; i++) {
			if (!buscaItem(pratos[i])) {
				throw new SistemaException(
						"So eh possivel cadastrar refeicoes com pratos ja cadastrados.");
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
	public void removeItemCardapio(String nome) throws ItemCardapioInvalidoException {
		if (!buscaItem(nome)) {
			throw new ItemCardapioInvalidoException("Esse item nao existe no cardapio");
		}
		cardapio.remove(nome);
	}

	private boolean buscaItem(String nome) {
		for (int i = 0; i < cardapio.size(); ++i) {
			if (cardapio.get(i).getNome().equalsIgnoreCase(nome)) {
				return true;
			}
		}
		return false;
	}

	public void ordenaCardapio(String tipoOrdenacao) throws SistemaException {
		this.comparador = this.factoryComparador.criaComparador(tipoOrdenacao);
		Collections.sort(this.cardapio, this.comparador);
	}

	public String imprimeCardapio() {
		String saida = "";
		for (int i = 0; i < this.cardapio.size(); i++) {
			if (i < this.cardapio.size() - 1) {
				saida += this.cardapio.get(i).getNome() + ";";
			} else {
				saida += this.cardapio.get(i).getNome();
			}
		}
		return saida;
	}

	public void geraRelatorioMenu() {
		PrintWriter out = null;
		try {
			String path = new File("./arquivos_sistema/relatorios/cad_restaurante.txt").getCanonicalPath();
			out = new PrintWriter(new BufferedWriter(new FileWriter(path)));
			out.println("Menu do Restaurante: " + cardapio.size() + " itens no cardapio");

			for (int i = 0; i < cardapio.size(); ++i) {
				out.println("==> Item " + (i + 1) + ":");
				out.println("Nome: " + cardapio.get(i).getNome() + " Preco: R$" + cardapio.get(i).getPreco());
				out.println("Descricao: " + cardapio.get(i).getDescricao());
				out.println("");
			}
			out.close();
		} catch (IOException e) {
		} finally {
			out.close();
		}

	}
}