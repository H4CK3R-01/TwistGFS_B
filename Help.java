import java.awt.Font;

public class Help
{
	public Help()
	{
		UI.helpText.setLineWrap(true); // Automatischer Zeilenumbruch wird eingeschalten
		UI.helpText.setWrapStyleWord(true); // Wort wird komplett in neue Zeile verschoben wenn es nicht mehr passt
		UI.helpText.setFont(new Font("Arial", Font.PLAIN, 13)); // Schriftart festlegen
		UI.helpText.setEditable(false); // TextArea kann nicht bearbeitet werden
		UI.helpDialog.add(UI.helpText); // TextArea wird zum Fenster hinzugefügt
		UI.helpDialog.setSize(400, 270); // Größe des Fensters wird festgelegt
		UI.helpDialog.setLocationRelativeTo(null); // Position des Fensters wird festgelegt (Mitte)
		UI.helpDialog.setResizable(false); // Fenster kann nicht vergrößert oder verkleinert werden
		UI.helpDialog.setModal(true); // Nur dieses Fenster ist aktiv, erst wenn dieses Fenster geschlossen wurde kann das andere Fenster wieder verwendet werden
		UI.helpDialog.setVisible(true); // Fenster ist sichtbar
	}
}
