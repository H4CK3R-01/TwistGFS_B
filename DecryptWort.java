package de.florian.gtwist;

import java.util.*;

public class DecryptWort {
	private String urspruenglichesWort;
	private HashSet<String> wortListeNachPermutation = new HashSet<>();

	public DecryptWort(String wort) {
		// Thread starten und Variablen leeren
		wortListeNachPermutation.clear();

		urspruenglichesWort = wort;

		// Sonderzeichen entfernen
		wort = wort.replaceAll("[^A-Za-zßäöüáãâàéẽêèëíĩîìóõôòúũûùÄÖÜÃÂÀÉẼÊÈËĨÎÌÓÕÔÒÚŨÛÙæ]", "");

		if (wort.length() > 2) {
			// ersten und letzten Buchstaben speichern
			wort = wort.toLowerCase();
			String ersterBuchstabe = wort.substring(0, 1);
			String letzterBuchstabe = wort.substring(wort.length() - 1, wort.length());

			// Array sortieren
			char[] tempArray = wort.substring(1, wort.length() - 1).toCharArray();
			Arrays.sort(tempArray);
			wort = new String(tempArray);

			// Permutation starten
			permute("", wort, ersterBuchstabe, letzterBuchstabe);

			// Wenn kein Wort gefunden wurde, wird das ursprüngliche Wort in die Liste
			// geschrieben
			if (wortListeNachPermutation.size() == 0) {
				wortListeNachPermutation.add(urspruenglichesWort);
			}
		} else {
			wortListeNachPermutation.add(wort);
		}
	}

	/**
	 * Generiert alle möglichen Permutationen
	 * 
	 * @param prefix           erster Teil des Wortes
	 * @param str              tweiter Teil des Wortes
	 * @param ersterBuchstabe  Erster Buchstabe des Ursprünglichen Wortes (Um zu
	 *                         prüfen ob Das gesamte Wort in der Wortliste steht)
	 * @param letzterBuchstabe Letzter Buchstabe des Ursprünglichen Wortes (Um zu
	 *                         prüfen ob Das gesamte Wort in der Wortliste steht)
	 */
	private void permute(String prefix, String str, String ersterBuchstabe, String letzterBuchstabe) {
		int n = str.length();
		if (n == 0) {
			// Wenn das generierte Wort in der Wortliste steht, wird es in die Liste
			// wortListeNachPermutation gespeichert
			if (Main.wordList.contains(ersterBuchstabe + prefix + letzterBuchstabe)) {
				wortListeNachPermutation.add(ersterBuchstabe + prefix + letzterBuchstabe);
			}
		} else {
			for (int i = 0; i < n; i++) {
				// Wenn ein Buchstabe doppelt vorkommt wird die Kombination nur einmal berechnet
				if (i > 0 && str.charAt(i) == str.charAt(i - 1))
					continue;
				permute(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n), ersterBuchstabe, letzterBuchstabe);
			}
		}
	}

	/**
	 * Methode zum auslesen der generierten Wörter
	 * 
	 * @return HashSet mit den möglichen Wörtern
	 */
	public HashSet<String> getWortListeNachPermutation() {
		return wortListeNachPermutation;
	}
}
