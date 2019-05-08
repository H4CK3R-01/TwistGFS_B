package de.florian.twist;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Main {
    public static HashSet<String> wordList = new HashSet<>();
    public static HashMap<Integer, String> text = new HashMap<Integer, String>();
    public static Console console = new Console();
    private static UI ui = new UI();

    public static void main(String[] args) {
        wordList = readWordListFile(Thread.currentThread().getContextClassLoader().getResource("wordlist/Deutsch.txt").getPath());

        if (args.length != 0) {
            if (isValueInArray(args, "-d") >= 0) {
                if (isValueInArray(args, "-w") >= 0) {
                    wordList = readWordListFile(args[isValueInArray(args, "-w") + 1]);
                }
                // Übergebene Argumente aus Array in HashMap übertragen
                for (int i = isValueInArray(args, "-d") + 1; i < args.length; i++) {
                    text.put(i, args[i].toLowerCase());
                }

                // Alle Wörter entschlüsseln
                text.entrySet().parallelStream().forEach(entry -> {
                    text.put(entry.getKey(), new DecryptWort(entry.getValue()).getWort());
                });

                // Ausgabe der Wörter
                for (Map.Entry<Integer, String> entry : text.entrySet()) {
                    System.out.print(entry.getValue() + " ");
                }
            } else if (isValueInArray(args, "-e") >= 0) {
                // Übergebene Argumente aus Array in HashMap übertragen
                for (int i = isValueInArray(args, "-e") + 1; i < args.length; i++) {
                    text.put(i, args[i].toLowerCase());
                }

                // Alle Wörter entschlüsseln
                text.entrySet().parallelStream().forEach(entry -> {
                    text.put(entry.getKey(), new EncryptWort(entry.getValue()).getWortNeu());
                });

                // Ausgabe der Wörter
                for (Map.Entry<Integer, String> entry : text.entrySet()) {
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
        System.out.println("Optionen:");
        System.out.println(" -d [Wörter]    Wörter entschlüsseln");
        System.out.println(" -e [Wörter]    Wörter verschlüsseln");
        System.out.println(" -w [Wortliste] Wortliste ändern (Hinweis: Muss zwangsläufig ganz am Anfang");
        System.out.println("                stehen und kann nur in Verbindung mit '-d' verwendet werden!)");
        System.out.println(" -g             Grafische Oberfläche starten");
        System.out.println();
        System.out.println("Beispiel:");
        System.out.println(" '-d Hlalo Wlet'                       Wort wird mit der Standard-Wortliste entschlüsselt");
        System.out.println(" '-w \"C:\\woerter.txt\" -d Hlalo Wlet'   andere Wortliste wird verwendet um das Wort zu entschlüsseln");
        System.out.println(" '-e Hallo Welt'                       Wort wird verschlüsselt");
    }
}
