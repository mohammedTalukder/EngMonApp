/**
 * ****************************************************************************
 *
 * EngMonApp.java
 * 
*******************************************************************************
 *
 * Maintenance log - insert most recent change descriptions at top
 * 
* Date.... .WHO....... Description.............. 23-01-2013 Mohammed Talukder
 * Initial implementation
 * 
*****************************************************************************
 */
package engmonapp;

import QueryApp.QueryConstant;
import etlobject.Host;
import etlobject.JobRunObject;
import etlobject.LogTypeConstant;
import etlobject.Project;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import java.util.Properties;
import util.FileRead;
import properties.PropertyUtil;
import properties.PropertyConstant;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.WriteAbortedException;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import util.FileOperationUtility;
import util.JDBCConnection;
import QueryApp.QueryApp;
import etlobject.DataLocation;
import etlobject.Job;
import etlobject.JobRun;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.net.NetworkInterface;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.management.MBeanServerConnection;
import util.JDBCProperties;

/**
 *
 * @author Mohammed
 */
public class EngMonApp {

    static String userPath;
    //
    // Read 1st level property file
    //
    static File dir;
    JobRunObject jobRunInst;
    Project newProject;
    Job newJob;
    JobRun newJobRun;
    DataLocation newDataLocation;
    ArrayList<Integer> rowInOut;
    ArrayList<String> dataObj;
    Hashtable<String, String> engMonProp;// = new Hashtable<>();
    Host engMonAppHost;
    static boolean firstRun;
    JDBCProperties jdbc_prop;
    JDBCConnection jdbc_conn;
    Connection conn;
    int hostID;

