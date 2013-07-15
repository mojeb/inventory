/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author hatiefm
 */
public class IP {
    
    private Equipment equipId;
    private String IP;

    public IP() {
        this.equipId = new Equipment();
        this.IP = "";
    }
    
    public IP(Equipment equipId, String IP) {
        this.equipId = equipId;
        this.IP = IP;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public Equipment getEquipId() {
        return equipId;
    }

    public void setEquipId(Equipment equipId) {
        this.equipId = equipId;
    }
}
