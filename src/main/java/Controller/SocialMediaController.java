package Controller;

import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//my imports below
import Model.Account;
import Model.Message;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController { //test with thunder client
    AccountService accountService;
    MessageService messageService;

    public SocialMediaController()
    {
        accountService = new AccountService();
        messageService = new MessageService();
    }
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("example-endpoint", this::exampleHandler);

        app.post("register", this::registerHandler);
        app.post("login", this::loginHandler);
        
        app.get("messages", this::getAllMessagesHandler);
        app.get("messages/{message_id}", this::getMessagebyID);
        app.post("messages/", this::createMessage);
        app.delete("messages/{message_id}", this::deleteMessagebyID);
        app.get("/accounts/{account_id}/messages", this::getMessagesbyAccount);
        app.patch("messages/{message_id}", this::updateMessagebyID);

        app.get("accounts", this::accountsHandler);
        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");

    }

    private void registerHandler(Context context) throws JsonProcessingException{
        //context.json(AccountService.createAccount(context.bodyAsClass()));
        
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);
        Account registeredAccount = accountService.createAccount(account);
        if(registeredAccount != null){
            context.status(200);
            context.json(mapper.writeValueAsString(registeredAccount));
        }else{
            context.status(400);
            context.json("");
        }
        
        
        //context.result(context.body());
        System.out.println("afddsafs");
        
    }

    private void loginHandler (Context context) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(context.body(), Account.class);

        Account loggedinAccount = accountService.loginAccount(account); //throws error 500. 


        if (loggedinAccount != null)
        {
            context.status(200);
            context.json(mapper.writeValueAsString(loggedinAccount));
        }
        else
        {
            context.status(401);
        }
        // context.json(mapper.writeValueAsString(new Account(1, "testuser1", "password")));
        // context.status(200);
    }

    // private void messagesHandler(Context context){
    //     context.json("messages");
    // }

    private void getAllMessagesHandler(Context context){
        context.json(messageService.getAllMessages());
    }


    private void getMessagebyID(Context context){
        try {
            context.json(messageService.getMessagebyID(context.pathParam("message_id"))); //nullptrexception
        } catch (Exception e) {
            // TODO: handle exception
            e.getMessage();
            context.status(200); //usually 404 would be used?
        }
            }

    private void createMessage(Context context) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(), Message.class);
        Message addedMessage = messageService.createMessage(message);
        if (addedMessage != null)
        {
            context.json(mapper.writeValueAsString(addedMessage));
        }
        else{
            context.status(400);
        }
    }

    private void deleteMessagebyID(Context context){
        try {
            context.json(messageService.deleteMessagebyID(context.pathParam("message_id")));
        } catch (Exception e) {
            // TODO: handle exception
            e.getMessage();
            context.status(200);
        }

    }

    private void updateMessagebyID(Context context) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(), Message.class);
        System.out.println(message.getMessage_text());
        //String messagetext = mapper.readTree(context.body()).get("message_text").asText(); //https://mkyong.com/java/jackson-how-to-parse-json/
        message.setMessage_id(Integer.parseInt(context.pathParam("message_id")));
        Message updatedMessage = messageService.updateMessagebyID(message);
        try{
        if (updatedMessage == null){
            context.status(400);
        }else{
            context.json(mapper.writeValueAsString(updatedMessage));
        }
    }catch(Exception e){
        context.status(400);
    }
        // try {
        //     context.json(messageService.updateMessagebyID(context.pathParam("message_id"))); //more info in http body
        // } catch (Exception e) {
        //     // TODO: handle exception
        //     e.getMessage();
        //     context.status(400);
        // }


    }

    private void accountsHandler(Context context){
        context.json("accounts");
    }

    private void getMessagesbyAccount(Context context)
    {
        context.json(messageService.getMessagesbyAccount(context.pathParam("account_id")));

    }

}