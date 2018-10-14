import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import com.sun.glass.events.WindowEvent;

public class Console extends Thread
{
	String text;

	public Console()
	{
	}

	public void setText(String text)
	{
		UI.logArea.setText(UI.logArea.getText() + text + " \n");
	}

	public void run()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
		}

		UI.logArea = new JTextArea(50, 5);
		UI.logArea.setFont(new Font("Arial", Font.PLAIN, 13));
		UI.logArea.setEditable(false);

		UI.constraints.fill = GridBagConstraints.BOTH;
		UI.constraints.weightx = 1.0;
		UI.constraints.weighty = 1.0;

		UI.Log.setTitle("Konsole");
		UI.Log.setLayout(new GridBagLayout());
		UI.Log.add(new JScrollPane(UI.logArea), UI.constraints);
		UI.Log.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		UI.Log.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent evt)
			{
				UI.helpKonsole.setText(Main.WoerterLanguage.get(7));
				UI.Log.setVisible(false);
				System.out.println(evt);
			}
		});
		UI.Log.setSize(500, 320);
		UI.Log.setIconImage(new ImageIcon("img/Twist.png").getImage());
	}
}