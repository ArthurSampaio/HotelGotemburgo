package cartao;

import excecoes.ConvertePontosException;
import excecoes.ValorInvalidoException;

/**
 * 
 * @author m4reana
 * @author Arthur Sampaio
 * @author Joao Mauricio
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
			throw new ValorInvalidoException();
		}

		this.pontos = 0;
		this.cartao = new Padrao();

	}

	/**
	 * Checa os pontos e atualiza tipo de cartao caso necessario.
	 */
	private void checaPontos() {
		if (this.pontos <= 350) {
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
	
	
	/**
	 * Calcula e retorna o valor da conversao de pontos para reais
	 * @param pt
	 * 		A quantidade de pontos a serem convertidas
	 * @return
	 * 		o valor em reais dos pontos convertidos
	 * @throws ConvertePontosException
	 * 		Quando a quantidade de pontos a ser convertida eh maior que a quantidade de pontos do cartao
	 */
	public double convertePontos(int pt)throws ConvertePontosException{
		if (pt > this.getPontos()){
			throw new ConvertePontosException();
		}else{
			//Armazena o valor convertido dos pontos
			double grana = this.cartao.convertePontos(pt);
			//Decresce a quantidade de pontos
			this.setPontos(this.getPontos() - pt);
			//Checa o tipo do cartão
			this.checaPontos();
			//retorna o valor em reais dos pontos convertidos
			return grana;
			
		}
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
		this.checaPontos();
		
	}
	
	/**
	 * Aplica desconto numa compra dependendo do tipo de cartao do cliente
	 * @param preco
	 * 		Preco da compra
	 * @return
	 * 		Compra com desconto aplicado
	 */
	public double aplicaDesconto(double preco){
		return cartao.aplicaDesconto(preco);
	}
	
	public String toString(){
		return cartao.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + pontos;
		return result;
	}

	/**
	 * Dois cartaoes sao iguais se possuirem a mesma quantidade de pontos
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Cartao){
			Cartao cartao = (Cartao)obj;
			if(cartao.getPontos() == this.getPontos()){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}	
}
