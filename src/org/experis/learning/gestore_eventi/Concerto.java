package org.experis.learning.gestore_eventi;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

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
        // Getter e Setter
    @Override
    public LocalDate getData() {
        return super.getData();
    }

    public LocalTime getOra() {
        return ora;
    }

    public void setOra(LocalTime ora) {
        this.ora = ora;
    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        validaPrezzo(prezzo);
        this.prezzo = prezzo;
    }
        //------------//

        // Metodi di servizio
            // Validatori
    private void validaPrezzo(BigDecimal prezzo) throws IllegalArgumentException {
        if (prezzo.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("Prezzo invalido: inserire un numero positivo.");
        }
    }
            //--------------//

    @Override
    protected String formattaData(LocalDate data) {
        return super.formattaData(data) + formattaOra(this.ora);
    }

    private String formattaOra(LocalTime ora){
        return ora.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.valueOf("HH:mm")));
    }

    private BigDecimal formattaPrezzo(BigDecimal prezzo){
        return prezzo.setScale(2, RoundingMode.HALF_DOWN);
    }

    @Override
    public String toString() {
        return formattaData(getData()) + "\t-\t" + formattaOra(getOra()) + "\t-\t" + formattaPrezzo(getPrezzo()) + "€";
    }
}
