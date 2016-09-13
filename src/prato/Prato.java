package prato;

import excecoes.StringInvalidaException;
import excecoes.ValorInvalidoException;

/**
 * 
 * @author Mariana Mendes
 *
 */
public class Prato {

	/**
	 * Atributos que cada Prato deve possuir.
	 */
	private String nome;
	private String descricao;
	private double preco;

	/**
	 * Construtor de um Prato.
	 * 
	 * @param nome
	 *            Nome do prato.
	 * @param descricao
	 *            Descricao do prato.
	 * @param preco
	 *            Preco do base do prato.
	 * @throws StringInvalidaException,
	 *             ValorInvalidoException
	 */
	public Prato(String nome, String descricao, double preco) throws Exception {
		if (nome.trim().isEmpty() || nome == null) {
			throw new StringInvalidaException();
		}

		if (descricao.trim().isEmpty() || descricao == null) {
			throw new StringInvalidaException();
		}

		if (preco < 0) {
			throw new ValorInvalidoException();
		}
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
	}

	/**
	 * 
	 * @return Nome de um prato.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * 
	 * @param nome
	 *            Altera nome de um prato.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * 
	 * @return Descricao de um prato.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * 
	 * @param descricao
	 *            Altera descricao de um prato.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * 
	 * @return Preco base de um prato.
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
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		long temp;
		temp = Double.doubleToLongBits(preco);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	
	/**
	 * Dois pratos serao iguais se possuirem o mesmo preco base e nome.
	 */
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Prato) {
			Prato prato = (Prato) objeto;

			return (this.preco == prato.getPreco() && this.nome.equalsIgnoreCase(prato.getNome()));
		}
		return false;
	}
	
	
}

