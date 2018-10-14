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
	 * Aufbau der Datei 'settings.txt' 
	 * 1 --> erster Start ( 1 = erster Start || 0 = nicht erster Start ) 
	 * 1 --> Zahl Anzeigen ( 1 = Zahl anzeigen || 0 = Zahl nicht anzeigen ) 
	 * 0 --> Sprache ( 1 = Englisch || 0 = Deutsch) 
	 * woerter2.txt --> Standard Wortliste 
	 * woerter0.txt --> Wortlisten nacheinander aufgelistet
	 * woerter1.txt  
	 * woerter2.txt 
	 * woerter3.txt
	 */

	// Variablen für Einstellungen
	public static String version = "2.0"; // Variable für die Version des Programms
	public static String versionNew; // Variable für die Nummer der neuen Version
	public static String versionNewURL; // Variable für die URL der neuen Version

	public static int firstStart = 1; // Variable die speichert, ob das Programm das erste mal gestartet wurde
	public static int zahlAnzeigen = 1; // Variable die speichert, ob die Zahl hinter den Wörtern bei 'Text' angezeigt werden soll
	public static int language = 0; // Variable die speichert, welche Sprache des Programms ausgewählt ist
	public static String stdWortliste; // Variable die speichert welche Wortliste die Standard-Wortliste ist 
	public static ArrayList<String> WoerterLanguage = new ArrayList<>(); // Variable die alle Texte speichert, je nachdem welchen Wert 'language' hat
	public static ArrayList<String> verfuegbareWortlisten = new ArrayList<>(); // Variable, die speichert welche Wortlisten vorhanden sind

	// Globale Variablen
	public static HashSet<String> wordList = new HashSet<>(); // HashSet, das alle Werte aus der Wortliste speichert
	public static Console console = new Console();
	public static DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm:ss"); // Variable für die formattierung der Zeitangaben

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

		// Sprach-Datei einlesen
		/*if (language == 1)
		{
			readLanguageFile("lang/en.txt");
		}
		else
		{
			readLanguageFile("lang/de.txt");
		}*/

		
		new UI(); // UI starten
		console.start(); // Konsole starten
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
			System.err.println(
					"[ " + LocalDateTime.now().format(Main.df) + " ] settings.txt: " + Main.WoerterLanguage.get(69));
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
			System.err.println(
					"[ " + LocalDateTime.now().format(Main.df) + " ] settings.txt: " + Main.WoerterLanguage.get(69));
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
			System.err.println(
					"[ " + LocalDateTime.now().format(Main.df) + " ] " + file + ": " + Main.WoerterLanguage.get(69));
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
