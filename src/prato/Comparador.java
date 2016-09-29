package prato;

import java.util.Comparator;

import excecoes.SistemaException;

/**
 * Classe para definir o tipo de comparacao de ItemCardapio
 * @author Tiago Pereira
 *
 */
public class Comparador implements Comparator<ItemCardapio> {

	private String tipoOrdenacao;
	
	/**
	 * Construtor da classe
	 * @param tipoOrdenacao
	 * 		O tipo de ordenacao requerido
	 * @throws SistemaException
	 * 		Quando a stringo eh nula ou vazia
	 */
	public Comparador(String tipoOrdenacao) throws SistemaException {
		if(tipoOrdenacao == null || tipoOrdenacao.trim().isEmpty()){
			throw new SistemaException("Argumento invalido.");
		}
		this.tipoOrdenacao = tipoOrdenacao;
	}
	
	/**
	 * Compara por nome e por preco
	 */
	@Override
	public int compare(ItemCardapio o1, ItemCardapio o2) {
		if(this.tipoOrdenacao.equalsIgnoreCase("Nome")){
			return o1.getNome().compareTo(o2.getNome());
		}
		if(this.tipoOrdenacao.equalsIgnoreCase("Preco")){
				return o1.compareTo(o2);
	}
		return 0;
	}
}
