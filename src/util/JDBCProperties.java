/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Properties;
import properties.PropertyConstant;
import properties.PropertyUtil;

/**
 *
 * @author Mohsin
 */
public class JDBCProperties {
    // -----------------
	// GLOBAL VARIABLES
	// ------------------

	// General Database connection details
	private String m_dbType = null;	
	private String m_dbUser = null;
	private String m_dbPass = null;
	private String m_dbHost = null;
	private String m_dbPort = null;
	private String m_dbName = null;
	

	
	 
	/**
	 * Set the JDBC properties automatically from the properties files.
	 * 
	 * @throws Exception
	 */
	public void auto_set_defaults() throws Exception {
		
		//Properties prop = new Properties();
		
		m_dbType = PropertyUtil.readSingleProperties(PropertyConstant.jdbc_dbType);
		m_dbUser = PropertyUtil.readSingleProperties(PropertyConstant.jdbc_dbUser);
		m_dbPass = PropertyUtil.readSingleProperties(PropertyConstant.jdbc_dbPass);
		m_dbHost = PropertyUtil.readSingleProperties(PropertyConstant.jdbc_dbHost);
		m_dbPort = PropertyUtil.readSingleProperties(PropertyConstant.jdbc_dbPort);
		m_dbName = PropertyUtil.readSingleProperties(PropertyConstant.jdbc_dbName);
		
		
		
	}
	
	
	/**
	 * Set the Database Type.
	 * 
	 * @param dbtype
	 */
	public void set_dbType(String dbtype) {
		m_dbType = dbtype;
	}
	
	/** 
	 * @return
	 * Get the currently configured Database type.
	 */
	public String get_dbType() {
		return m_dbType;
	}
	
	/**
	 * 
	 * @param dbUser
	 * Set the User name for the JDBC connection. 
	 */
	public void set_dbUser(String dbUser) {
		m_dbUser = dbUser;
	}
	
	/**
	 * 
	 * @return
	 * Get the currently configured user name.
	 * 
	 */
	public String get_dbUser() {
		return m_dbUser;
	}
	
	/**
	 * 
	 * @return
	 * Get the currently configured user password.
	 * 
	 */
	public String get_dbPassword() {
		return m_dbPass;
	}


	/**
	 * 
	 * @param password
	 * Set the user password.
	 * 
	 */
	public void set_dbPassword(String password) {
		m_dbPass = password;
	}


	/**
	 * 
	 * @return
	 * Get the currently configured database Host name.
	 * 
	 */
	public String get_dbHost() {
		return m_dbHost;
	}


	/**
	 * 
	 * @param host
	 * Set the Database Host name.
	 * 
	 */
	public void set_dbHost(String host) {
		m_dbHost = host;
	}


	/**
	 * 
	 * @return
	 * Get the currently configured Database port number.
	 * 
	 */
	public String get_dbPort() {
		return m_dbPort;
	}


	/**
	 * 
	 * @param port
	 * Set the Database connection port number
	 */
	public void set_dbPort(String port) {
		m_dbPort = port;
	}


	/**
	 * 
	 * @return
	 * Get the currently configured Database name.
	 * 
	 */
	public String get_dbName() {
		return m_dbName;
	}


	/**
	 * 
	 * @param name
	 * Set the database name to connect to.
	 * 
	 */
	public void set_dbName(String name) {
		m_dbName = name;
	}



    
}
