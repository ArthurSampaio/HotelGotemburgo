package quarto;

import excecoes.StringInvalidaException;
import excecoes.ValorInvalidoException;

/**
 * Classe do tipo Simples. Herda da SuperClasse Quarto.
 * 
 * Representa um tipo de quarto dentro do Hotel Gotemburgo.
 * 
 * @author Arthur Sampaio
 * @author Tiago Pereira
 */
public class Simples extends Quarto {

	private String ID;
	private double valorDiaria;
	private final double DIARIA_SIMPLES = 100;
	
	/**
	 * Construtor da Classe
	 * @param id
	 * 		O ID do quarto, ou numero do Quarto
	 */	
	public Simples(String id)throws StringInvalidaException{
		super(id);
		this.valorDiaria = DIARIA_SIMPLES;
	}
	
	/**
	 * Calcula a diaria a partir de um desconto
	 * @param desconto
	 * 		O desconto da diaria na forma decimal
	 * @return
	 * 		o valor da diaria
	 */
	@Override
	public double getDiaria(double desconto)throws ValorInvalidoException {
		if(desconto < 0){
			throw new ValorInvalidoException("O valor da diaria nao pode ser inferior a zero.");
		}
		return this.getDiaria() * (1-desconto);
	}

	/**
	 * Retorna o valor da diaria
	 * @return
	 * 		Um double com o valor da diaria
	 */
	public double getDiaria() {
		return this.valorDiaria;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		return result;
	}

	/**
	 * Um quarto eh igual ao outro se possuirem o mesmo ID
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Luxo){
			Luxo quarto = (Luxo)obj;
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