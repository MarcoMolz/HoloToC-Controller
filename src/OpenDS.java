public class OpenDS {

    private float x;
    private float y;
    private float z;

    private float speed;
    private float orientation;

    private int roadID;
    private int laneID;
    private float s;


    public synchronized float getX() {
        return x;
    }

    public synchronized void setX(float x) {
        this.x = x;
    }

    public synchronized float getY() {
        return y;
    }

    public synchronized void setY(float y) {
        this.y = y;
    }

    public synchronized float getZ() {
        return z;
    }

    public synchronized void setZ(float z) {
        this.z = z;
    }

    public synchronized float getSpeed() {
        return speed;
    }

    public synchronized void setSpeed(float speed) {
        this.speed = speed;
    }

    public synchronized float getOrientation() {
        return orientation;
    }

    public synchronized void setOrientation(float orientation) {
        this.orientation = orientation;
    }

    public synchronized int getRoadID() {
        return roadID;
    }

    public synchronized void setRoadID(int roadID) {
        this.roadID = roadID;
    }

    public synchronized int getLaneID() {
        return laneID;
    }

    public synchronized void setLaneID(int laneID) {
        this.laneID = laneID;
    }

    public synchronized float getS() {
        return s;
    }

    public synchronized void setS(float s) {
        this.s = s;
    }
}
