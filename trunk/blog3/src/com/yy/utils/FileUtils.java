package com.yy.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yy.exception.MyException;

public class FileUtils {

	private static final Log log = LogFactory.getLog(FileUtils.class);

	public static void saveFile(String value, File file, boolean append) {
		FileWriter fw = null;
		BufferedWriter br = null;
		try {
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			}
			fw = new FileWriter(file, append);
			br = new BufferedWriter(fw);
			br.write(value);
			br.flush();

		} catch (IOException e) {
			throw new MyException(e.getMessage());
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				if (fw != null) {
					fw.close();
				}
			} catch (IOException e) {
				br = null;
				fw = null;
				throw new MyException(e.getMessage());
			}
		}
	}

	public static String readFile(File file) throws IOException {

		StringBuffer buffer = new StringBuffer();
		String line = "";
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				throw e;
			}
		}
		return buffer.toString();
	}
	
	public static long createFile(File file) throws IOException {
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				throw e;
			}
		}
		return System.currentTimeMillis();
	}
	
	public static void deleteFile(File file){
		if (file.exists()) {
			file.delete();
		}
	}

}
