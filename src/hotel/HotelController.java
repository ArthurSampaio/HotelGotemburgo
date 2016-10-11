package hotel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import cliente.Cliente;
import cliente.ClienteFactory;
import estadia.Estadia;
import excecoes.AtributoClienteException;
import excecoes.AtualizaCadastroException;
import excecoes.CadastroInvalidoException;
import excecoes.EmailInexistenteException;
import excecoes.RemocaoException;
import excecoes.SistemaException;
import excecoes.StringInvalidaException;
import quarto.Quarto;
import quarto.QuartoFactory;
import restaurante.RestauranteController;

/**
 * Classe que representa o Controller do Hotel
 * 
 * @author João Maurício
 * @author Mariana Mendes
 * @author Arthur Sampaio
 * @author Tiago Pereira
 *
 */
public class HotelController {
	private Map<String, Cliente> clientes;
	private RestauranteController restaurante;
	private List<Transacao> transacoes;
	private ClienteFactory factoryCliente;
	private QuartoFactory factoryQuarto;

	/**
	 * Construtor do HotelController
	 */
	public HotelController() {
		this.clientes = new HashMap<String, Cliente>();
		this.restaurante = new RestauranteController();
		this.transacoes = new ArrayList<Transacao>();
		this.factoryCliente = new ClienteFactory();
		this.factoryQuarto = new QuartoFactory();
	}

	/**
	 * Cadastra um novo hospede no Sistema
	 * 
	 * @param nome
	 *            O nome do hospede
	 * @param email
	 *            O email do hospede, precisa ser unico
	 * @param data
	 *            O nascimento do usuário
	 * 
	 * @return O ID unico do usuario
	 * @throws SistemaException
	 *             Quando há alguma string invalida
	 */
	public String cadastraHospede(String nome, String email, String data) throws SistemaException {
		try {
			Cliente novoCliente = this.factoryCliente.criaCliente(nome, email, data);
			clientes.put(email, novoCliente);

			return email;
		} catch (Exception e) {
			throw new CadastroInvalidoException(e.getMessage());
		}
	}

	/**
	 * Converte uma dada quantidade de pontos em reais.
	 * 
	 * @param email
	 *            O email do cliente
	 * @param qtdPontos
	 *            Os pontos a serem convertidos
	 * @return O valor em reais
	 * @throws SistemaException
	 */
	public String convertePontos(String email, int qtdPontos) throws SistemaException {
		Cliente cliente = this.getCliente(email);
		double valor = cliente.convertePontos(qtdPontos);
		// this.adicionaTransacao(cliente, "CONVERSAO DE PONTOS", valor);
		valor = arredonda(valor);
		return String.format("R$%.2f", valor);
	}

	/**
	 * Realiza checkin de um cliente
	 * 
	 * @param email
	 *            email do cliente
	 * @param dias
	 *            dias hospedados
	 * @param id
	 *            ID do quarto
	 * @param tipoQuarto
	 *            Tipo do quarto
	 * @throws Exception
	 *             Quando o quarto ja esta ocupado
	 */
	public void realizaChekin(String email, int dias, String id, String tipoQuarto) throws SistemaException {
		if (!verificaQuartoDisponivel(id)) {
			throw new SistemaException("Erro ao realizar checkin. Quarto " + id + " ja esta ocupado.");
		}
		Cliente cliente = this.getCliente(email);
		Quarto quarto = factoryQuarto.criaQuarto(id, tipoQuarto);
		Estadia estadia = new Estadia(quarto, dias);
		cliente.adicionaEstadia(estadia);

	}

	/**
	 * Realiza checkout de um hospede
	 * 
	 * @param email
	 *            Email do cliente
	 * @param idQuarto
	 *            ID do quarto
	 * @return Retorna uma string com o valor da estadia
	 * @throws Exception
	 */
	public String realizaCheckout(String email, String idQuarto) throws SistemaException {
		Cliente cliente = getCliente(email);
		Estadia estadia = cliente.getEstadia().get(idQuarto);
		double valorTransacao = estadia.calculaValorEstadia() - cliente.aplicaDesconto(estadia.calculaValorEstadia());
		valorTransacao = arredonda(valorTransacao);
		adicionaTransacao(cliente, idQuarto, valorTransacao);

		cliente.addPontos(estadia.calculaValorEstadia());
		cliente.removeEstadia(estadia);
		return String.format("R$%.2f", valorTransacao);
	}

