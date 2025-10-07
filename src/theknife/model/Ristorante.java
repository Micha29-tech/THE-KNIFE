package theknife.model;

public class Ristorante {
    private String nome;
    private String nazione;
    private String citta;
    private String indirizzo;
    private String tipoCucina;
    private int prezzoMedio;
    private boolean delivery;
    private boolean prenotazione;
    private double mediaStelle;

    //costruttore
    public Ristorante(String nome, String nazione, String citta, String indirizzo, String tipoCucina,int prezzoMedio,boolean delivery, boolean prenotazione){
        this.nome = nome;
        this.nazione = nazione;
        this.citta = citta;
        this.indirizzo = indirizzo;
        this.tipoCucina = tipoCucina;
        this.prezzoMedio =prezzoMedio;
        this.delivery = delivery;
        this.prenotazione = prenotazione;
        this.mediaStelle = 0.0;
    }

    //getter e setter
    public String getNome() {
        return nome;
    }
    public String getNazione(){
        return nazione;
    }
    public String getCitta(){
        return citta;
    }
    public String getIndirizzo(){
        return indirizzo;
    }
    public String getTipoCucina(){
        return tipoCucina;
    }
    public int getPrezzoMedio(){
        return prezzoMedio;
    }
    public boolean isDelivery(){
        return delivery;
    }
    public boolean isPrenotazione(){
        return prenotazione;
    }
    public double getMediaStelle(){
        return mediaStelle;
    }

    public void setMediaStelle(double mediaStelle){
        this.mediaStelle =mediaStelle;
    }

    @Override
    public String toString(){
        return nome + "("+ citta +") - " + tipoCucina + "- Prezzo medio: " + prezzoMedio + "€ - * " + mediaStelle;
    }
}
