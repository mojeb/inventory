/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_interface;

import Models.Equipment;
import Tool.ErrorException;
import java.util.ArrayList;

/**
 *
 * @author mojeb
 */
public interface Equipment_interface {
    
    public int getResponse();
    public void addEquipment(Equipment e) throws ErrorException;
    public void editEquipment(Equipment e) throws ErrorException;
    public void deleteEquipment(Equipment e) throws ErrorException;
    public Equipment getEquipment_control_no(String control_no) throws ErrorException;
    public ArrayList<Equipment> getEquipment_description(String description) throws ErrorException;
    public ArrayList<Equipment> getEquipment_year_month(int year, int month) throws ErrorException;
    public ArrayList<Equipment> getEquipment_category(String category) throws ErrorException;
    public ArrayList<Equipment> getEquipment_employee(String emp_id) throws ErrorException;
    public ArrayList<Equipment> getEquipment_sequence_no(String sequence_no) throws ErrorException;
    public ArrayList<Equipment> getEquipment_po_no(String po_no) throws ErrorException;
    public ArrayList<Equipment> getEquipment_riv_no(String riv_no) throws ErrorException;
    public ArrayList<Equipment> getEquipment_office(String office) throws ErrorException;
    public ArrayList<Equipment> getEquipment_supplier(String supplier) throws ErrorException;
    public ArrayList<Equipment> getEquipment_category_date(String category, int month, int year) throws ErrorException;
    public int getLastSeq(int year, int month) throws ErrorException;
}
