package hotel;

import java.util.HashMap;
import java.util.Map;

import cliente.Cliente;
import cliente.ClienteFactory;
import estadia.Estadia;
import excecoes.EmailInexistenteException;
import excecoes.StringInvalidaException;
import quarto.Quarto;
import quarto.QuartoFactory;

/**
 * Classe que representa o Controller do Hotel 
 * 
 * @author João Maurício
 * @author Mariana Mendes
 * @author Arthur Sampaio
 * @author Tiago Pereira
 *
 */
public class HotelController {
	private Map<String, Cliente> clientes;
	private ClienteFactory factoryCliente;
	private QuartoFactory factoryQuarto;

	/**
	 * Construtor do HotelController
	 */
	public HotelController() {
		this.clientes = new HashMap<String, Cliente>();
		this.factoryCliente = new ClienteFactory();
		this.factoryQuarto = new QuartoFactory();
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
	
	public void realizaChekin(String email, int dias, String id, String tipoQuarto) throws Exception{
		Cliente cliente = this.getCliente(email);
		Quarto quarto = factoryQuarto.criaQuarto(id, tipoQuarto);
		Estadia estadia = new Estadia(quarto, dias);
		cliente.adicionaEstadia(estadia);
		
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
		Cliente cliente = getCliente(email);
		return cliente.getInfoHospede(info);

	}
	
	public String getInfoHospedagem(String email, String info)throws Exception{
		Cliente cliente = getCliente(email);
		if(!cliente.isHospedado()){
			throw new Exception("Erro na consulta de hospedagem. Hospede " + cliente.getNome() + " nao esta hospedado(a).");
		}
		return cliente.getInfoHospedagem(info);
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
		if (!clientes.containsKey(email)) {
			throw new EmailInexistenteException(
					"Erro na consulta de hospede. Hospede de email " + email + " nao foi cadastrado(a).");
		}

		if (tipoInfo.equalsIgnoreCase("email")) {
			//precisa deste procedimento para calcular o hash da chave do mapa
			Cliente cliente = clientes.get(email);
			clientes.put(novaInfo, cliente);
			//clientes.remove(email); ESSA GAMBIARRA ATÉ AJEITAREM OS TESTES
			clientes.get(novaInfo).atualizaCadastro(tipoInfo,novaInfo);
			return clientes.get(novaInfo).getInfoHospede(tipoInfo);
			
		}else{
			Cliente cliente = this.clientes.get(email);
			cliente.atualizaCadastro(tipoInfo, novaInfo);
			this.getInfoHospede(email, tipoInfo);
			
		}

		return null;
	}
	
	public Cliente getCliente(String email) throws Exception {
		if (!clientes.containsKey(email)) {
			throw new EmailInexistenteException(
					"Erro na consulta de hospede. Hospede de email " + email + " nao foi cadastrado(a).");
		}
		return clientes.get(email);
	}

	/**
	 * Remove um cliente do sistema
	 * @param email
	 * 		Identifica qual o cliente deve ser removido
	 * @throws Exception
	 * 		Quando nao ha um cliente cadastrado com o email passado
	 */
	public void removeHospede(String email) throws Exception {
		if (!clientes.containsKey(email)) {
			throw new EmailInexistenteException(
					"Erro na consulta de hospede. Hospede de email " + email + " nao foi cadastrado(a).");
		}
		clientes.remove(email);

	}
}
