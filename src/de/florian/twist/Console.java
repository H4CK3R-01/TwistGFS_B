package de.florian.twist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Console extends Thread {
    public static JFrame console = new JFrame();
    public static JTextArea consoleArea = new JTextArea();

    public Console() {
    }

    public void run() {
        consoleArea = new JTextArea(50, 5);
        consoleArea.setFont(new Font("Courier", Font.PLAIN, 20));
        consoleArea.setEditable(false);

        console.setTitle("Twist: Konsole");
        console.setMinimumSize(new Dimension(600, 400));
        console.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("img/Twist.png")).getImage());
        console.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        console.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                UI.konsole.setText("Konsole öffnen");
                console.setVisible(false);
            }
        });
        console.add(new JScrollPane(consoleArea));
    }

    /**
     * Methode um den Text der Konsole zu ändern
     *
     * @param text Text der hinzugefügt werden soll
     */
    public void setText(String text) {
        consoleArea.setText(consoleArea.getText() + text + " \n");
    }
}