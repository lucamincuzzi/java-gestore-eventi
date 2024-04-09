package org.experis.learning.gestore_eventi;

import java.time.LocalDate;

public class Evento {
    // Attributi
    private String titolo;
    private LocalDate data;
    private int postiTot;
    private int postiPrenotati;

    // Costruttori
    public Evento(String titolo, LocalDate data, int postiTot) throws IllegalArgumentException {

        this.titolo = titolo;
        if(data.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Data invalida: inserire una data posteriore a quella corrente.");
        }
        this.data = data;
        if(postiTot <= 0){
            throw new IllegalArgumentException("Numero di posti totale invalido: inserire un numero positivo.");
        }
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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) throws IllegalArgumentException {
        if(data.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Data invalida: inserire una data posteriore a quella corrente.");
        }
        this.data = data;
    }

    public int getPostiTot() {
        return postiTot;
    }

    public int getPostiPrenotati() {
        return postiPrenotati;
    }
}
