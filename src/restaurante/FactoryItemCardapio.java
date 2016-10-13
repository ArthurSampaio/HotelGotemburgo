package restaurante;
 
import java.io.Serializable;
import java.util.ArrayList;

import excecoes.DescricaoItemCardapioException;
import excecoes.ItemCardapioInvalidoException;
import excecoes.NomeItemCardapioException;
import excecoes.SistemaException;
import excecoes.ValorInvalidoException;
import prato.Prato;
import prato.Refeicao;
 
public class FactoryItemCardapio implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5362149428900354101L;

	public Prato criaPrato(String nome, double preco, String descricao) throws SistemaException {
		if (nome.trim().isEmpty() || nome == null) {
			throw new NomeItemCardapioException("Nome do prato esta vazio.");
		}
		if (descricao.trim().isEmpty() || descricao == null) {
			throw new DescricaoItemCardapioException("Descricao do prato esta vazia.");
		}
		if (preco < 0) {
			throw new ValorInvalidoException("Preco do prato eh invalido.");
		}
 
		Prato novoPrato = new Prato(nome, descricao, preco);
		return novoPrato;
 
	}
 
	public Refeicao criaRefeicao(String nome, String descricao, ArrayList<Prato> pratos) throws SistemaException{
		if(nome.trim().isEmpty() || nome == null){
			throw new NomeItemCardapioException("Nome da refeicao esta vazio.");
		}
		if (descricao.trim().isEmpty() || descricao == null) {
			throw new DescricaoItemCardapioException("Descricao da refeicao esta vazia.");
		}
		if (pratos.size() != 3 && pratos.size() != 4){
			throw new ValorInvalidoException("Uma refeicao completa deve possuir no minimo 3 e no maximo 4 pratos.");
		}
		for(int i = 0; i < pratos.size(); i++){
			if (pratos.get(i) == null){
				throw new ItemCardapioInvalidoException("Componente(s) esta(o) vazio(s).");
			}
		}
 
		Refeicao novaRefeicao = new Refeicao (nome, descricao, pratos);
		return novaRefeicao;
	}
 
 
}