package cliente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

import cartao.Cartao;
import excecoes.CadastroInvalidoException;
import excecoes.CadastroVazioException;
import excecoes.ConvertePontosException;
import excecoes.IdadeInvalidaException;
import excecoes.SistemaException;
import excecoes.StringInvalidaException;
import excecoes.ValorInvalidoException;
import estadia.Estadia;

/**
 * Classe que representa um cliente cadastrado no BD do Hotel Gutemburgo
 * 
 * @author Arthur Sampaio
 *
 */
public class Cliente {

	private boolean hospedado;
	private String nome;
	private String dataNasc;
	private String email;
	private HashMap<String, Estadia> estadias;
	private Cartao cartaoFidelidade;


	private static final String ERRO_CADASTRO = "Erro no cadastro de Hospede. ";
	private static final String ERRO_ATUALIZACAO = "Erro na atualizacao do cadastro de Hospede. ";
	private static final String CLIENTE_SEM_INFORMACAO = "O cliente nao possue a informacao especificada.";
	/**
	 * Construtor da Classe Cliente
	 * 
	 * @param nome
	 *            O nome do cliente
	 * @param dataNasc
	 *            A data de nascimento do cliente
	 * @param email
	 *            O Email do cliente
	 * @param hosp
	 *            Representa se esta hospedado ou nao
	 * @throws CadastroVazioException 
	 * @throws CadastroInvalidoException 
	 * @throws IdadeInvalidaException 
	 * @throws Exception
	 *             Quando alguma das strings é invalida.
	 */
	public Cliente(String nome, String email, String dataNasc, boolean hosp) throws ValorInvalidoException, CadastroVazioException, CadastroInvalidoException, IdadeInvalidaException {
		
		if (stringInvalida(nome)) {
			throw new CadastroVazioException(ERRO_CADASTRO, "Nome");
		}

		if (stringInvalida(dataNasc)) {
			throw new CadastroVazioException(ERRO_CADASTRO,"Data de Nascimento");
		}

		if (stringInvalida(email)) {
			throw new CadastroVazioException(ERRO_CADASTRO, "Email");
		}
		checaNome(nome, ERRO_CADASTRO);
		checaEmail(email, ERRO_CADASTRO);
		checaData(dataNasc, ERRO_CADASTRO);
		checaIdade(dataNasc, ERRO_CADASTRO);

		this.nome = nome;
		this.dataNasc = dataNasc;
		this.email = email;
		this.hospedado = hosp;
		this.estadias = new HashMap<String, Estadia>();
		this.cartaoFidelidade = new Cartao();

	}

	/**
	 * Construtor para quando o cliente nao estiver hospedado
	 * @throws CadastroVazioException 
	 * @throws CadastroInvalidoException 
	 * @throws IdadeInvalidaException 
	 */
	public Cliente(String nome, String email, String dataNasc) throws StringInvalidaException, ValorInvalidoException, CadastroVazioException, CadastroInvalidoException, IdadeInvalidaException {
		this(nome, email, dataNasc, false);
	}

	/**
	 * Retorna a quantidade de estadias do cliente
	 * 
	 * @return a quantidade de estadias
	 */
	public int qtdEstadias() {
		return this.estadias.size();
	}

	/**
	 * Atualiza o cadastro do usuario
	 * 
	 * @param tipoInformacao
	 *            A informacao a ser atualizada
	 * @param valor
	 *            O novo valor da informacao
	 * @throws Exception
	 *             Quando a informacao requerida nao existe no usuario
	 * @throws StringInvalidaException
	 *             Quando ha alguma string invalida (null ou vazia)
	 */
	public void atualizaCadastro(String tipoInformacao, String valor)throws SistemaException, StringInvalidaException {
	
		if (stringInvalida(tipoInformacao)) {
			throw new StringInvalidaException();
		}
		if (tipoInformacao.equalsIgnoreCase("Nome")) {
			if (stringInvalida(valor)) {
				throw new CadastroInvalidoException(ERRO_ATUALIZACAO, "Nome do(a) hospede nao pode ser vazio.");
			}
			checaNome(valor, ERRO_ATUALIZACAO);

			this.setNome(valor);
		} else if (tipoInformacao.equalsIgnoreCase("Data de Nascimento")) {

			if (stringInvalida(valor)) {
				throw new CadastroInvalidoException(ERRO_ATUALIZACAO, "Data de Nascimento do(a) hospede nao pode ser vazio.");
			}
			checaData(valor, ERRO_ATUALIZACAO);
			checaIdade(valor, ERRO_ATUALIZACAO);
			this.setDataNasc(valor);
		} else if (tipoInformacao.equalsIgnoreCase("Email")) {

			if (stringInvalida(valor)) {
				throw new CadastroInvalidoException(ERRO_ATUALIZACAO, "Email do(a) hospede nao pode ser vazio.");
			}
			checaEmail(valor, ERRO_ATUALIZACAO);
			this.setEmail(valor);
		}
		
		
	}
	
	
	
	public void addPontos(double gastos){
		cartaoFidelidade.addPontos(gastos);
			
	}
	
	public double convertePontos(int pontos) throws ConvertePontosException{
		return this.cartaoFidelidade.convertePontos(pontos);
	}
	

