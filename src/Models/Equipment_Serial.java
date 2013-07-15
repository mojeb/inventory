/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Rommel
 */
public class Equipment_Serial {
    
    private String are_no;
    private String pa_no;
    private String item_description;
    private String riv;
    private String po;
    private String acquisition_date;
    private double unit_price;
    private Supplier supplier;
    private Person accnt_person;
    private Category category;
    private String Equip_CPU_model;
    private String Equip_CPU_serial;
    private String monitor_model;
    private String monitor_serial;
    private String keyboard_model;
    private String keyboard_serial;
    private String seq_no;
    private int year;
    private int seq;
    
    public Equipment_Serial(String are_no, String pa_no, String item_description, 
            String riv, String po, String acquisition_date, double unit_price, 
            Supplier supplier, Person accnt_person, Category category, 
            String Equip_CPU_model, String Equip_CPU_serial, String monitor_model, 
            String monitor_serial, String keyboard_model, String keyboard_serial, 
            String seq_no, int year, int seq) {
        this.are_no = are_no;
        this.pa_no = pa_no;
        this.item_description = item_description;
        this.riv = riv;
        this.po = po;
        this.acquisition_date = acquisition_date;
        this.unit_price = unit_price;
        this.supplier = supplier;
        this.accnt_person = accnt_person;
        this.category = category;
        this.Equip_CPU_model = Equip_CPU_model;
        this.Equip_CPU_serial = Equip_CPU_serial;
        this.monitor_model = monitor_model;
        this.monitor_serial = monitor_serial;
        this.keyboard_model = keyboard_model;
        this.keyboard_serial = keyboard_serial;
        this.seq_no = seq_no;
        this.year = year;
        this.seq = seq;
    }

    public Equipment_Serial() {
        this.are_no = "";
        this.pa_no = "";
        this.item_description = "";
        this.riv = "";
        this.po = "";
        this.acquisition_date = "";
        this.unit_price = 0.00;
        this.supplier = new Supplier();
        this.accnt_person = new Person();
        this.category = new Category();
        this.Equip_CPU_model = "";
        this.Equip_CPU_serial = "";
        this.monitor_model = "";
        this.monitor_serial = "";
        this.keyboard_model = "";
        this.keyboard_serial = "";
        this.seq_no = "";
        this.year = 0;
        this.seq = 0;
    }

    public String getEquip_CPU_model() {
        return Equip_CPU_model;
    }

    public void setEquip_CPU_model(String Equip_CPU_model) {
        this.Equip_CPU_model = Equip_CPU_model;
    }

    public String getEquip_CPU_serial() {
        return Equip_CPU_serial;
    }

    public void setEquip_CPU_serial(String Equip_CPU_serial) {
        this.Equip_CPU_serial = Equip_CPU_serial;
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

    public String getAre_no() {
        return are_no;
    }

    public void setAre_no(String are_no) {
        this.are_no = are_no;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    public String getKeyboard_model() {
        return keyboard_model;
    }

    public void setKeyboard_model(String keyboard_model) {
        this.keyboard_model = keyboard_model;
    }

    public String getKeyboard_serial() {
        return keyboard_serial;
    }

    public void setKeyboard_serial(String keyboard_serial) {
        this.keyboard_serial = keyboard_serial;
    }

    public String getMonitor_model() {
        return monitor_model;
    }

    public void setMonitor_model(String monitor_model) {
        this.monitor_model = monitor_model;
    }

    public String getMonitor_serial() {
        return monitor_serial;
    }

    public void setMonitor_serial(String monitor_serial) {
        this.monitor_serial = monitor_serial;
    }

    public String getPa_no() {
        return pa_no;
    }

    public void setPa_no(String pa_no) {
        this.pa_no = pa_no;
    }

    public String getPo() {
        return po;
    }

    public void setPo(String po) {
        this.po = po;
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

    public String getSeq_no() {
        return seq_no;
    }

    public void setSeq_no(String seq_no) {
        this.seq_no = seq_no;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
