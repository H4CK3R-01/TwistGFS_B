import java.awt.Font;

import de.florian.twist.UI;

public class Help
{
	public Help()
	{
		UI.helpText.setLineWrap(true);
		UI.helpText.setWrapStyleWord(true);
		UI.helpText.setFont(new Font("Arial", Font.PLAIN, 13));
		UI.helpText.setEditable(false);
		UI.helpDialog.add(UI.helpText);
		UI.helpDialog.setSize(400, 270);
		UI.helpDialog.setLocationRelativeTo(null);
		UI.helpDialog.setResizable(false);
		UI.helpDialog.setModal(true);
		UI.helpDialog.setVisible(true);
	}
}
