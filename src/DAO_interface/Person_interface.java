/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO_interface;

import Models.Person;
import Tool.ErrorException;
import java.util.ArrayList;

/**
 *
 * @author mojeb
 */
public interface Person_interface {
    
    public int getResponse();
    public void addPerson(Person p) throws ErrorException;
    public void updatePerson(Person p) throws ErrorException;
    public void deletePerson(Person p) throws ErrorException;
    public Person getPersonID(String emp_id) throws ErrorException;
    public ArrayList<Person> getAllPerson() throws ErrorException;
    public ArrayList<Person> getPersonLastname(String lname) throws ErrorException;
    public ArrayList<Person> getPersonFirstname(String fname) throws ErrorException;
    //modify this...
    public ArrayList<Person> getPersonDepartment(String dept) throws ErrorException;
}
