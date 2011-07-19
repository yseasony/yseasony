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
 * $Id: SerializableHelper.java 16046 2007-04-12 14:34:58Z fguillaume $
 */

package org.nuxeo.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.Serializable;
import java.io.StreamCorruptedException;

/**
 * Helper for serializable objects.
 * 
 * @author <a href="mailto:ja@nuxeo.com">Julien Anguenot</a>
 */
public final class SerializableHelper {

    // This is an utility class.
    private SerializableHelper() {
    }

    /**
     * Checks if a given object is serializable.
     * 
     * @param ob
     *            the actual object we want to test
     * @return true if the object is serializable.
     * 
     */
    // XXX AT: since class loader isolation, this module is not aware anymore of
    // nuxeo.ear classes => ClassCastException can be thrown is tested object is
    // a DocumentModel for instance.
    public static boolean isSerializable(Object ob) {

        Serializable in;
        try {
            in = (Serializable) ob;
        } catch (ClassCastException cce) {
            return false;
        }

        try {
            ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
            ObjectOutputStream outStream = new ObjectOutputStream(byteOutStream);
            outStream.writeObject(in);
            ByteArrayInputStream byteInStream = new ByteArrayInputStream(
                    byteOutStream.toByteArray());
            ObjectInputStream inStream = new ObjectInputStream(byteInStream);
            Object obs = inStream.readObject();
            return obs != null;
        } catch (OptionalDataException e) {
            /*
             * throw new RuntimeException("Optional data found. " +
             * e.getMessage());
             */
            return false;
        } catch (StreamCorruptedException e) {
            /*
             * throw new RuntimeException( "Serialized object got corrupted. " +
             * e.getMessage());
             */
            return false;
        } catch (NotSerializableException ex) {
            /*
             * ex.printStackTrace(); throw new IllegalArgumentException( "Object
             * is not serializable: " + ex.getMessage()); //$NON-NLS-1$
             */
            return false;
        } catch (IOException e) {
            /*
             * throw new RuntimeException( "IO operation failed during
             * serialization. " + e.getMessage()); //$NON-NLS-1$
             */
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
