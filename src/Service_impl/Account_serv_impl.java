/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Service_impl;

import DAO_impl.Account_impl;
import DAO_interface.Account_interface;
import Models.Account;
import Service_interface.Account_serv_interface;
import Tool.ErrorException;
import java.util.ArrayList;

/**
 *
 * @author hatiefm
 */
public class Account_serv_impl implements Account_serv_interface {

    private Account_interface account;

    public Account_serv_impl (){
        account = new Account_impl();
    }
    
    @Override
    public int getResponse() {
        return account.getResponse();
    }

    @Override
    public void addAccount(Account acc) throws ErrorException {
        account.addAccount(acc);
    }

    @Override
    public void editAccount(Account acc) throws ErrorException {
        account.editAccount(acc);
    }

    @Override
    public void deleteAccount(Account acc) throws ErrorException {
        account.deleteAccount(acc);
    }

    @Override
    public Account getAccount_account_id(String accnt_id) throws ErrorException {
        return account.getAccount_account_id(accnt_id);
    }

    @Override
    public Account getAccount_username_pass(String username, String password) throws ErrorException {
        return account.getAccount_username_pass(username, password);
    }

    @Override
    public ArrayList<Account> getAccount_code(String code) throws ErrorException {
        return account.getAccount_code(code);
    }

    @Override
    public int getAccountLastSeq() throws ErrorException {
        return account.getAccountLastSeq();
    }
    
}
