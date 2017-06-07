package pe.com.bbva.visitame.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class DateUtil {
	
	public static final String LOCALE_NAME = "es";
	public static final String COUNTRY_NAME = "PE";
	public static final String CITY_NAME = "America/Lima";
	public static final String FORMAT_DATE = "dd/MM/yyyy";
	public static final String FORMAT_DATE_HOUR = "dd/MM/yyyy hh:mm:ss aaa";
	public static final String FORMAT_DATE_HOUR24 = "dd/MM/yyyy HH:mm:ss";
	public static final String FORMAT_DATE_2 = "yyyy-MM-dd";
	public static final String FORMAT_DATE_3 = "ddMMyyyy";
	public static final String FORMAT_MES = "MMMM";
	public static final String FORMAT_ANIO = "yyyy";
	public static final String FORMAT_INTER = "yyyyMMdd";
	public static final String FORMAT_GUION_MES_ANIO = "MMMM '-' yyyy";
	private static final String FORMATO_24_HORA = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
	
	public static final DateFormat DF_FECHA = new SimpleDateFormat(FORMAT_DATE, new Locale(LOCALE_NAME));
	public static final DateFormat DF_FECHA_HORA = new SimpleDateFormat(FORMAT_DATE_HOUR, new Locale(LOCALE_NAME));
	public static final DateFormat DF_FECHA_HORA24 = new SimpleDateFormat(FORMAT_DATE_HOUR24, new Locale(LOCALE_NAME));
	
    public static String getFechaActual() {
        return getFecha(new Date(), FORMAT_DATE);
    }
    
    public static Date parseDate(String date,String format){
    	if(StringUtils.isBlank(date)) return null;
    	SimpleDateFormat formateador = new SimpleDateFormat(format,new Locale(LOCALE_NAME));
    	try {
			return formateador.parse(date);
		} catch (ParseException e) {
			return null;
		}
    }
    public static Date parseDateFormat(String date,String format) throws Exception{
    	if(date==null) return null;
		SimpleDateFormat formateador = new SimpleDateFormat(format,new Locale(LOCALE_NAME));
		return formateador.parse(date);
    }
    
    public static Date parseDate(String date){
    	return parseDate(date, FORMAT_DATE);
    }
    
    public static Date parseDate2(String date){
    	return parseDate(date, FORMAT_DATE_2);
    }
    
    public static String getHoraFormat(Date ahora) {
    	if(ahora==null) return "";
        SimpleDateFormat formateador = new SimpleDateFormat("HH:mm:ss",new Locale(LOCALE_NAME));
        return formateador.format(ahora);
    }
    
    public static String getHoraMinutoFormat(Date ahora) {
    	if(ahora==null) return "";
        SimpleDateFormat formateador = new SimpleDateFormat("HH:mm",new Locale(LOCALE_NAME));
        return formateador.format(ahora);
    }
    
    public static String getFechaFormat(Date ahora) {
    	return getFechaFormat(ahora,FORMAT_DATE);
    }
    
    public static String getFechaFormat(Date ahora,String format) {
    	if(ahora==null) return  "";
        SimpleDateFormat formateador = new SimpleDateFormat(format,new Locale(LOCALE_NAME));
        return formateador.format(ahora);
    }
    
    public static String getFechaHoraFormat(Date ahora) {
    	try{
    		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy HH:mm", new Locale(LOCALE_NAME));
    		return formateador.format(ahora);
        }catch (Exception e) {
			return "";
		}
    }
    
    public static String getFecha(Date date){
        return getFecha(date,DF_FECHA);
    }
    
    public static String getFecha2(Date date){
        return getFecha(date, FORMAT_DATE_2);
    }
    
    public static String getFecha(Date date, String format){
    	SimpleDateFormat sdfPeru = new SimpleDateFormat(format, new Locale(LOCALE_NAME, COUNTRY_NAME));
    	return getFecha(date, sdfPeru);
    }
    
    public static String getFechaZona(Date date, String format){
    	SimpleDateFormat sdfPeru = new SimpleDateFormat(format, new Locale(LOCALE_NAME, COUNTRY_NAME));
    	sdfPeru.setTimeZone(TimeZone.getTimeZone(CITY_NAME));
    	return getFecha(date, sdfPeru);
    }
    
    public static String getFecha(Date date, DateFormat formateador){
    	try{
    		return formateador.format(date);
        }catch(Exception e){
        	return StringUtils.EMPTY;
        }
        
    }

    /**
     * Metodo usado para obtener la hora actual del sistema
     * @return Retorna un <b>STRING</b> con la hora actual formato "hh:mm:ss"
     * 
     * */
    public static String getHoraActual() {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("hh:mm:ss",new Locale(LOCALE_NAME));
        return formateador.format(ahora);
    }

    /**
     * Sumarle dias a una fecha determinada
     * @param fch La fecha para sumarle los dias
     * @param dias Numero de dias a agregar
     * @return La fecha agregando los dias
     * 
     * */
    public static java.sql.Date sumarFechasDias(java.sql.Date fch, int dias) {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(fch.getTime());
        cal.add(Calendar.DATE, dias);
        return new java.sql.Date(cal.getTimeInMillis());
    }

    /**
     * Restarle dias a una fecha determinada
     * @param fch La fecha
     * @param dias Dias a restar
     * @return La fecha restando los dias
     * 
     * */
    public static synchronized java.sql.Date restarFechasDias(java.sql.Date fch, int dias) {
        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(fch.getTime());
        cal.add(Calendar.DATE, -dias);
        return new java.sql.Date(cal.getTimeInMillis());
    }

    /**
     * Diferencias entre dos fechas
     * @param fechaInicial La fecha de inicio
     * @param fechaFinal  La fecha de fin
     * @return Retorna el numero de dias entre dos fechas
     * */
    public static int diferenciasDeFechasDia(Date fechaInicial, Date fechaFinal) {

        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM,new Locale(LOCALE_NAME));
        String fechaInicioString = df.format(fechaInicial);
        try {
            fechaInicial = df.parse(fechaInicioString);
        } catch (ParseException ex) {
        }

        String fechaFinalString = df.format(fechaFinal);
        try {
            fechaFinal = df.parse(fechaFinalString);
        } catch (ParseException ex) {
        }

        long fechaInicialMs = fechaInicial.getTime();
        long fechaFinalMs = fechaFinal.getTime();
        long diferencia = fechaFinalMs - fechaInicialMs;
        double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
        return ((int) dias);
    }
    
    /**
     * Diferencias entre dos fechas
     * @param fechaInicial La fecha de inicio
     * @param fechaFinal  La fecha de fin
     * @return Retorna el numero de dias entre dos fechas
     * 
     * */
    public static synchronized int diferenciasDeFechasHoras(Date fechaInicial, Date fechaFinal) {
        long fechaInicialMs = fechaInicial.getTime();
        long fechaFinalMs = fechaFinal.getTime();
        long diferencia = fechaFinalMs - fechaInicialMs;
        double minutos = Math.floor(diferencia / (1000 * 60*60 ));
        return ((int) minutos);
    }
    
    /**
     * Diferencias entre dos fechas
     * @param fechaInicial La fecha de inicio
     * @param fechaFinal  La fecha de fin
     * @return Retorna el numero de dias entre dos fechas
     * 
     * */
    public static synchronized int diferenciasDeFechasHorasMinutos(Date fechaInicial, Date fechaFinal) {

    	 long fechaInicialMs = fechaInicial.getTime();
         long fechaFinalMs = fechaFinal.getTime();
         long diferencia = fechaFinalMs - fechaInicialMs;
         double minutos = Math.floor(diferencia / (1000 * 60 ));
         return ((int) minutos);
    }
    
    public static synchronized int diferenciasDeFechasHorasSegundos(Date fechaInicial, Date fechaFinal) {
   		long fechaInicialMs = fechaInicial.getTime();
        long fechaFinalMs = fechaFinal.getTime();
        long diferencia = fechaFinalMs - fechaInicialMs;
        double segundos = Math.round(diferencia / 1000);
        return ((int) segundos);
	}
    
    public static synchronized long diferenciasDeFechasHorasMilisegundos(Date fechaInicial, Date fechaFinal) {
   		long fechaInicialMs = fechaInicial.getTime();
        long fechaFinalMs = fechaFinal.getTime();
        long milisegundos = fechaFinalMs - fechaInicialMs;
        return milisegundos;
	}

    /**
     * Devuele un java.util.Date desde un String en formato dd/MM/yyyy
     * @param La fecha a convertir a formato date
     * @return Retorna la fecha en formato Date
     * 
     * */
    public static synchronized java.util.Date stringToDate(String fecha) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat(FORMAT_DATE,new Locale(LOCALE_NAME));
        Date fechaEnviar = null;
        try {
            fechaEnviar = formatoDelTexto.parse(fecha);
            return fechaEnviar;
        } catch (ParseException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    /**
     * Devuele un String[] desde un numero de minutos
     * @param El nroMinutos a convertir a formato Array
     * @return Retorna un array [0]=Dia,[1]=Hora,[2]=Minuto
     * 
     * */
    public static String[] getDiaHoraMinuto(int nroMinutos) {
    	int dia = 0;int hora = 0;
        String[] aux = new String[3];
        while (nroMinutos >= 1440) {
        	nroMinutos -= 1440;
        	dia++;
        }
        while (nroMinutos >= 60) {
        	nroMinutos -= 60;
        	hora++;
        }
        if (dia < 10) aux[0] = ("0" + dia); else {
        	aux[0] = (dia + "");
        }
        if (hora < 10) aux[1] = ("0" + hora); else {
        	aux[1] = (hora + "");
        }
        if (nroMinutos < 10) aux[2] = ("0" + nroMinutos); else {
        	aux[2] = (nroMinutos + "");
        }
        return aux;
    }
    
    public static Date getPrimerDiaDelMes() {
    	return getPrimerDiaDelMes(new Date());
    }
    
    public static Date getUltimoDiaDelMes() {
    	return getUltimoDiaDelMes(new Date());
    }
    
    public static Date getPrimerDiaDelMes(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), 
                cal.get(Calendar.MONTH),
                cal.getActualMinimum(Calendar.DAY_OF_MONTH),
                cal.getMinimum(Calendar.HOUR_OF_DAY),
                cal.getMinimum(Calendar.MINUTE),
                cal.getMinimum(Calendar.SECOND));
        return cal.getTime();
    }
    
    public static Date getUltimoDiaDelMes(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.getActualMaximum(Calendar.DAY_OF_MONTH),
                cal.getMaximum(Calendar.HOUR_OF_DAY),
                cal.getMaximum(Calendar.MINUTE),
                cal.getMaximum(Calendar.SECOND));
        return cal.getTime();
    }

	public static boolean fechasIguales(Date fecha1, Date fecha2) {
		
		return getFecha(fecha1,"yyyyMMdd").equals(getFecha(fecha2,"yyyyMMdd"));
	}
	public static boolean exiteFecha(Date fechaInicio,Date fechaFin, Date fechaBusqueda){
		Integer fechaInicioInt		=Integer.parseInt(getFecha(fechaInicio, "yyyyMMdd"));
		Integer fechaFinInt			=Integer.parseInt(getFecha(fechaFin, "yyyyMMdd"));
		Integer fechaBusquedaInt	=Integer.parseInt(getFecha(fechaBusqueda, "yyyyMMdd"));
		
		return (fechaInicioInt<=fechaBusquedaInt && fechaFinInt>=fechaBusquedaInt);
	}
	
	public static int getMinutos(String nroMinutosInicio) {
		String tiempo[]=nroMinutosInicio.split(":");
		return Integer.parseInt(tiempo[0])*60+Integer.parseInt(tiempo[1]);
	}

	public static String getHorario(double nroMinutosFin) {
		String[] etiqueta=getDiaHoraMinuto((int)nroMinutosFin);
		return etiqueta[1]+":"+etiqueta[2];
	}
	
	public static Date getInicioDia(Date date){
		String fecha=DateUtil.getFecha(date,FORMAT_DATE);
		return DateUtil.parseDate(fecha + " 05:00:00 000","dd/MM/yyyy HH:mm:ss SSS");
	}
	
	public static Date getInicioDia2(Date date){
		String fecha=DateUtil.getFecha(date,FORMAT_DATE);
		return DateUtil.parseDate(fecha + " 00:00:00 000","dd/MM/yyyy HH:mm:ss SSS");
	}
	
	public static Date getInicioManiana(Date date){
		String fecha=DateUtil.getFecha(date,FORMAT_DATE);
		return DateUtil.parseDate(fecha+" 08:01:00 000","dd/MM/yyyy HH:mm:ss SSS");
	}
	
	public static Date getFinDia(Date date){
		String fecha=DateUtil.getFecha(date,FORMAT_DATE);
		return DateUtil.parseDate(fecha+" 23:59:59 999","dd/MM/yyyy HH:mm:ss SSS");		
	}
	
	public static Date getTopDia(Date date){
		String fecha=DateUtil.getFecha(date,FORMAT_DATE);
		return DateUtil.parseDate(fecha+" 23:45:00 000","dd/MM/yyyy HH:mm:ss SSS");
		
	}
		
	public static String nombreMes(Date date){
		String mesNombre = new SimpleDateFormat(FORMAT_MES, new Locale(LOCALE_NAME, COUNTRY_NAME)).format(date);
		String anioNombre = new SimpleDateFormat(FORMAT_ANIO, new Locale(LOCALE_NAME, COUNTRY_NAME)).format(date);
		return mesNombre.toUpperCase() + " " + anioNombre;
	}
	
	public static String nombreMesCorto(Date date){
		String mesNombre = new SimpleDateFormat(FORMAT_MES, new Locale(LOCALE_NAME, COUNTRY_NAME)).format(date);		
		return mesNombre.toUpperCase().substring(0, 3);
	}
	
	public static String anio(Date date){
		return new SimpleDateFormat(FORMAT_ANIO, new Locale(LOCALE_NAME, COUNTRY_NAME)).format(date);				
	}
	
	public static Date validarFechaActual(Date fechaSeleccion){
		Date fechaActual=new Date();
		if(fechaActual.before(fechaSeleccion)){
			return null;
		}else{
			return fechaSeleccion;
		}
	}
	
	public static Date sumarHora(Date date, String format, int cantidadHora){
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.HOUR, cantidadHora);
		String fecha=DateUtil.getFecha(cal.getTime(),format);
		return DateUtil.parseDate(fecha,format);
	}
	
	public static Date sumarMinuto(Date date, String format, int cantidadMinutos){
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, cantidadMinutos);
		String fecha=DateUtil.getFecha(cal.getTime(),format);
		return DateUtil.parseDate(fecha,format);
	}
	 
	public static Date restarFechasDias(Date fch, int dias) {
		Calendar cal = new GregorianCalendar();
	    cal.setTimeInMillis(fch.getTime());
	    cal.add(Calendar.DATE, -dias);
	    return cal.getTime();
	}
	
	public static Date horaInicio(){
		Calendar horaActual= new GregorianCalendar();
		horaActual.set(Calendar.MINUTE, 0);
		horaActual.add(Calendar.HOUR, 1);
		return horaActual.getTime();
	}
	
	public static Date horaFin(){
		Calendar horaActual= new GregorianCalendar(); 		
		horaActual.set(Calendar.MINUTE, 30);
		horaActual.add(Calendar.HOUR, 1);
		return horaActual.getTime();
	}
	
	public static int obtenerDiaSemana(Date fecha){
		Calendar cal= Calendar.getInstance(new Locale(LOCALE_NAME, COUNTRY_NAME));
		cal.setTime(fecha);
		return cal.get(Calendar.DAY_OF_WEEK);
	}
	
	public static boolean validaFormatoHora(String hora){
		Pattern pattern = Pattern.compile(FORMATO_24_HORA);
		Matcher matcher = pattern.matcher(hora);
		return matcher.matches();
	}
	
	public static Date sumarSegundo(Date date, int cantidadMilisegundo){
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.SECOND, cantidadMilisegundo);
		return cal.getTime();
	}
	
	public static Date sumarMes(Date date, String format, int cantidaMeses){
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.MONTH, cantidaMeses);
		String fecha=DateUtil.getFecha(cal.getTime(), format);
		return DateUtil.parseDate(fecha, format);
	}
	
	public static Date getFechaSinSegundo(Date fecha){
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(fecha);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}
	
	public static Date sumarAnio(Date date, int cantidadAnio) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, cantidadAnio);
		return calendar.getTime();
	}
	public static int calculateAge(Date bornDate) {
		   Calendar cal = Calendar.getInstance(); // current date
		   int currYear = cal.get(Calendar.YEAR);
		   int currMonth = cal.get(Calendar.MONTH);
		   int currDay = cal.get(Calendar.DAY_OF_MONTH);
		   cal.setTime(bornDate); // now born date
		   int years = currYear - cal.get(Calendar.YEAR);
		   int bornMonth = cal.get(Calendar.MONTH);
		   if (bornMonth == currMonth) { // same month
		    return cal.get(Calendar.DAY_OF_MONTH) <= currDay ? years
		      : years - 1;
		   } else {
		    return bornMonth < currMonth ? years - 1 : years;
		   }
		  }
}