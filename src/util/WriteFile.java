/******************************************************************************
*
*      WriteFile.java
*
*
*******************************************************************************
*
*   Date....	WHO                             Description.
*   29/01/2013	Mohammed Talukder		First Created
*
******************************************************************************/

package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * 
 * @author Mohammed Talukder
 *
 */
public class WriteFile {

    /**
     * Write a String type to file. The file name will not be modified.
     * If the string is empty then an end of line marker will be written.
     * 
     * @param file
     * The full path and file name for the String to be written.
     * 
     * @param text
     * The string to be written to file. The string may contain new line markers.
     * 
     * @throws Exception
     */
    public static void writeToFile(String file, String text) throws Exception
    {

   	//if (text.compareTo("") == 0) {
    //		text = System.getProperty("line.separator");
    //}
   	if (!text.contains(System.getProperty("line.separator"))){
   		text = text + System.getProperty("line.separator");
   	}
   	
    	try {
    		// Setup File    
    		File OutErr = new File(file);
    		if(OutErr.exists() == false) {
    			OutErr.createNewFile();
    		}
        
    		// Write text to file 
    		FileWriter OutErrWriter = new FileWriter(OutErr);        
    		OutErrWriter.write(text);                   
    		OutErrWriter.close();  
    		
    	} catch (IOException io) { 
    		throw new IOException(file);
    	}
        
    }

    /**
     * Write an ArrayList of object[] Strings to file.
     * 
     * Each element in the Object[] will be written on a single space delimited line.
     * Any null values will be replaced by the empty string "".
     * 
     * Each element in the ArrayList will be written to a new line.
     *   
     * @param file
     * The full path and file name to be written to.
     * 
     * @param results
     * The ArrayList of object[] to be written to file.
     * 
     * @throws Exception
     */
    public static void writeArrayList(String file, ArrayList<Object[]> results) throws Exception {
        
    		// set platform line separator
    		String space = " ";
    		String eol = java.lang.System.getProperty("line.separator");
    		  		
            // Setup File    
            File OutErr = new File(file);
            
            try {
	            if(OutErr.exists() == false) {
	                OutErr.createNewFile();
	            }
            }
            catch (IOException io) {
            	throw new Exception("IOExeption: file="+file,io);
            }
            
            // Write text to file 
            Iterator<Object[]> resultIter = results.iterator();
            FileWriter OutErrWriter = new FileWriter(OutErr);        
            
            while (resultIter.hasNext()) {
            	
            	
            	// get next row of results as String[]
            	Object[] objLine = (Object[])resultIter.next();
         
            	// add the first value to write
            	if (objLine.length >=1) {
            		String line = (String)objLine[0].toString().replace("\n",eol);
            	
	            	//for each String[] object result row add to String
	            	for (int i=1; i < objLine.length; i++) {
	            		
	            		if (objLine[i] == null) { objLine[i] = ""; }
	            		line = line + space +(String)objLine[i].toString().replace("\n",eol);
	            	}
            	
	            	// write the String to file + and end of line marker.
	            	OutErrWriter.write(line +java.lang.System.getProperty("line.separator"));
            	}
            }
            // finish writing all rows - close file
            OutErrWriter.close();   
    }

}