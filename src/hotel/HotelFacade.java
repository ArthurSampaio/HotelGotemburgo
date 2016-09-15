package hotel;

import easyaccept.EasyAccept;

public class HotelFacade {
	private HotelController gerencia;

	public HotelFacade() {
		this.gerencia = new HotelController();
		
	}
	
	
	public void iniciaSistema(){
	}

	public String cadastraHospede(String nome, String email, String data) {
		try {
			return gerencia.cadastraHospede(nome, email, data);
		} catch (Exception e) {
			return e.getMessage();

		}

	}

	public String getInfoHospede(String email, String info) throws Exception{
		return gerencia.getInfoHospede(email, info);
	}

	public String atualizaCadastro(String email, String tipoInfo, String novaInfo) {
		try {
			return gerencia.atualizaCadastro(email, tipoInfo, novaInfo);
		} catch (Exception e) {
			return e.getMessage();
		}

	}

	public void removeHospede(String email) {
		try {
			gerencia.removeHospede(email);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void fechaSistema(){
		
	}

	public static void main(String[] args) {
		args = new String[] { "hotel.HotelFacade", "acceptance_test/testes_uc1.txt" };
		EasyAccept.main(args);

	}
}
