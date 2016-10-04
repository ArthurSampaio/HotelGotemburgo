package prato;

import java.util.Comparator;

/**
 * Classe que compara ItemCardapio por preco
 * @author Tiago Pereira
 *
 */
public class ComparadorPreco implements Comparator<ItemCardapio> {

	public ComparadorPreco(){
	}
	
	/**
	 * Compara por preco
	 */
	public int compare(ItemCardapio o1, ItemCardapio o2) {
		return o1.compareTo(o2);
	}
}