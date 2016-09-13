package quarto;

import excecoes.StringInvalidaException;
import excecoes.ValorInvalidoException;

/**
 * Classe Quarto do Sistema do Hotel Gotemburgo
 * @author Arthur Sampaio
 *
 */
public class Quarto {
	
	protected String ID;
	protected double diaria;
	
	/**
	 * Construtor da Classe
	 * @param id
	 * 		O ID do quarto, ou numero do Quarto
	 * @param valor
	 * 		Valor da diaria
	 */	
	public Quarto(String id, double valor)throws StringInvalidaException, ValorInvalidoException{
		if(id == null || id.trim().isEmpty()){
			throw new StringInvalidaException();
		}
		if(valor < 0){
			throw new ValorInvalidoException();
		}
		this.ID = id;
		this.diaria = valor;
		
	}

	/**
	 * Obtem o Id do quarto
	 * @return
	 * 		Uma String com o ID do quarto
	 */
	public String getID() {
		return ID;
	}

	/**
	 * Altera o ID do quarto
	 * @param iD
	 * 		O novo ID do quarto
	 */
	public void setID(String id) throws StringInvalidaException{
		if(id == null || id.trim().isEmpty()){
			throw new StringInvalidaException();
		}
		this.ID = id;
	}

	/**
	 * Retorna o valor da diaria
	 * @return
	 * 		Um double com o valor da diaria
	 */
	public double getDiaria() {
		return diaria;
	}

	/**
	 * Altera o valor da diaria
	 * @param diaria
	 * 		novo valor da diaria
	 */
	public void setDiaria(double diaria) throws ValorInvalidoException{
		if(diaria < 0){
			throw new ValorInvalidoException("O valor da diaria nao pode ser negativo");
		}
		this.diaria = diaria;
	}
	
	/**
	 * Calcula a diaria apartir de um desconto
	 * @param desconto
	 * 		O desconto da diaria na forma decimal
	 * @return
	 */
	public double calculaDiaria(double desconto){
		return this.diaria * (1 - desconto);
	}

	/**
	 * Calcula o valor da diaria sem desconto
	 * @return
	 * 		o valor da diaria
	 */
	public double calculaDiaria(){
		return this.calculaDiaria(0);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		return result;
	}

	/**
	 * Um quarto é igual ao outro se possuirem o mesmo ID
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Quarto){
			Quarto quarto = (Quarto)obj;
			if(quarto.getID().equals(this.getID())){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	
	
	

}
