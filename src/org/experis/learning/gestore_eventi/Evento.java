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
}
