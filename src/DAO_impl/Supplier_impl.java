/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_impl;

import DAO_interface.Database_interface;
import DAO_interface.Supplier_interface;
import Models.Supplier;
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
public class Supplier_impl implements Supplier_interface {

    private Database_interface manager = null;
    private int response;

    private static final int SUCCESS = 1;
    private static final int FAILED = 0;

    public Supplier_impl(){
        this(null);
    }

    public Supplier_impl(Database_interface manager){
        this.manager = manager;
    }
    
    @Override
    public int getResponce() {
        return response;
    }

    @Override
    public void addSupplier(Supplier supp) throws ErrorException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "INSERT INTO supplier_tbl VALUES(?,?,?,?,?)";
        ResultSet resultSet = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();

            }

            connection = manager.getConnection();
            pStatement = connection.prepareStatement(sql);

            pStatement.setInt(1, 0);
            pStatement.setString(2, supp.getSupp_name());
            pStatement.setString(3, supp.getSupp_address());
            pStatement.setString(4, supp.getSupp_contact());
            pStatement.setString(5, supp.getSupp_email());

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
    public void updateSupplier(Supplier supp) throws ErrorException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "UPDATE supplier_tbl SET supp_name=?, supp_address=?, supp_contact=?, "
                + "supp_email=? WHERE supp_id = "+supp.getSupp_id();
        ResultSet resultSet = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();

            }

            connection = manager.getConnection();
            pStatement = connection.prepareStatement(sql);

            pStatement.setString(1, supp.getSupp_name());
            pStatement.setString(2, supp.getSupp_address());
            pStatement.setString(3, supp.getSupp_contact());
            pStatement.setString(4, supp.getSupp_email());

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
    public void deleteSupplier(Supplier supp) throws ErrorException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "DELETE FROM supplier_tbl "
                + "WHERE supp_id = "+supp.getSupp_id();
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
    public ArrayList<Supplier> getSupplier(String name) throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM supplier_tbl "
                + "WHERE supp_name LIKE '"+name+"%' "
                + "ORDER BY supp_name";
        ResultSet resultSet = null;
        ArrayList<Supplier> suppList = new ArrayList<Supplier>();

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();

            }

            connection = manager.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                
                Supplier supp = new Supplier();
                supp.setSupp_id(resultSet.getInt("supp_id"));
                supp.setSupp_name(resultSet.getString("supp_name"));
                supp.setSupp_address(resultSet.getString("supp_address"));
                supp.setSupp_contact(resultSet.getString("supp_contact"));
                supp.setSupp_email(resultSet.getString("supp_email"));
                
                suppList.add(supp);
            }
            
        }catch (SQLException ex) {
            throw new ErrorException(ex.getMessage(), "SQLException");
        }catch (FileNotFoundException ex) {
            
        }catch(IOException ex){
            
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return suppList;
    }

    @Override
    public ArrayList<Supplier> getSupplierList() throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM supplier_tbl "
                + "ORDER BY supp_name";
        ResultSet resultSet = null;
        ArrayList<Supplier> supplierList = new ArrayList<Supplier> ();

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();

            }

            connection = manager.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                
                Supplier supp = new Supplier();
                supp.setSupp_id(resultSet.getInt("supp_id"));
                supp.setSupp_name(resultSet.getString("supp_name"));
                supp.setSupp_address(resultSet.getString("supp_address"));
                supp.setSupp_contact(resultSet.getString("supp_contact"));
                supp.setSupp_email(resultSet.getString("supp_email"));
                
                supplierList.add(supp);
            }
            
        }catch (SQLException ex) {
            throw new ErrorException(ex.getMessage(), "SQLException");
        }catch (FileNotFoundException ex) {
            
        }catch(IOException ex){
            
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return supplierList;
    }

    @Override
    public Supplier getSupplierByName(String name) throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM supplier_tbl "
                + "WHERE supp_name = '"+name+"'";
        ResultSet resultSet = null;
        Supplier supp = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();

            }

            connection = manager.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                
                supp = new Supplier();
                supp.setSupp_id(resultSet.getInt("supp_id"));
                supp.setSupp_name(resultSet.getString("supp_name"));
                supp.setSupp_address(resultSet.getString("supp_address"));
                supp.setSupp_contact(resultSet.getString("supp_contact"));
                supp.setSupp_email(resultSet.getString("supp_email"));
            }
            
        }catch (SQLException ex) {
            throw new ErrorException(ex.getMessage(), "SQLException");
        }catch (FileNotFoundException ex) {
            
        }catch(IOException ex){
            
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return supp;
    }

    @Override
    public Supplier getSupplierByID(int id) throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM supplier_tbl "
                + "WHERE supp_id = "+id;
        ResultSet resultSet = null;
        Supplier supp = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();

            }

            connection = manager.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                
                supp = new Supplier();
                supp.setSupp_id(resultSet.getInt("supp_id"));
                supp.setSupp_name(resultSet.getString("supp_name"));
                supp.setSupp_address(resultSet.getString("supp_address"));
                supp.setSupp_contact(resultSet.getString("supp_contact"));
                supp.setSupp_email(resultSet.getString("supp_email"));
            }
            
        }catch (SQLException ex) {
            throw new ErrorException(ex.getMessage(), "SQLException");
        }catch (FileNotFoundException ex) {
            
        }catch(IOException ex){
            
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return supp;
    }
    
}
