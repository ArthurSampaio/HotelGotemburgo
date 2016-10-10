package cliente;

import excecoes.AtributoClienteException;
import excecoes.FormatoInvalidoException;
import excecoes.SistemaException;

/**
 * Factory da Classe Cliente
 * 
 * @author Arthur Sampaio
 *
 */
public class ClienteFactory {
	
	
	public ClienteFactory(){}
	
	/**
	 * Cria um objeto cliente
	 * 
	 * @param nome
	 *            O nome do cliente
	 * @param dataNasc
	 *            A data de nascimento do cliente
	 * @param email
	 *            O Email do cliente
	 * @param hosp
	 *            Representa se esta hospedado ou nao
	 * @throws Exception
	 *             Quando alguma das strings é invalida.
	 */
	public Cliente criaCliente(String nome, String email, String dataNasc, boolean hosp) throws SistemaException, AtributoClienteException, Exception, FormatoInvalidoException {
		return new Cliente( nome, email, dataNasc,hosp);
	}
	
	/**
	 * Cria um cliente quando o cliente nao estiver hospedado
	 */
	public Cliente criaCliente(String nome, String email, String dataNasc) throws SistemaException, AtributoClienteException, Exception, FormatoInvalidoException{
		return new Cliente(nome,email,dataNasc);
	}

}
