import java.awt.Font;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

public class UI implements ActionListener
{
	// Main Frame
	public static JFrame window = new JFrame("Twist");

	// Tabpane
	public static JTabbedPane tabpane = new JTabbedPane(JTabbedPane.BOTTOM, JTabbedPane.SCROLL_TAB_LAYOUT);

	// Panel
	public static JPanel textPanel = new JPanel();
	public static JPanel wortPanel = new JPanel();

	// MenuBar
	public static JMenuBar menu = new JMenuBar();
	public static JMenu menuDatei = new JMenu("Datei");
	public static JMenu menuHelp = new JMenu("Hilfe");
	public static JMenuItem dateiOpen = new JMenuItem("Öffnen");
	public static JMenuItem dateiSave = new JMenuItem("Speichern");
	public static JMenuItem dateiExit = new JMenuItem("Beenden");
	public static JMenuItem helpSettings = new JMenuItem("Einstellungen");
	public static JMenuItem helpUpdateProgram = new JMenuItem("Program aktualisieren");
	public static JMenuItem helpUpdateWordList = new JMenuItem("Wortliste aktualisieren");
	public static JMenuItem helpHelp = new JMenuItem("Hilfe");

	// Text
	public static JTextArea text1 = new JTextArea(8, 80);
	public static JTextArea text2 = new JTextArea(8, 80);
	public static JScrollPane text1ScrollPane = new JScrollPane();
	public static JScrollPane text2ScrollPane = new JScrollPane();
	public static JLabel text1Label = new JLabel("Verschlüsselten Text hier eingeben:");
	public static JLabel text2Label = new JLabel("Unverschlüsselten Text hier eingeben:");
	public static JButton textEntschluesseln = new JButton("Entschlüsseln");
	public static JButton textVerschluesseln = new JButton("Verschlüsseln");

	// Wort
	public static JTextArea wort1 = new JTextArea(8, 80);
	public static JTextArea wort2 = new JTextArea(8, 80);
	public static JScrollPane wort1ScrollPane = new JScrollPane();
	public static JScrollPane wort2ScrollPane = new JScrollPane();
	public static JLabel wort1Label = new JLabel("Verschlüsseltes Wort hier eingeben:");
	public static JLabel wort2Label = new JLabel("Unverschlüsseltes Wort hier eingeben:");
	public static JButton wortEntschluesseln = new JButton("Entschlüsseln");
	public static JButton wortVerschluesseln = new JButton("Verschlüsseln");

	// Dialoge
	public static JFileChooser dateiauswahl = new JFileChooser();
	public static JOptionPane optionDialog = new JOptionPane();
	public static JDialog helpDialog = new JDialog();
	public static JDialog progressDialog = new JDialog();
	public static JProgressBar progressBar = new JProgressBar();

	// Variablen
	public static JTextArea helpText = new JTextArea(
			"Mithilfe des Tabs 'Text' können Sie ganze Texte ver- oder entschlüsseln. Beim entschlüsseln wird, wenn für ein verschlüsseltes Wort mehrere mögliche Wörter passen würden, immer das erste Wort, das gefunden wird, ausgegeben. Die Zahl in Klammern dahinter zeigt, wieviele möglichen Wörter es gibt."
					+ "\n\nMithilfe des Tabs 'Wort' können Sie einzelne Wörter ver- oder entschlüsseln. Beim entschlüsseln werden im Gegensatz zu dem 'Text'-Tab alle Wörter ausgegeben. \n\nDas Programm kann Wörter bis zehn Buchstaben Länge entschlüsseln.");
	public static int settigsZahl = 1;
	public static int firstStart = 1;

