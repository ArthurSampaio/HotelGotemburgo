package restaurante;

import java.util.Comparator;

import excecoes.AtributoClienteException;
import excecoes.StringInvalidaException;
import prato.ComparadorNome;
import prato.ComparadorPreco;
import prato.ItemCardapio;

/**
 * Factory de Comparador para ordenacao especificada do Cardapio
 * 
 * @author Tiago Pereira
 *
 */
public class FactoryComparador {

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
