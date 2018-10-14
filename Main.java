import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.UIManager;

public class Main
{
	/*
	 * Aufbau der Datei 'settings.txt' 1 --> erster Start ( 1 = erster Start || 0 =
	 * nicht erster Start ) 1 --> Zahl Anzeigen ( 1 = Zahl anzeigen || 0 = Zahl
	 * nicht anzeigen ) 0 --> Sprache ( 1 = Englisch || 0 = Deutsch) woerter2.txt
	 * --> Standard Wortliste woerter0.txt woerter1.txt --> Wortlisten nacheinander
	 * aufgelistet woerter2.txt woerter3.txt
	 */

	// Variablen für Einstellungen
	public static String version = "1.7";
	public static String versionNew;
	public static String versionNewURL;

	public static int firstStart = 1;
	public static int zahlAnzeigen = 1;
	public static int language = 0;
	public static String stdWortliste;
	public static ArrayList<String> WoerterLanguage = new ArrayList<>();
	public static ArrayList<String> verfuegbareWortlisten = new ArrayList<>();

	// Globale Variablen
	public static HashSet<String> wordList = new HashSet<>();
	public static Console console = new Console();
	public static ArrayList<String> textWoerter = new ArrayList<>();
	public static ArrayList<ArrayList<String>> wortWoerter = new ArrayList<>();
	// public static ArrayList<String> wortListeNachPermutation = new ArrayList<>();
	public static DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm:ss");

	public static void main(String[] args)
	{
		// Moderne Benutzeroberfläche laden
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
		}

		/*
		 * if (language == 1) { readLanguageFile("lang/en.txt"); } else {
		 * readLanguageFile("lang/de.txt"); }
		 */

		new UI();
		console.start();
	}

	@SuppressWarnings("resource")
	public static void copyFile(File in, File out) throws IOException
	{
		FileChannel inChannel = null;
		FileChannel outChannel = null;
		try
		{
			inChannel = new FileInputStream(in).getChannel();
			outChannel = new FileOutputStream(out).getChannel();
			inChannel.transferTo(0, inChannel.size(), outChannel);
		}
		catch (IOException e)
		{
			throw e;
		} finally
		{
			try
			{
				if (inChannel != null) inChannel.close();
				if (outChannel != null) outChannel.close();
			}
			catch (IOException e)
			{
			}
		}
	}

	public static void readSettingsFile()
	{
		BufferedReader br;
		try
		{
			br = new BufferedReader(new FileReader("settings.txt"));
			Main.firstStart = Integer.parseInt(br.readLine());
			Main.zahlAnzeigen = Integer.parseInt(br.readLine());
			Main.language = Integer.parseInt(br.readLine());
			Main.stdWortliste = br.readLine();

			String s;
			while ((s = br.readLine()) != null)
			{
				Main.verfuegbareWortlisten.add(s);
			}
			br.close();
		}
		catch (IOException e1)
		{
		}
	}

	public static void saveSettingsFile()
	{
		try
		{
			BufferedWriter bw = new BufferedWriter(new FileWriter("settings.txt"));
			bw.write(Main.firstStart + "");
			bw.newLine();
			bw.write(Main.zahlAnzeigen + "");
			bw.newLine();
			bw.write(Main.language + "");
			bw.newLine();
			bw.write(Main.stdWortliste);
			bw.newLine();
			for (int i = 0; i < Main.verfuegbareWortlisten.size(); i++)
			{
				bw.write(Main.verfuegbareWortlisten.get(i));
				bw.newLine();
			}
			bw.close();
		}
		catch (IOException e1)
		{
		}
	}

	public static void readLanguageFile(String file)
	{
		BufferedReader br;
		try
		{
			br = new BufferedReader(new FileReader(file));
			String s;
			while ((s = br.readLine()) != null)
			{
				Main.WoerterLanguage.add(s);
			}
			br.close();
		}
		catch (IOException e1)
		{
		}
	}

	public static HashSet<String> readWordListFile(String file)
	{
		HashSet<String> list = new HashSet<>();

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
					"[ " + LocalDateTime.now().format(Main.df) + " ] " + file + ": " + Main.WoerterLanguage.get(69));
		}

		return list;
	}

	public static String swapCharactersOfString(String a, int i, int j)
	{
		char temp;
		char[] charArray = a.toCharArray();
		temp = charArray[i];
		charArray[i] = charArray[j];
		charArray[j] = temp;
		return String.valueOf(charArray);
	}
}
