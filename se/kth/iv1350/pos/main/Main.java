package se.kth.iv1350.pos.main;

import se.kth.iv1350.pos.controller.Controller;
import se.kth.iv1350.pos.view.View;

/**
 * Application entry point.
 */
public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        View view = new View(controller);
        view.Execution();
    }
}
