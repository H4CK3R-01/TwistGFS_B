package de.florian.twist;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Console extends Thread {
    private JFrame consoleFrame = new JFrame();
    private JTextArea consoleTextArea = new JTextArea();

    public Console() {
    }

    public void run() {
        consoleTextArea = new JTextArea(50, 5);
        consoleTextArea.setFont(new Font("Courier", Font.PLAIN, 25));
        consoleTextArea.setEditable(false);
        consoleFrame.add(new JScrollPane(consoleTextArea));

        consoleFrame.setTitle(Main.language.getString("consoleFrame"));
        consoleFrame.setMinimumSize(new Dimension(600, 400));
        consoleFrame.setIconImage(new ImageIcon(getClass().getClassLoader().getResource("img/Twist.png")).getImage());
        consoleFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        consoleFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                Main.ui.setConsoleMenuTitle(Main.language.getString("consoleMenuItem1"));
                consoleFrame.setVisible(false);
            }
        });
    }


    public void setText(String text) {
        consoleTextArea.setText(consoleTextArea.getText() + text + " \n");
    }

    public boolean isVisible() {
        return consoleFrame.isVisible();
    }

    public void setVisible(boolean b) {
        consoleFrame.setVisible(b);
    }

}