package hotel;

import java.io.IOException;

import db.BancoDeDados;
import easyaccept.EasyAccept;
import excecoes.EmailInexistenteException;
import excecoes.RemocaoException;
import excecoes.SistemaException;

public class HotelFacade {
	private HotelController gerencia;
	private BancoDeDados bd;

	public HotelFacade() {
		this.bd = BancoDeDados.getInstance();
	}

	public void iniciaSistema() throws IOException {
		try{
			bd.iniciaSistema();
			gerencia = bd.getHotelController();
		}catch(NullPointerException e){
			gerencia = new HotelController();
		}
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
	 *             Quando ha alguma string invalida
	 */
	public String cadastraHospede(String nome, String email, String data) throws SistemaException{
		return gerencia.cadastraHospede(nome, email, data);
	

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
	public void realizaCheckin(String email, int dias, String idQuarto, String tipoQuarto) throws SistemaException {
		gerencia.realizaChekin(email, dias, idQuarto, tipoQuarto);

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
	public String realizaCheckout(String email, String idQuarto) {
		try {
			return gerencia.realizaCheckout(email, idQuarto);
		} catch (Exception e) {
			return e.getMessage();
		}
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
	public String realizaPedido(String email, String itemMenu){
		try{
			return gerencia.realizaPedido(email, itemMenu);
		}catch(Exception e){
			return e.getMessage();
		}
	}

	/**
	 * Consulta transaçoes para um dado atributo
	 * @param atributo
	 * 		define o atributo
	 * @return
	 * 		a transacao
	 */
	public String consultaTransacoes(String atributo) {
		try {
			return gerencia.consultaTransacoes(atributo);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	/**
	 * Consulta um transacao especifica
	 * @param atributo
	 * 		O atributo a ser consultado
	 * @param indice
	 * 		A ordem da transacao
	 * @return
	 * 		Uma string representando a transacao
	 */
	public String consultaTransacoes(String atributo, int indice) {
		try {
			return gerencia.consultaTransacoes(atributo, indice);
		} catch (Exception e) {
			return e.getMessage();
		}
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
	public String getInfoHospede(String email, String info) throws Exception {
		return gerencia.getInfoHospede(email, info);
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
	public String getInfoHospedagem(String email, String info) throws Exception {
		return gerencia.getInfoHospedagem(email, info);
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
		return gerencia.atualizaCadastro(email, tipoInfo, novaInfo);

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
	public void removeHospede(String email) throws SistemaException {
			gerencia.removeHospede(email);
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
	 * @throws SistemaException
	 * 			Quando ocorre uma situacao inesperada 
	 */
	public void cadastraPrato(String nome, double preco, String descricao) throws SistemaException {
		gerencia.cadastraPrato(nome, preco, descricao);
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
		return gerencia.consultaRestaurante(nome, info);
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
		gerencia.cadastraRefeicao(nome, descricao, componentes);
	}

	/**
	 * Remove um item do cardapio
	 * 
	 * @param nome
	 *            item a ser removido
	 * @throws SistemaException
	 *             quando ocorre alguma situacao inesperada
	 */
	public void removeItemCardapio(String nome){
		try{
			gerencia.removeItemCardapio(nome);
		}catch(Exception e){
		System.out.println(e.getMessage());
			
		}
	}
	
	/**
	 * Consulta o menu
	 * 
	 * @return Imprime o menu
	 */
	public String consultaMenuRestaurante(){
		return gerencia.consultaMenuRestaurante();
	}
	
	/**
	 * Ordena o menu de acordo com o tipo de ordenacao escolhida
	 * 
	 * @param tipoOrdenacao
	 *            define o tipo de ordenecao
	 * @throws SistemaException
	 */
	public void ordenaMenu(String tipoOrdenacao){
		try{
			gerencia.ordenaMenu(tipoOrdenacao);
		}catch(Exception e){
			System.out.println(e.getMessage());
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
	public String convertePontos(String email, int pontos){
		try{
			return this.gerencia.convertePontos(email, pontos);
		}catch(SistemaException e){
			System.out.println(e.getMessage());
		}
		return null;
	}
	

	public void fechaSistema() throws IOException, SistemaException {
		bd.fechaSistema();
		gerencia.geraRelatorios();
	}

	public static void main(String[] args) {
		args = new String[] { "hotel.HotelFacade","acceptance_test/testes_uc1_exception.txt", "acceptance_test/testes_uc1.txt",
			 "acceptance_test/testes_uc2.txt",
				"acceptance_test/testes_uc3.txt", "acceptance_test/testes_uc4.txt",
				"acceptance_test/testes_uc4_exception.txt" ,"acceptance_test/testes_uc5.txt", "acceptance_test/testes_uc6.txt", 
				"acceptance_test/testes_uc7.txt"};
		EasyAccept.main(args);

	}
}