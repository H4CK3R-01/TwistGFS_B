package de.florian.twist;

import javax.swing.JFrame;

public class CustomThreadProgressBar extends Thread
{
	public CustomThreadProgressBar()
	{
	}

	public void run()
	{
		System.out.println("Hi");
		UI.progressDialog.setTitle("Fortschritt");
		UI.progressDialog.setSize(300, 100);
		UI.progressDialog.setLocationRelativeTo(null);
		UI.progressDialog.setResizable(true);
		UI.progressDialog.setVisible(true);
		UI.progressDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		UI.progressBar.setMinimum(0);
		UI.progressBar.setMaximum(Main.anzahlMoeglicherWoerter);

		UI.progressDialog.add(UI.progressBar);
		while (Main.anzahlWoerter < Main.anzahlMoeglicherWoerter)
		{
			System.out.println(Main.anzahlWoerter);
			UI.progressBar.setValue(Main.anzahlWoerter);
			
			try
			{
				Thread.sleep(500);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		// Thread beenden
	}

	public void setValue(int value)
	{
		UI.progressBar.setValue(value);
	}

}
