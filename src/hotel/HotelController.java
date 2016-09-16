package hotel;

import java.util.HashMap;
import java.util.Map;

import cliente.Cliente;
import cliente.ClienteFactory;
import excecoes.EmailInexistenteException;
import excecoes.StringInvalidaException;

/**
 * Classe que representa o Controller do Hotel 
 * 
 * @author João Maurício
 * @author Mariana Mendes
 * @author Arthur Sampaio
 *
 */
public class HotelController {
	private Map<String, Cliente> clientes;
	private ClienteFactory factoryCliente;

	/**
	 * Construtor do HotelController
	 */
	public HotelController() {
		this.clientes = new HashMap<String, Cliente>();
		this.factoryCliente = new ClienteFactory();
	}

	/**
	 * Cadastra um novo hospede no Sistema
	 * @param nome
	 * 		O nome do hospede
	 * @param email
	 * 		O email do hospede, precisa ser unico
	 * @param data
	 * 		O nascimento do usuário
	 * 
	 * @return
	 * 		O ID unico do usuario
	 * @throws Exception
	 * 		Quando há alguma string invalida
	 */
	public String cadastraHospede(String nome, String email, String data) throws Exception {
		Cliente novoCliente = this.factoryCliente.criaCliente(nome, email, data);
		clientes.put(email, novoCliente);
		return email;

	}


	/**
	 * Retorna uma determinada informacao do hospede
	 * 
	 * @param tipoInformacao
	 *            define qual informacao deve ser retornada
	 * @return um string contendo a informacao requerida
	 * @throws Exception
	 *             quando um tipoInformacao nao existe no cliente
	 * @throws StringInvalidaException
	 *             quando ha uma string invalida
	 * @throws EmailInexistenteException
	 * 				Quando nao há o email passado como parametro cadastrado no sistema            
	 */
	public String getInfoHospede(String email, String info) throws Exception {
		this.verificaEmail(email);
		Cliente cliente = this.clientes.get(email);
		return cliente.getInfoHospede(info);

	}

	/**
	 * Retorna uma determinada informacao do hospede
	 * 
	 * @param tipoInformacao
	 *          define qual informacao deve ser retornada
	 * @param novaInfor
	 * 			define o novo valor da informacao           
	 * @return um string contendo a informacao requerida
	 * 
	 * @throws Exception
	 *             quando um tipoInformacao nao existe no cliente
	 * @throws StringInvalidaException
	 *             quando ha uma string invalida
	 * @throws EmailInexistenteException
	 * 				Quando nao há o email passado como parametro cadastrado no sistema            
	 */
	public String atualizaCadastro(String email, String tipoInfo, String novaInfo) throws Exception {
		this.verificaEmail(email);

		if (tipoInfo.equalsIgnoreCase("email")) {
			//precisa deste procedimento para calcular o hash da chave do mapa
			clientes.put(novaInfo, clientes.get(email));
			clientes.remove(email);
			clientes.get(novaInfo).atualizaCadastro(tipoInfo,novaInfo);
			return clientes.get(novaInfo).getInfoHospede(tipoInfo);
			
		}else{
			Cliente cliente = this.clientes.get(email);
			cliente.atualizaCadastro(tipoInfo, novaInfo);
			this.getInfoHospede(email, tipoInfo);
			
		}

		return null;
	}

	/**
	 * Remove um cliente do sistema
	 * @param email
	 * 		Identifica qual o cliente deve ser removido
	 * @throws Exception
	 * 		Quando nao ha um cliente cadastrado com o email passado
	 */
	public void removeHospede(String email) throws Exception {
		this.verificaEmail(email);
		clientes.remove(email);

	}
	
	
	public String getHospede(String email, String atributo)throws Exception{
		
		return this.getInfoHospede(email, atributo);
		
		
		
	}
	
	/**
	 * Verifica se ha um determinado email dentro do Sistema
	 * @param email
	 * @throws Exception
	 */
	private void verificaEmail(String email)throws Exception{
		if (!clientes.containsKey(email)) {
			throw new EmailInexistenteException(
					"Erro na consulta de hospede. Hospede de email " + email + " nao foi cadastrado(a).");
		}
	}
}
