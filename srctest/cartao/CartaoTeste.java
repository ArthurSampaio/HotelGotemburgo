package cartao;

import static org.junit.Assert.*;

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
			fail(); /* nao deve lançar exececao aqui */

		}
	}

	@Test
	public void UpgradeEDowngrade() {
		
		assertEquals(0, cartao.getPontos());
		
		cartao.setPontos(350);
		assertEquals(350, cartao.getPontos());
		assertEquals("Padrao", cartao.toString());
		
		
		cartao2.setPontos(360);
		assertEquals(360, cartao2.getPontos());
		//assertEquals("Premium", cartao2.toString());
		
		
		
		
		
		
		
		
	}

}
