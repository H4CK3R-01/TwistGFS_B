import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

public class ActionHandler implements ActionListener
{
	int auswahl;
	String wort;
	String file;
	ArrayList<CustomThreadDecryptWort> threadTextEntschluesseln = new ArrayList<>();
	ArrayList<CustomThreadEncryptWort> threadTextVerschluesseln = new ArrayList<>();

	public void actionPerformed(ActionEvent e)
	{
		// Prüfen welche Aktion ausgeführt wurde 
		if (e.getSource() == UI.dateiOpen)
		{
			// Abfrage, ob Verschlüsselter oder entschlüüselter Text geöffnet werden soll
			auswahl = JOptionPane.showOptionDialog(null, Main.WoerterLanguage.get(37), Main.WoerterLanguage.get(38),
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Twist.png"),
					new String[] { Main.WoerterLanguage.get(39), Main.WoerterLanguage.get(40) },
					Main.WoerterLanguage.get(40));
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
			auswahl = JOptionPane.showOptionDialog(null, Main.WoerterLanguage.get(45), Main.WoerterLanguage.get(46),
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Twist.png"),
					new String[] { Main.WoerterLanguage.get(47), Main.WoerterLanguage.get(48) },
					Main.WoerterLanguage.get(48));
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
				UI.helpKonsole.setText(Main.WoerterLanguage.get(6));
			}
			else
			{
				UI.konsole.setVisible(true);
				UI.helpKonsole.setText(Main.WoerterLanguage.get(7));
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
				Scanner scanner = new Scanner(new URL("http://192.168.1781.24/version.txt").openStream()); // Versuchen Datei von Server herunterzuladen
				Main.versionNew = scanner.nextLine(); // erste Zeile aus Datei auslesen
				Main.versionNewURL = scanner.nextLine(); // Zweite Zeile aus Datei auslesen
				scanner.close(); // Scanner schließen
				
				if (!Main.version.equals(Main.versionNew)) // Neue Version verfügbar ist 
				{
					auswahl = JOptionPane.showOptionDialog(null,
							Main.WoerterLanguage.get(53) + Main.version + Main.WoerterLanguage.get(54) + Main.versionNew
									+ Main.WoerterLanguage.get(55),
							Main.WoerterLanguage.get(56), JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
							new ImageIcon("Twist.png"),
							new String[] { Main.WoerterLanguage.get(57), Main.WoerterLanguage.get(58) },
							Main.WoerterLanguage.get(58));
					if (auswahl == 1)
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
							Main.WoerterLanguage.get(59) + Main.version + Main.WoerterLanguage.get(60),
							Main.WoerterLanguage.get(61), JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
							new ImageIcon("Twist.png"), new String[] { Main.WoerterLanguage.get(62) },
							Main.WoerterLanguage.get(62));
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
			JOptionPane.showOptionDialog(UI.window, Main.WoerterLanguage.get(63), Main.WoerterLanguage.get(64),
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Twist.png"),
					new String[] { Main.WoerterLanguage.get(65) }, Main.WoerterLanguage.get(65));
			System.exit(0);
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

		else if (e.getSource() == UI.textEntschluesseln)
		{
			// String an allen Leerzeichen teilen
			UI.text2.setText("");
			String[] splited = UI.text1.getText().split("\\s+");
			threadTextEntschluesseln.clear();

			// Soviele Threats wie Wörter werden gestartet
			for (int i = 0; i < splited.length; i++)
			{
				threadTextEntschluesseln.add(new CustomThreadDecryptWort(splited[i]));
				threadTextEntschluesseln.get(threadTextEntschluesseln.size() - 1).start();
			}

			for (int i = 0; i < threadTextEntschluesseln.size(); i++)
			{
				try
				{
					threadTextEntschluesseln.get(i).join(); // Warten bis Thread fertig ist
					if (Main.zahlAnzeigen == 1)
					{
						// neues Wort mit Zahl dahinter in das Feld schreiben
						UI.text2.setText(UI.text2.getText() + " "
								+ threadTextEntschluesseln.get(i).getWortListeNachPermutation().iterator().next() + "("
								+ threadTextEntschluesseln.get(i).getWortListeNachPermutation().size() + ")");
					}
					else
					{
						// neues Wort ohne Zahl dahinter in das Feld schreiben
							UI.text2.setText(UI.text2.getText() + " " + threadTextEntschluesseln.get(i).getWortListeNachPermutation().iterator().next());
					}
				}
				catch (InterruptedException e1)
				{
					e1.printStackTrace();
				}
			}
			threadTextEntschluesseln.clear(); // Liste mit den Threads leeren
		}
		else if (e.getSource() == UI.textVerschluesseln)
		{
			// String an allen Leerzeichen teilen
			UI.text1.setText("");
			String[] splited = UI.text2.getText().split("\\s+");
			threadTextVerschluesseln.clear();

			// Soviele Threats wie Wörter werden gestartet
			for (int i = 0; i < splited.length; i++)
			{
				threadTextVerschluesseln.add(new CustomThreadEncryptWort(splited[i]));
				threadTextVerschluesseln.get(i).start();
			}

			for (int i = 0; i < threadTextVerschluesseln.size(); i++)
			{
				try
				{
					threadTextVerschluesseln.get(i).join(); // Warten bis Thread fertig ist
					UI.text1.setText(UI.text1.getText() + threadTextVerschluesseln.get(i).getWortNeu() + " "); // Wort in Textfeld schreiben
				}
				catch (InterruptedException e1)
				{
					e1.printStackTrace();
				}
			}
		}
		else if (e.getSource() == UI.wortEntschluesseln)
		{
			if (!UI.wort1.getText().contains(" ")) // Prüfen ob Wort Leerzeichen enthält
			{
				UI.wort2.setText(""); // Textfeld leeren
				wort = UI.wort1.getText(); // Wort auslesen

				CustomThreadDecryptWort threadWortEntschluesseln = new CustomThreadDecryptWort(wort); 
				threadWortEntschluesseln.start(); // neuen Thread starten

				try
				{
					threadWortEntschluesseln.join(); // Warten bis Thread fertig ist
				}
				catch (InterruptedException e1)
				{
					e1.printStackTrace();
				}

				Iterator<String> iterator = threadWortEntschluesseln.getWortListeNachPermutation().iterator();
				while (iterator.hasNext())
				{
					UI.wort2.setText(UI.wort2.getText() + iterator.next() + "\n"); // Entschlüsselte Wörter in Textfels speichern
				}
			}
			else
			{
				JOptionPane.showOptionDialog(UI.window, Main.WoerterLanguage.get(66), Main.WoerterLanguage.get(67),
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Twist.png"),
						new String[] { Main.WoerterLanguage.get(68) }, Main.WoerterLanguage.get(68));
			}

		}
		else if (e.getSource() == UI.wortVerschluesseln)
		{
			if (!UI.wort2.getText().contains(" ")) // Prüfen ob String Leerzeichen enthält
			{
				UI.wort1.setText(""); // Textfeld leeren
				wort = UI.wort2.getText(); // Wort auslesen

				CustomThreadEncryptWort threadWortEntschluesseln = new CustomThreadEncryptWort(wort);
				threadWortEntschluesseln.start(); // Thread starten

				try
				{
					threadWortEntschluesseln.join(); // Warten bis Thread fertig ist
				}
				catch (InterruptedException e1)
				{
					e1.printStackTrace();
				}
				UI.wort1.setText(threadWortEntschluesseln.getWortNeu()); // Wort in Textfeld schreiben
			}
			else
			{
				JOptionPane.showOptionDialog(UI.window, Main.WoerterLanguage.get(66), Main.WoerterLanguage.get(67),
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Twist.png"),
						new String[] { Main.WoerterLanguage.get(68) }, Main.WoerterLanguage.get(68));
			}
		}
		else
		{

		}
	}
}
