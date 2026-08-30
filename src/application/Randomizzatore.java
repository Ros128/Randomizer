package application;

import java.util.ArrayList;
import java.util.Collections;

public class Randomizzatore {
    public static String generatePassword(int length, Boolean symbols) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
        String password = "";
        
        for (int i = 0; i < length; i++) {
            int randomIndex = (int) (Math.random() * (symbols?characters.length():62));
            password += characters.charAt(randomIndex);
        }
        
        return password;
    }

    public static String testaOCroce() {
        return Math.random() < 0.5 ? "testa" : "croce";
    }

    public static String randomizeList(ArrayList<Entita> elenco) {
        Collections.shuffle(elenco);
        String tmp = "";
        int i = 1;
        for (Entita e : elenco) {
			tmp+=(i++)+")"+e.getNome()+"\n";
		}
		return tmp;
    }
    
    public static String estraiElemento(ArrayList<Entita> elenco) {
        int randomIndex = (int) (Math.random() * elenco.size());
        return elenco.get(randomIndex).getNome();
    }
}
