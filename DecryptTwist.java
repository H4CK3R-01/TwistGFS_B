import java.io.*;
import java.util.ArrayList;

public class DecryptTwist {
	private ArrayList<String> wordList = new ArrayList<>();
	private ArrayList<String> moeglicheWoerter = new ArrayList<>();

	public DecryptTwist() {
		wordList.clear();
		moeglicheWoerter.clear();
	}

	/**
	 * Mit dem BufferedReader wird die Datei die als Parameter übergeben wird
	 * Zeilenweise in eine Liste eingelesen.
	 * 
	 * @param file String des Dateinamens und Pfades, der Datei, die eingelesen
	 *             werden soll
	 * @return Liste, die alle Wörter aus der Datei enthält
	 */
	public ArrayList<String> readFile(String file) {
		ArrayList<String> list = new ArrayList<>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = null;
			br.readLine();
			while ((line = br.readLine()) != null) {
				list.add(line);
			}
			br.close();
		} catch (IOException e) {
			System.err.println(
					file + ": Datei konnte nicht geöffnet werden. Möglicherweise wurde sie gelöscht oder verschoben.");
		}

		return list;
	}

	/**
	 * Wörter die einen falschen Anfangsbuchstaben, einen falschen Endbuchstaben
	 * oder eine falsche Länge haben werden aus der Liste mit allen Wörtern gelöscht
	 * 
	 * @param firstChar Variable für den Anfangsbuchstaben
	 * @param lastChar  Variable für den Endbuchstaben
	 * @param length    Variable für die Länge des Wortes
	 * @return Liste, die die gelöschten Wörter nicht mehr enthält
	 */
	public ArrayList<String> getWords(char firstChar, char lastChar, int length) {

		// Wörter mit falschem ertsten Buchstaben aus Liste löschen
		for (int i = 0; i < wordList.size(); i++) {
			if (wordList.get(i).charAt(0) != firstChar) {
				wordList.remove(i);
				i--;
			}
		}

		// Wörter mit falschem letzten Buchstaben aus Liste löschen
		for (int i = 0; i < wordList.size(); i++) {
			if (wordList.get(i).charAt(wordList.get(i).length() - 1) != lastChar) {
				wordList.remove(i);
				i--;
			}
		}

		// Wörter mit falscher Länge aus Liste löschen
		for (int i = 0; i < wordList.size(); i++) {
			if (length != wordList.get(i).length()) {
				wordList.remove(i);
				i--;
			}
		}

		return wordList;
	}

	/**
	 * Vergleicht das Wort mit den Wörtern, die die Methode permutation()
	 * zurückgibt. Übereinstimmungen werden ausgegeben
	 * 
	 * @param wort verschlüsseltes Wort, dass entschlüsselt werden soll
	 * @return Liste mit allen möglichen Wörtern
	 */
	public String decrypt(String wort) {
		ArrayList<String> woerterMitChar = new ArrayList<>();

		wordList = readFile("woerter.txt");

		woerterMitChar = getWords(wort.charAt(0), wort.charAt(wort.length() - 1), wort.length());

		// Buchstaben vergleichen
		permutation("", delFirstLastChar(wort));
		for (int i = 0; i < moeglicheWoerter.size(); i++) {
			for (int j = 0; j < woerterMitChar.size(); j++) {
				if (moeglicheWoerter.get(i).compareTo(woerterMitChar.get(j)) != 0) { // ??????
					woerterMitChar.remove(j);
					j--;
				}
			}
		}

		// Wörter ausgeben
		for (int i = 0; i < woerterMitChar.size(); i++) {
			System.out.println(woerterMitChar.get(i));
		}

		return "";
	}

	/**
	 * Tauscht die Reihenfolge der Buchstaben des Wortes so lange, bhis jede
	 * Möglichkeit einmal vorkommt.
	 * </p>
	 * Ruft sich rekursiv immer weiter auf
	 * 
	 * @param prefix Variable, die den ersten Teil des Wortes enthält
	 * @param str    Variable, die den anderen Teil des Wortes enthält
	 */
	public void permutation(String prefix, String str) {
		int n = str.length();
		if (n == 0) {
			moeglicheWoerter.add(prefix);
		} else {
			for (int i = 0; i < n; i++) {
				permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
			}
		}
	}

	/**
	 * Löscht den ersten und letzten Buchstaben des Wortes, dass übergeben wird
	 * 
	 * @param wort Wort das übegeben wird
	 * @return Wort, ohne den ersten und letzten Buchstaben
	 */
	public String delFirstLastChar(String wort) {
		String wortNeu = null;
		for (int i = 0; i < wort.length(); i++) {
			wortNeu += wort.charAt(i);
		}
		return wortNeu;

	}
}
