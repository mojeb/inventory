/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_impl;

import DAO_interface.Database_interface;
import DAO_interface.JRF_interface;
import Models.Department;
import Models.JRF;
import Models.JRF_status;
import Models.JobReqAssignment;
import Models.Person;
import Tool.DataDispatcher;
import Tool.ErrorException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author hatiefm
 */
public class JRF_impl implements JRF_interface {

    private Database_interface manager = null;
    private int response;

    private static final int SUCCESS = 1;
    private static final int FAILED = 0;

    public JRF_impl(){
        this(null);
    }

    public JRF_impl(Database_interface manager){
        this.manager = manager;
    }
    
    @Override
    public int getResponse() {
        return response;
    }

    @Override
    public void addJRF(JRF jrf) throws ErrorException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "INSERT INTO jrf_tbl VALUES(?,NOW(),?,?,?,?,?,?,?,?,?,?,?)";
        ResultSet resultSet = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            pStatement = connection.prepareStatement(sql);

            pStatement.setString(1, jrf.getControl_no());
            pStatement.setString(2, jrf.getEmail());
            pStatement.setString(3, jrf.getContact());
            pStatement.setInt(4, jrf.getDept().getDept_id());
            pStatement.setString(5, jrf.getRoom());
            pStatement.setString(6, jrf.getRequested_by().getEmp_id());
            pStatement.setInt(7, jrf.getJob_req_on());
            pStatement.setString(8, jrf.getJob_description());
            pStatement.setString(9, jrf.getAction_taken());
            pStatement.setString(10, jrf.getApproved_by().getEmp_id());
            pStatement.setInt(11, jrf.getYear());
            pStatement.setInt(12, jrf.getSeq());

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
    public void updateJRF(JRF jrf) throws ErrorException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "UPDATE jrf_tbl SET email=?, contact_no=?, dept_id=?, room=?, "
                + "requested_by=?, job_request_on=?, job_req_description=?, "
                + "action_taken=?, approved_by=? "
                + "WHERE control_no = '"+jrf.getControl_no()+"'";
        ResultSet resultSet = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            pStatement = connection.prepareStatement(sql);

            pStatement.setString(1, jrf.getEmail());
            pStatement.setString(2, jrf.getContact());
            pStatement.setInt(3, jrf.getDept().getDept_id());
            pStatement.setString(4, jrf.getRoom());
            pStatement.setString(5, jrf.getRequested_by().getEmp_id());
            pStatement.setInt(6, jrf.getJob_req_on());
            pStatement.setString(7, jrf.getJob_description());
            pStatement.setString(8, jrf.getAction_taken());
            pStatement.setString(9, jrf.getApproved_by().getEmp_id());

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
    public void deleteJRF(JRF jrf) throws ErrorException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "DELETE FROM jrf_tbl "
                +    "WHERE control_no = '"+jrf.getControl_no()+"'";
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
    public JRF getJRF_control_no(String control_no) throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT j.control_no, j.date_created, j.email, j.contact_no,"
                + "j.dept_id, j.room, j.requested_by, j.job_request_on, j.job_req_description,"
                + "j.action_taken, j.approved_by, j.year, j.seq, e1.emp_id to_id,"
                + "e1.emp_lname to_lname, e1.emp_fname to_fname, e1.emp_mname to_mname,"
                + "e1.emp_sname to_sname, e1.emp_position to_position, e2.emp_id by_id,"
                + "e2.emp_lname by_lname, e2.emp_fname by_fname, e2.emp_mname by_mname,"
                + "e2.emp_sname by_sname, e2.emp_position by_position, "
                + "d.dept_id, d.dept_code, d.dept_name "
                + "FROM jrf_tbl j "
                + "INNER JOIN department_tbl d ON j.dept_id = d.dept_id "
                + "INNER JOIN employee_tbl e1 ON j.requested_by = e1.emp_id "
                + "INNER JOIN employee_tbl e2 ON j.approved_by = e2.emp_id "
                + "WHERE j.control_no = '"+control_no+"' "
                + "ORDER BY j.control_no";
        ResultSet resultSet = null;
        JRF jrf = null;

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
                
                Person requested_by = new Person();
                requested_by.setEmp_id(resultSet.getString("to_id"));
                requested_by.setEmp_lname(resultSet.getString("to_lname"));
                requested_by.setEmp_fname(resultSet.getString("to_fname"));
                requested_by.setEmp_mname(resultSet.getString("to_mname"));
                requested_by.setEmp_sname(resultSet.getString("to_sname"));
                requested_by.setEmp_position(resultSet.getString("to_position"));
                requested_by.setEmp_department(d);
                
