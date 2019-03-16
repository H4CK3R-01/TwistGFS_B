package de.florian.gtwist;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Console extends Thread
{
	public static JFrame console = new JFrame();
	public static JTextArea consoleArea = new JTextArea();

	public Console()
	{
	}

	public void run()
	{
		consoleArea = new JTextArea(50, 5);
		consoleArea.setFont(new Font("Courier", Font.PLAIN, 20));
		consoleArea.setEditable(false);
		
		console.setTitle("Twist: Konsole");
		console.setMinimumSize(new Dimension(600, 400));
		console.setIconImage(new ImageIcon("img/Twist.png").getImage());
		console.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		console.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent evt)
			{
				UI.menukonsole.setText("Konsole öffnen");
				console.setVisible(false);
			}
		});
		console.add(new JScrollPane(consoleArea));
	}

	public void setText(String text)
	{
		consoleArea.setText(consoleArea.getText() + text + " \n");
	}
}