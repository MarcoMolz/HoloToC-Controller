import javax.swing.*;
import java.awt.event.*;

public class CustomModel extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField NameField;
    private JCheckBox directPositioningCheckBox;
    private JTextField ahead;
    private JTextField elevation;
    private JTextField latitude;
    private JTextField Xscale;
    private JTextField Yscale;
    private JTextField Zscale;
    private JTextField Xrot;
    private JTextField Yrot;
    private JTextField Zrot;
    private JCheckBox alwaysFaceCameraCheckBox;
    private JCheckBox terrainCheckBox;

    public CustomModel() {
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
        String msg = "    {\n" +
                "        \"type\":\"Object\",\n" +
                "        \"name\":\""+NameField.getText() +"\",\n" +
                "\n" +
                "        \"direct\":"+directPositioningCheckBox.isSelected()+",\n" +
                "\n" +
                "        \"posX\":"+ahead.getText()+",\n" +
                "        \"posY\":"+elevation.getText()+",\n" +
                "        \"posZ\":"+latitude.getText()+",\n" +
                "                \n" +
                "        \"scaleX\":"+Xscale.getText()+",\n" +
                "        \"scaleY\":"+Yscale.getText()+",\n" +
                "        \"scaleZ\":"+Zscale.getText()+",\n" +
                "\n" +
                "        \"rotX\":"+Xrot.getText()+",\n" +
                "        \"rotY\":"+Yrot.getText()+",\n" +
                "        \"rotZ\":"+Zrot.getText()+",\n" +
                "\n" +
                "        \"facingcamera\":"+alwaysFaceCameraCheckBox.isSelected()+",\n" +
                "        \"terrain\":"+terrainCheckBox.isSelected()+"\n" +
                "    }";

        holotoc.h.textArea2.setText(msg);
        System.out.println(msg);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        CustomModel dialog = new CustomModel();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
