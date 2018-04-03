/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etlobject;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author tm024
 */
public class JobRun implements Serializable  {

    private Long jobRunStatusID;
    private Date runStartTime,
                 runEndTime;
    private int rowsIn,rowsOut;
    
    private String errorMessage,
                    jobID,
                    jobRunID;
    private String dataIn;
    private String dataOut;
    
    
    public JobRun(){
        
    }

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
     * @return the jobID
     */
    public String getJobID() {
        return jobID;
    }

    /**
     * @param jobID the jobID to set
     */
    public void setJobID(String jobID) {
        this.jobID = jobID;
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
     * @return the runStartTime
     */
    public Date getRunStartTime() {
        return runStartTime;
    }

    /**
     * @param runStartTime the runStartTime to set
     */
    public void setRunStartTime(Date runStartTime) {
        this.runStartTime = runStartTime;
    }

    /**
     * @return the runEndTime
     */
    public Date getRunEndTime() {
        return runEndTime;
    }

    /**
     * @param runEndTime the runEndTime to set
     */
    public void setRunEndTime(Date runEndTime) {
        this.runEndTime = runEndTime;
    }

    /**
     * @return the rowsIn
     */
    public int getRowsIn() {
        return rowsIn;
    }

    /**
     * @param rowsIn the rowsIn to set
     */
    public void setRowsIn(int rowsIn) {
        this.rowsIn = rowsIn;
    }

    /**
     * @return the rowsOut
     */
    public int getRowsOut() {
        return rowsOut;
    }

    /**
     * @param rowsOut the rowsOut to set
     */
    public void setRowsOut(int rowsOut) {
        this.rowsOut = rowsOut;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @return the dataIn
     */
    public String getDataIn() {
        return dataIn;
    }

    /**
     * @param dataIn the dataIn to set
     */
    public void setDataIn(String dataIn) {
        this.dataIn = dataIn;
    }

    /**
     * @return the dataOut
     */
    public String getDataOut() {
        return dataOut;
    }

    /**
     * @param dataOut the dataOut to set
     */
    public void setDataOut(String dataOut) {
        this.dataOut = dataOut;
    }
    
}
