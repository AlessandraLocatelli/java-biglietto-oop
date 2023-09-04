package org.java.bigliettotreno;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Biglietto {

private int km;
private int etaPasseggero;

private final static BigDecimal PREZZO_PER_KM = BigDecimal.valueOf(0.21);
private final static BigDecimal SCONTO_OVER_65 = BigDecimal.valueOf(0.40);
private final static BigDecimal SCONTO_UNDER_18 = BigDecimal.valueOf(0.20);

public Biglietto(int km, int etaPasseggero)
{

    this.km = isValidKm(km);
    this.etaPasseggero = isValidEta(etaPasseggero);

}


public int getKm()
{
    return km;
}

public int getEtaPasseggero() {
        return etaPasseggero;
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


public BigDecimal calcolaPrezzoFinalConEventualeSconto() {

    BigDecimal prezzoFinale = calcolaPrezzoStandard();

    if (etaPasseggero < 18)
    {
        prezzoFinale = prezzoFinale.subtract(prezzoFinale.multiply(SCONTO_UNDER_18));

    } else if (etaPasseggero > 65)
    {
        prezzoFinale = prezzoFinale.subtract(prezzoFinale.multiply(SCONTO_OVER_65));
    }

   return prezzoFinale;

}

private BigDecimal calcolaPrezzoStandard()
{


    return BigDecimal.valueOf(km).multiply(PREZZO_PER_KM);

}



public String toString()
{

  return "Dati Biglietto { "+
          " Km: "+km+
          " Età: "+etaPasseggero+
          " Prezzo: "+calcolaPrezzoFinalConEventualeSconto().setScale(2, RoundingMode.HALF_EVEN)+" euro."
          +'}';


}



}
