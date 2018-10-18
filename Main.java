import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;

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
	public static ArrayList<String> verfuegbareWortlisten = new ArrayList<>(); // Variable, die speichert welche Wortlisten vorhanden sind

	// Globale Variablen
	public static HashSet<String> wordList = new HashSet<>(); // HashSet, das alle Werte aus der Wortliste speichert
	public static Console console = new Console();
	public static DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm:ss"); // Variable für die formattierung der Zeitangaben
	public static ResourceBundle languageFile;

	/**
	 * Main Methode
	 * @param args
	 */
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

		new UI(); // UI starten
		console.start(); // Konsole starten
	}

	
	/**
	 * Kopiert Datei die als Parameter angegeben wird
	 * @param in Datei die Kopiert werden soll
	 * @param out Neue Datei die erstellt werden soll
	 */
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

	/**
	 * Ließt settings.txt ein und speichert alle Werte in Variablen
	 */
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

			
			File f = new File("./wordlist");
			File[] fileArray = f.listFiles();

			for (int i = 0; i < fileArray.length; i++)
			{
				if ((fileArray[i] != null) && fileArray[i].toString().toLowerCase().endsWith(".txt"))
				{
					Main.verfuegbareWortlisten.add(fileArray[i].getName());
				}
			}
			br.close();
		}
		catch (IOException e1)
		{
			UI.message.setText("settings.txt: " + Main.languageFile.getString("fileNotFound"));
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
			bw.close();
		}
		catch (IOException e1)
		{
			UI.message.setText("settings.txt: " + Main.languageFile.getString("fileNotFound"));
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
			UI.message.setText(file + " " + Main.languageFile.getString("fileNotFound"));
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
