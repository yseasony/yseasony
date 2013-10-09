package org.yseasony.edm.utils;

import org.apache.commons.codec.binary.Base64;

/**
 * User: yseasony Date: 13-10-7 Time: 下午7:10
 */
public class MyBase64 {

    public static String encodeStr(String plainText) {
        byte[] b = plainText.getBytes();
        Base64 base64 = new Base64();
        b = base64.encode(b);
        return new String(b);
    }

    public static String decodeStr(String encodeStr) {
        byte[] b = encodeStr.getBytes();
        Base64 base64 = new Base64();
        b = base64.decode(b);
        return new String(b);
    }
}
