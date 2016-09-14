package prato;

import excecoes.StringInvalidaException;

public abstract class ItensCardapio {
	private String nome;
	private String descricao;
	
	public ItensCardapio(String nome, String descricao) throws StringInvalidaException{
		if(nome.trim().isEmpty() || nome == null){
			throw new StringInvalidaException();
		}
		
		if(descricao.trim().isEmpty() || nome == null){
			throw new StringInvalidaException();
		}
	}
	
	public String getNome(){
		return this.nome;
	}
	
	public String getDescricao(){
		return this.descricao;
	}
	
	public abstract double getPreco();
}
