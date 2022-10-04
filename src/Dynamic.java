import javax.swing.*;
import java.awt.event.*;

public class

Dynamic extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField reason;
    private JTextField start;
    private JTextField ahead;
    private JTextField distance;

    public Dynamic() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
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
        String msg = "{\n" +
                "      \"type\": \"ToC\",\n" +
                "      \"reason\": \""+reason.getText()+"\",\n" +
                "      \"start\": "+start.getText()+",\n" +
                "      \"ahead\": "+ahead.getText()+",\n" +
                "      \"distanceToAP\": "+distance.getText()+"\n" +
                "    }";
        System.out.println(msg);
        holotoc.h.textArea2.setText(msg);

        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        Dynamic dialog = new Dynamic();
        dialog.setTitle("Dynamic ToC");
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
