/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Service_impl;

import DAO_impl.JRF_impl;
import DAO_interface.JRF_interface;
import Models.JRF;
import Models.JRF_status;
import Models.JobReqAssignment;
import Service_interface.JRF_serv_interface;
import Tool.ErrorException;
import java.util.ArrayList;

/**
 *
 * @author hatiefm
 */
public class JRF_serv_impl implements JRF_serv_interface {

    private JRF_interface jrf_var;

    public JRF_serv_impl (){
        jrf_var = new JRF_impl();
    }
    
    @Override
    public int getResponse() {
        return jrf_var.getResponse();
    }

    @Override
    public void addJRF(JRF jrf) throws ErrorException {
        jrf_var.addJRF(jrf);
    }

    @Override
    public void updateJRF(JRF jrf) throws ErrorException {
        jrf_var.updateJRF(jrf);
    }

    @Override
    public void deleteJRF(JRF jrf) throws ErrorException {
        jrf_var.deleteJRF(jrf);
    }

    @Override
    public JRF getJRF_control_no(String control_no) throws ErrorException {
        return jrf_var.getJRF_control_no(control_no);
    }

    @Override
    public ArrayList<JRF> getJRF_all() throws ErrorException {
        return jrf_var.getJRF_all();
    }

    @Override
    public ArrayList<JRF> getJRF_employee(String emp_id) throws ErrorException {
        return jrf_var.getJRF_employee(emp_id);
    }

    @Override
    public ArrayList<JRF> getJRF_date_app(String date1, String date2) throws ErrorException {
        return jrf_var.getJRF_date_app(date1, date2);
    }

    @Override
    public int getLast_seq(int year) throws ErrorException {
        return jrf_var.getLast_seq(year);
    }

    @Override
    public ArrayList<JRF> getJRF_Office(int dept_id) throws ErrorException {
        return jrf_var.getJRF_Office(dept_id);
    }

    @Override
    public ArrayList<JRF> getJRF_Job_req_on(int job_req_on) throws ErrorException {
        return jrf_var.getJRF_Job_req_on(job_req_on);
    }

    @Override
    public ArrayList<JRF> getJRF_date(String date) throws ErrorException {
        return jrf_var.getJRF_date(date);
    }

    @Override
    public void addJob_req_assignment(JobReqAssignment j) throws ErrorException {
        jrf_var.addJob_req_assignment(j);
    }

    @Override
    public void editJob_req_assignment(JobReqAssignment j) throws ErrorException {
        jrf_var.editJob_req_assignment(j);
    }

    @Override
    public void deleteJob_req_assignment(JobReqAssignment j) throws ErrorException {
        jrf_var.deleteJob_req_assignment(j);
    }

    @Override
    public JobReqAssignment getAssignment_control_no(String control) throws ErrorException {
        return jrf_var.getAssignment_control_no(control);
    }

    @Override
    public ArrayList<JobReqAssignment> getAssignment_employee(String emp_id, int status) throws ErrorException {
        return jrf_var.getAssignment_employee(emp_id, status);
    }

    @Override
    public ArrayList<JobReqAssignment> getAssignment_status(int status) throws ErrorException {
        return jrf_var.getAssignment_status(status);
    }

    @Override
    public void addJRF_status(JRF_status stat) throws ErrorException {
        jrf_var.addJRF_status(stat);
    }

    @Override
    public void updateJRF_status(JRF_status stat) throws ErrorException {
        jrf_var.updateJRF_status(stat);
    }

    @Override
    public void deleteJRF_status(JRF_status stat) throws ErrorException {
        jrf_var.deleteJRF_status(stat);
    }

    @Override
    public JRF_status getStatus(String control_no) throws ErrorException {
        return jrf_var.getStatus(control_no);
    }
    
}
