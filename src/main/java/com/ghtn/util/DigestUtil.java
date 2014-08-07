package com.ghtn.util;

import org.apache.commons.codec.digest.DigestUtils;

public class DigestUtil {
	public static String encryptByMD5(String data){
		return DigestUtils.md5Hex(data).toUpperCase();
	}
}
