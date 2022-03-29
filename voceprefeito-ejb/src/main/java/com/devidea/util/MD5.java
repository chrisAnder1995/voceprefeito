package com.devidea.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	
	public static String encodeMd5(String s) {
		String s2 = null;

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			BigInteger hash = new BigInteger(1, md.digest(s.getBytes("UTF-8")));
			s2 = hash.toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return s2;
	}
}
