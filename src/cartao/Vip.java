package cartao;

public class Vip implements CartaoInterface {

	private final double BONUS_VIP = 0.50;

	/**
	  * O cartao VIP acumula a parte inteira de 50% do total
	 * gasto na compra.
	 */
	public int addPontos(double gastos) {

		double pontos = gastos * BONUS_VIP;
		int totalPontos = (int) pontos;
		return totalPontos;
	}

	public double aplicaDesconto(double desconto) {
		// TODO Auto-generated method stub
		return 0;
	}

}
