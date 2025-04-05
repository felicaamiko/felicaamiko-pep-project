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
        if (getAccountbyUsername(username) == null && username != "" && password.length() > 4)
        {
            return accountDAO.createAccount(username, password);
        }
        return null;
    }

    public Account loginAccount(Account account)
    {
        String username = account.getUsername();
        System.out.println(username);
        // //System.out.println(getAccountbyUsername(username).getUsername());
        String password = account.getPassword();
        // //System.out.println(getAccountbyUsername(username).getPassword());
        System.out.println(password);

        AccountDAO dbuser = new AccountDAO();
        Account dbaccount = dbuser.getAccountbyUsername(username);
        if (dbaccount!=null)
        {
            System.out.println("dbacct found");
            String dbpass = dbaccount.getPassword(); //this throws an error 500 if dbaccount is null
            System.out.println(dbpass);
            if (dbpass.equals(password)){
                System.out.println("asfdsafds");
                //return new Account(1, "testuser1", "password");
                return getAccountbyUsername(username);
            }
        }
        // System.out.println(dbuser.getAccountbyUsername(username));
        // System.out.println("yafdysa");
        System.out.println("dbacct was never found?");

        return null;
    }


}