	/**
	 * Retorna uma determinada informacao do usuario
	 * 
	 * @param tipoInformacao
	 *            define qual informacao deve ser retornada
	 * @return um string contendo a informacao requerida
	 * @throws Exception
	 *             quando um tipoInformacao nao existe no cliente
	 * @throws StringInvalidaException
	 *             quando ha uma string invalida
	 */
	public String getInfoHospede(String tipoInformacao) throws Exception, StringInvalidaException {
		if (stringInvalida(tipoInformacao)) {
			throw new StringInvalidaException();
		}
		if (tipoInformacao.equalsIgnoreCase("Nome")) {
			return this.getNome();
		} else if (tipoInformacao.equalsIgnoreCase("Data de Nascimento")) {
			return this.getDataNasc();
		} else if (tipoInformacao.equalsIgnoreCase("Email")) {
			return this.getEmail();
		} else if (tipoInformacao.equalsIgnoreCase("Pontos")){
			return String.format("%d",this.cartaoFidelidade.getPontos());
		}
		else {
			throw new Exception(CLIENTE_SEM_INFORMACAO);
		}
	}

	/**
	 * Obtem informacoes das estadias do usuario
	 * @param info
	 * 		Define a informacao
	 * @return
	 * 		a informacao requerida
	 * @throws Exception
	 * @throws StringInvalidaException
	 * 		Quando a string for invalida ou nula
	 */
	public String getInfoHospedagem(String info) throws Exception, StringInvalidaException {
		if (stringInvalida(info)) {
			throw new StringInvalidaException();
		} else if (info.equalsIgnoreCase("Hospedagens ativas")) {
			return "" + this.estadias.size();
		} else if (info.equalsIgnoreCase("Quarto")) {
			return IDquartos();
		} else if (info.equalsIgnoreCase("Total")) {
			return String.format("R$%.2f", totalEstadias());

		} else {
			throw new Exception(CLIENTE_SEM_INFORMACAO);
		}
	}

	public boolean stringInvalida(String string) {
		return (string.trim().isEmpty() || string == null);

	}

	private String IDquartos() {
		String saida = "";
		ArrayList<String> idQuartos = new ArrayList<String>();
		for (Entry<String, Estadia> entry : this.estadias.entrySet()) {
			idQuartos.add(entry.getValue().getIDQuarto());
		}
		Collections.sort(idQuartos);
		for (int i = 0; i < idQuartos.size(); i++) {
			if (i < idQuartos.size() - 1) {
				saida += idQuartos.get(i) + ",";
			} else {
				saida += idQuartos.get(i);
			}
		}
		return saida;
	}

	/**
	 * Calcula o valor total das estadias
	 * @return
	 * 		O valor das estadias
	 * @throws Exception
	 */
	public double totalEstadias() throws Exception {
		double total = 0.0;
		for (Entry<String, Estadia> entry : this.estadias.entrySet()) {
			total += entry.getValue().calculaValorEstadia();
		}
		return total;
	}

	/**
	 * Adiciona uma estadia no HashMap de Estadias do Cliente
	 * 
	 * @param novaEstadia
	 */
	public void adicionaEstadia(Estadia novaEstadia) {
		this.estadias.put(novaEstadia.getIDQuarto(), novaEstadia);
		setHospedado(true);
	}

	/**
	 * Retira uma estadia do cliente
	 * @param estadia
	 * 		Especifica a estadia
	 * @throws Exception
	 */
	public void removeEstadia(Estadia estadia) throws Exception {
		String id = estadia.getIDQuarto();
		if (!this.estadias.containsKey(id)) {
			throw new Exception("Cliente nao possui estadia no quarto especificado.");
		}
		this.estadias.remove(id);
		if (this.estadias.size() == 0) {
			setHospedado(false);
		}
	}

	/**
	 * Esta hospedado?
	 * @return
	 * 		True se verdade, false cc. 
	 */
	public boolean isHospedado() {
		return hospedado;
	}

	public void setHospedado(boolean hospedado) {
		this.hospedado = hospedado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public HashMap<String, Estadia> getEstadia() {
		return this.estadias;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	/**
	 * Dois clientes sao iguais se possuirem o mesmo nome e email
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Cliente) {
			Cliente cliente = (Cliente) obj;
			if (this.getEmail().equals(cliente.getEmail()) || this.getNome().equals(cliente.getNome())) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	private void checaNome(String nome, String erro) throws CadastroVazioException, CadastroInvalidoException {
		if (!nome.matches("[a-zA-Z ]*")) {
			throw new CadastroInvalidoException(erro,"Nome do(a) hospede esta invalido.");
		}
		
	}
	

	public void checaEmail(String email, String erro) throws CadastroInvalidoException {
		if (!email.matches("[ a-zA-Z]+@[ a-zA-Z]+\\.[ a-zA-Z]+")
				&& !email.matches("[ a-zA-Z]+@[ a-zA-Z]+\\.[ a-zA-Z]+\\.[ a-zA-Z]+")) {
			throw new CadastroInvalidoException(erro,"Email do(a) hospede esta invalido.");
		}
	}

	private void checaData(String data, String erro) throws CadastroInvalidoException {
		if (!data.matches("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[0-2])/\\d{4}")) {
			throw new CadastroInvalidoException(erro,"Formato de data invalido.");		}
	}

	private void checaIdade(String data, String msg) throws IdadeInvalidaException {
		String ano = data.split("/")[2];
		if (Integer.parseInt(ano) > 1998) {
			throw new IdadeInvalidaException(msg);
		}
	}
	
	public double aplicaDesconto(double preco){
		return cartaoFidelidade.aplicaDesconto(preco);
	}
}