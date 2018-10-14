package de.florian.twist;

public class Permutation
{

	public Permutation(String s)
	{
		permute(s);
	}

	private void permute(String wort)
	{
		String[] temp = new String[fakultaet(wort.length())];

		for (int i = 0; i < wort.length(); i++)
		{
			temp[i] = wort.substring(wort.length() - 1) + wort.substring(0, wort.length() - 1);
			wort = temp[i];
			System.out.println(wort);
		}
		for (int i = 0; i < temp.length; i++)
		{
			//temp[i] // zweiten BUchstaben tauschen
		}
	}

	private int fakultaet(int wert)
	{
		if(wert > 1) {
			return (wert * fakultaet(wert - 1));
		} else {
			return 1;
		}
	}

}
