/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Service_impl;

import DAO_impl.Supplier_impl;
import DAO_interface.Supplier_interface;
import Models.Supplier;
import Service_interface.Supplier_serv_interface;
import Tool.ErrorException;
import java.util.ArrayList;

/**
 *
 * @author mojeb
 */
public class Supplier_serv_impl implements Supplier_serv_interface {

    private Supplier_interface supplier;

    public Supplier_serv_impl (){
        supplier = new Supplier_impl();
    }
    
    @Override
    public int getResponce() {
        return supplier.getResponce();
    }

    @Override
    public void addSupplier(Supplier supp) throws ErrorException {
        supplier.addSupplier(supp);
    }

    @Override
    public void updateSupplier(Supplier supp) throws ErrorException {
        supplier.updateSupplier(supp);
    }

    @Override
    public void deleteSupplier(Supplier supp) throws ErrorException {
        supplier.deleteSupplier(supp);
    }

    @Override
    public ArrayList<Supplier> getSupplier(String name) throws ErrorException {
        return supplier.getSupplier(name);
    }

    @Override
    public ArrayList<Supplier> getSupplierList() throws ErrorException {
        return supplier.getSupplierList();
    }

    @Override
    public Supplier getSupplierByName(String name) throws ErrorException {
        return supplier.getSupplierByName(name);
    }

    @Override
    public Supplier getSupplierById(int id) throws ErrorException {
        return supplier.getSupplierByID(id);
    }
    
}
