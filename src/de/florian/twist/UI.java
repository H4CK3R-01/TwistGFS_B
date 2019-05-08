package de.florian.twist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UI extends Thread {
    static JFrame mainFrame;
    static JPanel mainPanel;
    static JMenuBar menu;
    static JMenu file, switchWordList, run;
    static JMenuItem konsole, exit, decrypt, encrypt;
    static JRadioButtonMenuItem[] wordlists;
    static JLabel textDecryptedLabel, textEncryptedLabel;
    static JScrollPane textDecryptedScrollPane, textEncryptedScrollPane;
    static JTextArea textDecrypted, textEncrypted;
    static ImageIcon exitIcon, consoleIcon, settingsIcon, startIcon;
    static ButtonGroup wordlistsButtonGroup;
    ActionListener actionListenerWortlisten = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < UI.wordlists.length; i++) {
                if (e.getSource() == UI.wordlists[i]) {
                    Main.console.setText("[ " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm:ss")) + " ] Wortliste geändert. Neue Wortliste: " + UI.wordlists[i].getText());
                    Main.wordList = Main.readWordListFile(getClass().getClassLoader().getResource("wordlist/").getFile() + UI.wordlists[i].getText());
                    System.out.println(Main.wordList.size());
                }
            }
        }
    };
    ActionListener actionListenerMenu = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == UI.exit) {
                System.exit(0);
            } else if (e.getSource() == UI.konsole) {
                if (Console.console.isVisible()) {
                    Console.console.setVisible(false);
                    UI.konsole.setText("Konsole öffnen");
                } else {
                    Console.console.setVisible(true);
                    UI.konsole.setText("Konsole schließen");
                }
            } else if (e.getSource() == UI.decrypt) {
                new DecryptEncryptStart("decrypt").start();
            } else if (e.getSource() == UI.encrypt) {
                new DecryptEncryptStart("encrypt").start();
            }
        }
    };

    public UI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
    }

    /**
     * Methode um den Text im verschlüsselten Textfeld zu ändern
     *
     * @param text String der hinzugefügt wird
     */
    public static void setDecryptText(String text) {
        textDecrypted.setText(textDecrypted.getText() + text + " ");
    }

    /**
     * Methode um den Text im entschlüsselten Textfeld zu ändern
     *
     * @param text String der hinzugefügt wird
     */
    public static void setEncryptText(String text) {
        textEncrypted.setText(textEncrypted.getText() + text + " ");
    }

    @SuppressWarnings("deprecation")
    public void run() {
        // Main Frame
        mainFrame = new JFrame("Twist");

        // Panel
        mainPanel = new JPanel();

        // MenuBar
        menu = new JMenuBar();

        file = new JMenu("Datei");
        switchWordList = new JMenu("Wortliste ändern");
        konsole = new JMenuItem("Konsole öffnen");
        exit = new JMenuItem("Beenden");

        run = new JMenu("Starten");
        decrypt = new JMenuItem("Entschlüsseln");
        encrypt = new JMenuItem("Verschlüsseln");

        settingsIcon = new ImageIcon(getClass().getClassLoader().getResource("img/icon_settings.png"));
        exitIcon = new ImageIcon(getClass().getClassLoader().getResource("img/icon_exit.png"));
        consoleIcon = new ImageIcon(getClass().getClassLoader().getResource("img/icon_console.png"));
        startIcon = new ImageIcon(getClass().getClassLoader().getResource("img/icon_play.png"));

        // Textfelder
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

        wordlistsButtonGroup = new ButtonGroup();

        // Menü zusammensetzen
        File f = new File(getClass().getClassLoader().getResource("wordlist/").getFile());
        File[] fileArray = f.listFiles();
        wordlists = new JRadioButtonMenuItem[fileArray.length];
        for (int i = 0; i < fileArray.length; i++) {
            wordlists[i] = new JRadioButtonMenuItem(fileArray[i].getName());
            wordlistsButtonGroup.add(wordlists[i]);
            switchWordList.add(wordlists[i]);
            wordlists[i].addActionListener(actionListenerWortlisten);
        }
        wordlists[0].setSelected(true);

        switchWordList.setIcon(settingsIcon);
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
        exit.setIcon(exitIcon);
        exit.addActionListener(actionListenerMenu);
        konsole.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_MASK));
        konsole.setIcon(consoleIcon);
        konsole.addActionListener(actionListenerMenu);

        file.add(switchWordList);
        file.add(konsole);
        file.addSeparator();
        file.add(exit);

        decrypt.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F11, InputEvent.CTRL_MASK));
        decrypt.setIcon(startIcon);
        decrypt.addActionListener(actionListenerMenu);
        encrypt.setAccelerator(KeyStroke.getKeyStroke("F11"));
        encrypt.setIcon(startIcon);
        encrypt.addActionListener(actionListenerMenu);
        run.add(decrypt);
        run.add(encrypt);

        mainFrame.setJMenuBar(menu);
        menu.add(file);
        menu.add(run);

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

        // Fenster konfigurieren
        if (Toolkit.getDefaultToolkit().getScreenSize().width < 2000) {
            mainFrame.setSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height));
            mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        } else {
            mainFrame.setMinimumSize(new Dimension(2000, 1000));
        }
        mainFrame.setLocationRelativeTo(null); // Position des Fensters wird festgelegt (Mitte)
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("img/Twist.png")).getImage()); // Icon des Fensters festlegen
        mainFrame.getContentPane().add(mainPanel, BorderLayout.CENTER); // Tabpane zum Fenster hinzuf�gen
        mainFrame.setVisible(true); // Fenster ist sichtbar
    }

    /**
     * Methode um Objekte zur GUI hinzuzufügen
     *
     * @param component Objekt, das hinzugefügt werden soll
     * @param panel     Panel zu dem das Objekt hinzugefügt wird
     */
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
}
