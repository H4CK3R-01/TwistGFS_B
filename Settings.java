import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

public class Settings implements ActionListener
{
	public Settings()
	{
		UI.settingsSettings.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		UI.settingsSettings.setSize(460, 200);
		UI.settingsSettings.setResizable(false);
		UI.settingsSettings.setLocationRelativeTo(null);
		UI.settingsSettings.setIconImage(new ImageIcon("img/Twist.png").getImage());
		UI.settingsSettings.setVisible(true);
		UI.settingsContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		UI.settingsSettings.setContentPane(UI.settingsContentPane);
		UI.settingsGridBagLayout.columnWidths = new int[] { 0, 0, 0, 0 };
		UI.settingsGridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		UI.settingsGridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		UI.settingsGridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		UI.settingsContentPane.setLayout(UI.settingsGridBagLayout);

		GridBagConstraints gbc_zahlAnzeigenText = new GridBagConstraints();
		gbc_zahlAnzeigenText.anchor = GridBagConstraints.WEST;
		gbc_zahlAnzeigenText.insets = new Insets(0, 0, 5, 5);
		gbc_zahlAnzeigenText.gridx = 0;
		gbc_zahlAnzeigenText.gridy = 0;
		UI.settingsContentPane.add(UI.settingsZahlAnzeigenText, gbc_zahlAnzeigenText);

		UI.settingsPanel.setBorder(null);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.anchor = GridBagConstraints.EAST;
		gbc_panel.fill = GridBagConstraints.VERTICAL;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 0;
		UI.settingsContentPane.add(UI.settingsPanel, gbc_panel);
		UI.settingsgbl_panel.columnWidths = new int[] { 0, 0, 0 };
		UI.settingsgbl_panel.rowHeights = new int[] { 0, 0 };
		UI.settingsgbl_panel.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		UI.settingsgbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		UI.settingsPanel.setLayout(UI.settingsgbl_panel);

		UI.settingsGbc_zahlAnzeigenJa.insets = new Insets(0, 0, 0, 5);
		UI.settingsGbc_zahlAnzeigenJa.gridx = 0;
		UI.settingsGbc_zahlAnzeigenJa.gridy = 0;
		UI.settingsPanel.add(UI.settingsZahlAnzeigenJa, UI.settingsGbc_zahlAnzeigenJa);
		UI.settingsGbc_zahlAnzeigenNein.gridx = 1;
		UI.settingsGbc_zahlAnzeigenNein.gridy = 0;
		UI.settingsPanel.add(UI.settingsZahlAnzeigenNein, UI.settingsGbc_zahlAnzeigenNein);
		if (Main.zahlAnzeigen == 1)
		{
			UI.settingsZahlAnzeigenJa.setSelected(true);
			UI.settingsZahlAnzeigenNein.setSelected(false);
		}
		if (Main.zahlAnzeigen == 0)
		{
			UI.settingsZahlAnzeigenNein.setSelected(true);
			UI.settingsZahlAnzeigenJa.setSelected(false);
		}
		UI.settingsZahlAnzeigen.add(UI.settingsZahlAnzeigenJa);
		UI.settingsZahlAnzeigen.add(UI.settingsZahlAnzeigenNein);

		UI.settingsGbc_languageChangeText.anchor = GridBagConstraints.WEST;
		UI.settingsGbc_languageChangeText.insets = new Insets(0, 0, 5, 5);
		UI.settingsGbc_languageChangeText.gridx = 0;
		UI.settingsGbc_languageChangeText.gridy = 1;
		UI.settingsContentPane.add(UI.settingsLanguageChangeText, UI.settingsGbc_languageChangeText);
		UI.settingsGbc_languageComboBox.fill = GridBagConstraints.HORIZONTAL;
		UI.settingsGbc_languageComboBox.insets = new Insets(0, 0, 5, 0);
		UI.settingsGbc_languageComboBox.gridx = 2;
		UI.settingsGbc_languageComboBox.gridy = 1;
		UI.settingsContentPane.add(UI.settingsLanguageComboBox, UI.settingsGbc_languageComboBox);

		UI.settingsAddWortListeBtn.addActionListener(new ActionHandler());
		UI.settingsGbc_addWortListeText.anchor = GridBagConstraints.WEST;
		UI.settingsGbc_addWortListeText.insets = new Insets(0, 0, 5, 5);
		UI.settingsGbc_addWortListeText.gridx = 0;
		UI.settingsGbc_addWortListeText.gridy = 2;
		UI.settingsContentPane.add(UI.settingsAddWortListeText, UI.settingsGbc_addWortListeText);
		UI.settingsGbc_addWortListeBtn.anchor = GridBagConstraints.EAST;
		UI.settingsGbc_addWortListeBtn.insets = new Insets(0, 0, 5, 0);
		UI.settingsGbc_addWortListeBtn.gridx = 2;
		UI.settingsGbc_addWortListeBtn.gridy = 2;
		UI.settingsContentPane.add(UI.settingsAddWortListeBtn, UI.settingsGbc_addWortListeBtn);

		UI.settingsGbc_stdWortlisteText.anchor = GridBagConstraints.WEST;
		UI.settingsGbc_stdWortlisteText.insets = new Insets(0, 0, 5, 5);
		UI.settingsGbc_stdWortlisteText.gridx = 0;
		UI.settingsGbc_stdWortlisteText.gridy = 3;
		UI.settingsContentPane.add(UI.settingsStdWortlisteText, UI.settingsGbc_stdWortlisteText);
		UI.settingsGbc_stdWortlisteComboBox.fill = GridBagConstraints.HORIZONTAL;
		UI.settingsGbc_stdWortlisteComboBox.insets = new Insets(0, 0, 5, 0);
		UI.settingsGbc_stdWortlisteComboBox.gridx = 2;
		UI.settingsGbc_stdWortlisteComboBox.gridy = 3;
		UI.settingsContentPane.add(UI.settingsStdWortlisteComboBox, UI.settingsGbc_stdWortlisteComboBox);

		UI.settingsGbc_separator.gridwidth = 3;
		UI.settingsGbc_separator.fill = GridBagConstraints.BOTH;
		UI.settingsGbc_separator.insets = new Insets(0, 0, 5, 0);
		UI.settingsGbc_separator.gridx = 0;
		UI.settingsGbc_separator.gridy = 5;
		UI.settingsContentPane.add(UI.settingsSeparator, UI.settingsGbc_separator);
		UI.settingsOptions.setBorder(null);
		UI.settingsGbc_options.insets = new Insets(0, 0, 5, 0);
		UI.settingsGbc_options.fill = GridBagConstraints.BOTH;
		UI.settingsGbc_options.gridx = 2;
		UI.settingsGbc_options.gridy = 6;
		UI.settingsContentPane.add(UI.settingsOptions, UI.settingsGbc_options);
		UI.settingsOptions.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		UI.settingsOptions.add(UI.settingsBtnSpeichern);
		UI.settingsOptions.add(UI.settingsBtnAbbrechen);
		UI.settingsBtnAbbrechen.addActionListener(new ActionHandler());
		UI.settingsBtnSpeichern.addActionListener(new ActionHandler());
	}

	public void actionPerformed(ActionEvent arg0)
	{

	}
}
