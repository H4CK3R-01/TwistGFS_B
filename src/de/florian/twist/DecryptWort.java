package de.florian.twist;

import java.util.Arrays;

public class DecryptWort {
    private String generatedWord;

    public DecryptWort(String word) {
        // Sonderzeichen entfernen
        word = word.replaceAll("[^A-Za-zßäöüáãâàéẽêèëíĩîìóõôòúũûùÄÖÜÃÂÀÉẼÊÈËĨÎÌÓÕÔÒÚŨÛÙæ]", "");
        word = word.toLowerCase();

        if (word.length() > 2) {
            String firstChar = word.substring(0, 1);
            String lastChar = word.substring(word.length() - 1);

            // Array sortieren
            char[] tempArray = word.substring(1, word.length() - 1).toCharArray();
            Arrays.sort(tempArray);
            word = new String(tempArray);


            // Permutation starten
            permute("", word, firstChar, lastChar);
        }
    }

    /**
     * Generiert alle möglichen Permutationen
     *
     * @param a         erster Teil des Wortes
     * @param b         zweiter Teil des Wortes
     * @param firstChar Erster Buchstabe des Ursprünglichen Wortes (Um zu prüfen ob
     *                  Das gesamte Wort in der Wortliste steht)
     * @param lastChar  Letzter Buchstabe des Ursprünglichen Wortes (Um zu prüfen ob
     *                  Das gesamte Wort in der Wortliste steht)
     */
    private void permute(String a, String b, String firstChar, String lastChar) {
        int n = b.length();
        if (n == 0) {
            if (Main.wordList.contains(firstChar + a + lastChar)) { // prüfen ob Wort in Wortliste steht
                generatedWord = firstChar + a + lastChar; // Rekursive Methode abrechen
            }
        } else {
            for (int i = 0; i < n; i++) {
                if (i > 0 && b.charAt(i) == b.charAt(i - 1)) {
                    continue; // Wenn ein Buchstabe doppelt vorkommt wird die Kombination nur einmal berechnet
                }
                permute(a + b.charAt(i), b.substring(0, i) + b.substring(i + 1, n), firstChar, lastChar);
            }
        }
    }

    /**
     * Methode zum auslesen des generierten Wortes
     *
     * @return String mit dem Wort
     */
    public String getGeneratedWord() {
        return generatedWord;
    }
}
