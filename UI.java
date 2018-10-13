package de.florian.twist;

import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

public class UI implements ActionListener
{
	// Main Frame
	public static JFrame window = new JFrame("Twist");

	// MenuBar
	public static JMenuBar menuBar = new JMenuBar();
	public static JMenu datei = new JMenu("Datei");
	public static JMenu hilfe = new JMenu("Hilfe");
	public static JMenuItem open = new JMenuItem("Öffnen");
	public static JMenuItem save = new JMenuItem("Speichern");
	public static JMenuItem exit = new JMenuItem("Beenden");
	public static JMenuItem version = new JMenuItem("Version prüfen");
	public static JMenuItem help = new JMenuItem("Hilfe");

	// Tabpane
	public static JTabbedPane tabpane = new JTabbedPane(JTabbedPane.BOTTOM, JTabbedPane.SCROLL_TAB_LAYOUT);

	// Text
	public static JTextArea TextArea1 = new JTextArea(8, 80);
	public static JScrollPane scrollPaneTextArea1 = new JScrollPane();
	public static JTextArea TextArea2 = new JTextArea(8, 80);
	public static JScrollPane scrollPaneTextArea2 = new JScrollPane();
	public static JButton btnEntschluesselnText = new JButton("Entschlüsseln");
	public static JButton btnVerschluesselnText = new JButton("Verschlüsseln");
	public static JLabel lbtext = new JLabel("oder");

	// Wort
	public static JTextArea WortArea1 = new JTextArea(8, 80);
	public static JScrollPane scrollPaneWortArea1 = new JScrollPane();
	public static JTextArea WortArea2 = new JTextArea(8, 80);
	public static JScrollPane scrollPaneWortArea2 = new JScrollPane();
	public static JButton btnEntschluesselnWort = new JButton("Entschlüsseln");
	public static JButton btnVerschluesselnWort = new JButton("Verschlüsseln");
	public static JLabel lbwort = new JLabel("oder");

	// Panel
	public static JPanel panelText = new JPanel();
	public static JPanel panelWort = new JPanel();

	public static JFileChooser dateiauswahl = new JFileChooser();
	public static JDialog dialog = new JDialog();
	public static JTextArea helpText = new JTextArea(
			"Mithilfe des Tabs 'Text' können Sie ganze Texte ver- oder entschlüsseln. Beim entschlüsseln wird, wenn für ein verschlüsseltes Wort mehrere mögliche Wörter passen würden, immer das erste Wort, das gefunden wird, ausgegeben."
					+ "\n\nMithilfe des Tabs 'Wort' können Sie einzelne Wörter ver- oder entschlüsseln. Beim entschlüsseln werden im Gegensatz zu dem 'Text'-Tab alle Wörter ausgegeben.");
	public static JOptionPane optionDialog = new JOptionPane();
	
	@SuppressWarnings("deprecation")
	public UI()
	{
		// Fenster konfigurieren
		window.setSize(850, 400);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//window.setIconImage(new ImageIcon("Twist.png").getImage());
		window.add(tabpane);
		window.setVisible(true);

		// Menü zusammensetzen
		window.setJMenuBar(menuBar);
		menuBar.add(datei);
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		open.addActionListener(new ActionHandler());
		datei.add(open);
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		save.addActionListener(new ActionHandler());
		datei.add(save);
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
		exit.addActionListener(new ActionHandler());
		datei.add(exit);

		menuBar.add(hilfe);
		version.addActionListener(new ActionHandler());
		hilfe.add(version);
		help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.CTRL_MASK));
		help.addActionListener(new ActionHandler());
		hilfe.add(help);

		// Text Panel
		TextArea1.setLineWrap(true);
		TextArea1.setWrapStyleWord(true);
		TextArea1.setFont(new Font("Arial", Font.PLAIN, 13));
		TextArea1.setText("Verschlüsselten Text hier eingeben");
		scrollPaneTextArea1.setViewportView(TextArea1);
		panelText.add(scrollPaneTextArea1);
		panelText.add(lbtext);
		TextArea2.setLineWrap(true);
		TextArea2.setWrapStyleWord(true);
		TextArea2.setFont(new Font("Arial", Font.PLAIN, 13));
		TextArea2.setText("Entschlüsselten Text hier eingeben");
		scrollPaneTextArea2.setViewportView(TextArea2);
		panelText.add(scrollPaneTextArea2);
		btnEntschluesselnText.addActionListener(new ActionHandler());
		panelText.add(btnEntschluesselnText);
		btnVerschluesselnText.addActionListener(new ActionHandler());
		panelText.add(btnVerschluesselnText);
		tabpane.addTab("Text ", panelText);

		// Wort Panel
		WortArea1.setLineWrap(true);
		WortArea1.setWrapStyleWord(true);
		WortArea1.setFont(new Font("Arial", Font.PLAIN, 13));
		WortArea1.setText("Verschlüsseltes Wort hier eingeben");
		scrollPaneWortArea1.setViewportView(WortArea1);
		panelWort.add(scrollPaneWortArea1);
		panelWort.add(lbwort);
		WortArea2.setLineWrap(true);
		WortArea2.setWrapStyleWord(true);
		WortArea2.setFont(new Font("Arial", Font.PLAIN, 13));
		WortArea2.setText("Entschlüsseltes Wort hier eingeben");
		scrollPaneWortArea2.setViewportView(WortArea2);
		panelWort.add(scrollPaneWortArea2);
		btnEntschluesselnWort.addActionListener(new ActionHandler());
		panelWort.add(btnEntschluesselnWort);
		btnVerschluesselnWort.addActionListener(new ActionHandler());
		panelWort.add(btnVerschluesselnWort);
		tabpane.addTab("Wort", panelWort);
	}

	public void actionPerformed(ActionEvent e)
	{

	}
}
