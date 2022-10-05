import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
//import org.eclipse.paho.mqttv5.client.MqttConnectionOptions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;

public class holotoc extends JFrame {
    private JButton processButton;
    private JPanel panelMain;
    public JTextArea textArea2;
    private JTextField mqttport;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JCheckBox autopilotCheckBox;
    private JRadioButton onRadioButton;
    private JRadioButton offRadioButton;
    private JTextField textField5;
    private JTextField textField6;
    private JLabel positionLabel;
    private JLabel oriLabel;
    private JLabel SpeedLabel;
    private JLabel RoadLabel;
    private JLabel LaneLabel;
    private JLabel sLabel;
    private JLabel APLabel;
    private JCheckBox conditionalCheckBox;
    private JLabel Xfield;
    private JLabel YField;
    private JLabel Zfield;
    private JLabel Eventfield;
    private JLabel openfield;
    private JLabel targetlabel;
    private JRadioButton positionRadioButton;
    private JRadioButton roadDataRadioButton;

    public static OpenDS openDS = new OpenDS();

    private ButtonGroup buttonGroup;

    public static MqttAsyncClient mqtt;

    private CustomCallback callback;

    private static MQTT_Daemon mqtt_daemon;

    public static holotoc h = new holotoc();
    private ButtonGroup buttonGroup2;


    public static void connect(String ip, String port){
        String tmp = "tcp://" + ip + ":" + port;
        try {
            mqtt = new MqttAsyncClient(tmp, "holotocController_" + MqttClient.generateClientId(), new MemoryPersistence());
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            connOpts.setConnectionTimeout(60);
            connOpts.setKeepAliveInterval(60);
            connOpts.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1);
            CustomCallback callback = new CustomCallback();
            mqtt.setCallback(callback);
            mqtt.connect(connOpts);

            mqtt_daemon = new MQTT_Daemon(mqtt);
            mqtt_daemon.start();



        } catch (MqttException ex) {
            throw new RuntimeException(ex);
        }

    };
    public holotoc() {

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

        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (((String) comboBox1.getSelectedItem()).equals("Dynamic ToC")){
                    Dynamic customEvent = new Dynamic();
                    customEvent.setTitle("Dynamic ToC");
                    customEvent.setSize(400,300);
                    customEvent.setVisible(true);
                }

                if (((String) comboBox1.getSelectedItem()).equals("Custom Model")){
                    CustomModel customEvent = new CustomModel();
                    customEvent.setTitle("Custom Model");
                    customEvent.setSize(400,600);
                    customEvent.setVisible(true);
                }

                if (((String) comboBox1.getSelectedItem()).equals("Short ToC")){
                    ShortToC customEvent = new ShortToC();
                    customEvent.setTitle("Custom Model");
                    customEvent.setSize(400,300);
                    customEvent.setVisible(true);
                }

                if (((String) comboBox1.getSelectedItem()).equals("Medium ToC")){
                    MediumToC customEvent = new MediumToC();
                    customEvent.setTitle("Medium ToC");
                    customEvent.setSize(400,300);
                    customEvent.setVisible(true);
                }

                if (((String) comboBox1.getSelectedItem()).equals("Long ToC")){
                    LongToC customEvent = new LongToC();
                    customEvent.setTitle("Long ToC");
                    customEvent.setSize(400,300);
                    customEvent.setVisible(true);
                }

                if (((String) comboBox1.getSelectedItem()).equals("Autopilot Request")){
                    Autopilot customEvent = new Autopilot();
                    customEvent.setTitle("Autopilot request");
                    customEvent.setSize(400,300);
                    customEvent.setVisible(true);
                };

                if (((String) comboBox1.getSelectedItem()).equals("Recovery Event")){
                    Recovery customEvent = new Recovery();
                    customEvent.setTitle("Recovery Event");
                    customEvent.setSize(400,300);
                    customEvent.setVisible(true);
                };

                if (((String) comboBox1.getSelectedItem()).equals("Custom Image")){
                    imageRequest customEvent = new imageRequest();
                    customEvent.setTitle("Recovery Event");
                    customEvent.setSize(400,300);
                    customEvent.setVisible(true);
                };

                if (((String) comboBox1.getSelectedItem()).equals("Custom Event")){
                    CustomEvent customEvent = new CustomEvent();
                    customEvent.setTitle("Custom Event");
                    customEvent.setSize(400,600);
                    customEvent.setVisible(true);
                };
                comboBox1.setSelectedIndex(0);
            }
        });
    }

    public static void main(String[] args){
        h = new holotoc();
        h.setContentPane(h.panelMain);
        h.setTitle("HoloToC Event Controller");
        h.setSize(640,480);
        h.buttonGroup = new ButtonGroup();
        h.buttonGroup2 = new ButtonGroup();
        h.buttonGroup2.add(h.positionRadioButton);
        h.buttonGroup2.add(h.roadDataRadioButton);
        h.setVisible(true);
        h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Connect customEvent = new Connect();
        customEvent.setTitle("Connect to MQTT Broker");
        customEvent.setSize(400,200);
        customEvent.setVisible(true);

    }

    public void processMessage(String topic, String msg){
        //JsonObject j = Json.parse(msg).asObject();
        System.out.println(topic);
        String[] elems = msg.replace("\r","").replace("\n","").replace("\"","").replace(" ","").replace("{","").replace("}","").replace(",",":").split(":");

        for (int i = 0; i<elems.length;i++){
            elems[i] = elems[i].substring(0, Math.min(elems[i].length(), 7));
        }

        if (topic.contains("vehicle")) {

            Xfield.setText("X: "+elems[3]);
            YField.setText("Y: "+elems[5]);
            Zfield.setText("Z: "+elems[7]);

            oriLabel.setText("Ori: " + elems[9]);
            SpeedLabel.setText("Speed: " + elems[11]);
            APLabel.setText("Autopilot: " + elems[13]);
        } else if (topic.contains("data/road")){
            RoadLabel.setText("RoadID: "+elems[3]);
            LaneLabel.setText("LaneID: "+elems[5]);
            sLabel.setText("s: "+elems[7]);
            targetlabel.setText("Target: "+elems[9]);
        } else if (topic.contains("data/events")){
            Eventfield.setText("Events: "+elems[5]);
            openfield.setText("Open: "+elems[7]);
        }

    }
}
