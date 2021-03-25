package com.yfl.util;


import com.yfl.util.helper.Dates;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

public class ToolUtil {

	private static String[] week = new String[] { "周一", "周二", "周三", "周四", "周五",
			"周六", "周日" };
	// 地球平均半径 double // 地球平均半径6371.004km
	private static final double EARTH_RADIUS = 6371004;

	private static final String SHORTURL_STR = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String NUMBERS = "0123456789";
	
	// 转换为酒吧时间，头一天12点到今天12点
	public static Date convertBarDay(Date date){
		
		return Dates.from(date).hour(-12).beginOf('d').asDate();
	}
	
	// 是否为酒吧的同一天
	public static boolean isInOneBarDay(Date d1,Date d2){
		
		if(d1 == null || d2 == null){
			
			return false;
		}
		
		return convertBarDay(d1).equals(convertBarDay(d2));
	}
	
	/**
	 * 随机生成6位数字的校验码 function:
	 * 
	 * @return
	 * @since JDK 1.6
	 */
	public static final String randomSmsCode() {

		Random random = new Random();
		StringBuffer sbf = new StringBuffer();
		for (int i = 0; i < 6; i++) {
			String rand = String.valueOf(String.valueOf((random.nextInt(10))));
			sbf.append(rand);
		}

		return sbf.toString();
	}

	public static final boolean locationEmpty(String location){
		
		if(location == null || location.isEmpty()){
			
			return true;
		}
		
		double d = doubleVal(location);
		
		if(d > 0){
			
			return false;
		}
		
		return true;
	}
	
	/**
	 * 获取星期几 周一即为1
	 * 
	 * @param date
	 * @return
	 */

	public static final String getBeforeThreeMonthDateStr(Date nowDate,
			String dateFormat) {

		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		long nowDateTimeCount = nowDate.getTime();
		long oldDateTimeCount = nowDateTimeCount - (3 * 30 * 24 * 3600 * 1000);
		return sdf.format(new Date(oldDateTimeCount));
	}

