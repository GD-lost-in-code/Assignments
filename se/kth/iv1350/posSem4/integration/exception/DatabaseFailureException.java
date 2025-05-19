package se.kth.iv1350.posSem4.integration.exception;

public class DatabaseFailureException extends Exception {
    public DatabaseFailureException(String itemID){
        super("Database failure accessing item: "+itemID);
    }
}
