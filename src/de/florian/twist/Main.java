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

    // Test
    private static HashMap<String, DecryptWort> wordsDecrypt = new HashMap<>();
    private static HashMap<String, String> wordsEncrypt = new HashMap<>();
    private static HashSet<String> selectedWords = new HashSet<>();

    private static int index = 0;
    private static int richtig = 0;
    private static int falsch = 0;
    private static int anzahlWoerter = 10;


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
            } else if (isValueInArray(args, "-t") >= 0) {
                try {
                    anzahlWoerter = Integer.parseInt(args[isValueInArray(args, "-t") + 1]);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                test();
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

    private static String stringBuilder(String s, int breite) {
        while (s.length() <= breite) s = s + " ";
        return s;
    }

    /**
     * Tests
     */
    private static void test() {
        selectWoerter();
        encryptWoerter();

        // HashSet
        decryptWoerter();
        System.err.println("HashSet");
        ueberpruefeWoerter();


        wordListArrayList = new ArrayList<>(wordListHashSet);
        wordListHashSet = null;
        wordsDecrypt.clear();
        index = 0;
        richtig = 0;
        falsch = 0;

        // ArrayList
        decryptWoerter();
        System.err.println("ArrayList");
        ueberpruefeWoerter();
    }

    private static void selectWoerter() {
        while (selectedWords.size() < anzahlWoerter) {
            int zufall = (int) (Math.random() * wordListHashSet.size());
            Iterator<String> iterator = wordListHashSet.iterator();
            while (iterator.hasNext() && (zufall > 1)) {
                iterator.next();
                zufall = zufall - 1;
            }
            String wort = iterator.next();
            if (wort.length() < 10) {
                selectedWords.add(wort);
            }
        }
    }

    private static void encryptWoerter() {
        Iterator<String> iterator = selectedWords.iterator();
        while (iterator.hasNext()) {
            String wort = iterator.next();
            wordsEncrypt.put(wort.toLowerCase(), new EncryptWort(wort).getGeneratedWord());
        }
    }

    private static void decryptWoerter() {
        wordsEncrypt.entrySet().parallelStream().forEach(entry -> {
            wordsDecrypt.put(entry.getKey(), new DecryptWort(entry.getValue()));
        });
    }

    private static void ueberpruefeWoerter() {
        System.out.println("Verschlüsseltes Wort | Entschlüsseltes Wort | Richtiges Wort | Dauer in ms");
        System.out.println("--------------------------------------------------------------------------");
        for (Map.Entry<String, DecryptWort> entry : wordsDecrypt.entrySet()) {
            index = index + 1;
            if (entry.getKey().equals(entry.getValue().getGeneratedWord())) {
                System.out.println(stringBuilder(entry.getValue().getOriginalWord(), 19) + " | " + stringBuilder(entry.getKey(), 19) + " | " + stringBuilder(entry.getValue().getGeneratedWord(), 13) + " | " + entry.getValue().getLaufzeit());
                richtig = richtig + 1;
            } else {
                //System.err.println(entry.getKey() + stringBuilder(entry.getValue().getGeneratedWord()));
                falsch = falsch + 1;
            }
        }
        System.err.println("Von " + index + " Wörtern wurden " + richtig + " Richtig und " + falsch + " falsch erkannt.\n\n");
    }
}
