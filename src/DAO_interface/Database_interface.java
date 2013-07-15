/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_interface;

import Tool.ErrorException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;

/**
 *
 * @author mojeb
 */
public interface Database_interface {
    
    public Connection getConnection() throws ErrorException, FileNotFoundException, IOException ;
    public boolean isConnectedToDatabase() throws ErrorException;
}
