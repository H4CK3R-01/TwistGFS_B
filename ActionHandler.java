import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

public class ActionHandler implements ActionListener {
	int auswahl;

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == UI.dateiOpen) {
			if (UI.tabpane.getSelectedIndex() == 0) {
				if (Main.language == 0) {
					auswahl = JOptionPane.showOptionDialog(null,
							"Wollen Sie einen verschlüsselten Text oder einen entschlüsselten Text öffnen?", "Öffnen",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
							new ImageIcon("Twist.png"), new String[] { "Verschlüsselt", "Entschlüsselt" },
							"Entschlüsselt");
				} else {
					auswahl = JOptionPane.showOptionDialog(null,
							"Do you want to open an encrypted or a decrypted text?", "Open",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
							new ImageIcon("Twist.png"), new String[] { "Encoded", "Decoded" }, "Encoded");
				}
				if (UI.dateiauswahl.showOpenDialog(UI.window) == JFileChooser.APPROVE_OPTION) {
					try {
						BufferedReader br = new BufferedReader(new FileReader(UI.dateiauswahl.getSelectedFile()));
						String line = "";
						String s = "";
						if (auswahl == 1) {
							while ((line = br.readLine()) != null) {
								s += line;
							}
							UI.text2.setText(s);
						} else {
							while ((line = br.readLine()) != null) {
								s += line;
							}
							UI.text1.setText(s);
						}
						br.close();
					} catch (IOException e1) {
					}
				}
			} else {
				if (Main.language == 0) {
					auswahl = JOptionPane.showOptionDialog(null,
							"Wollen Sie eine verschlüsseltes Wort oder eine entschlüsseltes Wort öffnen?", "Öffnen",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
							new ImageIcon("Twist.png"), new String[] { "Verschlüsselt", "Entschlüsselt" },
							"Entschlüsselt");
				} else {
					auswahl = JOptionPane.showOptionDialog(null,
							"Do you want to open an encrypted or a decrypted word?", "Open",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
							new ImageIcon("Twist.png"), new String[] { "Encoded", "Decoded" }, "Encoded");
				}

				if (UI.dateiauswahl.showOpenDialog(UI.window) == JFileChooser.APPROVE_OPTION) {
					try {
						BufferedReader br = new BufferedReader(new FileReader(UI.dateiauswahl.getSelectedFile()));
						String line = "";
						String s = "";
						if (auswahl == 1) {
							while ((line = br.readLine()) != null) {
								s += line;
							}
							UI.wort2.setText(s);
						} else {
							while ((line = br.readLine()) != null) {
								s += line;
							}
							UI.wort1.setText(s);
						}
						br.close();
					} catch (IOException e1) {
					}
				}
			}
		} else if (e.getSource() == UI.dateiSave) {
			if (UI.tabpane.getSelectedIndex() == 0) {
				if (Main.language == 0) {
					auswahl = JOptionPane.showOptionDialog(null,
							"Wollen Sie den verschlüsselten Text oder den entschlüsselten Text speichern?", "Speichern",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
							new ImageIcon("Twist.png"), new String[] { "Verschlüsselt", "Entschlüsselt" },
							"Entschlüsselt");
				} else {
					auswahl = JOptionPane.showOptionDialog(null,
							"Do you want to save the encrypted text or the decrypted text?", "Save",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
							new ImageIcon("Twist.png"), new String[] { "Encoded", "Decoded" }, "Encoded");
				}

				if (UI.dateiauswahl.showOpenDialog(UI.window) == JFileChooser.APPROVE_OPTION) {
					try {
						BufferedWriter bw = new BufferedWriter(new FileWriter(UI.dateiauswahl.getSelectedFile()));
						if (auswahl == 1) {
							bw.write(UI.text2.getText());
						} else {
							bw.write(UI.text1.getText());
						}
						bw.close();
					} catch (IOException e1) {
					}
				}
			} else {
				if (Main.language == 0) {
					auswahl = JOptionPane.showOptionDialog(null,
							"Wollen Sie das verschlüsselte Wort oder das/die entschlüsselte/n Wort/e speichern?",
							"Speichern", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
							new ImageIcon("Twist.png"), new String[] { "Verschlüsselt", "Entschlüsselt" },
							"Entschlüsselt");
				} else {
					auswahl = JOptionPane.showOptionDialog(null,
							"Do you want to save the encrypted word or the decrypted word (s)?", "Save",
							JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
							new ImageIcon("Twist.png"), new String[] { "Encoded", "Decoded" }, "Encoded");
				}

				if (UI.dateiauswahl.showOpenDialog(UI.window) == JFileChooser.APPROVE_OPTION) {
					try {
						BufferedWriter bw = new BufferedWriter(new FileWriter(UI.dateiauswahl.getSelectedFile()));
						if (auswahl == 1) {
							bw.write(UI.wort2.getText());
						} else {
							bw.write(UI.wort1.getText());
						}
						bw.close();
					} catch (IOException e1) {
					}
				}
			}
		} else if (e.getSource() == UI.dateiExit) {
			System.exit(0);
		} else if (e.getSource() == UI.helpSettings) {
			UI.settingsSettings.setVisible(true);
		} else if (e.getSource() == UI.helpHelp) {
			new Help();
		} else if (e.getSource() == UI.helpUpdateProgram) {
			try {
				Scanner scanner = new Scanner(new URL("http://192.168.178.24/version.txt").openStream());
				Main.versionNew = scanner.nextLine();
				Main.versionNewURL = scanner.nextLine();
				scanner.close();
			} catch (MalformedURLException e1) {
			} catch (IOException e1) {
			}
			if (!Main.version.equals(Main.versionNew)) {
				if (Main.language == 0) {
					auswahl = JOptionPane.showOptionDialog(null,
							"Aktuell verwenden sie Version " + Main.version
									+ " des Programms.\n\nDie neuste Version ist " + Main.versionNew
									+ "\n\nWollen Sie die neue Version herunterladen?",
							"Version prüfen", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
							new ImageIcon("Twist.png"), new String[] { "Nein", "Ja" }, "Ja");
				} else {
					auswahl = JOptionPane.showOptionDialog(null,
							"Currently you use version " + Main.version + " of the program.\n\nThe latest version is "
									+ Main.versionNew + "\n\nDo you want do download the update?",
							"Check Version", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
							new ImageIcon("Twist.png"), new String[] { "No", "Yes" }, "Yes");
				}

				if (auswahl == 1) {
					try {
						Desktop.getDesktop().browse(new URL(Main.versionNewURL).toURI());
					} catch (IOException | URISyntaxException e1) {
					}

				}
			} else {
				if (Main.language == 0) {
					JOptionPane.showOptionDialog(null,
							"Aktuell verwenden sie Version " + Main.version
									+ " des Programms.\n\nDas ist bereits die neuste verfügbare Version.",
							"Version prüfen", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
							new ImageIcon("Twist.png"), new String[] { "OK" }, "OK");
				} else {
					JOptionPane.showOptionDialog(null,
							"Currently you use version " + Main.version
									+ " of the program.\n\nThat is the latest available update.",
							"Check Version", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
							new ImageIcon("Twist.png"), new String[] { "OK" }, "OK");
				}
			}
		} else if (e.getSource() == UI.settingsBtnAbbrechen) {
			UI.settingsSettings.setVisible(false);
		} else if (e.getSource() == UI.settingsBtnSpeichern) {
			if (UI.settingsZahlAnzeigenJa.isSelected()) {
				Main.zahlAnzeigen = 1;
			}
			if (UI.settingsZahlAnzeigenNein.isSelected()) {
				Main.zahlAnzeigen = 0;
			}

			if (UI.settingsLanguageComboBox.getSelectedIndex() == 0) {
				Main.language = 0;
			} else {
				Main.language = 1;
			}

			Main.stdWortliste = UI.settingsStdWortlisteComboBox.getSelectedItem().toString();

			saveSettings();
			UI.settingsSettings.setVisible(false);
			if (Main.language == 0) {
				JOptionPane.showOptionDialog(null, "Bitte Programm neustarten um Änderungen zu übernehmen.", "Neustart",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Twist.png"),
						new String[] { "OK" }, "OK");
				
				System.exit(0);
			} else {
				JOptionPane.showOptionDialog(null, "Please restart the program.", "Restart",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Twist.png"),
						new String[] { "OK" }, "OK");
			}
		} else if (e.getSource() == UI.settingsAddWortListeBtn) {
			int returnVal = UI.dateiauswahl.showOpenDialog(UI.window);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				try {
					boolean dateiExistiert = true;
					int i = 0;
					while (dateiExistiert) {
						File file = new File("woerter" + i + ".txt");
						if (!file.exists()) {
							Main.copyFile(UI.dateiauswahl.getSelectedFile(), new File("woerter" + i + ".txt"));
							Main.verfuegbareWortlisten.add("woerter" + i + ".txt");
							dateiExistiert = false;
						}
						i++;
					}
				} catch (IOException e1) {
				}
			}
		}

		else if (e.getSource() == UI.textEntschluesseln) {
			Main.wortListeNachPermutation.clear();
			CustomThreadDecryptText threadTextEntschluesseln = new CustomThreadDecryptText(UI.text1.getText());
			threadTextEntschluesseln.start();
		} else if (e.getSource() == UI.textVerschluesseln) {
			CustomThreadEncryptText threadTextVerschluesseln = new CustomThreadEncryptText(UI.text2.getText());
			threadTextVerschluesseln.start();
		} else if (e.getSource() == UI.wortEntschluesseln) {
			Main.wortListeNachPermutation.clear();
			CustomThreadDecryptWort threadWortEntschluesseln = new CustomThreadDecryptWort(UI.wort1.getText());
			threadWortEntschluesseln.start();
		} else if (e.getSource() == UI.wortVerschluesseln) {
			CustomThreadEncryptWort threadWortVerschluesseln = new CustomThreadEncryptWort(UI.wort2.getText());
			threadWortVerschluesseln.start();
		} else {

		}
	}

	public static void saveSettings() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("settings.txt"));
			bw.write(Main.firstStart + "");
			bw.newLine();
			bw.write(Main.zahlAnzeigen + "");
			bw.newLine();
			bw.write(Main.language + "");
			bw.newLine();
			bw.write(Main.stdWortliste);
			bw.newLine();
			for (int i = 0; i < Main.verfuegbareWortlisten.size(); i++) {
				bw.write(Main.verfuegbareWortlisten.get(i));
				bw.newLine();
			}
			bw.close();
		} catch (IOException e1) {
		}
	}
}
