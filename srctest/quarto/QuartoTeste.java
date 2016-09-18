package quarto;



import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class QuartoTeste {
	
	private Quarto q1;
	private Quarto q2;
	private Quarto q3;
	
	@Before
	public void setUp() throws Exception{
		
		q1 = new Simples("A401");
		q2 = new Luxo("B401");
		q3 = new Presidencial("C401");
		
	}
	
	@Test
	public void constructor(){
		try{
			Quarto love = new Luxo("Luxo01");
			Quarto normal = new Simples("xoxo");
			Quarto bixao = new Presidencial("Lula");
		}catch(Exception e){
			Assert.fail(); //nao devia lancar excecao
		}
	}
	
	@Test
	public void constructorException(){
		try{
			Quarto luxo = new Luxo(" ");
			Quarto luxo1 = new Luxo(null);
		}catch(Exception e){
			Assert.assertEquals("O ID nao pode ser vazio ou nulo", e.getMessage());
		}		
		try{
			Quarto simples = new Simples(" ");
			Quarto simples1 = new Simples(null);
		}catch(Exception e){
			Assert.assertEquals("O ID nao pode ser vazio ou nulo", e.getMessage());
		}
		try{
			Quarto presidencial = new Luxo(" ");
			Quarto presidencial1 = new Luxo(null);
		}catch(Exception e){
			Assert.assertEquals("O ID nao pode ser vazio ou nulo", e.getMessage());
		}
		
	}
	
	
	@Test 
	public void testCalculaDiaria()throws Exception{
		Assert.assertEquals(100, q1.calculaDiaria(0), 0.05);
		Assert.assertEquals(250.0, q2.calculaDiaria(), 0.05);
		Assert.assertEquals(450.0, q3.calculaDiaria(), 0.05);

		try{
			Assert.assertEquals(250.0, q2.calculaDiaria(-0.5), 0.05);
		}catch(Exception e){
			Assert.assertEquals("O valor da diaria nao pode ser inferior a zero.", e.getMessage());
		}
		
	}
	
}
