/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Models.Category;
import Models.Equipment;
import Service_impl.Category_serv_impl;
import Service_impl.Equipment_serv_impl;
import Service_interface.Category_serv_interface;
import Service_interface.Equipment_serv_interface;
import Tool.ErrorException;
import Tool.Methods;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;
import javax.swing.JPanel;

/**
 *
 * @author Rommel
 */
public class PrintData_EquipmentReport extends JPanel implements Printable {

    private ArrayList<Category> cList = null;
    private ArrayList<Equipment> eList = null;
    
    private Methods m = new Methods();
    private String date;
    private Category_serv_interface cServ;
    private Equipment_serv_interface eServ;
    
    public int y = 0;
    public int x = 0;
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        
        cServ = new Category_serv_impl();
        eServ = new Equipment_serv_impl();
        
        Font font;
        try{
            if(eList != null){
                font = new Font(Font.SANS_SERIF, Font.BOLD, 10);
                g.setFont(font);
                g.drawString("PHILIPPINE HEALTH INSURANCE CORPORATION", 20, 30);
                g.drawString("REPORT ON THE ACQUISITION OF EQUIPMENTS", 20, 60);
                font = new Font(Font.SANS_SERIF, Font.PLAIN, 10);
                g.setFont(font);
                g.drawString("PhilHealth Regional Office - ARMM", 20, 40);
                g.drawString(date, 20, 70);
                g.drawLine(20, 90, 520, 90);
                g.drawString("# Units", 20, 100);
                g.drawString("Item Description", 60, 100);
                g.drawString("Unit Cost", 300, 100);
                g.drawString("Total Amount", 380, 100);
                g.drawString("Control No.", 460, 100);
                g.drawLine(20, 105, 520, 105);
                
                int year = eList.get(0).getYear();
                int month = eList.get(0).getMonth();

                x = 20;
                y = 110;
                
                cList = cServ.getCategoryList();
                double grand_total = 0.00;
                for(Category cat : cList){
                    eList = eServ.getEquipment_category_date(cat.getCat_name(), month, year);
                    if(!eList.isEmpty()){
                        double total_amount = 0.00;
                        //display data
                        g.drawString(cat.getCat_name(), x, y+10);
                        y = y + 20;
                        for(Equipment e : eList){
                            g.drawString(e.getQuantity()+"", 30, y);
                            g.drawString(e.getUnit_price()+"", 300, y);
                            g.drawString((e.getQuantity()*e.getUnit_price())+"", 380, y);
                            total_amount = total_amount + (e.getQuantity()*e.getUnit_price());
                            g.drawString(e.getControl_no(), 460, y);
                            
                            String [] description = wrapText(e.getItem_descrption(), 45);
                            for (int n = 0; n < description.length; n++) {
                                g.drawString(description[n], 60, y);
                                y = y + 10;
                            }
                        }
                        //compute and display total amount...
                        g.drawString("TOTAL "+cat.getCat_name(), 100, y);
                        g.drawString(total_amount+"", 380, y);
                        y = y + 10;
                        grand_total = grand_total + total_amount;
                    }
                }
                g.drawString("Grand Total", 300, y);
                g.drawString(grand_total+"", 380, y);
                g.drawString("Prepared By: ____________________________", 20, y+50);
            }
            
            g.drawLine(20, 700, 520, 700);
            g.drawString("Date Printed: "+m.getDateApplication(), 20, 710);
            
            if(y < 715)
                y = 700;
            this.setSize (550, y+15);
            
        }catch(ErrorException ex){
        }catch(FileNotFoundException ex){
        }catch(IOException ex){}
    }
    
    ArrayList<Graphics2D> graph = new ArrayList<Graphics2D>();
    int[] pageBreak;
    
    @Override
    public int print(Graphics gx, PageFormat pf, int page) throws PrinterException {
        Graphics2D g = (Graphics2D) gx;
        Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 8);
        g.setFont(font);
        FontMetrics metrics = g.getFontMetrics(font);
        int lineHieght = metrics.getHeight();
        if(pageBreak == null){
            int linesPerPage = (int)((pf.getImageableHeight()-150)/lineHieght);
            int numBreaks = (getPageSize()-1)/linesPerPage;
            pageBreak = new int[numBreaks];
            for(int b=0; b<numBreaks; b++)
                pageBreak[b] = (b-1)*linesPerPage;
        }
        
        if(page > pageBreak.length)
            return NO_SUCH_PAGE;
        
        g.translate(pf.getImageableX(), pf.getImageableY());
        //Data Information here
        
        
        
        graph.add(g);
        return PAGE_EXISTS;
    }
    
    public void showPrintDialog(){
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);
        if(job.printDialog() == true){
            try{
                job.print();
            }catch(PrinterException ex){}
        }
    }
    
    private int getPageSize(){
        if(eList != null)
            return eList.size();
        
        return 0;
    }
    
    public void setDate(String date){
        this.date = date;
    }
    
    public void setEquipmentList(ArrayList<Equipment> eList){
        this.eList = eList;
    }
    
    static String [] wrapText (String text, int len){
        // return empty array for null text
        if (text == null)
        return new String [] {};

        // return text if len is zero or less
        if (len <= 0)
        return new String [] {text};

        // return text if less than length
        if (text.length() <= len)
        return new String [] {text};

        char [] chars = text.toCharArray();
        Vector lines = new Vector();
        StringBuffer line = new StringBuffer();
        StringBuffer word = new StringBuffer();

        for (int i = 0; i < chars.length; i++) {
            word.append(chars[i]);

            if (chars[i] == ' ') {
                if ((line.length() + word.length()) > len) {
                    lines.add(line.toString());
                    line.delete(0, line.length());
                }
                line.append(word);
                word.delete(0, word.length());
            }
        }

        // handle any extra chars in current word
        if (word.length() > 0) {
            if ((line.length() + word.length()) > len) {
                lines.add(line.toString());
                line.delete(0, line.length());
            }
            line.append(word);
        }

        // handle extra line
        if (line.length() > 0) {
            lines.add(line.toString());
        }

        String [] ret = new String[lines.size()];
        int c = 0; // counter
        for (Enumeration e = lines.elements(); e.hasMoreElements(); c++) {
            ret[c] = (String) e.nextElement();
        }

        return ret;
    }
    
}
