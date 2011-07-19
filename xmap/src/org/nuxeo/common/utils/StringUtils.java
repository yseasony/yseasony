/*
 * (C) Copyright 2007 Nuxeo SAS <http://nuxeo.com> and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Nuxeo - initial API and implementation
 *
 * $Id: StringUtils.java 17968 2007-04-28 16:00:51Z sfermigier $
 */

package org.nuxeo.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Utils for String manipulations.
 * 
 * @author <a href="mailto:at@nuxeo.com">Anahide Tchertchian</a>
 */
public final class StringUtils {

    private static final String PLAIN_ASCII =
    // grave
    "AaEeIiOoUu"
    // acute
            + "AaEeIiOoUuYy"
            // circumflex
            + "AaEeIiOoUuYy"
            // tilde
            + "AaEeIiOoUuYy"
            // umlaut
            + "AaEeIiOoUuYy"
            // ring
            + "Aa"
            // cedilla
            + "Cc";

    private static final String UNICODE = "\u00C0\u00E0\u00C8\u00E8\u00CC\u00EC\u00D2\u00F2\u00D9\u00F9"
            + "\u00C1\u00E1\u00C9\u00E9\u00CD\u00ED\u00D3\u00F3\u00DA\u00FA\u00DD\u00FD"
            + "\u00C2\u00E2\u00CA\u00EA\u00CE\u00EE\u00D4\u00F4\u00DB\u00FB\u0176\u0177"
            + "\u00C2\u00E2\u00CA\u00EA\u00CE\u00EE\u00D4\u00F4\u00DB\u00FB\u0176\u0177"
            + "\u00C4\u00E4\u00CB\u00EB\u00CF\u00EF\u00D6\u00F6\u00DC\u00FC\u0178\u00FF"
            + "\u00C5\u00E5" + "\u00C7\u00E7";

    // This is an utility class.
    private StringUtils() {
    }

    /**
     * Replaces accented characters from a non-null String by their ascii
     * equivalent.
     */
    public static String toAscii(String s) {
        StringBuffer sb = new StringBuffer();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int pos = UNICODE.indexOf(c);
            if (pos > -1) {
                sb.append(PLAIN_ASCII.charAt(pos));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * Improved versions of join method from
     * org.apache.commons.lang.StringUtils.
     * 
     * @param array
     * @return
     */
    public static String join(Object[] array) {
        if (array == null) {
            return null;
        }
        int arraySize = array.length;
        int bufSize = arraySize == 0 ? 0 : ((array[0] == null ? 16 : array[0]
                .toString().length()) + 1) * arraySize;
        StringBuffer buf = new StringBuffer(bufSize);

        for (int i = 0; i < arraySize; i++) {
            if (array[i] != null) {
                buf.append(array[i]);
            }
        }
        return buf.toString();
    }

    /**
     * Improved versions of join method from
     * org.apache.commons.lang.StringUtils.
     * 
     * @param array
     * @param separator
     * @return
     */
    public static String join(Object[] array, String separator) {
        if (array == null) {
            return null;
        }
        int arraySize = array.length;
        int bufSize = arraySize == 0 ? 0 : ((array[0] == null ? 16 : array[0]
                .toString().length()) + 1) * arraySize;
        StringBuffer buf = new StringBuffer(bufSize);

        buf.append(array[0]);
        for (int i = 1; i < arraySize; i++) {
            if (separator != null) {
                buf.append(separator);
            }
            if (array[i] != null) {
                buf.append(array[i]);
            }
        }
        return buf.toString();
    }

    /**
     * Joins strings from a {@code List} with an optional separator.
     * 
     * @param list
     *            the list.
     * @param separator
     *            the separator.
     * @return the joined string.
     */
    public static String join(List<String> list, String separator) {
        if (list == null) {
            return null;
        }
        if (list.isEmpty()) {
            return "";
        }
        int seplen;
        if (separator == null) {
            seplen = 0;
        } else {
            seplen = separator.length();
        }
        int len = -seplen;
        for (String s : list) {
            len += seplen;
            if (s != null) {
                len += s.length();
            }
        }
        StringBuffer buf = new StringBuffer(len);
        boolean first = true;
        for (String s : list) {
            if (first) {
                first = false;
            } else {
                if (seplen != 0) {
                    buf.append(separator);
                }
            }
            if (s != null) {
                buf.append(s);
            }
        }
        return buf.toString();
    }

    public static String join(List<String> list) {
        return join(list, null);
    }

    public static String join(List<String> list, char separator) {
        return join(list, String.valueOf(separator));
    }

    public static String[] split(String str, char delimiter, boolean trim) {
        int s = 0;
        int e = str.indexOf(delimiter, s);
        if (e == -1) {
            if (trim) {
                str = str.trim();
            }
            return new String[] { str };
        }
        List<String> ar = new ArrayList<String>();
        do {
            String segment = str.substring(s, e);
            if (trim) {
                segment = segment.trim();
            }
            ar.add(segment);
            s = e + 1;
            e = str.indexOf(delimiter, s);
        } while (e != -1);

        int len = str.length();
        if (s < len) {
            String segment = str.substring(s);
            if (trim) {
                segment = segment.trim();
            }
            ar.add(segment);
        } else {
            ar.add("");
        }

        return ar.toArray(new String[ar.size()]);
    }

    public static String toHex(String string) {
        char[] chars = string.toCharArray();
        StringBuffer buf = new StringBuffer();
        for (char c : chars) {
            buf.append(Integer.toHexString(c).toUpperCase());
        }
        return buf.toString();
    }

    public static boolean isBlank(String str) {
        int length;

        if (str == null || (length = str.length()) == 0) {
            return true;
        }

        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

}
