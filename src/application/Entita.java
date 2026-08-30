package application;

public class Entita implements Comparable<Entita> {
	private String nome;

	public Entita(String nome) throws NomeNonValidoException {
		super();
		if(nome == null || nome.isEmpty()) {
			throw new NomeNonValidoException();
		}else {
			this.nome = nome;
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String toCsv() {
		return nome;
	}

	@Override
	public int compareTo(Entita o) {
		return this.nome.compareTo(o.nome);
	}
}
