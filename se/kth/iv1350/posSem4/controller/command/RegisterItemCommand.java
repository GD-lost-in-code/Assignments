package se.kth.iv1350.posSem4.controller.command;
import se.kth.iv1350.pos.controller.Controller;

public class RegisterItemCommand implements Command{
    private final Controller controller;
    private final String itemID;

    public RegisterItemCommand(Controller controller, String itemID){
        this.controller = controller;
        this.itemID = itemID;
    }

    @Override
    public void execute(){
        try{
            controller.registerItem(itemID);
        }catch(Exception e){
            System.out.println("[Command Error] " + e.getMessage());
        }
    }
}
