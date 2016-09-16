package hotel;

import easyaccept.EasyAccept;
import restaurante.Restaurante;

public class HotelFacade {
	private HotelController gerencia;
	private Restaurante restaurante;

	public HotelFacade() {
		this.gerencia = new HotelController();
		this.restaurante = new Restaurante();

	}

	public void iniciaSistema() {
	}

	public String cadastraHospede(String nome, String email, String data) {
		try {
			return gerencia.cadastraHospede(nome, email, data);
		} catch (Exception e) {
			return e.getMessage();

		}

	}

	public String getInfoHospede(String email, String info) throws Exception {
		return gerencia.getInfoHospede(email, info);
	}

	public String atualizaCadastro(String email, String tipoInfo, String novaInfo) {
		try {
			return gerencia.atualizaCadastro(email, tipoInfo, novaInfo);
		} catch (Exception e) {
			return e.getMessage();
		}

	}
	
	

	public String getHospede(String email, String atributo) throws Exception {
		return gerencia.getHospede(email, atributo);
	}

	public void removeHospede(String email) {
		try {
			gerencia.removeHospede(email);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void cadastraPrato(String nome, double preco, String descricao) {
		try {
			restaurante.cadastraPrato(nome, preco, descricao);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void fechaSistema() {

	}

	public static void main(String[] args) {
		args = new String[] { "hotel.HotelFacade", "acceptance_test/testes_uc1.txt", "acceptance_test/testes_uc4.txt" };
		EasyAccept.main(args);

	}
}
