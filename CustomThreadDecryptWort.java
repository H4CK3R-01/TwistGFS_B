import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;

public class CustomThreadDecryptWort extends Thread
{
	private String wort;
	private HashSet<String> wortListeNachPermutation = new HashSet<>();

	public CustomThreadDecryptWort(String s)
	{
		this.wort = s;
	}

	public void run()
	{
		// Thread starten und Variablen leeren
		Main.console.setText("[ " + LocalDateTime.now().format(Main.df) + " ] Thread gestartet!");
		System.out.println("[ " + LocalDateTime.now().format(Main.df) + " ] Thread gestartet!");
		wortListeNachPermutation.clear();
		

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
			wortListeNachPermutation.add(wort);
		}

		Main.console.setText("[ " + LocalDateTime.now().format(Main.df) + " ] Fertig!");
		System.out.println("[ " + LocalDateTime.now().format(Main.df) + " ] Fertig!");
	}

	private void permute(String prefix, String str, String ersterBuchstabe, String letzterBuchstabe)
	{
		int n = str.length();
		if (n == 0)
		{
			// Wenn das generierte Wort in der Wortliste steht, wird es in die Liste wortListeNachPermutation gespeichert
			if (Main.wordList.contains(ersterBuchstabe + prefix + letzterBuchstabe))
			{
				wortListeNachPermutation.add(ersterBuchstabe + prefix + letzterBuchstabe);
				Main.console.setText("[ " + LocalDateTime.now().format(Main.df) + " ] Wort Gefunden: " + ersterBuchstabe + prefix + letzterBuchstabe);
				System.out.println("[ " + LocalDateTime.now().format(Main.df) + " ] Wort Gefunden: " + ersterBuchstabe + prefix + letzterBuchstabe);
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
