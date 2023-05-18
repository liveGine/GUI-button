import javax.swing.*;
import java.awt.*;
/**
 * A class that is inherited from {@link JButton}
 * Provides a button with a user interface
 */
class Button extends JButton {
    /**
     * The constructor of the class defines the properties of the button.
     */
    Button() {
        setText("Click");
        setContentAreaFilled(false);
        setFocusPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setToolTipText("Нажмите на кнопку");
    }
    /**
     * This method is inherited from JComponent
     * The method is used to change the appearance of the button
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g.create();
        if (!getModel().isPressed()) {
            GradientPaint gradient = new GradientPaint(new Point(0, 0), Color.CYAN, new Point(0, getHeight()), Color.BLUE);
            graphics2D.setPaint(gradient);
        } else {
            graphics2D.setPaint(Color.BLUE);
        }
        if (!getModel().isEnabled()) {
            graphics2D.setPaint(Color.BLUE);
        }
        graphics2D.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
}