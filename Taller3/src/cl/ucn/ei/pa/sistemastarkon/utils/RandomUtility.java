package cl.ucn.ei.pa.sistemastarkon.utils;
import java.util.Random;

public class RandomUtility {
    private static Random r;

    public RandomUtility(){}

    public static String obtenerCodigoAleatorio(int largo){
        int nRandom;
        String codigoR = "";
        for(int i = 0; i < largo;i++){
            r = new Random();
            nRandom = r.nextInt(9);
            codigoR = codigoR + nRandom;
        }
        return codigoR;
    }
}
