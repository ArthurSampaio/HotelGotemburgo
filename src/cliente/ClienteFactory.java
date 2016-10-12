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
	 * Cria um cliente
	 * @param nome
	 * 		Nome do cliente
	 * @param email
	 * 		Email do cliente
	 * @param dataNasc
	 * 		Data de nascimento do cliente
	 * @param hosp
	 * 		Boolean indicando se esta hospedado ou nao
	 * @return
	 * 		O cliente criado
	 * @throws SistemaException
	 * @throws AtributoClienteException
	 * @throws FormatoInvalidoException
	 */
	public Cliente criaCliente(String nome, String email, String dataNasc, boolean hosp) throws SistemaException, AtributoClienteException, FormatoInvalidoException {
		return new Cliente( nome, email, dataNasc,hosp);
	}

	/**
	 * Cria um cliente sem indicar se ele esta hospedado ou nao
	 * @param nome
	 * 		Nome do cliente
	 * @param email
	 * 		Email do cliente
	 * @param dataNasc
	 * 		Data de nascimento do cliente
	 * @return
	 * 		O cliente criado
	 * @throws SistemaException
	 * @throws AtributoClienteException
	 * @throws FormatoInvalidoException
	 */
	public Cliente criaCliente(String nome, String email, String dataNasc) throws SistemaException, AtributoClienteException, FormatoInvalidoException{
		return new Cliente(nome,email,dataNasc);
	}

}
