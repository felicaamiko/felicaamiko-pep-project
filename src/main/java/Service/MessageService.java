package Service;

import Model.Message;
import DAO.MessageDAO;

import java.util.List;

public class MessageService {
    //im copying from flightservice to see...
    MessageDAO messageDAO;

    public MessageService()
    {
        messageDAO = new MessageDAO();
    }
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

    public List<Message> getAllMessages()
    {
        return messageDAO.getAllMessages();
    }

    public Message getMessagebyID(String message_id)
    {
        int messageint = Integer.parseInt(message_id);
        return messageDAO.getMessagebyID(messageint);
    }
    // public List<Account> getAllAccounts2(){

    // }

}
