package org.salestock.main;

import java.util.logging.Logger;

import org.salestock.indexing.FileIndexing;

public class Server {

	private static final Logger log = Logger.getLogger(Server.class.getName());

	private FileIndexing fileIndexing;

	public Server(String sourceFile, FileIndexing fileIndexing) {
		this.fileIndexing = fileIndexing;
		this.initialize(sourceFile);
	}

	private void initialize(String sourceFile) {
		log.info("Creating Index");
		this.fileIndexing.createFileIndex(sourceFile);
		log.info("Creating Index Done");
	}

	public boolean checkBlacklist(String name, String number) {
		return this.fileIndexing.findData(name.toLowerCase().trim(), number.trim());
	}

}
