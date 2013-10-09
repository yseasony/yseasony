package org.yseasony.edm.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import org.apache.commons.io.IOUtils;

/**
 * User: yseasony
 * Date: 13-9-29
 * Time: 上午11:53
 */
public class DesUtil
{
    public static InputStream decrypt(InputStream is, Key key)
            throws Exception
    {
        OutputStream out = null;
        CipherOutputStream cos = null;
        ByteArrayOutputStream bout = null;
        try {
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(2, key);

            bout = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int count = 0;
            while ((count = is.read(buf)) != -1) {
                bout.write(buf, 0, count);
                buf = new byte[1024];
            }
            byte[] orgData = bout.toByteArray();
            byte[] raw = cipher.doFinal(orgData);
            return new ByteArrayInputStream(raw);
        } finally {
            IOUtils.closeQuietly(cos);
            IOUtils.closeQuietly(out);
            IOUtils.closeQuietly(bout);
        }
    }

    public static Key getKey(InputStream is)
    {
        Key key = null;
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(is);
            key = (Key)ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(ois);
        }
        return key;
    }
}
