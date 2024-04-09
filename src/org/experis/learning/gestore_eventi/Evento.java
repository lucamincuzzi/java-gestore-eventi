package org.experis.learning.gestore_eventi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Evento {
    // Attributi
    private String titolo;
    private LocalDate data;
    private int postiTot;
    private int postiPrenotati;

    // Costruttori
    public Evento(String titolo, LocalDate data, int postiTot) throws IllegalArgumentException {
        validaTitolo(titolo);
        validaData(data);
        validaPosti(postiTot);

        this.titolo = titolo;
        this.data = data;
        this.postiTot = postiTot;
        this.postiPrenotati = 0;
    }

    // Metodi
        // Getter e Setter
    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getData() {
        return formattaData(this.data);
    }

    public void setData(LocalDate data) throws IllegalArgumentException {
        validaData(data);
        this.data = data;
    }

    public int getPostiTot() {
        return postiTot;
    }

    public int getPostiPrenotati() {
        return postiPrenotati;
    }
        //-----------------//

        // Metodi di servizio
            // Validatori
    private void validaTitolo(String titolo) throws IllegalArgumentException {
        if(titolo.trim().isEmpty()){
            throw new IllegalArgumentException("Il titolo non può essere vuoto.");
        }
    }

    private void validaData(LocalDate data) throws IllegalArgumentException {
        if(!data.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Data invalida: inserire una data posteriore a quella corrente.");
        }
    }

    private void validaPosti(int posti) throws IllegalArgumentException {
        if(posti <= 0){
            throw new IllegalArgumentException("Numero di posti invalido: inserire un numero positivo.");
        }
    }

    private void eventoPassato() throws IllegalArgumentException {
        if (LocalDate.now().isAfter(this.data)){
            throw new IllegalArgumentException("Non è più possibile prenotare o disdire per quest'evento.");
        }
    }
            //----------------//

    private String formattaData(LocalDate data){
        return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public int postiDisponibili(){
        return getPostiTot() - getPostiPrenotati();
    }

    public void prenota(int posti) throws IllegalArgumentException {
        // Validazioni
        validaPosti(posti);
        eventoPassato();
        if (posti + this.postiPrenotati >= this.postiTot){
            throw new IllegalArgumentException("Posti disponibili insufficienti per la prenotazione richiesta.");
        }

        // Istruzione effettiva del metodo
        this.postiPrenotati += posti;
    }

    public void disdici(int posti) throws IllegalArgumentException {
        // Validazioni
        validaPosti(posti);
        if (posti > getPostiPrenotati()){
            throw new IllegalArgumentException("Numero di posti prenotati da disdire invalido.");
        }

        // Istruzione effettiva del metodo
        this.postiPrenotati -= posti;
    }

    @Override
    public String toString() {
        return getData() + " - " + getTitolo();
    }
}
