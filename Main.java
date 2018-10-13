import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.UIManager;

public class Main
{
	public static String version = "1.3";
	public static String versionNew;
	public static String versionNewURL;

	public static int firstStart;
	public static int zahlInKlammern;

	public static void main(String[] args)
	{
		BufferedReader br;
		try
		{
			br = new BufferedReader(new FileReader("settings.txt"));
			if (Integer.parseInt(br.readLine()) == 0)
			{
				UI.firstStart = true;
			}
			else
			{
				UI.firstStart = false;
			}
			
			if (Integer.parseInt(br.readLine()) == 1)
			{
				UI.settigsZahl = true;
			}
			else
			{
				UI.settigsZahl = false;
			}
			br.close();
		}
		catch (IOException e1)
		{
		}


		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
		}
		new UI();
	}
}
