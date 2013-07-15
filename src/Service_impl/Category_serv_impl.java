/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Service_impl;

import DAO_impl.Category_impl;
import DAO_interface.Category_interface;
import Models.Category;
import Service_interface.Category_serv_interface;
import Tool.ErrorException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author mojeb
 */
public class Category_serv_impl implements Category_serv_interface {

    private Category_interface category;

    public Category_serv_impl (){
        category = new Category_impl();
    }
    
    @Override
    public int getResponse() {
        return category.getResponse();
    }

    @Override
    public void addCategory(Category cat) throws ErrorException {
        category.addCategory(cat);
    }

    @Override
    public void updateCategory(Category cat) throws ErrorException {
        category.updateCategory(cat);
    }

    @Override
    public ArrayList<Category> getCategoryList() throws ErrorException, FileNotFoundException, IOException {
        return category.getCategoryList();
    }
    
    @Override
    public Category getcategoryByName(String name) throws ErrorException {
        return category.getcategoryByName(name);
    }

    @Override
    public Category getcategoryById(int id) throws ErrorException {
        return category.getcategoryByID(id);
    }
    
}
