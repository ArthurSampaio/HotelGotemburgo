package hotel;

import easyaccept.EasyAccept;
import restaurante.RestauranteController;

public class HotelFacade {
	private HotelController gerencia;
	private RestauranteController restaurante;

	public HotelFacade() {
		this.gerencia = new HotelController();
		this.restaurante = new RestauranteController();

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
	
	public void realizaCheckin(String email, int dias, String idQuarto, String tipoQuarto) throws Exception{
		gerencia.realizaChekin(email, dias, idQuarto, tipoQuarto);
		
	}
	
	public String realizaCheckout(String email, String idQuarto) {
		try{
			return gerencia.realizaCheckout(email, idQuarto);
		}catch(Exception e){
			return e.getMessage();
		}
	}
	
	public String consultaTransacoes(String atributo){
		try{
			return gerencia.consultaTransacoes(atributo);
		}catch(Exception e){
			return e.getMessage();
		}
	}
	
	public String consultaTransacoes(String atributo, int indice){
		try{
			return gerencia.consultaTransacoes(atributo, indice);
		}catch(Exception e){
			return e.getMessage();
		}
	}

	public String getInfoHospede(String email, String info) throws Exception {
		return gerencia.getInfoHospede(email, info);
	}
	
	public String getInfoHospedagem(String email, String info) throws Exception {
		return gerencia.getInfoHospedagem(email, info);
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

	public void cadastraPrato(String nome, double preco, String descricao) throws Exception {
			restaurante.cadastraPrato(nome, preco, descricao);
	}

	public String consultaRestaurante(String nome, String info) throws Exception{
		return restaurante.consultaRestaurante(nome, info);
	}
		 	
		 	
	public void cadastraRefeicao(String nome, String descricao, String componentes) throws Exception{
		restaurante.cadastraRefeicao(nome, descricao, componentes);
	}
	
	
	public void fechaSistema() {

	}

	public static void main(String[] args) {
		args = new String[] { "hotel.HotelFacade", "acceptance_test/testes_uc1.txt", "acceptance_test/testes_uc2.txt","acceptance_test/testes_uc3.txt", "acceptance_test/testes_uc4.txt","acceptance_test/testes_uc4_exception.txt" };
		EasyAccept.main(args);

	}
}
