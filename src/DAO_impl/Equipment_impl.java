/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_impl;

import DAO_interface.Database_interface;
import DAO_interface.Equipment_interface;
import Models.*;
import Tool.DataDispatcher;
import Tool.ErrorException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author mojeb
 */
public class Equipment_impl implements Equipment_interface {
    
    private Database_interface manager = null;
    private int response;

    private static final int SUCCESS = 1;
    private static final int FAILED = 0;

    public Equipment_impl(){
        this(null);
    }

    public Equipment_impl(Database_interface manager){
        this.manager = manager;
    }

    @Override
    public int getResponse() {
        return response;
    }

    @Override
    public void addEquipment(Equipment e) throws ErrorException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "INSERT INTO equipment_tbl VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        ResultSet resultSet = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            pStatement = connection.prepareStatement(sql);

            pStatement.setString(1, e.getControl_no());
            pStatement.setInt(2, e.getCategory().getCat_id());
            pStatement.setString(3, e.getAcquisition_date());
            pStatement.setInt(4, e.getQuantity());
            pStatement.setString(5, e.getUnit());
            pStatement.setString(6, e.getItem_descrption());
            pStatement.setDouble(7, e.getUnit_price());
            pStatement.setString(8, e.getRiv());
            pStatement.setString(9, e.getPo());
            pStatement.setString(10, e.getAccnt_person().getEmp_id());
            pStatement.setInt(11, e.getSupplier().getSupp_id());
            pStatement.setString(12, e.getRemarks());
            pStatement.setInt(13, e.getMonth());
            pStatement.setInt(14, e.getYear());
            pStatement.setInt(15, e.getSeq());
            pStatement.setString(16, e.getSequence_no());

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
    public void editEquipment(Equipment e) throws ErrorException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "UPDATE equipment_tbl SET category=?, acquisition_date=?, quantity=?, unit=?, "
                + "item_description=?, unit_price=?, riv=?, po=?, accnt_person=?, "
                + "supplier=?, remarks=?, sequence_no=? "
                + "WHERE control_no = '"+e.getControl_no()+"'";
        ResultSet resultSet = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            pStatement = connection.prepareStatement(sql);

            pStatement.setInt(1, e.getCategory().getCat_id());
            pStatement.setString(2, e.getAcquisition_date());
            pStatement.setInt(3, e.getQuantity());
            pStatement.setString(4, e.getUnit());
            pStatement.setString(5, e.getItem_descrption());
            pStatement.setDouble(6, e.getUnit_price());
            pStatement.setString(7, e.getRiv());
            pStatement.setString(8, e.getPo());
            pStatement.setString(9, e.getAccnt_person().getEmp_id());
            pStatement.setInt(10, e.getSupplier().getSupp_id());
            pStatement.setString(11, e.getRemarks());
            pStatement.setString(12, e.getSequence_no());

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
    public void deleteEquipment(Equipment e) throws ErrorException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "DELETE FROM equipment_tbl "
                +    "WHERE control_no = '"+e.getControl_no()+"'";
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
    public Equipment getEquipment_control_no(String control_no) throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM equipment_tbl j "
                + "INNER JOIN employee_tbl e ON j.accnt_person = e.emp_id "
                + "INNER JOIN department_tbl d ON e.emp_department = d.dept_id "
                + "INNER JOIN category_tbl c ON j.category = c.cat_id "
                + "INNER JOIN supplier_tbl s ON j.supplier = s.supp_id "
                + "WHERE j.control_no = '"+control_no+"'";
        ResultSet resultSet = null;
        Equipment e = null;

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
                
                Person accnt_person = new Person();
                accnt_person.setEmp_id(resultSet.getString("emp_id"));
                accnt_person.setEmp_lname(resultSet.getString("emp_lname"));
                accnt_person.setEmp_fname(resultSet.getString("emp_fname"));
                accnt_person.setEmp_mname(resultSet.getString("emp_mname"));
                accnt_person.setEmp_sname(resultSet.getString("emp_sname"));
                accnt_person.setEmp_position(resultSet.getString("emp_position"));
                accnt_person.setEmp_department(d);
                
                Category cat = new Category();
                cat.setCat_id(resultSet.getInt("cat_id"));
                cat.setCat_name(resultSet.getString("cat_description"));
                
                Supplier supplier = new Supplier();
                supplier.setSupp_id(resultSet.getInt("supp_id"));
                supplier.setSupp_name(resultSet.getString("supp_name"));
                
