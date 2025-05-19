package se.kth.iv1350.posSem4.controller.command;

import se.kth.iv1350.pos.controller.Controller;

public class PayCommand implements Command{
    private final Controller controller;
    private final double amount;

    public PayCommand(Controller controller, double amount){
        this.controller = controller;
        this.amount = amount;
    }

    @Override
    public void execute(){
        String receipt = controller.pay(amount);
        System.err.println(receipt);
    }
}
