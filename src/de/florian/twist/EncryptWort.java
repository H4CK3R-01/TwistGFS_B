package de.florian.twist;

public class EncryptWort {
    private String word;

    public EncryptWort(String s) {
        this.word = s.replaceAll("[^A-Za-zßäöüáãâàéẽêèëíĩîìóõôòúũûùÄÖÜÃÂÀÉẼÊÈËĨÎÌÓÕÔÒÚŨÛÙæ]", "").toLowerCase();

        if (word.length() > 3) {
            word = buchstabenTauschen(word.substring(1, word.length() - 1), word.substring(0, 1), word.substring(word.length() - 1), word);
        }
    }

    /**
     * Tauscht zwei Zeichen eines Strings
     *
     * @param word String, bei dem Buchstaben getaucht werden sollen
     * @return String, der verändert wurde
     */
    private static String swapCharactersOfString(char[] word) {
        int zufallswert1 = (int) (Math.random() * (word.length - 1) + 0.5); // Zufallszahl zwischen 1 und der Länge des Strings wird generiert
        int zufallswert2 = (int) (Math.random() * (word.length - 1) + 0.5); // Zufallszahl zwischen 1 und der Länge des Strings wird generiert

        char temp = word[zufallswert1];
        word[zufallswert1] = word[zufallswert2];
        word[zufallswert2] = temp;

        return String.valueOf(word);
    }

    /**
     * Methode zum verändern von Strings. Je nach Länge des Wortes werden
     * unterschiedlich viele Buchstaben getauscht
     *
     * @param word      String, bei dem zwei Buchstaben getaucht werden sollen
     * @param firstChar erster Buchstabe des ursprünglichen Wortes
     * @param lastChar  letzter Buchstabe des ursprünglichen Wortes
     * @return vertauschter String mit erstem und letztdem Buchstabe
     */
    private String buchstabenTauschen(String word, String firstChar, String lastChar, String originalWord) {
        for (int i = 0; i < (word.length() / 2); i++) // Je länger der String ist, desto öfters werden zwei Buchstaben getauscht
        {
            word = swapCharactersOfString(word.toCharArray());
        }

        if (!(firstChar + word + lastChar).equals(originalWord)) // Wenn das neue Wort gleich wie das Ursprüngliche Wort ist, wird die Methode
        // 'buchstabenTauschen()' nocheinmal aufgerufen
        {
            return firstChar + word + lastChar; // Neu generiertes Wort wird inklusive erstem und letzem Buchstabn zurückgegeben
        } else {
            return buchstabenTauschen(word, firstChar, lastChar, originalWord);
        }
    }

    /**
     * Methode zum auslesen des Neu-generierten Wortes
     *
     * @return Neu generiertes Wort
     */
    public String getWortNeu() {
        return word;
    }
}
