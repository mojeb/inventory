/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tool;

import Models.*;
import Service_impl.*;
import Service_interface.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hatiefm
 */
public class Methods {
    
    private Supplier_serv_interface suppServ;
    private Category_serv_interface catServ;
    private Person_serv_interface pServ;
    private Equipment_serv_interface eServ;
    private Department_serv_interface dServ;
    private JRF_serv_interface jrfServ;
    private Account_serv_interface accServ;
    
    public void loadCategory(JComboBox combo){
        combo.removeAllItems();
        ArrayList<Category> catList = new ArrayList<Category>();
        catServ = new Category_serv_impl();
        try{
            catList = catServ.getCategoryList();
        }catch(ErrorException ex){ }
        catch (FileNotFoundException ex) { }
        catch(IOException ex){ }
        combo.addItem("Select Category");
        for(Category cat : catList)
            combo.addItem(cat.getCat_name());
    }
    
    public void loadPerson(JComboBox combo, String dept){
        combo.removeAllItems();
        ArrayList<Person> pList = new ArrayList<Person>();
        pServ = new Person_serv_impl();
        try{
            pList = pServ.getPersonDepartment(dept);
        }catch(ErrorException ex){ }
        
        combo.addItem("Select Personnel");
        for(Person p : pList)
            combo.addItem(p.getEmp_id()+" "+p.getCompleteName());
    }
    
    public void loadYear(JComboBox combo){
        Calendar now = new GregorianCalendar();
        combo.addItem("Year");
        for(int i = now.getTime().getYear()+1900; i >= 2007; i--)
            combo.addItem(i);
    }
    
    public String getCurrebtDate(){
        Calendar now = new GregorianCalendar();
        String date = toStringMonth(now.getTime().getMonth())+" "+
                now.getTime().getDate()+", "+(now.getTime().getYear()+1900);
        
        return date;
    }
    
    public String getDateApplication(){
        Calendar now = new GregorianCalendar();
        String date = now.getTime().getMonth()+"/"+
                now.getTime().getDate()+"/"+(now.getTime().getYear()+1900);
        
        return date;
    }
    
    public String getEmp_ID(String str){
        String id;
        StringTokenizer t;
        t = new StringTokenizer(str);
        id = t.nextToken();
        return id;
    }
    
    public String toStringMonth(int month){
        String month_str = "";
        if(month == 0){
            month_str = "January";
        }else if(month == 1){
            month_str = "February";
        }else if(month == 2){
            month_str = "March";
        }else if(month == 3){
            month_str = "April";
        }else if(month == 4){
            month_str = "May";
        }else if(month == 5){
            month_str = "June";
        }else if(month == 6){
            month_str = "July";
        }else if(month == 7){
            month_str = "August";
        }else if(month == 8){
            month_str = "September";
        }else if(month == 9){
            month_str = "October";
        }else if(month == 10){
            month_str = "November";
        }else if(month == 11){
            month_str = "December";
        }
        return month_str;
    }
    
    public void loadSupplier(JComboBox combo){
        combo.removeAllItems();
        ArrayList<Supplier> suppList = new ArrayList<Supplier>();
        suppServ = new Supplier_serv_impl();
        try{
            suppList = suppServ.getSupplierList();
        }catch(ErrorException ex){ }
        combo.addItem("Select Supplier");
        for(Supplier supp : suppList)
            combo.addItem(supp.getSupp_name());
    }
    
    public void clearTable(DefaultTableModel table){
        int lastRow = table.getRowCount() - 1;

        for(int temp = lastRow ; temp > -1 ;temp--){
            table.removeRow(temp);

        }
    }
    
    public void displaySuupliers(JList list, final ArrayList<Supplier> 
            suppList){
        list.removeAll();
        list.setModel(new javax.swing.AbstractListModel() {
            @Override
            public int getSize() {
                return suppList.size();
            }
            @Override
            public String getElementAt(int index) {
                return "   "+suppList.get(index).getSupp_name();
            }
        });
    }
    
    public void displayToners(JList list, final ArrayList<Toner> 
            tList){
        list.removeAll();
        list.setModel(new javax.swing.AbstractListModel() {
            @Override
            public int getSize() {
                return tList.size();
            }
            @Override
            public String getElementAt(int index) {
                return "   "+tList.get(index).getToner_name();
            }
        });
    }
    
