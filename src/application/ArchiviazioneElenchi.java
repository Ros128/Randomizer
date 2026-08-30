package application;

import java.io.*;
import java.util.ArrayList;

public class ArchiviazioneElenchi {

    private static String fileName = "elenchi.csv";

    public void salvaElenco(ArrayList<Elenco> elenchi) throws IOException {
        BufferedWriter fout = new BufferedWriter(new FileWriter(fileName));
        for (Elenco e : elenchi) {
            fout.write(e.toCsv() + "\n");
        }
        fout.close();
    }

    public ArrayList<Elenco> leggiElenchi() throws IOException, NomeNonValidoException {
        ArrayList<Elenco> elenchi = new ArrayList<Elenco>();
        BufferedReader fin = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = fin.readLine()) != null) {
            String[] parts = line.split(",");
            String nome = parts[0];
            Elenco elenco = new Elenco(nome);
            for (int i = 1; i < parts.length; i++) {
                elenco.aggiungi(new Entita(parts[i]));
            }
            elenchi.add(elenco);
        }
        fin.close();
        return elenchi;
    }

    public void exportElenco(Elenco elenco, String fileName) throws IOException {
        BufferedWriter fout = new BufferedWriter(new FileWriter(fileName));
        fout.write(elenco.toCsv());
        fout.close();
    }

    public Elenco importElenco(String fileName) throws IOException, NomeNonValidoException {
        BufferedReader fin = new BufferedReader(new FileReader(fileName));
        String line;
        Elenco elenco = null;
        while ((line = fin.readLine()) != null) {
            String[] parts = line.split(",");
            String nome = parts[0];
            elenco = new Elenco(nome);
            for (int i = 1; i < parts.length; i++) {
                Entita entita = new Entita(parts[i]);
                elenco.aggiungi(entita);
            }
        }
        fin.close();
        return elenco;
    }
}
