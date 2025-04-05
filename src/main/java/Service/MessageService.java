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


    public Message createMessage(Message message)
    {
        // int posted_by = message.getPosted_by();
        // String message_text = message.getMessage_text();
        // long time_posted_epoch = message.getTime_posted_epoch();

        if (message.getMessage_text() !="")
        {
        return messageDAO.createMessage(message);
        }
        return null;
    }

    public Message deleteMessagebyID(String message_id)
    {
        int messageint = Integer.parseInt(message_id);
        Message messagetoreturn = messageDAO.getMessagebyID(messageint);
        messageDAO.deleteMessagebyID(messageint);
        return messagetoreturn;
    }

    public Message updateMessagebyID(Message message)
    {
        //int messageint = Integer.parseInt(message_id);
        Message messagetoUpdate = getMessagebyID(String.valueOf(message.getMessage_id()));

        //System.out.println(messagetoUpdate.message_text);
        if (messagetoUpdate == null){
            System.out.println("message is null");
            return null;
        }
        message.setPosted_by(messagetoUpdate.getPosted_by());
        message.setTime_posted_epoch(messagetoUpdate.getTime_posted_epoch());
        //we don't change postedby, message id...
        //we change message text
        //do we change time posted epoch?
        // if(messagetoUpdate != null)//message.getMessage_text() != "" && messagetext.length()<=255)
        // {



        return messageDAO.updateMessagebyID(message);
        // }
        // return null;
    }

    public List<Message> getMessagesbyAccount(String account_id){
        int accountint = Integer.parseInt(account_id);
        return messageDAO.getMessagesbyAccount(accountint);
    }   

}
