package com.devidea.util;

import java.util.ArrayList;
import java.util.List;

public class GeneralUtil {
	public static String generateHash(String str){
		return SHA.bytesToHex(SHA.hash256(str));
	}
	
	public static List<String> removeFromList (List<String> origin, List<String> remove){
		List<String> listCopy = new ArrayList<String>();
		
		boolean found = false;
		for (String obj : origin) {
			found = false;
			
			for (String rmv : remove) {
				if (obj.equals(rmv)) {
					found = true;
				}
			}
			
			if (!found) {
				listCopy.add(obj);
			}
		}
		
		return listCopy;
	}

	public static List<String> removeFromList(List<String> emails, String remove) {
		List<String> listRemove = new ArrayList<String>();
		listRemove.add(remove);
		
		return removeFromList(emails, listRemove);
	}
}
