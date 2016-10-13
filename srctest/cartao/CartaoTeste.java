package cartao;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import excecoes.ValorInvalidoException;

public class CartaoTeste {

	private Cartao cartao;
	private Cartao cartao2;

	@Before
	public void setUp() throws Exception {
		cartao = new Cartao();
		cartao2 = new Cartao();

	}

	@Test
	public void testeConstrutor() {
		try {
			Cartao card = new Cartao();
			Padrao padrao = new Padrao();
			Premium premium = new Premium();
			Vip vip = new Vip();

		} catch (Exception e) {
			fail(); /* nao deve lancar exececao aqui */

		}
	}

	@Test
	public void UpgradeEDowngrade() {

		/*
		 * Todos os cartoes comecam Padrao
		 */
		assertEquals("Padrao", cartao.toString());
		assertEquals("Padrao", cartao2.toString());

		assertEquals(0, cartao.getPontos());

		cartao.setPontos(350);
		assertEquals(350, cartao.getPontos());
		assertEquals("Padrao", cartao.toString());

		cartao2.setPontos(360);
		assertEquals(360, cartao2.getPontos());
		assertEquals("Premium", cartao2.toString());

		cartao2.setPontos(cartao2.getPontos() + 1000);
		assertEquals(1360, cartao2.getPontos());
		assertEquals("Vip", cartao2.toString());

	}

	@Test
	public void addPontos() {
		try {
			cartao.addPontos(-200);
		} catch (Exception e) {
			assertEquals("Valor invalido", e.getMessage());

		}
	}

}
