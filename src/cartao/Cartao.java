package cartao;

public class Cartao {

	private int pontos;

	public Cartao(int pontos) throws Exception {
		if (pontos < 0) {
			throw new Exception("Valor invalido.");
		}

		this.setPontos(pontos);

	}
	
	
	
	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

}
