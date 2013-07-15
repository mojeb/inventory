/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Date;

/**
 *
 * @author hatiefm
 */
public class Account {
    
    private String account_id;
    private String username;
    private String password;
    private Person user;
    private Date date_created;
    private String status;
    private int seq;

    public Account() {
        this.account_id = "";
        this.username = "";
        this.password = "";
        this.user = new Person();
        this.date_created = null;
        this.status = "";
        this.seq = 0;
    }
    
    public Account(String account_id, String username, String password, 
            Person user, Date date_created, String status,
            int seq) {
        this.account_id = account_id;
        this.username = username;
        this.password = password;
        this.user = user;
        this.date_created = date_created;
        this.status = status;
        this.seq = seq;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public Date getDate_created() {
        return date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Person getUser() {
        return user;
    }

    public void setUser(Person user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }
}
