package hotel;

import java.io.Serializable;

/**
 * Classe Transacao
 * @author Tiago Pereira
 *
 */
public class Transacao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4232120918575458099L;
	private String nomeCliente;
	private String emailCliente;
	private double valorTransacao;
	private String detalhe;
	
	/**
	 * Construtor da Classe
	 * @param nomeCliente
	 * 		Nome do cliente que realizou transacao
	 * @param emailCliente
	 * 		Email do Cliente que realizou Transacao
	 * @param valorTransacao
	 * 		Valor da transacao
	 * @param detalhe
	 * 		Detalhe da transacao, se foi um checkout ou um pedido no restaurante
	 */
	public Transacao(String nomeCliente, String emailCliente, double valorTransacao,
			String detalhe){
		this.nomeCliente = nomeCliente;
		this.emailCliente = emailCliente;
		this.valorTransacao = valorTransacao;
		this.detalhe = detalhe;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emailCliente == null) ? 0 : emailCliente.hashCode());
		return result;
	}

	/**
	 * Duas transacoes sao iguais se possuirem o mesmo email
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Transacao){
			Transacao outra = (Transacao) obj;
			if(this.emailCliente.equalsIgnoreCase(outra.getEmailCliente())){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	public double getValorTransacao() {
		return valorTransacao;
	}

	public void setValorTransacao(double valorTransacao) {
		this.valorTransacao = valorTransacao;
	}

	public String getDetalhe() {
		return detalhe;
	}

	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}
}
