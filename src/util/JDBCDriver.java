/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.io.FilenameFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.SQLException;
import properties.PropertyConstant;
import properties.PropertyUtil;

/**
 *
 * @author Mohsin
 */
public class JDBCDriver {
    /**
	 * Get the path location of the JDBC Drivers.
	 * It is expected that the driver path is stored in the EngMonApp property files.
	 * 
	 *  Property: (PropertyConstants.java)
	 *  jdbc.driver.path=<PATH>
	 * 
	 * @return
	 * Return the full folder path where the JDBC driver jars are located.
	 * 
	 */
	static private File getDriverLocation() throws SQLException, Exception {
		
		
		// property name and value
	
		String driverPath = PropertyUtil.readSingleProperties(PropertyConstant.jdbc_driver_path);
		
		// Check the driver property is complete
		if (driverPath == null) {
			throw new Exception();
		}

		// get a file pointer to the driver directory
		File driverFolder = new File(driverPath);

		// check that the driver path is a valid path
		driverFolder = new File(driverPath);
		if (driverFolder.exists() == false) {
			throw new Exception();
		}
		
		// return a valid folder path
		return driverFolder;
	}
	
	
	
	/**
	 * Read the list of jdbc jar files and convert into type: URLClassLoader
	 * 
	 * @return
	 * Return a URLClassLoader Object that contains the URLClass for all
	 * jars found in the driver location.
	 * 
	 * @throws Exception
	 */
	static public URLClassLoader initialize() throws Exception	{
		
		URLClassLoader m_classpath = null;
		
		// Get the driver folder path
		File jdbcDriverDir = getDriverLocation();		
		
		// Get the list of driver jar files
		File[] driverJarFiles = jdbcDriverDir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
					return name.endsWith(".jar");
				}
			});

		// build URL object with driver information
		URL[] driverUrls = new URL[driverJarFiles.length];

		// Setup the URL for the driver files
		try	{				
			for (int i = 0; i < driverJarFiles.length; i++) 	{
				driverUrls[i] = driverJarFiles[i].toURI().toURL();
			}				
		} catch (MalformedURLException urlException) {	
			throw new Exception("MalformedURLException="+urlException.getMessage());
		}

		// add the drivers to the classpath
		m_classpath = new URLClassLoader(driverUrls);
		
		return m_classpath;
	}
    
}
