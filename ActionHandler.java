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
		if (e.getSource() == UI.dateiOpen)
		{
			if (UI.tabpane.getSelectedIndex() == 0)
			{
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
						if (auswahl == 1)
						{
							while ((line = br.readLine()) != null)
							{
								s += line;
							}
							UI.text2.setText(s);
						}
						else
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
			else
			{
				auswahl = JOptionPane.showOptionDialog(null, Main.WoerterLanguage.get(41), Main.WoerterLanguage.get(42),
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Twist.png"),
						new String[] { Main.WoerterLanguage.get(43), Main.WoerterLanguage.get(44) },
						Main.WoerterLanguage.get(44));
				if (UI.dateiauswahl.showOpenDialog(UI.window) == JFileChooser.APPROVE_OPTION)
				{
					try
					{
						BufferedReader br = new BufferedReader(new FileReader(UI.dateiauswahl.getSelectedFile()));
						String line = "";
						String s = "";
						if (auswahl == 1)
						{
							while ((line = br.readLine()) != null)
							{
								s += line;
							}
							UI.wort2.setText(s);
						}
						else
						{
							while ((line = br.readLine()) != null)
							{
								s += line;
							}
							UI.wort1.setText(s);
						}
						br.close();
					}
					catch (IOException e1)
					{
					}
				}
			}
		}
		else if (e.getSource() == UI.dateiSave)
		{
			if (UI.tabpane.getSelectedIndex() == 0)
			{
				auswahl = JOptionPane.showOptionDialog(null, Main.WoerterLanguage.get(45), Main.WoerterLanguage.get(46),
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Twist.png"),
						new String[] { Main.WoerterLanguage.get(47), Main.WoerterLanguage.get(48) },
						Main.WoerterLanguage.get(48));
				if (UI.dateiauswahl.showOpenDialog(UI.window) == JFileChooser.APPROVE_OPTION)
				{
					try
					{
						BufferedWriter bw = new BufferedWriter(new FileWriter(UI.dateiauswahl.getSelectedFile()));
						if (auswahl == 1)
						{
							bw.write(UI.text2.getText());
						}
						else
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
			else
			{
				auswahl = JOptionPane.showOptionDialog(null, Main.WoerterLanguage.get(49), Main.WoerterLanguage.get(50),
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Twist.png"),
						new String[] { Main.WoerterLanguage.get(51), Main.WoerterLanguage.get(52) },
						Main.WoerterLanguage.get(52));
				if (UI.dateiauswahl.showOpenDialog(UI.window) == JFileChooser.APPROVE_OPTION)
				{
					try
					{
						BufferedWriter bw = new BufferedWriter(new FileWriter(UI.dateiauswahl.getSelectedFile()));
						if (auswahl == 1)
						{
							bw.write(UI.wort2.getText());
						}
						else
						{
							bw.write(UI.wort1.getText());
						}
						bw.close();
					}
					catch (IOException e1)
					{
					}
				}
			}
		}
		else if (e.getSource() == UI.dateiExit)
		{
			System.exit(0);
		}
		else if (e.getSource() == UI.helpKonsole)
		{
			if (UI.Log.isVisible())
			{
				UI.Log.setVisible(false);
				UI.helpKonsole.setText(Main.WoerterLanguage.get(6));
			}
			else
			{
				UI.Log.setVisible(true);
				UI.helpKonsole.setText(Main.WoerterLanguage.get(7));
			}
		}
		else if (e.getSource() == UI.helpSettings)
		{
			new Settings();
		}
		else if (e.getSource() == UI.helpHelp)
		{
			new Help();
		}
		else if (e.getSource() == UI.helpUpdateProgram)
		{
			try
			{
				Scanner scanner = new Scanner(new URL("http://192.168.178.24/version.txt").openStream());
				Main.versionNew = scanner.nextLine();
				Main.versionNewURL = scanner.nextLine();
				scanner.close();
			}
			catch (MalformedURLException e1)
			{
			}
			catch (IOException e1)
			{
			}
			if (!Main.version.equals(Main.versionNew))
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
						Desktop.getDesktop().browse(new URL(Main.versionNewURL).toURI());
					}
					catch (IOException | URISyntaxException e1)
					{
					}

				}
			}
			else
			{
				JOptionPane.showOptionDialog(null,
						Main.WoerterLanguage.get(59) + Main.version + Main.WoerterLanguage.get(60),
						Main.WoerterLanguage.get(61), JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
						new ImageIcon("Twist.png"), new String[] { Main.WoerterLanguage.get(62) },
						Main.WoerterLanguage.get(62));
			}
		}
		else if (e.getSource() == UI.settingsBtnAbbrechen)
		{
			UI.settingsSettings.setVisible(false);
		}
		else if (e.getSource() == UI.settingsBtnSpeichern)
		{
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

			Main.saveSettingsFile();
			UI.settingsSettings.setVisible(false);
			JOptionPane.showOptionDialog(UI.window, Main.WoerterLanguage.get(63), Main.WoerterLanguage.get(64),
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Twist.png"),
					new String[] { Main.WoerterLanguage.get(65) }, Main.WoerterLanguage.get(65));
			System.exit(0);
		}
		else if (e.getSource() == UI.settingsAddWortListeBtn)
		{
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
						if (!file.exists())
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
			UI.text2.setText("");
			String[] splited = UI.text1.getText().split("\\s+");
			threadTextEntschluesseln.clear();

			for (int i = 0; i < UI.wortlistenAuswahlMenu.size(); i++)
			{
				if (UI.wortlistenAuswahlMenu.get(i).isSelected())
				{
					file = "wordlist/woerter" + i + ".txt";
				}
			}

			for (int i = 0; i < splited.length; i++)
			{
				threadTextEntschluesseln.add(new CustomThreadDecryptWort(splited[i], file));
				threadTextEntschluesseln.get(i).start();
			}

			for (int i = 0; i < threadTextEntschluesseln.size(); i++)
			{
				try
				{
					threadTextEntschluesseln.get(i).join();
					if (Main.zahlAnzeigen == 1)
					{
						UI.text2.setText(UI.text2.getText() + " "
								+ threadTextEntschluesseln.get(i).getWortListeNachPermutation().get(0) + "("
								+ threadTextEntschluesseln.get(i).getWortListeNachPermutation().size() + ")");
					}
					else
					{
						UI.text2.setText(UI.text2.getText() + " "
								+ threadTextEntschluesseln.get(i).getWortListeNachPermutation().get(0));
					}
				}
				catch (InterruptedException e1)
				{
					e1.printStackTrace();
				}
			}
		}
		else if (e.getSource() == UI.textVerschluesseln)
		{
			UI.text1.setText("");
			String[] splited = UI.text2.getText().split("\\s+");
			threadTextVerschluesseln.clear();

			for (int i = 0; i < splited.length; i++)
			{
				threadTextVerschluesseln.add(new CustomThreadEncryptWort(splited[i]));
				threadTextVerschluesseln.get(i).start();
			}

			for (int i = 0; i < threadTextVerschluesseln.size(); i++)
			{
				try
				{
					threadTextVerschluesseln.get(i).join();
					UI.text1.setText(UI.text1.getText() + threadTextVerschluesseln.get(i).getWortNeu() + " ");
				}
				catch (InterruptedException e1)
				{
					e1.printStackTrace();
				}
			}
		}
		else if (e.getSource() == UI.wortEntschluesseln)
		{
			if (!UI.wort1.getText().contains(" "))
			{
				UI.wort2.setText("");
				wort = UI.wort1.getText();

				for (int i = 0; i < UI.wortlistenAuswahlMenu.size(); i++)
				{
					if (UI.wortlistenAuswahlMenu.get(i).isSelected())
					{
						file = "wordlist/woerter" + i + ".txt";
					}
				}

				CustomThreadDecryptWort threadWortEntschluesseln = new CustomThreadDecryptWort(wort, file);
				threadWortEntschluesseln.start();

				try
				{
					threadWortEntschluesseln.join();
				}
				catch (InterruptedException e1)
				{
					e1.printStackTrace();
				}

				for (int i = 0; i < threadWortEntschluesseln.getWortListeNachPermutation().size(); i++)
				{
					UI.wort2.setText(
							UI.wort2.getText() + threadWortEntschluesseln.getWortListeNachPermutation().get(i) + "\n");
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
			if (!UI.wort2.getText().contains(" "))
			{
				UI.wort1.setText("");
				wort = UI.wort2.getText();

				CustomThreadEncryptWort threadWortEntschluesseln = new CustomThreadEncryptWort(wort);
				threadWortEntschluesseln.start();

				try
				{
					threadWortEntschluesseln.join();
				}
				catch (InterruptedException e1)
				{
					e1.printStackTrace();
				}
				String wortNeu= threadWortEntschluesseln.getWortNeu();
				UI.wort1.setText(wortNeu);
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
