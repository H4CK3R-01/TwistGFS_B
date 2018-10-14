import java.util.ArrayList;

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

		if (UI.wortliste1.isSelected())
			file = "woerter_bwinf.txt";
		else if (UI.wortliste2.isSelected())
			file = "woerter_netzmafia.txt";
		else
			file = "woerter_sonstige.txt";

		wordList = DecryptEncrypt.readFile(file);
		wordList = DecryptEncrypt.removeWords(wort.charAt(0), wort.charAt(wort.length() - 1), wort.length(), wordList);

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
				str = swap(str, startIndex, i);
				permute(str, startIndex + 1, endIndex);
				str = swap(str, startIndex, i);
			}
		}
	}

	public static String swap(String a, int i, int j)
	{
		char temp;
		char[] charArray = a.toCharArray();
		temp = charArray[i];
		charArray[i] = charArray[j];
		charArray[j] = temp;
		return String.valueOf(charArray);
	}
}
