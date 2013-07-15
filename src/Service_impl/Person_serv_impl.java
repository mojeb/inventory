/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Service_impl;

import DAO_impl.Person_impl;
import DAO_interface.Person_interface;
import Models.Person;
import Service_interface.Person_serv_interface;
import Tool.ErrorException;
import java.util.ArrayList;

/**
 *
 * @author mojeb
 */
public class Person_serv_impl implements Person_serv_interface {

    private Person_interface person;

    public Person_serv_impl (){
        person = new Person_impl();
    }
    
    @Override
    public int getResponse() {
        return person.getResponse();
    }

    @Override
    public ArrayList<Person> getPersonDepartment(String dept) throws ErrorException {
        return person.getPersonDepartment(dept);
    }

    @Override
    public Person getPersonID(String id) throws ErrorException {
        return person.getPersonID(id);
    }

    @Override
    public void addPerson(Person p) throws ErrorException {
        person.addPerson(p);
    }

    @Override
    public void updatePerson(Person p) throws ErrorException {
        person.updatePerson(p);
    }

    @Override
    public void deletePerson(Person p) throws ErrorException {
        person.deletePerson(p);
    }

    @Override
    public ArrayList<Person> getAllPerson() throws ErrorException {
        return person.getAllPerson();
    }

    @Override
    public ArrayList<Person> getPersonLastname(String lname) throws ErrorException {
        return person.getPersonLastname(lname);
    }

    @Override
    public ArrayList<Person> getPersonFirstname(String fname) throws ErrorException {
        return person.getPersonFirstname(fname);
    }
    
}
