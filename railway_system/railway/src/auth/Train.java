package auth;

// Abstract Class
public abstract class Train {
    protected String trainName;
    protected String from;
    protected String to;

    public Train(String trainName, String from, String to) {
        this.trainName = trainName;
        this.from = from;
        this.to = to;
    }

    // Abstract method
    public abstract void showTrainDetails();
}
