/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_interface;

import Models.Department;
import Tool.ErrorException;
import java.util.ArrayList;

/**
 *
 * @author hatiefm
 */
public interface Department_interface {
    
    public int getResponce();
    public void addDepartment(Department d) throws ErrorException;
    public void updateDepartment(Department d) throws ErrorException;
    public void deleteDepartment(Department d) throws ErrorException;
    public ArrayList<Department> getAllDepartment() throws ErrorException;
    public ArrayList<Department> getDepartmentCode(String code) throws ErrorException;
    public ArrayList<Department> getDepartmentName(String name) throws ErrorException;
    public Department getDepartmentID(int id) throws ErrorException;
    public Department getDepartmentbyCode(String code) throws ErrorException;
    public Department getDepartmentbyName(String name) throws ErrorException;
}
