package MainWindow;

import CalculatorLogic.CalculatorController;
import Utils.ButtonActionListener;
import Utils.CalculatorKeyActionDispatcher;

import javax.swing.*;
import java.awt.*;


public class MainWindow extends JFrame {
    private JPanel mainPanel;

    private JButton buttonNum7;
    private JButton buttonNum4;
    private JButton buttonNum1;
    private JButton ButtonNum8;
    private JButton buttonNum5;
    private JButton buttonNum2;
    private JButton buttonNum9;
    private JButton buttonNum6;
    private JButton buttonNum3;
    private JButton buttonFinish;
    private JButton buttonCalcPoint;

    private JTextField textFieldMainAction;
    private JButton buttonNum0;
    private JTextField textFieldFullEq;
    private JButton buttonPlusSign;
    private JButton buttonMinusSign;
    private JButton buttonMultSign;
    private JButton buttonDivSign;
    private JButton ButtonDelAll;
    private JButton buttonPowSign;
    private JButton buttonSqrtSign;
    private JButton buttonClearLastNum;

    private ButtonActionListener buttonActionListener;
    private CalculatorKeyActionDispatcher calculatorKeyActionDispatcher ;
    private CalculatorController calculatorController;
    private CalculaterShowController calculatorShowerController;
    private ExceptionSender exceptionSender;

    public MainWindow(){
        this.setContentPane(mainPanel);
        this.setVisible(true);
        this.setFocusable(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setSize(300, 500);
        // If we put null, window will be at the center
        this.setLocationRelativeTo(null);

        this.textFieldFullEq.setEditable(false);
        this.textFieldMainAction.setEditable(false);

        this.calculatorShowerController = new CalculaterShowController(
                this.textFieldFullEq,
                this.textFieldMainAction
        );

        this.calculatorController = new CalculatorController(this.calculatorShowerController);
        this.exceptionSender = new ExceptionSender(this.textFieldFullEq);
        this.buttonActionListener = new ButtonActionListener(this.calculatorController, this.exceptionSender);

        this.calculatorKeyActionDispatcher = new CalculatorKeyActionDispatcher(this.buttonActionListener);
        this.initButtonListener();

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this.calculatorKeyActionDispatcher);
    }

    private void initButtonListener(){
        this.buttonNum0.addActionListener(this.buttonActionListener);
        this.buttonNum1.addActionListener(this.buttonActionListener);
        this.buttonNum2.addActionListener(this.buttonActionListener);
        this.buttonNum3.addActionListener(this.buttonActionListener);
        this.buttonNum4.addActionListener(this.buttonActionListener);
        this.buttonNum5.addActionListener(this.buttonActionListener);
        this.buttonNum6.addActionListener(this.buttonActionListener);
        this.buttonNum7.addActionListener(this.buttonActionListener);
        this.ButtonNum8.addActionListener(this.buttonActionListener);
        this.buttonNum9.addActionListener(this.buttonActionListener);

        this.buttonPlusSign.addActionListener(this.buttonActionListener);
        this.buttonMinusSign.addActionListener(this.buttonActionListener);
        this.buttonMultSign.addActionListener(this.buttonActionListener);;
        this.buttonDivSign.addActionListener(this.buttonActionListener);
        this.buttonPowSign.addActionListener(this.buttonActionListener);
        this.buttonSqrtSign.addActionListener(this.buttonActionListener);
        this.buttonCalcPoint.addActionListener(this.buttonActionListener);

        this.buttonFinish.addActionListener(this.buttonActionListener);
        this.ButtonDelAll.addActionListener(this.buttonActionListener);
        this.buttonClearLastNum.addActionListener(this.buttonActionListener);


    }

}
