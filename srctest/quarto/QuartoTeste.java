package quarto;



import quarto.QuartoFactory;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class QuartoTeste {
	
	private Quarto q1;
	private Quarto q2;
	private Quarto q3;
	private QuartoFactory quarto = new QuartoFactory();
	
	@Before
	public void setUp() throws Exception{
		
		q1 = this.quarto.criaQuarto("A401", "simples");
		q2 = this.quarto.criaQuarto("B401", "luxo");
		q3 = this.quarto.criaQuarto("C401", "presidencial");
		
	}
	
	@Test
	public void constructor(){
		try{
			Quarto love = this.quarto.criaQuarto("Luxo01", "luxo");
			Quarto normal = this.quarto.criaQuarto("xoxo", "simples");
			Quarto bixao = this.quarto.criaQuarto("Lula", "presidencial");
		}catch(Exception e){
			fail(); //nao devia lancar excecao
		}
	}
	
	@Test
	public void constructorException(){
		try{
			Quarto luxo =  this.quarto.criaQuarto(" ", "luxo");
			Quarto luxo1 =  this.quarto.criaQuarto(null, "luxo");
			fail();
		}catch(Exception e){
			assertEquals("O ID nao pode ser vazio ou nulo.", e.getMessage());
		}		
		try{
			Quarto luxo =  this.quarto.criaQuarto("luxo ", " ");
			Quarto luxo1 =  this.quarto.criaQuarto("arriba", null);
		}catch(Exception e){
			assertEquals("Tipo nao pode ser nulo ou vazio.", e.getMessage());
		}
				
	}
	
	
	@Test 
	public void testCalculaDiaria()throws Exception{
		assertEquals(100, q1.getDiaria(0), 0.05);
		assertEquals(250.0, q2.getDiaria(), 0.05);
		assertEquals(450.0, q3.getDiaria(), 0.05);

		try{
			assertEquals(250.0, q2.getDiaria(-0.5), 0.05);
		}catch(Exception e){
			assertEquals("Valor invalido", e.getMessage());
		}
		
	}
	
}
