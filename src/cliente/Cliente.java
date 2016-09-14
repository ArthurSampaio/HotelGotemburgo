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
		
	//FALTA, o getInfoHospede(String)
	//atualizaCadastro
	//
	
	private boolean stringInvalida(String string){
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
	

}