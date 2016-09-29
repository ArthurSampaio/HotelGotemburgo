package cartao;

public class Premium implements CartaoInterface {

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

}
