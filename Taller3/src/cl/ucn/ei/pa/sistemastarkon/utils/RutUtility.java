package cl.ucn.ei.pa.sistemastarkon.utils;

public class RutUtility {
    
    public RutUtility(){}

	/**
	 * This subprogram is in charge of adding points and hyphens to the Rut,
	 * at the time of overwriting the txt files.
	 * @param rut rut to format.
	 * @return rut with points and a dash. 
	 */
	public static String formatearRut(String rut) {
		int cont=0;
        String formato;
        if(rut.length() == 0){
            return "";
        }else{
            rut = rut.replace(".", "");
            rut = rut.replace("-", "");
            formato = "-"+rut.substring(rut.length()-1);
            for(int i = rut.length()-2;i>=0;i--){
                formato = rut.substring(i, i+1)+formato;
                cont++;
                if(cont == 3 && i != 0){
                    formato = "."+formato;
                    cont = 0;
                }
            }
            return formato;
        }
	}
	
	/** 
	 * @param rut
	 * @return String
	 */
	public static String quitarFormatoRut(String rut) {
		String RUTFormateado = rut.replaceAll("\\p{Punct}", "");
        String RUTFormateado2 = RUTFormateado.toLowerCase();
        return RUTFormateado2;
	}
}
