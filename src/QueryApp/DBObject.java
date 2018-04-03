/**
 * ****************************************************************************
 *
 * DBObject.java
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

package QueryApp;

/**
 *
 * @author Mohammed Talukder
 */
public class DBObject {
    public static final String SCHEMA = "dbo";
    //constant for tables
    public static final String TABLE_HOST = "Host";
    public static final String TABLE_PROJECT = "Project";
    public static final String TABLE_JOB = "job";
    public static final String TABLE_JOBRUN = "JobRun";
    public static final String TABLE_JOBRUNSTATUS = "JobRunStatus";
    public static final String TABLE_DATALOCATION="DataLocation";
    public static final String TABLE_JOBRUNDATALOCATION="JobRunDataLocation";
    //constant for columns
    public static final String HOST_HOSTID = "HostID",
                        HOST_HOSTNAME = "HostName";
    public static final String PROJECT_PROJECTID = "ProjectID",
                        PROJECT_PROJECTNAME = "ProjectName",
                        PROJECT_HOSTID = "fk_HostID",
                        PROJECT_PROJECTPATH = "LocalPath";
    public static final String JOB_JOBID="JobID",
                        JOB_JOBNAME="JobName",
                        JOB_PROJECTID="fk_ProjectID",
                        JOB_PATH="LocalPath";
    //Insert queries
    public static final String QUERY_INSERT_HOST = "insert into dbo.Host values (?)";
    public static final String QUERY_INSERT_PROJECT = "insert into dbo.Project (fk_HostID,ProjectName,ProjectPath) values(?,?,?)";

}
