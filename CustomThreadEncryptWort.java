import java.time.LocalDateTime;

public class CustomThreadEncryptWort extends Thread {
	String wortNeu;
	String wort;
	String urspruenglichesWort;
	String ersterBuchstabe;
	String letzterBuchstabe;

	public CustomThreadEncryptWort(String s) {
		this.wort = s;
		this.urspruenglichesWort = s;
	}

	public void run() {
		System.out.println("[ " + LocalDateTime.now().format(Main.df) + " ] Thread gestartet!");

		ersterBuchstabe = wort.substring(0, 1);
		letzterBuchstabe = wort.substring(wort.length() - 1, wort.length());
		wort = delFirstLastChar(wort);
		urspruenglichesWort = delFirstLastChar(urspruenglichesWort);

		UI.wort1.setText("");
		Main.wortRueckgabe.clear();
		wortNeu = buchstabenTauschen();
		System.out.println("[ " + LocalDateTime.now().format(Main.df) + " ] Fertig!");
	}

	private String buchstabenTauschen() {
		for (int i = 0; i < (wort.length() / 2); i++) {
			int zufallswert1 = (int) (Math.random() * (wort.length() - 1) + 0.5);
			int zufallswert2 = (int) (Math.random() * (wort.length() - 1) + 0.5);
			wort = Main.swapCharactersOfString(wort, zufallswert1, zufallswert2);
		}

		if (!wort.equals(urspruenglichesWort) && !wort.equals(UI.wort1.getText())) {
			return ersterBuchstabe + wort + letzterBuchstabe;
		} else {
			buchstabenTauschen();
		}
		return "";
	}

	public String delFirstLastChar(String wort) {
		return wort.substring(1, wort.length() - 1);
	}

	public String getWortNeu()
	{
		return wortNeu;
	}
