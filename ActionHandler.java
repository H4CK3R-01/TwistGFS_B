import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

public class ActionHandler implements ActionListener
{
	int auswahl;
	String file;

	public void actionPerformed(ActionEvent e)
	{
		// Prüfen welche Aktion ausgeführt wurde 
		if (e.getSource() == UI.dateiOpen)
		{
			// Abfrage, ob Verschlüsselter oder entschlüüselter Text geöffnet werden soll
			auswahl = JOptionPane.showOptionDialog(null, Main.languageFile.getString("openDecryptedOrEncryptedFiles"),
					Main.languageFile.getString("open"), JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Twist.png"),
					new String[] { Main.languageFile.getString("encrypted"), Main.languageFile.getString("decrypted") },
					Main.languageFile.getString("decrypted"));
			if (UI.dateiauswahl.showOpenDialog(UI.window) == JFileChooser.APPROVE_OPTION)
			{
				try
				{
					BufferedReader br = new BufferedReader(new FileReader(UI.dateiauswahl.getSelectedFile()));
					String line = "";
					String s = "";
					if (auswahl == 1) // entschlüsselter Text wird geöffnet
					{
						while ((line = br.readLine()) != null)
						{
							s += line;
						}
						UI.text2.setText(s);
					}
					else // verschlüsselter Text wird geöffnet
					{
						while ((line = br.readLine()) != null)
						{
							s += line;
						}
						UI.text1.setText(s);
					}
					br.close();
				}
				catch (IOException e1)
				{
				}
			}
		}
		else if (e.getSource() == UI.dateiSave)
		{
			// Abfrage, ob Verschlüsselter oder entschlüüselter Text gespeichert werden soll
			auswahl = JOptionPane.showOptionDialog(null, Main.languageFile.getString("saveDecryptedOrEncryptedFiles"),
					Main.languageFile.getString("save"), JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Twist.png"),
					new String[] { Main.languageFile.getString("encrypted"), Main.languageFile.getString("decrypted") },
					Main.languageFile.getString("decrypted"));
			if (UI.dateiauswahl.showOpenDialog(UI.window) == JFileChooser.APPROVE_OPTION)
			{
				try
				{
					BufferedWriter bw = new BufferedWriter(new FileWriter(UI.dateiauswahl.getSelectedFile()));
					if (auswahl == 1) // entschlüsselter Text wird gespeichert
					{
						bw.write(UI.text2.getText());
					}
					else // verschlüsselter Text wird gespeichert
					{
						bw.write(UI.text1.getText());
					}
					bw.close();
				}
				catch (IOException e1)
				{
				}
			}
		}
		else if (e.getSource() == UI.dateiExit)
		{
			System.exit(0); // Programm wird beendet
		}
		else if (e.getSource() == UI.helpKonsole)
		{
			// Wenn die Konsole bereits sichtbar ist, wird sie ausgeblendet, ansonsten wird sie angezeigt
			if (UI.konsole.isVisible())
			{
				UI.konsole.setVisible(false);
				UI.helpKonsole.setText(Main.languageFile.getString("consoleOpen"));
			}
			else
			{
				UI.konsole.setVisible(true);
				UI.helpKonsole.setText(Main.languageFile.getString("consoleClose"));
			}
		}
		else if (e.getSource() == UI.helpSettings)
		{
			new Settings(); // Settings-UI wird geöffnet
		}
		else if (e.getSource() == UI.helpHelp)
		{
			new Help(); // Hilfe-UI wird geöffnet
		}
		else if (e.getSource() == UI.helpUpdateProgram)
		{
			try
			{
				Scanner scanner = new Scanner(new URL("http://192.168.178.24/version.txt").openStream()); // Versuchen Datei von Server herunterzuladen
				Main.versionNew = scanner.nextLine(); // erste Zeile aus Datei auslesen
				Main.versionNewURL = scanner.nextLine(); // Zweite Zeile aus Datei auslesen
				scanner.close(); // Scanner schließen

				if (!Main.version.equals(Main.versionNew)) // Neue Version verfügbar ist 
				{
					auswahl = JOptionPane.showOptionDialog(null,
							Main.languageFile.getString("version1") + Main.version + " "
									+ Main.languageFile.getString("version2") + Main.versionNew
									+ Main.languageFile.getString("version3"),
							Main.languageFile.getString("checkVersion"), JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Twist.png"),
							new String[] { Main.languageFile.getString("yes"), Main.languageFile.getString("no") },
							Main.languageFile.getString("yes"));
					if (auswahl == 0)
					{
						try
						{
							Desktop.getDesktop().browse(new URL(Main.versionNewURL).toURI()); // URL im Browser öffnen
						}
						catch (IOException | URISyntaxException e1)
						{
						}

					}
				}
				else // Keine neue Version verfügbar
				{
					JOptionPane.showOptionDialog(null,
							Main.languageFile.getString("version4") + Main.version + " "
									+ Main.languageFile.getString("version5"),
							Main.languageFile.getString("checkVersion"), JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Twist.png"),
							new String[] { Main.languageFile.getString("ok") }, Main.languageFile.getString("ok"));
				}
			}
			catch (MalformedURLException e1)
			{
				System.err.println("Server nicht gefunden");
			}
			catch (IOException e1)
			{
				System.err.println("Server nicht gefunden");
			}
		}
		else if (e.getSource() == UI.settingsBtnAbbrechen)
		{
			UI.settingsSettings.setVisible(false);
		}
		else if (e.getSource() == UI.settingsBtnSpeichern)
		{
			// Einstellungen in Variablen speichern
			if (UI.settingsZahlAnzeigenJa.isSelected())
			{
				Main.zahlAnzeigen = 1;
			}
			if (UI.settingsZahlAnzeigenNein.isSelected())
			{
				Main.zahlAnzeigen = 0;
			}

			if (UI.settingsLanguageComboBox.getSelectedIndex() == 0)
			{
				Main.language = 0;
			}
			else
			{
				Main.language = 1;
			}

			Main.stdWortliste = UI.settingsStdWortlisteComboBox.getSelectedItem().toString();

			// Einstellungen in Datei speichern
			Main.saveSettingsFile();

			// Dialog um das Programm neuzustarten
			UI.settingsSettings.setVisible(false);
		}
		else if (e.getSource() == UI.settingsAddWortListeBtn)
		{
			// Neue Wortliste in den Einstellungen hinzufügen
			int returnVal = UI.dateiauswahl.showOpenDialog(UI.window);
			if (returnVal == JFileChooser.APPROVE_OPTION)
			{
				try
				{
					boolean dateiExistiert = true;
					int i = 0;
					while (dateiExistiert)
					{
						File file = new File("wordlist/woerter" + i + ".txt");
						if (!file.exists()) // Prüfen ob Dateiname schon vorhanden ist
						{
							Main.copyFile(UI.dateiauswahl.getSelectedFile(), new File("wordlist/woerter" + i + ".txt"));
							Main.verfuegbareWortlisten.add("woerter" + i + ".txt");
							dateiExistiert = false;
						}
						i++;
					}
				}
				catch (IOException e1)
				{
				}
			}
		}

		else if (e.getSource() == UI.textEntschluesseln) new StarteThread("DecryptText").start();
		else if (e.getSource() == UI.textVerschluesseln) new StarteThread("EncryptText").start();
		else if (e.getSource() == UI.wortEntschluesseln) new StarteThread("DecryptWort").start();
		else if (e.getSource() == UI.wortVerschluesseln) new StarteThread("EncryptWort").start();
	}
}
