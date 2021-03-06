package prato;

import excecoes.SistemaException;

/**
 * 
 * @author Mariana Mendes
 *
 */

/**
 * 
 * A classe prato herda de ItensCardapio.
 *
 */
public class Prato extends ItemCardapio {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8657385087012454372L;
	private double preco;

	/**
	 * Construtor de um Prato.
	 * 
	 * Os atributos nome e descricao sao herdados de ItensCardapio.
	 * 
	 * @param preco
	 *            Preco do base do prato.
	 * @throws SistemaException
	 */
	public Prato(String nome, String descricao, double preco) throws SistemaException {
		super(nome, descricao);

		this.preco = preco;
	}

	/**
	 * @return preco de um prato.
	 */
	public double getPreco() {
		return preco;
	}

	/**
	 * 
	 * @param preco
	 *            Altera preco base de um prato.
	 */
	public void setPreco(double preco) {
		this.preco = preco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getDescricao() == null) ? 0 : getDescricao().hashCode());
		result = prime * result + ((getNome() == null) ? 0 : getNome().hashCode());
		long temp;
		temp = Double.doubleToLongBits(preco);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	public String toString() {
		return this.getDescricao();
	}

	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Prato) {
			Prato prato = (Prato) objeto;

			return (this.preco == prato.getPreco() && this.getNome().equalsIgnoreCase(prato.getNome()));
		}
		return false;
	}

}
