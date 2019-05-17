package de.florian.twist;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static HashSet<String> wordListHashSet;
    public static ArrayList<String> wordListArrayList;
    public static HashMap<Integer, String> inputWords = new HashMap<>();
    public static ResourceBundle language = ResourceBundle.getBundle("lang/language");
    public static boolean permute;
    public static Console console = new Console();
    public static UI ui = new UI();

    // Test
    private static HashMap<String, DecryptWort> wordsDecrypt = new HashMap<>();
    private static HashMap<String, String> wordsEncrypt = new HashMap<>();
    private static HashSet<String> selectedWords = new HashSet<>();

    private static int index = 0;
    private static int right = 0;
    private static int wrong = 0;
    private static int numberOfWords = 10;


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
                    numberOfWords = Integer.parseInt(args[isValueInArray(args, "-t") + 1]);
                } catch (NumberFormatException e) {
                    try {
                        String wort1 = args[isValueInArray(args, "-t") + 1];
                        startTest(wort1);
                        return;
                    } catch (Exception ex) {
                    }
                }
                startTest(numberOfWords);
            } else {
                help();
            }
        } else {
            help();
        }
    }

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
     * Checks if Array contains String
     *
     * @param arr Array to check
     * @param s   String to search
     * @return index of String if found, else -1
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
     * Shows help
     */
    private static void help() {
        System.out.println(language.getString("help"));
    }

    private static String stringBuilder(String s, int width) {
        while (s.length() <= width) s = s + " ";
        return s;
    }

    /**
     * Tests
     */
    private static void startTest(int numberOfWords) {
        selectWords(numberOfWords, 15);
        encryptWords();

        permute = false;
        test("\nHashSet ohne Permutationen");

        permute = true;
        test("\nHashSet mit Permutationen");


        wordListArrayList = new ArrayList<>(wordListHashSet);
        wordListHashSet = null;

        permute = false;
        test("\nArrayList ohne Permutationen");

        permute = true;
        test("\nArrayList mit Permutationen");
    }

    private static void startTest(String word) {
        wordsEncrypt.put(word.toLowerCase(), new EncryptWort(word).getGeneratedWord());

        permute = false;
        test("\nHashSet ohne Permutationen");

        permute = true;
        test("\nHashSet mit Permutationen");


        wordListArrayList = new ArrayList<>(wordListHashSet);
        wordListHashSet = null;

        permute = false;
        test("\nArrayList ohne Permutationen");

        permute = true;
        test("\nArrayList mit Permutationen");
    }


    private static void test(String s) {
        decryptWords();
        System.err.println(s);
        checkWords();
        wordsDecrypt.clear();
        index = 0;
        right = 0;
        wrong = 0;
    }

    private static void selectWords(int number) {
        int laenge = 4;
        while (selectedWords.size() < number) {
            int zufall = (int) (Math.random() * wordListHashSet.size());
            Iterator<String> iterator = wordListHashSet.iterator();
            while (iterator.hasNext() && (zufall > 1)) {
                iterator.next();
                zufall = zufall - 1;
            }
            String wort = iterator.next();
            if (wort.length() == laenge) {
                selectedWords.add(wort);
                System.out.println(wort);
                laenge = laenge + 1;
            }
        }
    }

    private static void selectWords(int number, int maxLength) {
        while (selectedWords.size() < number) {
            int zufall = (int) (Math.random() * wordListHashSet.size());
            Iterator<String> iterator = wordListHashSet.iterator();
            while (iterator.hasNext() && (zufall > 1)) {
                iterator.next();
                zufall = zufall - 1;
            }
            String wort = iterator.next();
            if (wort.length() <= maxLength) {
                selectedWords.add(wort);
            }
        }
    }

    private static void encryptWords() {
        Iterator<String> iterator = selectedWords.iterator();
        while (iterator.hasNext()) {
            String wort = iterator.next();
            wordsEncrypt.put(wort.toLowerCase(), new EncryptWort(wort).getGeneratedWord());
        }
    }

    private static void decryptWords() {
        wordsEncrypt.entrySet().parallelStream().forEach(entry -> {
            wordsDecrypt.put(entry.getKey(), new DecryptWort(entry.getValue()));
        });
    }

    private static void checkWords() {
        System.out.println("Verschlüsseltes Wort   |   Entschlüsseltes Wort   |      Richtiges Wort      | Dauer in ms");
        System.out.println("------------------------------------------------------------------------------------------");
        for (Map.Entry<String, DecryptWort> entry : wordsDecrypt.entrySet()) {
            index = index + 1;
            if (entry.getKey().equals(entry.getValue().getGeneratedWord())) {
                System.out.println(stringBuilder(entry.getValue().getOriginalWord(), 21) + " | " + stringBuilder(entry.getValue().getGeneratedWord(), 23) + " | " + stringBuilder(entry.getKey(), 23) + " | " + entry.getValue().getRuntime());
                right = right + 1;
            } else {
                System.err.println(stringBuilder(entry.getValue().getOriginalWord(), 21) + " | " + stringBuilder(entry.getValue().getGeneratedWord(), 23) + " | " + stringBuilder(entry.getKey(), 23) + " | " + entry.getValue().getRuntime());
                wrong = wrong + 1;
            }
        }
        System.err.println("Von " + index + " Wörtern wurden " + right + " Richtig und " + wrong + " falsch erkannt.\n\n");
    }
}
