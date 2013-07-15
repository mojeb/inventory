/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_impl;

import DAO_interface.Category_interface;
import DAO_interface.Database_interface;
import Models.Category;
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
public class Category_impl implements Category_interface {

    private Database_interface manager = null;
    private int response;

    private static final int SUCCESS = 1;
    private static final int FAILED = 0;

    public Category_impl(){
        this(null);
    }

    public Category_impl(Database_interface manager){
        this.manager = manager;
    }
    
    @Override
    public int getResponse() {
        return response;
    }

    @Override
    public void addCategory(Category cat) throws ErrorException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "INSERT INTO category_tbl VALUES(?,?)";
        ResultSet resultSet = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();
            }

            connection = manager.getConnection();
            pStatement = connection.prepareStatement(sql);

            pStatement.setInt(1, 0);
            pStatement.setString(2, cat.getCat_name());

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
    public void updateCategory(Category cat) throws ErrorException {
        response = FAILED;
        Connection connection = null;
        PreparedStatement pStatement = null;
        String sql = "UPDATE category_tbl SET cat_description=? "
                + "WHERE cat_id = "+cat.getCat_id();
        ResultSet resultSet = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();

            }

            connection = manager.getConnection();
            pStatement = connection.prepareStatement(sql);

            pStatement.setString(1, cat.getCat_name());

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
    public ArrayList<Category> getCategoryList() throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM category_tbl "
                + "ORDER BY cat_description";
        ResultSet resultSet = null;
        ArrayList<Category> categoryList = new ArrayList<Category> ();

        try {
            if (manager == null) {
                manager = Database_impl.getInstance();
            }
            
            connection = manager.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            
            while(resultSet.next()){
                
                Category cat = new Category();
                cat.setCat_id(resultSet.getInt("cat_id"));
                cat.setCat_name(resultSet.getString("cat_description"));
                
                categoryList.add(cat);
            }
            
        }catch (SQLException ex) {
            ex.printStackTrace();
        }catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }catch(IOException ex){
            ex.printStackTrace();
        } finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return categoryList;
    }

    @Override
    public Category getcategoryByName(String name) throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM category_tbl "
                + "WHERE cat_description = '"+name+"'";
        ResultSet resultSet = null;
        Category cat = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();

            }

            connection = manager.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                
                cat = new Category();
                cat.setCat_id(resultSet.getInt("cat_id"));
                cat.setCat_name(resultSet.getString("cat_description"));
            }
            
        }catch (SQLException ex) {
            throw new ErrorException(ex.getMessage(), "SQLException");
        }catch (FileNotFoundException ex) {
            
        }catch(IOException ex){
            
        }finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return cat;
    }

    @Override
    public Category getcategoryByID(int id) throws ErrorException {
        Connection connection = null;
        Statement statement = null;
        String sql = "SELECT * FROM category_tbl "
                + "WHERE cat_id = "+id;
        ResultSet resultSet = null;
        Category cat = null;

        try {

            if (manager == null) {
                manager = Database_impl.getInstance();

            }

            connection = manager.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                
                cat = new Category();
                cat.setCat_id(resultSet.getInt("cat_id"));
                cat.setCat_name(resultSet.getString("cat_description"));
            }
            
        }catch (SQLException ex) {
            throw new ErrorException(ex.getMessage(), "SQLException");
        }catch (FileNotFoundException ex) {
            
        }catch(IOException ex){
            
        }finally {
            DataDispatcher.dispatch(resultSet, statement, connection);
        }

        return cat;
    }
    
}
