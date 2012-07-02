package com.lotrading.softlot.util.base;

import java.math.BigDecimal;


public class UtilFunctions {

	public static double roundDecimalPlaces(double d, int decimals) {		
		BigDecimal bd = new BigDecimal(d);
		bd = bd.setScale(decimals, BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue() ;   
	  
	}
	
	public static double convertKgToLb(double number) {	
		return number * Constants.KILOGRAM_TO_POUNDS;	  
	}
	
	public static double convertLbToKg(double number) {	
		return number / Constants.KILOGRAM_TO_POUNDS;	  
	}
	
	public static double roundUpDecimalPlaces(double d, int decimals) {		
		BigDecimal bd = new BigDecimal(d);
		bd = bd.setScale(decimals, BigDecimal.ROUND_UP);
		return bd.doubleValue() ;   
	  
	}
	
}
