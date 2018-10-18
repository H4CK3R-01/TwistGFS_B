import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;

public class CustomThreadDecryptWort extends Thread
{
	private long zaehler;
	private String wort;
	private String urspruenglichesWort;
	private HashSet<String> wortListeNachPermutation = new HashSet<>();

	public CustomThreadDecryptWort(String s)
	{
		this.wort = s;
	}

	public void run()
	{
		// Thread starten und Variablen leeren
		Main.console.setText("[ " + LocalDateTime.now().format(Main.df) + " ] " + Main.languageFile.getString("startedDecrypt"));
		UI.message.setText(Main.languageFile.getString("startedDecrypt"));
		wortListeNachPermutation.clear();
		
		urspruenglichesWort = wort;

		// Sonderzeichen entfernen
		wort = wort.replaceAll("[^A-Za-zßäöüÄÖÜ]", "");

		// ersten und letzten Buchstaben speichern
		String ersterBuchstabe = wort.substring(0, 1);
		String letzterBuchstabe = wort.substring(wort.length() - 1, wort.length());
		
		// Array sortieren
		char[] tempArray = wort.substring(1, wort.length() - 1).toCharArray();
		Arrays.sort(tempArray);
		wort = new String(tempArray);
		
		// Permutation starten
		permute("", wort, ersterBuchstabe, letzterBuchstabe);
		
		// Wenn kein Wort gefunden wurde, wird das ursprüngliche Wort in die Liste geschrieben
		if (wortListeNachPermutation.size() == 0)
		{
			wortListeNachPermutation.add(urspruenglichesWort);
		}

		Main.console.setText("[ " + LocalDateTime.now().format(Main.df) + " ] " + Main.languageFile.getString("finished"));
		UI.message.setText(Main.languageFile.getString("finished"));
	}

	/**
	 * Generiert alle möglichen Permutationen
	 * @param prefix erster Teil des Wortes
	 * @param str tweiter Teil des Wortes
	 * @param ersterBuchstabe Erster Buchstabe des Ursprünglichen Wortes (Um zu prüfen ob Das gesamte Wort in der Wortliste steht)
	 * @param letzterBuchstabe Letzter Buchstabe des Ursprünglichen Wortes (Um zu prüfen ob Das gesamte Wort in der Wortliste steht)
	 */
	private void permute(String prefix, String str, String ersterBuchstabe, String letzterBuchstabe)
	{
		int n = str.length();
		if (n == 0)
		{
			zaehler++;
			UI.progressBar.setValue((int) zaehler);
			// Wenn das generierte Wort in der Wortliste steht, wird es in die Liste wortListeNachPermutation gespeichert
			if (Main.wordList.contains(ersterBuchstabe + prefix + letzterBuchstabe))
			{
				wortListeNachPermutation.add(ersterBuchstabe + prefix + letzterBuchstabe);
				Main.console.setText("[ " + LocalDateTime.now().format(Main.df) + " ] " + Main.languageFile.getString("foundWord") + ": " + ersterBuchstabe + prefix + letzterBuchstabe);
			}
		}
		else
		{
			for (int i = 0; i < n; i++)
			{
				// Wenn ein Buchstabe doppelt vorkommt wird die Kombination nur einmal berechnet
				if(i>0 && str.charAt(i) == str.charAt(i-1)) continue;
				permute(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n), ersterBuchstabe, letzterBuchstabe);
			}
		}
	}

	public HashSet<String> getWortListeNachPermutation()
	{
		return wortListeNachPermutation;
	}
}
