package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {

    AccountDAO accountDAO;

    public AccountService()
    {
        accountDAO = new AccountDAO();
    }

    public Account getAccountbyUsername(String username)
    {
        return accountDAO.getAccountbyUsername(username);
    }


    public Account createAccount(Account account)
    {
        String username = account.getUsername();
        String password = account.getPassword();
        if (getAccountbyUsername(username) == null)
        {
            return accountDAO.createAccount(username, password);
        }
        return null;
    }

}
