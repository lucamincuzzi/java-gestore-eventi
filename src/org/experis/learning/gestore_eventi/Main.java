package org.experis.learning.gestore_eventi;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Inizializzo l'oggetto Evento come null
        Evento evento = null;

        // Invoco il metodo per creare l'evento
        evento = creaEvento(evento);

        // Se è stato istanziato correttamente chiedo all'utente quali operazioni eseguire sull'evento
        if (evento != null){
            operazioniEvento(evento);
        }
    }

    private static void operazioniEvento(Evento evento) {
        boolean uscita = false;
        Scanner inputScan = new Scanner(System.in);

        while (!uscita){
            System.out.println("Cosa si desidera fare? (Inserire il numero associato alla scelta)");
            System.out.print("1: Prenotare - 2: Disdire - 3: Uscire");
            int scelta = Integer.parseInt(inputScan.nextLine());
            switch (scelta){
                case 1:
                    if (prenotazione(inputScan, evento)) break;
                case 2:
                    if (disdetta(inputScan, evento)) break;
                case 3:
                    System.out.println("Arrivederci!");
                    uscita = true;
                    break;
                default:
                    System.out.println("Scelta invalida: riprovare.");
                    break;
            }
        }
        inputScan.close();
    }

    private static boolean disdetta(Scanner inputScan, Evento evento) {
        try {
            System.out.print("Quanti posti si desidera disdire?");
            int posti = Integer.parseInt(inputScan.nextLine());
            evento.disdici(posti);
            return true;
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());;
        }
        return false;
    }

    private static boolean prenotazione(Scanner inputScan, Evento evento) {
        try {
            System.out.print("Quanti posti si desidera prenotare?");
            int postiPrenotazione = Integer.parseInt(inputScan.nextLine());
            evento.prenota(postiPrenotazione);
            return true;
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());;
        }
        return false;
    }

    private static Evento creaEvento(Evento evento) {
        // Fintanto che l'evento non è stato creato con successo
        while(evento == null) {
            // Blocco try-with-resources
            try (Scanner eventoScan = new Scanner(System.in)) {
                // Ricevo gli input dell'utente
                System.out.println("Creazione del nuovo evento:");
                System.out.print("Titolo: ");
                String titolo = eventoScan.nextLine();
                System.out.print("Data - inserire anno: ");
                int anno = Integer.parseInt(eventoScan.nextLine());
                System.out.print("Data - inserire mese: ");
                int mese = Integer.parseInt(eventoScan.nextLine());
                System.out.print("Data - inserire giorno: ");
                int giorno = Integer.parseInt(eventoScan.nextLine());
                LocalDate data = LocalDate.of(anno, mese, giorno);
                System.out.print("Inserire il numero di posti totale: ");
                int nPostiTot = Integer.parseInt(eventoScan.nextLine());

                // Invocazione del costruttore
                evento = new Evento(titolo, data, nPostiTot);
            } catch (IllegalArgumentException e) { // Blocco catch
                System.err.println(e.getMessage());
            }
        }

        // Ritorno l'oggetto Evento istanziato con successo
        return evento;
    }
}
