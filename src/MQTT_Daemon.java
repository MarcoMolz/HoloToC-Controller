import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.internal.ClientState;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class MQTT_Daemon extends Thread {

    private MqttAsyncClient client;

    private boolean started = false;

    public MQTT_Daemon(MqttAsyncClient client){
        this.client = client;
    }
    @Override
    public void run() {
        try {
            while (true) {
                if (client.isConnected() && !started) {
                    client.subscribe("opends-holographic-interface/data/events",2);
                    client.subscribe("opends-holographic-interface/data/vehicle",2);
                    client.subscribe("opends-holographic-interface/data/road",2);
                    client.subscribe("opends-holographic-interface/data/objects",2);
                    System.out.println("Connected "+client.isConnected());

                    holotoc.h.setTitle("HoloToC Event Controller : Connected");
                    //client.publish("holotoc-controller/test",msg);
                    started = true;
                }


            }
        } catch (Exception e){
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
}
