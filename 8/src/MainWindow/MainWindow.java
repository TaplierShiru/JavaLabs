package MainWindow;

import CalculatorLogic.CalculatorController;
import Utils.ButtonActionListener;
import Utils.KeyActionListener;

import javax.swing.*;

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
    private JButton button_finish;
    private JButton button_calc_point;

    private JTextField textField_main_action;
    private JButton buttonNum0;
    private JTextField textField_full_eq;
    private JButton buttonPlusSign;
    private JButton buttonMinusSign;
    private JButton buttonMultSign;
    private JButton buttonDivSign;
    private JButton ButtonDelAll;
    private JButton buttonPowSign;
    private JButton buttonSqrtSign;
    private JButton buttonClearLastNum;

    private ButtonActionListener buttonActionListener;
    private KeyActionListener keyActionListener;
    private CalculatorController calculatorController;
    private CalculaterShowController calculatorShowerController;
    private ExceptionSender exceptionSender;

    public MainWindow(){
        setContentPane(mainPanel);
        setVisible(true);
        this.setFocusable(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setSize(300, 500);
        // If we put null, window will be at the center
        setLocationRelativeTo(null);

        this.textField_full_eq.setEditable(false);
        this.textField_main_action.setEditable(false);

        this.calculatorShowerController = new CalculaterShowController(
                this.textField_full_eq,
                this.textField_main_action
        );

        this.calculatorController = new CalculatorController(this.calculatorShowerController);
        this.exceptionSender = new ExceptionSender(this.textField_full_eq);
        this.buttonActionListener = new ButtonActionListener(this.calculatorController, this.exceptionSender);

        this.keyActionListener = new KeyActionListener(this.buttonActionListener);
        this.addKeyListener(this.keyActionListener);
        this.initButtonListener();
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
        this.button_calc_point.addActionListener(this.buttonActionListener);

        this.button_finish.addActionListener(this.buttonActionListener);
        this.ButtonDelAll.addActionListener(this.buttonActionListener);
        this.buttonClearLastNum.addActionListener(this.buttonActionListener);


    }

}
