/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etlobject;

/**
 *
 * @author Mohsin
 */
public class JobRunObject {
    
    private String jobID, 
            jobRunIDTemp,
            jobRunID,
            projectName,
            jobName,
            jobRunStartTime,
            jobRunEndTime,
            jobRunStatus,
            errorMessage;
    
    private double runDuration;
    
    private int rowsIn,rowsOut,statusCode;
     private String dataIn;
    private String dataOut;

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
     * @return the jobName
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * @param jobName the jobName to set
     */
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    /**
     * @return the jobRunStartTime
     */
    public String getJobRunStartTime() {
        return jobRunStartTime;
    }

    /**
     * @param jobRunStartTime the jobRunStartTime to set
     */
    public void setJobRunStartTime(String jobRunStartTime) {
        this.jobRunStartTime = jobRunStartTime;
    }

    /**
     * @return the jobRunEndTime
     */
    public String getJobRunEndTime() {
        return jobRunEndTime;
    }

    /**
     * @param jobRunEndTime the jobRunEndTime to set
     */
    public void setJobRunEndTime(String jobRunEndTime) {
        this.jobRunEndTime = jobRunEndTime;
    }

    /**
     * @return the jobRunStatus
     */
    public String getJobRunStatus() {
        return jobRunStatus;
    }

    /**
     * @param jobRunStatus the jobRunStatus to set
     */
    public void setJobRunStatus(String jobRunStatus) {
        this.jobRunStatus = jobRunStatus;
    }

    /**
     * @return the runDuration
     */
    public double getRunDuration() {
        return runDuration;
    }

    /**
     * @param runDuration the runDuration to set
     */
    public void setRunDuration(double runDuration) {
        this.runDuration = runDuration;
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
     * @return the jobRunIDTemp
     */
    public String getJobRunIDTemp() {
        return jobRunIDTemp;
    }

    /**
     * @param jobRunIDTemp the jobRunIDTemp to set
     */
    public void setJobRunIDTemp(String jobRunIDTemp) {
        this.jobRunIDTemp = jobRunIDTemp;
    }

    /**
     * @return the statusCode
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode the statusCode to set
     */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
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
