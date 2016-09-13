package prato;


/**
 * Herda da superclasse Prato.
 * 
 * Representa um prato do tipo entrada no cardapio do restaurante.
 *
 * @author Mariana Mendes
 */

public class Entrada extends Prato{

	public Entrada(String nome, String descricao, double preco) throws Exception {
		super(nome, descricao, preco);
	}

}
