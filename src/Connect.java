import javax.swing.*;
import java.awt.event.*;

public class Connect extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextField a127001TextField;
    private JTextField a1883TextField;
    private JButton buttonCancel;

    public Connect() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        holotoc.connect(a127001TextField.getText(),a1883TextField.getText());
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        holotoc.h.dispose();
        dispose();
    }

    public static void main(String[] args) {
        Connect dialog = new Connect();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
