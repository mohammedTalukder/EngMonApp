
/******************************************************************************
*
*      FileRead.java
*
*******************************************************************************
*
*      Maintenance log - insert most recent change descriptions at top
*
*      Date....   	WHO                   Description..............
*      10-01-2013       Mohammed Talukder     Initial implementation  
*
******************************************************************************/
package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * 
 * @author Mohammed
 */

public class FileRead {


	/**
	 * Reads a file and returns the content in the form byte[] Array
	 * 
	 * @param fileName
	 * The PATH and NAME of the file you want to read.
	 * 
	 * @return
	 * Returns file content as a byte[]
	 * @throws Exception
	 */
	public static byte[] readFromFilebytes(String fileName) throws Exception {

		File file = new File(fileName);
		
		return readFromFilebytes(file);

	}
	
	/**
	 * Reads a file and returns the content in the form byte[] Array
	 *
	 * @param fileName
	 * A FILE objects of the file you want to read.
	 * 
	 * @return
	 * Returns file content as a byte[]
	 * 
	 * @throws Exception
	 */
	public static byte[] readFromFilebytes(File fileName) throws Exception {
		
		
        InputStream is = new FileInputStream(fileName);
        
        byte[] bytes = new byte[(int)fileName.length()];
    
        // Read the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length && (numRead=is.read(bytes, offset, bytes.length - offset)) >= 0) { 
            offset += numRead;
        }
    
        // Make sure all bytes have been read
        if (offset < bytes.length) {
            throw new IOException("Could not read file fully" + fileName.getName());
        }
    
        is.close();
        return bytes;
	}
	
	
	/**
	 * Opens a file and returns a BufferedReader
	 *
	 * @param fileName
	 * The PATH and NAME of the file you want to read.
	 * 
	 * @return
	 * Returns a file BufferedReader
	 * 
	 * @throws Exception
	 */
	public static BufferedReader readBufferFromFile(String fileName) throws Exception {
		
		/* Read Build Info */
		File file = new File(fileName);
		FileReader readFile = new FileReader(file);
		BufferedReader readBuffer = new BufferedReader(readFile);
		
		return readBuffer;
	}
	
	
	/**
	 * Reads a file and returns the content as a type String
	 *
	 * @param fileName
	 * The PATH and NAME of the file you want to read.
	 * 
	 * @return
	 * Returns file content as a String
	 * 
	 * @throws Exception
	 */
	public static String readStringFromFile(String fileName) throws Exception {
		
		String result = "";
		
		/* Read Build Info */
		File file = new File(fileName);
		FileReader readFile = new FileReader(file);
		BufferedReader readBuffer = new BufferedReader(readFile);
		
		// read the first line
		result= readBuffer.readLine();
		
		// read subsequent lines
		String line = null;
		while((line = readBuffer.readLine()) != null) {
			
			result = result + line +"\n";
		}
	
		return result;
	}

		
	/**
	 * Reads a file and returns the content as a type ArrayList<String>
	 *
	 * @param fileName
	 * The PATH and NAME of the file you want to read.
	 * 
	 * @return
	 * Returns file content as an ArrayList<String>
	 * 
	 * @throws Exception
	 */
	public static ArrayList<String> readFromFileArrayList(String fileName) throws Exception {
		
		ArrayList<String> result = new ArrayList<String>();
		
		/* Read Build Info */
		File file = new File(fileName);
		FileReader readFile = new FileReader(file);
		BufferedReader readBuffer = new BufferedReader(readFile);
		
		String line = null;
		while(null != (line = readBuffer.readLine())) {
			result.add(line);
		}
	
		return result;
	}
	
	
}
