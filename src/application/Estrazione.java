package application;

public class Estrazione {
	private int codice;
	private static int progressivo = 0;
	private String tipo;
	private String risultato;
	
	public Estrazione(String tipo, String risultato) {
		this.codice = progressivo++;
		this.tipo = tipo;
		this.risultato = risultato;
	}

	public int getCodice() {
		return codice;
	}

	public void setCodice(int codice) {
		this.codice = codice;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getRisultato() {
		return risultato;
	}

	public void setRisultato(String risultato) {
		this.risultato = risultato;
	}

	@Override
	public String toString() {
		return "tipo=" + tipo + "\nrisultato:\n" + risultato ;
	}
}
