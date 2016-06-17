package util;

import java.util.Calendar;
import java.util.Date;

public class Util {
	public static Date createDate (int dia, int mes, int ano) {
		Calendar c = Calendar.getInstance();
		c.set(ano, mes-1, dia);
		return c.getTime();
	}
	
	public static void main (String args[]) {
		System.out.println(createDate(20,5+1, 1980));
	}
}
