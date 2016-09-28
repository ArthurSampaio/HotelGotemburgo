package cartao;

/**
 * 
 * @author m4reana
 *
 */

/**
 * 
 * Classe cartao representando o cartao fidelidade que um hospede vai possuir,
 * para garantir beneficios, que podem variar de acordo com o tipo do cartao,
 * Padrao, Premium ou Vip.
 *
 */
public class Cartao {

	private int pontos;
	private CartaoInterface cartao;

	/**
	 * Construtor do cartao.
	 * 
	 * @param pontos
	 *            os pontos do cartao de um hospede incia em zero.
	 * @throws Exception
	 */
	public Cartao(int pontos) throws Exception {
		if (pontos < 0) {
			throw new Exception("Valor invalido.");
		}

		this.pontos = 0;
		this.cartao = new Padrao();

	}

	/**
	 * Metodo que vai adicionar pontos acumulados numa compra de acordo com o
	 * tipo do cartao, padrao, premium ou vip.
	 * 
	 * @param gastos
	 */
	public void addPontos(double gastos) {
		int pontosObtidos = this.cartao.addPontos(gastos);
		this.setPontos(getPontos() + pontosObtidos);
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

}
