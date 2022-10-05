
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;

public class CustomCallback implements MqttCallback {

    //private holotoc h;

    public CustomCallback(){

    }

    @Override
    public void connectionLost(Throwable cause) {

    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        String msg = new String(message.getPayload());
        JSONObject j = new JSONObject(msg);
        System.out.println(msg);
        holotoc.h.processMessage(topic,j);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

    }
}
