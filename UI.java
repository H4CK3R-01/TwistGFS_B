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

	// Log
	public static JFrame Log = new JFrame();
	public static JTextArea logArea = new JTextArea();
	public static GridBagConstraints constraints = new GridBagConstraints();

	// Andere UI-Elemente
	public static JTextArea helpText;
	public static FileNameExtensionFilter filter;
	public static ArrayList<JRadioButtonMenuItem> wortlistenAuswahlMenu = new ArrayList<>();

	@SuppressWarnings("deprecation")
	public UI()
	{
		save = new ImageIcon(save.getImage().getScaledInstance(16, 16, java.awt.Image.SCALE_DEFAULT));
		open = new ImageIcon(open.getImage().getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH));
		exit = new ImageIcon(exit.getImage().getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH));
		updates = new ImageIcon(updates.getImage().getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH));
		settings = new ImageIcon(settings.getImage().getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH));
		help = new ImageIcon(help.getImage().getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH));

		// Menü
		menuDatei = new JMenu(Main.WoerterLanguage.get(0));
		menuHelp = new JMenu(Main.WoerterLanguage.get(1));
		dateiOpen = new JMenuItem(Main.WoerterLanguage.get(2));
		dateiSave = new JMenuItem(Main.WoerterLanguage.get(3));
		dateiExit = new JMenuItem(Main.WoerterLanguage.get(4));
		helpSwitchWordList = new JMenu(Main.WoerterLanguage.get(5));
		helpKonsole = new JMenuItem(Main.WoerterLanguage.get(6));
		helpUpdateProgram = new JMenuItem(Main.WoerterLanguage.get(8));
		helpSettings = new JMenuItem(Main.WoerterLanguage.get(9));
		helpHelp = new JMenuItem(Main.WoerterLanguage.get(10));

		// Tabs
		tabpane.addTab(Main.WoerterLanguage.get(11), wortPanel);
		tabpane.addTab(Main.WoerterLanguage.get(12), textPanel);
		text1Label = new JLabel(Main.WoerterLanguage.get(13));
		text2Label = new JLabel(Main.WoerterLanguage.get(14));
		textEntschluesseln = new JButton(Main.WoerterLanguage.get(15));
		textVerschluesseln = new JButton(Main.WoerterLanguage.get(16));
		wort1Label = new JLabel(Main.WoerterLanguage.get(17));
		wort2Label = new JLabel(Main.WoerterLanguage.get(18));
		wortEntschluesseln = new JButton(Main.WoerterLanguage.get(19));
		wortVerschluesseln = new JButton(Main.WoerterLanguage.get(20));

		// Settings
		settingsSettings = new JFrame(Main.WoerterLanguage.get(21));
		settingsZahlAnzeigenText = new JLabel(Main.WoerterLanguage.get(22));
		settingsZahlAnzeigenJa = new JRadioButton(Main.WoerterLanguage.get(23));
		settingsZahlAnzeigenNein = new JRadioButton(Main.WoerterLanguage.get(24));
		settingsLanguageChangeText = new JLabel(Main.WoerterLanguage.get(25));
		settingsAddWortListeText = new JLabel(Main.WoerterLanguage.get(26));
		settingsAddWortListeBtn = new JButton(Main.WoerterLanguage.get(27));
		settingsStdWortlisteText = new JLabel(Main.WoerterLanguage.get(28));
		settingsBtnSpeichern = new JButton(Main.WoerterLanguage.get(29));
		settingsBtnAbbrechen = new JButton(Main.WoerterLanguage.get(30));

		// Sonstige
		helpText = new JTextArea(Main.WoerterLanguage.get(31));
		helpDialog.setTitle(Main.WoerterLanguage.get(32));
		dateiauswahl.setDialogTitle(Main.WoerterLanguage.get(33));
		filter = new FileNameExtensionFilter(Main.WoerterLanguage.get(34), "txt", "text");
		settingsLanguageComboBox.addItem(Main.WoerterLanguage.get(35));
		settingsLanguageComboBox.addItem("Bald auch Englische Übersetzung");
		//settingsLanguageComboBox.addItem(Main.WoerterLanguage.get(36));
		settingsLanguageComboBox.setSelectedIndex(Main.language);

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
		dateiOpen.setIcon(open);
		dateiOpen.addActionListener(new ActionHandler());
		dateiSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		dateiSave.setIcon(save);
		dateiSave.addActionListener(new ActionHandler());
		dateiExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
		dateiExit.setIcon(exit);
		dateiExit.addActionListener(new ActionHandler());
		menuDatei.add(dateiOpen);
		menuDatei.add(dateiSave);
		menuDatei.addSeparator();
		menuDatei.add(dateiExit);

		for (int i = 0; i < Main.verfuegbareWortlisten.size(); i++)
		{
			wortlistenAuswahlMenu.add(new JRadioButtonMenuItem(Main.verfuegbareWortlisten.get(i)));
		}
		for (int i = 0; i < Main.verfuegbareWortlisten.size(); i++)
		{
			if (Main.verfuegbareWortlisten.get(i).contains(Main.stdWortliste))
			{
				wortlistenAuswahlMenu.get(i).setSelected(true);
			}
			helpSwitchWordList.add(wortlistenAuswahlMenu.get(i));
			wortlisteRadioButtons.add(wortlistenAuswahlMenu.get(i));
			settingsStdWortlisteComboBox.addItem(Main.verfuegbareWortlisten.get(i));
		}

		settingsStdWortlisteComboBox.setSelectedItem(Main.stdWortliste);
		helpKonsole.addActionListener(new ActionHandler());
		helpKonsole.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_MASK));
		helpUpdateProgram.setIcon(updates);
		helpUpdateProgram.addActionListener(new ActionHandler());
		helpSettings.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		helpSettings.setIcon(settings);
		helpSettings.addActionListener(new ActionHandler());
		helpHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.CTRL_MASK));
		helpHelp.setIcon(help);
		helpHelp.addActionListener(new ActionHandler());
		menuHelp.add(helpSwitchWordList);
		menuHelp.add(helpKonsole);
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
		dateiauswahl.setFileFilter(filter);
	}

	public void actionPerformed(ActionEvent e)
	{

	}
}
