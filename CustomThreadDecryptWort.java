import java.time.LocalDateTime;
import java.util.ArrayList;

public class CustomThreadDecryptWort extends Thread
{
	private String wort;
	private String file;
	private ArrayList<String> wordList = new ArrayList<>();
	private ArrayList<String> wortListeNachPermutation = new ArrayList<>();

	public CustomThreadDecryptWort(String s, String file)
	{
		this.wort = s;
		this.file = file;
	}

	public void run()
	{
		Main.logger.setText("[ " + LocalDateTime.now().format(Main.df) + " ] Thread gestartet!");
		System.out.println("[ " + LocalDateTime.now().format(Main.df) + " ] Thread gestartet!");
		wortListeNachPermutation.clear();

		wordList = Main.readWordListFile(file);
		wordList = Main.removeWordsOfArray(wort.charAt(0), wort.charAt(wort.length() - 1), wort.length(), wordList);

		permute(wort, 0, wort.length() - 1, wordList, wortListeNachPermutation);

		Main.logger.setText("[ " + LocalDateTime.now().format(Main.df) + " ] Fertig!");
		System.out.println("[ " + LocalDateTime.now().format(Main.df) + " ] Fertig!");
	}

	public static void permute(String str, int startIndex, int endIndex, ArrayList<String> wordList,
			ArrayList<String> wortListeNachPermutation)
	{
		if (startIndex == endIndex)
		{
			if (!wortListeNachPermutation.contains(str) && wordList.contains(str))
			{
				wortListeNachPermutation.add(str);
				Main.logger.setText("[ " + LocalDateTime.now().format(Main.df) + " ] Wort Gefunden: " + str);
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

	public ArrayList<String> getWortListeNachPermutation()
	{
		return wortListeNachPermutation;
	}
}
