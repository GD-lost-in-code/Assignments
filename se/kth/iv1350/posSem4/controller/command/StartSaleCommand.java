package se.kth.iv1350.posSem4.controller.command;

import se.kth.iv1350.pos.controller.Controller;

public class StartSaleCommand implements Command{
    private final Controller controller;

    public StartSaleCommand(Controller controller){
        this.controller = controller;
    }

    @Override
    public void execute(){
        controller.startSale();
    }
}
