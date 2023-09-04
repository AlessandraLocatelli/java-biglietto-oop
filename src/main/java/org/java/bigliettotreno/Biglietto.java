package org.java.bigliettotreno;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class Biglietto {

private int km;
private int etaPasseggero;

private LocalDate data;

private boolean flessibile;

private final static BigDecimal PREZZO_PER_KM = BigDecimal.valueOf(0.21);
private final static BigDecimal SCONTO_OVER_65 = BigDecimal.valueOf(0.40);
private final static BigDecimal SCONTO_UNDER_18 = BigDecimal.valueOf(0.20);

private final static int DURATA_NORMALE = 30;
public final static int DURATA_FLESSIBILE = 90;

public Biglietto(int km, int etaPasseggero, LocalDate data, boolean flessibile)
{

    this.km = isValidKm(km);
    this.etaPasseggero = isValidEta(etaPasseggero);
    this.data = isValidData(data);
    this.flessibile = flessibile;

}


public int getKm()
{
    return km;
}

public int getEtaPasseggero() {
        return etaPasseggero;
}

    public LocalDate getData() {
        return data;
    }

    public boolean isFlessibile() {
        return flessibile;
    }

    public BigDecimal calcolaPrezzo()
    {
        BigDecimal prezzoConEventualeSconto = calcolaSconto();
        BigDecimal prezzoFinale = prezzoConEventualeSconto;

        LocalDate dataScadenza = calcolaDataScadenza();

        if(dataScadenza.isEqual(data.plusDays(DURATA_FLESSIBILE)))
        {
            BigDecimal aumentoPrezzo = BigDecimal.valueOf(0.10);
            BigDecimal prezzoConAumento = prezzoConEventualeSconto.multiply(aumentoPrezzo);
            prezzoFinale = prezzoConEventualeSconto.add(prezzoConAumento);

        }


        return prezzoFinale;

    }




private int isValidKm (int km) throws IllegalArgumentException
{
   if(km < 0)
   {

       throw  new IllegalArgumentException("Inserisci un numero positivo per i Km.");

   }

   return km;

}

private int isValidEta(int etaPasseggero) throws IllegalArgumentException
{

    if(etaPasseggero < 0 || etaPasseggero > 130)
    {
        throw new IllegalArgumentException("Inserisci un'età tra 0-130 anni.");
    }

    return etaPasseggero;

}


private LocalDate isValidData(LocalDate data) throws InvalidDateException
{
    if(data.isBefore(LocalDate.now()))
    {

        throw new InvalidDateException("Il treno è già partito!");

    }

    return data;
}




private LocalDate calcolaDataScadenza()
{
    LocalDate dataScadenza = null;

    if(flessibile)
    {
        dataScadenza = data.plusDays(DURATA_FLESSIBILE);

    }
    else
    {
        dataScadenza =  data.plusDays(DURATA_NORMALE);

    }


    return dataScadenza;


}



private BigDecimal calcolaSconto() {

    BigDecimal prezzoConEventualeSconto = BigDecimal.valueOf(km).multiply(PREZZO_PER_KM);;

    if (etaPasseggero < 18)
    {
        prezzoConEventualeSconto = prezzoConEventualeSconto.subtract(prezzoConEventualeSconto.multiply(SCONTO_UNDER_18));

    } else if (etaPasseggero > 65)
    {
        prezzoConEventualeSconto = prezzoConEventualeSconto.subtract(prezzoConEventualeSconto.multiply(SCONTO_OVER_65));
    }

    return prezzoConEventualeSconto;

}





public String toString()
{

  return "Dati Biglietto { "+
          " Km: "+km+
          " Età: "+etaPasseggero+
          " Prezzo: "+ calcolaPrezzo().setScale(2, RoundingMode.HALF_EVEN)+" euro."
          +'}';


}



}
