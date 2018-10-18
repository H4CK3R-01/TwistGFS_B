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

	/**
	 * Prüft welche Aktion in der UI ausgeführt wurde und reagiert dann entsprechend
	 */
	@SuppressWarnings("resource")
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

			String line = "";
			String s = "";
			if (auswahl == 1) // entschlüsselter Text wird geöffnet
			{
				if (UI.dateiauswahl.showOpenDialog(UI.window) == JFileChooser.APPROVE_OPTION)
				{
					try
					{
						BufferedReader br = new BufferedReader(new FileReader(UI.dateiauswahl.getSelectedFile()));
						while ((line = br.readLine()) != null)
						{
							s += line;
						}
						UI.text2.setText(s);
						UI.message.setText(Main.languageFile.getString("finished"));
					}
					catch (IOException e1)
					{
						UI.message.setText(UI.dateiauswahl.getSelectedFile().getName() + " "
								+ Main.languageFile.getString("fileNotFound"));
					}
				}
			}
			else if (auswahl == 0) // verschlüsselter Text wird geöffnet
			{
				if (UI.dateiauswahl.showOpenDialog(UI.window) == JFileChooser.APPROVE_OPTION)
				{
					try
					{
						BufferedReader br = new BufferedReader(new FileReader(UI.dateiauswahl.getSelectedFile()));
						while ((line = br.readLine()) != null)
						{
							s += line;
						}
						UI.text1.setText(s);
						UI.message.setText(Main.languageFile.getString("finished"));
					}
					catch (IOException e1)
					{
						UI.message.setText(UI.dateiauswahl.getSelectedFile().getName() + " "
								+ Main.languageFile.getString("fileNotFound"));
					}
				}

			}
		}
		else if (e.getSource() == UI.dateiSave)
		{
			auswahl = JOptionPane.showOptionDialog(null, Main.languageFile.getString("saveDecryptedOrEncryptedFiles"),
					Main.languageFile.getString("save"), JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Twist.png"),
					new String[] { Main.languageFile.getString("encrypted"), Main.languageFile.getString("decrypted") },
					Main.languageFile.getString("decrypted"));

			if (auswahl == 1) // entschlüsselter Text wird geöffnet
			{
				if (UI.dateiauswahl.showOpenDialog(UI.window) == JFileChooser.APPROVE_OPTION)
				{
					try
					{
						BufferedWriter bw = new BufferedWriter(new FileWriter(UI.dateiauswahl.getSelectedFile()));
						bw.write(UI.text2.getText());
						bw.close();
						UI.message.setText(Main.languageFile.getString("finished"));
					}
					catch (IOException e1)
					{
						UI.message.setText(UI.dateiauswahl.getSelectedFile().getName() + " "
								+ Main.languageFile.getString("fileNotFound"));
					}
				}

			}
			else if (auswahl == 0) // verschlüsselter Text wird geöffnet
			{
				if (UI.dateiauswahl.showOpenDialog(UI.window) == JFileChooser.APPROVE_OPTION)
				{
					try
					{
						BufferedWriter bw = new BufferedWriter(new FileWriter(UI.dateiauswahl.getSelectedFile()));
						bw.write(UI.text1.getText());
						bw.close();
						UI.message.setText(Main.languageFile.getString("finished"));
					}
					catch (IOException e1)
					{
						UI.message.setText(UI.dateiauswahl.getSelectedFile().getName() + " "
								+ Main.languageFile.getString("fileNotFound"));
					}
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
				UI.message.setText(Main.languageFile.getString("serverNotFound"));
			}
			catch (IOException e1)
			{
				UI.message.setText(Main.languageFile.getString("serverNotFound"));
			}
		}
		else if (e.getSource() == UI.settingsBtnAbbrechen)
		{
			UI.settingsSettings.setVisible(false);
			UI.message.setText("Abgebrochen!");
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
				Main.languageFile = ResourceBundle.getBundle("de.florian.twist.de");
			}
			else
			{
				Main.language = 1;
				Main.languageFile = ResourceBundle.getBundle("de.florian.twist.en");
			}
			UI.elementeBeschriften();
			UI.konsole.setTitle(Main.languageFile.getString("console"));

			Main.stdWortliste = UI.settingsStdWortlisteComboBox.getSelectedItem().toString();

			// Einstellungen in Datei speichern
			Main.saveSettingsFile();

			UI.settingsSettings.setVisible(false);
			UI.message.setText(Main.languageFile.getString("savedSettings"));
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
					while (dateiExistiert)
					{
						File file = new File("wordlist/" + UI.dateiauswahl.getSelectedFile().getName());
						if (!file.exists()) // Prüfen ob Dateiname schon vorhanden ist
						{
							Main.copyFile(UI.dateiauswahl.getSelectedFile(),
									new File("wordlist/" + UI.dateiauswahl.getSelectedFile().getName()));
							Main.verfuegbareWortlisten.add(UI.dateiauswahl.getSelectedFile().getName());
							UI.wortlistenAuswahlMenu.add(new JRadioButtonMenuItem(
									Main.verfuegbareWortlisten.get(Main.verfuegbareWortlisten.size() - 1)));

							UI.helpSwitchWordList
									.add(UI.wortlistenAuswahlMenu.get(Main.verfuegbareWortlisten.size() - 1));
							UI.wortlistenAuswahlMenu.get(Main.verfuegbareWortlisten.size() - 1)
									.addActionListener(new ActionHandler());
							UI.wortlisteRadioButtons
									.add(UI.wortlistenAuswahlMenu.get(Main.verfuegbareWortlisten.size() - 1));
							UI.settingsStdWortlisteComboBox
									.addItem(Main.verfuegbareWortlisten.get(Main.verfuegbareWortlisten.size() - 1));
							
							dateiExistiert = false;
						}
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
		else
		{
			for (int i = 0; i < UI.wortlistenAuswahlMenu.size(); i++)
			{
				if (e.getSource() == UI.wortlistenAuswahlMenu.get(i))
				{
					file = "./wordlist/" + Main.verfuegbareWortlisten.get(i);
					Main.wordList = Main.readWordListFile(file);
					UI.message
							.setText(Main.verfuegbareWortlisten.get(i) + " " + Main.languageFile.getString("fileOpen"));
				}

			}

		}
	}
}
