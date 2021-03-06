package cartao;

import java.io.Serializable;

public class Premium implements CartaoInterface, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6186682839817913739L;
	private final double BONUS_PREMIUM = 0.30;

	/**
	 * O cartao premium numa compra acumula a parte inteira de 30% do total
	 * gasto, alem disso, o cartao soma mais 10 pontos a cada R$100,00.
	 */
	public int addPontos(double gastos) {
		double pontos = gastos * BONUS_PREMIUM;
		int totalPontos = (int) pontos;

		if (gastos > 100) {
			double adicional = gastos / 100;
			totalPontos += (int) adicional * 10;

		}
		return totalPontos;

	}

	public double aplicaDesconto(double preco) {
			return preco * 0.1;
	}

	public double convertePontos(int pt) {
		
		double pnts = BONUS_PREMIUM * pt;
		pnts += (pt/10) * 0.2;
		return pnts;
		
	}
	
	public String toString(){
		return "Premium";
	}

}
