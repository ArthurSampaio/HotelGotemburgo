package quarto;

import excecoes.StringInvalidaException;
import excecoes.ValorInvalidoException;

/**
 * SuperClasse quarto
 * 
 * @author Arthur Sampaio
 * @author Tiago Pereira
 */
public class Quarto{
	private String ID;
	private TipoDeQuarto diaria;
	
	/**
	 * Construtor da Classe
	 * @param id
	 * 		ID do quarto (numero do quarto)
	 * @throws StringInvalidaException
	 * 		Quando uma ID eh invalida, nula ou vazia
	 */
	public Quarto(String id, TipoDeQuarto tipo)throws StringInvalidaException{
		if(id == null || id.trim().isEmpty()){
			throw new StringInvalidaException("O ID nao pode ser vazio ou nulo");
		}
		this.ID = id;
		this.diaria = tipo;
			
	}
	


	/**
	 * Retorna o valor da diaria de um quarto dado um desconto
	 * @param desconto
	 * 		Desconto para o calculo da diaria
	 * @return
	 * 		Um double informando o valor da diaria com desconto
	 * @throws ValorInvalidoException
	 * 		Quando o desconto for menor que zero
	 */
	public double getDiaria(double desconto) throws ValorInvalidoException{
		return this.diaria.getDiaria()*(1-desconto);
	}

	/**
	 * Retorna o valor da diaria sem desconto
	 * @return
	 * 		Valor da diaria sem desconto
	 */
	public double getDiaria()throws ValorInvalidoException{
		return this.getDiaria(0);
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		return result;
	}

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
	
	
	


