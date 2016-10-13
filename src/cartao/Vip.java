package cartao;

import java.io.Serializable;

public class Vip implements CartaoInterface, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4677519802543825379L;

	private final double BONUS_VIP = 0.50;

	private final double VALOR_CONVERSAO = 0.7;
	
	/**
	  * O cartao VIP acumula a parte inteira de 50% do total
	 * gasto na compra.
	 */
	public int addPontos(double gastos) {

		double pontos = gastos * BONUS_VIP;
		int totalPontos = (int) pontos;
		return totalPontos;
	}

	public double aplicaDesconto(double preco) {
		double desconto = preco * 0.15;
		if (preco >= 100){
			double extra = (preco/100);
			desconto += (int)extra * 10;
		}
		return desconto;
	}

	public double convertePontos(int pt) {
		double pnts = VALOR_CONVERSAO * pt;
		pnts += (pt/10) * BONUS_VIP;
		return pnts;
	}
	
	public String toString(){
		return "Vip";
	}

}
