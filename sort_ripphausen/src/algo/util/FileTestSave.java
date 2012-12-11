package algo.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
					e.printStackTrace();
				}
		}
	}
}
