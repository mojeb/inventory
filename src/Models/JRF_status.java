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
public class JRF_status {
    
    private JRF control_no;
    private String status;
    private Date date;

    public JRF_status() {
        this.control_no = new JRF();
        this.status = "";
        this.date = null;
    }
    
    public JRF_status(JRF control_no, String status, Date date) {
        this.control_no = control_no;
        this.status = status;
        this.date = date;
    }

    public JRF getControl_no() {
        return control_no;
    }

    public void setControl_no(JRF control_no) {
        this.control_no = control_no;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
