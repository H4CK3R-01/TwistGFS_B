public class CustomThreadEncryptWort extends Thread
{
	String wort;
	String urspruenglichesWort;
	String ersterBuchstabe;
	String letzterBuchstabe;

	public CustomThreadEncryptWort(String s)
	{
		this.wort = s;
		this.urspruenglichesWort = s;
	}

	public void run()
	{
		System.out.println("Thread gestartet!");

		ersterBuchstabe = wort.substring(0, 1);
		letzterBuchstabe = wort.substring(wort.length() - 1, wort.length());
		wort = delFirstLastChar(wort);
		System.out.println(wort);
		urspruenglichesWort = delFirstLastChar(urspruenglichesWort);

		UI.wort1.setText("");
		System.out.println("Verschlüsseln gestartet...");
		buchstabenTauschen();

		System.out.println("Fertig!");
	}

	private void buchstabenTauschen()
	{
		for (int i = 0; i < 10; i++)
		{
			wort = swap(wort, (int) ((Math.random()) * (wort.length() - 1) + 1),
					(int) ((Math.random()) * (wort.length() - 1) + 1));
		}

		if (!wort.equals(urspruenglichesWort) && !wort.equals(UI.wort1.getText()))
		{
			UI.wort1.setText(ersterBuchstabe + wort + letzterBuchstabe);
		}
		else
		{
			buchstabenTauschen();
		}
	}

	public static String swap(String a, int i, int j)
	{
		char temp;
		char[] charArray = a.toCharArray();
		temp = charArray[i];
		charArray[i] = charArray[j];
		charArray[j] = temp;
		return String.valueOf(charArray);
	}

	public String delFirstLastChar(String wort)
	{
		String wortNeu = "";
		for (int i = 1; i < wort.length(); i++)
		{
			wortNeu += wort.charAt(i);
		}
		return wortNeu;

	}
}
