package restaurante;
 
import java.util.ArrayList;

import excecoes.ItemCardapioInvalidoException;
import excecoes.SistemaException;
import excecoes.StringInvalidaException;
import excecoes.ValorInvalidoException;
import prato.Prato;
import prato.Refeicao;
 
public class FactoryItemCardapio {
	public Prato criaPrato(String nome, double preco, String descricao) throws SistemaException {
		if (nome.trim().isEmpty() || nome == null) {
			throw new ItemCardapioInvalidoException("Erro no cadastro do prato. Nome do prato esta vazio.");
		}
		if (descricao.trim().isEmpty() || descricao == null) {
			throw new ItemCardapioInvalidoException("Erro no cadastro do prato. Descricao do prato esta vazia.");
		}
		if (preco < 0) {
			throw new ValorInvalidoException("Erro no cadastro do prato. Preco do prato eh invalido.");
		}
 
		Prato novoPrato = new Prato(nome, descricao, preco);
		return novoPrato;
 
	}
 
	public Refeicao criaRefeicao(String nome, String descricao, ArrayList<Prato> pratos) throws SistemaException{
		if(nome.trim().isEmpty() || nome == null){
			throw new ItemCardapioInvalidoException("Erro no cadastro de refeicao. Nome da refeicao esta vazio.");
		}
		if (descricao.trim().isEmpty() || descricao == null) {
			throw new ItemCardapioInvalidoException("Erro no cadastro de refeicao. Descricao da refeicao esta vazia.");
		}
		if (pratos.size() != 3 && pratos.size() != 4){
			throw new ValorInvalidoException("Erro no cadastro de refeicao completa. Uma refeicao completa deve possuir no minimo 3 e no maximo 4 pratos.");
		}
		for(int i = 0; i < pratos.size(); i++){
			if (pratos.get(i) == null){
				throw new ItemCardapioInvalidoException("Erro no cadastro de refeicao. Componente(s) esta(o) vazio(s).");
			}
		}
 
		Refeicao novaRefeicao = new Refeicao (nome, descricao, pratos);
		return novaRefeicao;
	}
 
 
}