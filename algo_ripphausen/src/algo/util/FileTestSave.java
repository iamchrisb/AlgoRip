package algo.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileTestSave {
	static public void addStringToFile(String dat, String string) {

		try {
			@SuppressWarnings("resource")
			FileWriter fw = new FileWriter(new File(dat));
			fw.write(string);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void bla(String dat) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(dat);
		} catch (Exception e) {
			System.out
					.println("Datei konnte nicht ge�ffnet werden oder existiert nicht");
		}

		try {
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);

			String row;
			int length = new Integer(br.readLine());

			for (int i = 0; i < length; i++) {
				row = br.readLine();
				if (row == null)
					break;
			}

		} catch (Exception e) {
			System.out.println();
		} finally {

		}
	}
}
