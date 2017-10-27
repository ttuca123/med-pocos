package br.com.med.pocos.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DataUtils {

	
	public static Date converterDataTimeZone() {
		
		SimpleDateFormat dateFormatGmt = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT-3"));

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
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT-3"));

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
	
}
