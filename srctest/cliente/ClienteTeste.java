package cliente;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import estadia.Estadia;
import quarto.Quarto;

import quarto.Simples;

public class ClienteTeste {

	private Cliente cliente;
	private Cliente cliente2;

	@Before
	public void setUp() throws Exception {
		cliente = new Cliente("Tiago Pereira", "tiago@lima.cc", "04/09/1995");
		cliente2 = new Cliente("Mariana Mendes", "mariana@menes.cc", "21/11/1997");
	}

	@Test
	public void construtor() {
		try {
			Cliente alguem = new Cliente("Alguem Aleatorio", "alguem@aleat.cc", "05/07/1990");
		} catch (Exception e) {
			Assert.fail(); // nao deve lancar excecao
		}

	}

	@Test
	public void construtorException() {
		try {
			Cliente alguem = new Cliente("", "alguem@aleat.cc", "05/07/1990");
		} catch (Exception e) {
			Assert.assertEquals("Nome nao pode ser nulo ou vazio", e.getMessage());
		}

		try {
			Cliente alguem = new Cliente("Alguem Aleatorio", "", "05/07/1990");
		} catch (Exception e) {
			Assert.assertEquals("O email nao pode ser nulo ou vazio", e.getMessage());
		}

		try {
			Cliente alguem = new Cliente("Alguem Aleatorio", "alguem@aleat.cc", "05/07/1990");
		} catch (Exception e) {
			Assert.assertEquals("Data nao pode ser nula ou vazia", e.getMessage());
		}
	}

	@Test
	public void qtdEstadias() {
		Assert.assertEquals(0, cliente.qtdEstadias());
	}

	@Test
	public void atualizaCadastro() {
		try {
			cliente.atualizaCadastro("nome", "Tiago Lima");
			Assert.assertEquals("Tiago Lima", cliente.getNome());
		} catch (Exception e) {
			Assert.fail(); // nao deve lancar excecao
		}

		try {
			cliente.atualizaCadastro("email", "leonhart@gmail.cc");
			Assert.assertEquals("leonhart@gmail.cc", cliente.getEmail());
		} catch (Exception e) {
			Assert.fail(); // nao deve lancar excecao
		}

		try {
			cliente.atualizaCadastro("data de nascimento", "23/02/1988");
			Assert.assertEquals("23/02/1988", cliente.getDataNasc());
		} catch (Exception e) {
			Assert.fail(); // nao deve lancar excecao
		}
	}

	@Test
	public void getInfoHospede() {
		try {
			Assert.assertEquals("Mariana Mendes", cliente2.getInfoHospede("Nome"));
			Assert.assertEquals("mariana@menes.cc", cliente2.getInfoHospede("Email"));
			Assert.assertEquals("21/11/1997", cliente2.getInfoHospede("Data de nascimento"));
		} catch (Exception e) {
			Assert.fail(); // nao deve lancar excecao
		}
	}

	@Test
	public void getInfoHospedagem() {
		try {
			Quarto quarto = new Simples("1A");
			Estadia estadia = new Estadia(quarto, 3);
			cliente.adicionaEstadia(estadia);
			Assert.assertEquals("1", cliente.getInfoHospedagem("Hospedagens ativas"));
			Assert.assertEquals("1A", cliente.getInfoHospedagem("Quarto"));
			Assert.assertEquals("R$300,00", cliente.getInfoHospedagem("total"));
		} catch (Exception e) {
			Assert.fail(); // nao deve lancar excecao
		}
	}

	@Test
	public void adicionaEstadia() {
		try {
			Assert.assertEquals("0", cliente.getInfoHospedagem("Hospedagens ativas"));
			Quarto quarto = new Simples("1A");
			Estadia estadia = new Estadia(quarto, 3);
			cliente.adicionaEstadia(estadia);
			Assert.assertEquals("1", cliente.getInfoHospedagem("Hospedagens ativas"));
			Assert.assertEquals("1A", cliente.getInfoHospedagem("Quarto"));

			cliente.removeEstadia(estadia);
			Assert.assertEquals("0", cliente.getInfoHospedagem("Hospedagens ativas"));

		} catch (Exception e) {
			Assert.fail(); // nao deve lancar excecao
		}
	}
}
