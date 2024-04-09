package org.experis.learning.gestore_eventi;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Inizializzo l'oggetto Evento come null
        Evento evento = null;

        // Inizializzo lo Scanner
        Scanner eventoScan = new Scanner(System.in);

        // Invoco il metodo per creare l'evento
        evento = creaEvento(evento, eventoScan);

        // Se è stato istanziato correttamente chiedo all'utente quali operazioni eseguire sull'evento
        if (evento != null){
            operazioniEvento(evento, eventoScan);
        }

        // Chiudo lo Scanner
        eventoScan.close();
    }

    // METODI

    private static Evento creaEvento(Evento evento, Scanner eventoScan) {
        // Fintanto che l'evento non è stato creato con successo
        while(evento == null) {
            // Blocco try-with-resources
            try {
                // Ricevo gli input dell'utente
                System.out.println("======= Creazione del nuovo evento ===========\n");
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

    private static void operazioniEvento(Evento evento, Scanner inputScan) {
        boolean uscita = false;

        while (!uscita){
            System.out.println("\n======== Operazioni Evento =========\nCosa si desidera fare? (Inserire il numero associato alla scelta)");
            System.out.print("1: Prenotare - 2: Disdire - 3: Uscire ");
            int scelta = Integer.parseInt(inputScan.nextLine());
            switch (scelta){
                // Prenotazione
                case 1:
                    // Flag
                    boolean prenotazioneEffettuata = prenotazione(inputScan, evento);

                    // Fintanto che la prenotazione non viene effettuata
                    while (!prenotazioneEffettuata){
                        // Tento la prenotazione
                        prenotazioneEffettuata = prenotazione(inputScan, evento);
                    }

                    break;
                // Disdetta
                case 2:
                    // Flag
                    boolean disdettaEffettuata = disdetta(inputScan, evento);

                    // Fintanto che la disdetta non viene effettuata
                    while (!disdettaEffettuata){
                        // Tento la disdetta
                        disdettaEffettuata = disdetta(inputScan, evento);
                    }

                    break;
                // Uscita
                case 3:
                    System.out.println("Arrivederci!");
                    uscita = true;
                    break;
                // Input invalido
                default:
                    System.out.println("Scelta invalida: riprovare.");
                    break;
            }
        }
    }

    private static boolean prenotazione(Scanner inputScan, Evento evento) {
        try {
            System.out.print("Quanti posti si desidera prenotare? ");
            int postiPrenotazione = Integer.parseInt(inputScan.nextLine());
            evento.prenota(postiPrenotazione);
            infoPosti(evento);
            return true;
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());;
        }
        return false;
    }

    private static boolean disdetta(Scanner inputScan, Evento evento) {
        try {
            System.out.print("Quanti posti si desidera disdire? ");
            int posti = Integer.parseInt(inputScan.nextLine());
            evento.disdici(posti);
            infoPosti(evento);
            return true;
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());;
        }
        return false;
    }

    private static void infoPosti(Evento e){
        System.out.println("------------------\n === Info posti ===\nPosti prenotati: " + e.getPostiPrenotati() + "\nPosti disponibili: " + e.postiDisponibili() + "\n---------------");
    }
}
