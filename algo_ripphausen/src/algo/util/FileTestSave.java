package algo.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileTestSave {
	static public void addStringToFile(String dat, String string) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(new File(dat),true);
			fw.write(string + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(fw != null)
				try {
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
