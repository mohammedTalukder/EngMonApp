/**
 * ****************************************************************************
 *
 * QueryApp.java
 *
 * @author Mohammed Talukder
 * 
*******************************************************************************
 *
 * Maintenance log - insert most recent change descriptions at top
 *
 * Date.... .WHO....... Description.............. 
 * 17-03-2013 MT        method to write dataLocation record added
 * 09-03-2013 MT        writeJobInfo()changed to void, jobID changed from Long to String JobID is now passed with
 *                      insert query, no need to return jobID any more 
 * 02-02-2013 Mohammed Talukder  Initial implementation
 * 
*****************************************************************************
 */
package QueryApp;

import QueryApp.QueryConstant;
import etlobject.Host;
import etlobject.Job;
import etlobject.JobRunDataLocation;
import etlobject.JobRunObject;
import etlobject.LogTypeConstant;
import etlobject.Project;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mohammed Talukder
 */
public class QueryApp {

    /**
     *
     * @param host
     */
    public static int writeHostInfo(Connection conn, Host host) {
        int hostID = 0;
        try {

            PreparedStatement updateHost;

            //if host not exist in table write host info in database
            String queryInsert = QueryConstant.QUERY_INSERT_HOST_IFNOTEXIST;
            updateHost = conn.prepareStatement(queryInsert);
            updateHost.setString(1, host.getHostName());
            updateHost.setString(2, host.getHostName());
            boolean queryStatus = updateHost.execute();
            System.out.println(queryStatus ? "Host name written in host table" : "host exist in table");
            //now reytreive host id from Host Table            
            ResultSet rs_host = hostTableData(conn);

            while (rs_host.next()) {
                //
                if (rs_host.getString("HostName").equals(host.getHostName())) {
                    hostID = rs_host.getInt("HostID");
                    System.out.println(hostID);
                    break;
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(QueryApp.class.getName()).log(Level.SEVERE, null, ex);

        }

        return hostID;
    }//end write host

    /**
     *
     * @param conn
     * @param host
     */
    public static void updateHostTable(Connection conn, Host host) {
        int hostID = host.getHostID();

        try {

            PreparedStatement updateHost;
            //"update "+SCHEMA+"."+TABLE_HOST+"set PhysicalAddress=?,OS=?,Memory=?,FreeMemory=? where HostID=?";
            //if host not exist in table write host info in database
            String queryString = QueryConstant.QUERY_UPDATE_HOST;
            updateHost = conn.prepareStatement(queryString);
            updateHost.setString(1, host.getPhysicalAddress());
            updateHost.setString(2, host.getOS());
            updateHost.setInt(3, host.getMemory());
            updateHost.setInt(4, host.getFreeMemory());
            updateHost.setInt(5, hostID);
            updateHost.execute();
            System.out.println("Host info in host table");

        } catch (Exception ex) {
            Logger.getLogger(QueryApp.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    /**
     *
     * @param conn
     * @return
     */
    public static ResultSet hostTableData(Connection conn) {

        ResultSet rs = null;
        try {

            PreparedStatement selectHost;
            String querySelect = QueryConstant.QUERY_SELECT_ALL + DBObject.SCHEMA + "." + DBObject.TABLE_HOST;
            selectHost = conn.prepareStatement(querySelect);
            rs = selectHost.executeQuery();



        } catch (Exception ex) {
            Logger.getLogger(QueryApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    /**
     *
     * @param conn
     * @param project
     */
    public static Long writeProjectInfo(Connection conn, Project project) {

        Long projectID = 0L;
        try {
            PreparedStatement writeProject = conn.prepareStatement(QueryConstant.QUERY_INSERT_PROJECT_IFNOTEXIST);
            writeProject.setString(1, project.getProjectName());
            writeProject.setInt(2, project.getHostID());
            writeProject.setString(3, project.getLocalPath());
            writeProject.setString(4, project.getProjectName());
            writeProject.setInt(5, project.getHostID());
            System.out.println(writeProject.toString());
            writeProject.execute();
            System.out.println("Project written in project table");
            //now get the projectID to be used for writing into job table
            //SQL Query string select * from project table       
            String querySelect = QueryConstant.QUERY_SELECT_ALL + DBObject.SCHEMA + "." + DBObject.TABLE_PROJECT;
            PreparedStatement selectProject;

            selectProject = conn.prepareStatement(querySelect);
            //selectProject.setString(1, "dbo.Project");
            ResultSet rs = selectProject.executeQuery();
            while (rs.next()) {
                if (rs.getString("ProjectName").equals(project.getProjectName()) && (rs.getInt(DBObject.PROJECT_HOSTID) == project.getHostID())) {

                    projectID = rs.getLong(DBObject.PROJECT_PROJECTID);
                    System.out.println(projectID);
                    break;
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(QueryApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return projectID;

    }//end readhost table

    /**
     * writeJobInfo()
     *
     * @param conn
     * @param newJob
     * @return void
     *
     */
    public static void writeJobInfo(Connection conn, Job newJob) {
        String JobID;
        try {
            PreparedStatement writeJob = conn.prepareStatement(QueryConstant.QUERY_INSERT_JOB_IFNOTEXIST);
            writeJob.setString(1, newJob.getJobID());
            writeJob.setString(2, newJob.getJobName());
            writeJob.setLong(3, newJob.getProjectID());
            writeJob.setString(4, newJob.getLocalPath());
            writeJob.setString(5, newJob.getJobID());
            writeJob.setString(6, newJob.getJobName());
            //System.out.println(writeJob.toString());
            writeJob.execute();
            System.out.println("Job written in project table");
            //now get the JobID to be used for writing into job table
            //SQL Query string select * from project table       
            String querySelect = QueryConstant.QUERY_SELECT_ALL + DBObject.SCHEMA + "." + DBObject.TABLE_JOB;
            PreparedStatement selectJob;

            selectJob = conn.prepareStatement(querySelect);
            //selectProject.setString(1, "dbo.Project");
            ResultSet rs = selectJob.executeQuery();
            while (rs.next()) {
                if (rs.getString("JobName").equals(newJob.getJobName())) {

                    JobID = rs.getString(DBObject.JOB_JOBID);
                    System.out.println(JobID);
                    break;
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(QueryApp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//end writeJobInfo()

    /**
     * writeJobRunInfo() - write jobRunInfo when jobRun start with
     * jobRunStatus=Running
     *
     * @param Connection
     * @param JobRunObject
     * @return void
     */
    public static void writeJobRunInfo(Connection conn, JobRunObject jobRun) {
        //"insert into " + SCHEMA + "."+TABLE_JOBRUN+"(JobRunID,fk_JobID,RunStartTime,RunEndTime,RunDuration,fk_RunStatus,RowsIn,RowsOut,ErrorMessage)"
        //+" Select ?,?,?,?,?,?,?,?,? Where not exists(select * from Job where JobRunID=?)";
        String jobRunID = jobRun.getJobRunID();
        try {
            //build job run start/end time as sql date 
            java.sql.Timestamp jobRunStart = convertDate(jobRun.getJobRunStartTime());
            Long jobRunEnd = 0L;

            PreparedStatement writeJobRun = conn.prepareStatement(QueryConstant.QUERY_INSERT_JOBRUN_IFNOTEXIST);
            writeJobRun.setString(1, jobRunID);
            writeJobRun.setString(2, jobRun.getJobID());
            writeJobRun.setTimestamp(3, jobRunStart);
            writeJobRun.setLong(4, jobRunEnd);
            writeJobRun.setDouble(5, jobRun.getRunDuration());
            //buildJobRun StatusCode 1=success, 2=failed, 3=running, 4= warning
            writeJobRun.setInt(6, setJobRunStatusCode(jobRun.getJobRunStatus()));
            writeJobRun.setLong(7, jobRun.getRowsIn());
            writeJobRun.setLong(8, jobRun.getRowsOut());
            writeJobRun.setString(9, jobRun.getErrorMessage());
            writeJobRun.setString(10, jobRunID);
            //System.out.println(writeJob.toString());
            writeJobRun.execute();
            System.out.println("Job Run data written in JobRun table");
            //now get the JobID to be used for writing into job table
            //SQL Query string select * from project table
            /* String querySelect = QueryConstant.QUERY_SELECT_ALL + QueryConstant.SCHEMA + "." + QueryConstant.TABLE_JOBRUN;
             PreparedStatement selectJobRun;
             selectJob = conn.prepareStatement(querySelect);
             //selectProject.setString(1, "dbo.Project");
             ResultSet rs = selectJob.executeQuery();
             while (rs.next()) {
             if (rs.getString("JobName").equals(newJob.getJobName())) {
             JobID = rs.getString(QueryConstant.JOB_JOBID);
             System.out.println(JobID);
             break;
             }
             }*/

        } catch (ParseException | SQLException ex) {
            Logger.getLogger(QueryApp.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//end write jobRunInfo

    /**
     *
     * @param conn
     * @param jobRun
     */
    public static void updateJobRunInfo(Connection conn, JobRunObject jobRun) {
        //"update "+SCHEMA+"."+TABLE_JOBRUN+" set RunEndTime=?,RunDuration=?,fk_RunStatus=?, RowsIn=?,RowsOut=?, ErrorMEssage=? Where JobRunID=?";
        String jobRunID = jobRun.getJobRunID();
        JobRunDataLocation jrdl;
        try {

            java.sql.Timestamp jobRunEnd = convertDate(jobRun.getJobRunEndTime());

            PreparedStatement writeJobRun = conn.prepareStatement(QueryConstant.QUERY_UPDATE_JOBRUN);
            PreparedStatement writeJobRunDLS = conn.prepareStatement(QueryConstant.QUERY_INSERT_JOBRUNDL);
            PreparedStatement writeJobRunDLT = conn.prepareStatement(QueryConstant.QUERY_INSERT_JOBRUNDL);
            

            writeJobRun.setTimestamp(1, jobRunEnd);
            writeJobRun.setDouble(2, jobRun.getRunDuration());
            //buildJobRun StatusCode 1=success, 2=failed, 3=running, 4= warning
            writeJobRun.setInt(3, setJobRunStatusCode(jobRun.getJobRunStatus()));
            writeJobRun.setLong(4, jobRun.getRowsIn());
            writeJobRun.setLong(5, jobRun.getRowsOut());
            writeJobRun.setString(6, jobRun.getErrorMessage());
            writeJobRun.setString(7, jobRunID);
            //System.out.println(writeJob.toString());
            writeJobRun.execute();
            System.out.println("JobRun updated JobRun table");

            /**
             * now write datalocation info
             */
            //write into datalocation table and //get dataInLoc key
            //if(jobRun.getDataIn())
            String dataInLoc=jobRun.getDataIn();
            String dataOutLoc=jobRun.getDataOut();
            //if()
            int dataInID = writeDataLocation(conn, jobRun.getDataIn());
            int dataOutID = writeDataLocation(conn, jobRun.getDataOut());
            //write source
            jrdl=new JobRunDataLocation();
               jrdl.setJobRunID(jobRunID);
               jrdl.setDlID(dataInID);
               jrdl.setIsSource(1);
               jrdl.setIsTarget(0);
               //write target
               writejobRunDL(conn,jrdl);
               jrdl=new JobRunDataLocation();
               jrdl.setJobRunID(jobRunID);
               jrdl.setDlID(dataOutID);
               jrdl.setIsSource(0);
               jrdl.setIsTarget(1);
               
               writejobRunDL(conn,jrdl);
               
               //write Target
               
               
            
               
            //write jobRunDataLocation table



        } catch (ParseException | SQLException ex) {
            Logger.getLogger(QueryApp.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//end UpdatejobRunInfo
    
        private static void writejobRunDL(Connection conn, JobRunDataLocation jrdl) {
        try {            
            PreparedStatement writeJobRunDL = conn.prepareStatement(QueryConstant.QUERY_INSERT_JOBRUNDL);
            writeJobRunDL.setString(1, jrdl.getJobRunID());
               writeJobRunDL.setInt(2,jrdl.getDlID());
               writeJobRunDL.setInt(3, jrdl.getIsSource());
               writeJobRunDL.setInt(4, jrdl.getIsTarget());
               writeJobRunDL.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(QueryApp.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    /**
     *
     * @param dateString
     * @return long date
     * @throws ParseException
     */
    private static Long convertDateLong(String dateString) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        java.util.Date date = sdf.parse(dateString);
        Long lngDate = date.getTime();
        System.out.println(lngDate);


        return lngDate;

    }//end convert Date

    public static java.sql.Timestamp convertDate(String strDate) throws ParseException {
        //String datetimeString = "2010-05-23 23:32:45"; 
        Date result;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        java.util.Date date = sdf.parse(strDate);
        Long lngDate = date.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        result = formatter.parse(strDate);
        java.sql.Timestamp dateTime = new java.sql.Timestamp(lngDate);

        System.out.println("SQL date " + dateTime);

        return dateTime;


    }

    private static int setJobRunStatusCode(String status) {
        int statusCode;

        switch (status) {
            case "Running":
                statusCode = 3;
                break;
            case "success":
                statusCode = 1;
                break;
            case "warning":
                statusCode = 4;
                break;
            case "failure":
                statusCode = 2;
                break;
            default:
                statusCode = 5;
                break;
        }
        return statusCode;

    }

    private static int writeDataLocation(Connection conn, String dataIn) {
        try {
            PreparedStatement writeDL = conn.prepareStatement(QueryConstant.QUERY_INSERT_DATALOCATION);
            writeDL.setString(1, dataIn);
            writeDL.setString(2, dataIn);
            writeDL.execute();
            System.out.println("DataLocation written table");
            //now get datalocation id
            PreparedStatement getDL = conn.prepareStatement(QueryConstant.QUERY_SELECT_DATALOCATION);
            getDL.setString(1, dataIn);
            ResultSet rs = getDL.executeQuery();

            while (rs.next()) {
                if (rs.getString("DataStoreName").equals(dataIn)) {
                    return rs.getInt("LocationID");
                    //System.out.println();
                    //break;
                }

            }
            //return 0;
        } catch (SQLException ex) {
            Logger.getLogger(QueryApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }


}
