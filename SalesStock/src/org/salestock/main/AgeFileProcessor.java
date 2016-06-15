package org.salestock.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.salestock.util.FileUtil;

public class AgeFileProcessor {

	private static final Logger log = Logger.getLogger(AgeFileProcessor.class.getName());
	private static final String sourceFile = "age.txt";
	private static final String resultFile = "sorted_age.txt";

	public static void main(String args[]) {
		long number = 0;
		boolean valid = true;
		String path = null;
		Scanner scanner = new Scanner(System.in);

		// Get the path for age.txt
		while (valid) {
			System.out.print("Enter age.txt data file path : ");

			path = scanner.nextLine();
			if (FileUtil.checkFile(path + System.getProperty("file.separator") + sourceFile)) {
				valid = false;
			} else {
				log.info("File not found or invalid path");
			}
			System.out.println();

		}
		scanner.close();

		Map<String, Long> map = new HashMap<>();

		// Read file
		try (BufferedReader br = Files
				.newBufferedReader(Paths.get(path + System.getProperty("file.separator") + sourceFile));) {

			for (String line = null; (line = br.readLine()) != null;) {
				number = map.get(line) != null ? map.get(line) : 0;
				map.put(line, number + 1);
			}
			// Sorting
			Map<Integer, Long> sortedMap = new TreeMap<Integer, Long>();
			for (Map.Entry<String, Long> entry : map.entrySet()) {
				sortedMap.put(Integer.valueOf(entry.getKey()), entry.getValue());
			}
			// Write result
			FileUtil.writeToFile(sortedMap, path, resultFile);
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}

		log.log(Level.INFO, "Completed");

	}

}
