package org.salestock.indexing;

public interface FileIndexing {

	public void createFileIndex(String sourceFile);

	public boolean findData(String name, String number);

}
