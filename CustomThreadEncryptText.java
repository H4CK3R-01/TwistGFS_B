import de.florian.twist.Main;
import de.florian.twist.UI;

public class CustomThreadEncryptText extends Thread
{
	String text;
	String wortNeu;

	public CustomThreadEncryptText(String s)
	{
		this.text = s;
	}

	public void run()
	{
		String[] splited = text.split("\\s+");
		System.out.println("Thread gestartet!");

		try
		{
			Thread.sleep(500);
		}
		catch (InterruptedException e)
		{
		}

		UI.wort1.setText("");

		System.out.println("Verschlüsseln gestartet...");
		for (int i = 0; i < splited.length; i++)
		{
			wortNeu = splited[i];
			for (int j = 0; j < 50; j++)
			{
				wortNeu = Main.swap(wortNeu, (int) Math.random() * (wortNeu.length() - 1) + 1,
						(int) Math.random() * (wortNeu.length() - 1) + 1);
			}

			if (!wortNeu.equals(splited[i]))
			{
				UI.text1.setText(UI.text1.getText() + " " + wortNeu);
			}
			System.out.println("Test");
		}
		System.out.println("Fertig!");
	}
}
