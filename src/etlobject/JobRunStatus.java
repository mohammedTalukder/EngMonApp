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
public class JobRunStatus implements Serializable  {
    private Long jobRunStatusID;
    private String statusDescription;
    
    public JobRunStatus(){
        
    }

    /**
     * @return the jobRunStatusID
     */
    public Long getJobRunStatusID() {
        return jobRunStatusID;
    }

    /**
     * @param jobRunStatusID the jobRunStatusID to set
     */
    public void setJobRunStatusID(Long jobRunStatusID) {
        this.jobRunStatusID = jobRunStatusID;
    }

    /**
     * @return the statusDescription
     */
    public String getStatusDescription() {
        return statusDescription;
    }

    /**
     * @param statusDescription the statusDescription to set
     */
    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }
    
}
