package prato;

public enum TipoDePrato {
	
	ENTRADA(1),
	PRINCIPAL(2),
	SOBREMESA(3),
	PETITFOUR(4);
	
	public int ordemPrato;
	
	TipoDePrato(int valor){
		ordemPrato = valor;
	}
	
	

}
