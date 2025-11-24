package theknife.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class FileUtentiService implements UtentiService {

    private final File fileUtenti;

    public FileUtentiService(String pathFile) {
        this.fileUtenti = new File(pathFile);
    }

    // ---- cifratura "semplice" della password (Base64) ----
    private String cifraPassword(String passwordChiara) {
        return Base64.getEncoder()
                     .encodeToString(passwordChiara.getBytes(StandardCharsets.UTF_8));
    }

    // ---- lettura di tutti gli utenti dal CSV ----
    private List<Utente> leggiTutti() {
        List<Utente> utenti = new ArrayList<>();

        if (!fileUtenti.exists()) {
            return utenti; // file ancora vuoto
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fileUtenti))) {
            String riga;
            while ((riga = br.readLine()) != null) {
                if (riga.isBlank() || riga.startsWith("username;")) {
                    continue; // salta intestazione / righe vuote
                }

                String[] campi = riga.split(";");
                if (campi.length < 7) {
                    continue;
                }

                String username = campi[0];
                String password = campi[1];
                String nome = campi[2];
                String cognome = campi[3];
                String ruolo = campi[4];
                String dataNascitaStr = campi[5];
                String domicilio = campi[6];

                Utente u = new Utente();
                u.setUsername(username);
                u.setPassword(password);
                u.setNome(nome);
                u.setCognome(cognome);
                u.setRuolo(ruolo);

                if (!dataNascitaStr.isBlank()) {
                    u.setDataNascita(LocalDate.parse(dataNascitaStr));
                }

                u.setLuogoDomicilio(domicilio);

                utenti.add(u);
            }
        } catch (IOException e) {
            System.err.println("Errore in lettura utenti: " + e.getMessage());
        }

        return utenti;
    }

    // ---- scrittura di tutti gli utenti nel CSV ----
    private void salvaTutti(List<Utente> utenti) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileUtenti))) {
            // intestazione
            bw.write("username;password;nome;cognome;ruolo;dataNascita;domicilio");
            bw.newLine();

            for (Utente u : utenti) {
                String dataNascitaStr = "";
                if (u.getDataNascita() != null) {
                    dataNascitaStr = u.getDataNascita().toString(); // YYYY-MM-DD
                }

                String riga =
                        u.getUsername() + ";" +
                        u.getPassword() + ";" +
                        u.getNome() + ";" +
                        u.getCognome() + ";" +
                        u.getRuolo() + ";" +
                        dataNascitaStr + ";" +
                        u.getLuogoDomicilio();

                bw.write(riga);
                bw.newLine();
            }

        } catch (IOException e) {
            System.err.println("Errore in scrittura utenti: " + e.getMessage());
        }
    }

    // ---- implementazione UtentiService ----

    @Override
    public boolean registra(Utente utente, String passwordChiara) {
        List<Utente> utenti = leggiTutti();

        boolean esiste = utenti.stream()
                .anyMatch(u -> u.getUsername().equalsIgnoreCase(utente.getUsername()));

        if (esiste) {
            return false; // username già usato
        }

        utente.setPassword(cifraPassword(passwordChiara));
        utenti.add(utente);
        salvaTutti(utenti);
        return true;
    }

    @Override
    public Optional<Utente> login(String username, String passwordChiara) {
        List<Utente> utenti = leggiTutti();
        String passwordCifrata = cifraPassword(passwordChiara);

        return utenti.stream()
                .filter(u -> u.getUsername().equalsIgnoreCase(username)
                          && u.getPassword().equals(passwordCifrata))
                .findFirst();
    }

    @Override
    public Optional<Utente> byUsername(String username) {
        return leggiTutti().stream()
                .filter(u -> u.getUsername().equalsIgnoreCase(username))
                .findFirst();
    }

    @Override
    public List<Utente> tutti() {
        return leggiTutti();
    }
}
