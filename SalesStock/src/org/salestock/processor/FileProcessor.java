package org.salestock.processor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.salestock.util.FileUtil;

public class FileProcessor {

	public static void main(String args[]) {
		Map<Integer, Long> content = new HashMap<Integer, Long>();
		content.put(1, 2000000L);
		content.put(10, 6000000L);
		content.put(71, 6000000L);
		content.put(70, 6000000L);
		content.put(60, 6000000L);
		content.put(40, 6000000L);
		content.put(20, 6000000L);
		content.put(11, 6000000L);
		content.put(15, 6000000L);
		content.put(7, 2000000L);
		content.put(5, 200000L);
		content.put(6, 2000000L);
		content.put(9, 4000000L);
		try {
			FileUtil.writeToFile(content, "D:\\salestock", "data.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
