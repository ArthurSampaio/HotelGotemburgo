package hotel;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cliente.Cliente;
import cliente.ClienteFactory;
import estadia.Estadia;
import excecoes.AtributoClienteException;
import excecoes.AtualizaCadastroException;
import excecoes.CadastroInvalidoException;
import excecoes.CadastroItemCardapioException;
import excecoes.EmailInexistenteException;
import excecoes.RemocaoException;
import excecoes.SistemaException;
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
public class HotelController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5713763590536159507L;
	private List<Cliente> clientes;
	private RestauranteController restaurante;
	private List<Transacao> transacoes;
	private ClienteFactory factoryCliente;
	private QuartoFactory factoryQuarto;

	/**
	 * Construtor do HotelController
	 */
	public HotelController() {
		this.clientes = new ArrayList<Cliente>();
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
			if (getIndiceCliente(email) == -1)
				clientes.add(novoCliente);

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
	 * @throws SistemaException
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
	 * @throws SistemaException
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
	 * @throws SistemaException
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
	 * @throws SistemaException
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
	 * @throws SistemaException
	 *             quando um tipoInformacao nao existe no cliente, string invalida ou cliente inexistente
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
	 * @throws SistemaException
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
	 * Atualiza o cadastro de um determinado cliente
	 * @param email
	 * 		Email do cliente a ser atualizado
	 * @param tipoInfo
	 * 		Tipo de informacao a ser atualizada
	 * @param novaInfo
	 * 		Nova informacao
	 * @return
	 * 		O email do cliente
	 * @throws SistemaException
	 * 		Quando ocorre um erro de String invalida, informacao invalida ou cliente inexistente
	 */
	public String atualizaCadastro(String email, String tipoInfo, String novaInfo) throws SistemaException {
		if (getIndiceCliente(email) == -1) {
			throw new EmailInexistenteException(
					"Erro na consulta de hospede. Hospede de email " + email + " nao foi cadastrado(a).");
		}
		try {
				Cliente cliente = getCliente(email);
				cliente.atualizaCadastro(tipoInfo, novaInfo);
				return cliente.getEmail();
		} catch (Exception e) {
			throw new AtualizaCadastroException(e.getMessage());
		}

		
	}

	/**
	 * Busca e retorna um cliente
	 * 
	 * @param email
	 *            email do cliente
	 * @return Retorna o cliente quando encontrado
	 * @throws SistemaException
	 *             Quando hospede nao esta cadastrado no sistema
	 */
	public Cliente getCliente(String email) throws SistemaException {
		for (int i = 0; i < clientes.size(); i++){
			if (clientes.get(i).getEmail().equals(email)){
				return clientes.get(i);
			}
		}
		throw new EmailInexistenteException(
					"Erro na consulta de hospede. Hospede de email " + email + " nao foi cadastrado(a).");
	}
	
	private int getIndiceCliente(String email){
		for (int i = 0; i < clientes.size(); i++){
			if (clientes.get(i).getEmail().equals(email)){
				return i;
			}
		}
		return -1;
	}

	/**
	 * Remove um cliente do sistema
	 * 
	 * @param email
	 *            Identifica qual o cliente deve ser removido
	 * @throws EmailInexistenteException
	 *             Quando nao ha um cliente cadastrado com o email passado
	 * @throws RemocaoException
	 * 			Quando ocorre algum erro na remocao            
	 */
	public void removeHospede(String email) throws RemocaoException, EmailInexistenteException {
		try{
		checaEmail(email, "Erro na remocao do Hospede. Formato de email invalido.");
		}catch(Exception e){
			throw new RemocaoException(e.getMessage());
		}
		if (getIndiceCliente(email) == -1) {
			throw new EmailInexistenteException(
					"Erro na consulta de hospede. Hospede de email " + email + " nao foi cadastrado(a).");
		}
		clientes.remove(getIndiceCliente(email));
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
		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).isHospedado() && clientes.get(i).getEstadia().containsKey(id)) {
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
	 * @throws SistemaException
	 *             Quando a string eh invalida ou o formato do email eh
	 *             irregular
	 */
	public void checaEmail(String email, String msg) throws SistemaException {
		if (!email.matches("[ a-zA-Z]+@[ a-zA-Z]+\\.[ a-zA-Z]+")
				&& !email.matches("[ a-zA-Z]+@[ a-zA-Z]+\\.[ a-zA-Z]+\\.[ a-zA-Z]+")) {
			throw new SistemaException(" Formato de email invalido.");
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
	 * @throws CadastroItemCardapioException 
	 */
	public void cadastraPrato(String nome, double preco, String descricao) throws CadastroItemCardapioException {
		restaurante.cadastraPrato(nome, preco, descricao);
	}

	/**
	 * Consulta uma informacao de um item no restaurante
	 * @param nome
	 * 		Nome do item
	 * @param info
	 * 		Informacao requerida do Item
	 * @return
	 * 		A informacao especificada
	 * @throws SistemaException
	 */
	public String consultaRestaurante(String nome, String info) throws SistemaException {
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
	 * @throws SistemaException 
	 */
	public void cadastraRefeicao(String nome, String descricao, String componentes) throws SistemaException {
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
	 * @return Retorna o valor da transacao ja com descontos aplicados.
	 * @throws SistemaException
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
	
	/**
	 * Gera relatorio dos clientes
	 * @throws SistemaException
	 */
	private void geraRelatorioCliente() throws SistemaException {
		PrintWriter out = null;
		try {
			String path = new File("./arquivos_sistema/relatorios/cad_hospedes.txt").getCanonicalPath();
			out= new PrintWriter(new BufferedWriter(new FileWriter(path)));
			out.println("Cadastro de Hospedes: " + clientes.size() + " hospedes registrados");
		
			for (int i = 0; i < clientes.size(); i++){
				out.println("==> Hospede " + (i + 1));
				Cliente atual = getCliente(clientes.get(i).getEmail());
				out.println("Email: " + atual.getEmail());
				out.println("Nome: " + atual.getNome());
				String[] data = atual.getDataNasc().split("/");
				out.println("Data de nascimento: " + data[2] + "-" + data[1] + "-" + data[0]);
				out.println(" ");
			}
		} 
		catch (FileNotFoundException e) {
			File f = new File( "arquivos_sistema/relatorios/cad_hospedes.txt" );
			f.getParentFile().mkdirs();
			try{
				f.createNewFile();
			
			}catch(IOException ee){
				System.err.println("IOException: " + e.getMessage());
			}
		}
		catch (IOException e) {
		}
		finally{
			out.close();
		}	
	}
	
	/**
	 * Gera relatorio do cardapio
	 * @throws IOException
	 * @throws SistemaException
	 */
	private void geraRelatorioMenu() throws IOException, SistemaException{
		restaurante.geraRelatorioMenu();
	}
	
	/**
	 * Gera relatorio de transacoes
	 */
	private void geraRelatorioTransacoes(){
		PrintWriter out = null;
		String path; 
		try {
			path = new File ("./arquivos_sistema/relatorios/cad_transacoes.txt").getCanonicalPath();
			out= new PrintWriter(new BufferedWriter(new FileWriter(path)));
			out.println("Historio de Transacoes:");
			
			for (int i = 0; i < transacoes.size(); ++i){
				Transacao atual = transacoes.get(i);
				out.println("Nome: " + atual.getNomeCliente() + " Gasto:R$" + String.format("%.2f",atual.getValorTransacao()) + " Detalhes: " + atual.getDetalhe());
			}
			out.println("===== Resumo de transacoes =====");
			out.println("Lucro total: R$ " + String.format("%.2f", getTotalTransacoes()));
			out.println("Total de transacoes: " + transacoes.size());
			out.println("Lucro medio por transacao: R$" + String.format("%.2f", (getTotalTransacoes()/transacoes.size())));
		} catch (FileNotFoundException e) {
			File f = new File( "arquivos_sistema/relatorios/cad_transacoes.txt" );
			f.getParentFile().mkdirs();
			try{
				f.createNewFile();
			
			}catch(IOException ee){
				System.err.println("IOException: " + e.getMessage());
			}
		}catch(IOException e){
			
		}
		
		finally{
			out.close();
		}	
	}
	
	/**
	 * Gera relatorio completo do hotel
	 */
	private void geraRelatorioHotel(){
		try {
			String path = new File("./arquivos_sistema/relatorios/cad_hospedes.txt").getCanonicalPath();
			BufferedReader in = new BufferedReader(new FileReader(path));
			String pathout = new File("./arquivos_sistema/relatorios/hotel_principal.txt").getCanonicalPath();
			PrintWriter out= new PrintWriter(new BufferedWriter(new FileWriter(pathout)));
			String linha;
			
			out.println("====================================");
			while ( (linha = in.readLine()) != null ){
				out.println(linha);
			}
			out.println("====================================");
			path = new File("./arquivos_sistema/relatorios/cad_restaurante.txt").getCanonicalPath();
			in = new BufferedReader(new FileReader(path));
			
			while ( (linha = in.readLine()) != null ){
				out.println(linha);
			}
			
			out.println("====================================");
			path = new File("./arquivos_sistema/relatorios/cad_transacoes.txt").getCanonicalPath();
			in = new BufferedReader(new FileReader(path));
			
			while ( (linha = in.readLine()) != null ){
				out.println(linha);
			}
			in.close();
			out.close();
		
		}catch (FileNotFoundException e) {
			File f = new File( "arquivos_sistema/relatorios/hotel_principal.txt" );
			f.getParentFile().mkdirs();
			try{
				f.createNewFile();
			
			}catch(IOException ee){
				System.err.println("IOException: " + e.getMessage());
			} 
		}
		catch (IOException e) {
			
		}
		
	}
	
	/**
	 * Gera todos os 4 relatorios
	 * @throws IOException
	 * @throws SistemaException
	 */
	public void geraRelatorios() throws IOException, SistemaException{
		geraRelatorioMenu();
		geraRelatorioTransacoes();
		geraRelatorioCliente();
		geraRelatorioHotel();
	}
}
