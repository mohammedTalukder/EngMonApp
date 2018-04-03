/**
 * ****************************************************************************
 *
 * JobRunDataLocation.java
 * 
*******************************************************************************
 *
 * Maintenance log - most recent change descriptions at top
 * 
 * Date.... .WHO....... Description.............. 
 * Mar 21, 2013  Mohammed Talukder 
 * 
******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etlobject;

/**
 *
 * @author Mohammed Talukder
 */
public class JobRunDataLocation {
    
    private String jobRunID;
            int isSource,isTarget,dlID;

    /**
     * @return the jobRunID
     */
    public String getJobRunID() {
        return jobRunID;
    }

    /**
     * @param jobRunID the jobRunID to set
     */
    public void setJobRunID(String jobRunID) {
        this.jobRunID = jobRunID;
    }

    /**
     * @return the dlID
     */
    public int getDlID() {
        return dlID;
    }

    /**
     * @param dlID the dlID to set
     */
    public void setDlID(int dlID) {
        this.dlID = dlID;
    }

    /**
     * @return the isSource
     */
    public int getIsSource() {
        return isSource;
    }

    /**
     * @param isSource the isSource to set
     */
    public void setIsSource(int isSource) {
        this.isSource = isSource;
    }

    /**
     * @return the isTarget
     */
    public int getIsTarget() {
        return isTarget;
    }

    /**
     * @param isTarget the isTarget to set
     */
    public void setIsTarget(int isTarget) {
        this.isTarget = isTarget;
    }
    

}
