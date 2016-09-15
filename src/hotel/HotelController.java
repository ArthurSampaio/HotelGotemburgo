package hotel;

import java.util.HashMap;
import java.util.Map;

import cliente.Cliente;

public class HotelController {
	private Map<String, Cliente> clientes;

	public HotelController() {
		this.clientes = new HashMap<String, Cliente>();
	}

	public String cadastraHospede(String nome, String email, String data) throws Exception {
		Cliente novoCliente = new Cliente(nome, email, data);
		clientes.put(email, novoCliente);

		return email;

	}

	public String getInfoHospede(String email, String info) throws Exception {
		if (!clientes.containsKey(email)) {
			throw new Exception("Erro na consulta de hospede. Hospede de email " + email + " nao foi cadastrado(a).");
		}

		Cliente cliente = this.clientes.get(email);
		return cliente.getInfoHospede(info);

	}

	public String atualizaCadastro(String email, String tipoInfo, String novaInfo) throws Exception {
		if (novaInfo.trim().isEmpty() || novaInfo == null) {
			throw new Exception("Informacao invalida.");

		}

		if (tipoInfo.trim().isEmpty() || tipoInfo == null) {
			throw new Exception("Informacao invalida.");
		}

		if (tipoInfo.equalsIgnoreCase("nome")) {
			clientes.get(email).setNome(novaInfo);
			return novaInfo;
		}

		if (tipoInfo.equalsIgnoreCase("data de nascimento")) {
			clientes.get(email).setDataNasc(novaInfo);
			return novaInfo;
		}

		if (tipoInfo.equalsIgnoreCase("email")) {
			clientes.put(novaInfo, clientes.get(email));
			clientes.remove(email);
			clientes.get(novaInfo).setEmail(novaInfo);
			return novaInfo;
		}

		return null;
	}

	public void removeHospede(String email) throws Exception {
		if (!clientes.containsKey(email)) {
			throw new Exception("Erro na consulta de hospede. Hospede de email " + email + " nao foi cadastrado(a).");
		}
		clientes.remove(email);

	}
}
