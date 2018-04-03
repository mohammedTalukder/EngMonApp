/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Mohammed
 */
public class FileOperationUtility {
    
    static String FSEP = File.separator;
    
     public static String getBaseFolderResource() {
    	
    	String basePath = java.lang.System.getProperty("user.dir") +FSEP;
    	return basePath;
    }
     public static void copyFile(String sourceFile, String destinFile) throws IOException {

		//File inputFile = new File(sourceFile);
		//File outputFile = new File(destinFile);
		
		//FileReader in = new FileReader(inputFile);
		//FileWriter out = new FileWriter(outputFile);

		// modified to use Stream Buffers
		FileInputStream input = new FileInputStream(sourceFile);
		BufferedInputStream in = new BufferedInputStream(input,4096);
		
		FileOutputStream output = new FileOutputStream(destinFile);
		BufferedOutputStream out = new BufferedOutputStream(output,4096);

		// *** Code required to use a char buffer
		// *** I expect this will improve performance
		// byte[] buf = new byte[1024];
		// int len;
		// while ((len = in.read(buf)) > 0) {
        //    out.write(buf, 0, len);
    	// }
	
		try {
			int c;
			while ((c = in.read()) != -1) {
				out.write(c);
			}
		} finally {
			in.close();
			out.close();
		}		
	}
     public static boolean deleteFile(String fileName) throws IOException {
			
	 		File f = new File(fileName);
			
	 		return f.delete();

		}
    
}
