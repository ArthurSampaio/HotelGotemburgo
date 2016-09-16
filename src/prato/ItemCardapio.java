package prato;

import excecoes.StringInvalidaException;

public abstract class ItemCardapio {
	private String nome;
	private String descricao;
	
	public ItemCardapio(String nome, String descricao) throws StringInvalidaException{
		if(nome.trim().isEmpty() || nome == null){
			throw new StringInvalidaException();
		}
		
		if(descricao.trim().isEmpty() || nome == null){
			throw new StringInvalidaException();
		}
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

	public abstract double getPreco();
	
	public boolean equals(Object outro){
		if (outro instanceof  ItemCardapio){
			ItemCardapio outroItem = (ItemCardapio)outro;
			return (this.nome.equalsIgnoreCase(outroItem.getNome()) && this.descricao.equalsIgnoreCase(outroItem.getDescricao()));
		}return false;
	}
}
