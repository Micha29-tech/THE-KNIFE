package theknife.model;

public interface RistorantiService {
    //Salva e restituisce l'ID assegnato
    String aggiungiRistorante(Ristorante ristorante, String ristoratoreUsername);
    boolean ristoratoreGestisce(String ristoratoreUsername,String ristoranteId);
    double mediaStelle(String ristoranteId);
    int numeroRecensioni(String ristoranteId);

    
}
