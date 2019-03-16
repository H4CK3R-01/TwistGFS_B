package de.florian.gtwist;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

public class UI extends Thread {
	static JFrame window;
	static JPanel mainPanel;
	static JMenuBar menuBar;
	static JMenu menufile, menuswitchWordList, menurun;
	static JMenuItem menukonsole, menuexit, menudecrypt, menuencrypt;
	static JRadioButtonMenuItem[] wortlisten;
	static JLabel textDecryptedLabel, textEncryptedLabel;
	static JScrollPane textDecryptedScrollPane, textEncryptedScrollPane;
	static JTextArea textDecrypted, textEncrypted;
	static ImageIcon exitIcon, consoleIcon, settingsIcon, startIcon;
	static ButtonGroup wortlistenButtonGroup;

	public UI() {
		// Moderne Benutzeroberfläche laden
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}
	}

	public void run() {
		// Main Frame
		window = new JFrame("Twist");

		// Panel
		mainPanel = new JPanel();

		// MenuBar
		menuBar = new JMenuBar();

		menufile = new JMenu("Datei");
		menuswitchWordList = new JMenu("Wortliste ändern");
		menukonsole = new JMenuItem("Konsole öffnen");
		menuexit = new JMenuItem("Beenden");

		menurun = new JMenu("Starten");
		menudecrypt = new JMenuItem("Entschlüsseln");
		menuencrypt = new JMenuItem("Verschlüsseln");

		settingsIcon = new ImageIcon("./img/icon_settings.png");
		exitIcon = new ImageIcon("./img/icon_exit.png");
		consoleIcon = new ImageIcon("./img/icon_console.png");
		startIcon = new ImageIcon("./img/icon_play.png");
		
		// Text
		textDecrypted = new JTextArea();
		textEncrypted = new JTextArea();
		textDecryptedScrollPane = new JScrollPane();
		textEncryptedScrollPane = new JScrollPane();
		textDecryptedLabel = new JLabel();
		textEncryptedLabel = new JLabel();

		exitIcon = new ImageIcon(exitIcon.getImage().getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH));
		settingsIcon = new ImageIcon(settingsIcon.getImage().getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH));
		consoleIcon = new ImageIcon(consoleIcon.getImage().getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH));
		startIcon = new ImageIcon(startIcon.getImage().getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH));

		wortlistenButtonGroup = new ButtonGroup();

		// Fenster konfigurieren
		if (Toolkit.getDefaultToolkit().getScreenSize().width < 2000) {
			window.setSize(new Dimension(900, 600));
			window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		} else {
			window.setMinimumSize(new Dimension(2000, 1000));
		}
		window.setLocationRelativeTo(null); // Position des Fensters wird festgelegt (Mitte)
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setIconImage(new ImageIcon("img/Twist.png").getImage()); // Icon des Fensters festlegen
		window.getContentPane().add(mainPanel, BorderLayout.CENTER); // Tabpane zum Fenster hinzufügen
		window.setVisible(true); // Fenster ist sichtbar

		// Menü zusammensetzen
		File f = new File("./wordlist");
		File[] fileArray = f.listFiles();
		wortlisten = new JRadioButtonMenuItem[fileArray.length];
		for (int i = 0; i < fileArray.length; i++) {
			wortlisten[i] = new JRadioButtonMenuItem(fileArray[i].getName());
			wortlistenButtonGroup.add(wortlisten[i]);
			menuswitchWordList.add(wortlisten[i]);
			wortlisten[i].addActionListener(actionListenerWortlisten);
		}
		wortlisten[0].setSelected(true);

		menuswitchWordList.setIcon(settingsIcon);
		menuexit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
		menuexit.setIcon(exitIcon);
		menuexit.addActionListener(actionListenerMenu);
		menukonsole.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_MASK));
		menukonsole.setIcon(consoleIcon);
		menukonsole.addActionListener(actionListenerMenu);

		menufile.add(menuswitchWordList);
		menufile.add(menukonsole);
		menufile.addSeparator();
		menufile.add(menuexit);

		menudecrypt.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F11, InputEvent.CTRL_MASK));
		menudecrypt.setIcon(startIcon);
		menudecrypt.addActionListener(actionListenerMenu);
		menuencrypt.setAccelerator(KeyStroke.getKeyStroke("F11"));
		menuencrypt.setIcon(startIcon);
		menuencrypt.addActionListener(actionListenerMenu);
		menurun.add(menudecrypt);
		menurun.add(menuencrypt);

		window.setJMenuBar(menuBar);
		menuBar.add(menufile);
		menuBar.add(menurun);

		mainPanel.setLayout(new GridBagLayout());

		// Text Panel konfigurieren
		textDecryptedLabel.setFont(new Font("Courier", Font.PLAIN, 20));
		textDecryptedLabel.setText("Verschlüsselter Text ");
		textDecrypted.setLineWrap(true);
		textDecrypted.setWrapStyleWord(true);
		textDecrypted.setFont(new Font("Courier", Font.PLAIN, 17));
		textDecryptedScrollPane.setViewportView(textDecrypted);
		textEncryptedLabel.setFont(new Font("Courier", Font.PLAIN, 20));
		textEncryptedLabel.setText("Entschlüsselter Text ");
		textEncrypted.setLineWrap(true);
		textEncrypted.setWrapStyleWord(true);
		textEncrypted.setFont(new Font("Courier", Font.PLAIN, 17));
		textEncryptedScrollPane.setViewportView(textEncrypted);

		addGB(textDecryptedLabel, mainPanel, 1, 1, GridBagConstraints.NONE, 0.0, 0.0, GridBagConstraints.FIRST_LINE_START, new Insets(0, 10, 6, 5), 0, 0);
		addGB(textDecryptedScrollPane, mainPanel, GridBagConstraints.REMAINDER, 1, GridBagConstraints.BOTH, 1.0, 1.0, GridBagConstraints.CENTER, new Insets(0, 0, 6, 10), 0, 0);
		addGB(textEncryptedLabel, mainPanel, 1, 1, GridBagConstraints.NONE, 0.0, 0.0, GridBagConstraints.FIRST_LINE_START, new Insets(0, 10, 6, 5), 0, 0);
		addGB(textEncryptedScrollPane, mainPanel, GridBagConstraints.REMAINDER, 1, GridBagConstraints.BOTH, 1.0, 1.0, GridBagConstraints.CENTER, new Insets(0, 0, 6, 10), 0, 0);
	}

	private void addGB(Component component, JPanel panel, int gridwidth, int gridheight, int fill, double weightx, double weighty, int anchor, Insets insets, int ipadx, int ipady) {
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = GridBagConstraints.RELATIVE;
		constraints.gridy = GridBagConstraints.RELATIVE;
		constraints.gridwidth = gridwidth;
		constraints.gridheight = gridheight;
		constraints.fill = fill;
		constraints.weightx = weightx;
		constraints.weighty = weighty;
		constraints.anchor = anchor;
		constraints.insets = insets;
		constraints.ipadx = ipadx;
		constraints.ipady = ipady;
		panel.add(component, constraints);
	}

	ActionListener actionListenerWortlisten = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < UI.wortlisten.length; i++) {
				if (e.getSource() == UI.wortlisten[i]) {
					Main.console
							.setText("[ " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm:ss")) + " ] Wortliste geändert. Neue Wortliste: " + UI.wortlisten[i].getText());
					Main.wordList = Main.readWordListFile("./wordlist/" + UI.wortlisten[i].getText());
					System.out.println(Main.wordList.size());
				}
			}
		}
	};

	ActionListener actionListenerMenu = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == UI.menuexit) {
				System.exit(0);
			} else if (e.getSource() == UI.menukonsole) {
				if (Console.console.isVisible()) {
					Console.console.setVisible(false);
					UI.menukonsole.setText("Konsole öffnen");
				} else {
					Console.console.setVisible(true);
					UI.menukonsole.setText("Konsole schließen");
				}
			} else if (e.getSource() == UI.menudecrypt) {
				new DecryptEncryptStart("decrypt").start();
			} else if (e.getSource() == UI.menuencrypt) {
				new DecryptEncryptStart("encrypt").start();;
			}
		}
	};
	
	public static void setDecryptText(String text) {
		textDecrypted.setText(textDecrypted.getText() + text + " ");
	}
	
	public static void setEncryptText(String text) {
		textEncrypted.setText(textEncrypted.getText() + text + " ");
	}
}
