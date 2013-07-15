/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Service_interface;

import Models.Account;
import Tool.ErrorException;
import java.util.ArrayList;

/**
 *
 * @author hatiefm
 */
public interface Account_serv_interface {
    
    public int getResponse();
    public void addAccount(Account acc) throws ErrorException;
    public void editAccount(Account acc) throws ErrorException;
    public void deleteAccount(Account acc) throws ErrorException;
    public Account getAccount_account_id(String accnt_id) throws ErrorException;
    public Account getAccount_username_pass(String username, String password) throws ErrorException;
    public ArrayList<Account> getAccount_code(String code) throws ErrorException;
    public int getAccountLastSeq() throws ErrorException;
}
