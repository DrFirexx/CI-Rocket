public class FrameCounter {

    private int counter;
    private int timeInterval;

    public FrameCounter(int timeInterval) {
        this.counter = 0;
        this.timeInterval = timeInterval;
    }

    public boolean checkCounter() {
        if (this.counter == this.timeInterval) {
            return true;
        } else {
            return false;
        }
    }

    public void increment() {
        this.counter += 1;
    }

    public void resetCount() {
        this.counter = 0;
    }
}
