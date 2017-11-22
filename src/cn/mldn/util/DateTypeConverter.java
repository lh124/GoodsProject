package cn.mldn.util;

import java.util.HashSet;
import java.util.Set;

public class DateTypeConverter {
	/**
	 *将String转为long 
	 * @param str
	 * @return
	 */
	public  static Set<Long> ConverterStringToLong(String str[]){
		Set<Long> set=new HashSet<>();
		for(int i=0;i<str.length;i++) {
			set.add(Long.parseLong(str[i]));
		}
		return set;
	}
	
	public static Set<Long> converterStringSplit(String str) {
		Set<Long> set = new HashSet<Long>() ;
		String result [] = str.split(",") ;
		for (int x = 0 ; x < result.length ; x ++) {
			set.add(Long.parseLong(result[x])) ;
		}
		return set ;
	} 

}
