package se.kth.iv1350.posSem4.main;

import se.kth.iv1350.posSem4.controller.Controller;
import se.kth.iv1350.posSem4.view.View;

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
