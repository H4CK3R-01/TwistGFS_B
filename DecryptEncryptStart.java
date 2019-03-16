package de.florian.gtwist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class DecryptEncryptStart extends Thread {
	String action;

	public DecryptEncryptStart(String s) {
		action = s;
	}

	public void run() {
		if (action.equals("decrypt")) {
			Main.console.setText("[ " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm:ss")) + " ] Entschlüsseln gestartet");
			// Wörter aus Textfeld in Hasmap übertragen
			int i = 0;
			for (String entry : UI.textDecrypted.getText().split("\\s+")) {
				Main.text.put(i, entry);
				i = i + 1;
			}

			// Wörter entschlüsseln
			Main.text.entrySet().parallelStream().forEach(entry -> {
				Main.text.put(entry.getKey(), new DecryptWort(entry.getValue()).getWortListeNachPermutation().iterator().next());
			});

			// Ausgabe der Wörter
			UI.textEncrypted.setText("");
			for (Map.Entry<Integer, String> entry : Main.text.entrySet()) {
				UI.setEncryptText(entry.getValue());
			}
			Main.text.clear();
			Main.console.setText("[ " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm:ss")) + " ] Entschlüsseln beendet");
		} else if (action.equals("encrypt")) {
			Main.console.setText("[ " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm:ss")) + " ] Verschlüsseln gestartet");
			// Wörter aus Textfeld in Hasmap übertragen
			int i = 0;
			for (String entry : UI.textEncrypted.getText().split("\\s+")) {
				Main.text.put(i, entry);
				i = i + 1;
			}

			// Wörter verschlüsseln
			Main.text.entrySet().parallelStream().forEach(entry -> {
				Main.text.put(entry.getKey(), new EncryptWort(entry.getValue()).getWortNeu());
			});

			// Ausgabe der Wörter
			UI.textDecrypted.setText("");
			for (Map.Entry<Integer, String> entry : Main.text.entrySet()) {
				UI.setDecryptText(entry.getValue());
			}
			Main.text.clear();
			Main.console.setText("[ " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm:ss")) + " ] Verschlüsseln beendet");
		}
	}
}
