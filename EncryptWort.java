package de.florian.gtwist;

public class EncryptWort {
	private String wortNeu;
	private String urspruenglichesWort;

	public EncryptWort(String wort) {
		wort = wort.toLowerCase();
		wort = wort.replaceAll("[^A-Za-zßäöüáãâàéẽêèëíĩîìóõôòúũûùÄÖÜÃÂÀÉẼÊÈËĨÎÌÓÕÔÒÚŨÛÙæ]", "");
		urspruenglichesWort = wort;

		if (wort.length() > 3) {
			wortNeu = buchstabenTauschen(wort.substring(1, wort.length() - 1), wort.substring(0, 1), wort.substring(wort.length() - 1, wort.length()), urspruenglichesWort);
		} else {
			wortNeu = wort;
		}
	}

	/**
	 * Methode zum verändern von Strings. Je nach Länge des Wortes werden
	 * unterschiedlich viele Buchstaben getauscht
	 *
	 * @param wort             String, bei dem zwei Buchstaben getaucht werden
	 *                         sollen
	 * @param ersterBuchstabe  erster Buchstabe des ursprünglichen Wortes
	 * @param letzterBuchstabe letzter Buchstabe des ursprünglichen Wortes
	 * @return vertauschter String mit erstem und letztdem Buchstabe
	 */
	private String buchstabenTauschen(String wort, String ersterBuchstabe, String letzterBuchstabe, String urspruenglichesWort) {
		for (int i = 0; i < (wort.length() / 2); i++) // Je länger der String ist, desto öfters werden zwei Buchstaben getauscht
		{
			wort = swapCharactersOfString(wort.toCharArray());
		}

		if (!(ersterBuchstabe + wort + letzterBuchstabe).equals(urspruenglichesWort)) // Wenn das neue Wort gleich wie das Ursprüngliche Wort ist, wird die Methode 'buchstabenTauschen()' nocheinmal aufgerufen
		{
			return ersterBuchstabe + wort + letzterBuchstabe; // Neu generiertes Wort wird inklusive erstem und letzem Buchstabn zurückgegeben
		}
		else
		{
			return buchstabenTauschen(wort, ersterBuchstabe, letzterBuchstabe, urspruenglichesWort);
		}
	}

	/**
	 * Tauscht zwei Zeichen eines Strings
	 *
	 * @param wort String, bei dem Buchstaben getaucht werden sollen
	 * @return String, der verändert wurde
	 */
	private static String swapCharactersOfString(char[] wort) {
		int zufallswert1 = (int) (Math.random() * (wort.length - 1) + 0.5); // Zufallszahl zwischen 1 und der Länge des Strings wird generiert
		int zufallswert2 = (int) (Math.random() * (wort.length - 1) + 0.5); // Zufallszahl zwischen 1 und der Länge des Strings wird generiert

		char temp = wort[zufallswert1];
		wort[zufallswert1] = wort[zufallswert2];
		wort[zufallswert2] = temp;

		return String.valueOf(wort);
	}

	/**
	 * Methode zum auslesen des Neu-generierten Wortes
	 *
	 * @return Neu generiertes Wort
	 */
	public String getWortNeu() {
		return wortNeu;
	}
}
