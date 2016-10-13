package restaurante;

import java.io.Serializable;
import java.util.Comparator;

import excecoes.AtributoClienteException;
import prato.ComparadorNome;
import prato.ComparadorPreco;
import prato.ItemCardapio;

/**
 * Factory de Comparador para ordenacao especificada do Cardapio
 * 
 * @author Tiago Pereira
 *
 */
public class FactoryComparador implements Serializable { 

	/**
	 * 
	 */
	private static final long serialVersionUID = 7895975638809818155L;

	public Comparator<ItemCardapio> criaComparador(String criterioOrdenacao){
		if(!(criterioOrdenacao.equalsIgnoreCase("nome") || criterioOrdenacao.equalsIgnoreCase("preco"))){
			throw new AtributoClienteException("Criterio de ordenacao invalido.");
		}
		if(criterioOrdenacao.equalsIgnoreCase("nome")){
			return new ComparadorNome();
		}
		if(criterioOrdenacao.equalsIgnoreCase("preco")){
			return new ComparadorPreco();
		}
		return null;
	}
}
