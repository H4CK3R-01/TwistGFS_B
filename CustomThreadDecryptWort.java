import java.time.LocalDateTime;
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
		wort = wort.replaceAll("[^A-Za-zﬂ]" , "");
		Main.console.setText("[ " + LocalDateTime.now().format(Main.df) + " ] Thread gestartet!");
		System.out.println("[ " + LocalDateTime.now().format(Main.df) + " ] Thread gestartet!");
		wortListeNachPermutation.clear();

		permute(wort, 0, wort.length() - 1, Main.wordList, wortListeNachPermutation);
		if (wortListeNachPermutation.size() == 0)
		{
			wortListeNachPermutation.add(wort);
		}

		Main.console.setText("[ " + LocalDateTime.now().format(Main.df) + " ] Fertig!");
		System.out.println("[ " + LocalDateTime.now().format(Main.df) + " ] Fertig!");
	}

	
	public static void permute(String str, int startIndex, int endIndex, HashSet<String> wordList,
			HashSet<String> wortListeNachPermutation)
	{
		if (wortListeNachPermutation.size() > 0)
		{
			return;
		}
		if (startIndex == endIndex)
		{
			if (!wortListeNachPermutation.contains(str) && wordList.contains(str))
			{
				wortListeNachPermutation.add(str);
				Main.console.setText("[ " + LocalDateTime.now().format(Main.df) + " ] Wort Gefunden: " + str);
				System.out.println("[ " + LocalDateTime.now().format(Main.df) + " ] Wort Gefunden: " + str);

			}
		}
		else
		{
			for (int i = startIndex; i <= endIndex; i++)
			{
				str = Main.swapCharactersOfString(str, startIndex, i);
				permute(str, startIndex + 1, endIndex, wordList, wortListeNachPermutation);
				str = Main.swapCharactersOfString(str, startIndex, i);
			}
		}
	}

	public HashSet<String> getWortListeNachPermutation()
	{
		return wortListeNachPermutation;
	}
}
