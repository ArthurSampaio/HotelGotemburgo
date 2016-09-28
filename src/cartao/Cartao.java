package cartao;

import excecoes.ValorInvalidoException;

/**
 * 
 * @author m4reana
 *
 */

/**
 * 
 * Classe cartao representando o cartao fidelidade que um hospede vai possuir
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
	public Cartao() throws ValorInvalidoException {
		if (pontos < 0) {
			throw new ValorInvalidoException("Valor invalido.");
		}

		this.pontos = 0;
		this.cartao = new Padrao();

	}

	private void checaPontos() {
		if (this.pontos < 350) {
			this.cartao = new Padrao();
		}else if(this.pontos < 1000){
			this.cartao = new Premium();
		}else{
			this.cartao = new Vip();
		}
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
		this.checaPontos();
		
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

}
