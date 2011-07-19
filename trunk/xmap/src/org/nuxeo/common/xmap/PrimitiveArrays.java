/*
 * (C) Copyright 2006-2007 Nuxeo SAS (http://nuxeo.com/) and contributors.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * Contributors:
 *     Nuxeo - initial API and implementation
 *
 * $Id$
 */

package org.nuxeo.common.xmap;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author <a href="mailto:bs@nuxeo.com">Bogdan Stefanescu</a>
 * 
 */
@SuppressWarnings("rawtypes")
public class PrimitiveArrays {

    // Utility class.
    private PrimitiveArrays() {
    }

    public static Object toPrimitiveArray(Collection<Object> col,
            Class<?> primitiveArrayType) {
        if (primitiveArrayType == Integer.TYPE) {
            return toIntArray(col);
        } else if (primitiveArrayType == Long.TYPE) {
            return toLongArray(col);
        } else if (primitiveArrayType == Double.TYPE) {
            return toDoubleArray(col);
        } else if (primitiveArrayType == Float.TYPE) {
            return toFloatArray(col);
        } else if (primitiveArrayType == Boolean.TYPE) {
            return toBooleanArray(col);
        } else if (primitiveArrayType == Byte.TYPE) {
            return toByteArray(col);
        } else if (primitiveArrayType == Character.TYPE) {
            return toCharArray(col);
        } else if (primitiveArrayType == Short.TYPE) {
            return toShortArray(col);
        }
        return null;
    }

    public static int[] toIntArray(Collection col) {
        int size = col.size();
        int[] ar = new int[size];
        Iterator it = col.iterator();
        int i = 0;
        while (it.hasNext()) {
            ar[i++] = (Integer) it.next();
        }
        return ar;
    }

    public static long[] toLongArray(Collection col) {
        int size = col.size();
        long[] ar = new long[size];
        Iterator it = col.iterator();
        int i = 0;
        while (it.hasNext()) {
            ar[i++] = (Long) it.next();
        }
        return ar;
    }

    public static double[] toDoubleArray(Collection col) {
        int size = col.size();
        double[] ar = new double[size];
        Iterator it = col.iterator();
        int i = 0;
        while (it.hasNext()) {
            ar[i++] = (Double) it.next();
        }
        return ar;
    }

    public static float[] toFloatArray(Collection col) {
        int size = col.size();
        float[] ar = new float[size];
        Iterator it = col.iterator();
        int i = 0;
        while (it.hasNext()) {
            ar[i++] = (Float) it.next();
        }
        return ar;
    }

    public static boolean[] toBooleanArray(Collection col) {
        int size = col.size();
        boolean[] ar = new boolean[size];
        Iterator it = col.iterator();
        int i = 0;
        while (it.hasNext()) {
            ar[i++] = (Boolean) it.next();
        }
        return ar;
    }

    public static short[] toShortArray(Collection col) {
        int size = col.size();
        short[] ar = new short[size];
        Iterator it = col.iterator();
        int i = 0;
        while (it.hasNext()) {
            ar[i++] = (Short) it.next();
        }
        return ar;
    }

    public static byte[] toByteArray(Collection col) {
        int size = col.size();
        byte[] ar = new byte[size];
        Iterator it = col.iterator();
        int i = 0;
        while (it.hasNext()) {
            ar[i++] = (Byte) it.next();
        }
        return ar;
    }

    public static char[] toCharArray(Collection col) {
        int size = col.size();
        char[] ar = new char[size];
        Iterator it = col.iterator();
        int i = 0;
        while (it.hasNext()) {
            ar[i++] = (Character) it.next();
        }
        return ar;
    }

}
