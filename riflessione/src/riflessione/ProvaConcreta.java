package riflessione;

public class ProvaConcreta extends ProvaAstratta {

	@Override
	public String getNome() {
		return "ciccio";
	}
	
	@Override
	public String getTipo() {
		return "concreto";
	}

}
