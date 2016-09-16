package quarto;

/**
 * Interface que define um quarto no Hotel Gutemburgo
 * 
 * @author arthurspc
 *
 */
public interface Quarto {

	
	/**
	 * Sobrecarga do metodo calculaDiaria(double):double, para calcular a diaria sem desconto
	 * @return
	 * 		o valor da diaria
	 */
	double calculaDiaria()throws Exception;
	
	/**
	 * Calcula a diaria apartir de um desconto
	 * @param desconto
	 * 		O desconto da diaria na forma decimal
	 * @return
	 * 		o valor da diaria
	 */
	double calculaDiaria(double desconto)throws Exception;
	
	String getID();
}
