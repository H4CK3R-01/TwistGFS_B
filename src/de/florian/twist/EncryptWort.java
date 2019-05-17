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
     * Exchanges two characters of char Array
     *
     * @param word word to exchange characters
     * @return String
     */
    private static String swapCharactersOfCharArray(char[] word) {
        int random1 = (int) (Math.random() * (word.length - 1) + 0.5); // Zufallszahl zwischen 1 und der Länge des Strings wird generiert
        int random2 = (int) (Math.random() * (word.length - 1) + 0.5); // Zufallszahl zwischen 1 und der Länge des Strings wird generiert

        char temp = word[random1];
        word[random1] = word[random2];
        word[random2] = temp;

        return String.valueOf(word);
    }

    private String modifyString(String word, String firstChar, String lastChar, String originalWord) {
        for (int i = 0; i < (word.length() / 2); i++) {
            word = swapCharactersOfCharArray(word.toCharArray());
        }

        if (!(firstChar + word + lastChar).equals(originalWord)) {
            return firstChar + word + lastChar;
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
