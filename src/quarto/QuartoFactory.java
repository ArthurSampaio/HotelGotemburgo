package quarto;

import java.util.HashMap;

import excecoes.AtributoClienteException;
import excecoes.SistemaException;

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
	 * @throws SistemaException
	 * 		Quando ID ou Tipo sao invalidas. 
	 */
	public Quarto criaQuarto(String ID, String tipo)throws SistemaException{
		if(ID == null || ID.trim().isEmpty()){
			throw new AtributoClienteException("O ID nao pode ser invalida ou nula.");
		}
		if(tipo == null || tipo.trim().isEmpty()){
			throw new AtributoClienteException("Tipo nao pode ser nulo ou invalido.");
		}
		
		if(tipo.equalsIgnoreCase("simples") || tipo.equalsIgnoreCase("luxo") ||
				tipo.equalsIgnoreCase("presidencial")){
			return new Quarto(ID, this.setTipoDeQuarto(tipo));
		}else{
			throw new AtributoClienteException("Tipo invalido para o hotel.");
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
