package org.java.bigliettotreno;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Biglietteria {


    public static void main(String[] args) {

        Biglietto biglietto = null;
        Scanner sc = new Scanner(System.in);

        while(biglietto == null){

            try
        {
            System.out.println("Inserisci numero km da percorrere: ");
            int kmInput = Integer.parseInt(sc.nextLine());
            System.out.println("Inserisci età passeggero: ");
            int etaInput = Integer.parseInt(sc.nextLine());
            System.out.println("Inserisci data (yyyy-mm-dd): ");
            LocalDate dataInput = LocalDate.parse(sc.nextLine());
            System.out.println("Il biglietto è di durata flessibile? (s/n) (flessibile = 90 giorni, normale = 30 giorni). ");
            String flessibileStringa = sc.nextLine();
            boolean flessibile = false;

            if(flessibileStringa.equalsIgnoreCase("s"))
            {
                flessibile = true;
            }

            biglietto = new Biglietto(kmInput,etaInput,dataInput,flessibile);
            System.out.println(biglietto);


        }
            catch (NumberFormatException e)
            {
                System.out.println("Inserisci dei numeri interi.");

            }

            catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());

        }
            catch (DateTimeParseException e)
            {

                System.out.println("Formato data corretto: (yyyy-mm-dd)");

            }
            catch (InvalidDateException e)
            {
                System.out.println(e.getMessage());

            }

         }


    }




}
