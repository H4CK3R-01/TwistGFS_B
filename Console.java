import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Console extends Thread
{
	String text;

	public Console()
	{
	}

	public void run()
	{
		UI.konsoleArea = new JTextArea(50, 5); // Größe der TextArea festlegen
		UI.konsoleArea.setFont(new Font("Arial", Font.PLAIN, 13)); // Schriftart festlegen
		UI.konsoleArea.setEditable(false); // TextArea kann nicht bearbeitet werden

		UI.constraints.fill = GridBagConstraints.BOTH;
		UI.constraints.weightx = 1.0;
		UI.constraints.weighty = 1.0;

		UI.konsole.setTitle(Main.languageFile.getString("console")); // Name des Fensters setzen
		UI.konsole.setLayout(new GridBagLayout());
		UI.konsole.add(new JScrollPane(UI.konsoleArea), UI.constraints); // TextArea zum Fenster hinzufügen
		UI.konsole.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		UI.konsole.addWindowListener(new WindowAdapter() // Text in Menü ändern wenn Konsole geschlossen wird
		{
			public void windowClosing(WindowEvent evt)
			{
				UI.helpKonsole.setText(Main.languageFile.getString("consoleOpen"));
				UI.konsole.setVisible(false);
			}
		});
		UI.konsole.setSize(500, 320); // Größe des Fensters festlegen
		UI.konsole.setIconImage(new ImageIcon("img/Twist.png").getImage()); // Icon des Fensters setzen
	}

	public void setText(String text)
	{
		UI.konsoleArea.setText(UI.konsoleArea.getText() + text + " \n");
	}
}