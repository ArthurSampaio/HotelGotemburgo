package prato;

import java.io.Serializable;

import excecoes.DescricaoItemCardapioException;
import excecoes.NomeItemCardapioException;

/**
 * Classe que representa um item do cardapio.
 * @author Joao Mauricio Carvalho
 * @author Mariana Mendes
 * @author Arthur Sampaio
 * @author Tiago Pereira
 *
 */

public abstract class ItemCardapio implements Comparable<ItemCardapio>, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2787317864336979797L;
	private String nome;
	private String descricao;
	
	/**
	 * Construtor da classe.
	 * @param nome
	 * 			Nome do item.
	 * @param descricao
	 * 			Descricao do item.
	 * @throws NomeItemCardapioException 
	 */
	public ItemCardapio(String nome, String descricao) throws NomeItemCardapioException, DescricaoItemCardapioException{
	
		this.nome = nome;
		this.descricao = descricao;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Classe que herdam tem seu propio calculo de preco.
	 * @return
	 * 		Preco do item.
	 */
	public abstract double getPreco();
	
	/**
	 * Itens sao iguais se tem mesmo nome e descricao.
	 */
	public boolean equals(Object outro){
		if (outro instanceof  ItemCardapio){
			ItemCardapio outroItem = (ItemCardapio)outro;
			return (this.nome.equalsIgnoreCase(outroItem.getNome()) && this.descricao.equalsIgnoreCase(outroItem.getDescricao()));
		}return false;
	}
	
	public int compareTo(ItemCardapio outro){
		if(this.getPreco() > outro.getPreco()){
			return 1;
		}
		if(this.getPreco() < outro.getPreco()){
			return -1;
		}
		return 0;
	}
}
