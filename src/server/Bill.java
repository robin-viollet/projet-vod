package server;

import java.io.Serializable;
import java.math.BigInteger;

public class Bill implements Serializable {
    public Bill(String movieName, BigInteger outrageousPrice){
        this.movieName = movieName;
        this.outrageousPrice = outrageousPrice;
    }

    String movieName;
    BigInteger outrageousPrice;

    @Override
    public String toString(){
        return String.valueOf(outrageousPrice);
    }
}
