package org.salestock.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.salestock.util.FileUtil;

public abstract class Server {

	private static final Logger log = Logger.getLogger(Server.class.getName());

	private String filePath;

	public static void main(String args[]) {
		Server s = new Server("a") {
		};
		s.checkBlacklist("a", "b");
	}

	public Server(String filePath) {
		this.initialize(filePath);
	}

	private void initialize(String filePath) {
		this.filePath = filePath;
		this.createIndexFile();
	}

	private void createIndexFile() {

	}

	public boolean checkBlacklist(String name, String number) {
		int nameLength = name.length();
		int numberLength = number.length();
		if (FileUtil.checkFile(this.filePath)) {
			try (BufferedReader br = Files.newBufferedReader(Paths.get(this.filePath))) {
				for (String line = null; (line = br.readLine()) != null;) {
					String[] content = line.split(" ");
					if (nameLength == content[0].length()) {
						if (numberLength == content[1].length()) {
							if (Integer.valueOf(number) == Integer.valueOf(content[1])) {
								if (name.equals(content[0])) {
									return true;
								}
							}
						}
					}
				}
			} catch (IOException e) {
				log.log(Level.SEVERE, e.getMessage(), e);
			}

		}
		return false;
	}

}
