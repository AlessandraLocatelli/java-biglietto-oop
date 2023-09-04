package org.java.bigliettotreno;

import java.util.Scanner;

public class Biglietteria {


    public static void main(String[] args) {

        Biglietto biglietto = null;
        Scanner sc = new Scanner(System.in);

        while(biglietto == null){
        System.out.println("Inserisci numero km da percorrere: ");
        int kmInput = Integer.parseInt(sc.nextLine());
        System.out.println("Inserisci età passeggero: ");
        int etaInput = Integer.parseInt(sc.nextLine());

        try
        {
            biglietto = new Biglietto(kmInput,etaInput);
            System.out.println(biglietto);

        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());

        }

         }


    }




}
