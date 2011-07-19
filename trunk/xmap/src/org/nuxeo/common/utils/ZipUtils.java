/*
 * (C) Copyright 2002 - 2006 Nuxeo SAS <http://nuxeo.com> and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Nuxeo - initial API and implementation
 *
 *
 * $$Id$$
 */

package org.nuxeo.common.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @author bstefanescu
 * 
 */
public final class ZipUtils {

    private static final Log log = LogFactory.getLog(ZipUtils.class);

    // This is an utility class
    private ZipUtils() {
    }

    // _____________________________ ZIP ________________________________

    public static void _putDirectoryEntry(String entryName, ZipOutputStream out)
            throws IOException {
        ZipEntry zentry = new ZipEntry(entryName + '/');
        out.putNextEntry(zentry);
        out.closeEntry();
    }

    public static void _putFileEntry(File file, String entryName,
            ZipOutputStream out) throws IOException {
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            _zip(entryName, in, out);
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    public static void _zip(String entryName, InputStream in,
            ZipOutputStream out) throws IOException {
        ZipEntry zentry = new ZipEntry(entryName);
        out.putNextEntry(zentry);
        // Transfer bytes from the input stream to the ZIP file
        FileUtils.copy(in, out);
        out.closeEntry();
    }

    public static void _zip(String entryName, File file, ZipOutputStream out)
            throws IOException {
        // System.out.println("Compressing "+entryName);
        if (file.isDirectory()) {
            entryName += '/';
            ZipEntry zentry = new ZipEntry(entryName);
            out.putNextEntry(zentry);
            out.closeEntry();
            File[] files = file.listFiles();
            for (int i = 0, len = files.length; i < len; i++) {
                _zip(entryName + files[i].getName(), files[i], out);
            }
        } else {
            InputStream in = null;
            try {
                in = new BufferedInputStream(new FileInputStream(file));
                _zip(entryName, in, out);
            } finally {
                if (in != null) {
                    in.close();
                }
            }
        }
    }

    public static void _zip(File[] files, ZipOutputStream out, String prefix)
            throws IOException {
        if (prefix != null) {
            int len = prefix.length();
            if (len == 0) {
                prefix = null;
            } else if (prefix.charAt(len - 1) != '/') {
                prefix += '/';
            }
        }
        for (int i = 0, len = files.length; i < len; i++) {
            String name = prefix != null ? prefix + files[i].getName()
                    : files[i].getName();
            _zip(name, files[i], out);
        }
    }

    public static void zip(File file, OutputStream out, String prefix)
            throws IOException {
        if (prefix != null) {
            int len = prefix.length();
            if (len == 0) {
                prefix = null;
            } else if (prefix.charAt(len - 1) != '/') {
                prefix += '/';
            }
        }
        String name = prefix != null ? prefix + file.getName() : file.getName();
        ZipOutputStream zout = null;
        try {
            zout = new ZipOutputStream(out);
            _zip(name, file, zout);
        } finally {
            if (zout != null) {
                zout.finish();
            }
        }
    }

    public static void zip(File[] files, OutputStream out, String prefix)
            throws IOException {
        ZipOutputStream zout = null;
        try {
            zout = new ZipOutputStream(out);
            _zip(files, zout, prefix);
        } finally {
            if (zout != null) {
                zout.finish();
            }
        }
    }

    public static void zip(File file, File zip) throws IOException {
        OutputStream out = null;
        try {
            out = new BufferedOutputStream(new FileOutputStream(zip));
            zip(file, out, null);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public static void zip(File[] files, File zip) throws IOException {
        OutputStream out = null;
        try {
            out = new BufferedOutputStream(new FileOutputStream(zip));
            zip(files, out, null);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public static void zip(File file, File zip, String prefix)
            throws IOException {
        OutputStream out = null;
        try {
            out = new BufferedOutputStream(new FileOutputStream(zip));
            zip(file, out, prefix);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public static void zip(File[] files, File zip, String prefix)
            throws IOException {
        OutputStream out = null;
        try {
            out = new BufferedOutputStream(new FileOutputStream(zip));
            zip(files, out, prefix);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public static void zipFilesUsingPrefix(String prefix, File[] files,
            OutputStream out) throws IOException {
        ZipOutputStream zout = null;
        try {
            zout = new ZipOutputStream(out);
            if (prefix != null && prefix.length() > 0) {
                int p = prefix.indexOf('/');
                while (p > -1) {
                    _putDirectoryEntry(prefix.substring(0, p), zout);
                    p = prefix.indexOf(p + 1, '/');
                }
                _putDirectoryEntry(prefix, zout);
                prefix += '/';
            } else {
                prefix = "";
            }
            // prefix = prefix + '/';
            for (File file : files) {
                _putFileEntry(file, prefix + file.getName(), zout);
            }
        } finally {
            if (zout != null) {
                zout.finish();
            }
        }
    }

    // _____________________________ UNZIP ________________________________

    public static void unzip(String prefix, InputStream zipStream, File dir)
            throws IOException {
        ZipInputStream in = null;
        try {
            in = new ZipInputStream(new BufferedInputStream(zipStream));
            unzip(prefix, in, dir);
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    public static void unzip(InputStream zipStream, File dir)
            throws IOException {
        ZipInputStream in = null;
        try {
            in = new ZipInputStream(new BufferedInputStream(zipStream));
            unzip(in, dir);
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    public static void unzip(String prefix, URL zip, File dir)
            throws IOException {
        ZipInputStream in = null;
        try {
            in = new ZipInputStream(new BufferedInputStream(zip.openStream()));
            unzip(prefix, in, dir);
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    public static void unzip(URL zip, File dir) throws IOException {
        ZipInputStream in = null;
        try {
            in = new ZipInputStream(new BufferedInputStream(zip.openStream()));
            unzip(in, dir);
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    public static void unzip(String prefix, File zip, File dir)
            throws IOException {
        ZipInputStream in = null;
        try {
            in = new ZipInputStream(new BufferedInputStream(
                    new FileInputStream(zip)));
            unzip(prefix, in, dir);
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    public static void unzip(File zip, File dir) throws IOException {
        ZipInputStream in = null;
        try {
            in = new ZipInputStream(new BufferedInputStream(
                    new FileInputStream(zip)));
            unzip(in, dir);
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    public static void unzip(String prefix, ZipInputStream in, File dir)
            throws IOException {
        dir.mkdirs();
        ZipEntry entry = in.getNextEntry();
        while (entry != null) {
            if (!entry.getName().startsWith(prefix)) {
                entry = in.getNextEntry();
                continue;
            }
            log.debug("Extracting " + entry.getName());
            File file = new File(dir, entry.getName()
                    .substring(prefix.length()));
            if (entry.isDirectory()) {
                file.mkdirs();
            } else {
                file.getParentFile().mkdirs();
                FileUtils.copyToFile(in, file);
            }
            entry = in.getNextEntry();
        }
    }

    public static void unzip(ZipInputStream in, File dir) throws IOException {
        dir.mkdirs();
        ZipEntry entry = in.getNextEntry();
        while (entry != null) {
            // System.out.println("Extracting "+entry.getName());
            File file = new File(dir, entry.getName());
            if (entry.isDirectory()) {
                file.mkdirs();
            } else {
                file.getParentFile().mkdirs();
                FileUtils.copyToFile(in, file);
            }
            entry = in.getNextEntry();
        }
    }

    public static void unzipIgnoreDirs(ZipInputStream in, File dir)
            throws IOException {
        dir.mkdirs();
        ZipEntry entry = in.getNextEntry();
        while (entry != null) {
            String entryName = entry.getName();
            log.debug("Extracting " + entryName);
            if (entry.isDirectory()) {
                log.debug("Directory zip entry. Ignoring ..");
            } else {
                log.debug("Extracting file entry: " + entryName);
                int p = entryName.lastIndexOf('/');
                if (p > -1) {
                    entryName = entryName.substring(p + 1);
                    log.debug("Short entry name: " + entryName);
                }
                File file = new File(dir, entryName);
                FileUtils.copyToFile(in, file);
            }
            entry = in.getNextEntry();
        }
    }

    public static void unzipIgnoreDirs(InputStream zipStream, File dir)
            throws IOException {
        ZipInputStream in = null;
        try {
            in = new ZipInputStream(new BufferedInputStream(zipStream));
            unzipIgnoreDirs(in, dir);
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    public static void unzip(File zip, File dir, PathFilter filter)
            throws IOException {
        ZipInputStream in = null;
        try {
            in = new ZipInputStream(new BufferedInputStream(
                    new FileInputStream(zip)));
            unzip(in, dir, filter);
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    public static void unzip(ZipInputStream in, File dir, PathFilter filter)
            throws IOException {
        if (filter == null) {
            unzip(in, dir);
            return;
        }
        ZipEntry entry = in.getNextEntry();
        while (entry != null) {
            String entryName = entry.getName();
            if (filter.accept(new Path(entryName))) {
                // System.out.println("Extracting "+entryName);
                File file = new File(dir, entryName);
                if (entry.isDirectory()) {
                    file.mkdirs();
                } else {
                    file.getParentFile().mkdirs();
                    FileUtils.copyToFile(in, file);
                }
            }
            entry = in.getNextEntry();
        }
    }

}
