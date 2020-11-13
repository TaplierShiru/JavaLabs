package MainWindow;

import javax.swing.*;

public class ExceptionSender {

    private JTextField textField;
    private boolean isErrorShow = false;

    public ExceptionSender(JTextField textField){
        this.textField = textField;
    }

    public boolean getIsErrorShow(){
        return this.isErrorShow;
    }

    public void setErrorMess(String errorMess){
        this.isErrorShow = true;
        this.textField.setText(errorMess);
    }

    public void resetField(){
        this.isErrorShow = false;
        this.textField.setText("");
    }
}
