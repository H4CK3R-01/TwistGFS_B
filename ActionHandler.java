import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

public class ActionHandler implements ActionListener
{
	public void actionPerformed(ActionEvent e)
	{
		DecryptTwist twist = new DecryptTwist();
		ArrayList<String> textWoerter = new ArrayList<>();
		ArrayList<ArrayList<String>> wortWoerter = new ArrayList<>();

		if (e.getSource() == UI.dateiOpen)
		{
			if (UI.tabpane.getSelectedIndex() == 0)
			{
				int auswahl = JOptionPane.showOptionDialog(null,
						"Wollen Sie einen verschlüsselten Text oder einen entschlüsselten Text öffnen?", "Öffnen",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Twist.png"),
						new String[] { "Verschlüsselt", "Entschlüsselt" }, "Entschlüsselt");
				int returnVal = UI.dateiauswahl.showOpenDialog(UI.window);
				if (returnVal == JFileChooser.APPROVE_OPTION)
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
				int auswahl = JOptionPane.showOptionDialog(null,
						"Wollen Sie eine verschlüsseltes Wort oder ein entschlüsseltes Wort öffnen?", "Öffnen",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Twist.png"),
						new String[] { "Verschlüsselt", "Entschlüsselt" }, "Entschlüsselt");
				int returnVal = UI.dateiauswahl.showOpenDialog(UI.window);
				if (returnVal == JFileChooser.APPROVE_OPTION)
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
				int auswahl = JOptionPane.showOptionDialog(null,
						"Wollen Sie den verschlüsselten Text oder den entschlüsselten Text speichern?", "Speichern",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Twist.png"),
						new String[] { "Verschlüsselt", "Entschlüsselt" }, "Entschlüsselt");
				int returnVal = UI.dateiauswahl.showOpenDialog(UI.window);
				if (returnVal == JFileChooser.APPROVE_OPTION)
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
				int auswahl = JOptionPane.showOptionDialog(null,
						"Wollen Sie das verschlüsselte Wort oder das/die entschlüsselte/n Wort/e speichern?",
						"Speichern", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
						new ImageIcon("Twist.png"), new String[] { "Verschlüsselt", "Entschlüsselt" }, "Entschlüsselt");
				int returnVal = UI.dateiauswahl.showOpenDialog(UI.window);
				if (returnVal == JFileChooser.APPROVE_OPTION)
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
		else if (e.getSource() == UI.helpSettings)
		{
			int auswahl = JOptionPane.showOptionDialog(null, "Zahl in der Klammer deaktivieren?", "Einstellungen",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Twist.png"),
					new String[] { "Deaktivieren", "Aktivieren", "Abbrechen" }, "Abbrechen");
			if (auswahl == 1)
			{
				UI.settigsZahl = true;
			}
			else if (auswahl == 0)
			{
				UI.settigsZahl = false;
			}
		}
		else if (e.getSource() == UI.helpHelp)
		{
			UI.dialog.setTitle("Hilfe");
			UI.dialog.setSize(300, 250);
			UI.dialog.setResizable(false);
			UI.helpText.setLineWrap(true);
			UI.helpText.setWrapStyleWord(true);
			UI.helpText.setFont(new Font("Arial", Font.PLAIN, 13));
			UI.helpText.setEditable(false);
			UI.dialog.add(UI.helpText);
			UI.dialog.setModal(true);
			UI.dialog.setVisible(true);

		}
		else if (e.getSource() == UI.helpVersion)
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
				int auswahl = JOptionPane.showOptionDialog(null,
						"Aktuell verwenden sie Version " + Main.version + " des Programms.\n\nDie neuste Version ist "
								+ Main.versionNew + "\n\nWollen Sie die neue Version herunterladen?",
						"Version prüfen", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
						new ImageIcon("Twist.png"), new String[] { "Nein", "Ja" }, "Ja");
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
						"Aktuell verwenden sie Version " + Main.version
								+ " des Programms.\n\nDas ist die neuste verfügbare Version:",
						"Version prüfen", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
						new ImageIcon("Twist.png"), new String[] { "OK" }, "OK");
			}
		}
		else if (e.getSource() == UI.textEntschluesseln) // Text Entschlüsseln
		{
			String[] splited = UI.text1.getText().split("\\s+");

			UI.text2.setText("");
			for (int i = 0; i < splited.length; i++)
			{
				if (UI.settigsZahl)
				{
					textWoerter.add(twist.decrypt(splited[i]).get(0) + "(" + twist.decrypt(splited[i]).size() + ")");
				}
				else
				{
					textWoerter.add(twist.decrypt(splited[i]).get(0));
				}
			}

			for (int i = 0; i < textWoerter.size(); i++)
			{
				UI.text2.setText(UI.text2.getText() + textWoerter.get(i) + " ");
			}
		}
		else if (e.getSource() == UI.textVerschluesseln)
		{
			System.out.println("Verschlüsseln Text"); 
		}
		else if (e.getSource() == UI.wortEntschluesseln) // Wort Entschlüsseln
		{
			UI.wort2.setText("");

			wortWoerter.clear();
			wortWoerter.add(twist.decrypt(UI.wort1.getText()));
			for (int i = 0; i < wortWoerter.get(0).size(); i++)
			{
				UI.wort2.setText(UI.wort2.getText() + wortWoerter.get(0).get(i) + "\n");
			}
		}
		else if (e.getSource() == UI.wortVerschluesseln) 
		{
			twist.permute(UI.wort2.getText(), 1, UI.wort2.getText().length() - 4);
			for (int i = 0; i < twist.getMoeglicheWoerter().size(); i++)
			{
				System.out.println(twist.getMoeglicheWoerter().get(i));
			}

		}
		else
		{

		}
	}
}
