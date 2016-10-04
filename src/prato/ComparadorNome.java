package prato;

import java.util.Comparator;

/**
 * Classe que compara ItemCardapio por nome
 * @author Tiago Pereira
 *
 */
public class ComparadorNome implements Comparator<ItemCardapio> {

	public ComparadorNome(){
	}
	
	/**
	 * Compara por nome
	 */
	public int compare(ItemCardapio o1, ItemCardapio o2) {
		return o1.getNome().compareTo(o2.getNome());
		
	}
}
