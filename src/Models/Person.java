/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author mojeb
 */
public class Person {
    
    private String emp_id;
    private String emp_lname;
    private String emp_fname;
    private String emp_mname;
    private String emp_sname;
    private String emp_position;
    private Department emp_department;

    public Person() {
        this.emp_id = "";
        this.emp_lname = "";
        this.emp_fname = "";
        this.emp_mname = "";
        this.emp_sname = "";
        this.emp_position = "";
        this.emp_department = new Department();
    }
    
    public Person(String emp_id, String emp_lname, String emp_fname, String emp_mname, 
            String emp_sname, String emp_position, Department emp_department) {
        this.emp_id = emp_id;
        this.emp_lname = emp_lname;
        this.emp_fname = emp_fname;
        this.emp_mname = emp_mname;
        this.emp_sname = emp_sname;
        this.emp_position = emp_position;
        this.emp_department = emp_department;
    }

    public Department getEmp_department() {
        return emp_department;
    }

    public void setEmp_department(Department emp_department) {
        this.emp_department = emp_department;
    }

    public String getEmp_fname() {
        return emp_fname;
    }

    public void setEmp_fname(String emp_fname) {
        this.emp_fname = emp_fname;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_lname() {
        return emp_lname;
    }

    public void setEmp_lname(String emp_lname) {
        this.emp_lname = emp_lname;
    }

    public String getEmp_mname() {
        return emp_mname;
    }

    public void setEmp_mname(String emp_mname) {
        this.emp_mname = emp_mname;
    }

    public String getEmp_position() {
        return emp_position;
    }

    public void setEmp_position(String emp_position) {
        this.emp_position = emp_position;
    }

    public String getEmp_sname() {
        return emp_sname;
    }

    public void setEmp_sname(String emp_sname) {
        this.emp_sname = emp_sname;
    }
    
    public String getCompleteName(){
        String complete_name = "";
        
        if(!this.emp_id.isEmpty()){
            if(this.emp_sname.isEmpty()){
                complete_name = this.emp_lname.toUpperCase()+", "+this.emp_fname+" "+
                                this.emp_mname.charAt(0)+".";
            }else{
                complete_name = this.emp_lname.toUpperCase()+", "+this.emp_fname+" "+
                    this.emp_mname.charAt(0)+"., "+this.emp_sname;
            }
        }
        return complete_name;
    }
}
