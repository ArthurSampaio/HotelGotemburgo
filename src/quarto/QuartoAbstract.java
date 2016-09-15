package quarto;

import excecoes.StringInvalidaException;

public abstract class QuartoAbstract implements Quarto {
	protected String ID;
	
	public QuartoAbstract(String id)throws StringInvalidaException{
		if(id == null || id.trim().isEmpty()){
			throw new StringInvalidaException();
		}
		
		this.ID = id;
		
	}
	
	/**
	 * Obtem o Id do quarto
	 * @return
	 * 		Uma String com o ID do quarto
	 */
	public String getID() {
		return ID;
	}

	/**
	 * Altera o ID do quarto
	 * @param iD
	 * 		O novo ID do quarto
	 */
	public void setID(String id) throws StringInvalidaException{
		if(id == null || id.trim().isEmpty()){
			throw new StringInvalidaException();
		}
		this.ID = id;
	}

}
	
	
	


