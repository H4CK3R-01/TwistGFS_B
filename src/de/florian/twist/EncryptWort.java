package de.florian.twist;

public class EncryptWort {
    private String originalWord;
    private String generatedWord;

    public EncryptWort(String s) {
        s = s.replaceAll("[^A-Za-zßäöüáãâàéẽêèëíĩîìóõôòúũûùÄÖÜÃÂÀÉẼÊÈËĨÎÌÓÕÔÒÚŨÛÙæ]", "").toLowerCase();

        originalWord = s;

        if (s.length() > 3) {
            generatedWord = modifyString(s.substring(1, s.length() - 1), s.substring(0, 1), s.substring(s.length() - 1), s);
        }
    }

    /**
     * Tauscht zwei Zeichen eines Strings
     *
     * @param word String, bei dem Buchstaben getaucht werden sollen
     * @return String, der verändert wurde
     */
    private static String swapCharactersOfCharArray(char[] word) {
        int random1 = (int) (Math.random() * (word.length - 1) + 0.5); // Zufallszahl zwischen 1 und der Länge des Strings wird generiert
        int random2 = (int) (Math.random() * (word.length - 1) + 0.5); // Zufallszahl zwischen 1 und der Länge des Strings wird generiert

        char temp = word[random1];
        word[random1] = word[random2];
        word[random2] = temp;

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
    private String modifyString(String word, String firstChar, String lastChar, String originalWord) {
        for (int i = 0; i < (word.length() / 2); i++) // Je länger der String ist, desto öfters werden zwei Buchstaben getauscht
        {
            word = swapCharactersOfCharArray(word.toCharArray());
        }

        if (!(firstChar + word + lastChar).equals(originalWord)) // Wenn das neue Wort gleich wie das Ursprüngliche Wort ist, wird die Methode
        // 'modifyString()' nocheinmal aufgerufen
        {
            return firstChar + word + lastChar; // Neu generiertes Wort wird inklusive erstem und letzem Buchstabn zurückgegeben
        } else {
            return modifyString(word, firstChar, lastChar, originalWord);
        }
    }

    public String getGeneratedWord() {
        return generatedWord;
    }

    public String getGOriginalWord() {
        return originalWord;
    }
}
