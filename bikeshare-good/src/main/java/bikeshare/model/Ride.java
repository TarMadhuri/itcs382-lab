
package bikeshare.model;

public class Ride {
    private final int bikeId;
    private final String memberId;
    private final int minutes;
    private final double fee;
    private final String returnStation;

    public Ride(int bikeId, String memberId, int minutes,
                double fee, String returnStation) {
        this.bikeId = bikeId;
        this.memberId = memberId;
        this.minutes = minutes;
        this.fee = fee;
        this.returnStation = returnStation;
    }

    public int getBikeId() {
        return bikeId;
    }

    public String getMemberId() {
        return memberId;
    }

    public int getMinutes() {
        return minutes;
    }

    public double getFee() {
        return fee;
    }

    public String getReturnStation() {
        return returnStation;
    }

    public String toCsv() {
        return bikeId + "," + memberId + "," + minutes
                + "," + fee + "," + returnStation;
    }
}
