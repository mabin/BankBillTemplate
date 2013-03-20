package utils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class PackageDataFile {
	
	public static void unpack(String sourcePath, String destPath){
		File source = new File(sourcePath);
		
		try {
			BufferedReader fis = new BufferedReader(new InputStreamReader(new FileInputStream(source)));
			
			String line = null ;
			while ( (line = fis.readLine()) != null ){
				System.out.println(line);
			}
			
			fis.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
