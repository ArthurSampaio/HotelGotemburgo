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

	/**
	 * Aplica desconto em uma certa compra
	 * @param gasto
	 * 		Valor da transacao.
	 * @return 
	 *		Retorna o desconto dependendo do tipo de cartao
	 */
	public double aplicaDesconto(double gasto);

	public double convertePontos(int pt);
	
	public String toString();
	
}
