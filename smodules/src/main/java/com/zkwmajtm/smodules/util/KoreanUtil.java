package com.zkwmajtm.smodules.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class KoreanUtil {
	public static final int[] digit = { 2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5 };
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
		System.out.println(getSeperateSSN(createSSN(MAN, NATIVE, 33)));
	}

	public static String createSSN(String sex, String nation, int age) {
		String result = "";

		Calendar date = Calendar.getInstance();
		date = getCalculateDate(date, age);
		result += getFormatedDate(yyMMdd, date);
		result += getSexInt(date, sex, nation);
		result += generateIntString(5);
		result += generateDigit(result);

		return result;
	}

	public static String getFormatedDate(final String pattern, final Calendar date) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date.getTime());
	}

	public static String getSexInt(final Calendar date, final String sex, String nation) {
		int result = -1;
		int year = Integer.parseInt(getFormatedDate(yyyy, date));

		if (MAN.equals(sex)) { // MAN
			if (FOREIGNER.equals(nation)) { // MAN FOREIGNER
				if (year < 2000) {
					result = 5;
				} else {
					result = 7;
				}
			} else { // MAN NATIVE
				if (year < 1900) {
					result = 9;
				} else if (year < 2000) {
					result = 1;
				} else {
					result = 3;
				}
			}
		} else { // WOMAN 아직 중성이 생기지 않아 else if 로 만들지 않았다.
			if (FOREIGNER.equals(nation)) { // WOMAN FOREIGNER
				if (year < 2000) {
					result = 6;
				} else {
					result = 8;
				}
			} else { // WOMAN NATIVE
				if (year < 1900) {
					result = 0;
				} else if (year < 2000) {
					result = 2;
				} else {
					result = 4;
				}
			}
		}

		return String.valueOf(result);
	}

	public static Calendar getCalculateDate(final Calendar date, int age) {
		Calendar result = (Calendar) date.clone();
		result.add(Calendar.YEAR, -age);
		return result;
	}

	public static String generateIntString(int value) {
		String result = "";

		for (int i = 0; i < value; i++) {
			result += String.valueOf((int) Math.ceil(Math.random() * 9));
		}

		return result;
	}

	public static String generateDigit(String value) {
		int[] intArray = new int[12];
		for (int i = 0; i < value.length(); i++) {
			intArray[i] = Integer.valueOf(value.substring(i, i + 1));
		}
		int result = 0;

		for (int i = 0; i < value.length(); i++) {
			result += intArray[i] + digit[i];
		}

		return String.valueOf((11 - (result % 11)) % 10);
	}
	
	public static String getSeperateSSN(String value) {
		String result = value.substring(0, 6);
		result += "-";
		result += value.substring(6,13);
		return result;
	}
}
