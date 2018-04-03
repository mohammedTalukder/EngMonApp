/**
 * ****************************************************************************
 *
 * DataLocation.java
 * 
*******************************************************************************
 *
 * Maintenance log - insert most recent change descriptions at top
 * 
* Date.... .WHO....... Description.............. 
* 15-01-2013 Mohammed Talukder Initial implementation
 * 
******************************************************************************/

package etlobject;

import java.io.Serializable;

/**
 *
 * @author tm024
 */
public class DataLocation implements Serializable  {
    
    private Long dataLocationID, jobRunID;
    private String dbName,schemaName,tableName, location;
    private byte isTarget,isSource;
    
    public DataLocation(){
        
    }

    /**
     * @return the dataLocationID
     */
    public Long getDataLocationID() {
        return dataLocationID;
    }

    /**
     * @param dataLocationID the dataLocationID to set
     */
    public void setDataLocationID(Long dataLocationID) {
        this.dataLocationID = dataLocationID;
    }

    /**
     * @return the jobRunID
     */
    public Long getJobRunID() {
        return jobRunID;
    }

    /**
     * @param jobRunID the jobRunID to set
     */
    public void setJobRunID(Long jobRunID) {
        this.jobRunID = jobRunID;
    }

    /**
     * @return the dbName
     */
    public String getDbName() {
        return dbName;
    }

    /**
     * @param dbName the dbName to set
     */
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    /**
     * @return the schemaName
     */
    public String getSchemaName() {
        return schemaName;
    }

    /**
     * @param schemaName the schemaName to set
     */
    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    /**
     * @return the tableName
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * @param tableName the tableName to set
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the isTarget
     */
    public byte getIsTarget() {
        return isTarget;
    }

    /**
     * @param isTarget the isTarget to set
     */
    public void setIsTarget(byte isTarget) {
        this.isTarget = isTarget;
    }

    /**
     * @return the isSource
     */
    public byte getIsSource() {
        return isSource;
    }

    /**
     * @param isSource the isSource to set
     */
    public void setIsSource(byte isSource) {
        this.isSource = isSource;
    }
    
    
}
