package Utils;

import java.awt.*;
import java.awt.event.KeyEvent;


/**
 * Process typed keyboard buttons, send certain button char into ActionListener
 */
public class CalculatorKeyActionDispatcher implements KeyEventDispatcher {

    private ButtonActionListener buttonActionListener;
    private boolean pressed = false;
    private boolean holdPressed = false;

    public CalculatorKeyActionDispatcher(ButtonActionListener buttonActionListener){
        this.buttonActionListener = buttonActionListener;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        if (!pressed){
            // Clicked
            pressed = true;
            this.buttonActionListener.actionPerformed("" + e.getKeyChar());
        }else if (!holdPressed){
            // Hold
            holdPressed = true;
        }else{
            // Released
            pressed = false;
            holdPressed = false;
        }
        return true;
    }

}
