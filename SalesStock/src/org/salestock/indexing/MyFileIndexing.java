package org.salestock.indexing;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.salestock.util.FileUtil;

public class MyFileIndexing implements FileIndexing {

	private static final Logger log = Logger.getLogger(MyFileIndexing.class.getName());

	private String tempDir;

	private String sourceFile;

	private final static String dash = "-";

	private final static int indexLength = 1;

	public MyFileIndexing() {

	}

	@Override
	public void createFileIndex(String sourceFile) {
		this.sourceFile = sourceFile;
		String absolutePath = new File(sourceFile).getAbsolutePath();
		this.tempDir = absolutePath.substring(0, absolutePath.lastIndexOf(File.separator))
				.concat(System.getProperty("file.separator")).concat("temp");
		this.clearPrevFileIndex();
		try (BufferedReader br = Files.newBufferedReader(Paths.get(sourceFile))) {
			long lines = 1;
			for (String line = null; (line = br.readLine()) != null;) {
				String[] content = line.trim().split(" ");
				String fileName = content[0].substring(0, indexLength).toLowerCase().concat(dash)
						.concat(content[1].substring(0, indexLength)).concat(dash)
						.concat(String.valueOf(line.length()));
				FileUtil.appendToFile(lines, this.tempDir, fileName);
				lines++;
			}
			br.close();
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	@Override
	public boolean findData(String name, String number) {
		boolean found = false;
		int dataLength = name.length() + number.length() + 1;
		String fileName = name.substring(0, indexLength).concat(dash).concat(number.substring(0, indexLength))
				.concat(dash).concat(String.valueOf(dataLength));
		BufferedReader br2 = null;
		try (BufferedReader br = Files
				.newBufferedReader(Paths.get(this.tempDir + System.getProperty("file.separator") + fileName));) {

			List<Long> lineIndex = new ArrayList<Long>();

			for (String line = null; (line = br.readLine()) != null;) {
				lineIndex.add(Long.valueOf(line));
			}

			br.close();

			br2 = Files.newBufferedReader(Paths.get(this.sourceFile));
			long linePos = 1;
			for (Long x : lineIndex) {
				for (String content = null; (content = br2.readLine()) != null;) {
					if (x == linePos) {
						if (content.equalsIgnoreCase(name.concat(" ").concat(number))) {
							found = true;
							break;
						}
					}
					linePos++;
				}
			}

		} catch (NoSuchFileException ex) {

		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		} finally {
			if (br2 != null) {
				try {
					br2.close();
				} catch (IOException e) {
					log.log(Level.SEVERE, e.getMessage(), e);
				}
			}
		}
		return found;
	}

	private void clearPrevFileIndex() {
		File dir = new File(this.tempDir);
		if (dir.exists()) {
			for (File file : dir.listFiles()) {
				file.delete();
			}
		} else {
			dir.mkdir();
		}
	}

}
