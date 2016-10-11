package hotel;

import java.io.IOException;

import easyaccept.EasyAccept;
import excecoes.AtualizaCadastroException;
import excecoes.CadastroInvalidoException;
import excecoes.EmailInexistenteException;
import excecoes.RemocaoException;
import excecoes.SistemaException;

public class HotelFacade {
	private HotelController gerencia;

	public HotelFacade() {
		this.gerencia = new HotelController();
	}

	public void iniciaSistema() {
	}

	public String cadastraHospede(String nome, String email, String data) throws SistemaException, CadastroInvalidoException {
		return gerencia.cadastraHospede(nome, email, data);
	

	}

	public void realizaCheckin(String email, int dias, String idQuarto, String tipoQuarto) throws SistemaException {
		gerencia.realizaChekin(email, dias, idQuarto, tipoQuarto);

	}

	public String realizaCheckout(String email, String idQuarto) {
		try {
			return gerencia.realizaCheckout(email, idQuarto);
		} catch (Exception e) {
			return e.getMessage();
		}
	}
	
	public String realizaPedido(String email, String itemMenu){
		try{
			return gerencia.realizaPedido(email, itemMenu);
		}catch(Exception e){
			return e.getMessage();
		}
	}

	public String consultaTransacoes(String atributo) {
		try {
			return gerencia.consultaTransacoes(atributo);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	public String consultaTransacoes(String atributo, int indice) {
		try {
			return gerencia.consultaTransacoes(atributo, indice);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	public String getInfoHospede(String email, String info) throws Exception {
		return gerencia.getInfoHospede(email, info);
	}

	public String getInfoHospedagem(String email, String info) throws Exception {
		return gerencia.getInfoHospedagem(email, info);
	}

	public String atualizaCadastro(String email, String tipoInfo, String novaInfo) throws Exception, AtualizaCadastroException {
		return gerencia.atualizaCadastro(email, tipoInfo, novaInfo);

	}

	public void removeHospede(String email) throws RemocaoException, EmailInexistenteException {
			gerencia.removeHospede(email);
	}

	public void cadastraPrato(String nome, double preco, String descricao) throws Exception {
		gerencia.cadastraPrato(nome, preco, descricao);
	}

	public String consultaRestaurante(String nome, String info) throws Exception {
		return gerencia.consultaRestaurante(nome, info);
	}

	public void cadastraRefeicao(String nome, String descricao, String componentes) throws Exception {
		gerencia.cadastraRefeicao(nome, descricao, componentes);
	}

	
	public void removeItemCardapio(String nome){
		try{
			gerencia.removeItemCardapio(nome);
		}catch(Exception e){
		System.out.println(e.getMessage());
			
		}
	}
	
	public String consultaMenuRestaurante(){
		return gerencia.consultaMenuRestaurante();
	}
	
	public void ordenaMenu(String tipoOrdenacao){
		try{
			gerencia.ordenaMenu(tipoOrdenacao);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	
	public String convertePontos(String email, int pontos){
		try{
			return this.gerencia.convertePontos(email, pontos);
		}catch(SistemaException e){
			System.out.println(e.getMessage());
		}
		return null;
	}
	

	public void fechaSistema() {
		try {
			gerencia.geraRelatorioCliente();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		args = new String[] { "hotel.HotelFacade","acceptance_test/testes_uc1_exception.txt", "acceptance_test/testes_uc1.txt",
			 "acceptance_test/testes_uc2.txt",
				"acceptance_test/testes_uc3.txt", "acceptance_test/testes_uc4.txt",
				"acceptance_test/testes_uc4_exception.txt" ,"acceptance_test/testes_uc5.txt", "acceptance_test/testes_uc6.txt", 
				"acceptance_test/testes_uc7.txt"};
		EasyAccept.main(args);

	}
}