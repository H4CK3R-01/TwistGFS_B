import java.util.ArrayList;
import de.florian.twist.Main;
import de.florian.twist.UI;

public class CustomThreadDecryptText extends Thread
{
	String text;
	String file;

	public CustomThreadDecryptText(String s)
	{
		this.text = s;
	}

	public void run()
	{

		String[] splited = text.split("\\s+");

		System.out.println("Thread gestartet!");

		for (int i = 0; i < UI.wortlistenAuswahlMenu.size(); i++)
		{

			if (UI.wortlistenAuswahlMenu.get(i).isSelected())
				file = "woerter" + i + ".txt";
		}
		System.out.println(file);
		UI.text2.setText("");

		for (int i = 0; i < splited.length; i++)
		{
			ArrayList<String> wordList = new ArrayList<>();

			wordList = Main.readFile(file);
			wordList = Main.removeWords(splited[i].charAt(0), splited[i].charAt(splited[i].length() - 1),
					splited[i].length(), wordList);

			try
			{
				Thread.sleep(500);
			}
			catch (InterruptedException e)
			{
			}

			// Buchstaben vergleichen
			Main.wortListeNachPermutation.clear();

			System.out.println("Permutation gestartet...");
			permute(splited[i], 0, splited[i].length() - 1);

			System.out.println("Vergleiche Wörter...");
			for (int j = 0; j < Main.wortListeNachPermutation.size(); j++)
			{
				if (!wordList.contains(Main.wortListeNachPermutation.get(j)))
				{
					Main.wortListeNachPermutation.remove(j);
					j--;
				}
			}

			if (Main.zahlAnzeigen == 1)
			{
				UI.text2.setText(UI.text2.getText() + " " + Main.wortListeNachPermutation.get(0) + "("
						+ Main.wortListeNachPermutation.size() + ")");
			}
			else
			{
				UI.text2.setText(UI.text2.getText() + " " + Main.wortListeNachPermutation.get(0));
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
