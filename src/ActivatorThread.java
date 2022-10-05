public class ActivatorThread extends Thread{

    private String payload;

    private float x;
    private boolean checkX;

    private float y;
    private boolean checkY;

    private float z;
    private boolean checkZ;

    private float RoadID;
    private boolean checkRoadID;

    private float LaneID;
    private boolean checkLaneID;

    private float s;
    private boolean checkS;

    private boolean autopilot;
    private boolean checkAutopilot;

    public ActivatorThread(String payload, float x, boolean checkX, float y, boolean checkY, float z, boolean checkZ, float roadID, boolean checkRoadID, float laneID, boolean checkLaneID, float s, boolean checkS, boolean autopilot, boolean checkAutopilot) {
        this.payload = payload;
        this.x = x;
        this.checkX = checkX;
        this.y = y;
        this.checkY = checkY;
        this.z = z;
        this.checkZ = checkZ;
        RoadID = roadID;
        this.checkRoadID = checkRoadID;
        LaneID = laneID;
        this.checkLaneID = checkLaneID;
        this.s = s;
        this.checkS = checkS;
        this.autopilot = autopilot;
        this.checkAutopilot = checkAutopilot;
    }

    @Override
    public void run() {
        while (true){
            //TODO





        }
    }
}
