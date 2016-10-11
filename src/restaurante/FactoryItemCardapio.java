package restaurante;

import java.util.ArrayList;

import excecoes.SistemaException;
import prato.Prato;
import prato.Refeicao;

public class FactoryItemCardapio {
	public Prato criaPrato(String nome, double preco, String descricao) throws SistemaException {

		Prato novoPrato = new Prato(nome, descricao, preco);
		return novoPrato;

	}

	public Refeicao criaRefeicao(String nome, String descricao, ArrayList<Prato> pratos) throws SistemaException {

		Refeicao novaRefeicao = new Refeicao(nome, descricao, pratos);
		return novaRefeicao;
	}

}