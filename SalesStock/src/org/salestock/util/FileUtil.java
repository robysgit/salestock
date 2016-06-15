package org.salestock.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileUtil {

	private static final Logger log = Logger.getLogger(FileUtil.class.getName());

	public static boolean checkFile(String path) {
		if (path != null) {
			File file = new File(path);
			if (file.exists()) {
				return true;
			}
			return false;
		}
		return false;
	}

	public static void appendToFile(long content, String path, String fileName) throws IOException {
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			File file = new File(path + System.getProperty("file.separator") + fileName);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			bw.write(String.valueOf(content));
			bw.write(System.getProperty("line.separator"));

		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			throw new IOException(e);
		} finally {
			if (bw != null) {
				bw.close();
				bw = null;
			}
			if (fw != null) {
				fw.close();
				fw = null;
			}
		}
	}

	public static void writeToFile(Map<Integer, Long> content, String path, String fileName) throws IOException {
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			File file = new File(path + System.getProperty("file.separator") + fileName);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			for (Map.Entry<Integer, Long> entry : content.entrySet()) {
				String key = String.valueOf(entry.getKey());
				long max = entry.getValue();
				for (long i = 0; i < max; i++) {
					bw.write(key);
					bw.write(System.getProperty("line.separator"));
				}
			}

		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			throw new IOException(e);
		} finally {
			if (bw != null) {
				bw.close();
				bw = null;
			}
			if (fw != null) {
				fw.close();
				fw = null;
			}
		}
	}
}
