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
import java.util.ArrayList;
import java.util.HashSet;

public class UI extends Thread {
    private JFrame mainFrame;
    private JPanel mainPanel;
    private JMenuBar menuBar;
    private JMenu fileMenu, switchWordListMenu, runMenu;
    ActionListener actionListenerWordlists = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < wordListsRadioButtons.length; i++) {
                if (e.getSource() == wordListsRadioButtons[i]) {
                    Main.console.setText("[ " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm:ss")) + " ] " + Main.language.getString("switchWordlist") + wordListsRadioButtons[i].getText());
                    Main.wordListHashSet = Main.readWordListFile(getClass().getClassLoader().getResource("wordlist/").getFile() + wordListsRadioButtons[i].getText());
                    System.out.println(Main.wordListHashSet.size());
                }
            }
        }
    };
    private JMenuItem consoleMenuItem, exitMenuItem, decryptMenuItem, encryptMenuItem;
    private JCheckBoxMenuItem arrayListJMenuItem, permuteJMenuItem;
    private JRadioButtonMenuItem[] wordListsRadioButtons;
    ActionListener actionListenerMenu = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == exitMenuItem) {
                System.exit(0);
            } else if (e.getSource() == consoleMenuItem) {
                if (Main.console.isVisible()) {
                    Main.console.setVisible(false);
                    consoleMenuItem.setText(Main.language.getString("consoleMenuItem1"));
                } else {
                    Main.console.setVisible(true);
                    consoleMenuItem.setText(Main.language.getString("consoleMenuItem2"));
                }
            } else if (e.getSource() == arrayListJMenuItem) {
                if (arrayListJMenuItem.getState()) {
                    Main.wordListArrayList = new ArrayList<>(Main.wordListHashSet);
                    Main.wordListHashSet = null;
                    Main.console.setText("[ " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm:ss")) + " ] " + Main.language.getString("useArrayList"));
                } else {
                    Main.wordListHashSet = new HashSet<>(Main.wordListArrayList);
                    Main.wordListArrayList = null;
                    Main.console.setText("[ " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm:ss")) + " ] " + Main.language.getString("useHashSet"));
                }
            } else if (e.getSource() == permuteJMenuItem) {
                Main.permute = permuteJMenuItem.getState();
            } else if (e.getSource() == decryptMenuItem) {
                new DecryptEncryptStart("decrypt").start();
            } else if (e.getSource() == encryptMenuItem) {
                new DecryptEncryptStart("encrypt").start();
            }
        }
    };

    private JLabel textDecryptedLabel, textEncryptedLabel;
    private JScrollPane textDecryptedScrollPane, textEncryptedScrollPane;
    private JTextArea textDecryptedTextArea, textEncryptedTextArea;
    private ImageIcon exitIcon, consoleIcon, settingsIcon, startIcon;
    private ButtonGroup wordListsButtonGroup;
    private Font font;

    public UI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
    }

    @SuppressWarnings("deprecation")
    public void run() {
        // Main Frame
        mainFrame = new JFrame(Main.language.getString("mainFrame"));

        // Panel
        mainPanel = new JPanel();

        // MenuBar
        menuBar = new JMenuBar();

        fileMenu = new JMenu(Main.language.getString("fileMenu"));
        switchWordListMenu = new JMenu(Main.language.getString("switchWordListMenu"));
        consoleMenuItem = new JMenuItem(Main.language.getString("consoleMenuItem1"));
        arrayListJMenuItem = new JCheckBoxMenuItem(Main.language.getString("arrayListMenuItem"));
        permuteJMenuItem = new JCheckBoxMenuItem(Main.language.getString("permuteMenuItem"));
        exitMenuItem = new JMenuItem(Main.language.getString("exitMenuItem"));

        runMenu = new JMenu(Main.language.getString("runMenu"));
        decryptMenuItem = new JMenuItem(Main.language.getString("decryptMenuItem"));
        encryptMenuItem = new JMenuItem(Main.language.getString("encryptMenuItem"));

        settingsIcon = new ImageIcon(getClass().getClassLoader().getResource("img/icon_settings.png"));
        exitIcon = new ImageIcon(getClass().getClassLoader().getResource("img/icon_exit.png"));
        consoleIcon = new ImageIcon(getClass().getClassLoader().getResource("img/icon_console.png"));
        startIcon = new ImageIcon(getClass().getClassLoader().getResource("img/icon_play.png"));

        font = new Font("Courier", Font.PLAIN, 25);
        // Textfelder
        textDecryptedTextArea = new JTextArea();
        textEncryptedTextArea = new JTextArea();
        textDecryptedScrollPane = new JScrollPane();
        textEncryptedScrollPane = new JScrollPane();
        textDecryptedLabel = new JLabel();
        textEncryptedLabel = new JLabel();

        exitIcon = new ImageIcon(exitIcon.getImage().getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH));
        settingsIcon = new ImageIcon(settingsIcon.getImage().getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH));
        consoleIcon = new ImageIcon(consoleIcon.getImage().getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH));
        startIcon = new ImageIcon(startIcon.getImage().getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH));

        wordListsButtonGroup = new ButtonGroup();

        // Menü zusammensetzen
        File f = new File(getClass().getClassLoader().getResource("wordlist/").getFile());
        File[] fileArray = f.listFiles();
        wordListsRadioButtons = new JRadioButtonMenuItem[fileArray.length];
        for (int i = 0; i < fileArray.length; i++) {
            wordListsRadioButtons[i] = new JRadioButtonMenuItem(fileArray[i].getName());
            wordListsButtonGroup.add(wordListsRadioButtons[i]);
            switchWordListMenu.add(wordListsRadioButtons[i]);
            wordListsRadioButtons[i].addActionListener(actionListenerWordlists);
        }
        wordListsRadioButtons[0].setSelected(true);

        switchWordListMenu.setIcon(settingsIcon);
        exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
        exitMenuItem.setIcon(exitIcon);
        exitMenuItem.addActionListener(actionListenerMenu);
        consoleMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_MASK));
        consoleMenuItem.setIcon(consoleIcon);
        consoleMenuItem.addActionListener(actionListenerMenu);
        //arrayListJMenuItem.setIcon(exitIcon);
        arrayListJMenuItem.addActionListener(actionListenerMenu);
        permuteJMenuItem.addActionListener(actionListenerMenu);

        fileMenu.add(switchWordListMenu);
        fileMenu.add(consoleMenuItem);
        fileMenu.add(arrayListJMenuItem);
        fileMenu.add(permuteJMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        decryptMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F11, InputEvent.CTRL_MASK));
        decryptMenuItem.setIcon(startIcon);
        decryptMenuItem.addActionListener(actionListenerMenu);
        encryptMenuItem.setAccelerator(KeyStroke.getKeyStroke("F11"));
        encryptMenuItem.setIcon(startIcon);
        encryptMenuItem.addActionListener(actionListenerMenu);
        runMenu.add(decryptMenuItem);
        runMenu.add(encryptMenuItem);

        mainFrame.setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        menuBar.add(runMenu);

        mainPanel.setLayout(new GridBagLayout());

        // Text Panel konfigurieren
        textDecryptedLabel.setFont(font);
        textDecryptedLabel.setText(Main.language.getString("textDecryptedLabel"));
        textDecryptedTextArea.setLineWrap(true);
        textDecryptedTextArea.setWrapStyleWord(true);
        textDecryptedTextArea.setFont(font);
        textDecryptedScrollPane.setViewportView(textDecryptedTextArea);
        textEncryptedLabel.setFont(font);
        textEncryptedLabel.setText(Main.language.getString("textEncryptedLabel"));
        textEncryptedTextArea.setLineWrap(true);
        textEncryptedTextArea.setWrapStyleWord(true);
        textEncryptedTextArea.setFont(font);
        textEncryptedScrollPane.setViewportView(textEncryptedTextArea);

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
        mainFrame.getContentPane().add(mainPanel, BorderLayout.CENTER); // Tabpane zum Fenster hinzufügen
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

    public void setConsoleMenuTitle(String name) {
        consoleMenuItem.setText(name);
    }

    public void setButtonsEnabled(boolean enabled) {
        this.decryptMenuItem.setEnabled(enabled);
        this.encryptMenuItem.setEnabled(enabled);
    }

    public String getTextEncryptedTextArea() {
        return textEncryptedTextArea.getText();
    }

    public void setTextEncryptedTextArea(String text) {
        textEncryptedTextArea.setText(textEncryptedTextArea.getText() + text + " ");
    }

    public String getTextDecryptedTextArea() {
        return textDecryptedTextArea.getText();
    }

    public void setTextDecryptedTextArea(String text) {
        textDecryptedTextArea.setText(textDecryptedTextArea.getText() + text + " ");
    }

    public void clearTextDecryptedTextArea() {
        textDecryptedTextArea.setText("");
    }

    public void clearTextEncryptedTextArea() {
        textEncryptedTextArea.setText("");
    }
}
