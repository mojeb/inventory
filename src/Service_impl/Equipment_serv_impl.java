/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Service_impl;

import DAO_impl.Equipment_impl;
import DAO_interface.Equipment_interface;
import Models.Equipment;
import Service_interface.Equipment_serv_interface;
import Tool.ErrorException;
import java.util.ArrayList;

/**
 *
 * @author mojeb
 */
public class Equipment_serv_impl implements Equipment_serv_interface {
    
    private Equipment_interface equipment;

    public Equipment_serv_impl (){
        equipment = new Equipment_impl();
    }

    @Override
    public int getResponse() {
        return equipment.getResponse();
    }

    @Override
    public void addEquipment(Equipment e) throws ErrorException {
        equipment.addEquipment(e);
    }

    @Override
    public void editEquipment(Equipment e) throws ErrorException {
        equipment.editEquipment(e);
    }

    @Override
    public void deleteEquipment(Equipment e) throws ErrorException {
        equipment.deleteEquipment(e);
    }

    @Override
    public Equipment getEquipment_control_no(String control_no) throws ErrorException {
        return equipment.getEquipment_control_no(control_no);
    }

    @Override
    public ArrayList<Equipment> getEquipment_description(String description) throws ErrorException {
        return equipment.getEquipment_description(description);
    }

    @Override
    public ArrayList<Equipment> getEquipment_year_month(int year, int month) throws ErrorException {
        return equipment.getEquipment_year_month(year, month);
    }

    @Override
    public ArrayList<Equipment> getEquipment_category(String category) throws ErrorException {
        return equipment.getEquipment_category(category);
    }

    @Override
    public ArrayList<Equipment> getEquipment_employee(String emp_id) throws ErrorException {
        return equipment.getEquipment_employee(emp_id);
    }

    @Override
    public ArrayList<Equipment> getEquipment_sequence_no(String sequence_no) throws ErrorException {
        return equipment.getEquipment_sequence_no(sequence_no);
    }

    @Override
    public ArrayList<Equipment> getEquipment_po_no(String po_no) throws ErrorException {
        return equipment.getEquipment_po_no(po_no);
    }

    @Override
    public ArrayList<Equipment> getEquipment_riv_no(String riv_no) throws ErrorException {
        return equipment.getEquipment_riv_no(riv_no);
    }

    @Override
    public ArrayList<Equipment> getEquipment_office(String office) throws ErrorException {
        return equipment.getEquipment_office(office);
    }

    @Override
    public int getLastSeq(int year, int month) throws ErrorException {
        return equipment.getLastSeq(year, month);
    }

    @Override
    public ArrayList<Equipment> getEquipment_supplier(String supplier) throws ErrorException {
        return equipment.getEquipment_supplier(supplier);
    }

    @Override
    public ArrayList<Equipment> getEquipment_category_date(String category, int month, int year) throws ErrorException {
        return equipment.getEquipment_category_date(category, month, year);
    }
    
}
