package restaurante;
 
import java.util.ArrayList;
 
import excecoes.StringInvalidaException;
import excecoes.ValorInvalidoException;
import prato.Prato;
import prato.Refeicao;
 
public class FactoryItemCardapio {
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
 
	public Refeicao criaRefeicao(String nome, String descricao, ArrayList<Prato> pratos) throws Exception{
		if(nome.trim().isEmpty() || nome == null){
			throw new StringInvalidaException();
		}
		if (descricao.trim().isEmpty() || descricao == null) {
			throw new StringInvalidaException();
		}
		if (pratos.size() != 3 && pratos.size() != 4){
			throw new Exception();
		}
 
		Refeicao novaRefeicao = new Refeicao (nome, descricao, pratos);
		return novaRefeicao;
	}
 
 
}