    public void displayCategory(JList list, final ArrayList<Category> 
            catList){
        list.removeAll();
        list.setModel(new javax.swing.AbstractListModel() {
            @Override
            public int getSize() {
                return catList.size();
            }
            @Override
            public String getElementAt(int index) {
                return "   "+catList.get(index).getCat_name();
            }
        });
    }
    
    public int checkSupplier(Supplier supp){
        suppServ = new Supplier_serv_impl();
        int value = 0;
        try{
            Supplier s = suppServ.getSupplierByName(supp.getSupp_name());
            if(s != null)
                value = 1;
        }catch(ErrorException ex){ }
        return value;
    }
    
    public int checkDepartment(Department d){
        int value = 0;
        dServ = new Department_serv_impl();
        try{
            Department dept_code = dServ.getDepartmentbyCode(d.getDept_code());
            Department dept_name = dServ.getDepartmentbyName(d.getDept_name());
            
            if(dept_code != null || dept_name != null)
                value = 1;
        }catch(ErrorException ex){ }
        return value;
    }
    
    public void loadDepartment(JComboBox combo){
        combo.removeAllItems();
        ArrayList<Department> dList = new ArrayList<Department>();
        dServ = new Department_serv_impl();
        try{
            dList = dServ.getAllDepartment();
        }catch(ErrorException ex){ }
        combo.addItem("Select Office");
        for(Department d : dList)
            combo.addItem(d.getDept_code());
    }
    
    public int checkCategory(Category cat){
        catServ = new Category_serv_impl();
        int value = 0;
        try{
            Category c = catServ.getcategoryByName(cat.getCat_name());
            if(c != null)
                value = 1;
        }catch(ErrorException ex){ }
        return value;
    }
    
    public int checkTonerDuplicate(ArrayList<Toner> tList, String toner){
        int value = -1;
        for(Toner t : tList){
            if(t.getToner_name().equalsIgnoreCase(toner)){
                value = 1;
                break;
            }
        }
        return value;
    }
    
    public String getServerIP() throws FileNotFoundException, IOException{
        String s="";
        
        File file = new File("serverIP.txt");
        FileInputStream fis;
        BufferedInputStream bis;
        DataInputStream dis;        
        fis = new FileInputStream(file);
        bis = new BufferedInputStream(fis);
        dis = new DataInputStream(bis);
        while (dis.available() != 0) {
           s = dis.readLine();
        }
        fis.close();
        bis.close();
        dis.close(); 
        
        return s;
    }
    
    public Category getCategory(String cat){
        Category c = new Category();
        catServ = new Category_serv_impl();
        try{
            c = catServ.getcategoryByName(cat);
        }catch(ErrorException e){ }
        return c;
    }
    
    public Category getCategoryByID(int id){
        Category c = new Category();
        catServ = new Category_serv_impl();
        try{
            c = catServ.getcategoryById(id);
        }catch(ErrorException e){ }
        return c;
    }
    
    public Supplier getSupplier(String supp){
        Supplier s = new Supplier();
        suppServ = new Supplier_serv_impl();
        try{
            s = suppServ.getSupplierByName(supp);
        }catch(ErrorException e){ }
        return s;
    }
    
    public Supplier getSupplierByID(int id){
        Supplier s = new Supplier();
        suppServ = new Supplier_serv_impl();
        try{
            s = suppServ.getSupplierById(id);
        }catch(ErrorException e){ }
        return s;
    }
    
    public Person getEmployee(String id){
        Person p = new Person();
        pServ = new Person_serv_impl();
        try{
            p = pServ.getPersonID(id);
        }catch(ErrorException e){ }
        return p;
    }
    
    public void displayDepartment(DefaultTableModel model, ArrayList<Department> list){
        clearTable(model);
        pServ = new Person_serv_impl();
        try{
            for (Department d : list) {
                Person p = new Person();
                if(!d.getPerson().getEmp_id().isEmpty()){
                    p = pServ.getPersonID(d.getPerson().getEmp_id());
                }
                
                model.addRow(new Object[]{d.getDept_code(), d.getDept_name(),
                p.getCompleteName()});
            }
        }catch(ErrorException ex){}
    }
    
