package cartao;

/**
 * 
 * @author m4reana
 *
 */

public interface CartaoInterface {

	/**
	 * 
	 * @param gasto
	 *            Gasto do hospede em estadias, quartos e/ou restaurante.
	 * @return Pontos a serem adicionados no cartao fidelidade
	 */
	public int addPontos(double gasto);

	public double aplicaDesconto(double desconto);

	public double convertePontos(int pt);
	
	public String toString();
	
}
