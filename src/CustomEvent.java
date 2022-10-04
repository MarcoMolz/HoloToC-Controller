import javax.swing.*;
import java.awt.event.*;

public class CustomEvent extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox comboBox1;
    private JCheckBox showDistanceInProgressCheckBox;
    private JTextField TopText;
    private JTextField BottomText;
    private JTextField StartBox;
    private JTextField AheadBox;
    private JTextField lateral;
    private JTextField elev;
    private JSpinner numofsigns;
    private JTextField distance;
    private JSpinner numofupdates;
    private JCheckBox showGoalRingCheckBox;
    private JCheckBox showSignsCheckBox;
    private JCheckBox silentCheckBox;

    public CustomEvent() {
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
        String color = "g";
        switch (comboBox1.getSelectedIndex()) {
            case 0 -> color = "b";
            case 1 -> color = "y";
            case 2 -> color = "r";
            case 3 -> color = "g";
        }


        String msg = "{\n" +
                "      \"type\": \"event\",\n" +
                "      \"color\": \""+color+"\",\n" +
                "      \"dynamicDist\": "+showDistanceInProgressCheckBox.isSelected()+",\n" +
                "      \"topText\": \""+TopText.getText()+"\",\n" +
                "      \"bottomText\": \""+BottomText.getText()+"\",\n" +
                "      \"start\": "+StartBox.getText()+",\n" +
                "      \"ahead\": "+AheadBox.getText()+",\n" +
                "      \"side\": "+lateral.getText()+",\n" +
                "      \"up\": "+elev.getText()+",\n" +
                "      \"numOfSigns\": "+(int)numofsigns.getValue()+",\n" +
                "      \"signDist\": "+distance.getText()+",\n" +
                "      \"numOfNotifications\": "+(int)numofupdates.getValue()+",\n" +
                "      \"visible\": "+showSignsCheckBox.isSelected()+",\n" +
                "      \"silent\": "+silentCheckBox.isSelected()+",\n" +
                "      \"showRing\": "+showGoalRingCheckBox.isSelected()+"\n" +
                "    }";

        System.out.println(msg);
        holotoc.h.textArea2.setText(msg);

        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        CustomEvent dialog = new CustomEvent();
        dialog.setTitle("Create Custom Event");
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
