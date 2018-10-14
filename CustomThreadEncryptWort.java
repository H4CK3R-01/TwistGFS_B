import java.time.LocalDateTime;

public class CustomThreadEncryptWort extends Thread
{
	private String wortNeu;
	private String wort;
	private String urspruenglichesWort;
	private String ersterBuchstabe;
	private String letzterBuchstabe;

	public CustomThreadEncryptWort(String s)
	{
		this.wort = s;
		this.urspruenglichesWort = s;
	}

	public void run()
	{
		System.out.println("[ " + LocalDateTime.now().format(Main.df) + " ] Thread gestartet!");
		wort = wort.replaceAll("[^A-Za-zß]", "");

		if (wort.length() > 3)
		{
			ersterBuchstabe = wort.substring(0, 1);
			letzterBuchstabe = wort.substring(wort.length() - 1, wort.length());
			wort = delFirstLastChar(wort);
			urspruenglichesWort = delFirstLastChar(urspruenglichesWort);
			wortNeu = buchstabenTauschen();
		}
		else
		{
			wortNeu = wort;
		}
		System.out.println("[ " + LocalDateTime.now().format(Main.df) + " ] Fertig!");
	}

	private String buchstabenTauschen()
	{
		for (int i = 0; i < (wort.length() / 2); i++)
		{
			int zufallswert1 = (int) (Math.random() * (wort.length() - 1) + 0.5);
			int zufallswert2 = (int) (Math.random() * (wort.length() - 1) + 0.5);
			wort = Main.swapCharactersOfString(wort, zufallswert1, zufallswert2);
		}

		if (!wort.equals(urspruenglichesWort))
		{
			return ersterBuchstabe + wort + letzterBuchstabe;
		}
		else
		{
			return buchstabenTauschen();
		}
	}

	public String delFirstLastChar(String wort)
	{
		return wort.substring(1, wort.length() - 1);
	}

	public String getWortNeu()
	{
		return wortNeu;
	}

}
