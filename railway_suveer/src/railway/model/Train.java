package railway.model;

public abstract class Train {
    protected String trainName;
    protected int trainNumber;

    public Train(String trainName, int trainNumber) {
        this.trainName = trainName;
        this.trainNumber = trainNumber;
    }

    // Abstract methods
    public abstract void displayTrainDetails();
    public abstract double calculateFare(int seats);

    public String getTrainName() {
        return trainName;
    }

    public int getTrainNumber() {
        return trainNumber;
    }
}
