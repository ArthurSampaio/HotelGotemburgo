package restaurante;

import excecoes.StringInvalidaException;
import excecoes.ValorInvalidoException;
import prato.Entrada;
import prato.Prato;
import prato.Principal;
import prato.Sobremesa;
import prato.PetitFour;

public class FactoryPrato {
	public Prato criaPrato(String nome, String descricao, double preco, String tipo)throws Exception{
		if (nome.trim().isEmpty() || nome == null){
			throw new StringInvalidaException();
		}
		if (descricao.trim().isEmpty() || descricao == null){
			throw new StringInvalidaException();
		}
		if (preco < 0){
			throw new ValorInvalidoException();
		}
		if (tipo.equalsIgnoreCase("Sobremesa")){
			return criaSobremesa(nome, descricao, preco);
		}
		if (tipo.equalsIgnoreCase("Principal")){
			return criaPrincipal(nome, descricao, preco);
		}
		if (tipo.equalsIgnoreCase("Entrada")){
			return criaEntrada(nome, descricao, preco);
		}
		if (tipo.equalsIgnoreCase("Petit Four")){
			return criaPetitFour(nome, descricao, preco);
		}
		else{
			throw new Exception ("Tipo invalido");
		}
	}
	
	private Prato criaPrincipal(String nome, String descricao, double preco) throws Exception {
		Prato prato = new Principal(nome, descricao, preco);
		return prato;
	}

	private Prato criaSobremesa(String nome, String descricao, double preco) throws Exception{
		Prato prato = new Sobremesa(nome, descricao, preco);
		return prato;
	}
	
	private Prato criaEntrada(String nome, String descricao, double preco) throws Exception{
		Prato prato = new Entrada(nome, descricao, preco);
		return prato;
	}
	
	private Prato criaPetitFour(String nome, String descricao, double preco) throws Exception{
		Prato prato = new PetitFour(nome, descricao, preco);
		return prato;
	}
}
