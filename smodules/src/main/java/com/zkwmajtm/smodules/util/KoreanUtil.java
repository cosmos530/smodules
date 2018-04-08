package com.zkwmajtm.smodules.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class KoreanUtil {
	public static final String yyyyMMdd = "yyyyMMdd";
	public static final String yyMMdd = "yyMMdd";
	public static final String yyyy = "yyyy";
	public static final String yy = "yy";
	public static final String MM = "MM";
	public static final String dd = "dd";
	public static final String MAN = "MAN";
	public static final String NATIVE = "NATIVE";
	public static final String FOREIGNER = "FOREIGNER";
	
	public static void main(String[] args) {
		Calendar date = Calendar.getInstance();
		
		System.out.println(getFormatedDate(yyMMdd, date));
	}
	

	public static String getYmd() {
		String result;
		
		result = "";
		
		
		return result;
	}
	
	public static String getFormatedDate(final String pattern, final Calendar date) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date.getTime());
	}
	
	public static String getSexInt(final String sex, final Calendar date, String nation) {
		int result = -1;
		int year = Integer.parseInt(getFormatedDate(yyyy, date));
		
		if(MAN.equals(sex)) {	// MAN
			if(FOREIGNER.equals(nation)) { // MAN FOREIGNER
				if(year < 2000) {
					result = 5;
				}else{
					result = 7;
				}
			}else{	// MAN NATIVE
				if(year < 1900) {
					result = 9;
				}else if(year < 2000) {
					result = 1;
				}else{
					result = 3;
				}
			}
		}else{	// WOMAN 아직 중성이 생기지 않아 else if 로 만들지 않았다.
			if(FOREIGNER.equals(nation)) { // WOMAN FOREIGNER
				if(year < 2000) {
					result = 6;
				}else{
					result = 8;
				}
			}else{	// WOMAN NATIVE
				if(year < 1900) {
					result = 0;
				}else if(year < 2000) {
					result = 2;
				}else{
					result = 4;
				}
			}
		}
		
		return String.valueOf(result);
	}
}
