/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Service_interface;

import Models.Category;
import Tool.ErrorException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author mojeb
 */
public interface Category_serv_interface {
    
    public int getResponse();
    public void addCategory(Category cat) throws ErrorException;
    public void updateCategory(Category cat) throws ErrorException;
    public ArrayList<Category> getCategoryList() throws ErrorException, FileNotFoundException, IOException;
    public Category getcategoryByName(String name) throws ErrorException;
    public Category getcategoryById(int id) throws ErrorException;
}
