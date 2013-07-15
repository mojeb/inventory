/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Service_interface;

import Models.JRF;
import Models.JRF_status;
import Models.JobReqAssignment;
import Tool.ErrorException;
import java.util.ArrayList;

/**
 *
 * @author hatiefm
 */
public interface JRF_serv_interface {
    
    public int getResponse();
    public void addJRF(JRF jrf) throws ErrorException;
    public void updateJRF(JRF jrf) throws ErrorException;
    public void deleteJRF(JRF jrf) throws ErrorException;
    public JRF getJRF_control_no(String control_no) throws ErrorException;
    public ArrayList<JRF> getJRF_all() throws ErrorException;
    public ArrayList<JRF> getJRF_Office(int dept_id) throws ErrorException;
    public ArrayList<JRF> getJRF_Job_req_on(int job_req_on) throws ErrorException;
    public ArrayList<JRF> getJRF_date(String date) throws ErrorException;
    public ArrayList<JRF> getJRF_employee(String emp_id) throws ErrorException;
    public ArrayList<JRF> getJRF_date_app(String date1, String date2) throws ErrorException;
    public int getLast_seq(int year) throws ErrorException;
    
    //for job request assignment
    public void addJob_req_assignment(JobReqAssignment j) throws ErrorException;
    public void editJob_req_assignment(JobReqAssignment j) throws ErrorException;
    public void deleteJob_req_assignment(JobReqAssignment j) throws ErrorException;
    public JobReqAssignment getAssignment_control_no(String control) throws ErrorException;
    public ArrayList<JobReqAssignment> getAssignment_employee(String emp_id, int status) throws ErrorException;
    public ArrayList<JobReqAssignment> getAssignment_status(int status) throws ErrorException;
//    public ArrayList<Person> getIT_staff() throws ErrorException;
    
    //jrf status
    public void addJRF_status(JRF_status stat) throws ErrorException;
    public void updateJRF_status(JRF_status stat) throws ErrorException;
    public void deleteJRF_status(JRF_status stat) throws ErrorException;
    public JRF_status getStatus(String control_no) throws ErrorException;
}
