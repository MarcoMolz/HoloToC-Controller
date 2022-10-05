public class OpenDS {

    private float x;
    private float y;
    private float z;

    private float speed;
    private float orientation;

    private boolean autopilot;

    private int roadID;
    private int laneID;
    private float s;

    public synchronized boolean getAutopilot() {
        return autopilot;
    }

    public synchronized void setAutopilot(boolean autopilot) {
        this.autopilot = autopilot;
    }

    public  float getX() {
        return x;
    }

    public synchronized void setX(float x) {
        this.x = x;
    }

    public  float getY() {
        return y;
    }

    public synchronized void setY(float y) {
        this.y = y;
    }

    public  float getZ() {
        return z;
    }

    public synchronized void setZ(float z) {
        this.z = z;
    }

    public  float getSpeed() {
        return speed;
    }

    public synchronized void setSpeed(float speed) {
        this.speed = speed;
    }

    public  float getOrientation() {
        return orientation;
    }

    public synchronized void setOrientation(float orientation) {
        this.orientation = orientation;
    }

    public  int getRoadID() {
        return roadID;
    }

    public synchronized void setRoadID(int roadID) {
        this.roadID = roadID;
    }

    public  int getLaneID() {
        return laneID;
    }

    public synchronized void setLaneID(int laneID) {
        this.laneID = laneID;
    }

    public  float getS() {
        return s;
    }

    public synchronized void setS(float s) {
        this.s = s;
    }
}