    public EngMonApp() {
        engMonProp = new Hashtable<>();
        engMonAppHost = new Host();
        firstRun = true;

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, SQLException, Exception {
        //get Host Computer name
        String computername = InetAddress.getLocalHost().getHostName();
        InetAddress ip = InetAddress.getLocalHost();
        String add=InetAddress.getLocalHost().getHostAddress();
        System.out.println("Host Name: " + computername);

        //create instance of EngMonApp
        EngMonApp mon = new EngMonApp();
        mon.engMonAppHost.setHostName(computername);
        mon.jobRunInst = new JobRunObject();
        mon.jdbc_prop = new JDBCProperties();
        mon.jdbc_prop.auto_set_defaults();

        mon.jdbc_conn = new JDBCConnection(mon.jdbc_prop);
        mon.conn = mon.jdbc_conn.openConnection();

        //write host info in db
        mon.hostID = QueryApp.writeHostInfo(mon.conn, mon.engMonAppHost);
        mon.engMonAppHost.setHostID(mon.hostID);
        // following 5 lines' source: http://www.mkyong.com/java/how-to-get-mac-address-in-java/
        NetworkInterface network = NetworkInterface.getByInetAddress(ip);
        byte[] mac = network.getHardwareAddress();
        StringBuilder macAddress = new StringBuilder();
        long memorySize = ((com.sun.management.OperatingSystemMXBean) ManagementFactory
        .getOperatingSystemMXBean()).getTotalPhysicalMemorySize();
        double load=((com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean()).getSystemLoadAverage();
        String os=System.getProperty("os.name");
        
        
        System.out.println(load);
        System.out.println(Runtime.getRuntime().freeMemory()/8/1024/1024); 
        for (int i = 0; i < mac.length; i++) {
            macAddress.append(String.format("%02X%s", mac[i],  ""));
        }
        //OperatingSystemMXBean osMXBean = ManagementFactory.getOperatingSystemMXBean();
        //osMXBean.getClass().getDeclaredMethods(
        memorySize=memorySize/1024/1024;
        System.out.println(memorySize);
        mon.engMonAppHost.setOS(os);
        mon.engMonAppHost.setMemory((int)memorySize);
        mon.engMonAppHost.setPhysicalAddress(add);
        mon.engMonAppHost.setFreeMemory(0);
       // mon.engMonAppHost.set
        QueryApp.updateHostTable(mon.conn, mon.engMonAppHost);
        mon.engMonAppHost.setMacAddress(macAddress.toString());
        System.out.println("Current MAC address : " + mon.engMonAppHost.getMacAddress());

        //start monitoring	
        mon.MonitorFiles(PropertyUtil.readSingleProperties(PropertyConstant.directoryName));
    }

    /**
     *
     * @param directoryName
     */
    public void MonitorFiles(String directoryName) {
        try {
            String logFileName = PropertyUtil.readSingleProperties(PropertyConstant.fileName);
            System.out.println("ETL Log file Name and Path: " + logFileName);
            WatchService watchService = FileSystems.getDefault().newWatchService();
            //System.out.println(directoryName);
            Path path = Paths.get(directoryName);


            path.register(watchService, ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE);
            while (true) {
                WatchKey key = watchService.take();
                for (WatchEvent<?> watchEvent : key.pollEvents()) {

                    //getting the type of the event

                    WatchEvent.Kind<?> kind = watchEvent.kind();

                        //getting the file name from the WatchEvent object

                    Path file = (Path) watchEvent.context();
                    //  System.out.println("Filename --> " + file + " Event type --> " + kind.name());

                    //File modilfied? read file
                    if (kind == ENTRY_MODIFY) {
                        processFile(logFileName);

                    }
                }

                key.reset();
                //System.out.print("new Run");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void processFile(String fileName) throws Exception {

        Date dt = new Date();
        String mydate = "2011-11-29 12:34:25";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = null;

        try {
            date = sdf.parse(mydate);
            System.out.println(date);
        } catch (Exception ex) {
            // handle exception
        }
        String datetimeString = "2010-05-23 23:32:45"; 
        Date result; 
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
        result = formatter.parse (datetimeString); 
        java.sql.Date sqlDate = new java.sql.Date(result.getTime()); 
        java.sql.Time sqlTime = new java.sql.Time(result.getTime()); 
        System.out.println("SQL date " + sqlDate + " " + sqlTime);

        //regular expression for timestamp
        String regexTimestamp = "((?:2|1)\\d{3}(?:-|\\/)(?:(?:0[1-9])|(?:1[0-2]))(?:-|\\/)(?:(?:0[1-9])|(?:[1-2][0-9])|(?:3[0-1]))(?:T|\\s)(?:(?:[0-1][0-9])|(?:2[0-3])):(?:[0-5][0-9]):(?:[0-5][0-9]))";
        try (BufferedReader read = FileRead.readBufferFromFile(fileName)) {
            String logbackupfile = PropertyUtil.readSingleProperties(PropertyConstant.logbackupPath) + PropertyUtil.readSingleProperties(PropertyConstant.logbackupFile);
            //  System.out.println("LogBackupfileName: "+logbackupfile);
            BufferedReader read1 = FileRead.readBufferFromFile(logbackupfile);

            // System.out.println(fileName);
            String fileData = null;

            String jobRunID = null;
            while ((fileData = read.readLine()) != null) {

                boolean newRun = false;
                // String jobID = "null";
                if (fileData.equals(read1.readLine())) {
                    //System.out.println("fileData.equals(read1.readLine()==true");

                    continue;
                }//End if
                else {
                    //firstRun=false;
                    FileOperationUtility.copyFile(fileName, logbackupfile);
                    String[] dataField = fileData.split(";", -1);
                    int logsAttributesNo = dataField.length;
                    //if found a datetime in string, 
                    if (patternMatcher(dataField[0], regexTimestamp)) {

                        //get jobRunID from log line, compare with previosly stored runID, if its same,
                        //skip (means this line in the log file about same run), otherwise create a new jobRun object
                        if (!dataField[1].equalsIgnoreCase(jobRunInst.getJobRunID())) {
                            jobRunID = dataField[1];
                            rowInOut = new ArrayList<Integer>();
                            dataObj=new ArrayList<String>();
                            newProject = new Project();
                            newJob = new Job();
                            newJobRun = new JobRun();
                            newDataLocation = new DataLocation();

                            buildJobRunObject(dataField);


                        }
                        if (dataField[1].equalsIgnoreCase(jobRunInst.getJobRunID())) {
                            //log line with 12 fields contains error message
                            if (dataField.length == 12) {
                                jobRunInst.setErrorMessage(dataField[9] + "," + dataField[10]);

                            }
                            if (dataField.length == 13) {
                                jobRunInst.setErrorMessage(dataField[9] + "," + dataField[10] + ", " + dataField[11]);

                            }
                            //collect row in/out information
                            if (dataField.length == 15) {
                                System.out.println(dataField[11]);
                                dataObj.add(dataField[11]);
                                rowInOut.add(Integer.parseInt(dataField[12]));
                            }
                            //lineNo++;
                            //log contains word 'end' means a stage/job run ended, run end time and run duration present in this line of log
                            //end if jobRunID same condition

                            if (fileData.contains("end")) {
                                jobRunInst.setRunDuration((jobRunInst.getRunDuration() + Integer.parseInt(dataField[13])) / 1000);
                                jobRunInst.setJobRunEndTime(dataField[0]);
                                jobRunInst.setJobRunStatus(dataField[12]);
                                System.out.print(convertDate(dataField[0]));
                                
                                

                                //now SetRowsInOut Info
                                if (rowInOut.size() > 0) {
                                    jobRunInst.setRowsIn(rowInOut.get(0));
                                    jobRunInst.setRowsOut(rowInOut.get(rowInOut.size() - 1));

                                }
                                
                                //now set dataInOut location
                                
                                if(dataObj.size()>0){
                                    jobRunInst.setDataIn(dataObj.get(0));
                                    jobRunInst.setDataOut(dataObj.get(dataObj.size()-1));
                                }
                                System.out.println("Run Duration " + jobRunInst.getRunDuration() + " Sec |RunEndTime " + jobRunInst.getJobRunEndTime() + " | Rows Read: " + jobRunInst.getRowsIn() + " | Rows Written:" + jobRunInst.getRowsOut() + "| Status: " + jobRunInst.getJobRunStatus() + "|");
                                if (!jobRunInst.getJobRunStatus().contains(LogTypeConstant.SUCCESS)) {
                                    System.out.println("Error: " + jobRunInst.getErrorMessage());
                                }

                                //Now write jobRun info in db table
                                //build jobrun id for db table
                                // jobRunInst.setJobRunID(jobRunInst.getJobRunID()+engMonAppHost.getMacAddress());
                                //update JobRunInfo
                                QueryApp.updateJobRunInfo(conn, jobRunInst);


                                System.out.println("------------------------------------------------------------------------" + rowInOut.size());
                            }
                        }//end if jobRunID same condition

                    }//end date matcher
                    else {
                        System.out.println("dateTimeFound False:" + fileData);
                    }
                    //backup log file after operataion
                    // FileOperationUtility.copyFile(fileName, logbackupfile);
                }//End else


            }
        }
        
    }

    /**
     * Method create jobRunObject From jobRun log.
     *
     * @param logLine
     */
    public void buildJobRunObject(String[] logLine) {
        //build jobID host address+id from log file, this ensure every job has a unique id
        jobRunInst.setJobID(engMonAppHost.getMacAddress() + logLine[7]);
        jobRunInst.setJobRunID(logLine[1]);
        jobRunInst.setProjectName(logLine[5]);
        jobRunInst.setJobName(logLine[6]);
        jobRunInst.setJobRunStartTime(logLine[0]);
        jobRunInst.setRunDuration(0);
        jobRunInst.setJobRunEndTime(null);
        jobRunInst.setJobRunStatus(null);
        jobRunInst.setRowsIn(0);
        jobRunInst.setRowsOut(0);
        jobRunInst.setJobRunStatus(LogTypeConstant.RUNNING);
        jobRunInst.setErrorMessage(null);
        jobRunInst.setDataIn("NULL");
        jobRunInst.setDataOut("NULL");
        //create project object

        //newProject.setHostID(engMonAppHost.getHostID());
        newProject.setHostID(hostID);
        newProject.setProjectName(jobRunInst.getProjectName());
        newProject.setLocalPath("");
        newProject.setProjectID(QueryApp.writeProjectInfo(conn, newProject));
        //Now create job Object
        newJob.setJobID(jobRunInst.getJobID());
        newJob.setProjectID(newProject.getProjectID());
        newJob.setJobName(jobRunInst.getJobName());
        newJob.setLocalPath("");
        //write job in table
        QueryApp.writeJobInfo(conn, newJob);
        //write jobRunInfo- running
        QueryApp.writeJobRunInfo(conn, jobRunInst);


        //create job object

        //Developer's test lines
        System.out.println("=======================================================================");
        System.out.println("Project: " + jobRunInst.getProjectName() + "|Job: " + jobRunInst.getJobName() + "| RunID: " + jobRunInst.getJobRunID() + "| RunStartTime: " + jobRunInst.getJobRunStartTime());

        //System.out.println("------------------------------------------------------------------------");


    } //End Method buildJobRunObject

    /**
     *
     * @param data
     * @param regEX
     * @return
     */
    public boolean patternMatcher(String data, String regEX) {
        Pattern p = Pattern.compile(regEX, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher m = p.matcher(data);
        if (m.find()) {
            String timestamp1 = m.group(1);
            //System.out.print("("+timestamp1.toString()+")"+"\n");
            return true;
        }
        return false;
    }
    
    public java.sql.Timestamp convertDate(String strDate) throws ParseException{
        //String datetimeString = "2010-05-23 23:32:45"; 
        Date result; 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        java.util.Date date = sdf.parse(strDate);
        Long lngDate=date.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
        result = formatter.parse (strDate); 
        java.sql.Timestamp dateTime = new java.sql.Timestamp(lngDate); 
        
        System.out.println("SQL date " + dateTime);
        
        return dateTime;

        
    }
}
