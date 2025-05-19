package se.kth.iv1350.posSem4.integration.exception;

public class ItemNotFoundException extends Exception {
    public ItemNotFoundException(String itemID){
        super("Item identifier not found: "+itemID);
    }
}
