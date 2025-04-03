package Service;

import Model.Account;
import DAO.AccountDAO;

import java.util.List;

public class AccountService {
    //im copying from flightservice to see...
    AccountDAO accountDAO;

    // public AccountService ()
    // {
    //     accountDAO = new AccountDAO();
    // }

    // public Account addAccount(Account account)
    // {
    //     return accountDAO.insertAccount(account);
    // }

    // public Account updateAccount()
    // {

    // }

    public List<Account> getAllAccounts()
    {
        return accountDAO.getAllAccounts();
    }

    // public List<Account> getAllAccounts2(){

    // }

}
