import java.time.LocalDateTime;
import java.util.ArrayList;

public class CustomThreadDecryptWort extends Thread {
	ArrayList<String> wortNeu = new ArrayList<>();
	String wort;
	String file;

	public CustomThreadDecryptWort(String s) {
		this.wort = s;
	}

	public void run() {
		ArrayList<String> wordList = new ArrayList<>();
		Main.logger.setText("[ " + LocalDateTime.now().format(Main.df) + " ] Thread gestartet!");
        System.out.println("[ " + LocalDateTime.now().format(Main.df) + " ] Thread gestartet!");

		for (int i = 0; i < UI.wortlistenAuswahlMenu.size(); i++) {
			if (UI.wortlistenAuswahlMenu.get(i).isSelected())
				file = "wordlist/woerter" + i + ".txt";
		}

		wordList = Main.readWordListFile(file);
		wordList = Main.removeWordsOfArray(wort.charAt(0), wort.charAt(wort.length() - 1), wort.length(), wordList);

		System.out.println("[ " + LocalDateTime.now().format(Main.df) + " ] Datei fertig!");
		// Buchstaben vergleichen
		Main.wortListeNachPermutation.clear();
		permute(wort, 0, wort.length() - 1);

		Main.logger.setText("[ " + LocalDateTime.now().format(Main.df) + " ] Vergleiche Wörter...");
		System.out.println("[ " + LocalDateTime.now().format(Main.df) + " ] Vergleiche Wörter...");
		for (int i = 0; i < Main.wortListeNachPermutation.size(); i++) {
			if (!wordList.contains(Main.wortListeNachPermutation.get(i))) {
				Main.wortListeNachPermutation.remove(i);
				i--;
			} else {
				wortNeu.add(Main.wortListeNachPermutation.get(i));
				Main.logger.setText("[ " + LocalDateTime.now().format(Main.df) + " ] Wort Gefunden: " + Main.wortListeNachPermutation.get(i));
				System.out.println("[ " + LocalDateTime.now().format(Main.df) + " ] Wort Gefunden: " + Main.wortListeNachPermutation.get(i));
			}
		}
		Main.logger.setText("[ " + LocalDateTime.now().format(Main.df) + " ] Fertig!");
		System.out.println("[ " + LocalDateTime.now().format(Main.df) + " ] Fertig!");
	}

	public static void permute(String str, int startIndex, int endIndex) {
		if (startIndex == endIndex) {
			if (!Main.wortListeNachPermutation.contains(str)) {
				Main.wortListeNachPermutation.add(str);
			}
		} else {
			for (int i = startIndex; i <= endIndex; i++) {
				str = Main.swapCharactersOfString(str, startIndex, i);
				permute(str, startIndex + 1, endIndex);
				str = Main.swapCharactersOfString(str, startIndex, i);
			}
		}
	}
	
	public ArrayList<String> getWortNeu()
	{
		return wortNeu;
	}
}