	@SuppressWarnings("deprecation")
	public UI()
	{
		// Fenster konfigurieren
		window.setSize(850, 420);
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setIconImage(new ImageIcon("Twist.png").getImage());
		window.add(tabpane);
		window.setVisible(true);

		// Menü zusammensetzen
		window.setJMenuBar(menu);
		menu.add(menuDatei);
		dateiOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		dateiOpen.setIcon(new ImageIcon("img/icon_open.png"));
		dateiOpen.addActionListener(new ActionHandler());
		menuDatei.add(dateiOpen);
		dateiSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		dateiSave.setIcon(new ImageIcon("img/icon_save.png"));
		dateiSave.addActionListener(new ActionHandler());
		menuDatei.add(dateiSave);
		menuDatei.addSeparator();
		dateiExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
		dateiExit.setIcon(new ImageIcon("img/icon_exit.png"));
		dateiExit.addActionListener(new ActionHandler());
		menuDatei.add(dateiExit);

		menu.add(menuHelp);
		helpUpdateWordList.addActionListener(new ActionHandler());
		helpUpdateWordList.setIcon(new ImageIcon("img/icon_update.png"));
		menuHelp.add(helpUpdateWordList);
		helpUpdateProgram.setIcon(new ImageIcon("img/icon_update.png"));
		helpUpdateProgram.addActionListener(new ActionHandler());
		menuHelp.add(helpUpdateProgram);
		menuHelp.addSeparator();
		helpSettings.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		helpSettings.setIcon(new ImageIcon("img/icon_settings.png"));
		helpSettings.addActionListener(new ActionHandler());
		menuHelp.add(helpSettings);
		helpHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.CTRL_MASK));
		helpHelp.setIcon(new ImageIcon("img/icon_help.png"));
		helpHelp.addActionListener(new ActionHandler());
		menuHelp.add(helpHelp);

		// Text Panel
		text1.setLineWrap(true);
		text1.setWrapStyleWord(true);
		text1.setFont(new Font("Arial", Font.PLAIN, 13));
		textPanel.add(text1Label);
		text1ScrollPane.setViewportView(text1);
		textPanel.add(text1ScrollPane);
		text2.setLineWrap(true);
		text2.setWrapStyleWord(true);
		text2.setFont(new Font("Arial", Font.PLAIN, 13));
		textPanel.add(text2Label);
		text2ScrollPane.setViewportView(text2);
		textPanel.add(text2ScrollPane);
		textEntschluesseln.addActionListener(new ActionHandler());
		textPanel.add(textEntschluesseln);
		textVerschluesseln.addActionListener(new ActionHandler());
		textPanel.add(textVerschluesseln);
		tabpane.addTab("Text ", textPanel);

		// Wort Panel
		wort1.setLineWrap(true);
		wort1.setWrapStyleWord(true);
		wort1.setFont(new Font("Arial", Font.PLAIN, 13));
		wortPanel.add(wort1Label);
		wort1ScrollPane.setViewportView(wort1);
		wortPanel.add(wort1ScrollPane);
		wort2.setLineWrap(true);
		wort2.setWrapStyleWord(true);
		wort2.setFont(new Font("Arial", Font.PLAIN, 13));
		wortPanel.add(wort2Label);
		wort2ScrollPane.setViewportView(wort2);
		wortPanel.add(wort2ScrollPane);
		wortEntschluesseln.addActionListener(new ActionHandler());
		wortPanel.add(wortEntschluesseln);
		wortVerschluesseln.addActionListener(new ActionHandler());
		wortPanel.add(wortVerschluesseln);
		tabpane.addTab("Wort", wortPanel);

		if (firstStart == 1)
		{
			int auswahl = JOptionPane.showOptionDialog(null,
					"Mit diesem Programm können Sie Wörter bzw. Texte ver- oder entschlüsseln.\n\nUm Wörter zu ver- oder entschlüsseln wechseln Sie auf den Tab 'Wort', für Wörter wechseln Sie auf 'Wort'.",
					"Erster Start", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
					new ImageIcon("Twist.png"), new String[] { "OK" }, "OK");
			if (auswahl == 0)
			{
				firstStart = 0;
				try
				{
					BufferedWriter bw = new BufferedWriter(new FileWriter("settings.txt"));
					bw.write(firstStart + "");
					bw.newLine();
					bw.write(settigsZahl + "");
					bw.close();
				}
				catch (IOException e)
				{
				}
			}
		}
	}

	public void actionPerformed(ActionEvent e)
	{

	}
}
