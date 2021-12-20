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
		int counter=0;
		String format;
		format = "-"+rut.substring(rut.length()-1);
		for(int i = rut.length()-2;i>=0;i--) {
			format = rut.substring(i, i+1)+format;
			counter++;
			if(counter == 3 && i != 0) {
				format = "."+format;	
				counter = 0;
			}
		}
		return format;
	}
	
	/** 
	 * @param rut
	 * @return String
	 */
	public static String quitarFormatoRut(String rut) {
		rut = rut.replace(".", "");
		rut = rut.replace("-", "");	
		return rut.toLowerCase();
	}
}
