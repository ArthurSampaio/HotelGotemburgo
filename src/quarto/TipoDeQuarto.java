package quarto;

/**
 * Enum para os valores dos quartos
 * @author Arthur Sampaio
 *
 */
public enum TipoDeQuarto {

	SIMPLES(100.0), LUXO(250.0), PRESIDENCIAL(450.0);

	public double diariaQuarto;
	
	private TipoDeQuarto(double diaria) {
		diariaQuarto = diaria;
	}
	
	public double getDiaria(){
		return diariaQuarto;
	}
	
	public void setDiaria(double diaria){
		this.diariaQuarto = diaria;
	}
	
}
