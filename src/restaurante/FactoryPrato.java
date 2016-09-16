package restaurante;

import excecoes.StringInvalidaException;
import excecoes.ValorInvalidoException;
import prato.Prato;

public class FactoryPrato {
	public Prato criaPrato(String nome, double preco, String descricao) throws Exception {
		if (nome.trim().isEmpty() || nome == null) {
			throw new StringInvalidaException();
		}
		if (descricao.trim().isEmpty() || descricao == null) {
			throw new StringInvalidaException();
		}
		if (preco < 0) {
			throw new ValorInvalidoException();
		}

		Prato novoPrato = new Prato(nome, descricao, preco);
		return novoPrato;

	}

}
