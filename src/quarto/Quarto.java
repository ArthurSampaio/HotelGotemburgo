package quarto;

import excecoes.StringInvalidaException;
import excecoes.ValorInvalidoException;

public abstract class Quarto{
	private String ID;
	
	public Quarto(String id)throws StringInvalidaException{
		if(id == null || id.trim().isEmpty()){
			throw new StringInvalidaException("O ID nao pode ser vazio ou nulo");
		}
		
		this.ID = id;
		
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Quarto){
			Quarto quarto = (Quarto)obj;
			if(quarto.getID().equals(this.getID())){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

	public abstract double getDiaria(double desconto) throws ValorInvalidoException;

	
	public abstract double getDiaria();
	
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
	
	
	


