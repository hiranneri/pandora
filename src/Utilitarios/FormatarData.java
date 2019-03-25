package Utilitarios;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatarData {
	
	//
	public static java.sql.Date formatarDataParaSQL(java.util.Date data) {
		java.sql.Date dataSQL = new java.sql.Date(data.getTime());
		return dataSQL;
	}
	
	
	public static Time formatarHoraParaSQL(Date hora) {
		
			Time time = new Time(hora.getTime());
			return time;
	
	}
	public static Date formatarDataSQLParaDate(Date horaNoBanco) {
		SimpleDateFormat formatador = new SimpleDateFormat("dd-MM-yyyy");
		try {
			String dateString = formatador.format(horaNoBanco);
			Date h = (Date)formatador.parse(dateString);
			return h;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	
}