                Person approved_by = new Person();
                approved_by.setEmp_id(resultSet.getString("by_id"));
                approved_by.setEmp_lname(resultSet.getString("by_lname"));
                approved_by.setEmp_fname(resultSet.getString("by_fname"));
                approved_by.setEmp_mname(resultSet.getString("by_mname"));
                approved_by.setEmp_sname(resultSet.getString("by_sname"));
                approved_by.setEmp_position(resultSet.getString("by_position"));
                approved_by.setEmp_department(d);
                
                jrf = new JRF();
                jrf.setControl_no(resultSet.getString("control_no"));
                jrf.setDate_app(resultSet.getDate("date_created"));
                jrf.setEmail(resultSet.getString("email"));
                jrf.setContact(resultSet.getString("contact_no"));
                jrf.setDept(d);
                jrf.setRoom(resultSet.getString("room"));
                jrf.setRequested_by(requested_by);
                jrf.setJob_req_on(resultSet.getInt("job_request_on"));
                jrf.setJob_description(resultSet.getString("job_req_description"));
                jrf.setAction_taken(resultSet.getString("action_taken"));
                jrf.setApproved_by(approved_by);
                jrf.setYear(resultSet.getInt("year"));
                jrf.setSeq(resultSet.getInt("seq"));
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

        return jrf;
    }

    @Override
    public ArrayList<JRF> getJRF_all() throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT j.control_no, j.date_created, j.email, j.contact_no,"
                + "j.dept_id, j.room, j.requested_by, j.job_request_on, j.job_req_description,"
                + "j.action_taken, j.approved_by, j.year, j.seq, e1.emp_id to_id,"
                + "e1.emp_lname to_lname, e1.emp_fname to_fname, e1.emp_mname to_mname,"
                + "e1.emp_sname to_sname, e1.emp_position to_position, e2.emp_id by_id,"
                + "e2.emp_lname by_lname, e2.emp_fname by_fname, e2.emp_mname by_mname,"
                + "e2.emp_sname by_sname, e2.emp_position by_position, "
                + "d.dept_id, d.dept_code, d.dept_name "
                + "FROM jrf_tbl j "
                + "INNER JOIN department_tbl d ON j.dept_id = d.dept_id "
                + "INNER JOIN employee_tbl e1 ON j.requested_by = e1.emp_id "
                + "INNER JOIN employee_tbl e2 ON j.approved_by = e2.emp_id "
                + "ORDER BY j.control_no";
        ResultSet resultSet = null;
        ArrayList<JRF> jrfList = new ArrayList<JRF>();

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
                
                Person requested_by = new Person();
                requested_by.setEmp_id(resultSet.getString("to_id"));
                requested_by.setEmp_lname(resultSet.getString("to_lname"));
                requested_by.setEmp_fname(resultSet.getString("to_fname"));
                requested_by.setEmp_mname(resultSet.getString("to_mname"));
                requested_by.setEmp_sname(resultSet.getString("to_sname"));
                requested_by.setEmp_position(resultSet.getString("to_position"));
                requested_by.setEmp_department(d);
                
                Person approved_by = new Person();
                approved_by.setEmp_id(resultSet.getString("by_id"));
                approved_by.setEmp_lname(resultSet.getString("by_lname"));
                approved_by.setEmp_fname(resultSet.getString("by_fname"));
                approved_by.setEmp_mname(resultSet.getString("by_mname"));
                approved_by.setEmp_sname(resultSet.getString("by_sname"));
                approved_by.setEmp_position(resultSet.getString("by_position"));
                approved_by.setEmp_department(d);
                
                JRF jrf = new JRF();
                jrf.setControl_no(resultSet.getString("control_no"));
                jrf.setDate_app(resultSet.getDate("date_created"));
                jrf.setEmail(resultSet.getString("email"));
                jrf.setContact(resultSet.getString("contact_no"));
                jrf.setDept(d);
                jrf.setRoom(resultSet.getString("room"));
                jrf.setRequested_by(requested_by);
                jrf.setJob_req_on(resultSet.getInt("job_request_on"));
                jrf.setJob_description(resultSet.getString("job_req_description"));
                jrf.setAction_taken(resultSet.getString("action_taken"));
                jrf.setApproved_by(approved_by);
                jrf.setYear(resultSet.getInt("year"));
                jrf.setSeq(resultSet.getInt("seq"));
                
                jrfList.add(jrf);
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

        return jrfList;
    }

    @Override
    public ArrayList<JRF> getJRF_employee(String emp_id) throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT j.control_no, j.date_created, j.email, j.contact_no,"
                + "j.dept_id, j.room, j.requested_by, j.job_request_on, j.job_req_description,"
                + "j.action_taken, j.approved_by, j.year, j.seq, e1.emp_id to_id,"
                + "e1.emp_lname to_lname, e1.emp_fname to_fname, e1.emp_mname to_mname,"
                + "e1.emp_sname to_sname, e1.emp_position to_position, e2.emp_id by_id,"
                + "e2.emp_lname by_lname, e2.emp_fname by_fname, e2.emp_mname by_mname,"
                + "e2.emp_sname by_sname, e2.emp_position by_position, "
                + "d.dept_id, d.dept_code, d.dept_name "
                + "FROM jrf_tbl j "
                + "INNER JOIN department_tbl d ON j.dept_id = d.dept_id "
                + "INNER JOIN employee_tbl e1 ON j.requested_by = e1.emp_id "
                + "INNER JOIN employee_tbl e2 ON j.approved_by = e2.emp_id "
                + "WHERE j.requested_by = '"+emp_id+"' "
                + "ORDER BY j.control_no";
        ResultSet resultSet = null;
        ArrayList<JRF> jrfList = new ArrayList<JRF>();

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
                
                Person requested_by = new Person();
                requested_by.setEmp_id(resultSet.getString("to_id"));
                requested_by.setEmp_lname(resultSet.getString("to_lname"));
                requested_by.setEmp_fname(resultSet.getString("to_fname"));
                requested_by.setEmp_mname(resultSet.getString("to_mname"));
                requested_by.setEmp_sname(resultSet.getString("to_sname"));
                requested_by.setEmp_position(resultSet.getString("to_position"));
                requested_by.setEmp_department(d);
                
                Person approved_by = new Person();
                approved_by.setEmp_id(resultSet.getString("by_id"));
                approved_by.setEmp_lname(resultSet.getString("by_lname"));
                approved_by.setEmp_fname(resultSet.getString("by_fname"));
                approved_by.setEmp_mname(resultSet.getString("by_mname"));
                approved_by.setEmp_sname(resultSet.getString("by_sname"));
                approved_by.setEmp_position(resultSet.getString("by_position"));
                approved_by.setEmp_department(d);
                
                JRF jrf = new JRF();
                jrf.setControl_no(resultSet.getString("control_no"));
                jrf.setDate_app(resultSet.getDate("date_created"));
                jrf.setEmail(resultSet.getString("email"));
                jrf.setContact(resultSet.getString("contact_no"));
                jrf.setDept(d);
                jrf.setRoom(resultSet.getString("room"));
                jrf.setRequested_by(requested_by);
                jrf.setJob_req_on(resultSet.getInt("job_request_on"));
                jrf.setJob_description(resultSet.getString("job_req_description"));
                jrf.setAction_taken(resultSet.getString("action_taken"));
                jrf.setApproved_by(approved_by);
                jrf.setYear(resultSet.getInt("year"));
                jrf.setSeq(resultSet.getInt("seq"));
                
                jrfList.add(jrf);
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

        return jrfList;
    }

    @Override
    public ArrayList<JRF> getJRF_date_app(String date1, String date2) throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT j.control_no, j.date_created, j.email, j.contact_no,"
                + "j.dept_id, j.room, j.requested_by, j.job_request_on, j.job_req_description,"
                + "j.action_taken, j.approved_by, j.year, j.seq, e1.emp_id to_id,"
                + "e1.emp_lname to_lname, e1.emp_fname to_fname, e1.emp_mname to_mname,"
                + "e1.emp_sname to_sname, e1.emp_position to_position, e2.emp_id by_id,"
                + "e2.emp_lname by_lname, e2.emp_fname by_fname, e2.emp_mname by_mname,"
                + "e2.emp_sname by_sname, e2.emp_position by_position, "
                + "d.dept_id, d.dept_code, d.dept_name "
                + "FROM jrf_tbl j "
                + "INNER JOIN department_tbl d ON j.dept_id = d.dept_id "
                + "INNER JOIN employee_tbl e1 ON j.requested_by = e1.emp_id "
                + "INNER JOIN employee_tbl e2 ON j.approved_by = e2.emp_id "
                + "WHERE j.date_created BETWEEN '"+date1+"' AND '"+date2+"' "
                + "ORDER BY j.control_no";
        ResultSet resultSet = null;
        ArrayList<JRF> jrfList = new ArrayList<JRF>();

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
                
                Person requested_by = new Person();
                requested_by.setEmp_id(resultSet.getString("to_id"));
                requested_by.setEmp_lname(resultSet.getString("to_lname"));
                requested_by.setEmp_fname(resultSet.getString("to_fname"));
                requested_by.setEmp_mname(resultSet.getString("to_mname"));
                requested_by.setEmp_sname(resultSet.getString("to_sname"));
                requested_by.setEmp_position(resultSet.getString("to_position"));
                requested_by.setEmp_department(d);
                
                Person approved_by = new Person();
                approved_by.setEmp_id(resultSet.getString("by_id"));
                approved_by.setEmp_lname(resultSet.getString("by_lname"));
                approved_by.setEmp_fname(resultSet.getString("by_fname"));
                approved_by.setEmp_mname(resultSet.getString("by_mname"));
                approved_by.setEmp_sname(resultSet.getString("by_sname"));
                approved_by.setEmp_position(resultSet.getString("by_position"));
                approved_by.setEmp_department(d);
                
                JRF jrf = new JRF();
                jrf.setControl_no(resultSet.getString("control_no"));
                jrf.setDate_app(resultSet.getDate("date_created"));
                jrf.setEmail(resultSet.getString("email"));
                jrf.setContact(resultSet.getString("contact_no"));
                jrf.setDept(d);
                jrf.setRoom(resultSet.getString("room"));
                jrf.setRequested_by(requested_by);
                jrf.setJob_req_on(resultSet.getInt("job_request_on"));
                jrf.setJob_description(resultSet.getString("job_req_description"));
                jrf.setAction_taken(resultSet.getString("action_taken"));
                jrf.setApproved_by(approved_by);
                jrf.setYear(resultSet.getInt("year"));
                jrf.setSeq(resultSet.getInt("seq"));
                
                jrfList.add(jrf);
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

        return jrfList;
    }

    @Override
    public int getLast_seq(int year) throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT seq FROM jrf_tbl "
                + "WHERE year = "+year+" "
                + "ORDER BY seq";
        ResultSet resultSet = null;
        int last_seq = -1;

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
        }finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return last_seq;
    }

    @Override
    public ArrayList<JRF> getJRF_Office(int dept_id) throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT j.control_no, j.date_created, j.email, j.contact_no,"
                + "j.dept_id, j.room, j.requested_by, j.job_request_on, j.job_req_description,"
                + "j.action_taken, j.approved_by, j.year, j.seq, e1.emp_id to_id,"
                + "e1.emp_lname to_lname, e1.emp_fname to_fname, e1.emp_mname to_mname,"
                + "e1.emp_sname to_sname, e1.emp_position to_position, e2.emp_id by_id,"
                + "e2.emp_lname by_lname, e2.emp_fname by_fname, e2.emp_mname by_mname,"
                + "e2.emp_sname by_sname, e2.emp_position by_position, "
                + "d.dept_id, d.dept_code, d.dept_name "
                + "FROM jrf_tbl j "
                + "INNER JOIN department_tbl d ON j.dept_id = d.dept_id "
                + "INNER JOIN employee_tbl e1 ON j.requested_by = e1.emp_id "
                + "INNER JOIN employee_tbl e2 ON j.approved_by = e2.emp_id "
                + "WHERE j.dept_id = "+dept_id+" "
                + "ORDER BY j.control_no";
        ResultSet resultSet = null;
        ArrayList<JRF> jrfList = new ArrayList<JRF>();

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
                
                Person requested_by = new Person();
                requested_by.setEmp_id(resultSet.getString("to_id"));
                requested_by.setEmp_lname(resultSet.getString("to_lname"));
                requested_by.setEmp_fname(resultSet.getString("to_fname"));
                requested_by.setEmp_mname(resultSet.getString("to_mname"));
                requested_by.setEmp_sname(resultSet.getString("to_sname"));
                requested_by.setEmp_position(resultSet.getString("to_position"));
                requested_by.setEmp_department(d);
                
                Person approved_by = new Person();
                approved_by.setEmp_id(resultSet.getString("by_id"));
                approved_by.setEmp_lname(resultSet.getString("by_lname"));
                approved_by.setEmp_fname(resultSet.getString("by_fname"));
                approved_by.setEmp_mname(resultSet.getString("by_mname"));
                approved_by.setEmp_sname(resultSet.getString("by_sname"));
                approved_by.setEmp_position(resultSet.getString("by_position"));
                approved_by.setEmp_department(d);
                
                JRF jrf = new JRF();
                jrf.setControl_no(resultSet.getString("control_no"));
                jrf.setDate_app(resultSet.getDate("date_created"));
                jrf.setEmail(resultSet.getString("email"));
                jrf.setContact(resultSet.getString("contact_no"));
                jrf.setDept(d);
                jrf.setRoom(resultSet.getString("room"));
                jrf.setRequested_by(requested_by);
                jrf.setJob_req_on(resultSet.getInt("job_request_on"));
                jrf.setJob_description(resultSet.getString("job_req_description"));
                jrf.setAction_taken(resultSet.getString("action_taken"));
                jrf.setApproved_by(approved_by);
                jrf.setYear(resultSet.getInt("year"));
                jrf.setSeq(resultSet.getInt("seq"));
                
                jrfList.add(jrf);
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

        return jrfList;
    }

    @Override
    public ArrayList<JRF> getJRF_Job_req_on(int job_req_on) throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT j.control_no, j.date_created, j.email, j.contact_no,"
                + "j.dept_id, j.room, j.requested_by, j.job_request_on, j.job_req_description,"
                + "j.action_taken, j.approved_by, j.year, j.seq, e1.emp_id to_id,"
                + "e1.emp_lname to_lname, e1.emp_fname to_fname, e1.emp_mname to_mname,"
                + "e1.emp_sname to_sname, e1.emp_position to_position, e2.emp_id by_id,"
                + "e2.emp_lname by_lname, e2.emp_fname by_fname, e2.emp_mname by_mname,"
                + "e2.emp_sname by_sname, e2.emp_position by_position, "
                + "d.dept_id, d.dept_code, d.dept_name "
                + "FROM jrf_tbl j "
                + "INNER JOIN department_tbl d ON j.dept_id = d.dept_id "
                + "INNER JOIN employee_tbl e1 ON j.requested_by = e1.emp_id "
                + "INNER JOIN employee_tbl e2 ON j.approved_by = e2.emp_id "
                + "WHERE j.job_request_on = "+job_req_on+" "
                + "ORDER BY j.control_no";
        ResultSet resultSet = null;
        ArrayList<JRF> jrfList = new ArrayList<JRF>();

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
                
                Person requested_by = new Person();
                requested_by.setEmp_id(resultSet.getString("to_id"));
                requested_by.setEmp_lname(resultSet.getString("to_lname"));
                requested_by.setEmp_fname(resultSet.getString("to_fname"));
                requested_by.setEmp_mname(resultSet.getString("to_mname"));
                requested_by.setEmp_sname(resultSet.getString("to_sname"));
                requested_by.setEmp_position(resultSet.getString("to_position"));
                requested_by.setEmp_department(d);
                
                Person approved_by = new Person();
                approved_by.setEmp_id(resultSet.getString("by_id"));
                approved_by.setEmp_lname(resultSet.getString("by_lname"));
                approved_by.setEmp_fname(resultSet.getString("by_fname"));
                approved_by.setEmp_mname(resultSet.getString("by_mname"));
                approved_by.setEmp_sname(resultSet.getString("by_sname"));
                approved_by.setEmp_position(resultSet.getString("by_position"));
                approved_by.setEmp_department(d);
                
                JRF jrf = new JRF();
                jrf.setControl_no(resultSet.getString("control_no"));
                jrf.setDate_app(resultSet.getDate("date_created"));
                jrf.setEmail(resultSet.getString("email"));
                jrf.setContact(resultSet.getString("contact_no"));
                jrf.setDept(d);
                jrf.setRoom(resultSet.getString("room"));
                jrf.setRequested_by(requested_by);
                jrf.setJob_req_on(resultSet.getInt("job_request_on"));
                jrf.setJob_description(resultSet.getString("job_req_description"));
                jrf.setAction_taken(resultSet.getString("action_taken"));
                jrf.setApproved_by(approved_by);
                jrf.setYear(resultSet.getInt("year"));
                jrf.setSeq(resultSet.getInt("seq"));
                
                jrfList.add(jrf);
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

        return jrfList;
    }

    @Override
    public ArrayList<JRF> getJRF_date(String date) throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT j.control_no, j.date_created, j.email, j.contact_no,"
                + "j.dept_id, j.room, j.requested_by, j.job_request_on, j.job_req_description,"
                + "j.action_taken, j.approved_by, j.year, j.seq, e1.emp_id to_id,"
                + "e1.emp_lname to_lname, e1.emp_fname to_fname, e1.emp_mname to_mname,"
                + "e1.emp_sname to_sname, e1.emp_position to_position, e2.emp_id by_id,"
                + "e2.emp_lname by_lname, e2.emp_fname by_fname, e2.emp_mname by_mname,"
                + "e2.emp_sname by_sname, e2.emp_position by_position, "
                + "d.dept_id, d.dept_code, d.dept_name "
                + "FROM jrf_tbl j "
                + "INNER JOIN department_tbl d ON j.dept_id = d.dept_id "
                + "INNER JOIN employee_tbl e1 ON j.requested_by = e1.emp_id "
                + "INNER JOIN employee_tbl e2 ON j.approved_by = e2.emp_id "
                + "WHERE j.date_created = '"+date+"' "
                + "ORDER BY j.control_no";
        ResultSet resultSet = null;
        ArrayList<JRF> jrfList = new ArrayList<JRF>();

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
                
                Person requested_by = new Person();
                requested_by.setEmp_id(resultSet.getString("to_id"));
                requested_by.setEmp_lname(resultSet.getString("to_lname"));
                requested_by.setEmp_fname(resultSet.getString("to_fname"));
                requested_by.setEmp_mname(resultSet.getString("to_mname"));
                requested_by.setEmp_sname(resultSet.getString("to_sname"));
                requested_by.setEmp_position(resultSet.getString("to_position"));
                requested_by.setEmp_department(d);
                
                Person approved_by = new Person();
                approved_by.setEmp_id(resultSet.getString("by_id"));
                approved_by.setEmp_lname(resultSet.getString("by_lname"));
                approved_by.setEmp_fname(resultSet.getString("by_fname"));
                approved_by.setEmp_mname(resultSet.getString("by_mname"));
                approved_by.setEmp_sname(resultSet.getString("by_sname"));
                approved_by.setEmp_position(resultSet.getString("by_position"));
                approved_by.setEmp_department(d);
                
                JRF jrf = new JRF();
                jrf.setControl_no(resultSet.getString("control_no"));
                jrf.setDate_app(resultSet.getDate("date_created"));
                jrf.setEmail(resultSet.getString("email"));
                jrf.setContact(resultSet.getString("contact_no"));
                jrf.setDept(d);
                jrf.setRoom(resultSet.getString("room"));
                jrf.setRequested_by(requested_by);
                jrf.setJob_req_on(resultSet.getInt("job_request_on"));
                jrf.setJob_description(resultSet.getString("job_req_description"));
                jrf.setAction_taken(resultSet.getString("action_taken"));
                jrf.setApproved_by(approved_by);
                jrf.setYear(resultSet.getInt("year"));
                jrf.setSeq(resultSet.getInt("seq"));
                
                jrfList.add(jrf);
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

        return jrfList;
    }

    @Override
    public void addJob_req_assignment(JobReqAssignment j) throws ErrorException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "INSERT INTO jrf_assign_tbl VALUES(?,NOW(),?,?,?,?)";
        ResultSet resultSet = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            pStatement = connection.prepareStatement(sql);

            pStatement.setString(1, j.getControl_no());
            pStatement.setString(2, j.getAssigned_to().getEmp_id());
            pStatement.setString(3, j.getAssigned_by().getEmp_id());
            pStatement.setInt(4, j.getStatus());
            pStatement.setDate(5, (Date)j.getDate_completed());

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
    public void editJob_req_assignment(JobReqAssignment j) throws ErrorException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "UPDATE jrf_assign_tbl SET assigned_to=?, status=?, date_completed=? "
                + "WHERE control_no = '"+j.getControl_no()+"'";
        ResultSet resultSet = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            pStatement = connection.prepareStatement(sql);

            pStatement.setString(1, j.getAssigned_to().getEmp_id());
            pStatement.setInt(2, j.getStatus());
            pStatement.setDate(3, (Date) j.getDate_completed());

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
    public void deleteJob_req_assignment(JobReqAssignment j) throws ErrorException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "DELETE FROM jrf_assign_tbl "
                +    "WHERE control_no = '"+j.getControl_no()+"'";
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
    public JobReqAssignment getAssignment_control_no(String control) throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT j.control_no, j.assign_date, j.assigned_to, j.assigned_by, "
                + "j. status, j.date_completed, e1.emp_id to_id,"
                + "e1.emp_lname to_lname, e1.emp_fname to_fname, e1.emp_mname to_mname,"
                + "e1.emp_sname to_sname, e1.emp_position to_position, e2.emp_id by_id,"
                + "e2.emp_lname by_lname, e2.emp_fname by_fname, e2.emp_mname by_mname,"
                + "e2.emp_sname by_sname, e2.emp_position by_position "
                + "FROM jrf_assign_tbl j "
                + "INNER JOIN employee_tbl e1 ON j.assigned_to = e1.emp_id "
                + "LEFT JOIN employee_tbl e2 ON j.assigned_by = e2.emp_id "
                + "WHERE j.control_no = '"+control+"' "
                + "ORDER BY j.control_no";
        ResultSet resultSet = null;
        JobReqAssignment jrf_assign = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                
                Person assigned_to = new Person();
                assigned_to.setEmp_id(resultSet.getString("to_id"));
                assigned_to.setEmp_lname(resultSet.getString("to_lname"));
                assigned_to.setEmp_fname(resultSet.getString("to_fname"));
                assigned_to.setEmp_mname(resultSet.getString("to_mname"));
                assigned_to.setEmp_sname(resultSet.getString("to_sname"));
                assigned_to.setEmp_position(resultSet.getString("to_position"));
                
                Person assigned_by = new Person();
                assigned_by.setEmp_id(resultSet.getString("by_id"));
                assigned_by.setEmp_lname(resultSet.getString("by_lname"));
                assigned_by.setEmp_fname(resultSet.getString("by_fname"));
                assigned_by.setEmp_mname(resultSet.getString("by_mname"));
                assigned_by.setEmp_sname(resultSet.getString("by_sname"));
                assigned_by.setEmp_position(resultSet.getString("by_position"));
                
                jrf_assign = new JobReqAssignment();
                jrf_assign.setControl_no(resultSet.getString("control_no"));
                jrf_assign.setAssigned_to(assigned_to);
                jrf_assign.setAssigned_by(assigned_by);
                jrf_assign.setStatus(resultSet.getInt("status"));
                jrf_assign.setDate_completed(resultSet.getDate("date_completed"));
                jrf_assign.setDate_assigned(resultSet.getDate("assign_date"));
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

        return jrf_assign;
    }

    @Override
    public ArrayList<JobReqAssignment> getAssignment_employee(String emp_id, int status) throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT  j.control_no, j.assign_date, j.assigned_to, j.assigned_by, "
                + "j. status, j.date_completed, e1.emp_id to_id,"
                + "e1.emp_lname to_lname, e1.emp_fname to_fname, e1.emp_mname to_mname,"
                + "e1.emp_sname to_sname, e1.emp_position to_position, e2.emp_id by_id,"
                + "e2.emp_lname by_lname, e2.emp_fname by_fname, e2.emp_mname by_mname,"
                + "e2.emp_sname by_sname, e2.emp_position by_position "
                + "FROM jrf_assign_tbl j "
                + "INNER JOIN employee_tbl e1 ON j.assigned_to = e1.emp_id "
                + "LEFT JOIN employee_tbl e2 ON j.assigned_by = e2.emp_id "
                + "WHERE j.assigned_to = '"+emp_id+"' AND j.status = "+status+" "
                + "ORDER BY j.control_no";
        ResultSet resultSet = null;
        ArrayList<JobReqAssignment> jrf_assignList = new ArrayList<JobReqAssignment>();

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                
                Person assigned_to = new Person();
                assigned_to.setEmp_id(resultSet.getString("to_id"));
                assigned_to.setEmp_lname(resultSet.getString("to_lname"));
                assigned_to.setEmp_fname(resultSet.getString("to_fname"));
                assigned_to.setEmp_mname(resultSet.getString("to_mname"));
                assigned_to.setEmp_sname(resultSet.getString("to_sname"));
                assigned_to.setEmp_position(resultSet.getString("to_position"));
                
                Person assigned_by = new Person();
                assigned_by.setEmp_id(resultSet.getString("by_id"));
                assigned_by.setEmp_lname(resultSet.getString("by_lname"));
                assigned_by.setEmp_fname(resultSet.getString("by_fname"));
                assigned_by.setEmp_mname(resultSet.getString("by_mname"));
                assigned_by.setEmp_sname(resultSet.getString("by_sname"));
                assigned_by.setEmp_position(resultSet.getString("by_position"));
                
                JobReqAssignment jrf_assign = new JobReqAssignment();
                jrf_assign.setControl_no(resultSet.getString("control_no"));
                jrf_assign.setAssigned_to(assigned_to);
                jrf_assign.setAssigned_by(assigned_by);
                jrf_assign.setStatus(resultSet.getInt("status"));
                jrf_assign.setDate_completed(resultSet.getDate("date_completed"));
                jrf_assign.setDate_assigned(resultSet.getDate("assign_date"));
                
                jrf_assignList.add(jrf_assign);
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

        return jrf_assignList;
    }

    @Override
    public ArrayList<JobReqAssignment> getAssignment_status(int status) throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT  j.control_no, j.assign_date, j.assigned_to, j.assigned_by, "
                + "j. status, j.date_completed, e1.emp_id to_id,"
                + "e1.emp_lname to_lname, e1.emp_fname to_fname, e1.emp_mname to_mname,"
                + "e1.emp_sname to_sname, e1.emp_position to_position, e2.emp_id by_id,"
                + "e2.emp_lname by_lname, e2.emp_fname by_fname, e2.emp_mname by_mname,"
                + "e2.emp_sname by_sname, e2.emp_position by_position "
                + "FROM jrf_assign_tbl j "
                + "INNER JOIN employee_tbl e1 ON j.assigned_to = e1.emp_id "
                + "LEFT JOIN employee_tbl e2 ON j.assigned_by = e2.emp_id "
                + "WHERE j.status = "+status+" "
                + "ORDER BY j.control_no";
        ResultSet resultSet = null;
        ArrayList<JobReqAssignment> jrf_assignList = new ArrayList<JobReqAssignment>();

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                
                Person assigned_to = new Person();
                assigned_to.setEmp_id(resultSet.getString("to_id"));
                assigned_to.setEmp_lname(resultSet.getString("to_lname"));
                assigned_to.setEmp_fname(resultSet.getString("to_fname"));
                assigned_to.setEmp_mname(resultSet.getString("to_mname"));
                assigned_to.setEmp_sname(resultSet.getString("to_sname"));
                assigned_to.setEmp_position(resultSet.getString("to_position"));
                
                Person assigned_by = new Person();
                assigned_by.setEmp_id(resultSet.getString("by_id"));
                assigned_by.setEmp_lname(resultSet.getString("by_lname"));
                assigned_by.setEmp_fname(resultSet.getString("by_fname"));
                assigned_by.setEmp_mname(resultSet.getString("by_mname"));
                assigned_by.setEmp_sname(resultSet.getString("by_sname"));
                assigned_by.setEmp_position(resultSet.getString("by_position"));
                
                JobReqAssignment jrf_assign = new JobReqAssignment();
                jrf_assign.setControl_no(resultSet.getString("control_no"));
                jrf_assign.setAssigned_to(assigned_to);
                jrf_assign.setAssigned_by(assigned_by);
                jrf_assign.setStatus(resultSet.getInt("status"));
                jrf_assign.setDate_completed(resultSet.getDate("date_completed"));
                jrf_assign.setDate_assigned(resultSet.getDate("assign_date"));
                
                jrf_assignList.add(jrf_assign);
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

        return jrf_assignList;
    }

    @Override
    public void addJRF_status(JRF_status stat) throws ErrorException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "INSERT INTO jrf_status_tbl VALUES(?,?,NOW())";
        ResultSet resultSet = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            pStatement = connection.prepareStatement(sql);

            pStatement.setString(1, stat.getControl_no().getControl_no());
            pStatement.setString(2, stat.getStatus());

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
    public void updateJRF_status(JRF_status stat) throws ErrorException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "UPDATE jrf_status_tbl SET date=NOW(), status=? "
                + "WHERE control_no = '"+stat.getControl_no().getControl_no()+"'";
        ResultSet resultSet = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            pStatement = connection.prepareStatement(sql);

            pStatement.setString(1, stat.getStatus());

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
    public JRF_status getStatus(String control_no) throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT  j.control_no, j.date_created, j.email, j.contact_no,"
                + "j.dept_id, j.room, j.requested_by, j.job_request_on, j.job_req_description,"
                + "j.action_taken, j.approved_by, j.year, j.seq, e1.emp_id to_id,"
                + "e1.emp_lname to_lname, e1.emp_fname to_fname, e1.emp_mname to_mname,"
                + "e1.emp_sname to_sname, e1.emp_position to_position, e2.emp_id by_id,"
                + "e2.emp_lname by_lname, e2.emp_fname by_fname, e2.emp_mname by_mname,"
                + "e2.emp_sname by_sname, e2.emp_position by_position, "
                + "d.dept_id, d.dept_code, d.dept_name, s.control_no, s.status, s.date "
                + "FROM jrf_status_tbl s "
                + "INNER JOIN jrf_tbl j ON j.control_no = s.control_no "
                + "INNER JOIN department_tbl d ON j.dept_id = d.dept_id "
                + "INNER JOIN employee_tbl e1 ON j.requested_by = e1.emp_id "
                + "INNER JOIN employee_tbl e2 ON j.approved_by = e2.emp_id "
                + "WHERE s.control_no = '"+control_no+"'";
        ResultSet resultSet = null;
        JRF_status jrf_stat = null;

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
                
                Person requested_by = new Person();
                requested_by.setEmp_id(resultSet.getString("to_id"));
                requested_by.setEmp_lname(resultSet.getString("to_lname"));
                requested_by.setEmp_fname(resultSet.getString("to_fname"));
                requested_by.setEmp_mname(resultSet.getString("to_mname"));
                requested_by.setEmp_sname(resultSet.getString("to_sname"));
                requested_by.setEmp_position(resultSet.getString("to_position"));
                requested_by.setEmp_department(d);
                
                Person approved_by = new Person();
                approved_by.setEmp_id(resultSet.getString("by_id"));
                approved_by.setEmp_lname(resultSet.getString("by_lname"));
                approved_by.setEmp_fname(resultSet.getString("by_fname"));
                approved_by.setEmp_mname(resultSet.getString("by_mname"));
                approved_by.setEmp_sname(resultSet.getString("by_sname"));
                approved_by.setEmp_position(resultSet.getString("by_position"));
                approved_by.setEmp_department(d);
                
                JRF jrf = new JRF();
                jrf.setControl_no(resultSet.getString("control_no"));
                jrf.setDate_app(resultSet.getDate("date_created"));
                jrf.setEmail(resultSet.getString("email"));
                jrf.setContact(resultSet.getString("contact_no"));
                jrf.setDept(d);
                jrf.setRoom(resultSet.getString("room"));
                jrf.setRequested_by(requested_by);
                jrf.setJob_req_on(resultSet.getInt("job_request_on"));
                jrf.setJob_description(resultSet.getString("job_req_description"));
                jrf.setAction_taken(resultSet.getString("action_taken"));
                jrf.setApproved_by(approved_by);
                jrf.setYear(resultSet.getInt("year"));
                jrf.setSeq(resultSet.getInt("seq"));
                
                jrf_stat = new JRF_status();
                jrf_stat.setControl_no(jrf);
                jrf_stat.setStatus(resultSet.getString("status"));
                jrf_stat.setDate(resultSet.getDate("date"));
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

        return jrf_stat;
    }

    @Override
    public void deleteJRF_status(JRF_status stat) throws ErrorException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "DELETE FROM jrf_status_tbl "
                +    "WHERE control_no = '"+stat.getControl_no().getControl_no()+"'";
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
    
}
