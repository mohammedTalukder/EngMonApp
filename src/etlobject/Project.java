/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etlobject;

import java.io.Serializable;

/**
 *
 * @author tm024
 */
public class Project implements Serializable  {
    private Long projectID;
    private int hostID;
    private String projectName,
            localPath;
    
    public Project(){
        
    }

    /**
     * @return the projectID
     */
    public Long getProjectID() {
        return projectID;
    }

    /**
     * @param projectID the projectID to set
     */
    public void setProjectID(Long projectID) {
        this.projectID = projectID;
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
     * @return the projectName
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * @param projectName the projectName to set
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * @return the localPath
     */
    public String getLocalPath() {
        return localPath;
    }

    /**
     * @param localPath the localPath to set
     */
    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }
    
}
