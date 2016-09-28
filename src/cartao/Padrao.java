package cartao;

public class Padrao implements CartaoInterface {

	private final double BONUS_PADRAO = 0.10;

	/**
	 * O cartao padrao acumula a parte inteira de 10% do total
	 * gasto na compra.
	 * 
	 */
	public int addPontos(double gastos) {
		double pontos = gastos * BONUS_PADRAO;
		int totalPontos = (int) pontos;
		return totalPontos;
	}

	public double aplicaDesconto(double desconto) {
		// TODO Auto-generated method stub
		return 0;
	}

}
