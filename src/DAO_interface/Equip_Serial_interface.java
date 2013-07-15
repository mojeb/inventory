/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_interface;

import Models.Equipment_Serial;
import Tool.ErrorException;
import java.util.ArrayList;

/**
 *
 * @author Rommel
 */
public interface Equip_Serial_interface {
    
    public int getResponse();
    public void addEquipSerial(Equipment_Serial es) throws ErrorException;
    public void editEquipSerial(Equipment_Serial es) throws ErrorException;
    public void deleteEquipSerial(Equipment_Serial es) throws ErrorException;
    public Equipment_Serial getEquipSerial_are_no(String are_no);
    public ArrayList<Equipment_Serial> getEquipSerial_seq_no(String seq_no);
    public ArrayList<Equipment_Serial> getEquipSerial_riv(String riv);
    public ArrayList<Equipment_Serial> getEquipSerial_po(String po);
    public ArrayList<Equipment_Serial> getEquipSerial_emp(String emp_id);
    public ArrayList<Equipment_Serial> getEquipSerial_year(int year);
    public int getLastSeq(int year);
}
