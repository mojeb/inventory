/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_impl;

import DAO_interface.Account_interface;
import DAO_interface.Database_interface;
import Models.Account;
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
 * @author hatiefm
 */
public class Account_impl implements Account_interface {
    
    private Database_interface manager = null;
    private int response;

    private static final int SUCCESS = 1;
    private static final int FAILED = 0;

    public Account_impl(){
        this(null);
    }

    public Account_impl(Database_interface manager){
        this.manager = manager;
    }

    @Override
    public int getResponse() {
        return response;
    }

    @Override
    public void addAccount(Account acc) throws ErrorException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "INSERT INTO account_tbl VALUES(?,?,?,?,NOW(),?,?)";
        ResultSet resultSet = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            pStatement = connection.prepareStatement(sql);

            pStatement.setString(1, acc.getAccount_id());
            pStatement.setString(2, acc.getUsername());
            pStatement.setString(3, acc.getPassword());
            pStatement.setString(4, acc.getUser().getEmp_id());
            pStatement.setString(5, acc.getStatus());
            pStatement.setInt(6, acc.getSeq());

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
    public void editAccount(Account acc) throws ErrorException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "UPDATE account_tbl SET username=?, password=?, code=?, status=?, seq=? "
                + "WHERE account_id = '"+acc.getAccount_id()+"'";
        ResultSet resultSet = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            pStatement = connection.prepareStatement(sql);

            pStatement.setString(1, acc.getUsername());
            pStatement.setString(2, acc.getPassword());
            pStatement.setString(3, acc.getStatus());
            pStatement.setInt(4, acc.getSeq());

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
    public void deleteAccount(Account acc) throws ErrorException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "DELETE FROM account_tbl "
                +    "WHERE account_id = '"+acc.getAccount_id()+"'";
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
    public Account getAccount_account_id(String accnt_id) throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM account_tbl "
                + "INNER JOIN employee_tbl ON employee_tbl.emp_id = account_tbl.user "
                + "INNER JOIN department_tbl ON employee_tbl.emp_department = department_tbl.dept_id "
                + "WHERE account_id = '"+accnt_id+"'";
        ResultSet resultSet = null;
        Account acc = null;

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
                
                Person user = new Person();
                user.setEmp_id(resultSet.getString("to_id"));
                user.setEmp_lname(resultSet.getString("to_lname"));
                user.setEmp_fname(resultSet.getString("to_fname"));
                user.setEmp_mname(resultSet.getString("to_mname"));
                user.setEmp_sname(resultSet.getString("to_sname"));
                user.setEmp_position(resultSet.getString("to_position"));
                user.setEmp_department(d);
                
                acc = new Account();
                acc.setAccount_id(resultSet.getString("account_id"));
                acc.setUsername(resultSet.getString("username"));
                acc.setPassword(resultSet.getString("password"));
                acc.setUser(user);
                acc.setDate_created(resultSet.getDate("date_created"));
                acc.setStatus(resultSet.getString("status"));
                acc.setSeq(resultSet.getInt("seq"));
            }
            
        }catch (SQLException ex) {
            throw new ErrorException(ex.getMessage(), "SQLException");
        }catch (FileNotFoundException ex) {
            throw new ErrorException(ex.getMessage(), "FileNotFoundException");
        }catch(IOException ex){
            throw new ErrorException(ex.getMessage(), "IOException");
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return acc;
    }

    @Override
    public Account getAccount_username_pass(String username, String password) throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM account_tbl "
                + "INNER JOIN employee_tbl ON employee_tbl.emp_id = account_tbl.user "
                + "INNER JOIN department_tbl ON employee_tbl.emp_department = department_tbl.dept_id "
                + "WHERE username = '"+username+"' AND password = '"+password+"'";
        ResultSet resultSet = null;
        Account acc = null;

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
                
                Person user = new Person();
                user.setEmp_id(resultSet.getString("to_id"));
                user.setEmp_lname(resultSet.getString("to_lname"));
                user.setEmp_fname(resultSet.getString("to_fname"));
                user.setEmp_mname(resultSet.getString("to_mname"));
                user.setEmp_sname(resultSet.getString("to_sname"));
                user.setEmp_position(resultSet.getString("to_position"));
                user.setEmp_department(d);
                
                acc = new Account();
                acc.setAccount_id(resultSet.getString("account_id"));
                acc.setUsername(resultSet.getString("username"));
                acc.setPassword(resultSet.getString("password"));
                acc.setUser(user);
                acc.setDate_created(resultSet.getDate("date_created"));
                acc.setStatus(resultSet.getString("status"));
                acc.setSeq(resultSet.getInt("seq"));
            }
            
        }catch (SQLException ex) {
            throw new ErrorException(ex.getMessage(), "SQLException");
        }catch (FileNotFoundException ex) {
            throw new ErrorException(ex.getMessage(), "FileNotFoundException");
        }catch(IOException ex){
            throw new ErrorException(ex.getMessage(), "IOException");
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return acc;
    }

    @Override
    public ArrayList<Account> getAccount_code(String code) throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM account_tbl "
                + "INNER JOIN employee_tbl ON employee_tbl.emp_id = account_tbl.user "
                + "INNER JOIN department_tbl ON employee_tbl.emp_department = department_tbl.dept_id "
                + "WHERE account_tbl.code = '"+code+"'";
        ResultSet resultSet = null;
        ArrayList<Account> accList = new ArrayList<Account>();

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
                
                Person user = new Person();
                user.setEmp_id(resultSet.getString("to_id"));
                user.setEmp_lname(resultSet.getString("to_lname"));
                user.setEmp_fname(resultSet.getString("to_fname"));
                user.setEmp_mname(resultSet.getString("to_mname"));
                user.setEmp_sname(resultSet.getString("to_sname"));
                user.setEmp_position(resultSet.getString("to_position"));
                user.setEmp_department(d);
                
                Account acc = new Account();
                acc.setAccount_id(resultSet.getString("account_id"));
                acc.setUsername(resultSet.getString("username"));
                acc.setPassword(resultSet.getString("password"));
                acc.setUser(user);
                acc.setDate_created(resultSet.getDate("date_created"));
                acc.setStatus(resultSet.getString("status"));
                acc.setSeq(resultSet.getInt("seq"));
                
                accList.add(acc);
            }
            
        }catch (SQLException ex) {
            throw new ErrorException(ex.getMessage(), "SQLException");
        }catch (FileNotFoundException ex) {
            throw new ErrorException(ex.getMessage(), "FileNotFoundException");
        }catch(IOException ex){
            throw new ErrorException(ex.getMessage(), "IOException");
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return accList;
    }

    @Override
    public int getAccountLastSeq() throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT seq FROM account_tbl "
                + "ORDER BY seq";
        ResultSet resultSet = null;
        int last_seq = 0;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                if(resultSet.isLast()){
                    last_seq = resultSet.getInt("seq");
                }
            }
            
        }catch (SQLException ex) {
            throw new ErrorException(ex.getMessage(), "SQLException");
        }catch (FileNotFoundException ex) {
            throw new ErrorException(ex.getMessage(), "FileNotFoundException");
        }catch(IOException ex){
            throw new ErrorException(ex.getMessage(), "IOException");
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return last_seq;
    }
    
}
