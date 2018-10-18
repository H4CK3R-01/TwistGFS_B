import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class StarteThread extends Thread
{
	String s;
	String wort;
	HashSet<Character> wortNeu = new HashSet<Character>();
	ArrayList<CustomThreadDecryptWort> threadTextEntschluesseln = new ArrayList<>();
	ArrayList<CustomThreadEncryptWort> threadTextVerschluesseln = new ArrayList<>();

	public StarteThread(String s)
	{
		this.s = s;
	}

	public void run()
	{
		if (s.equals("DecryptWort"))
		{
			if (!UI.wort1.getText().contains(" ")) // Prüfen ob Wort Leerzeichen enthält
			{
				UI.wort2.setText(""); // Textfeld leeren
				wort = UI.wort1.getText(); // Wort auslesen
				for (int i = 0; i < wort.length(); i++)
				{
					wortNeu.add(wort.charAt(i));
				}
				UI.progressBar.setMinimum(0);
				UI.progressBar.setMaximum((int) fakultaet(wortNeu.size()));
				UI.progressBar.setValue(0);
				
				CustomThreadDecryptWort threadWortEntschluesseln = new CustomThreadDecryptWort(wort);
				threadWortEntschluesseln.start(); // neuen Thread starten

				try
				{
					threadWortEntschluesseln.join(); // Warten bis Thread fertig ist
				}
				catch (InterruptedException e1)
				{
					e1.printStackTrace();
				}

				Iterator<String> iterator = threadWortEntschluesseln.getWortListeNachPermutation().iterator();
				while (iterator.hasNext())
				{
					UI.wort2.setText(UI.wort2.getText() + iterator.next() + "\n"); // Entschlüsselte Wörter in Textfels speichern
				}
			}
			else
			{
				JOptionPane.showOptionDialog(UI.window,
						Main.languageFile.getString("noSpaceInWords"), Main.languageFile.getString("wrongInput"),
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Twist.png"),
						new String[] { Main.languageFile.getString("ok") }, Main.languageFile.getString("ok"));
			}
		}
		else if (s.equals("EncryptWort"))
		{
			if (!UI.wort2.getText().contains(" ")) // Prüfen ob String Leerzeichen enthält
			{
				UI.wort1.setText(""); // Textfeld leeren
				wort = UI.wort2.getText(); // Wort auslesen
				UI.progressBar.setMinimum(0);
				UI.progressBar.setMaximum(wort.length()/2);
				UI.progressBar.setValue(0);
				
				CustomThreadEncryptWort threadWortEntschluesseln = new CustomThreadEncryptWort(wort);
				threadWortEntschluesseln.start(); // Thread starten

				try
				{
					threadWortEntschluesseln.join(); // Warten bis Thread fertig ist
				}
				catch (InterruptedException e1)
				{
					e1.printStackTrace();
				}
				UI.wort1.setText(threadWortEntschluesseln.getWortNeu()); // Wort in Textfeld schreiben
			}
			else
			{
				JOptionPane.showOptionDialog(UI.window, Main.languageFile.getString("noSpaceInWords"), Main.languageFile.getString("wrongInput"),
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Twist.png"),
						new String[] { Main.languageFile.getString("ok") }, Main.languageFile.getString("ok"));
			}
		}
		else if (s.equals("DecryptText"))
		{
			// String an allen Leerzeichen teilen
			UI.text2.setText("");
			String[] splited = UI.text1.getText().split("\\s+");
			threadTextEntschluesseln.clear();

			// Soviele Threats wie Wörter werden gestartet
			for (int i = 0; i < splited.length; i++)
			{
				threadTextEntschluesseln.add(new CustomThreadDecryptWort(splited[i]));
				threadTextEntschluesseln.get(threadTextEntschluesseln.size() - 1).start();
			}

			for (int i = 0; i < threadTextEntschluesseln.size(); i++)
			{
				try
				{
					threadTextEntschluesseln.get(i).join(); // Warten bis Thread fertig ist
					if (Main.zahlAnzeigen == 1)
					{
						// neues Wort mit Zahl dahinter in das Feld schreiben
						UI.text2.setText(UI.text2.getText() + " "
								+ threadTextEntschluesseln.get(i).getWortListeNachPermutation().iterator().next() + "("
								+ threadTextEntschluesseln.get(i).getWortListeNachPermutation().size() + ")");
					}
					else
					{
						// neues Wort ohne Zahl dahinter in das Feld schreiben
						UI.text2.setText(UI.text2.getText() + " "
								+ threadTextEntschluesseln.get(i).getWortListeNachPermutation().iterator().next());
					}
				}
				catch (InterruptedException e1)
				{
					e1.printStackTrace();
				}
			}
			threadTextEntschluesseln.clear(); // Liste mit den Threads leeren
		}
		else if (s.equals("EncryptText"))
		{
			// String an allen Leerzeichen teilen
			UI.text1.setText("");
			String[] splited = UI.text2.getText().split("\\s+");
			threadTextVerschluesseln.clear();

			// Soviele Threats wie Wörter werden gestartet
			for (int i = 0; i < splited.length; i++)
			{
				threadTextVerschluesseln.add(new CustomThreadEncryptWort(splited[i]));
				threadTextVerschluesseln.get(i).start();
			}

			for (int i = 0; i < threadTextVerschluesseln.size(); i++)
			{
				try
				{
					threadTextVerschluesseln.get(i).join(); // Warten bis Thread fertig ist
					UI.text1.setText(UI.text1.getText() + threadTextVerschluesseln.get(i).getWortNeu() + " "); // Wort in Textfeld schreiben
				}
				catch (InterruptedException e1)
				{
					e1.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Berechnet von jeder beliebigen Zahl die Fakultät
	 * @param num Zahl von der die Fakultät berechnet werden soll
	 * @return Fakultät von num
	 */
	public static long fakultaet(long num)
	{
		if (num == 0) return 1;
		long result = 1;
		for (long i = 2; i <= num; i++)
		{
			result *= i;
		}
		return result;
	}
}
