package application;

import java.util.ArrayList;
import java.util.Collections;

public class Elenco {
	private String nome;
	private ArrayList<Entita> elementi;
	
	public Elenco(String nome) throws NomeNonValidoException {
		
		if (nome == null || nome.isEmpty()) {
			throw new NomeNonValidoException();
		}else {
			this.nome = nome;
			this.elementi = new ArrayList<Entita>();
		}
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void aggiungi(Entita a) {
		elementi.add(a);
		Collections.sort(elementi);
	}
	
	public boolean modifica(String nome, Entita nuova) {
		for (Entita entita : elementi) {
			if (entita.getNome().equals(nome)) {
				elementi.set(elementi.indexOf(entita), nuova);
				Collections.sort(elementi);
				return true;
			}
		}
		return false;
	}
	
	public boolean elimina(String nome) {
		for (Entita entita : elementi) {
			if (entita.getNome().equals(nome)) {
				elementi.remove(entita);
				return true;
			}
		}
		return false;
	}

	public String toCsv() {
		String csv = nome;
		for (Entita entita : elementi) {
			csv += ","+entita.toCsv();
		}
		return csv;
	}

	public ArrayList<Entita> getElementi() {
		return elementi;
	}

	public String toString() {
		String str = "";	
		for (Entita entita : elementi) {
			str += entita.getNome() + "\n";
		}
		return str;
	}

}
