package estadia;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import estadia.Estadia;
import quarto.Quarto;
import quarto.QuartoFactory;

public class EstadiaTeste {

	private Estadia estadia1;
	private Estadia estadia2;
	private QuartoFactory factory = new QuartoFactory();
	private Quarto quarto1;
	private Quarto quarto2;
	
	@Before
	public void setUp() throws Exception {
		
		this.quarto1 = factory.criaQuarto("A15", "luxo");
		this.quarto2 = factory.criaQuarto("B16", "presidencial");
		this.estadia1 = new Estadia(quarto1, 5);
		this.estadia2 = new Estadia(quarto2, 5);
	}
	
	@Test
	public void construtor()  {
		try{
			Estadia estadia = new Estadia(quarto2, 7);
		}catch(Exception e){
			fail();
		}
	}
	
	@Test
	public void construtorException(){
		try{
			Quarto quarto = null;
			Estadia estadia = new Estadia(quarto, 5);
		}catch(Exception e){
			Assert.assertEquals("Quarto inválido.", e.getMessage());
		}
		
		try{
			Estadia estadia = new Estadia(quarto1, 0);
		}catch(Exception e){
			Assert.assertEquals("Não é possível se hospedar durante 0 ou menos dias.", e.getMessage());
		}
		
	}
	
	@Test
	public void calculaValorEstadia() throws Exception{
		try{
			Assert.assertEquals(1250.0, estadia1.calculaValorEstadia(), 0.0001);
		}catch(Exception e){
			fail();
		}
	}
}
