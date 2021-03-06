package prato;

import java.util.ArrayList;
import java.util.List;

import excecoes.SistemaException;

/**
 * Classe representando uma refeicao completa
 * 
 * @author Joao Mauricio Carvalho
 *
 */

public class Refeicao extends ItemCardapio {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3891465390648044966L;
	private List<Prato> menu;
	private static final double DESCONTO = 0.9;

	/**
	 * Construtor da Classe
	 * 
	 * @param nome
	 *            Nome da refeicao
	 * @param descricao
	 *            Descricao da refeicao
	 * @param pratos
	 *            Conjuto de pratos que compoem a refeicao
	 * @throws SistemaException
	 */
	public Refeicao(String nome, String descricao, ArrayList<Prato> pratos) throws SistemaException {
		super(nome, descricao);

		this.menu = pratos;
	}

	/**
	 * Obtem os pratos da refeicao
	 * 
	 * @return Uma lista com todos os pratos que compoem a refeicao
	 */
	public List<Prato> getMenu() {
		return this.menu;
	}

	/**
	 * Obtem o preco da refeicao
	 * 
	 * @return Retorna a soma do preco de todos os pratos e aplica um desconto
	 *         de 10%
	 */
	public double getPreco() {
		double preco = 0.0;
		for (int i = 0; i < menu.size(); i++) {
			preco += menu.get(i).getPreco();
		}
		return preco * DESCONTO;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getDescricao() == null) ? 0 : getDescricao().hashCode());
		result = prime * result + ((getMenu() == null) ? 0 : getMenu().hashCode());
		result = prime * result + ((getNome() == null) ? 0 : getNome().hashCode());
		return result;
	}

	public String toString() {
		String saida = "";
		saida += this.getDescricao() + " Serao servidos:";

		for (int i = 0; i < this.menu.size(); i++) {
			saida += " (" + (i + 1) + ") " + menu.get(i).getNome();
			if (i > menu.size() - 2) {
				saida += ".";
			} else {
				saida += ",";
			}

		}
		return saida;
	}

	@Override
	/**
	 * Uma refeicao eh igual a outra se possui mesmo nome descricao e pratos.
	 */
	public boolean equals(Object obj) {
		if (obj instanceof Refeicao) {
			Refeicao outra = (Refeicao) obj;
			if (super.getNome().equals(outra.getNome()) && super.getDescricao().equals(outra.getDescricao())) {
				for (int i = 0; i < this.menu.size();) {
					if (!this.menu.get(i).equals(outra.getMenu().get(i))) {
						return false;
					}
					return true;
				}
			}
		}
		return false;
	}
}
