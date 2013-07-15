/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Service_impl;

import DAO_impl.Department_impl;
import DAO_interface.Department_interface;
import Models.Department;
import Service_interface.Department_serv_interface;
import Tool.ErrorException;
import java.util.ArrayList;

/**
 *
 * @author hatiefm
 */
public class Department_serv_impl implements Department_serv_interface {

    private Department_interface dept;

    public Department_serv_impl (){
        dept = new Department_impl();
    }
    
    @Override
    public int getResponce() {
        return dept.getResponce();
    }

    @Override
    public void addDepartment(Department d) throws ErrorException {
        dept.addDepartment(d);
    }

    @Override
    public void updateDepartment(Department d) throws ErrorException {
        dept.updateDepartment(d);
    }

    @Override
    public void deleteDepartment(Department d) throws ErrorException {
        dept.deleteDepartment(d);
    }

    @Override
    public ArrayList<Department> getAllDepartment() throws ErrorException {
        return dept.getAllDepartment();
    }

    @Override
    public ArrayList<Department> getDepartmentCode(String code) throws ErrorException {
        return dept.getDepartmentCode(code);
    }

    @Override
    public ArrayList<Department> getDepartmentName(String name) throws ErrorException {
        return dept.getDepartmentName(name);
    }

    @Override
    public Department getDepartmentID(int id) throws ErrorException {
        return dept.getDepartmentID(id);
    }

    @Override
    public Department getDepartmentbyCode(String code) throws ErrorException {
        return dept.getDepartmentbyCode(code);
    }

    @Override
    public Department getDepartmentbyName(String name) throws ErrorException {
        return dept.getDepartmentbyName(name);
    }
}
