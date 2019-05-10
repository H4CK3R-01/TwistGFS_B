package de.florian.twist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class DecryptEncryptStart extends Thread {
    private String action;

    public DecryptEncryptStart(String action) {
        this.action = action;
    }

    public void run() {
        if (action.equals("decrypt")) {
            Main.ui.setButtonsEnabled(false);
            Main.console.setText("[ " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm:ss")) + " ] " + Main.language.getString("decryptStart"));
            // Wörter aus Textfeld in Hasmap übertragen
            int i = 0;
            for (String entry : Main.ui.getTextDecryptedTextArea().split("\\s+")) {
                Main.inputWords.put(i, entry);
                i = i + 1;
            }

            // Wörter entschlüsseln
            Main.inputWords.entrySet().parallelStream().forEach(entry -> {
                Main.inputWords.put(entry.getKey(), new DecryptWort(entry.getValue()).getGeneratedWord());
            });

            // Ausgabe der Wörter
            Main.ui.clearTextEncryptedTextArea();
            for (Map.Entry<Integer, String> entry : Main.inputWords.entrySet()) {
                Main.ui.setTextEncryptedTextArea(entry.getValue());
            }
            Main.inputWords.clear();
            Main.console.setText("[ " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm:ss")) + " ] " + Main.language.getString("decryptStop"));
            Main.ui.setButtonsEnabled(true);
        } else if (action.equals("encrypt")) {
            Main.console.setText("[ " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm:ss")) + " ] " + Main.language.getString("encryptStart"));
            // Wörter aus Textfeld in Hasmap übertragen
            int i = 0;
            for (String entry : Main.ui.getTextEncryptedTextArea().split("\\s+")) {
                Main.inputWords.put(i, entry);
                i = i + 1;
            }

            // Wörter verschlüsseln
            Main.inputWords.entrySet().parallelStream().forEach(entry -> {
                Main.inputWords.put(entry.getKey(), new EncryptWort(entry.getValue()).getGeneratedWord());
            });

            // Ausgabe der Wörter
            Main.ui.clearTextDecryptedTextArea();
            for (Map.Entry<Integer, String> entry : Main.inputWords.entrySet()) {
                Main.ui.setTextDecryptedTextArea(entry.getValue());
            }
            Main.inputWords.clear();
            Main.console.setText("[ " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm:ss")) + " ] " + Main.language.getString("encryptStop"));
        }
    }
}
