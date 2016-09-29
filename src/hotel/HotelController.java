package hotel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import cliente.Cliente;
import cliente.ClienteFactory;
import estadia.Estadia;
import excecoes.EmailInexistenteException;
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
	 * @param nome
	 * 		O nome do hospede
	 * @param email
	 * 		O email do hospede, precisa ser unico
	 * @param data
	 * 		O nascimento do usuário
	 * 
	 * @return
	 * 		O ID unico do usuario
	 * @throws SistemaException
	 * 		Quando há alguma string invalida
	 */
	public String cadastraHospede(String nome, String email, String data) throws SistemaException {
		Cliente novoCliente = this.factoryCliente.criaCliente(nome, email, data);
		clientes.put(email, novoCliente);

		return email;
	}
	
	/**
	 * Realiza checkin de um cliente
	 * @param email
	 * 		email do cliente
	 * @param dias
	 * 		dias hospedados
	 * @param id
	 * 		ID do quarto
	 * @param tipoQuarto
	 * 		Tipo do quarto
	 * @throws Exception
	 * 		Quando o quarto ja esta ocupado
	 */
	public void realizaChekin(String email, int dias, String id, String tipoQuarto) throws Exception{
		if(!verificaQuartoDisponivel(id)){
			throw new Exception("Erro ao realizar checkin. Quarto " + id + " ja esta ocupado.");
		}
		Cliente cliente = this.getCliente(email);
		Quarto quarto = factoryQuarto.criaQuarto(id, tipoQuarto);
		Estadia estadia = new Estadia(quarto, dias);
		cliente.adicionaEstadia(estadia);
		
	}
	
	/**
	 * Realiza checkout de um hospede
	 * @param email
	 * 		Email do cliente
	 * @param idQuarto
	 * 		ID do quarto
	 * @return
	 * 		Retorna uma string com o valor da estadia
	 * @throws Exception
	 */
	public String realizaCheckout(String email, String idQuarto) throws Exception{
		Cliente cliente = getCliente(email);
		Estadia estadia = cliente.getEstadia().get(idQuarto);
		double valorTransacao = estadia.calculaValorEstadia() - cliente.aplicaDesconto(estadia.calculaValorEstadia());
		adicionaTransacao(cliente, idQuarto, valorTransacao);
		
		cliente.addPontos(estadia.calculaValorEstadia());
		cliente.removeEstadia(estadia);
		return String.format("R$%.2f", valorTransacao);		
	}
	
	
	
	/**
	 * Adiciona uma transacao numa lista de transacoes quando um cliente faz checkout
	 * @param cliente
	 * 		Cliente que esta fazendo checkout
	 * @param idQuarto
	 * 		ID do quarto que ele esta fazendo checkout
	 * @throws Exception
	 * 		Quando ocorre um erro ao criar um novo cliente
	 */
	public void adicionaTransacao(Cliente cliente, String detalhe, double valorTransacao) throws Exception{
		String nome = cliente.getNome();
		String email = cliente.getEmail();
		Transacao transacao = new Transacao(nome, email, valorTransacao, detalhe);
		this.transacoes.add(transacao);
	}


	/**
	 * Verifica se um cliente ja possui transacoes na lista de transacoes
	 * @param email
	 * 		email do cliente
	 * @return
	 * 		retorna um boolean indicando se o cliente possui transacoes(true) ou nao(false)
	 */
	public boolean contemClienteTransacao(String email){
		for(int i = 0; i < this.transacoes.size(); i++){
			if(this.transacoes.get(i).getEmailCliente().equalsIgnoreCase(email)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Busca uma Transacao da lista de transacoes
	 * @param email
	 * 		email do cliente para busca
	 * @return
	 * 		A transacao encontrada ou null
	 */
	public Transacao getClienteTransacao(String email){
		for(int i = 0; i < this.transacoes.size(); i++){
			if(this.transacoes.get(i).getEmailCliente().equalsIgnoreCase(email)){
				return this.transacoes.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Retorna o total das transacoes
	 * 
	 * @return
	 * 		Retorna o total (em double) de transacoes
	 * @throws Exception
	 * 		Quando ocorre um erro no calculo das estadias
	 */
	public double getTotalTransacoes() throws Exception{
		double total = 0.0;
		for(int i = 0; i < this.transacoes.size(); i++){
			total += this.transacoes.get(i).getValorTransacao();
		}
		return total;
	}
	
	/**
	 * @return
	 * 		Retorna os nomes dos clientes que realizaram transacoes
	 */
	public String getNomesTransacoes(){
		String saida = "";
		for(int i = 0; i < this.transacoes.size(); i++){
			if(i < this.transacoes.size() - 1){
				saida += this.transacoes.get(i).getNomeCliente() + ";";
			}else{
				saida += this.transacoes.get(i).getNomeCliente();

			}
		}
		return saida;
	}
	
	/**
	 * Retorna informacoes especificas das transacoes
	 * @param info
	 * 		informacao requerida
	 * @return
	 * 		retorna a informacao requerida
	 * @throws Exception
	 */
	public String consultaTransacoes(String info) throws Exception{
		if(info.equalsIgnoreCase("Quantidade")){
			return "" + this.transacoes.size();
		}else if(info.equalsIgnoreCase("Total")){
			return String.format("R$%.2f", getTotalTransacoes());
		}else if(info.equalsIgnoreCase("Nome")){
			return getNomesTransacoes();
		}
		else{
			throw new Exception("Nao ha a informacao especificada.");
		}
	}
	
	/**
	 * Retorna informacoes especificas das transacoes
	 * @param info
	 * 		informacao requerida
	 * @param indice
	 * 		indice da lista de transacoes
	 * @return
	 * 		retorna a informacao requerida no indice especificado
	 * @throws Exception
	 */
	public String consultaTransacoes(String info, int indice) throws Exception{
		Transacao transacao = this.transacoes.get(indice);
		if(info.equalsIgnoreCase("Total")){
			return String.format("R$%.2f", transacao.getValorTransacao());
		}else if(info.equalsIgnoreCase("Nome")){
			return transacao.getNomeCliente();
		}else if(info.equalsIgnoreCase("detalhes")){
			return transacao.getDetalhe();
		}
		
		else{
			throw new Exception("Nao ha a informacao especificada.");
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
	 * 				Quando nao há o email passado como parametro cadastrado no sistema            
	 */
	public String getInfoHospede(String email, String info) throws Exception {
		Cliente cliente = getCliente(email);
		return cliente.getInfoHospede(info);

	}
	
	/**
	 * Obtem informacaoes sobre a hospedagem de um determinado cliente
	 * @param email
	 * 		email do cliente
	 * @param info
	 * 		informacao requerida
	 * @return
	 * 		retorna a informacao requerida
	 * @throws Exception
	 * 		Quando o cliente nao esta hospedado
	 */
	public String getInfoHospedagem(String email, String info)throws Exception{
		Cliente cliente = getCliente(email);
		if(!cliente.isHospedado()){
			throw new Exception("Erro na consulta de hospedagem. Hospede " + cliente.getNome() + " nao esta hospedado(a).");
		}
		return cliente.getInfoHospedagem(info);
	}

	/**
	 * Retorna uma determinada informacao do hospede
	 * 
	 * @param tipoInformacao
	 *          define qual informacao deve ser retornada
	 * @param novaInfor
	 * 			define o novo valor da informacao           
	 * @return um string contendo a informacao requerida
	 * 
	 * @throws Exception
	 *             quando um tipoInformacao nao existe no cliente
	 * @throws StringInvalidaException
	 *             quando ha uma string invalida
	 * @throws EmailInexistenteException
	 * 				Quando nao há o email passado como parametro cadastrado no sistema            
	 */
	public String atualizaCadastro(String email, String tipoInfo, String novaInfo) throws Exception {
		if (!clientes.containsKey(email)) {
			throw new EmailInexistenteException(
					"Erro na consulta de hospede. Hospede de email " + email + " nao foi cadastrado(a).");
		}

		if (tipoInfo.equalsIgnoreCase("email")) {
			//precisa deste procedimento para calcular o hash da chave do mapa
			Cliente cliente = clientes.get(email);
			clientes.put(novaInfo, cliente);
			//clientes.remove(email); ESSA GAMBIARRA ATÉ AJEITAREM OS TESTES
			clientes.get(novaInfo).atualizaCadastro(tipoInfo,novaInfo);
			return clientes.get(novaInfo).getInfoHospede(tipoInfo);
			
		}else{
			Cliente cliente = this.clientes.get(email);
			cliente.atualizaCadastro(tipoInfo, novaInfo);
			this.getInfoHospede(email, tipoInfo);
			
		}

		return null;
	}
	
	/**
	 * Busca e retorna um cliente
	 * @param email
	 * 		email do cliente
	 * @return
	 * 		Retorna o cliente quando encontrado
	 * @throws Exception
	 * 		Quando hospede nao esta cadastrado no sistema
	 */
	public Cliente getCliente(String email) throws Exception {
		if (!clientes.containsKey(email)) {
			throw new EmailInexistenteException(
					"Erro na consulta de hospede. Hospede de email " + email + " nao foi cadastrado(a).");
		}
		return clientes.get(email);
	}

	/**
	 * Remove um cliente do sistema
	 * @param email
	 * 		Identifica qual o cliente deve ser removido
	 * @throws Exception
	 * 		Quando nao ha um cliente cadastrado com o email passado
	 */
	public void removeHospede(String email) throws SistemaException {
		checaEmail(email, "Erro na remocao do Hospede. Formato de email invalido.");
		if (!clientes.containsKey(email)) {
			throw new EmailInexistenteException(
					"Erro na consulta de hospede. Hospede de email " + email + " nao foi cadastrado(a).");
		}
		clientes.remove(email);
	}
	
	/**
	 * Verifica a disponibilidade de um quarto
	 * @param id
	 * 		id do quarto a ser verificado
	 * @return
	 * 		retorna um boolean indiciando se um quarto esta disponivel(true) ou nao(false)
	 */
	public boolean verificaQuartoDisponivel(String id){
		for(Entry<String, Cliente> entry : this.clientes.entrySet()) {
			if(entry.getValue().isHospedado() && entry.getValue().getEstadia().containsKey(id)){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Checa a expressao regular do email
	 * @param email
	 * 		email a ser verificado
	 * @param msg
	 * 		mensagem de excecao (caso ocorra)
	 * @throws StringInvalidaException
	 * 		Quando a string eh invalida ou o formato do email eh irregular
	 */
	public void checaEmail(String email, String msg) throws StringInvalidaException {
		if (!email.matches("[ a-zA-Z]+@[ a-zA-Z]+\\.[ a-zA-Z]+")
				&& !email.matches("[ a-zA-Z]+@[ a-zA-Z]+\\.[ a-zA-Z]+\\.[ a-zA-Z]+")) {
			throw new StringInvalidaException(msg);
		}
	}
	
	public void cadastraPrato(String nome, double preco, String descricao) throws SistemaException {
		restaurante.cadastraPrato(nome, preco, descricao);
	}

	public String consultaRestaurante(String nome, String info) throws Exception {
		return restaurante.consultaRestaurante(nome, info);
	}

	public void cadastraRefeicao(String nome, String descricao, String componentes) throws SistemaException {
		restaurante.cadastraRefeicao(nome, descricao, componentes);
	}

	
	public void removeItemCardapio(String nome) throws SistemaException{
		restaurante.removeItemCardapio(nome);
	}
	
	public String consultaMenuRestaurante(){
		return restaurante.imprimeCardapio();
	}
	
	public void ordenaMenu(String tipoOrdenacao) throws SistemaException{
		restaurante.ordenaCardapio(tipoOrdenacao);
	}
	
	public String realizaPedido(String emailCliente, String pedido)throws Exception{
		Cliente cliente = this.getCliente(emailCliente);
		double valorTransacao = restaurante.getItemPreco(pedido) - cliente.aplicaDesconto(restaurante.getItemPreco(pedido));
		cliente.addPontos(restaurante.getItemPreco(pedido));
		adicionaTransacao(cliente, pedido, valorTransacao);
		return String.format("R$%.2f", valorTransacao);
	}
}
