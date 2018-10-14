import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import javax.swing.UIManager;

public class Main {
	/*
	 * Aufbau der Datei 'settings.txt' 1 --> erster Start ( 1 = erster Start || 0 =
	 * nicht erster Start ) 1 --> Zahl Anzeigen ( 1 = Zahl anzeigen || 0 = Zahl
	 * nicht anzeigen ) 0 --> Sprache ( 1 = Emglisch || 0 = Deutsch) woerter2.txt
	 * --> Standard Wortliste woerter0.txt --> Wortlisten nacheinander aufgelistet
	 * woerter1.txt woerter2.txt woerter3.txt
	 */

	public static String version = "1.6";
	public static String versionNew;
	public static String versionNewURL;

	public static int firstStart = 1;
	public static int zahlAnzeigen = 1;
	public static int language = 0;
	public static String stdWortliste;

	public static ArrayList<String> verfuegbareWortlisten = new ArrayList<>();

	public static ArrayList<String> textWoerter = new ArrayList<>();
	public static ArrayList<ArrayList<String>> wortWoerter = new ArrayList<>();
	public static ArrayList<String> wortListeNachPermutation = new ArrayList<>();

	/**
	 *  
	 */
	public static void main(String[] args) {
		// Moderne Benutzeroberfläche laden
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}

		// Werte aus Einstellungs-Datei auslesen
		readSettings();
		
		// UI starten
		new UI();
		new Settings();
		UI.settingsSettings.setVisible(false);
	}
	
	@SuppressWarnings("resource")
	public static void copyFile(File in, File out) throws IOException {
		FileChannel inChannel = null;
		FileChannel outChannel = null;
		try {
			inChannel = new FileInputStream(in).getChannel();
			outChannel = new FileOutputStream(out).getChannel();
			inChannel.transferTo(0, inChannel.size(), outChannel);
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (inChannel != null)
					inChannel.close();
				if (outChannel != null)
					outChannel.close();
			} catch (IOException e) {
			}
		}
	}

	public static void readSettings() {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("settings.txt"));
			Main.firstStart = Integer.parseInt(br.readLine());
			Main.zahlAnzeigen = Integer.parseInt(br.readLine());
			Main.language = Integer.parseInt(br.readLine());
			Main.stdWortliste = br.readLine();

			String s;
			while ((s = br.readLine()) != null) {
				Main.verfuegbareWortlisten.add(s);
			}
			br.close();
		} catch (IOException e1) {
		}
	}
	
	public static ArrayList<String> readFile(String file) {
		ArrayList<String> list = new ArrayList<>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = br.readLine()) != null) {
				list.add(line);
			}
			br.close();
		} catch (IOException e) {
			System.err.println(
					file + ": Datei konnte nicht geöffnet werden. Möglicherweise wurde sie gelöscht oder verschoben.");
		}

		return list;
	}

	public static ArrayList<String> removeWords(char firstChar, char lastChar, int length, ArrayList<String> wordList) {

		// Wörter mit falschem ertsten Buchstaben aus Liste löschen
		for (int i = 0; i < wordList.size(); i++) {
			if (wordList.get(i).charAt(0) != firstChar) {
				wordList.remove(i);
				i--;
			}
		}

		// Wörter mit falschem letzten Buchstaben aus Liste löschen
		for (int i = 0; i < wordList.size(); i++) {
			if (wordList.get(i).charAt(wordList.get(i).length() - 1) != lastChar) {
				wordList.remove(i);
				i--;
			}
		}

		// Wörter mit falscher Länge aus Liste löschen
		for (int i = 0; i < wordList.size(); i++) {
			if (length != wordList.get(i).length()) {
				wordList.remove(i);
				i--;
			}
		}

		return wordList;
	}

	public static String swap(String a, int i, int j) {
		char temp;
		char[] charArray = a.toCharArray();
		temp = charArray[i];
		charArray[i] = charArray[j];
		charArray[j] = temp;
		return String.valueOf(charArray);
	}
}