                e = new Equipment();
                e.setControl_no(resultSet.getString("control_no"));
                e.setCategory(cat);
                e.setAcquisition_date(resultSet.getString("acquisition_date"));
                e.setQuantity(resultSet.getInt("quantity"));
                e.setUnit(resultSet.getString("unit"));
                e.setItem_descrption(resultSet.getString("item_description"));
                e.setUnit_price(resultSet.getDouble("unit_price"));
                e.setRiv(resultSet.getString("riv"));
                e.setPo(resultSet.getString("po"));
                e.setAccnt_person(accnt_person);
                e.setSupplier(supplier);
                e.setRemarks(resultSet.getString("remarks"));
                e.setMonth(resultSet.getInt("month"));
                e.setYear(resultSet.getInt("year"));
                e.setSeq(resultSet.getInt("seq"));
                e.setSequence_no(resultSet.getString("sequence_no"));
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

        return e;
    }

    @Override
    public ArrayList<Equipment> getEquipment_description(String description) throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM equipment_tbl j "
                + "INNER JOIN employee_tbl e ON j.accnt_person = e.emp_id "
                + "INNER JOIN department_tbl d ON e.emp_department = d.dept_id "
                + "INNER JOIN category_tbl c ON j.category = c.cat_id "
                + "INNER JOIN supplier_tbl s ON j.supplier = s.supp_id "
                + "WHERE j.item_description = '"+description+"' "
                + "ORDER BY c.cat_description";
        ResultSet resultSet = null;
        ArrayList<Equipment> eList = new ArrayList<Equipment>();

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
                
                Person accnt_person = new Person();
                accnt_person.setEmp_id(resultSet.getString("emp_id"));
                accnt_person.setEmp_lname(resultSet.getString("emp_lname"));
                accnt_person.setEmp_fname(resultSet.getString("emp_fname"));
                accnt_person.setEmp_mname(resultSet.getString("emp_mname"));
                accnt_person.setEmp_sname(resultSet.getString("emp_sname"));
                accnt_person.setEmp_position(resultSet.getString("emp_position"));
                accnt_person.setEmp_department(d);
                
                Category cat = new Category();
                cat.setCat_id(resultSet.getInt("cat_id"));
                cat.setCat_name(resultSet.getString("cat_description"));
                
                Supplier supplier = new Supplier();
                supplier.setSupp_id(resultSet.getInt("supp_id"));
                supplier.setSupp_name(resultSet.getString("supp_name"));
                
                Equipment e = new Equipment();
                e.setControl_no(resultSet.getString("control_no"));
                e.setCategory(cat);
                e.setAcquisition_date(resultSet.getString("acquisition_date"));
                e.setQuantity(resultSet.getInt("quantity"));
                e.setUnit(resultSet.getString("unit"));
                e.setItem_descrption(resultSet.getString("item_description"));
                e.setUnit_price(resultSet.getDouble("unit_price"));
                e.setRiv(resultSet.getString("riv"));
                e.setPo(resultSet.getString("po"));
                e.setAccnt_person(accnt_person);
                e.setSupplier(supplier);
                e.setRemarks(resultSet.getString("remarks"));
                e.setMonth(resultSet.getInt("month"));
                e.setYear(resultSet.getInt("year"));
                e.setSeq(resultSet.getInt("seq"));
                e.setSequence_no(resultSet.getString("sequence_no"));
                
                eList.add(e);
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

        return eList;
    }

    @Override
    public ArrayList<Equipment> getEquipment_year_month(int year, int month) throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM equipment_tbl j "
                + "INNER JOIN employee_tbl e ON j.accnt_person = e.emp_id "
                + "INNER JOIN department_tbl d ON e.emp_department = d.dept_id "
                + "INNER JOIN category_tbl c ON j.category = c.cat_id "
                + "INNER JOIN supplier_tbl s ON j.supplier = s.supp_id "
                + "WHERE j.year = "+year+" AND j.month = "+month+" "
                + "ORDER BY c.cat_description";
        ResultSet resultSet = null;
        ArrayList<Equipment> eList = new ArrayList<Equipment>();

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
                
                Person accnt_person = new Person();
                accnt_person.setEmp_id(resultSet.getString("emp_id"));
                accnt_person.setEmp_lname(resultSet.getString("emp_lname"));
                accnt_person.setEmp_fname(resultSet.getString("emp_fname"));
                accnt_person.setEmp_mname(resultSet.getString("emp_mname"));
                accnt_person.setEmp_sname(resultSet.getString("emp_sname"));
                accnt_person.setEmp_position(resultSet.getString("emp_position"));
                accnt_person.setEmp_department(d);
                
                Category cat = new Category();
                cat.setCat_id(resultSet.getInt("cat_id"));
                cat.setCat_name(resultSet.getString("cat_description"));
                
                Supplier supplier = new Supplier();
                supplier.setSupp_id(resultSet.getInt("supp_id"));
                supplier.setSupp_name(resultSet.getString("supp_name"));
                
                Equipment e = new Equipment();
                e.setControl_no(resultSet.getString("control_no"));
                e.setCategory(cat);
                e.setAcquisition_date(resultSet.getString("acquisition_date"));
                e.setQuantity(resultSet.getInt("quantity"));
                e.setUnit(resultSet.getString("unit"));
                e.setItem_descrption(resultSet.getString("item_description"));
                e.setUnit_price(resultSet.getDouble("unit_price"));
                e.setRiv(resultSet.getString("riv"));
                e.setPo(resultSet.getString("po"));
                e.setAccnt_person(accnt_person);
                e.setSupplier(supplier);
                e.setRemarks(resultSet.getString("remarks"));
                e.setMonth(resultSet.getInt("month"));
                e.setYear(resultSet.getInt("year"));
                e.setSeq(resultSet.getInt("seq"));
                e.setSequence_no(resultSet.getString("sequence_no"));
                
                eList.add(e);
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

        return eList;
    }

    @Override
    public ArrayList<Equipment> getEquipment_category(String category) throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM equipment_tbl j "
                + "INNER JOIN employee_tbl e ON j.accnt_person = e.emp_id "
                + "INNER JOIN department_tbl d ON e.emp_department = d.dept_id "
                + "INNER JOIN category_tbl c ON j.category = c.cat_id "
                + "INNER JOIN supplier_tbl s ON j.supplier = s.supp_id "
                + "WHERE c.cat_description = '"+category+"' "
                + "ORDER BY c.cat_description";
        ResultSet resultSet = null;
        ArrayList<Equipment> eList = new ArrayList<Equipment>();

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
                
                Person accnt_person = new Person();
                accnt_person.setEmp_id(resultSet.getString("emp_id"));
                accnt_person.setEmp_lname(resultSet.getString("emp_lname"));
                accnt_person.setEmp_fname(resultSet.getString("emp_fname"));
                accnt_person.setEmp_mname(resultSet.getString("emp_mname"));
                accnt_person.setEmp_sname(resultSet.getString("emp_sname"));
                accnt_person.setEmp_position(resultSet.getString("emp_position"));
                accnt_person.setEmp_department(d);
                
                Category cat = new Category();
                cat.setCat_id(resultSet.getInt("cat_id"));
                cat.setCat_name(resultSet.getString("cat_description"));
                
                Supplier supplier = new Supplier();
                supplier.setSupp_id(resultSet.getInt("supp_id"));
                supplier.setSupp_name(resultSet.getString("supp_name"));
                
                Equipment e = new Equipment();
                e.setControl_no(resultSet.getString("control_no"));
                e.setCategory(cat);
                e.setAcquisition_date(resultSet.getString("acquisition_date"));
                e.setQuantity(resultSet.getInt("quantity"));
                e.setUnit(resultSet.getString("unit"));
                e.setItem_descrption(resultSet.getString("item_description"));
                e.setUnit_price(resultSet.getDouble("unit_price"));
                e.setRiv(resultSet.getString("riv"));
                e.setPo(resultSet.getString("po"));
                e.setAccnt_person(accnt_person);
                e.setSupplier(supplier);
                e.setRemarks(resultSet.getString("remarks"));
                e.setMonth(resultSet.getInt("month"));
                e.setYear(resultSet.getInt("year"));
                e.setSeq(resultSet.getInt("seq"));
                e.setSequence_no(resultSet.getString("sequence_no"));
                
                eList.add(e);
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

        return eList;
    }

    @Override
    public ArrayList<Equipment> getEquipment_employee(String emp_id) throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM equipment_tbl j "
                + "INNER JOIN employee_tbl e ON j.accnt_person = e.emp_id "
                + "INNER JOIN department_tbl d ON e.emp_department = d.dept_id "
                + "INNER JOIN category_tbl c ON j.category = c.cat_id "
                + "INNER JOIN supplier_tbl s ON j.supplier = s.supp_id "
                + "WHERE j.accnt_person = '"+emp_id+"' "
                + "ORDER BY c.cat_description";
        ResultSet resultSet = null;
        ArrayList<Equipment> eList = new ArrayList<Equipment>();

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
                
                Person accnt_person = new Person();
                accnt_person.setEmp_id(resultSet.getString("emp_id"));
                accnt_person.setEmp_lname(resultSet.getString("emp_lname"));
                accnt_person.setEmp_fname(resultSet.getString("emp_fname"));
                accnt_person.setEmp_mname(resultSet.getString("emp_mname"));
                accnt_person.setEmp_sname(resultSet.getString("emp_sname"));
                accnt_person.setEmp_position(resultSet.getString("emp_position"));
                accnt_person.setEmp_department(d);
                
                Category cat = new Category();
                cat.setCat_id(resultSet.getInt("cat_id"));
                cat.setCat_name(resultSet.getString("cat_description"));
                
                Supplier supplier = new Supplier();
                supplier.setSupp_id(resultSet.getInt("supp_id"));
                supplier.setSupp_name(resultSet.getString("supp_name"));
                
                Equipment e = new Equipment();
                e.setControl_no(resultSet.getString("control_no"));
                e.setCategory(cat);
                e.setAcquisition_date(resultSet.getString("acquisition_date"));
                e.setQuantity(resultSet.getInt("quantity"));
                e.setUnit(resultSet.getString("unit"));
                e.setItem_descrption(resultSet.getString("item_description"));
                e.setUnit_price(resultSet.getDouble("unit_price"));
                e.setRiv(resultSet.getString("riv"));
                e.setPo(resultSet.getString("po"));
                e.setAccnt_person(accnt_person);
                e.setSupplier(supplier);
                e.setRemarks(resultSet.getString("remarks"));
                e.setMonth(resultSet.getInt("month"));
                e.setYear(resultSet.getInt("year"));
                e.setSeq(resultSet.getInt("seq"));
                e.setSequence_no(resultSet.getString("sequence_no"));
                
                eList.add(e);
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

        return eList;
    }

    @Override
    public ArrayList<Equipment> getEquipment_sequence_no(String sequence_no) throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM equipment_tbl j "
                + "INNER JOIN employee_tbl e ON j.accnt_person = e.emp_id "
                + "INNER JOIN department_tbl d ON e.emp_department = d.dept_id "
                + "INNER JOIN category_tbl c ON j.category = c.cat_id "
                + "INNER JOIN supplier_tbl s ON j.supplier = s.supp_id "
                + "WHERE j.sequence_no = '"+sequence_no+"' "
                + "ORDER BY c.cat_description";
        ResultSet resultSet = null;
        ArrayList<Equipment> eList = new ArrayList<Equipment>();

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
                
                Person accnt_person = new Person();
                accnt_person.setEmp_id(resultSet.getString("emp_id"));
                accnt_person.setEmp_lname(resultSet.getString("emp_lname"));
                accnt_person.setEmp_fname(resultSet.getString("emp_fname"));
                accnt_person.setEmp_mname(resultSet.getString("emp_mname"));
                accnt_person.setEmp_sname(resultSet.getString("emp_sname"));
                accnt_person.setEmp_position(resultSet.getString("emp_position"));
                accnt_person.setEmp_department(d);
                
                Category cat = new Category();
                cat.setCat_id(resultSet.getInt("cat_id"));
                cat.setCat_name(resultSet.getString("cat_description"));
                
                Supplier supplier = new Supplier();
                supplier.setSupp_id(resultSet.getInt("supp_id"));
                supplier.setSupp_name(resultSet.getString("supp_name"));
                
                Equipment e = new Equipment();
                e.setControl_no(resultSet.getString("control_no"));
                e.setCategory(cat);
                e.setAcquisition_date(resultSet.getString("acquisition_date"));
                e.setQuantity(resultSet.getInt("quantity"));
                e.setUnit(resultSet.getString("unit"));
                e.setItem_descrption(resultSet.getString("item_description"));
                e.setUnit_price(resultSet.getDouble("unit_price"));
                e.setRiv(resultSet.getString("riv"));
                e.setPo(resultSet.getString("po"));
                e.setAccnt_person(accnt_person);
                e.setSupplier(supplier);
                e.setRemarks(resultSet.getString("remarks"));
                e.setMonth(resultSet.getInt("month"));
                e.setYear(resultSet.getInt("year"));
                e.setSeq(resultSet.getInt("seq"));
                e.setSequence_no(resultSet.getString("sequence_no"));
                
                eList.add(e);
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

        return eList;
    }

    @Override
    public ArrayList<Equipment> getEquipment_po_no(String po_no) throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM equipment_tbl j "
                + "INNER JOIN employee_tbl e ON j.accnt_person = e.emp_id "
                + "INNER JOIN department_tbl d ON e.emp_department = d.dept_id "
                + "INNER JOIN category_tbl c ON j.category = c.cat_id "
                + "INNER JOIN supplier_tbl s ON j.supplier = s.supp_id "
                + "WHERE j.po = '"+po_no+"' "
                + "ORDER BY c.cat_description";
        ResultSet resultSet = null;
        ArrayList<Equipment> eList = new ArrayList<Equipment>();

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
                
                Person accnt_person = new Person();
                accnt_person.setEmp_id(resultSet.getString("emp_id"));
                accnt_person.setEmp_lname(resultSet.getString("emp_lname"));
                accnt_person.setEmp_fname(resultSet.getString("emp_fname"));
                accnt_person.setEmp_mname(resultSet.getString("emp_mname"));
                accnt_person.setEmp_sname(resultSet.getString("emp_sname"));
                accnt_person.setEmp_position(resultSet.getString("emp_position"));
                accnt_person.setEmp_department(d);
                
                Category cat = new Category();
                cat.setCat_id(resultSet.getInt("cat_id"));
                cat.setCat_name(resultSet.getString("cat_description"));
                
                Supplier supplier = new Supplier();
                supplier.setSupp_id(resultSet.getInt("supp_id"));
                supplier.setSupp_name(resultSet.getString("supp_name"));
                
                Equipment e = new Equipment();
                e.setControl_no(resultSet.getString("control_no"));
                e.setCategory(cat);
                e.setAcquisition_date(resultSet.getString("acquisition_date"));
                e.setQuantity(resultSet.getInt("quantity"));
                e.setUnit(resultSet.getString("unit"));
                e.setItem_descrption(resultSet.getString("item_description"));
                e.setUnit_price(resultSet.getDouble("unit_price"));
                e.setRiv(resultSet.getString("riv"));
                e.setPo(resultSet.getString("po"));
                e.setAccnt_person(accnt_person);
                e.setSupplier(supplier);
                e.setRemarks(resultSet.getString("remarks"));
                e.setMonth(resultSet.getInt("month"));
                e.setYear(resultSet.getInt("year"));
                e.setSeq(resultSet.getInt("seq"));
                e.setSequence_no(resultSet.getString("sequence_no"));
                
                eList.add(e);
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

        return eList;
    }

    @Override
    public ArrayList<Equipment> getEquipment_riv_no(String riv_no) throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM equipment_tbl j "
                + "INNER JOIN employee_tbl e ON j.accnt_person = e.emp_id "
                + "INNER JOIN department_tbl d ON e.emp_department = d.dept_id "
                + "INNER JOIN category_tbl c ON j.category = c.cat_id "
                + "INNER JOIN supplier_tbl s ON j.supplier = s.supp_id "
                + "WHERE j.riv = '"+riv_no+"' "
                + "ORDER BY c.cat_description";
        ResultSet resultSet = null;
        ArrayList<Equipment> eList = new ArrayList<Equipment>();

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
                
                Person accnt_person = new Person();
                accnt_person.setEmp_id(resultSet.getString("emp_id"));
                accnt_person.setEmp_lname(resultSet.getString("emp_lname"));
                accnt_person.setEmp_fname(resultSet.getString("emp_fname"));
                accnt_person.setEmp_mname(resultSet.getString("emp_mname"));
                accnt_person.setEmp_sname(resultSet.getString("emp_sname"));
                accnt_person.setEmp_position(resultSet.getString("emp_position"));
                accnt_person.setEmp_department(d);
                
                Category cat = new Category();
                cat.setCat_id(resultSet.getInt("cat_id"));
                cat.setCat_name(resultSet.getString("cat_description"));
                
                Supplier supplier = new Supplier();
                supplier.setSupp_id(resultSet.getInt("supp_id"));
                supplier.setSupp_name(resultSet.getString("supp_name"));
                
                Equipment e = new Equipment();
                e.setControl_no(resultSet.getString("control_no"));
                e.setCategory(cat);
                e.setAcquisition_date(resultSet.getString("acquisition_date"));
                e.setQuantity(resultSet.getInt("quantity"));
                e.setUnit(resultSet.getString("unit"));
                e.setItem_descrption(resultSet.getString("item_description"));
                e.setUnit_price(resultSet.getDouble("unit_price"));
                e.setRiv(resultSet.getString("riv"));
                e.setPo(resultSet.getString("po"));
                e.setAccnt_person(accnt_person);
                e.setSupplier(supplier);
                e.setRemarks(resultSet.getString("remarks"));
                e.setMonth(resultSet.getInt("month"));
                e.setYear(resultSet.getInt("year"));
                e.setSeq(resultSet.getInt("seq"));
                e.setSequence_no(resultSet.getString("sequence_no"));
                
                eList.add(e);
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

        return eList;
    }

    @Override
    public ArrayList<Equipment> getEquipment_office(String office) throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM equipment_tbl j "
                + "INNER JOIN employee_tbl e ON j.accnt_person = e.emp_id "
                + "INNER JOIN department_tbl d ON e.emp_department = d.dept_id "
                + "INNER JOIN category_tbl c ON j.category = c.cat_id "
                + "INNER JOIN supplier_tbl s ON j.supplier = s.supp_id "
                + "WHERE d.dept_code = '"+office+"' "
                + "ORDER BY c.cat_description";
        ResultSet resultSet = null;
        ArrayList<Equipment> eList = new ArrayList<Equipment>();

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
                
                Person accnt_person = new Person();
                accnt_person.setEmp_id(resultSet.getString("emp_id"));
                accnt_person.setEmp_lname(resultSet.getString("emp_lname"));
                accnt_person.setEmp_fname(resultSet.getString("emp_fname"));
                accnt_person.setEmp_mname(resultSet.getString("emp_mname"));
                accnt_person.setEmp_sname(resultSet.getString("emp_sname"));
                accnt_person.setEmp_position(resultSet.getString("emp_position"));
                accnt_person.setEmp_department(d);
                
                Category cat = new Category();
                cat.setCat_id(resultSet.getInt("cat_id"));
                cat.setCat_name(resultSet.getString("cat_description"));
                
                Supplier supplier = new Supplier();
                supplier.setSupp_id(resultSet.getInt("supp_id"));
                supplier.setSupp_name(resultSet.getString("supp_name"));
                
                Equipment e = new Equipment();
                e.setControl_no(resultSet.getString("control_no"));
                e.setCategory(cat);
                e.setAcquisition_date(resultSet.getString("acquisition_date"));
                e.setQuantity(resultSet.getInt("quantity"));
                e.setUnit(resultSet.getString("unit"));
                e.setItem_descrption(resultSet.getString("item_description"));
                e.setUnit_price(resultSet.getDouble("unit_price"));
                e.setRiv(resultSet.getString("riv"));
                e.setPo(resultSet.getString("po"));
                e.setAccnt_person(accnt_person);
                e.setSupplier(supplier);
                e.setRemarks(resultSet.getString("remarks"));
                e.setMonth(resultSet.getInt("month"));
                e.setYear(resultSet.getInt("year"));
                e.setSeq(resultSet.getInt("seq"));
                e.setSequence_no(resultSet.getString("sequence_no"));
                
                eList.add(e);
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

        return eList;
    }

    @Override
    public int getLastSeq(int year, int month) throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT seq FROM equipment_tbl "
                + "WHERE year = "+year+" AND month = "+month+" "
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
        
        if(last_seq < 0)
            last_seq = 0;

        return last_seq;
    }

    @Override
    public ArrayList<Equipment> getEquipment_supplier(String supp) throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM equipment_tbl j "
                + "INNER JOIN employee_tbl e ON j.accnt_person = e.emp_id "
                + "INNER JOIN department_tbl d ON e.emp_department = d.dept_id "
                + "INNER JOIN category_tbl c ON j.category = c.cat_id "
                + "INNER JOIN supplier_tbl s ON j.supplier = s.supp_id "
                + "WHERE s.supp_name = '"+supp+"' "
                + "ORDER BY c.cat_description";
        ResultSet resultSet = null;
        ArrayList<Equipment> eList = new ArrayList<Equipment>();

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
                
                Person accnt_person = new Person();
                accnt_person.setEmp_id(resultSet.getString("emp_id"));
                accnt_person.setEmp_lname(resultSet.getString("emp_lname"));
                accnt_person.setEmp_fname(resultSet.getString("emp_fname"));
                accnt_person.setEmp_mname(resultSet.getString("emp_mname"));
                accnt_person.setEmp_sname(resultSet.getString("emp_sname"));
                accnt_person.setEmp_position(resultSet.getString("emp_position"));
                accnt_person.setEmp_department(d);
                
                Category cat = new Category();
                cat.setCat_id(resultSet.getInt("cat_id"));
                cat.setCat_name(resultSet.getString("cat_description"));
                
                Supplier supplier = new Supplier();
                supplier.setSupp_id(resultSet.getInt("supp_id"));
                supplier.setSupp_name(resultSet.getString("supp_name"));
                
                Equipment e = new Equipment();
                e.setControl_no(resultSet.getString("control_no"));
                e.setCategory(cat);
                e.setAcquisition_date(resultSet.getString("acquisition_date"));
                e.setQuantity(resultSet.getInt("quantity"));
                e.setUnit(resultSet.getString("unit"));
                e.setItem_descrption(resultSet.getString("item_description"));
                e.setUnit_price(resultSet.getDouble("unit_price"));
                e.setRiv(resultSet.getString("riv"));
                e.setPo(resultSet.getString("po"));
                e.setAccnt_person(accnt_person);
                e.setSupplier(supplier);
                e.setRemarks(resultSet.getString("remarks"));
                e.setMonth(resultSet.getInt("month"));
                e.setYear(resultSet.getInt("year"));
                e.setSeq(resultSet.getInt("seq"));
                e.setSequence_no(resultSet.getString("sequence_no"));
                
                eList.add(e);
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

        return eList;
    }

    @Override
    public ArrayList<Equipment> getEquipment_category_date(String category, int month, int year) throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM equipment_tbl j "
                + "INNER JOIN employee_tbl e ON j.accnt_person = e.emp_id "
                + "INNER JOIN department_tbl d ON e.emp_department = d.dept_id "
                + "INNER JOIN category_tbl c ON j.category = c.cat_id "
                + "INNER JOIN supplier_tbl s ON j.supplier = s.supp_id "
                + "WHERE j.year = "+year+" AND j.month = "+month+" "
                + "AND c.cat_description = '"+category+"' "
                + "ORDER BY c.cat_description";
        ResultSet resultSet = null;
        ArrayList<Equipment> eList = new ArrayList<Equipment>();

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
                
                Person accnt_person = new Person();
                accnt_person.setEmp_id(resultSet.getString("emp_id"));
                accnt_person.setEmp_lname(resultSet.getString("emp_lname"));
                accnt_person.setEmp_fname(resultSet.getString("emp_fname"));
                accnt_person.setEmp_mname(resultSet.getString("emp_mname"));
                accnt_person.setEmp_sname(resultSet.getString("emp_sname"));
                accnt_person.setEmp_position(resultSet.getString("emp_position"));
                accnt_person.setEmp_department(d);
                
                Category cat = new Category();
                cat.setCat_id(resultSet.getInt("cat_id"));
                cat.setCat_name(resultSet.getString("cat_description"));
                
                Supplier supplier = new Supplier();
                supplier.setSupp_id(resultSet.getInt("supp_id"));
                supplier.setSupp_name(resultSet.getString("supp_name"));
                
                Equipment e = new Equipment();
                e.setControl_no(resultSet.getString("control_no"));
                e.setCategory(cat);
                e.setAcquisition_date(resultSet.getString("acquisition_date"));
                e.setQuantity(resultSet.getInt("quantity"));
                e.setUnit(resultSet.getString("unit"));
                e.setItem_descrption(resultSet.getString("item_description"));
                e.setUnit_price(resultSet.getDouble("unit_price"));
                e.setRiv(resultSet.getString("riv"));
                e.setPo(resultSet.getString("po"));
                e.setAccnt_person(accnt_person);
                e.setSupplier(supplier);
                e.setRemarks(resultSet.getString("remarks"));
                e.setMonth(resultSet.getInt("month"));
                e.setYear(resultSet.getInt("year"));
                e.setSeq(resultSet.getInt("seq"));
                e.setSequence_no(resultSet.getString("sequence_no"));
                
                eList.add(e);
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

        return eList;
    }
    
}
