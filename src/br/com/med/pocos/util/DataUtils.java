package br.com.med.pocos.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import br.com.med.pocos.enu.EnumMes;

public class DataUtils {

	
	private static final String TIME_ZONE = "GMT-3";
	
	public static Date converterDataTimeZone() {
		
		SimpleDateFormat dateFormatGmt = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));

		//Local time zone   
		SimpleDateFormat dateFormatLocal = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");

		//Time in GMT		
		Date data = null;
		
		try {
			
			
			data = dateFormatLocal.parse( dateFormatGmt.format(new Date()));		
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
	}
	
	public static Date converterDataTimeZone(Date data) {
		
		SimpleDateFormat dateFormatGmt = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));

		//Local time zone   
		SimpleDateFormat dateFormatLocal = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");

		//Time in GMT		
		Date dataFormatada = null;
		
		try {
			
			
			dataFormatada = dateFormatLocal.parse( dateFormatGmt.format(dataFormatada));		
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dataFormatada;
	}
	
	
	public static int qtdDiasMes(EnumMes enuMes, String ano) {		
		
		Calendar data = new GregorianCalendar();
		
		data.set(Calendar.YEAR, Integer.parseInt(ano));
		
		data.set(Calendar.MONTH, enuMes.getCodigo());
		
		int totalDias = data.getActualMaximum (Calendar.DAY_OF_MONTH);
		
		return totalDias;
		
	}
	
}
