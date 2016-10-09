package estadia;
import excecoes.QuartoInvalidoException;
import excecoes.SistemaException;
import excecoes.ValorInvalidoException;
import quarto.Quarto;

/**
 * Classe Estadia. Representa a estadia de um cliente durante um determinado periodo de tempo. 
 * 
 * @author Arthur Sampaio
 *
 */
public class Estadia {
	private Quarto quarto;
	private int dias;
	
	/**
	 * Construtor de Estadia
	 * @param quarto
	 * 		O quarto referente a estadia
	 * @param dias
	 * 		A quantidade de dias que estará no quarto
	 * @throws QuartoInvalidoException
	 * 		Quando o quarto não existir
	 * @throws ValorInvalidoException
	 * 		Quando a quantidade de dias for menor ou igual a zero. 
	 */
	public Estadia(Quarto quarto, int dias)throws QuartoInvalidoException, ValorInvalidoException{
		if(quarto == null){
			throw new QuartoInvalidoException();
		}
		if(dias <= 0){
			throw new ValorInvalidoException("Não é possível se hospedar durante 0 ou menos dias.");
		}
		
		this.quarto = quarto;
		this.dias = dias;
	}
	
	/**
	 * Calcula o valor total da estadia no quarto dado um desconto.
	 * @param desconto
	 * 		O valor do desconto
	 * @return
	 * 		O valor total da estadia dada a quantidade de dias definida na criação do objeto
	 */
	public double calculaValorEstadia(double desconto)throws Exception{
		return this.quarto.getDiaria(desconto) * this.dias;
	}
	
	/**
	 * Calcula o valor da estadia sem desconto.
	 * Sobrecarga do metodo calculaValorEstadia(double).
	 * @return
	 * 		O valor da estadia. 
	 */
	public double calculaValorEstadia()throws SistemaException{
		return this.quarto.getDiaria() * this.dias;
	}

	public Quarto getQuarto() {
		return quarto;
	}
	
	public String getIDQuarto(){
		return this.quarto.getID();
	}
		
	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}

	public int getDias() {
		return dias;
	}

	public void setDias(int dias) {
		this.dias = dias;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dias;
		result = prime * result + ((quarto == null) ? 0 : quarto.hashCode());
		return result;
	}



	/**
	 * Duas estadias são iguais se possuirem o mesmo quarto e a mesma quantidade de dias. 
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Estadia){
			Estadia estadia = (Estadia)obj;
			if(estadia.getDias() == this.getDias() && this.getQuarto().equals(estadia.getQuarto())){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	
	

}
