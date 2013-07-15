/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_impl;

import DAO_interface.Database_interface;
import Tool.ErrorException;
import Tool.Methods;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author mojeb
 */
public class Database_impl implements Database_interface {

    private Methods m = new Methods();
    
    private String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private String userName = "root";
    private String password = "root";
    private boolean connectedToDatabase = false;
    private volatile static Database_interface INSTANCE = null;

    public Database_impl() throws ErrorException{
        try {
            Class.forName(JDBC_DRIVER);
	} catch (ClassNotFoundException Exception) {
            throw new ErrorException(Exception.getMessage(),"ClassNotFoundException");
        }
    }


    public static Database_interface getInstance() throws ErrorException{
        if(INSTANCE==null){
            synchronized(Database_interface.class){
		if(INSTANCE==null){
                    INSTANCE= new Database_impl();
		}
            }
        }
        return INSTANCE;
    }

    @Override
    public Connection getConnection() throws ErrorException, FileNotFoundException, IOException{
        Connection connection=null;
        try{
            connection= DriverManager.getConnection( m.getServerIP(), userName, password );
            connectedToDatabase = true;
        }
        catch(SQLException e){
            throw new ErrorException("Error creating a connection" +
            "url:"+m.getServerIP()+
            "driverName:"+JDBC_DRIVER+
            "userName: "+userName, "SQLException");
        }
        return connection;
    }

    @Override
    public boolean isConnectedToDatabase(){
        return connectedToDatabase;
    }
}
