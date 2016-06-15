package org.test.salestock;

import org.salestock.indexing.MyFileIndexing;
import org.salestock.main.Server;

public class ServerTest {

	public static void main(String args[]) {
		Server s = new Server("D:\\salestock\\blacklist.txt", new MyFileIndexing());
		System.out.println(s.checkBlacklist("Andi", "1341441"));
		System.out.println(s.checkBlacklist("Melisa", "1341441"));
		System.out.println(s.checkBlacklist("Melisa", "8565468"));
		System.out.println(s.checkBlacklist("Melisa", "8565469"));
	}
}
