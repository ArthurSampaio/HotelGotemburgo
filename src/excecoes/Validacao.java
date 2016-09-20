package excecoes;

public class Validacao {
	
	public void checaNome(String nome) throws StringInvalidaException {
		if (!nome.matches("[a-zA-Z ]*")){
			throw new StringInvalidaException("Erro no cadastro de Hospede. Nome do(a) hospede esta invalido.");
			}
		}
}
