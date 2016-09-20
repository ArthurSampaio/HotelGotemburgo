package quarto;

import java.util.HashMap;

import excecoes.StringInvalidaException;

/**
 * Factory para o tipo Quarto
 * @author Arthur Sampaio
 *
 */
public class QuartoFactory {

	private HashMap<String, TipoDeQuarto> mapQuarto;
	
	public QuartoFactory(){
		this.initializeMap();
	};
	
	/**
	 * Cria um quarto a partir de um ID dado e o tipo do quarto
	 * @param ID
	 * 		O ID Unico do quarto
	 * @param tipo
	 * 		O tipo do quarto
	 * @return
	 * 		Um objeto do tipo quarto
	 * @throws StringInvalidaException
	 * 		Quando ID ou Tipo sao invalidas. 
	 */
	public Quarto criaQuarto(String ID, String tipo) throws Exception, StringInvalidaException{
		if(ID == null || ID.trim().isEmpty()){
			throw new StringInvalidaException("O ID nao pode ser invalida ou nula.");
		}
		if(tipo == null || tipo.trim().isEmpty()){
			throw new StringInvalidaException("Tipo nao pode ser nulo ou invalido.");
		}
		
		if(tipo.equalsIgnoreCase("simples")){
			return new Quarto(ID, this.setTipoDeQuarto(tipo));
		}else if(tipo.equalsIgnoreCase("luxo")){
			return new Quarto(ID, this.setTipoDeQuarto(tipo));
		}else if(tipo.equalsIgnoreCase("presidencial")){
			return new Quarto(ID, this.setTipoDeQuarto(tipo));
		}else{
			throw new Exception("Tipo invalido para o hotel.");
		}

		
	}
	
	public TipoDeQuarto setTipoDeQuarto(String tipo){
		return this.mapQuarto.get(tipo.toUpperCase());
	}
	
	private void initializeMap() {
		this.mapQuarto = new HashMap<String, TipoDeQuarto>();
		mapQuarto.put("PRESIDENCIAL", TipoDeQuarto.PRESIDENCIAL);
		mapQuarto.put("SIMPLES", TipoDeQuarto.SIMPLES);
		mapQuarto.put("LUXO", TipoDeQuarto.LUXO);
		

	}
	
}
