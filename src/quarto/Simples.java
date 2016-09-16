package quarto;

import excecoes.StringInvalidaException;
import excecoes.ValorInvalidoException;

/**
 * Classe do tipo Simples. Implementa a interface Quarto.
 * Representa um tipo de quarto dentro do Hotel Gotemburgo.
 * 
 * @author Arthur Sampaio
 *
 */
public class Simples extends QuartoAbstract {

	private String ID;
	private ValorDeQuartos diaria;
	private static final double SEM_DESCONTO = 0;
	
	/**
	 * Construtor da Classe
	 * @param id
	 * 		O ID do quarto, ou numero do Quarto
	 */	
	public Simples(String id)throws StringInvalidaException{
		super(id);
		this.diaria = ValorDeQuartos.SIMPLES;
		
	}
	

	/**
	 * Calcula o valor da diaria sem descontoimplements
	 */
	@Override
	public double calculaDiaria() throws Exception{
		return this.calculaDiaria(SEM_DESCONTO);
	}

	/**
	 * Calcula a diaria apartir de um desconto
	 * @param desconto
	 * 		O desconto da diaria na forma decimal
	 * @return
	 * 		o valor da diaria
	 */
	@Override
	public double calculaDiaria(double desconto)throws Exception {
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
		return diaria.getValor();
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