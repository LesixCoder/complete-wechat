package com.lunchtasting.server.util;



public class Encodeing {
	final static char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
			'9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
			'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
			'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
			'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
			'Z', '+', '/', };

	/**
	 * 把64进制的字符串转换成10进制
	 * 
	 * @param decompStr
	 * @return
	 */

	public static long UnCompressNumber(String decompStr) {
		long result = 0;
		for (int i = decompStr.length() - 1; i >= 0; i--) {
			if (i == decompStr.length() - 1) {
				result += getCharIndexNum(decompStr.charAt(i));
				continue;
			}
			for (int j = 0; j < digits.length; j++) {
				if (decompStr.charAt(i) == digits[j]) {
					result += ((long) j) << 6 * (decompStr.length() - 1 - i);
				}
			}
		}
		return result;
	}

	/**
	 * 把10进制的数字转换成64进制
	 * 
	 * @param number
	 * @param shift
	 * @return
	 */
	public static String CompressNumber(long number, int shift) {
		char[] buf = new char[64];
		int charPos = 64;
		int radix = 1 << shift;
		long mask = radix - 1;
		do {
			buf[--charPos] = digits[(int) (number & mask)];
			number >>>= shift;
		} while (number != 0);
		return new String(buf, charPos, (64 - charPos));
	}

	private static long getCharIndexNum(char ch) {
		int num = ((int) ch);
		if (num >= 48 && num <= 57) {
			return num - 48;
		} else if (num >= 97 && num <= 122) {
			return num - 87;
		} else if (num >= 65 && num <= 90) {
			return num - 29;
		} else if (num == 43) {
			return 62;
		} else if (num == 47) {
			return 63;
		}
		return 0;
	}
	
	
	// 转化字符串为十六进制编码
	public static String toHexString(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = (int) s.charAt(i);
			String s4 = Integer.toHexString(ch);
			str = str + s4;
		}
		return str;
	}

	// 转化十六进制编码为字符串
	public static String toStringHex(String s) {
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(
						i * 2, i * 2 + 2), 16));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		try {
			s = new String(baKeyword, "utf-8");// UTF-16le:Not
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return s;
	}
	
	// 十六进制转换成十进制
	public static String liushi(String sliu) {
		System.out.println("......."+sliu);
		String sliushi = Integer.valueOf(sliu, 16).toString();
		return sliushi;
	}
	
	// 十进制转换成十六进制
	public static String shiliu(int shi) {
		String ishi = Integer.toHexString(shi);
		return ishi;
	}
	

	//字符串转64进制
	public static String stringTo64(String str){	
		String str_64 = CompressNumber(Long.parseLong(str),6);
		str_64 = str_64.replace("/", ".");
		return str_64;
	}
	

	//64进制转字符串
	public static String decode64(String str){	
		str = str.replace(".", "/");
		String id = Long.toString(UnCompressNumber(str));
		return id;
	}
	
	
	public static void main(String[] args) {
		System.out.println("字符串转16="+toHexString("3313221"));
		System.out.println("16转字符串="+toStringHex("33333133323231"));
		System.out.println("16转10="+liushi("3313221"));
		System.out.println("10转16="+shiliu(53555745));
		
		System.out.println(CompressNumber(999999999999999999L,6));    
		System.out.println(UnCompressNumber(CompressNumber(999999999999999999L,6))); 
	}

}
