package de.florian.twist;

import java.util.*;

public class DecryptWort {
    private String generatedWord;
    private String originalWord;
    private HashSet<String> wordListHashSet = new HashSet<>();
    private ArrayList<String> wordListArrayList = new ArrayList<>();
    private Iterator it;
    private long runtime;


    public DecryptWort(String word) {
        long time = System.currentTimeMillis();

        word = word.replaceAll("[^A-Za-zßäöüáãâàéẽêèëíĩîìóõôòúũûùÄÖÜÃÂÀÉẼÊÈËĨÎÌÓÕÔÒÚŨÛÙæ]", "").toLowerCase();
        originalWord = word;

        if (word.length() > 2) {
            if (Main.permute) {
                findWordWithPermutation(word);
            } else {
                if (Main.wordListHashSet != null) {
                    Iterator it = Main.wordListHashSet.iterator();
                    while (it.hasNext()) {
                        String wordFromList = it.next().toString();
                        if (wordFromList.length() == word.length() && wordFromList.startsWith(word.substring(0, 1)) && wordFromList.endsWith(word.substring(word.length() - 1))) {
                            wordListHashSet.add(wordFromList);
                        }
                    }
                } else {
                    Iterator it = Main.wordListArrayList.iterator();
                    while (it.hasNext()) {
                        String wordFromList = it.next().toString();
                        if (wordFromList.length() == word.length() && wordFromList.startsWith(word.substring(0, 1)) && wordFromList.endsWith(word.substring(word.length() - 1))) {
                            wordListArrayList.add(wordFromList);
                        }
                    }
                }
                generatedWord = findWordWithChars(word);
            }
            runtime = System.currentTimeMillis() - time;
        }
    }


    private void findWordWithPermutation(String word) {
        String firstChar = word.substring(0, 1);
        String lastChar = word.substring(word.length() - 1);

        // Array sortieren
        char[] tempArray = word.substring(1, word.length() - 1).toCharArray();
        Arrays.sort(tempArray);
        word = new String(tempArray);


        // Permutation starten
        permute("", word, firstChar, lastChar);
    }

    /**
     * Generates all permutations
     *
     * @param a         part of the word
     * @param b         part of the word
     * @param firstChar first char of word to check if wordlist contains the full word
     * @param lastChar  last char of word to check if wordlist contains the full word
     */
    private void permute(String a, String b, String firstChar, String lastChar) {
        int n = b.length();
        if (n == 0) {
            if (Main.wordListHashSet != null) {
                if (Main.wordListHashSet.contains(firstChar + a + lastChar)) {
                    generatedWord = firstChar + a + lastChar;
                    return;
                }
            } else {
                if (Main.wordListArrayList.contains(firstChar + a + lastChar)) {
                    generatedWord = firstChar + a + lastChar;
                    return;
                }
            }
        } else {
            for (int i = 0; i < n; i++) {
                if (i > 0 && b.charAt(i) == b.charAt(i - 1)) {
                    continue;
                }
                permute(a + b.charAt(i), b.substring(0, i) + b.substring(i + 1, n), firstChar, lastChar);
            }
        }
        return;
    }


    private String findWordWithChars(String decryptedWord) {
        if (Main.wordListHashSet != null) {
            it = wordListHashSet.iterator();
        } else {
            it = wordListArrayList.iterator();
        }

        while (it.hasNext()) {
            String wordFromList = it.next().toString();

            HashMap<Character, Integer> charMapWordFromWordList = new HashMap<>();
            for (int i = 0; i < wordFromList.length(); i++) {
                Character key = wordFromList.charAt(i);
                if (charMapWordFromWordList.containsKey(key)) {
                    charMapWordFromWordList.put(key, charMapWordFromWordList.get(key) + 1);
                } else {
                    charMapWordFromWordList.put(key, 1);
                }
            }

            HashMap<Character, Integer> charMapDecryptedWord = new HashMap<>();
            for (int i = 0; i < decryptedWord.length(); i++) {
                Character key = decryptedWord.charAt(i);
                if (charMapDecryptedWord.containsKey(key)) {
                    charMapDecryptedWord.put(key, charMapDecryptedWord.get(key) + 1);
                } else {
                    charMapDecryptedWord.put(key, 1);
                }
            }

            boolean everyCharCorrect = true;
            for (int i = 0; i < decryptedWord.length() && everyCharCorrect; i++) {
                if (charMapDecryptedWord.get(decryptedWord.charAt(i)) != charMapWordFromWordList.get(decryptedWord.charAt(i)) && everyCharCorrect) {
                    everyCharCorrect = false;

                }
            }

            if (everyCharCorrect) {
                return wordFromList;
            }
        }
        return "";
    }

    public String getGeneratedWord() {
        return generatedWord;
    }

    public String getOriginalWord() {
        return originalWord;
    }

    public long getRuntime() {
        return runtime;
    }
}
