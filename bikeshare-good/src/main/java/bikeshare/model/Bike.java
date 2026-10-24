
package bikeshare.model;

import bikeshare.Station;

public class Bike {
    private final int id;
    private Station station;
    private String memberId;

    public Bike(int id, Station station) {
        this.id = id;
        this.station = station;
    }

    public int getId() {
        return id;
    }

    public Station getStation() {
        return station;
    }

    public String getMemberId() {
        return memberId;
    }

    public boolean isAvailable() {
        return memberId == null;
    }

    public void rent(String memberId) {
        if (!isAvailable()) {
            throw new IllegalStateException("Bike is not available");
        }
        this.memberId = memberId;
    }

    public void returnBike(Station station) {
        if (isAvailable()) {
            throw new IllegalStateException("Bike is not rented");
        }
        this.station = station;
        this.memberId = null;
    }
}
