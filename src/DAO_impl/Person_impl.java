/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_impl;

import DAO_interface.Database_interface;
import DAO_interface.Person_interface;
import Models.Department;
import Models.Person;
import Tool.DataDispatcher;
import Tool.ErrorException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author mojeb
 */
public class Person_impl implements Person_interface {

    private Database_interface manager = null;
    private int response;

    private static final int SUCCESS = 1;
    private static final int FAILED = 0;

    public Person_impl(){
        this(null);
    }

    public Person_impl(Database_interface manager){
        this.manager = manager;
    }
    
    @Override
    public int getResponse() {
        return response;
    }

    @Override
    public ArrayList<Person> getPersonDepartment(String dept) throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM employee_tbl "
                + "INNER JOIN department_tbl ON employee_tbl.emp_department = department_tbl.dept_id "
                + "WHERE department_tbl.dept_code = '"+dept+"' "
                + "ORDER BY emp_lname";
        ResultSet resultSet = null;
        ArrayList<Person> empList = new ArrayList<Person>();

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();

            }

            connection = manager.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                
                Department d = new Department();
                d.setDept_id(resultSet.getInt("dept_id"));
                d.setDept_code(resultSet.getString("dept_code"));
                d.setDept_name(resultSet.getString("dept_name"));
                
                Person p = new Person();
                p.setEmp_id(resultSet.getString("emp_id"));
                p.setEmp_lname(resultSet.getString("emp_lname"));
                p.setEmp_fname(resultSet.getString("emp_fname"));
                p.setEmp_mname(resultSet.getString("emp_mname"));
                p.setEmp_sname(resultSet.getString("emp_sname"));
                p.setEmp_position(resultSet.getString("emp_position"));
                p.setEmp_department(d);
                
                empList.add(p);
            }
            
        }catch (SQLException ex) {
            throw new ErrorException(ex.getMessage(), "SQLException");
        }catch (FileNotFoundException ex) {
            
        }catch(IOException ex){
            
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return empList;
    }

    @Override
    public Person getPersonID(String emp_id) throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM employee_tbl "
                + "INNER JOIN department_tbl ON employee_tbl.emp_department = department_tbl.dept_id "
                + "WHERE emp_id = '"+emp_id+"'";
        ResultSet resultSet = null;
        Person p = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();

            }

            connection = manager.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                
                Department d = new Department();
                d.setDept_id(resultSet.getInt("dept_id"));
                d.setDept_code(resultSet.getString("dept_code"));
                d.setDept_name(resultSet.getString("dept_name"));
                
                p = new Person();
                p.setEmp_id(resultSet.getString("emp_id"));
                p.setEmp_lname(resultSet.getString("emp_lname"));
                p.setEmp_fname(resultSet.getString("emp_fname"));
                p.setEmp_mname(resultSet.getString("emp_mname"));
                p.setEmp_sname(resultSet.getString("emp_sname"));
                p.setEmp_position(resultSet.getString("emp_position"));
                p.setEmp_department(d);
            }
            
        }catch (SQLException ex) {
            throw new ErrorException(ex.getMessage(), "SQLException");
        }catch (FileNotFoundException ex) {
            
        }catch(IOException ex){
            
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return p;
    }

    @Override
    public void addPerson(Person p) throws ErrorException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "INSERT INTO employee_tbl VALUES(?,?,?,?,?,?,?)";
        ResultSet resultSet = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            pStatement = connection.prepareStatement(sql);

            pStatement.setString(1, p.getEmp_id());
            pStatement.setString(2, p.getEmp_lname());
            pStatement.setString(3, p.getEmp_fname());
            pStatement.setString(4, p.getEmp_mname());
            pStatement.setString(5, p.getEmp_sname());
            pStatement.setString(6, p.getEmp_position());
            pStatement.setInt(7, p.getEmp_department().getDept_id());

            if (pStatement.executeUpdate() > 0) {
                response = SUCCESS;
            }

        } catch (SQLException ex) {
        } catch (Exception ex) {
        } finally {
            DataDispatcher.dispatch(resultSet, pStatement, connection);
        }
    }

    @Override
    public void updatePerson(Person p) throws ErrorException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "UPDATE employee_tbl "
                + "SET emp_lname=?, emp_fname=?, emp_mname=?, emp_sname=?, "
                +     "emp_position=?, emp_department=? "
                + "WHERE emp_id = "+p.getEmp_id();
        ResultSet resultSet = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            pStatement = connection.prepareStatement(sql);

            pStatement.setString(1, p.getEmp_lname());
            pStatement.setString(2, p.getEmp_fname());
            pStatement.setString(3, p.getEmp_mname());
            pStatement.setString(4, p.getEmp_sname());
            pStatement.setString(5, p.getEmp_position());
            pStatement.setInt(6, p.getEmp_department().getDept_id());

            if (pStatement.executeUpdate() > 0) {
                response = SUCCESS;
            }

        } catch (SQLException ex) {
        } catch (Exception ex) {
        } finally {
             DataDispatcher.dispatch(resultSet, pStatement, connection);
        }
    }

    @Override
    public void deletePerson(Person p) throws ErrorException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "DELETE FROM employee_tbl "
                +    "WHERE emp_id = "+p.getEmp_id();
        ResultSet resultSet = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            pStatement = connection.prepareStatement(sql);

            if (pStatement.executeUpdate() > 0) {
                response = SUCCESS;
            }

        } catch (SQLException ex) {
        } catch (Exception ex) {
        } finally {
            DataDispatcher.dispatch(resultSet, pStatement, connection);
        }
    }

    @Override
    public ArrayList<Person> getAllPerson() throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM employee_tbl "
                + "INNER JOIN department_tbl ON employee_tbl.emp_department = department_tbl.dept_id "
                + "ORDER BY emp_lname";
        ResultSet resultSet = null;
        ArrayList<Person> empList = new ArrayList<Person>();

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();

            }

            connection = manager.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                
                Department d = new Department();
                d.setDept_id(resultSet.getInt("dept_id"));
                d.setDept_code(resultSet.getString("dept_code"));
                d.setDept_name(resultSet.getString("dept_name"));
                
                Person p = new Person();
                p.setEmp_id(resultSet.getString("emp_id"));
                p.setEmp_lname(resultSet.getString("emp_lname"));
                p.setEmp_fname(resultSet.getString("emp_fname"));
                p.setEmp_mname(resultSet.getString("emp_mname"));
                p.setEmp_sname(resultSet.getString("emp_sname"));
                p.setEmp_position(resultSet.getString("emp_position"));
                p.setEmp_department(d);
                
                empList.add(p);
            }
            
        }catch (SQLException ex) {
            throw new ErrorException(ex.getMessage(), "SQLException");
        }catch (FileNotFoundException ex) {
            
        }catch(IOException ex){
            
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return empList;
    }

    @Override
    public ArrayList<Person> getPersonLastname(String lname) throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM employee_tbl "
                + "INNER JOIN department_tbl ON employee_tbl.emp_department = department_tbl.dept_id "
                + "WHERE emp_lname LIKE '"+lname+"%' "
                + "ORDER BY emp_lname";
        ResultSet resultSet = null;
        ArrayList<Person> empList = new ArrayList<Person>();

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();

            }

            connection = manager.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                
                Department d = new Department();
                d.setDept_id(resultSet.getInt("dept_id"));
                d.setDept_code(resultSet.getString("dept_code"));
                d.setDept_name(resultSet.getString("dept_name"));
                
                Person p = new Person();
                p.setEmp_id(resultSet.getString("emp_id"));
                p.setEmp_lname(resultSet.getString("emp_lname"));
                p.setEmp_fname(resultSet.getString("emp_fname"));
                p.setEmp_mname(resultSet.getString("emp_mname"));
                p.setEmp_sname(resultSet.getString("emp_sname"));
                p.setEmp_position(resultSet.getString("emp_position"));
                p.setEmp_department(d);
                
                empList.add(p);
            }
            
        }catch (SQLException ex) {
            throw new ErrorException(ex.getMessage(), "SQLException");
        }catch (FileNotFoundException ex) {
            
        }catch(IOException ex){
            
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return empList;
    }

    @Override
    public ArrayList<Person> getPersonFirstname(String fname) throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM employee_tbl "
                + "INNER JOIN department_tbl ON employee_tbl.emp_department = department_tbl.dept_id "
                + "WHERE emp_fname LIKE '"+fname+"%' "
                + "ORDER BY emp_lname";
        ResultSet resultSet = null;
        ArrayList<Person> empList = new ArrayList<Person>();

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();

            }

            connection = manager.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                
                Department d = new Department();
                d.setDept_id(resultSet.getInt("dept_id"));
                d.setDept_code(resultSet.getString("dept_code"));
                d.setDept_name(resultSet.getString("dept_name"));
                
                Person p = new Person();
                p.setEmp_id(resultSet.getString("emp_id"));
                p.setEmp_lname(resultSet.getString("emp_lname"));
                p.setEmp_fname(resultSet.getString("emp_fname"));
                p.setEmp_mname(resultSet.getString("emp_mname"));
                p.setEmp_sname(resultSet.getString("emp_sname"));
                p.setEmp_position(resultSet.getString("emp_position"));
                p.setEmp_department(d);
                
                empList.add(p);
            }
            
        }catch (SQLException ex) {
            throw new ErrorException(ex.getMessage(), "SQLException");
        }catch (FileNotFoundException ex) {
            
        }catch(IOException ex){
            
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return empList;
    }
    
}
