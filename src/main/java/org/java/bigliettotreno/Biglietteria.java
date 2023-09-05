package org.java.bigliettotreno;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Biglietteria {


    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/treni";
        String user = "root";
        String password = "Root_root26.";

        Scanner sc = new Scanner(System.in);
        System.out.println("Inserisci stazione di partenza: ");
        String cerca = sc.nextLine();
        System.out.println("Cerco treni che partono da "+cerca);

        try(Connection con = DriverManager.getConnection(url,user,password)){

            System.out.println(con.getCatalog());

         String sql = "SELECT * FROM db_tratte WHERE partenza like ?";

         try(PreparedStatement ps = con.prepareStatement(sql)){

          ps.setString(1, "%"+cerca+"%");

         try(ResultSet rs = ps.executeQuery()){

             while(rs.next())
             {

               String stazionePartenza = rs.getString("partenza");
               String stazioneArrivo = rs.getString("arrivo");
               String km = rs.getString("Km");
               System.out.println("Partenza: "+stazionePartenza+"\n"
               +"Arrivo: "+stazioneArrivo+"\n"+"Km: "+km);

             }



         }


         }

        }
        catch(SQLException e)
        {

            System.out.println("Errore di connessione al database.");

        }



        Biglietto biglietto = null;


        while(biglietto == null){

            try
        {
            System.out.println("Inserisci numero km da percorrere in base alla destinazione scelta: ");
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
