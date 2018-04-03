/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Properties;

/**
 *
 * @author Mohsin
 */
public class JDBCConnection {
    // -----------------
	// GLOBAL VARIABLES
	// ------------------

	// general database variables 
	private String m_dbType = null; 	
	private String m_dbUser = null; 
	private String m_dbPassword = null;
	private String m_dbHost = null; 
	private String m_dbPort = null; 
	private String m_dbName = null; 
	
	// JDBC connection variables
	private Connection m_conn = null;
	private String m_driver = "";
	private MessageFormat m_format = null;
	private String m_urlFormat = null;
	private String m_url = null;
	
	/**
	 * Create a JDBC connection instance.
	 * 
	 * @param propDB
	 * You must create a JDBC properties class before you can 
	 * create a JDBC connection.
	 */
	public JDBCConnection(JDBCProperties propDB) {
		
		//Set Connection Member variable properties
		m_dbType = propDB.get_dbType();
		m_dbUser = propDB.get_dbUser();
		m_dbPassword = propDB.get_dbPassword();
		m_dbHost = propDB.get_dbHost();
		m_dbPort = propDB.get_dbPort();
		m_dbName = propDB.get_dbName();
	
	}
	
	/**
	 * Open a JDBC connection.
	 * Server and login details are taken from the provided connection 
	 * properties class.
	 * 
	 * @return
	 * Return the connection.
	 * 
	 * @throws Exception
	 */
	public Connection openConnection() throws Exception {
		
		// setup the drivers ready for use.
		URLClassLoader classpath = JDBCDriver.initialize();
                //System.out.println(classpath);
		Driver driver = (Driver) Class.forName(getDriver(), true, classpath).newInstance();
	
		// define the user details for the connection
		Properties userDetails = new Properties();
		userDetails.put("user", m_dbUser);
		userDetails.put("password", m_dbPassword);
		
		// create the URL connection string
		//String url = getUrl(); //inegrated security off
                String url = getUrl()+";integratedSecurity=true"; //integrated security true

		System.out.println(url);
		// create a connection to the database
		try {
			 m_conn = driver.connect(url, userDetails);
		} catch (SQLException sql) {
			//throw new JDBCConnectionException(getDriverDetails(),sql.getErrorCode(),sql.getSQLState());
                    sql.printStackTrace();
		}

		return this.m_conn; 			
	}
	
	/**
	 * Close the current JDBC connection
	 * 
	 * @throws Exception
	 */
	public void closeConnection() throws Exception {
	
		this.m_conn.close();
	}
	
	
	/**
	 * Get the basic details about the current JDBC driver.
	 * 
	 * @throws Exception
	 */
	public String[] getDriverDetails() throws Exception {
	
		String[] details = {
			"Driver=" +this.m_driver,
			"MessageFormat="+ this.m_format.toPattern().toString(),
			"MessageLocale="+ this.m_format.getLocale().toString(),
			"urlFormat=" +this.m_urlFormat,
			"url="+this.m_url,
			"UserName=" +this.m_dbUser,
			"Password=" +this.m_dbPassword
		};
		return details;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getdbType() throws Exception {
		return this.m_dbType;
	}
	
	
	//
	// Generate the URL String
	//
	private String getUrl()	{
		
		m_url =  getURLProto() + ":" + getURLBase();
		return m_url;
	}
	
	//
	// Generate the URL protocol String
	//
	private String getURLProto() {

		String dbType = "unknown URL Protocol for("+m_dbType+")";
	
		if (m_dbType.equalsIgnoreCase("oracle")) {
			dbType = "jdbc:oracle";
		}
		else if (m_dbType.equalsIgnoreCase("sqlserver")) {
			dbType = "jdbc:sqlserver";
		}
		else if (m_dbType.equalsIgnoreCase("mysql")) {
			dbType = "mysql";
		}
		return dbType;
	}
	
	
	//
	// Generate the URLBase String
	//
	private String getURLBase()	{
		
		m_urlFormat = "unknown URL Base for("+m_dbType+")";
		
    
    		m_urlFormat = getURLFormat().format(new Object[] {m_dbHost, m_dbPort, m_dbName});
    	
    	
    	return m_urlFormat;
	}
	
	//
	// Set the message format
	//
	private MessageFormat getURLFormat()
	{
		m_format = new MessageFormat("unknown URL Format for("+m_dbType+")");
	
		if (m_dbType.equalsIgnoreCase("oracle")) {
			m_format = new MessageFormat("//{0}:{1};SID={2}");
		}
		else if (m_dbType.equalsIgnoreCase("sqlserver")) {
			m_format = new MessageFormat("//{0}:{1};DatabaseName={2}");
		}

		return m_format;

	}
	
	//
	// Get the JDBC Driver Jar files
	//
	private String getDriver()	{
		
		m_driver = "unknown jdbc Driver("+m_dbType+")";
            if (m_dbType.equalsIgnoreCase("oracle"))   {
	    	m_driver =  "";
	    }
	 
	    else if (m_dbType.equalsIgnoreCase("sqlserver")) {
	    	m_driver =  "com.microsoft.sqlserver.jdbc.SQLServerDriver";
                //System.out.println();
	    }
	    
    	return m_driver;
	}
    
}
