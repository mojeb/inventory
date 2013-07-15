/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author mojeb
 */
public class Supplier {
    
    private int supp_id;
    private String supp_name;
    private String supp_address;
    private String supp_contact;
    private String supp_email;

    public Supplier() {
        this.supp_id = -1;
        this.supp_name = "";
        this.supp_address = "";
        this.supp_contact = "";
        this.supp_email = "";
    }
    
    public Supplier(int supp_id, String supp_name, String supp_address, 
            String supp_contact, String supp_email) {
        this.supp_id = supp_id;
        this.supp_name = supp_name;
        this.supp_address = supp_address;
        this.supp_contact = supp_contact;
        this.supp_email = supp_email;
    }

    public String getSupp_address() {
        return supp_address;
    }

    public void setSupp_address(String supp_address) {
        this.supp_address = supp_address;
    }

    public String getSupp_contact() {
        return supp_contact;
    }

    public void setSupp_contact(String supp_contact) {
        this.supp_contact = supp_contact;
    }

    public String getSupp_email() {
        return supp_email;
    }

    public void setSupp_email(String supp_email) {
        this.supp_email = supp_email;
    }

    public int getSupp_id() {
        return supp_id;
    }

    public void setSupp_id(int supp_id) {
        this.supp_id = supp_id;
    }

    public String getSupp_name() {
        return supp_name;
    }

    public void setSupp_name(String supp_name) {
        this.supp_name = supp_name;
    }
    
    public String getSupplierCompleteInfo(){
        return ("  "+this.supp_name+"    "+this.supp_address+"    "+
                this.supp_contact+"    "+this.supp_email);
    }
}
