import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.swing.UIManager;

public class Main
{
	public static String version = "1.2";
	public static String versionNew;
	public static String versionNewURL;

	public static void main(String[] args)
	{
		try
		{
			Scanner scanner = new Scanner(new URL("http://192.168.178.24/version.txt").openStream());
			versionNew = scanner.nextLine();
			versionNewURL = scanner.nextLine();
			scanner.close();
		}
		catch (MalformedURLException e1)
		{
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