    public void displayPerson(DefaultTableModel model, ArrayList<Person> list){
        clearTable(model);
        for (Person p : list) {
            model.addRow(new Object[]{p.getEmp_id(), p.getCompleteName()});
        }
    }
    
    public String getControlNo(){
        String control_no = "";
        jrfServ = new JRF_serv_impl();
        try{
            
            int last_seq = jrfServ.getLast_seq(getCurrentYear())+1;
            if(last_seq == 0)
                control_no = "0001-"+(getCurrentYear()-2000);
            else if(last_seq < 10)
                control_no = "000"+last_seq+"-"+(getCurrentYear()-2000);
            else if(last_seq > 10 && last_seq < 100)
                control_no = "00"+last_seq+"-"+(getCurrentYear()-2000);
            else if(last_seq > 100 && last_seq < 1000)
                control_no = "0"+last_seq+"-"+(getCurrentYear()-2000);
            else if(last_seq > 1000)
                control_no = last_seq+"-"+(getCurrentYear()-2000);
        }catch(ErrorException ex){}
        return control_no;
    }
    
    public String getEquip_ControlNo(){
        String control_no = "";
        eServ = new Equipment_serv_impl();
        try{
            String year = (getCurrentYear()-2000)+"";
            String month = getCurrentMonth()+"";
            if(getCurrentMonth() < 10)
                month = "0"+getCurrentMonth();
            
            int last_seq = eServ.getLastSeq(getCurrentYear(), getCurrentMonth())+1;
            
            if(last_seq == 0)
                control_no = "001-"+month+"-"+year;
            else if(last_seq < 10)
                control_no = "00"+last_seq+"-"+month+"-"+year;
            else if(last_seq > 10 && last_seq < 100)
                control_no = "0"+last_seq+"-"+month+"-"+year;
            else if(last_seq > 100 && last_seq < 1000)
                control_no = last_seq+"-"+month+"-"+year;
        }catch(ErrorException ex){}
        return control_no;
    }
    
    public int getCurrentYear(){
        Calendar year = new GregorianCalendar();
        return year.getTime().getYear()+1900;
    }
    
    public int getCurrentMonth(){
        Calendar year = new GregorianCalendar();
        return year.getTime().getMonth()+1;
    }
    
    public void displayJRF(DefaultTableModel model, ArrayList<JRF> list){
        clearTable(model);
        for (JRF jrf : list) {
            String job_re = "";
            if(jrf.getJob_req_on() == 0){
                job_re = "Hardware/OS";
            }else if(jrf.getJob_req_on() == 1){
                job_re = "Network";
            }else job_re = "Database";
            
            model.addRow(new Object[]{jrf.getControl_no(), jrf.getDate_app().toString(), 
                jrf.getDept().getDept_code(), jrf.getRequested_by().getCompleteName(), 
                job_re});
        }
    }
    
    public void displayJobReqAssignmentList(DefaultTableModel model, ArrayList<JobReqAssignment> list){
        clearTable(model);
        for (JobReqAssignment j : list) {
            
            model.addRow(new Object[]{j.getControl_no(), j.getDate_assigned(),
                    j.getAssigned_to().getCompleteName(), "Assigned to IT Staff", 
                    j.getAssigned_by().getCompleteName()});
        }
    }
    
    public void displayEquipments(DefaultTableModel model, ArrayList<Equipment> list){
        clearTable(model);
        for (Equipment e : list) {
            model.addRow(new Object[]{e.getCategory().getCat_name(), e.getItem_descrption(),
            e.getAcquisition_date(), e.getRiv(), e.getPo(),
            e.getSupplier().getSupp_name(), e.getUnit_price(), e.getAccnt_person().getCompleteName(),
            e.getAccnt_person().getEmp_department().getDept_code()});
        }
    }
    
    public void displayEquipments_description(DefaultTableModel model, ArrayList<Equipment> list){
        clearTable(model);
        for (Equipment e : list) {
            model.addRow(new Object[]{e.getCategory().getCat_name(),e.getControl_no(),
                e.getItem_descrption(), e.getQuantity(), e.getUnit(),
                e.getAcquisition_date(), e.getRiv(), e.getPo(),
                e.getSupplier().getSupp_name(), e.getUnit_price(), e.getAccnt_person().getCompleteName(),
                e.getAccnt_person().getEmp_department().getDept_code()});
        }
    }
}