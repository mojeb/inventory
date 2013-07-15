/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_interface;

import Models.Supplier;
import Tool.ErrorException;
import java.util.ArrayList;

/**
 *
 * @author mojeb
 */
public interface Supplier_interface {
    
    public int getResponce();
    public void addSupplier(Supplier supp) throws ErrorException;
    public void updateSupplier(Supplier supp) throws ErrorException;
    public void deleteSupplier(Supplier supp) throws ErrorException;
    public ArrayList<Supplier> getSupplier(String name) throws ErrorException;
    public ArrayList<Supplier> getSupplierList() throws ErrorException;
    public Supplier getSupplierByName(String name) throws ErrorException;
    public Supplier getSupplierByID(int id) throws ErrorException;
}
