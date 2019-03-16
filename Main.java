package de.florian.gtwist;

import java.io.*;
import java.util.*;

public class Main {
	public static HashSet<String> wordList = new HashSet<>();
	public static HashMap<Integer, String> text = new HashMap<Integer, String>();
	public static Console console = new Console();
	private static UI ui = new UI();


	public static void main(String[] args) {
		wordList = readWordListFile("./wordlist/Deutsch.txt");

		ui.start();
		console.start();
	}

	/**
	 * Liest eine Datei Zeile für Zeile ein und speichert alle Werte in einem
	 * HashSet
	 * 
	 * @param file Datei, die eingelesen werden soll
	 * @return HashSet, dass alle Strings der Datei enthält
	 * 
	 */
	public static HashSet<String> readWordListFile(String file) {
		HashSet<String> list = new HashSet<>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = br.readLine()) != null) {
				list.add(line.toLowerCase());
			}
			br.close();
		} catch (IOException e) {
		}
		return list;
	}
}
