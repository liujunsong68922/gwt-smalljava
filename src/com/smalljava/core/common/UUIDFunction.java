package com.smalljava.core.common;

import java.util.Random;

public class UUIDFunction {
	public static String uuid() {
		Random rand = new Random();
		String[] sdict = new String[] {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		String sret="";
		for(int i=0;i<16;i++) {
			int j = rand.nextInt(26);
			if(j<sdict.length) {
				sret += sdict[j];
			}
		}
		return sret;
		
	}
}
