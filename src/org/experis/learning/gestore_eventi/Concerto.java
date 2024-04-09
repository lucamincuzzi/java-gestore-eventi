package org.experis.learning.gestore_eventi;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class Concerto extends Evento {
    // Attributi
    private LocalTime ora;
    private BigDecimal prezzo;

    // Costruttori
    public Concerto(String titolo, LocalDate data, int postiTot, LocalTime ora, BigDecimal prezzo) throws IllegalArgumentException {
        super(titolo, data, postiTot);

        validaPrezzo(prezzo);

        this.ora = ora;
        this.prezzo = prezzo;
    }

    // Metodi
        // Validatori
    private void validaPrezzo(BigDecimal prezzo) throws IllegalArgumentException {
        if (prezzo.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Prezzo invalido.");
        }
    }
}
