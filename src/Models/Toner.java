/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author mojeb
 */
public class Toner {
    
    private Equipment equip_id;
    private String toner_name;

    public Toner() {
        this.equip_id = new Equipment();
        this.toner_name = "";
    }
    
    public Toner(Equipment equip_id, String toner_name) {
        this.equip_id = equip_id;
        this.toner_name = toner_name;
    }

    public Equipment getEquip_id() {
        return equip_id;
    }

    public void setEquip_id(Equipment equip_id) {
        this.equip_id = equip_id;
    }

    public String getToner_name() {
        return toner_name;
    }

    public void setToner_name(String toner_name) {
        this.toner_name = toner_name;
    }
}
