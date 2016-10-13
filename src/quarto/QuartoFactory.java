package quarto;

import java.io.Serializable;
import java.util.HashMap;

import excecoes.SistemaException;
import excecoes.StringInvalidaException;

/**
 * Factory para o tipo Quarto
 * @author Arthur Sampaio
 *
 */
public class QuartoFactory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6843085609182582855L;
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
		if(tipo == null || tipo.trim().isEmpty()){
			throw new StringInvalidaException("Tipo nao pode ser nulo ou vazio.");
		}
		
		if(tipo.equalsIgnoreCase("simples") || tipo.equalsIgnoreCase("luxo") ||
				tipo.equalsIgnoreCase("presidencial")){
			return new Quarto(ID, this.setTipoDeQuarto(tipo));
		}else{
			throw new StringInvalidaException("Tipo invalido para o hotel.");
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
