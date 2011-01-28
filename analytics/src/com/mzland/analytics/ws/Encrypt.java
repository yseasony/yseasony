package com.mzland.analytics.ws;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

public class Encrypt {
	public final static int[] KEY = new int[]{0x1D96B5A3, 0x388A268B, 0xA4ECB758, 0x689D5CE2};
	public final static int TIMES = 16;

	private final static byte[] hex = "0123456789ABCDEF".getBytes();

	/**
	 * 
	 * @param content
	 * @param offset
	 * @param key
	 * @param times
	 * @return
	 */
	public static byte[] encrypt(byte[] content, int offset, int[] key, int times) {
		int[] tempInt = byteToInt(content, offset);

		int y = tempInt[0], z = tempInt[1], sum = 0, i;

		int delta = 0x9E3779B9;

		int a = key[0], b = key[1], c = key[2], d = key[3];

		for (i = 0; i < times; i++) {
			sum += delta;
			y += ((z << 4) + a) ^ (z + sum) ^ ((z >> 5) + b);
			z += ((y << 4) + c) ^ (y + sum) ^ ((y >> 5) + d);
		}

		tempInt[0] = y;
		tempInt[1] = z;

		return intToByte(tempInt, 0);
	}

	/**
	 * ����
	 * 
	 * @param encryptContent
	 * @param offset
	 * @param key
	 * @param times
	 * @return
	 */
	public static byte[] decrypt(byte[] encryptContent, int offset, int[] key, int times) {
		int[] tempInt = byteToInt(encryptContent, offset);
		int y = tempInt[0], z = tempInt[1], sum = 0, i;
		int delta = 0x9e3779b9;
		int a = key[0], b = key[1], c = key[2], d = key[3];
		if (times == 32)
			sum = 0xC6EF3720;
		else if (times == 16)
			sum = 0xE3779B90;
		else
			sum = delta * times;

		for (i = 0; i < times; i++) {
			z -= ((y << 4) + c) ^ (y + sum) ^ ((y >> 5) + d);
			y -= ((z << 4) + a) ^ (z + sum) ^ ((z >> 5) + b);
			sum -= delta;
		}

		tempInt[0] = y;
		tempInt[1] = z;

		return intToByte(tempInt, 0);
	}

	/**
	 * byte[]
	 * 
	 * @param content
	 * @param offset
	 * @return
	 */
	public static int[] byteToInt(byte[] content, int offset) {
		int[] result = new int[content.length >> 2];
		for (int i = 0, j = offset; j < content.length; i++, j += 4) {
			result[i] = transform(content[j])
						| transform(content[j + 1]) << 8
						| transform(content[j + 2]) << 16
						| (int) content[j + 3] << 24;
		}
		return result;
	}

	/**
	 * int[]
	 * 
	 * @param content
	 * @param offset
	 * @return
	 */
	public static byte[] intToByte(int[] content, int offset) {
		byte[] result = new byte[content.length << 2];
		for (int i = 0, j = offset; j < result.length; i++, j += 4) {
			result[j] = (byte) (content[i] & 0xff);
			result[j + 1] = (byte) ((content[i] >> 8) & 0xff);
			result[j + 2] = (byte) ((content[i] >> 16) & 0xff);
			result[j + 3] = (byte) ((content[i] >> 24) & 0xff);
		}
		return result;
	}

	/**
	 * 
	 */
	public static int transform(byte temp) {
		int tempInt = (int) temp;
		if (tempInt < 0) {
			tempInt += 256;
		}
		return tempInt;
	}

	/**
	 * 
	 * 
	 * @param fileName
	 * @param b
	 */
	public static void save(final String fileName, byte[] b) {
		File file = new File(fileName);
		try {
			OutputStream out = new FileOutputStream(file);
			out.write(b, 0, b.length);
			out.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param fileName
	 * @return
	 */
	public static byte[] open(final String fileName) {
		StringBuffer buf = new StringBuffer();
		File file = new File(fileName);

		try {
			InputStream in = new FileInputStream(file);
			int i = -1;
			while ((i = in.read()) != -1) {
				buf.append((char) i);
			}
			in.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 
	 * @param info
	 * @return
	 */
	public static byte[] encryptByTea(String info) {
		try {
			byte[] temp = info.getBytes("UTF-8");
			int n = 8 - temp.length % 8;
			byte[] encryptStr = new byte[temp.length + n];
			encryptStr[temp.length + n - 1] = (byte) n;
			System.arraycopy(temp, 0, encryptStr, 0, temp.length);
			byte[] result = new byte[encryptStr.length];

			for (int offset = 0; offset < result.length; offset += 8) {
				byte[] tempEncrpt = Encrypt.encrypt(encryptStr, offset, KEY, TIMES);
				System.arraycopy(tempEncrpt, 0, result, offset, 8);
			}

			return result;

		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param secretInfo
	 * @return
	 */
	public static String decryptByTea(byte[] secretInfo) {
		if (secretInfo.length == 0) {
			return "";
		}
		byte[] decryptStr = null;
		byte[] tempDecrypt = new byte[secretInfo.length];
		for (int offset = 0; offset < secretInfo.length; offset += 8) {
			decryptStr = Encrypt.decrypt(secretInfo, offset, KEY, TIMES);
			System.arraycopy(decryptStr, 0, tempDecrypt, offset, 8);
		}

		int n = tempDecrypt[secretInfo.length - 1];
		return new String(tempDecrypt, 0, decryptStr.length - n);
	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	public final static String md5(String s) {
		char hexDigits[] = {'0',
							'1',
							'2',
							'3',
							'4',
							'5',
							'6',
							'7',
							'8',
							'9',
							'A',
							'B',
							'C',
							'D',
							'E',
							'F'};
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		}
		catch (Exception e) {
			return null;
		}
	}

	// 从字节数组到十六进制字符串转换
	public static String bytesToHexString(byte[] b) {
		byte[] buff = new byte[2 * b.length];
		for (int i = 0; i < b.length; i++) {
			buff[2 * i] = hex[(b[i] >> 4) & 0x0f];
			buff[2 * i + 1] = hex[b[i] & 0x0f];
		}
		return new String(buff);
	}

	// 从十六进制字符串到字节数组转换
	public static byte[] hexStringToBytes(String hexstr) {
		byte[] b = new byte[hexstr.length() / 2];
		int j = 0;
		for (int i = 0; i < b.length; i++) {
			char c0 = hexstr.charAt(j++);
			char c1 = hexstr.charAt(j++);
			b[i] = (byte) ((parse(c0) << 4) | parse(c1));
		}
		return b;
	}

	private static int parse(char c) {
		if (c >= 'a')
			return (c - 'a' + 10) & 0x0f;
		if (c >= 'A')
			return (c - 'A' + 10) & 0x0f;
		return (c - '0') & 0x0f;
	}

}