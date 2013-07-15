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
public class JobReqAssignment {
    
    private Date date_assigned;
    private String control_no;
    private Person assigned_to;
    private Person assigned_by;
    private int status;
    private Date date_completed;

    public JobReqAssignment() {
        this.date_assigned = null;
        this.control_no = "";
        this.assigned_to = new Person();
        this.assigned_by = new Person();
        this.status = -1;
        this.date_completed = null;
    }
    
    public JobReqAssignment(Date date_assigned, String control_no, 
            Person assigned_to, Person assigned_by, int status,
            Date date_completed) {
        this.date_assigned = date_assigned;
        this.control_no = control_no;
        this.assigned_to = assigned_to;
        this.assigned_by = assigned_by;
        this.status = status;
        this.date_completed = date_completed;
    }

    public Person getAssigned_by() {
        return assigned_by;
    }

    public void setAssigned_by(Person assigned_by) {
        this.assigned_by = assigned_by;
    }

    public Person getAssigned_to() {
        return assigned_to;
    }

    public void setAssigned_to(Person assigned_to) {
        this.assigned_to = assigned_to;
    }

    public String getControl_no() {
        return control_no;
    }

    public void setControl_no(String control_no) {
        this.control_no = control_no;
    }

    public Date getDate_assigned() {
        return date_assigned;
    }

    public void setDate_assigned(Date date_assigned) {
        this.date_assigned = date_assigned;
    }
    
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public Date getDate_completed() {
        return date_completed;
    }

    public void setDate_completed(Date date_assigned) {
        this.date_assigned = date_assigned;
    }
}
