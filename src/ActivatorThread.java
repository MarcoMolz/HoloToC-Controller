import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.nio.charset.StandardCharsets;

public class ActivatorThread extends Thread{

    private MqttAsyncClient mqtt;

    private String payload;

    private float x;
    private float y;
    private float z;
    private boolean coordinates;

    private int RoadID;

    private int LaneID;
    private float s;


    private boolean autopilot;
    private boolean checkAutopilot;

    private OpenDS openDS;

    public ActivatorThread(MqttAsyncClient mqtt, String payload, float x, float y, float z, boolean coordinates, int roadID, int laneID, float s, boolean autopilot, boolean checkAutopilot, OpenDS openDS) {
        this.mqtt = mqtt;
        this.payload = payload;
        this.x = x;
        this.y = y;
        this.z = z;
        this.coordinates = coordinates;
        RoadID = roadID;
        LaneID = laneID;
        this.s = s;
        this.autopilot = autopilot;
        this.checkAutopilot = checkAutopilot;
        this.openDS = openDS;
    }

    @Override
    public void run() {
        if (coordinates){
            while (true){
                System.out.println("checking");
               if (Math.abs(openDS.getX()-x) < 1 && Math.abs(openDS.getY()-y)<1 && Math.abs(openDS.getZ()-z)<1){
                   if (checkAutopilot){
                       if (autopilot==openDS.getAutopilot()){
                           try {
                               mqtt.publish("opends-holographic-interface/request/event",new MqttMessage(payload.getBytes(StandardCharsets.UTF_8)));
                           } catch (MqttException e) {
                               throw new RuntimeException(e);
                           }
                       }
                   } else {
                       try {
                           mqtt.publish("opends-holographic-interface/request/event",new MqttMessage(payload.getBytes(StandardCharsets.UTF_8)));
                       } catch (MqttException e) {
                           throw new RuntimeException(e);
                       }
                   }
               }
            }
        } else {
            while (true){
                System.out.println("checking: "+Math.abs(openDS.getS()-s) + " "+ (RoadID==openDS.getRoadID()) +" "+ (LaneID==openDS.getLaneID()) +" "+ (Math.abs(openDS.getS()-s)<1));
                if (RoadID==openDS.getRoadID() && LaneID==openDS.getLaneID() && Math.abs(openDS.getS()-s)<1){
                    if (checkAutopilot){
                        if (autopilot==openDS.getAutopilot()){
                            try {
                                mqtt.publish("opends-holographic-interface/request/event",new MqttMessage(payload.getBytes(StandardCharsets.UTF_8)));
                                return;
                            } catch (MqttException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    } else {
                        try {
                            mqtt.publish("opends-holographic-interface/request/event",new MqttMessage(payload.getBytes(StandardCharsets.UTF_8)));
                            return;
                        } catch (MqttException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }



    }
}
