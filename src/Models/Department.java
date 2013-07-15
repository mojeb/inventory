/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author hatiefm
 */
public class Department {
    
    private int dept_id;
    private String dept_code;
    private String dept_name;
    private Person person;

    public Department() {
        this.dept_id = -1;
        this.dept_code = "";
        this.dept_name = "";
//        this.person = null;
    }
    
    public Department(int dept_id, String dept_code, String dept_name, Person person) {
        this.dept_id = dept_id;
        this.dept_code = dept_code;
        this.dept_name = dept_name;
        this.person = person;
    }

    public String getDept_code() {
        return dept_code;
    }

    public void setDept_code(String dept_code) {
        this.dept_code = dept_code;
    }

    public int getDept_id() {
        return dept_id;
    }

    public void setDept_id(int dept_id) {
        this.dept_id = dept_id;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
