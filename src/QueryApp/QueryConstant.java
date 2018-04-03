/**
 * ****************************************************************************
 *
 * QueryConstant.java
 * 
*******************************************************************************
 *
 * Maintenance log - insert most recent change descriptions at top
 * 
* Date.... .WHO....... Description.............. 
* 08-02-2013 Mohammed Talukder Initial implementation
 * 
*****************************************************************************
 */
package QueryApp;

/**
 *
 * @author Mohsin
 */
public class QueryConstant {

    /*static final String SCHEMA = "dbo";
    //constant for tables
    static final String TABLE_HOST = "Host";
    static final String TABLE_PROJECT = "Project";
    static final String TABLE_JOB = "job";
    static final String TABLE_JOBRUN = "JobRun";
    static final String TABLE_JOBRUNSTATUS = "JobRunStatus";
    //constant for columns
    static final String HOST_HOSTID = "HostID",
                        HOST_HOSTNAME = "HostName";
    static final String PROJECT_PROJECTID = "ProjectID",
                        PROJECT_PROJECTNAME = "ProjectName",
                        PROJECT_HOSTID = "fk_HostID",
                        PROJECT_PROJECTPATH = "LocalPath";
    static final String JOB_JOBID="JobID",
                        JOB_JOBNAME="JobName",
                        JOB_PROJECTID="fk_ProjectID",
                        JOB_PATH="LocalPath";
    //Insert queries
    public static final String QUERY_INSERT_HOST = "insert into dbo.Host values (?)";
    public static final String QUERY_INSERT_PROJECT = "insert into dbo.Project (fk_HostID,ProjectName,ProjectPath) values(?,?,?)";
    /**
     * sql param 1 = ProjectName(string), 2=fk_HostID(int),
     * 3=ProjectPath(string), 4=ProjectName(string)
     */
    public static final String QUERY_INSERT_HOST_IFNOTEXIST = "insert into " + DBObject.SCHEMA + "."+DBObject.TABLE_HOST+"(HostName)Select ? Where not exists(select * from Host where HostName=?)";
    
    public static final String QUERY_INSERT_PROJECT_IFNOTEXIST = "insert into " + DBObject.SCHEMA +"."+DBObject.TABLE_PROJECT+ "(ProjectName,fk_HostID,ProjectPath) "
                                                                  +"Select ?,?,? Where not exists(select * from "+DBObject.TABLE_PROJECT+" where ProjectName=? and fk_HostID=?)";
    
    public static final String QUERY_UPDATE_HOST="update "+DBObject.SCHEMA+"."+DBObject.TABLE_HOST+" set [PhysicalAddress]=?,OS=?,Memory=?,FreeMemory=? where HostID=?"; 
    
    public static final String QUERY_INSERT_JOB_IFNOTEXIST = "insert into " + DBObject.SCHEMA + "."+DBObject.TABLE_JOB+"(JobID,JobName,fk_ProjectID,LocalPath) "
                                                            +"Select ?,?,?,? Where not exists(select * from "+DBObject.TABLE_JOB+" where JobID=? and JobName=?)";
    
    public static final String QUERY_INSERT_JOBRUN_IFNOTEXIST = "insert into " + DBObject.SCHEMA + "."+DBObject.TABLE_JOBRUN+"(JobRunID,fk_JobID,RunStartTime,RunEndTime,RunDuration,fk_RunStatus,RowsIn,RowsOut,ErrorMessage)"
                                                                +" Select ?,?,?,?,?,?,?,?,? Where not exists(select * from "+DBObject.TABLE_JOBRUN+" where JobRunID=?)";
    public static final String QUERY_UPDATE_JOBRUN="update "+DBObject.SCHEMA+"."+DBObject.TABLE_JOBRUN+" set RunEndTime=?,RunDuration=?,fk_RunStatus=?, RowsIn=?,RowsOut=?, ErrorMEssage=? Where JobRunID=?";
    
    //DataLOcationTable
    public static final String QUERY_INSERT_DATALOCATION="insert into " + DBObject.SCHEMA + "."+DBObject.TABLE_DATALOCATION+"(DataStoreName)"
                                                                +" Select ? Where not exists(select * from "+DBObject.TABLE_DATALOCATION+" where DataStoreName=?)";
    
    //JobRunDataLocation Table
     public static final String QUERY_INSERT_JOBRUNDL="insert into " + DBObject.SCHEMA + "."+DBObject.TABLE_JOBRUNDATALOCATION+"(JobRunID,DataLocatorID,isSource,isTarget) Values(?,?,?,?)";
                                                               
  
    //Select queries
    public static final String QUERY_SELECT_HOST = "select * from dbo.Host";
    public static final String QUERY_SELECT_ALL = "select * from ";
    public static final String QUERY_SELECT_IDENTITY = " select SCOPE_IDENTITY()";
    public static final String QUERY_SELECT_DATALOCATION = "select * from dbo.DataLocation where DataStoreName=?";
}