	public static int getWeedId(String date) {

		if (date == null) {

			return -1;
		}

		String[] data = null;
		if (date.length() == 8) {// 北京竞彩接口 返回day 格式yyyymmdd

			data = new String[] { date.substring(0, 4), date.substring(4, 6),
					date.substring(6, 8) };
		} else {// 天津竞彩接口 返回day 格式yyyy-mm-dd
			data = date.split("-");
		}
		if (date.length() < 3) {

			return -1;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, Integer.parseInt(data[0]));// 年份
		calendar.set(Calendar.MONTH, Integer.parseInt(data[1]) - 1);// 月 1月为0
		calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(data[2]));// 日
		// 日历中1代表周日
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		// 周日
		if (dayOfWeek == 1) {

			return 7;
		} else {

			return dayOfWeek - 1;
		}
	}

	// 获取月份的第一天
	public static Date getMonthStart(Date date) {

		return getDate(getDateStr(date, "yyyy-MM"), "yyyy-MM");
	}
	
	/**
	 * 
	 * @param week
	 * @return
	 */
	public static final String getWeekStr(int weekId) {
		if (weekId < 1 || weekId > 7) {
			return null;
		}
		return week[weekId - 1];
	}
	
	public static final String randomStr(int len) {

		StringBuffer buf = new StringBuffer();

		Random random = new Random();

		for (int i = 0; i < len; i++) {

			buf.append(SHORTURL_STR.charAt(random.nextInt(SHORTURL_STR.length())));
		}

		return buf.toString();
	}
	
	public static final String randomNums(int len){
		StringBuilder sb = new StringBuilder();
		Random rand = new Random();
		sb.append(rand.nextInt(9)+1);
		for(int i = 1;i<len;i++){
			sb.append(NUMBERS.charAt(rand.nextInt(NUMBERS.length())));
		}
		return sb.toString();
	}

	/**
	 * 比较2个字符串，如果都是null或者都是空字符串则认为相同 function:
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 * @since JDK 1.6
	 */
	public static boolean isEqualIgnoreEmpty(String str1, String str2) {

		if (str1 == null) {

			if (str2 == null) {

				return true;
			}

			return str2.isEmpty();
		}

		if (str2 == null) {

			return str1.isEmpty();
		}

		return str1.trim().equals(str2.trim());
	}

	public static String appendNotEmpty(String s1,String s2){
		
		if(ToolUtil.hasEmptyStr(s1,s2)){
			
			return null;
		}
		
		return s1+s2;
	}
	
	/**
	 * 比较2个时间是否相同，排除空的影响 function:
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 * @since JDK 1.6
	 */
	public static boolean isEqualIgnoreEmpty(Date d1, Date d2) {

		if (d1 == null) {

			return d2 == null;
		}

		if (d2 == null) {

			return false;
		}

		return d1.getTime() == d2.getTime();
	}

	/**
	 * 格式化时间 function:
	 * 
	 * @param date
	 * @return
	 * @since JDK 1.6
	 */
	public static final String getDateStr(Date date) {
		if (date == null) {
			return null;
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return df.format(date);

	}

	public static boolean isEmptyNotTrim(String str){
		
		return str == null || str.equals("");
	}
	
	public static boolean isEmpty(String str) {

		return str == null || str.trim().equals("");
	}

	public static final Date getDate(String date, String formatPattern) {

		if (isEmpty(date)) {
			return null;
		}
		DateFormat df = new SimpleDateFormat(formatPattern);

		try {
			return df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;

	}

	public static String formatDouble(double data) {

		DecimalFormat df = new DecimalFormat("#0.00");

		return df.format(data);
	}
	
	public static String formatDouble(double data,String format){
		
		DecimalFormat df = new DecimalFormat(format);

		return df.format(data);
	}

	public static String formatLong(long data) {

		DecimalFormat df = new DecimalFormat("#0");

		return df.format(data);
	}

	public static int intVal(String num) {
		try {
			if (num == null) {
				return 0;
			}
			int value = Integer.parseInt(num.toString());
			return value;
		} catch (Exception e) {
			return 0;
		}

	}
	
	public static long longVal(String num){
		
		try {
			if (num == null) {
				return 0;
			}
			long value = Long.parseLong(num.toString());
			return value;
		} catch (Exception e) {
			return 0;
		}
	}

	public static double doubleVal(String num) {
		try {
			if (num == null) {
				return 0;
			}
			double value = Double.parseDouble(num);
			return value;
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static double add(String d1,String d2){
		
		BigDecimal b1 = new BigDecimal(d1);
		BigDecimal b2 = new BigDecimal(d2);

		return b1.add(b2).doubleValue();
	}
	
	public static boolean boolVal(String boolStr){
		
		if(isEmpty(boolStr)){
			
			return false;
		}
		
		return boolStr.toLowerCase().matches("true|1");
	}

	public static final String getDateStr(Date date, String formatPattern) {
		if (date == null) {
			return null;
		}
		DateFormat df = new SimpleDateFormat(formatPattern);

		return df.format(date);

	}

	/**
	 * 根据时间差计算新的时间 function:
	 * 
	 * @param date
	 * @param interval
	 * @return
	 * @since JDK 1.6
	 */
	public static final Date calDate(Date date, long interval) {

		if (date == null || interval == 0) {

			return date;
		}

		return new Date(date.getTime() + interval);
	}

	public static final String formatStr(String str) {

		if (str == null) {

			return "";
		}

		return str;
	}
	
	public static final String formatStr(String str,String defaultStr){
		
		if(str == null){
			
			return defaultStr;
		}
		
		return str;
	}

	/**
	 * 计算相差天数时间
	 * 
	 * @param date
	 *            "yyyy-MM-dd"格式
	 * @param day
	 * @return "yyyy-MM-dd"格式
	 */
	public static String subDayToDate(String date, Integer day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getDate(date, "yyyy-MM-dd"));
		cal.add(Calendar.DATE, day);
		return getDateStr(cal.getTime(), "yyyy-MM-dd");
	}

	/**
	 * 获取date时间之前最早和周次对应的day
	 * 
	 * @param date
	 * @param weekStr
	 *            周一 - 周日
	 * @return yyyy-MM-dd 格式
	 */
	public static String getDay(Date date, String weekStr) {
		String matchDateStr = ToolUtil.getDateStr(date, "yyyy-MM-dd");

		int weekId = ToolUtil.getWeedId(matchDateStr);

		String weekDay = ToolUtil.getWeekStr(weekId);

		while (!weekStr.equals(weekDay)) {

			matchDateStr = ToolUtil.subDayToDate(matchDateStr, -1);

			weekDay = ToolUtil.getWeekStr(ToolUtil.getWeedId(matchDateStr));
		}

		return matchDateStr;
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static final Date getDate(String date) {
		if (date == null || date.trim().equals("")) {
			return null;
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @Title: getDate2
	 * @Description: 格式2015122317的时间转换为时间(date)
	 * @param @param date
	 * @param @return 设定文件
	 * @return Date 返回类型
	 * @throws
	 */
	public static final Date getDate2(String date) {
		if (date == null || date.trim().equals("")) {
			return null;
		}
		DateFormat df = new SimpleDateFormat("yyyyMMddHH");
		try {
			return df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static final Date addDay(Date date, int addDay) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, addDay);

		return cal.getTime();
	}

	public static final Date getNextMonthStart(Date date) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(getMonthStart(date));
		cal.add(Calendar.MONTH, 1);

		return cal.getTime();
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static final String getDateYear(Date date) {

		DateFormat df = new SimpleDateFormat("yyyy");

		return df.format(date);

	}

	/**
	 * 返回yyyy-mm-dd格式day
	 * 
	 * @param day
	 *            yyyymmdd格式
	 * @return
	 */
	public static String getDay(String day) {
		return new StringBuffer().append(day.substring(0, 4)).append("-")
				.append(day.substring(4, 6)).append("-")
				.append(day.substring(6, 8)).toString();
	}

	public static final boolean isDateEqual(Date d1, Date d2) {

		if (d1 == null) {

			return d2 == null;
		}

		return d1.equals(d2);
	}
	
	public static final boolean isDateEqual(Date d1, Date d2,String formatStr) {

		String str1 = getDateStr(d1, formatStr);
		String str2 = getDateStr(d2, formatStr);
		
		return ToolUtil.isEqualIgnoreEmpty(str1, str2);
	}

	/**
	 * 
	 * function:判断两个字符串是否相同，字符串为空记为""
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 * @since JDK 1.6
	 */
	public static final boolean isStrEqual(String s1, String s2) {

		if (s1 == null) {

			return s2 == null || s2.isEmpty();
		}

		if (s2 == null) {

			return s1 == null || s1.isEmpty();
		}

		return s1.trim().equals(s2.trim());
	}

	public static final boolean hasEmptyStr(String... strs) {

		boolean result = true;

		if (strs == null) {

			return result;
		}

		for (String str : strs) {

			result = isEmpty(str);

			if (result) {

				break;
			}
		}

		return result;
	}

	/**
	 * 算地球上任意两点(经纬度)距离 function:
	 * 
	 * @param lng0
	 *            第一点经度
	 * @param lat0
	 *            第一点纬度
	 * @param lng1
	 *            第二点经度
	 * @param lat1
	 *            第二点纬度
	 * @return 返回距离 单位：米
	 * @since JDK 1.6
	 */
	public static double getDistanceHav(double lng0, double lat0, double lng1,
			double lat1) {

		// "用haversine公式计算球面两点间的距离。"
		// # 经纬度转换成弧度

		lat0 = Math.toRadians(lat0);
		lat1 = Math.toRadians(lat1);
		lng0 = Math.toRadians(lng0);
		lng1 = Math.toRadians(lng1);

		double dlng = Math.abs(lng0 - lng1);
		double dlat = Math.abs(lat0 - lat1);

		double h = hav(dlat) + Math.cos(lat0) * Math.cos(lat1) * hav(dlng);

		double distance = 2 * EARTH_RADIUS * Math.asin(Math.sqrt(h));

		return distance;
	}

	// World Geodetic System ==> Mars Geodetic System
	public static double[] transform(double wgLat, double wgLon) {

		double a = 6378245.0;
		double ee = 0.00669342162296594323;

		double mgLat = 0;
		double mgLon = 0;

		if (outOfChina(wgLat, wgLon)) {
			mgLat = wgLat;
			mgLon = wgLon;
			return new double[] { mgLat, mgLon };
		}
		double dLat = transformLat(wgLon - 105.0, wgLat - 35.0);
		double dLon = transformLon(wgLon - 105.0, wgLat - 35.0);
		double radLat = wgLat / 180.0 * Math.PI;
		double magic = Math.sin(radLat);
		magic = 1 - ee * magic * magic;
		double sqrtMagic = Math.sqrt(magic);
		dLat = (dLat * 180.0)
				/ ((a * (1 - ee)) / (magic * sqrtMagic) * Math.PI);
		dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * Math.PI);
		mgLat = wgLat + dLat;
		mgLon = wgLon + dLon;

		return new double[] { mgLat, mgLon };
	}

	// 判断坐标是否在中国
	private static boolean outOfChina(double lat, double lon) {
		if (lon < 72.004 || lon > 137.8347)
			return true;
		if (lat < 0.8293 || lat > 55.8271)
			return true;
		return false;
	}

	private static double transformLat(double x, double y) {
		double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y
				+ 0.2 * Math.sqrt(Math.abs(x));
		ret += (20.0 * Math.sin(6.0 * x * Math.PI) + 20.0 * Math.sin(2.0 * x
				* Math.PI)) * 2.0 / 3.0;
		ret += (20.0 * Math.sin(y * Math.PI) + 40.0 * Math.sin(y / 3.0
				* Math.PI)) * 2.0 / 3.0;
		ret += (160.0 * Math.sin(y / 12.0 * Math.PI) + 320 * Math.sin(y
				* Math.PI / 30.0)) * 2.0 / 3.0;
		return ret;
	}

	private static double transformLon(double x, double y) {
		double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1
				* Math.sqrt(Math.abs(x));
		ret += (20.0 * Math.sin(6.0 * x * Math.PI) + 20.0 * Math.sin(2.0 * x
				* Math.PI)) * 2.0 / 3.0;
		ret += (20.0 * Math.sin(x * Math.PI) + 40.0 * Math.sin(x / 3.0
				* Math.PI)) * 2.0 / 3.0;
		ret += (150.0 * Math.sin(x / 12.0 * Math.PI) + 300.0 * Math.sin(x
				/ 30.0 * Math.PI)) * 2.0 / 3.0;
		return ret;
	}

	private static double hav(double theta) {

		double s = Math.sin(theta / 2);

		return s * s;
	}

	/**
	 * 根据距离计算弧度 function:
	 * 
	 * @param distance
	 *            距离，米
	 * @return 经纬度相差弧度
	 * @since JDK 1.6
	 */
	public static double getDistanceDegrees(double longt1, double lat1,
			double distance) {

		// double dlat = distance / EARTH_RADIUS;
		// # 弧度转换成角度
		// return Math.toDegrees(dlat);

		double a = (180 * distance)
				/ (Math.PI * EARTH_RADIUS * Math.cos(lat1 * Math.PI / 180));

		return a;
	}

	/**
	 * d1 + d2 function:
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 * @since JDK 1.6
	 */
	public static double add(double d1, double d2) {

		BigDecimal b1 = new BigDecimal(String.valueOf(d1));
		BigDecimal b2 = new BigDecimal(String.valueOf(d2));

		return b1.add(b2).doubleValue();
	}

	/**
	 * d1 - d2 function:
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 * @since JDK 1.6
	 */
	public static double subtract(double d1, double d2) {

		BigDecimal b1 = new BigDecimal(String.valueOf(d1));
		BigDecimal b2 = new BigDecimal(String.valueOf(d2));

		return b1.subtract(b2).doubleValue();
	}

	/**
	 * d1 * d2 ,保留scale 位小数 function:
	 * 
	 * @param d1
	 * @param d2
	 * @param scale
	 * @return
	 * @since JDK 1.6
	 */
	public static double multiply(double d1, double d2, int scale) {

		BigDecimal b1 = new BigDecimal(String.valueOf(d1));
		BigDecimal b2 = new BigDecimal(String.valueOf(d2));

		return b1.multiply(b2)
				.divide(new BigDecimal(1), scale, BigDecimal.ROUND_DOWN)
				.doubleValue();
	}

	/**
	 * d1 / d2 ,保留scale 位小数 function:
	 * 
	 * @param d1
	 * @param d2
	 * @param scale
	 * @return
	 * @since JDK 1.6
	 */
	public static double divide(double d1, double d2, int scale) {

		BigDecimal b1 = new BigDecimal(String.valueOf(d1));
		BigDecimal b2 = new BigDecimal(String.valueOf(d2));

		return b1.divide(b2, scale, BigDecimal.ROUND_DOWN).doubleValue();
	}

	/**
	 * d1 * d2 function:
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 * @since JDK 1.6
	 */
	public static double multiply(double d1, double d2) {

		BigDecimal b1 = new BigDecimal(String.valueOf(d1));
		BigDecimal b2 = new BigDecimal(String.valueOf(d2));

		return b1.multiply(b2).doubleValue();
	}
	
	public static double multiply(double d1, double ...d2) {

		BigDecimal b1 = new BigDecimal(String.valueOf(d1));
		for (int i = 0; i < d2.length; i++) {
			BigDecimal b2 = new BigDecimal(String.valueOf(d2[i]));
			b1=b1.multiply(b2);
		}
		return b1.doubleValue();
	}
	

	public static String formatPhone(String phone) {

		if (phone == null) {

			return "";
		}

		if (phone.length() < 7) {

			return phone;
		}

		return phone.substring(0, 3)
				+ phone.substring(phone.length() - 4, phone.length());
	}

	public static String formatPhoneShow(String phone) {

		if (phone == null) {

			return "";
		}

		if (phone.length() < 6) {

			return phone;
		}

		return phone.substring(0, 3) + "***"
				+ phone.substring(phone.length() - 3, phone.length());
	}

	public static String getDayStart(Date date) {

		String dayStr = ToolUtil.getDateStr(date, "yyyy-MM-dd");

		return dayStr + " 00:00:00";
	}

	public static String getDayStr(Date date, int addDay) {

		Calendar cal = Calendar.getInstance();

		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		cal.add(Calendar.DATE, addDay);

		return getDateStr(cal.getTime());
	}


	public static void main(String[] args) {
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(new Date());
//		int number = 1045;
//		int hour = 0;
//		int minute = 0;
//		if (number >= 100) {
//			hour = number / 100;
//			minute = number % 100;
//		} else if (number < 100) {
//			hour = 0;
//			minute = number;
//		}
//		cal.set(Calendar.HOUR_OF_DAY, hour);
//		cal.set(Calendar.MINUTE, minute);
//		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:dd").format(cal.getTime()));
		// // 北京 北纬39.55 东经116.24
		// double lng0 = 116.46933339451;
		// // 0.001052
		// // 116.447411
		// // 116.447151
		// double lat0 = 39.938282307702;
		// // 宜昌 北纬30.42 东经111.17
		// double lng1 = 116.463288;
		// double lat1 = 39.937624;
		//
		// System.out.println(getDistanceHav(lng0, lat0, lng1, lat1));
		// // 116.447729,39.948491
		// System.out.println(getDistanceDegrees(116.446359, 39.948498, 112));
		//
		// System.out.println(MD5.getInstance().getMD516("111111cj"));
		//
		// System.out.println(formatDouble(.00));
		//
		// double[] dd = transform(lat0, lng0);
		// System.out.println(dd[0] + ":" + dd[1]);
		//
		// System.out.println(getDistanceHav(dd[1], dd[0], lng1, lat1));
		//
		// System.out.println(formatPhoneShow("13810318552"));
		//
		// System.out.println(1 - 0.8);
		// System.out.println(0.8 * 100);
		//
		// System.out.println(getDayStart(new Date()));
		// System.out.println(getDayStr(new Date(), 1));
		// System.out.println(ToolUtil.divide(12, 12, 4));
		//
		// System.out.println(SHORTURL_STR.length());
		//
		// String url = "http://www.baidu.com";
		//
		// for (int i = 0; i < 10; i++) {
		// System.out.println(getShortUrl(url));
		// }

//		System.out.println(getDate2("2015102317"));
//		
//		System.out.println(ToolUtil.hasBit(4, 2));
//		
//		boolean[] bits = ToolUtil.bits(8,4);
//		
//		System.out.println(bits);
//		
//		System.out.println(ToolUtil.bitsNum(new int[]{3}));
//		
//		System.out.println(ToolUtil.formatSecond(11161));
//		
//		Date d1 = Dates.now().hour(-5).asDate();
//		Date d2 = new Date();
//		System.out.println(isInOneBarDay(d1, d2));
//		Date weekStart = Dates.from(new Date()).firstDayOfWeek(2).beginOf('w').asDate();
//		Date date = Dates.from(new Date()).firstDayOfWeek(2).endOf('w').asDate();
//		System.out.println(ToolUtil.getDateStr(weekStart));
//		System.out.println(ToolUtil.getDateStr(date));
		System.out.println(boolVal("1"));
		System.out.println(boolVal("true"));
		System.out.println(boolVal(null));
		System.out.println(boolVal("0"));
	}

	// 格式化秒的显示  输出 时：分：秒
	public static String formatSecond(int seconds){
		
		int hour = seconds / 3600;
		int min = (seconds - hour * 3600) / 60;
		int second = seconds - hour * 3600 - 60* min;
		
		return formatInt(hour,2) + ":" + formatInt(min,2) + ":" + formatInt(second,2);
		
	}
	
	// 格式化昵称
	public static String formatNickName(String nickName) {
		// 不处理表情符号
		if(true){
			
			return nickName;
		}
		
		@SuppressWarnings("unused")
		Charset charset = Charset.forName("utf-8");

		byte[] b_text = nickName.getBytes(charset);

		for (int i = 0; i < b_text.length; i++) {
			if ((b_text[i] & 0xF8) == 0xF0) {

				byte[] bb = "^_^!".getBytes();

				for (int j = 0; j < 4; j++) {
					b_text[i + j] = bb[j];
				}
				i += 3;
			}
		}

		return new String(b_text, charset);
	}


	/**
	 * 获取图片宽度
	 * 
	 * @param file
	 *            图片文件
	 * @return 宽度
	 */
	public static int getImgWidth(String file) {
		InputStream is = null;
		BufferedImage src = null;
		int ret = 0;
		try {
			is = new FileInputStream(file);
			src = javax.imageio.ImageIO.read(is);
			ret = src.getWidth(null); // 得到源图宽
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public static String formatInt(int data) {

		DecimalFormat df = new DecimalFormat("#0");

		return df.format(data);
	}
	
	public static String formatInt(int data,int minLen){
		
		if(minLen <= 0){
			minLen = 1;
		}
		
		StringBuilder format = new StringBuilder("#");
		
		for(int i=0;i<minLen;i++){
			
			format.append("0");
		}
		
		DecimalFormat df = new DecimalFormat(format.toString());

		return df.format(data);
	}
	
	/**
	 * 获取图片高度
	 * 
	 * @param file
	 *            图片文件
	 * @return 高度
	 */
	public static int getImgHeight(String file) {
		InputStream is = null;
		BufferedImage src = null;
		int ret = 0;
		try {
			is = new FileInputStream(file);
			src = javax.imageio.ImageIO.read(is);
			ret = src.getHeight(null); // 得到源图高
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * 
	 * @Title: compareDates
	 * @Description: 对两个日期进行 比较(若相等 返回 0；若 date1<date2 返回小于0；若date1>date2 返回大于0)
	 * @param @param date1
	 * @param @param date2
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @throws
	 */
	public static int compareDates(Date date1, Date date2) {
		return date1.compareTo(date2);
	}
	
	public static boolean hasBit(int num,int bit){
		
		int bitNum = (int) Math.pow(2, bit - 1);
		
		return (num & bitNum) == bitNum;
	}
	
	public static int bitsNum(int[] bits){
		
		int bitNum = 0;
		
		if(bits == null){
			
			return bitNum;
		}
		
		for (int i = 0; i < bits.length; i++) {
			
			bitNum += Math.pow(2, bits[i] - 1);
		}
		
		return bitNum;
	}
	
	public static int formatStart(int start){
		
		if(start <= 0){
			
			start = 1;
		}
		
		return start;
	}
	
	public static int formatPageSize(int size){
		
		if(size <= 0){
			
			size = 10;
		} else if(size > 50){
			
			size = 50;
		}
		
		return size;
	}
	
	public static boolean[] bits(int status,int bitCount){
		
		if(bitCount <=0 || status < 0){
			return null;
		}
		
		boolean[] bits = new boolean[bitCount];
		
		for (int i = 0; i < bits.length; i++) {
			
			boolean result = hasBit(status, i + 1);
			
			bits[i] = result;
		}
		
		return bits;
	}
	
	public static String getOrderIndex(String userId){
		StringBuffer sb=new StringBuffer();
		if(userId==null||userId.equals("")){
			return "000";
		}
		sb.append("10").append(userId.substring(userId.length()-1));
		return sb.toString();
	}
	
	public static final String formatMobileType(int mobileType) {

		String str = "";

		switch (mobileType) {
		case 0:
			str = "联通";
			break;
		case 1:
			str = "移动";
			break;
		case 2:
			str = "电信";
			break;

		default:
			break;
		}

		return str;
	}

	public static String mapToXml(Map<String, String> map,String[] nocdataStrs) {

		StringBuilder builder = new StringBuilder("<xml>");

		Set<Entry<String, String>> entrys = map.entrySet();

		List<String> nocdataKeys = new ArrayList<String>();
		
		if(nocdataStrs != null){
			for (String string : nocdataStrs) {
				nocdataKeys.add(string);
			}
		}
		for (Entry<String, String> entry : entrys) {

			String key = entry.getKey();
			Object value = entry.getValue();

			if (!nocdataKeys.contains(key)) {

				builder.append("<").append(key).append("><![CDATA[").append(value).append("]]></").append(key).append(">");
			} else {

				builder.append("<").append(key).append(">").append(value).append("</").append(key).append(">");
			}

		}
		builder.append("</xml>");

		return builder.toString();
	}
	
}
