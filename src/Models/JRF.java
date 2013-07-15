/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;

/**
 *
 * @author hatiefm
 */
public class JRF {
    
    private String control_no;
    private Date date_app;
    private String email;
    private String contact;
    private Department dept;
    private String room;
    private Person requested_by;
    private int job_req_on;
    private String job_description;
    private String action_taken;
    private Person approved_by;
    private int year;
    private int seq;

    public JRF() {
        this.control_no = "";
        this.date_app = null;
        this.email = "";
        this.contact = "";
        this.dept = new Department();
        this.room = "";
        this.requested_by = new Person();
        this.job_req_on = -1;
        this.job_description = "";
        this.action_taken = "";
        this.approved_by = new Person();
        this.year = -1;
        this.seq = -1;
    }
    
    public JRF(String control_no, Date date_app, String email, String contact, 
            Department dept, String room, Person requested_by, int job_req_on, 
            String job_description, String action_taken, Person approved_by, 
            int year, int seq) {
        this.control_no = control_no;
        this.date_app = date_app;
        this.email = email;
        this.contact = contact;
        this.dept = dept;
        this.room = room;
        this.requested_by = requested_by;
        this.job_req_on = job_req_on;
        this.job_description = job_description;
        this.action_taken = action_taken;
        this.approved_by = approved_by;
        this.year = year;
        this.seq = seq;
    }

    public String getAction_taken() {
        return action_taken;
    }

    public void setAction_taken(String action_taken) {
        this.action_taken = action_taken;
    }

    public Person getApproved_by() {
        return approved_by;
    }

    public void setApproved_by(Person approved_by) {
        this.approved_by = approved_by;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getControl_no() {
        return control_no;
    }

    public void setControl_no(String control_no) {
        this.control_no = control_no;
    }

    public Date getDate_app() {
        return date_app;
    }

    public void setDate_app(Date date_app) {
        this.date_app = date_app;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJob_description() {
        return job_description;
    }

    public void setJob_description(String job_description) {
        this.job_description = job_description;
    }

    public int getJob_req_on() {
        return job_req_on;
    }

    public void setJob_req_on(int job_req_on) {
        this.job_req_on = job_req_on;
    }

    public Person getRequested_by() {
        return requested_by;
    }

    public void setRequested_by(Person requested_by) {
        this.requested_by = requested_by;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
