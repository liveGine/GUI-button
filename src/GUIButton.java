import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
/**
 * This is Main class.
 * @author Dmitry Voronin
 */
public class GUIButton {
    JLabel label = new JLabel();
    Button buttonMain = new Button();
    ImageIcon iconStandard = new ImageIcon("standard.png");
    /**
     * This is the constructor of the main class.
     * Here I create a container frame
     * I'm doing the default setting for the correct display
     * I set the GridBagLayout layout manager and configure it for the correct display of objects
     * Adding objects to the content panel.
     */
    public GUIButton(){
        JFrame frame = new JFrame("Button");
        frame.setSize(400,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setIconImage(iconStandard.getImage());
        frame.setLayout(new GridBagLayout());
        GridBagConstraints bagConstraints = new GridBagConstraints();

        buttonMain.setPreferredSize(new Dimension(200,100));
        buttonMain.setFont(new Font("Bradley Hand ITC", Font.BOLD,45));
        frame.add(buttonMain,bagConstraints);

        bagConstraints.gridy = 1;
        bagConstraints.insets = new Insets(30,0,0,0);
        frame.add(label,bagConstraints);
        label.setVisible(false);

        buttonMain.addActionListener(new ActionListener() {
            /**
             * Activating the event listener for the "buttonMain" button
             * It works with the graphical interface and runs a background task using the SwingWorker class {@link GUIButton#runWorker()}.
             * @param e The event is generated when the button is clicked.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonMain.setEnabled(false);
                label.setText("Происходит загрузка в фоновом потоке. Подождите...");
                label.setVisible(true);
                runWorker();
            }
        });
    }
    /**
     * This method creates a dialog panel.
     */
    public void dialogPane(){
        Button buttonDialog = new Button();
        buttonDialog.setText("OK");
        buttonDialog.setFont(new Font("Bradley Hand ITC", Font.BOLD,12));

        JOptionPane pane = new JOptionPane("Действие выполнено!",JOptionPane.PLAIN_MESSAGE,JOptionPane.DEFAULT_OPTION,iconStandard,new Object[]{buttonDialog});

        JDialog dialog = pane.createDialog("Сообщение");
        dialog.setIconImage(iconStandard.getImage());
        buttonDialog.addActionListener(new ActionListener() {
            /**
             * Activate the event listener for the button on the dialog panel to close it.
             * @param e The event is generated when the button is clicked.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        dialog.setVisible(true);
    }

    /**
     * The main method puts a frame in the event dispatching stream to update the interface.
     * @param args not used.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUIButton();
            }
        });
    }

    /**
     * This method allows you to use the SwingWorker class and work with a background process.
     */
    private void runWorker() {
        SwingWorker<Void,Integer> worker = new SwingWorker<Void,Integer>() {
            /**
             * The method contains the logic of our background task.
             * @throws Exception It is used to generate an exception to any type
             */
            @Override
            protected Void doInBackground() throws Exception {
                for (int i = 0; i <= 10; i++) {
                    Thread.sleep(1000);
                    System.out.println(i);
                    publish(i);
                }
                return null;
            }
            /**
             * It is used to process intermediate results and updates the GUI.
             * @param chunks intermediate results to process
             */
            @Override
            protected void process(List<Integer> chunks) {
                int num = chunks.get(chunks.size() - 1);
                if (num % 3 == 0){
                    label.setText("Происходит загрузка в фоновом потоке. Подождите...");
                } else if (num % 2 == 0) {
                    label.setText("Происходит загрузка в фоновом потоке. Подождите..");
                }else {
                    label.setText("Происходит загрузка в фоновом потоке. Подождите.");
                }

            }
            /**
             * Called after the background task is completed and updates the GUI.
             */
            @Override
            protected void done() {
                try {
                    label.setText("");
                    dialogPane();
                    buttonMain.setEnabled(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        worker.execute();
    }
}

