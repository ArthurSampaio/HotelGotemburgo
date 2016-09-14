package quarto;

/**
 * Enum com os valores discriminados de cada tipo de quarto
 * 
 * @author arthurspc
 *
 */
public enum ValorDeQuartos {
	
	SIMPLES(100.0), LUXO(250.0), PRESIDENCIAL(450.0);
	
	public double valorDosQuartos;
	
	private ValorDeQuartos(double valor){
		valorDosQuartos = valor;
	}

	public double getValor(){
		return this.valorDosQuartos;
	}
}
