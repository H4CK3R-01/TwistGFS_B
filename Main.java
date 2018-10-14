import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.UIManager;

public class Main
{
	public static String version = "1.5";
	public static String versionNew;
	public static String versionNewURL;

	public static int firstStart;
	public static int zahlInKlammern;
	public static int anzahlMoeglicherWoerter = 10000;
	public static int anzahlWoerter = 0;

	public static void main(String[] args)
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
		}

		BufferedReader br;
		try
		{
			br = new BufferedReader(new FileReader("settings.txt"));
			UI.firstStart = Integer.parseInt(br.readLine());
			UI.settigsZahl = Integer.parseInt(br.readLine());
			br.close();
		}
		catch (IOException e1)
		{
		}

		new UI();
		
		//new Permutation("Test");
	}
}
