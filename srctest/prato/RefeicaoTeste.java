package prato;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import prato.Prato;
import prato.Refeicao;

public class RefeicaoTeste {
	private Refeicao refeicao;
	private Prato prato1;
	private Prato prato2;
	private Prato prato3;
	private Prato prato4;
	private ArrayList<Prato> pratos;
	@Before
	public void setUp() throws Exception{
		prato1 = new Prato("nome1", "descricao1", 15.20);
		prato2 = new Prato("nome2", "descricao2", 5.40);
		prato3 = new Prato("nome3", "descricao3", 23.40);
		prato4 = new Prato("nome4", "descricao4", 34.60);
		pratos = new ArrayList<Prato>();
		pratos.add(prato1); pratos.add(prato2); pratos.add(prato3); pratos.add(prato4);
		refeicao = new Refeicao ("Delicia", "muito bom", pratos);
	}
	
	@Test
	public void testaPreco(){
		assertEquals(70,74, refeicao.getPreco());
	}
	
	@Test
	public void testaToString(){
		String esperado = "muito bom Serao servidos: (1) nome1, (2) nome2, (3) nome3, (4) nome4.";
		assertEquals(esperado, refeicao.toString());
	}
	
	@Test 
	public void testException(){
		try{
			pratos.remove(prato4);
			pratos.add(null);
			refeicao = new Refeicao("Delicia", "very good", pratos);
			fail();
		} catch (Exception e) {
			assertEquals("Prato vazio", e.getMessage());
		}
	}
}
