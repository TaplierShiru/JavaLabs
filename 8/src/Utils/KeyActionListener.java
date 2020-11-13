package Utils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * Process typed keyboard buttons, send certain button char into ActionListener
 */
public class KeyActionListener implements KeyListener {

    private ButtonActionListener buttonActionListener;

    public KeyActionListener(ButtonActionListener buttonActionListener){
        this.buttonActionListener = buttonActionListener;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        this.buttonActionListener.actionPerformed("" + e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) { }
}
