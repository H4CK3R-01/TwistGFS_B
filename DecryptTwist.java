import java.io.*;
import java.util.ArrayList; 

public class DecryptTwist
{
	private ArrayList<String> wortListeMitAllenWoerternNachPermutation = new ArrayList<>();

	public DecryptTwist()
	{

	}

	/**
	 * Mit dem BufferedReader wird die Datei die als Parameter übergeben wird
	 * Zeilenweise in eine Liste eingelesen.
	 * 
	 * @param file String des Dateinamens und Pfades, der Datei, die eingelesen
	 *             werden soll
	 * @return Liste, die alle Wörter aus der Datei enthält
	 */
	public ArrayList<String> readFile(String file)
	{
		ArrayList<String> list = new ArrayList<>();

		try
		{
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = br.readLine()) != null)
			{
				list.add(line);
			}
			br.close();
		}
		catch (IOException e)
		{
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
	 * @param wordList  Liste aus der die Wörter gelöscht werden sollen
	 * @return Liste, die die gelöschten Wörter nicht mehr enthält
	 */
	public ArrayList<String> removeWords(char firstChar, char lastChar, int length, ArrayList<String> wordList)
	{

		// Wörter mit falschem ertsten Buchstaben aus Liste löschen
		for (int i = 0; i < wordList.size(); i++)
		{
			if (wordList.get(i).charAt(0) != firstChar)
			{
				wordList.remove(i);
				i--;
			}
		}

		// Wörter mit falschem letzten Buchstaben aus Liste löschen
		for (int i = 0; i < wordList.size(); i++)
		{
			if (wordList.get(i).charAt(wordList.get(i).length() - 1) != lastChar)
			{
				wordList.remove(i);
				i--;
			}
		}

		// Wörter mit falscher Länge aus Liste löschen
		for (int i = 0; i < wordList.size(); i++)
		{
			if (length != wordList.get(i).length())
			{
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
	public ArrayList<String> decrypt(String wort)
	{
		ArrayList<String> wordList = new ArrayList<>();

		wordList = readFile("woerter.txt");
		wordList = removeWords(wort.charAt(0), wort.charAt(wort.length() - 1), wort.length(), wordList);

		// Buchstaben vergleichen
		wortListeMitAllenWoerternNachPermutation.clear();
		permute(wort, 0, wort.length() - 1);

		for (int i = 0; i < wortListeMitAllenWoerternNachPermutation.size(); i++)
		{
			if (!wordList.contains(wortListeMitAllenWoerternNachPermutation.get(i)))
			{
				wortListeMitAllenWoerternNachPermutation.remove(i);
				i--;
			}
		}

		return wortListeMitAllenWoerternNachPermutation;
	}

	/**
	 * permutation function
	 * 
	 * @param str string to calculate permutation for
	 * @param l   starting index
	 * @param r   end index
	 */
	public void permute(String str, int l, int r) // -------------------------------verstehen---------------------------------
	{
		if (l == r)
		{
			if (!wortListeMitAllenWoerternNachPermutation.contains(str))
				wortListeMitAllenWoerternNachPermutation.add(str);
		}
		else
		{
			for (int i = l; i <= r; i++)
			{
				str = swap(str, l, i);
				permute(str, l + 1, r);
				str = swap(str, l, i);
			}
		}
	}

	/**
	 * Swap Characters at position
	 * 
	 * @param a string value
	 * @param i position 1
	 * @param j position 2
	 * @return swapped string
	 */
	public String swap(String a, int i, int j)
	{
		char temp;
		char[] charArray = a.toCharArray();
		temp = charArray[i];
		charArray[i] = charArray[j];
		charArray[j] = temp;
		return String.valueOf(charArray);
	} // -----------------------------------------------------------verstehen----------------------------------------------------------------

	public ArrayList<String> getMoeglicheWoerter()
	{
		return wortListeMitAllenWoerternNachPermutation;
	}

}
