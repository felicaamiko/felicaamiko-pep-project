package Controller;

import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;

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
        app.get("register", this::registerHandler);
        app.get("login", this::loginHandler);
        
        app.get("messages", this::getAllMessagesHandler);
        app.get("messages/{message_id}", this::getMessagebyID);
        app.delete("messages/{message_id}", this::deleteMessagebyID);
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

    private void registerHandler(Context context){
        context.json("register");
    }

    private void loginHandler(Context context){
        context.json("login");
    }

    // private void messagesHandler(Context context){
    //     context.json("messages");
    // }

    private void getAllMessagesHandler(Context context){
        context.json(messageService.getAllMessages());
    }

    private void getMessagebyID(Context context){
        context.json(messageService.getMessagebyID(context.pathParam("message_id"))); //nullptrexception
    }

    private void deleteMessagebyID(Context context){
        context.json(messageService.getMessagebyID(context.pathParam("message_id")));
    }

    private void accountsHandler(Context context){
        context.json("accounts");
    }
}