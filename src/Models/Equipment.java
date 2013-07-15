/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author mojeb
 */
public class Equipment {
    private String control_no;
    private Category category;
    private String acquisition_date;
    private int quantity;
    private String unit;
    private String item_descrption;
    private Double unit_price;
    private String riv;
    private String po;
    private Person accnt_person;
    private Supplier supplier;
    private String remarks;
    private int month;
    private int year;
    private int seq;
    private String sequence_no;
    
    public Equipment() {
        this.control_no = "";
        this.category = new Category();
        this.acquisition_date = "";
        this.quantity = 0;
        this.unit = "";
        this.item_descrption = "";
        this.unit_price = 0.00;
        this.riv = "";
        this.po = "";
        this.accnt_person = new Person();
        this.supplier = new Supplier();
        this.remarks = "";
        this.month = 0;
        this.year = 0;
        this.seq = 0;
        this.sequence_no = "";
    }

    public Equipment(String control_no, Category category, String acquisition_date, 
            int quantity, String unit, String item_descrption, Double unit_price, 
            String riv, String po, Person accnt_person, Supplier supplier, 
            String remarks, int month, String sequence_no,
            int year, int seq) {
        this.control_no = control_no;
        this.category = category;
        this.acquisition_date = acquisition_date;
        this.quantity = quantity;
        this.unit = unit;
        this.item_descrption = item_descrption;
        this.unit_price = unit_price;
        this.riv = riv;
        this.po = po;
        this.accnt_person = accnt_person;
        this.supplier = supplier;
        this.remarks = remarks;
        this.month = month;
        this.year = year;
        this.seq = seq;
        this.sequence_no = sequence_no;
    }

    public Person getAccnt_person() {
        return accnt_person;
    }

    public void setAccnt_person(Person accnt_person) {
        this.accnt_person = accnt_person;
    }

    public String getAcquisition_date() {
        return acquisition_date;
    }

    public void setAcquisition_date(String acquisition_date) {
        this.acquisition_date = acquisition_date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getControl_no() {
        return control_no;
    }

    public void setControl_no(String control_no) {
        this.control_no = control_no;
    }

    public String getItem_descrption() {
        return item_descrption;
    }

    public void setItem_descrption(String item_descrption) {
        this.item_descrption = item_descrption;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getPo() {
        return po;
    }

    public void setPo(String po) {
        this.po = po;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getRiv() {
        return riv;
    }

    public void setRiv(String riv) {
        this.riv = riv;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(Double unit_price) {
        this.unit_price = unit_price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    public String getSequence_no() {
        return sequence_no;
    }

    public void setSequence_no(String sequence_no) {
        this.sequence_no = sequence_no;
    }
}
