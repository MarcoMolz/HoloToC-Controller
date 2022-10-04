import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
//import org.eclipse.paho.mqttv5.client.MqttConnectionOptions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;

public class holotoc extends JFrame {
    private JTextField mqttaddress;
    private JButton connectButton;
    private JButton processButton;
    private JButton loadButton;
    private JPanel panelMain;
    private JTextArea textArea2;
    private JTextField mqttport;
    private JComboBox comboBox1;
    private JCheckBox xCheckBox;
    private JTextField textField1;
    private JTextField textField2;
    private JCheckBox roadIDCheckBox;
    private JCheckBox laneIDCheckBox;
    private JCheckBox sValueCheckBox;
    private JTextField textField3;
    private JTextField textField4;
    private JCheckBox autopilotCheckBox;
    private JRadioButton onRadioButton;
    private JRadioButton offRadioButton;
    private JCheckBox yCheckBox;
    private JCheckBox zCheckBox;
    private JTextField textField5;
    private JTextField textField6;

    private ButtonGroup buttonGroup;

    private MqttAsyncClient mqtt;

    private CustomCallback callback;

    private MQTT_Daemon mqtt_daemon;

    public holotoc() {
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (connectButton.getText().equals("Connect")) {
                    String tmp = "tcp://" + mqttaddress.getText() + ":" + mqttport.getText();
                    //tmp = "tcp://127.0.0.1:1883";
                    System.out.println(tmp);
                    try {
                        processButton.setEnabled(true);
                        mqtt = new MqttAsyncClient(tmp, "holotocController_" + MqttClient.generateClientId(), new MemoryPersistence());
                        MqttConnectOptions connOpts = new MqttConnectOptions();
                        connOpts.setCleanSession(true);
                        connOpts.setConnectionTimeout(60);
                        connOpts.setKeepAliveInterval(60);
                        connOpts.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1);
                        callback = new CustomCallback();
                        mqtt.setCallback(callback);
                        //mqtt_daemon = new MQTT_Daemon(mqtt);
                        connectButton.setText("Disconnect");
                        //mqtt_daemon.start();
                        mqtt.connect(connOpts);

                        mqtt_daemon = new MQTT_Daemon(mqtt);
                        mqtt_daemon.start();



                    } catch (MqttException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    try {
                        mqtt.disconnect();
                        processButton.setEnabled(false);
                        connectButton.setText("Connect");
                    } catch (MqttException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mqtt.publish("opends-holographic-interface/request/event", new MqttMessage(textArea2.getText().getBytes(StandardCharsets.UTF_8)));
                } catch (MqttException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public static void main(String[] args){
        holotoc h = new holotoc();
        h.setContentPane(h.panelMain);
        h.setTitle("HoloToC Event Controller");
        h.setSize(640,480);
        h.buttonGroup = new ButtonGroup();
        h.buttonGroup.add(h.onRadioButton);
        h.buttonGroup.add(h.offRadioButton);
        h.setVisible(true);
        h.processButton.setEnabled(false);
        h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void processMessage(String message){
        System.out.println(message);
    }
}
