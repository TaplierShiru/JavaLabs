package MainWindow;

import javax.swing.*;

public class CalculaterShowController {
    private JTextField fullEq;
    private JTextField singleEq;

    private int indexStart = 0;

    public CalculaterShowController(JTextField fullEq, JTextField singleEq){
        this.fullEq = fullEq;
        this.singleEq = singleEq;
    }

    public void setFullEq(String fullEqStr){
        this.indexStart = this.fullEq.getText().length();

        this.fullEq.setText(fullEq.getText() + fullEqStr);
    }

    public void setSingleEq(String singleEqStr){
        this.singleEq.setText(singleEqStr);
    }

    public void removeLastEntered(){
        String removedLast = this.fullEq.getText().substring(0, this.indexStart);
        this.fullEq.setText(removedLast);
        this.indexStart = -1;
    }

    public void resetFullEq(){
        this.fullEq.setText("");
        this.singleEq.setText("");
    }
}
