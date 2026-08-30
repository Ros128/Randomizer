package application;

import java.util.ArrayList;

public class GestoreElenchi {
    private ArrayList<Elenco> elenchi;

    public GestoreElenchi() {
        this.elenchi = new ArrayList<Elenco>();
    }

    public void aggiungiTutti(ArrayList<Elenco> elenchi) {
        this.elenchi.addAll(elenchi);
    }

    public boolean rimuoviElenco(String nome) {
        for (Elenco elenco : elenchi) {
            if (elenco.getNome().equals(nome)) {
                elenchi.remove(elenco);
                return true;
            }
        }
        return false;
    }

    public boolean aggiungiEntitaAdElenco(String nomeElenco, Entita entita) {
        for (Elenco elenco : elenchi) {
            if (elenco.getNome().equals(nomeElenco)) {
                elenco.aggiungi(entita);
                return true;
            }
        }
        return false;
    }

    public boolean rimuoviEntitaDaElenco(String nomeElenco, String nomeEntita) {
        for (Elenco elenco : elenchi) {
            if (elenco.getNome().equals(nomeElenco)) {
                return elenco.elimina(nomeEntita);
            }
        }
        return false;
    }

    public boolean modificaEntitaInElenco(String nomeElenco, String nomeEntita, Entita nuova) {
        for (Elenco elenco : elenchi) {
            if (elenco.getNome().equals(nomeElenco)) {
                return elenco.modifica(nomeEntita, nuova);
            }
        }
        return false;
    }

    public String[] getNomi(){
        String[] nomi = new String[elenchi.size()];
        for (int i = 0; i < elenchi.size(); i++) {
            nomi[i] = elenchi.get(i).getNome();
        }
        return nomi;
    }

    public Elenco getElenco(String nome) {
        for (Elenco elenco : elenchi) {
            if (elenco.getNome().equals(nome)) {
                return elenco;
            }
        }
        return null;
    }

    public void aggiungiElenco(Elenco elenco) {
        elenchi.add(elenco);
    }
    
    public ArrayList<Elenco> getElenchi() {
        return elenchi;
    }

}