/**
 * ****************************************************************************
 *
 * Host.java
 * 
*******************************************************************************
 *
 * Maintenance log - most recent change descriptions at top
 * 
 * Date.... .WHO....... Description.............. 
 * 16/01/2013  Mohammed Talukder initial implementation 
 * 
******************************************************************************/
package etlobject;

import java.io.Serializable;

/**
 *
 * @author tm024
 */
public class Host implements Serializable {
    private int hostID;
    private String hostName;
    private String macAddress;
    private String physicalAddress,
            OS;
    private int memory,freeMemory;
    private java.sql.Date date;
    
    public Host(){
        
    }

    /**
     * @return the hostID
     */
    public int getHostID() {
        return hostID;
    }

    /**
     * @param hostID the hostID to set
     */
    public void setHostID(int hostID) {
        this.hostID = hostID;
    }

    /**
     * @return the hostName
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * @param hostName the hostName to set
     */
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    /**
     * @return the macAddress
     */
    public String getMacAddress() {
        return macAddress;
    }

    /**
     * @param macAddress the macAddress to set
     */
    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    /**
     * @return the physicalAddress
     */
    public String getPhysicalAddress() {
        return physicalAddress;
    }

    /**
     * @param physicalAddress the physicalAddress to set
     */
    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }

    /**
     * @return the OS
     */
    public String getOS() {
        return OS;
    }

    /**
     * @param OS the OS to set
     */
    public void setOS(String OS) {
        this.OS = OS;
    }

    /**
     * @return the memory
     */
    public int getMemory() {
        return memory;
    }

    /**
     * @param memory the memory to set
     */
    public void setMemory(int memory) {
        this.memory = memory;
    }

    /**
     * @return the freeMemory
     */
    public int getFreeMemory() {
        return freeMemory;
    }

    /**
     * @param freeMemory the freeMemory to set
     */
    public void setFreeMemory(int freeMemory) {
        this.freeMemory = freeMemory;
    }

    /**
     * @return the date
     */
    public java.sql.Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(java.sql.Date date) {
        this.date = date;
    }
    
}
