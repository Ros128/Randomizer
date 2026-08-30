package application;

import java.io.*;
import java.util.ArrayList;

public class ArchiviazioneEstrazioni {
    private static String fileName = "estrazioni.csv";

    public void salvaEstrazione(ArrayList<Estrazione> estrazioni) throws IOException {
        BufferedWriter fout = new BufferedWriter(new FileWriter(fileName));
        for (Estrazione e : estrazioni) {
            String[] tmp = e.getRisultato().split("\n");
            String ris = "";
            for (String s : tmp) {
                ris += ","+s;
            }
            fout.write(e.getTipo() + "," + ris+"\n");
        }
        fout.close();
    }


    public ArrayList<Estrazione> leggiEstrazioni() throws IOException {
        ArrayList<Estrazione> estrazioni = new ArrayList<>();
        BufferedReader fin = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = fin.readLine()) != null) {
            String[] parts = line.split(",");
            String tipo = parts[0];
            String risultato = parts[1];
            for (int i = 2; i < parts.length; i++) {
                risultato += parts[i] + "\n";
            }
            Estrazione estrazione = new Estrazione(tipo, risultato);
            estrazioni.add(estrazione);
        }
       
        fin.close();
        return estrazioni;
    }

    public void exportEstrazioni(ArrayList<Estrazione> estrazioni, String fileName) throws IOException {
        BufferedWriter fout = new BufferedWriter(new FileWriter(fileName));
        for (Estrazione e : estrazioni) {
            String[] tmp = e.getRisultato().split("\n");
            String ris = "";
            for (String s : tmp) {
                ris += ","+s;
            }
            fout.write(e.getTipo() + "," + ris+"\n");
        }
        fout.close();
    }

    public ArrayList<Estrazione> importEstrazioni(String fileName) throws IOException {
        ArrayList<Estrazione> estrazioni = new ArrayList<>();
        BufferedReader fin = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = fin.readLine()) != null) {
            String[] parts = line.split(",");
            String tipo = parts[0];
            String risultato = parts[1];
            for (int i = 2; i < parts.length; i++) {
                risultato += parts[i] + "\n";
            }
            Estrazione estrazione = new Estrazione(tipo, risultato);
            estrazioni.add(estrazione);
        }
        fin.close();
        return estrazioni;
    }

}
