package cliente;

import java.util.HashMap;

import excecoes.StringInvalidaException;
import estadia.Estadia;

/**
 * Classe que representa um cliente cadastrado no BD do Hotel Gutemburgo
 * @author Arthur Sampaio
 *
 */
public class Cliente {
	
	private boolean hospedado;
	private String nome; 
	private String dataNasc;
	private String email;
	private HashMap<String,Estadia> estadias;
	
	/**
	 * Construtor da Classe Cliente
	 * @param nome
	 * 		O nome do cliente
	 * @param dataNasc
	 * 		A data de nascimento do cliente
	 * @param email
	 * 		O Email do cliente
	 * @param hosp
	 * 		Representa se esta hospedado ou nao
	 * @throws Exception
	 * 		Quando alguma das strings é invalida. 
	 */
	public Cliente(String nome, String dataNasc, String email, boolean hosp)throws Exception{
		if(stringInvalida(nome)){
			throw new StringInvalidaException("Nome nao pode ser nulo ou vazio");
		}if(stringInvalida(dataNasc)){
			throw new StringInvalidaException("Data nao pode ser nula ou vazia");
		}
		if(stringInvalida(email)){
			throw new StringInvalidaException("O email nao pode ser nulo ou vazio");
		}
		
		this.nome = nome;
		this.dataNasc = dataNasc;
		this.email = email;
		this.hospedado = hosp;
		this.estadias = new HashMap<String,Estadia>();
		
	}

	/**
	 * Construtor para quando o cliente nao estiver hospedado
	 */
	public Cliente(String nome, String dataNasc, String email) throws Exception{
		this(nome,dataNasc,email, false);
	}
	
	/**
	 * Retorna a quantidade de estadias do cliente
	 * @return
	 * 		a quantidade de estadias
	 */
	public int qtdEstadias(){
		return this.estadias.size();
	}
	
	/**
	 * Atualiza o cadastro do usuario
	 * @param tipoInformacao
	 * 		A informacao a ser atualizada
	 * @param valor
	 * 		O novo valor da informacao
	 * @throws Exception
	 * 		Quando a informacao requerida nao existe no usuario
	 * @throws StringInvalidaException
	 * 		Quando ha alguma string invalida (null ou vazia)
	 */
	public void atualizaCadastro(String tipoInformacao, String valor)throws Exception,StringInvalidaException{
		if(stringInvalida(tipoInformacao) || stringInvalida(valor)){
			throw new StringInvalidaException();
		}
		if(tipoInformacao.equalsIgnoreCase("Nome")){
			 this.setNome(valor);
		}else if (tipoInformacao.equalsIgnoreCase("Data de Nascimento")){
			 this.setDataNasc(valor);
		}else if (tipoInformacao.equalsIgnoreCase("Email")){
			 this.setEmail(valor);
		}else{
			throw new Exception("O cliente nao possue a informacao especificada.");
		}
	}
			
	/**
	 * Retorna uma determinada informacao do usuario
	 * @param tipoInformacao
	 * 		define qual informacao deve ser retornada
	 * @return
	 * 		um string contendo a informacao requerida
	 * @throws Exception
	 * 		quando um tipoInformacao nao existe no cliente
	 * @throws StringInvalidaException
	 * 		quando ha uma string invalida
	 */
	public String getInfoHospede(String tipoInformacao)throws Exception,StringInvalidaException{
		if(stringInvalida(tipoInformacao)){
			throw new StringInvalidaException();
		}
		if(tipoInformacao.equalsIgnoreCase("Nome")){
			return this.getNome();
		}else if (tipoInformacao.equalsIgnoreCase("Data de Nascimento")){
			return this.getDataNasc();
		}else if (tipoInformacao.equalsIgnoreCase("Email")){
			return this.getEmail();
		}else{
			throw new Exception("O cliente nao possue a informacao especificada.");
		}
	}
	
	
	public boolean stringInvalida(String string){
		if(string == null || string.trim().isEmpty()){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * Adiciona uma estadia no HashMap de Estadias do Cliente
	 * @param novaEstadia
	 */
	public void adicionaEstadia(Estadia novaEstadia){
		this.estadias.put(novaEstadia.getIDQuarto(), novaEstadia);
	}
	

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

	public HashMap<String,Estadia> getEstadia(){
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
		if (obj instanceof Cliente){
			Cliente cliente = (Cliente)obj;
			if (this.getEmail().equals(cliente.getEmail()) || this.getNome().equals(cliente.getNome())){
				return true;
			}else{
				return false;
			}
		}else{
			return true;
		}
	}
	
	
	

}