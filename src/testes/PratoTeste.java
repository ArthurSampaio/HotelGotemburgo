package testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import prato.Prato;

public class PratoTeste {

	@Test
	public void criaPrato() {

		try {

			Prato prato3 = new Prato("nome3", "descricao3", 23.40);
			Prato prato4 = new Prato("nome4", "descricao4", 34.60);

		} catch (Exception e) {
			fail(); /* Nao deve ser lançada exececao */

		}

	}

	@Test
	public void criaPratoException() {
		try {
			Prato prato4 = new Prato("", "descricao4", 67.0);
			Prato prato5 = new Prato(null, "descricao5", 34.0);
			fail();
		} catch (Exception e) {
			assertEquals("String nula ou vazia", e.getMessage());

		}

		try {
			Prato prato4 = new Prato("nome4", "", 67.0);
			Prato prato5 = new Prato("nome5", null, 34.0);
			fail();

		} catch (Exception e) {
			assertEquals("String nula ou vazia", e.getMessage());
		}

		try {
			Prato prato4 = new Prato("nome4", "descricao4", -67.0);
			fail();
		} catch (Exception e) {
			assertEquals("Valor inválido", e.getMessage());
		}

	}

}
