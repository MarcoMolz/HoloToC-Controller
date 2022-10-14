import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CustomNotification extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JCheckBox visibleCheckBox;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JTextField textField2;
    private JSlider slider1;
    private JCheckBox directPositioningCheckBox;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JLabel PercentageLabel;

    public CustomNotification() {
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

        slider1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                PercentageLabel.setText(""+slider1.getValue());
            }
        });
    }

    private void onOK() {
        String color = "g";
        switch (comboBox1.getSelectedIndex()) {
            case 0 -> color = "b";
            case 1 -> color = "y";
            case 2 -> color = "r";
            case 3 -> color = "g";
        }

        String msg = "{\n" +
                "        \"type\": \"notification\",\n" +
                "        \"visible\": "+visibleCheckBox.isSelected()+",\n" +
                "        \"color\": \""+color+"\",\n" +
                "        \"topText\": \""+textField1.getText()+"\",\n" +
                "        \"bottomText\": \""+textField2.getText()+"\",\n" +
                "        \"percentage\": "+slider1.getValue()+",\n" +
                "        \"direct\":"+directPositioningCheckBox.isSelected()+",\n" +
                "        \"posX\":"+textField3.getText()+",\n" +
                "        \"posY\":"+textField4.getText()+",\n" +
                "        \"posZ\":"+textField5.getText()+"\n" +
                "    }";

        holotoc.h.textArea2.setText(msg);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        CustomNotification dialog = new CustomNotification();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