	/**
	 * Adiciona uma transacao numa lista de transacoes quando um cliente faz
	 * checkout
	 * 
	 * @param cliente
	 *            Cliente que esta fazendo checkout
	 * @param idQuarto
	 *            ID do quarto que ele esta fazendo checkout
	 * @throws Exception
	 *             Quando ocorre um erro ao criar um novo cliente
	 */
	public void adicionaTransacao(Cliente cliente, String detalhe, double valorTransacao) throws SistemaException {
		String nome = cliente.getNome();
		String email = cliente.getEmail();
		Transacao transacao = new Transacao(nome, email, valorTransacao, detalhe);
		this.transacoes.add(transacao);
	}

	/**
	 * Verifica se um cliente ja possui transacoes na lista de transacoes
	 * 
	 * @param email
	 *            email do cliente
	 * @return retorna um boolean indicando se o cliente possui transacoes(true)
	 *         ou nao(false)
	 */
	public boolean contemClienteTransacao(String email) {
		for (int i = 0; i < this.transacoes.size(); i++) {
			if (this.transacoes.get(i).getEmailCliente().equalsIgnoreCase(email)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Busca uma Transacao da lista de transacoes
	 * 
	 * @param email
	 *            email do cliente para busca
	 * @return A transacao encontrada ou null
	 */
	public Transacao getClienteTransacao(String email) {
		for (int i = 0; i < this.transacoes.size(); i++) {
			if (this.transacoes.get(i).getEmailCliente().equalsIgnoreCase(email)) {
				return this.transacoes.get(i);
			}
		}
		return null;
	}

	/**
	 * Retorna o total das transacoes
	 * 
	 * @return Retorna o total (em double) de transacoes
	 * @throws Exception
	 *             Quando ocorre um erro no calculo das estadias
	 */
	public double getTotalTransacoes() {
		double total = 0.0;
		for (int i = 0; i < this.transacoes.size(); i++) {
			total += this.transacoes.get(i).getValorTransacao();
		}
		return total;
	}

	/**
	 * @return Retorna os nomes dos clientes que realizaram transacoes
	 */
	public String getNomesTransacoes() {
		String saida = "";
		for (int i = 0; i < this.transacoes.size(); i++) {
			if (i < this.transacoes.size() - 1) {
				saida += this.transacoes.get(i).getNomeCliente() + ";";
			} else {
				saida += this.transacoes.get(i).getNomeCliente();

			}
		}
		return saida;
	}

	/**
	 * Retorna informacoes especificas das transacoes
	 * 
	 * @param info
	 *            informacao requerida
	 * @return retorna a informacao requerida
	 * @throws Exception
	 */
	public String consultaTransacoes(String info) {
		if (info.equalsIgnoreCase("Quantidade")) {
			return "" + this.transacoes.size();
		} else if (info.equalsIgnoreCase("Total")) {
			return String.format("R$%.2f", getTotalTransacoes());
		} else if (info.equalsIgnoreCase("Nome")) {
			return getNomesTransacoes();
		} else {
			throw new AtributoClienteException("Nao ha a informacao especificada.");
		}
	}

	/**
	 * Retorna informacoes especificas das transacoes
	 * 
	 * @param info
	 *            informacao requerida
	 * @param indice
	 *            indice da lista de transacoes
	 * @return retorna a informacao requerida no indice especificado
	 * @throws Exception
	 */
	public String consultaTransacoes(String info, int indice) throws SistemaException {
		Transacao transacao = this.transacoes.get(indice);
		if (info.equalsIgnoreCase("Total")) {
			return String.format("R$%.2f", transacao.getValorTransacao());
		} else if (info.equalsIgnoreCase("Nome")) {
			return transacao.getNomeCliente();
		} else if (info.equalsIgnoreCase("detalhes")) {
			return transacao.getDetalhe();
		}

		else {
			throw new SistemaException("Nao ha a informacao especificada.");
		}
	}

	/**
	 * Retorna uma determinada informacao do hospede
	 * 
	 * @param tipoInformacao
	 *            define qual informacao deve ser retornada
	 * @return um string contendo a informacao requerida
	 * @throws Exception
	 *             quando um tipoInformacao nao existe no cliente
	 * @throws StringInvalidaException
	 *             quando ha uma string invalida
	 * @throws EmailInexistenteException
	 *             Quando nao há o email passado como parametro cadastrado no
	 *             sistema
	 */
	public String getInfoHospede(String email, String info) throws SistemaException {
		Cliente cliente = getCliente(email);
		return cliente.getInfoHospede(info);

	}

	/**
	 * Obtem informacaoes sobre a hospedagem de um determinado cliente
	 * 
	 * @param email
	 *            email do cliente
	 * @param info
	 *            informacao requerida
	 * @return retorna a informacao requerida
	 * @throws Exception
	 *             Quando o cliente nao esta hospedado
	 */
	public String getInfoHospedagem(String email, String info) throws SistemaException {
		Cliente cliente = getCliente(email);
		if (!cliente.isHospedado()) {
			throw new SistemaException(
					"Erro na consulta de hospedagem. Hospede " + cliente.getNome() + " nao esta hospedado(a).");
		}
		return cliente.getInfoHospedagem(info);
	}

	/**
	 * Retorna uma determinada informacao do hospede
	 * 
	 * @param tipoInformacao
	 *            define qual informacao deve ser retornada
	 * @param novaInfor
	 *            define o novo valor da informacao
	 * @return um string contendo a informacao requerida
	 * 
	 * @throws Exception
	 *             quando um tipoInformacao nao existe no cliente
	 * @throws StringInvalidaException
	 *             quando ha uma string invalida
	 * @throws EmailInexistenteException
	 *             Quando nao há o email passado como parametro cadastrado no
	 *             sistema
	 */
	public String atualizaCadastro(String email, String tipoInfo, String novaInfo) throws SistemaException {
		if (!clientes.containsKey(email)) {
			throw new EmailInexistenteException(
					"Erro na consulta de hospede. Hospede de email " + email + " nao foi cadastrado(a).");
		}
		try {
			if (tipoInfo.equalsIgnoreCase("email")) {
				// precisa deste procedimento para calcular o hash da chave do
				// mapa
				Cliente cliente = clientes.get(email);
				clientes.put(novaInfo, cliente);
				// clientes.remove(email); ESSA GAMBIARRA ATÉ AJEITAREM OS
				// TESTES
				clientes.get(novaInfo).atualizaCadastro(tipoInfo, novaInfo);
				return clientes.get(novaInfo).getInfoHospede(tipoInfo);

			} else {
				Cliente cliente = this.clientes.get(email);
				cliente.atualizaCadastro(tipoInfo, novaInfo);
				this.getInfoHospede(email, tipoInfo);

			}
		} catch (Exception e) {
			throw new AtualizaCadastroException(e.getMessage());
		}

		return null;
	}

	/**
	 * Busca e retorna um cliente
	 * 
	 * @param email
	 *            email do cliente
	 * @return Retorna o cliente quando encontrado
	 * @throws Exception
	 *             Quando hospede nao esta cadastrado no sistema
	 */
	public Cliente getCliente(String email) throws SistemaException {
		if (!clientes.containsKey(email)) {
			throw new EmailInexistenteException(
					"Erro na consulta de hospede. Hospede de email " + email + " nao foi cadastrado(a).");
		}
		return clientes.get(email);
	}

	/**
	 * Remove um cliente do sistema
	 * 
	 * @param email
	 *            Identifica qual o cliente deve ser removido
	 * @throws Exception
	 *             Quando nao ha um cliente cadastrado com o email passado
	 */
	public void removeHospede(String email) throws RemocaoException, EmailInexistenteException {
		try{
		checaEmail(email, "Erro na remocao do Hospede. Formato de email invalido.");
		}catch(Exception e){
			throw new RemocaoException(e.getMessage());
		}
		if (!clientes.containsKey(email)) {
			throw new EmailInexistenteException(
					"Erro na consulta de hospede. Hospede de email " + email + " nao foi cadastrado(a).");
		}
		clientes.remove(email);
	}

	/**
	 * Verifica a disponibilidade de um quarto
	 * 
	 * @param id
	 *            id do quarto a ser verificado
	 * @return retorna um boolean indiciando se um quarto esta disponivel(true)
	 *         ou nao(false)
	 */
	public boolean verificaQuartoDisponivel(String id) {
		for (Entry<String, Cliente> entry : this.clientes.entrySet()) {
			if (entry.getValue().isHospedado() && entry.getValue().getEstadia().containsKey(id)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Checa a expressao regular do email
	 * 
	 * @param email
	 *            email a ser verificado
	 * @param msg
	 *            mensagem de excecao (caso ocorra)
	 * @throws Exception 
	 * @throws StringInvalidaException
	 *             Quando a string eh invalida ou o formato do email eh
	 *             irregular
	 */
	public void checaEmail(String email, String msg) throws Exception {
		if (!email.matches("[ a-zA-Z]+@[ a-zA-Z]+\\.[ a-zA-Z]+")
				&& !email.matches("[ a-zA-Z]+@[ a-zA-Z]+\\.[ a-zA-Z]+\\.[ a-zA-Z]+")) {
			throw new Exception(" Formato de email invalido.");
		}
	}

	/**
	 * Cadastra um novo prato no Restaurante.
	 * 
	 * @param nome
	 *            Nome do prato.
	 * @param preco
	 *            Preco do prato.
	 * @param descricao
	 *            Descricao do prato.
	 * @throws Exception 
	 */
	public void cadastraPrato(String nome, double preco, String descricao) throws Exception {
		restaurante.cadastraPrato(nome, preco, descricao);
	}

	public String consultaRestaurante(String nome, String info) throws Exception {
		return restaurante.consultaRestaurante(nome, info);
	}

	/**
	 * Cadastra uma nova refeicao
	 * 
	 * @param nome
	 *            Nome da refeicao
	 * @param descricao
	 *            Descricao da refeicao
	 * @param componentes
	 *            Pratos que fazem parte
	 * @throws Exception 
	 */
	public void cadastraRefeicao(String nome, String descricao, String componentes) throws Exception {
		restaurante.cadastraRefeicao(nome, descricao, componentes);
	}

	/**
	 * Remove um item do cardapio
	 * 
	 * @param nome
	 *            item a ser removido
	 * @throws SistemaException
	 *             quando ocorre alguma situacao inesperada
	 */
	public void removeItemCardapio(String nome) throws SistemaException {
		restaurante.removeItemCardapio(nome);
	}

	/**
	 * Consulta o menu
	 * 
	 * @return Imprime o menu
	 */
	public String consultaMenuRestaurante() {
		return restaurante.imprimeCardapio();
	}

	/**
	 * Ordena o menu de acordo com o tipo de ordenacao escolhida
	 * 
	 * @param tipoOrdenacao
	 *            define o tipo de ordenecao
	 * @throws SistemaException
	 */
	public void ordenaMenu(String tipoOrdenacao) throws SistemaException {
		restaurante.ordenaCardapio(tipoOrdenacao);
	}

	/**
	 * Realiza um pedido no Restaurante
	 * 
	 * @param emailCliente
	 *            Email do cliente que esta efetuando o pedido.
	 * @param pedido
	 *            Nome do Item do Cardapio desejado.
	 * @return Retrona o valor da transacao ja com descontos aplicados.
	 * @throws Exception
	 *             Quando nao ha cliente cadastrado com esse email ou quando nao
	 *             ha Item no Cardapio com esse nome
	 */
	public String realizaPedido(String emailCliente, String pedido) throws SistemaException {
		Cliente cliente = this.getCliente(emailCliente);
		double valorTransacao = restaurante.getItemPreco(pedido)
				- cliente.aplicaDesconto(restaurante.getItemPreco(pedido));
		valorTransacao = arredonda(valorTransacao);
		adicionaTransacao(cliente, pedido, valorTransacao);
		cliente.addPontos(restaurante.getItemPreco(pedido));
		return String.format("R$%.2f", valorTransacao);
	}

	/**
	 * Arredonda o valor de uma transacao para 2 casas decimais, ja que se trata
	 * de dinheiro.
	 * 
	 * @param valor
	 *            Valor da transacao
	 * @return Valor arredondado
	 */
	private double arredonda(double valor) {
		valor = Math.ceil(valor * 100);
		valor /= 100.0;
		return valor;
	}
	
	public void geraRelatorioCliente() throws IOException, SistemaException{
		String path = new File("./arquivos_sistema/relatorios/cad_hospedes.txt").getCanonicalPath();
		PrintWriter out= new PrintWriter(new BufferedWriter(new FileWriter(path)));
		out.println("Cadastro de Hospedes: " + clientes.size() + " hospedes registrados");
		Set<String> chaves = clientes.keySet();
		int numCliente = 1;
		for (String cliente : chaves){
			out.println("==> Hospede " + numCliente);
			numCliente++;
			Cliente atual = getCliente(cliente);
			out.println("Email: " + atual.getEmail());
			out.println("Nome: " + atual.getNome());
			out.println("Data de nascimento: " + atual.getDataNasc());
			out.println(" ");
		}
		out.close();
	}
	
	public void geraRelatorioMenu() throws IOException, SistemaException{
		restaurante.geraRelatorioMenu();
	}
	
	public void geraRelatorioTransacoes() throws IOException{
		String path = new File("./arquivos_sistema/relatorios/cad_transacoes.txt").getCanonicalPath();
		PrintWriter out= new PrintWriter(new BufferedWriter(new FileWriter(path)));
		out.println("Historio de Transacoes:");
		
		for (int i = 0; i < transacoes.size(); ++i){
			Transacao atual = transacoes.get(i);
			out.println("Nome: " + atual.getNomeCliente() + " Gasto:R$" + atual.getValorTransacao() + " Detalhes: " + atual.getDetalhe());
		}
		out.close();
	}
	
	public void geraRelatorios() throws IOException, SistemaException{
		geraRelatorioMenu();
		geraRelatorioTransacoes();
		geraRelatorioCliente();
	}
}
