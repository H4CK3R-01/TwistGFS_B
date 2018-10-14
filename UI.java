import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

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
	public static JMenu menuDatei;
	public static JMenu menuHelp;
	public static JMenuItem dateiOpen;
	public static JMenuItem dateiSave;
	public static JMenuItem dateiExit;
	public static JMenu helpSwitchWordList;
	public static ButtonGroup wortlisteRadioButtons = new ButtonGroup();
	public static JMenuItem helpUpdateProgram;
	public static JMenuItem helpKonsole;
	public static JMenuItem helpSettings;
	public static JMenuItem helpHelp;
	public static ImageIcon save = new ImageIcon("img/icon_save.png");
	public static ImageIcon open = new ImageIcon("img/icon_open.png");
	public static ImageIcon exit = new ImageIcon("img/icon_exit.png");
	public static ImageIcon updates = new ImageIcon("img/icon_updates.png");
	public static ImageIcon settings = new ImageIcon("img/icon_settings.png");
	public static ImageIcon help = new ImageIcon("img/icon_help.png");

	// Text Panel
	public static JTextArea text1 = new JTextArea(8, 80);
	public static JTextArea text2 = new JTextArea(8, 80);
	public static JScrollPane text1ScrollPane = new JScrollPane();
	public static JScrollPane text2ScrollPane = new JScrollPane();
	public static JLabel text1Label;
	public static JLabel text2Label;
	public static JButton textEntschluesseln;
	public static JButton textVerschluesseln;

	// Wort Panel
	public static JTextArea wort1 = new JTextArea(8, 80);
	public static JTextArea wort2 = new JTextArea(8, 80);
	public static JScrollPane wort1ScrollPane = new JScrollPane();
	public static JScrollPane wort2ScrollPane = new JScrollPane();
	public static JLabel wort1Label;
	public static JLabel wort2Label;
	public static JButton wortEntschluesseln;
	public static JButton wortVerschluesseln;

	// Dialoge
	public static JFileChooser dateiauswahl = new JFileChooser();
	public static JOptionPane optionDialog = new JOptionPane();
	public static JDialog helpDialog = new JDialog();

	// Einstellungen
	public static JPanel settingsContentPane = new JPanel();
	public static JFrame settingsSettings;
	public static GridBagLayout settingsGridBagLayout = new GridBagLayout();
	public static JLabel settingsZahlAnzeigenText;
	public static JPanel settingsPanel = new JPanel();
	public static GridBagLayout settingsgbl_panel = new GridBagLayout();
	public static JRadioButton settingsZahlAnzeigenJa;
	public static GridBagConstraints settingsGbc_zahlAnzeigenJa = new GridBagConstraints();
	public static JRadioButton settingsZahlAnzeigenNein;
	public static GridBagConstraints settingsGbc_zahlAnzeigenNein = new GridBagConstraints();
	public static ButtonGroup settingsZahlAnzeigen = new ButtonGroup();
	public static JLabel settingsLanguageChangeText;
	public static GridBagConstraints settingsGbc_languageChangeText = new GridBagConstraints();
	public static JComboBox<String> settingsLanguageComboBox = new JComboBox<String>();
	public static GridBagConstraints settingsGbc_languageComboBox = new GridBagConstraints();
	public static JLabel settingsAddWortListeText;
	public static GridBagConstraints settingsGbc_addWortListeText = new GridBagConstraints();
	public static JButton settingsAddWortListeBtn;
	public static GridBagConstraints settingsGbc_addWortListeBtn = new GridBagConstraints();
	public static JPanel settingsOptions = new JPanel();
	public static GridBagConstraints settingsGbc_stdWortlisteText = new GridBagConstraints();
	public static JLabel settingsStdWortlisteText;
	public static JComboBox<String> settingsStdWortlisteComboBox = new JComboBox<String>();
	public static GridBagConstraints settingsGbc_stdWortlisteComboBox = new GridBagConstraints();
	public static GridBagLayout settingsGbl_options = new GridBagLayout();
	public static GridBagConstraints settingsGbc_options = new GridBagConstraints();
	public static JSeparator settingsSeparator = new JSeparator();
	public static GridBagConstraints settingsGbc_separator = new GridBagConstraints();
	public static JButton settingsBtnSpeichern;
	public static GridBagConstraints settingsGbc_btnSpeichern = new GridBagConstraints();
	public static JButton settingsBtnAbbrechen;
	public static GridBagConstraints settingsGbc_btnAbbrechen = new GridBagConstraints();

	// Console
	public static JFrame konsole = new JFrame();
	public static JTextArea konsoleArea = new JTextArea();
	public static GridBagConstraints constraints = new GridBagConstraints();

	// Andere UI-Elemente
	public static JTextArea helpText;
	public static FileNameExtensionFilter filter;
	public static ArrayList<JRadioButtonMenuItem> wortlistenAuswahlMenu = new ArrayList<>();

	@SuppressWarnings("deprecation")
	public UI()
	{
		Main.readSettingsFile(); // Einstellungen einlesen
		Main.readLanguageFile("lang/de.txt"); // Sprachdateien einlesen

		// Icons von Menü festlegen
		save = new ImageIcon(save.getImage().getScaledInstance(16, 16, java.awt.Image.SCALE_DEFAULT));
		open = new ImageIcon(open.getImage().getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH));
		exit = new ImageIcon(exit.getImage().getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH));
		updates = new ImageIcon(updates.getImage().getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH));
		settings = new ImageIcon(settings.getImage().getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH));
		help = new ImageIcon(help.getImage().getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH));

		// Menü
		menuDatei = new JMenu(Main.WoerterLanguage.get(0)); // Datei
		menuHelp = new JMenu(Main.WoerterLanguage.get(1)); // Hilfe

		dateiOpen = new JMenuItem(Main.WoerterLanguage.get(2)); // Öffnen
		dateiSave = new JMenuItem(Main.WoerterLanguage.get(3)); // Speichern
		dateiExit = new JMenuItem(Main.WoerterLanguage.get(4)); // Beenden

		helpSwitchWordList = new JMenu(Main.WoerterLanguage.get(5)); // Wortliste auswählen
		helpKonsole = new JMenuItem(Main.WoerterLanguage.get(6)); // Konsole öffnen/schließen
		helpUpdateProgram = new JMenuItem(Main.WoerterLanguage.get(8)); // Program aktualisieren
		helpSettings = new JMenuItem(Main.WoerterLanguage.get(9)); // Einstellungen
		helpHelp = new JMenuItem(Main.WoerterLanguage.get(10)); // Hilfe

		// Tabs
		tabpane.addTab(Main.WoerterLanguage.get(11), wortPanel); // Wort
		tabpane.addTab(Main.WoerterLanguage.get(12), textPanel); // Text
		text1Label = new JLabel(Main.WoerterLanguage.get(13)); // Verschlüsselten Text hier eingeben
		text2Label = new JLabel(Main.WoerterLanguage.get(14)); // Unverschlüsselten Text hier eingeben
		textEntschluesseln = new JButton(Main.WoerterLanguage.get(15)); // Entschlüsseln
		textVerschluesseln = new JButton(Main.WoerterLanguage.get(16)); // Verschlüsseln
		wort1Label = new JLabel(Main.WoerterLanguage.get(17)); // Verschlüsseltes Wort hier eingeben
		wort2Label = new JLabel(Main.WoerterLanguage.get(18)); // Unverschlüsseltes Wort hier eingeben
		wortEntschluesseln = new JButton(Main.WoerterLanguage.get(19)); // Entschlüsseln
		wortVerschluesseln = new JButton(Main.WoerterLanguage.get(20)); // Verschlüsseln

		// Settings
		settingsSettings = new JFrame(Main.WoerterLanguage.get(21)); // Einstellungen
		settingsZahlAnzeigenText = new JLabel(Main.WoerterLanguage.get(22)); // Soll die Zahl hinter den Wörtern bei 'Text' stehen
		settingsZahlAnzeigenJa = new JRadioButton(Main.WoerterLanguage.get(23)); // Ja
		settingsZahlAnzeigenNein = new JRadioButton(Main.WoerterLanguage.get(24)); // Nein
		settingsLanguageChangeText = new JLabel(Main.WoerterLanguage.get(25)); // Sprache auswählen
		settingsAddWortListeText = new JLabel(Main.WoerterLanguage.get(26)); // Neue Wortliste zum Programm hinzufügen
		settingsAddWortListeBtn = new JButton(Main.WoerterLanguage.get(27)); // Wortliste auswählen
		settingsStdWortlisteText = new JLabel(Main.WoerterLanguage.get(28)); // Standard Wortliste auswählen
		settingsBtnSpeichern = new JButton(Main.WoerterLanguage.get(29)); // Speichern
		settingsBtnAbbrechen = new JButton(Main.WoerterLanguage.get(30)); // Abbrechen

		// Sonstige
		helpText = new JTextArea(Main.WoerterLanguage.get(31)); // Text der im Hilfe-Fenster angezeigt wird
		helpDialog.setTitle(Main.WoerterLanguage.get(32)); // Hilfe
		dateiauswahl.setDialogTitle(Main.WoerterLanguage.get(33)); // Wortliste auswählen
		filter = new FileNameExtensionFilter(Main.WoerterLanguage.get(34), "txt", "text"); // Nur Text Dateien
		settingsLanguageComboBox.addItem(Main.WoerterLanguage.get(35)); // Deutsch
		settingsLanguageComboBox.addItem("Bald auch Englische Übersetzung");
		//settingsLanguageComboBox.addItem(Main.WoerterLanguage.get(36)); // Englisch
		settingsLanguageComboBox.setSelectedIndex(Main.language);

		// Fenster konfigurieren
		window.setSize(850, 420); // Fenster-Größe festlegen
		window.setResizable(false); // Größe des Fensters kann nich geändert werden
		window.setLocationRelativeTo(null); // Position des Fensters wird festgelegt (Mitte)
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setIconImage(new ImageIcon("img/Twist.png").getImage()); // Icon des Fensters festlegen
		window.add(tabpane); // Tabpane zum Fenster hinzufügen
		window.setVisible(true); // Fenster ist sichtbar

		// Menü zusammensetzen
		window.setJMenuBar(menu); // Menu zum Fenster hinzufügen
		dateiOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK)); // Tastenkombination für 'Öffnen'
		dateiOpen.setIcon(open); // Icon für 'Öffnen'
		dateiOpen.addActionListener(new ActionHandler()); // ActionHandler für 'Öffnen'
		dateiSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK)); // Tastenkombination für 'Speichern'
		dateiSave.setIcon(save); // Icon für 'Speichern'
		dateiSave.addActionListener(new ActionHandler()); // ActionHandler für 'Speichern'
		dateiExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK)); // Tastenkombination für 'Beenden'
		dateiExit.setIcon(exit); // Icon für 'Beenden'
		dateiExit.addActionListener(new ActionHandler()); // ActionHandler für 'Beenden'
		menuDatei.add(dateiOpen); // 'Öffnen' zum Datei-Menü hinzufügen
		menuDatei.add(dateiSave); // 'Speichern' zum Datei-Menü hinzufügen
		menuDatei.addSeparator(); // Trennstrich zum Datei-Menü hinzufügen
		menuDatei.add(dateiExit); // 'Beenden' zum Datei-Menü hinzufügen

		for (int i = 0; i < Main.verfuegbareWortlisten.size(); i++)
		{
			wortlistenAuswahlMenu.add(new JRadioButtonMenuItem(Main.verfuegbareWortlisten.get(i))); // Radiobuttons für Wortlistenauswahl erstellen
		}
		for (int i = 0; i < Main.verfuegbareWortlisten.size(); i++)
		{
			if (Main.verfuegbareWortlisten.get(i).contains(Main.stdWortliste))
			{
				wortlistenAuswahlMenu.get(i).setSelected(true);
			}
			helpSwitchWordList.add(wortlistenAuswahlMenu.get(i)); // Radiobuttons zum 'Wortliste auswählen'-Menü hinzufügen
			wortlisteRadioButtons.add(wortlistenAuswahlMenu.get(i));
			settingsStdWortlisteComboBox.addItem(Main.verfuegbareWortlisten.get(i)); // Wortlisten zum Auswahlmenü in den Einstellungen hinzufügen
		}

		settingsStdWortlisteComboBox.setSelectedItem(Main.stdWortliste); // Standardmäßig ausgewählte Wortliste auswählen (aus Datei ausgelesen)
		helpKonsole.addActionListener(new ActionHandler()); // ActionHandler für 'Konsole'
		helpKonsole.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_MASK)); // Tastenkombination für 'Konsole'
		helpUpdateProgram.setIcon(updates); // Icon für 'Updates'
		helpUpdateProgram.addActionListener(new ActionHandler()); // ActionHandler für 'Updates'
		helpSettings.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK)); // Tastenkombination für 'Einstellungen'
		helpSettings.setIcon(settings); // Icon für 'Settings'
		helpSettings.addActionListener(new ActionHandler()); // ActionHandler für 'Settings'
		helpHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.CTRL_MASK)); // Tastenkombination für 'Hilfe'
		helpHelp.setIcon(help); // Icon für 'Hilfe'
		helpHelp.addActionListener(new ActionHandler()); // ActionHandler für 'Hilfe'
		menuHelp.add(helpSwitchWordList); // 'Wortliste auswählen' zum Datei-Menü hinzufügen
		menuHelp.add(helpKonsole); // 'Konsole' zum Datei-Menü hinzufügen
		menuHelp.add(helpUpdateProgram); // 'Update' zum Datei-Menü hinzufügen
		menuHelp.addSeparator(); // Trennstrich zum Datei-Menü hinzufügen
		menuHelp.add(helpSettings); // 'Einstellungen' zum Datei-Menü hinzufügen
		menuHelp.add(helpHelp); // 'Hilfe' zum Datei-Menü hinzufügen

		menu.add(menuDatei); // Datei-Menü zum Menü hinzufügen
		menu.add(menuHelp); // Hilfe-Menü zum Menü hinzufügen

		// Text Panel konfigurieren
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

		// Wort Panel konfigurieren
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

		// Prüfen ob das Programm das erste mal gestartet wurde
		if (Main.firstStart == 1)
		{
			int auswahl = JOptionPane.showOptionDialog(null,
					"Mit diesem Programm können Sie Wörter bzw. Texte ver- oder entschlüsseln.\n\nUm Wörter zu ver- oder entschlüsseln wechseln Sie auf den Tab 'Wort', für Wörter wechseln Sie auf 'Wort'.",
					"Erster Start", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
					new ImageIcon("Twist.png"), new String[] { "OK" }, "OK");
			if (auswahl == 0)
			{
				Main.firstStart = 0;
				Main.saveSettingsFile();
			}
		}

		dateiauswahl.setCurrentDirectory(new java.io.File("."));
		dateiauswahl.setAcceptAllFileFilterUsed(false);
		dateiauswahl.setFileFilter(filter); // Dateifilter festlegen

		// Wortliste auswählen
		String file = "";
		for (int i = 0; i < UI.wortlistenAuswahlMenu.size(); i++)
		{
			if (UI.wortlistenAuswahlMenu.get(i).isSelected())
			{
				file = "wordlist/woerter" + i + ".txt";
			}
		}
		// Wortliste einlesen
		Main.wordList = Main.readWordListFile(file);
	}

	public void actionPerformed(ActionEvent e)
	{

	}
}
