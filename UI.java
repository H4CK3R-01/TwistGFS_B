import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author Florian
 * @version 1.6
 */
public class UI implements ActionListener {
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
	public static JMenuItem helpSettings;
	public static JMenuItem helpHelp;

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

	// Andere UI-Elemente
	public static JTextArea helpText;
	public static FileNameExtensionFilter filter;
	public static ArrayList<JRadioButtonMenuItem> wortlistenAuswahlMenu = new ArrayList<>();

	@SuppressWarnings("deprecation")
	public UI() {
		// Sprache einstellen
		if (Main.language == 0) { // Deutsch
			// Menü
			menuDatei = new JMenu("Datei");
			menuHelp = new JMenu("Hilfe");
			dateiOpen = new JMenuItem("Öffnen");
			dateiSave = new JMenuItem("Speichern");
			dateiExit = new JMenuItem("Beenden");
			helpSwitchWordList = new JMenu("Wortliste auswählen");
			helpUpdateProgram = new JMenuItem("Program aktualisieren");
			helpSettings = new JMenuItem("Einstellungen");
			helpHelp = new JMenuItem("Hilfe");

			// Tabs
			tabpane.addTab("Wort", wortPanel);
			tabpane.addTab("Text ", textPanel);
			text1Label = new JLabel("Verschlüsselten Text hier eingeben:");
			text2Label = new JLabel("Unverschlüsselten Text hier eingeben:");
			textEntschluesseln = new JButton("Entschlüsseln");
			textVerschluesseln = new JButton("Verschlüsseln");
			wort1Label = new JLabel("Verschlüsseltes Wort hier eingeben:");
			wort2Label = new JLabel("Unverschlüsseltes Wort hier eingeben:");
			wortEntschluesseln = new JButton("Entschlüsseln");
			wortVerschluesseln = new JButton("Verschlüsseln");

			// Settings
			settingsSettings = new JFrame("Einstellungen");
			settingsZahlAnzeigenText = new JLabel("Soll die Zahl hinter den Wörtern bei 'Text' stehen?");
			settingsZahlAnzeigenJa = new JRadioButton("Ja");
			settingsZahlAnzeigenNein = new JRadioButton("Nein");
			settingsLanguageChangeText = new JLabel("Sprache auswählen");
			settingsAddWortListeText = new JLabel("Neue Wortliste zum Programm hinzufügen");
			settingsAddWortListeBtn = new JButton("Wortliste auswählen");
			settingsStdWortlisteText = new JLabel("Standard Wortliste auswählen");
			settingsBtnSpeichern = new JButton("Speichern");
			settingsBtnAbbrechen = new JButton("Abbrechen");

			// Sonstige
			helpText = new JTextArea(
					"Mithilfe des Tabs 'Text' können Sie ganze Texte ver- oder entschlüsseln. Beim entschlüsseln wird, wenn für ein verschlüsseltes Wort mehrere mögliche Wörter passen würden, immer das erste Wort, das gefunden wird, ausgegeben. Die Zahl in Klammern dahinter zeigt, wieviele möglichen Wörter es gibt."
							+ "\n\nMithilfe des Tabs 'Wort' können Sie einzelne Wörter ver- oder entschlüsseln. Beim entschlüsseln werden im Gegensatz zu dem 'Text'-Tab alle Wörter ausgegeben. \n\nDas Programm kann Wörter bis zehn Buchstaben Länge entschlüsseln.\n\nWenn kein Wort gefunden wurde können sie unter Hilfe > Wortliste auswählen eine andere Worliste auswählen.");
			helpDialog.setTitle("Hilfe");
			dateiauswahl.setDialogTitle("Wortliste auswählen");
			filter = new FileNameExtensionFilter("Nur Text Dateien", "txt", "text");
			settingsLanguageComboBox.addItem("Deutsch   ");
			settingsLanguageComboBox.addItem("Englisch  ");
			settingsLanguageComboBox.setSelectedIndex(Main.language);

		} else { // Englisch
			// Menu
			menuDatei = new JMenu("File");
			menuHelp = new JMenu("Help");
			dateiOpen = new JMenuItem("Open");
			dateiSave = new JMenuItem("Save");
			dateiExit = new JMenuItem("Exit");
			helpSwitchWordList = new JMenu("Choose word list");
			helpUpdateProgram = new JMenuItem("Update Program");
			helpSettings = new JMenuItem("Settings");
			helpHelp = new JMenuItem("Help");

			// Tabs
			tabpane.addTab("Word", wortPanel);
			tabpane.addTab("Text ", textPanel);
			text1Label = new JLabel("Enter encrypted text here:");
			text2Label = new JLabel("Enter decrypted text here:");
			textEntschluesseln = new JButton("Decode");
			textVerschluesseln = new JButton("Encode");
			wort1Label = new JLabel("Enter encrypted word here:");
			wort2Label = new JLabel("Enter decrypted word here:");
			wortEntschluesseln = new JButton("Decode");
			wortVerschluesseln = new JButton("Encode");

			// Settings
			settingsSettings = new JFrame("Settings");
			settingsZahlAnzeigenText = new JLabel("Should the number stand behind the words at 'Text'?");
			settingsZahlAnzeigenJa = new JRadioButton("Yes");
			settingsZahlAnzeigenNein = new JRadioButton("No");
			settingsLanguageChangeText = new JLabel("Choose language");
			settingsAddWortListeText = new JLabel("Add new word list");
			settingsAddWortListeBtn = new JButton("Choose own word list");
			settingsStdWortlisteText = new JLabel("Choose default word list");
			settingsBtnSpeichern = new JButton("Save");
			settingsBtnAbbrechen = new JButton("Abort");

			// Sonstige
			helpText = new JTextArea(
					"Use the tab 'Text' to enrypt or decrypt texts. When there are many possible words, the first word which eas found will printed in the output field. The number behind the wort shows how many possibilities are found. You can turn off the number in the settings.\n\nTo decrypt or encrypt words, you can go to the tab 'Words'. Every possible word is printed out in the output field. Sometimes no word is found, but then you can switch the wordlist and maybe then you can find a word.");
			helpDialog.setTitle("Help");
			dateiauswahl.setDialogTitle("Choose word list");
			filter = new FileNameExtensionFilter("Only Text-Files", "txt", "text");
			settingsLanguageComboBox.addItem("German    ");
			settingsLanguageComboBox.addItem("English   ");
			settingsLanguageComboBox.setSelectedIndex(Main.language);
		}

		// Fenster konfigurieren
		window.setSize(850, 420);
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setIconImage(new ImageIcon("img/Twist.png").getImage());
		window.add(tabpane);
		window.setVisible(true);

		// Menü zusammensetzen
		window.setJMenuBar(menu);
		dateiOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		dateiOpen.setIcon(new ImageIcon("img/icon_open.png"));
		dateiOpen.addActionListener(new ActionHandler());
		dateiSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		dateiSave.setIcon(new ImageIcon("img/icon_save.png"));
		dateiSave.addActionListener(new ActionHandler());
		dateiExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
		dateiExit.setIcon(new ImageIcon("img/icon_exit.png"));
		dateiExit.addActionListener(new ActionHandler());
		menuDatei.add(dateiOpen);
		menuDatei.add(dateiSave);
		menuDatei.addSeparator();
		menuDatei.add(dateiExit);

		for (int i = 0; i < Main.verfuegbareWortlisten.size(); i++) {
			wortlistenAuswahlMenu.add(new JRadioButtonMenuItem(Main.verfuegbareWortlisten.get(i)));
		}
		for (int i = 0; i < Main.verfuegbareWortlisten.size(); i++) {
			if (Main.verfuegbareWortlisten.get(i).contains(Main.stdWortliste)) {
				wortlistenAuswahlMenu.get(i).setSelected(true);
			}
			helpSwitchWordList.add(wortlistenAuswahlMenu.get(i));
			wortlisteRadioButtons.add(wortlistenAuswahlMenu.get(i));
			settingsStdWortlisteComboBox.addItem(Main.verfuegbareWortlisten.get(i));
		}

		settingsStdWortlisteComboBox.setSelectedItem(Main.stdWortliste);
		helpUpdateProgram.setIcon(new ImageIcon("img/icon_update.png"));
		helpUpdateProgram.addActionListener(new ActionHandler());
		helpSettings.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		helpSettings.setIcon(new ImageIcon("img/icon_settings.png"));
		helpSettings.addActionListener(new ActionHandler());
		helpHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.CTRL_MASK));
		helpHelp.setIcon(new ImageIcon("img/icon_help.png"));
		helpHelp.addActionListener(new ActionHandler());
		menuHelp.add(helpSwitchWordList);
		menuHelp.add(helpUpdateProgram);
		menuHelp.addSeparator();
		menuHelp.add(helpSettings);
		menuHelp.add(helpHelp);

		menu.add(menuDatei);
		menu.add(menuHelp);

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

		// Prüfen ob das Programm das erste mal gestartet wurde
		if (Main.firstStart == 1) {
			int auswahl = JOptionPane.showOptionDialog(null,
					"Mit diesem Programm können Sie Wörter bzw. Texte ver- oder entschlüsseln.\n\nUm Wörter zu ver- oder entschlüsseln wechseln Sie auf den Tab 'Wort', für Wörter wechseln Sie auf 'Wort'.",
					"Erster Start", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
					new ImageIcon("Twist.png"), new String[] { "OK" }, "OK");
			if (auswahl == 0) {
				Main.firstStart = 0;
				ActionHandler.saveSettings();
			}
		}

		dateiauswahl.setCurrentDirectory(new java.io.File("."));
		dateiauswahl.setAcceptAllFileFilterUsed(false);
		dateiauswahl.setFileFilter(filter);
	}

	public void actionPerformed(ActionEvent e) {

	}
}
