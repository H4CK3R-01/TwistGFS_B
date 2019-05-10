package de.florian.twist;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static HashSet<String> wordListHashSet;
    public static ArrayList<String> wordListArrayList;
    public static HashMap<Integer, String> inputWords = new HashMap<Integer, String>();
    public static ResourceBundle language = ResourceBundle.getBundle("lang/language");
    public static Console console = new Console();
    public static UI ui = new UI();


    public static void main(String[] args) {
        wordListHashSet = new HashSet<>(readWordListFile(Thread.currentThread().getContextClassLoader().getResource("wordlist/Deutsch.txt").getPath()));
        wordListArrayList = null;

        if (args.length != 0) {
            if (isValueInArray(args, "-d") >= 0) { // Wörter entschlüsseln
                if (isValueInArray(args, "-w") >= 0) {
                    wordListHashSet.clear();
                    wordListHashSet = readWordListFile(args[isValueInArray(args, "-w") + 1]);
                }

                if (isValueInArray(args, "-a") >= 0) {
                    wordListArrayList = new ArrayList<>(wordListHashSet);
                    wordListHashSet = null;
                }

                // Übergebene Argumente aus Array in HashMap übertragen
                for (int i = isValueInArray(args, "-d") + 1; i < args.length; i++) {
                    inputWords.put(i, args[i].toLowerCase());
                }

                // Alle Wörter entschlüsseln
                inputWords.entrySet().parallelStream().forEach(entry -> {
                    inputWords.put(entry.getKey(), new DecryptWort(entry.getValue()).getGeneratedWord());
                });

                // Ausgabe der Wörter
                for (Map.Entry<Integer, String> entry : inputWords.entrySet()) {
                    System.out.print(entry.getValue() + " ");
                }
            } else if (isValueInArray(args, "-e") >= 0) { // Wörter verschlüsseln
                // Übergebene Argumente aus Array in HashMap übertragen
                for (int i = isValueInArray(args, "-e") + 1; i < args.length; i++) {
                    inputWords.put(i, args[i].toLowerCase());
                }

                // Alle Wörter entschlüsseln
                inputWords.entrySet().parallelStream().forEach(entry -> {
                    inputWords.put(entry.getKey(), new EncryptWort(entry.getValue()).getGeneratedWord());
                });

                // Ausgabe der Wörter
                for (Map.Entry<Integer, String> entry : inputWords.entrySet()) {
                    System.out.print(entry.getValue() + " ");
                }
            } else if (isValueInArray(args, "-g") >= 0) {
                ui.start();
                console.start();
            } else {
                help();
            }
        } else {
            help();
        }
    }

    /**
     * Liest eine Datei Zeile für Zeile ein und speichert alle Werte in einem
     * HashSet
     *
     * @param file Datei, die eingelesen werden soll
     * @return HashSet, dass alle Strings der Datei enthält
     */
    public static HashSet<String> readWordListFile(String file) {
        HashSet<String> list = new HashSet<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) {
                list.add(line.toLowerCase());
            }
            br.close();
        } catch (IOException e) {
            System.err.println(e);
        }
        return list;
    }

    /**
     * Prüft ob ein Wert in einem Array vorhanden ist
     *
     * @param arr Array das überprüft werden soll
     * @param s   Wert der gesucht werden soll
     * @return Wenn der Wert gefunden wurde, wird der Index davon zurückgegeben,
     * ansonsten -1
     */
    private static int isValueInArray(String[] arr, String s) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(s)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Zeigt die möglichen Ausführ-Parameter an
     */
    private static void help() {
        System.out.println(language.getString("help"));
    }
}
