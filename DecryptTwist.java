import java.io.*;
import java.util.ArrayList;

public class DecryptTwist {
	private ArrayList<String> wordList = new ArrayList<>();

	public DecryptTwist() {

	}

	private void readTxtFile() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("woerter.txt"));
			String line = null;
			br.readLine();
			while ((line = br.readLine()) != null) {
				wordList.add(line);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private ArrayList<String> getWords(char firstChar, char lastChar) {
		ArrayList<String> woerterMitChar = new ArrayList<>();
		ArrayList<String> woerterMitCharNeu = new ArrayList<>();
		for (int i = 0; i < wordList.size(); i++) {
			if (wordList.get(i).charAt(0) == firstChar) {
				woerterMitChar.add(wordList.get(i));
			}
		}
		for (int i = 0; i < woerterMitChar.size(); i++) {
			if (woerterMitChar.get(i).charAt(woerterMitChar.get(i).length() - 1) == lastChar) {
				woerterMitCharNeu.add(woerterMitChar.get(i));

			}
		}

		return woerterMitCharNeu;
	}

	public String decrypt(String wort) {
		readTxtFile();
		ArrayList<String> woerterMitChar = new ArrayList<>();
		woerterMitChar = getWords(wort.charAt(0), wort.charAt(wort.length() - 1));

		for (int i = 0; i < woerterMitChar.size(); i++) {
			if (wort.length() != woerterMitChar.get(i).length()) {
				woerterMitChar.remove(i);
				i--;
			}
		}
		try {
			if (woerterMitChar.size() != 0) {
				for (int i = 0; i < woerterMitChar.size(); i++) {
					for (int j = 0; j < wort.length() - 1; j++) {
						if (wort.charAt(j) != woerterMitChar.get(i).charAt(j)) {
							woerterMitChar.remove(i);
							j--;
						}
					}
				}
			}
		} catch (Exception e) {
			System.err.println("Kein passendes Wort gefunden!");
		}

		for (int i = 0; i < woerterMitChar.size(); i++) {
			System.out.println(woerterMitChar.get(i));
		}

		return "";
	}
}
