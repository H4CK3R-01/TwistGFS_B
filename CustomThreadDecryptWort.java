import java.util.ArrayList;
import de.florian.twist.Main;
import de.florian.twist.UI;

public class CustomThreadDecryptWort extends Thread
{
	String wort;
	String file;

	public CustomThreadDecryptWort(String s)
	{
		this.wort = s;
	}

	public void run()
	{
		ArrayList<String> wordList = new ArrayList<>();

		for (int i = 0; i < UI.wortlistenAuswahlMenu.size(); i++)
		{
			if (UI.wortlistenAuswahlMenu.get(i).isSelected())
				file = "woerter" + i + ".txt";
		}

		wordList = Main.readFile(file);
		wordList = Main.removeWords(wort.charAt(0), wort.charAt(wort.length() - 1), wort.length(), wordList);

		System.out.println("Thread gestartet!");

		try
		{
			Thread.sleep(500);
		}
		catch (InterruptedException e)
		{
		}

		// Buchstaben vergleichen
		Main.wortListeNachPermutation.clear();
		UI.wort2.setText("");

		System.out.println("Permutation gestartet...");
		permute(wort, 0, wort.length() - 1);

		System.out.println("Vergleiche Wörter...");
		for (int i = 0; i < Main.wortListeNachPermutation.size(); i++)
		{
			if (!wordList.contains(Main.wortListeNachPermutation.get(i)))
			{
				Main.wortListeNachPermutation.remove(i);
				i--;
			}
			else
			{
				UI.wort2.setText(UI.wort2.getText() + Main.wortListeNachPermutation.get(i) + "\n");
				System.out.println(Main.wortListeNachPermutation.get(i));
			}
		}
		System.out.println("Fertig!");
	}

	public static void permute(String str, int startIndex, int endIndex)
	{
		if (startIndex == endIndex)
		{
			if (!Main.wortListeNachPermutation.contains(str))
			{
				Main.wortListeNachPermutation.add(str);
			}
		}
		else
		{
			for (int i = startIndex; i <= endIndex; i++)
			{
				str = Main.swap(str, startIndex, i);
				permute(str, startIndex + 1, endIndex);
				str = Main.swap(str, startIndex, i);
			}
		}
	}
}
