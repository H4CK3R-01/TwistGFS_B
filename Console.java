import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.sun.glass.events.WindowEvent;

public class Console extends Thread
{
	String text;

	public Console()
	{
	}

	public void setText(String text)
	{
		UI.konsoleArea.setText(UI.konsoleArea.getText() + text + " \n");
	}

	public void run()
	{
		UI.konsoleArea = new JTextArea(50, 5); // Größe der TextArea festlegen
		UI.konsoleArea.setFont(new Font("Arial", Font.PLAIN, 13)); // Schriftart festlegen
		UI.konsoleArea.setEditable(false); // TextArea kann nicht bearbeitet werden

		UI.constraints.fill = GridBagConstraints.BOTH;
		UI.constraints.weightx = 1.0;
		UI.constraints.weighty = 1.0;

		UI.konsole.setTitle("Konsole"); // Name des Fensters setzen
		UI.konsole.setLayout(new GridBagLayout());
		UI.konsole.add(new JScrollPane(UI.konsoleArea), UI.constraints); // TextArea zum Fenster hinzufügen
		UI.konsole.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
		UI.konsole.addWindowListener(new WindowAdapter()
		{
			@SuppressWarnings("unused")
			public void windowClosing(WindowEvent evt)
			{
				UI.helpKonsole.setText(Main.WoerterLanguage.get(7));
				UI.konsole.setVisible(false);
				System.out.println(evt);
			}
		});
		UI.konsole.setSize(500, 320); // Größe des Fensters festlegen
		UI.konsole.setIconImage(new ImageIcon("img/Twist.png").getImage()); // Icon des Fensters setzen
	}
}