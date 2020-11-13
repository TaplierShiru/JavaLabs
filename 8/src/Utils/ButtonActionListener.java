package Utils;

import CalculatorLogic.CalculatorController;
import MainWindow.ExceptionSender;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Send operation from other controlers/senders to main calculation controller
 * Which calculate final number
 */
public class ButtonActionListener implements ActionListener {

    private CalculatorController calcController;
    private ExceptionSender exceptionSender;

    public ButtonActionListener(CalculatorController calcController, ExceptionSender exceptionSender){
        this.calcController = calcController;
        this.exceptionSender = exceptionSender;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = ((JButton) e.getSource()).getActionCommand();
        if (null != command) {
            this.actionPerformed(command);
        }
    }

    /**
     * @param op Operation which should be processed
     */
    public void actionPerformed(String op){
        // TODO: delete debug print
        System.out.println(op);
        try{
            // If field show error message, clear it
            if (this.exceptionSender.getIsErrorShow()){
                this.exceptionSender.resetField();
            }
            // Process input operation
            this.calcController.sendSingleEq(op);
        } catch (ArithmeticException | NumberFormatException ex){
            // Clear all controls and variables
            this.calcController.resetAllControls();
            // Show error mess
            this.exceptionSender.setErrorMess(ex.getMessage());
        }
    }

}
