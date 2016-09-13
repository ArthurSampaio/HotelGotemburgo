package prato;

import java.util.ArrayList;
import java.util.List;

import excecoes.StringInvalidaException;

/**
 * Classe representando uma refeicao completa
 * 
 * @author Joao Mauricio Carvalho
 *
 */

public class Refeicao {
	private String nome;
	private String descricao;
	private List<Prato> menu;
	
	
	/**
	 * Construtor da Classe
	 * @param nome
	 * 				Nome da refeicao
	 * @param descricao
	 * 				Descricao da refeicao
	 * @param pratos
	 * 				Conjuto de pratos que compoem a refeicao			
	 */
	public Refeicao(String nome, String descricao, ArrayList<Prato> pratos)throws Exception{
			if(nome.trim().isEmpty() || nome == null){
				throw new StringInvalidaException("Nome nao pode ser vazio");
			}
			if (descricao.trim().isEmpty() || descricao == null){
				throw new StringInvalidaException("Descricao nao pode ser vazia");
			}
			if (pratos == null){
				throw new Exception ("Prato vazio");
			}
			
			this.nome = nome;
			this.descricao = descricao;
			this.menu = pratos;
	}
	
	/**
	 * Obtem o nome da refeicao
	 * @return
	 * 		Uma string com o nome da refeicao
	 */
	public String getNome(){
		return this.nome;
	}
	
	/**
	 * Obtem descricao da refeicao
	 * @return
	 * 		Uma string com a descricao da refeicao
	 */
	public String getDescricao(){
		return this.descricao;
	}
	
	/**
	 * Obtem os pratos da refeicao
	 * @return
	 * 		Uma lista com todos os pratos que compoem a refeicao
	 */
	public List<Prato> getMenu(){
		return this.menu;
	}
	
	/**
	 * Obtem o preco da refeicao
	 * @return
	 * 		Retorna a soma do preco de todos os pratos e aplica um desconto de 10%
	 */
	public double getPreco(){
		double preco = 0.0;
		for (int i = 0; i < menu.size(); i++){
			preco += menu.get(i).getPreco();
		}
		return preco * 0.9;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((menu == null) ? 0 : menu.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	/**
	 * Uma refeicao eh igual a outra se possui mesmo nome descricao e pratos.
	 */
	public boolean equals(Object obj) {
		if (obj instanceof Refeicao){
			Refeicao outra = (Refeicao)obj;
			if (this.nome.equals(outra.getNome()) && this.descricao.equals(outra.getDescricao())){
				for (int i = 0; i < this.menu.size(); i++){
					if (! this.menu.get(i).equals(outra.getMenu().get(i))){
						return false;
					}
					return true;
				}
			}
		}
		return false;
	}	
}
	