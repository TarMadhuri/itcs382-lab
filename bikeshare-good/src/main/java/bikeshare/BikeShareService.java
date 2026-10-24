
package bikeshare;

import bikeshare.model.Bike;
import bikeshare.model.Member;
import bikeshare.model.Ride;

import java.util.ArrayList;
import java.util.List;

public class BikeShareService {
    private final List<Bike> bikes = new ArrayList<>();
    private final RideRepository rideRepository;

    public BikeShareService(RideRepository rideRepository) {
        this.rideRepository = rideRepository;

        int id = 1;
        for (Station station : Station.values()) {
            bikes.add(new Bike(id++, station));
            bikes.add(new Bike(id++, station));
        }
    }

    public Bike rent(String memberId, MemberType type, Station station) {
        for (Bike bike : bikes) {
            if (bike.getStation() == station && bike.isAvailable()) {
                bike.rent(memberId);
                return bike;
            }
        }
        throw new IllegalStateException(
                "No available bike at " + station);
    }

    public Ride returnBike(String memberId, Station station, int minutes) {
        if (minutes <= 0) {
            throw new IllegalArgumentException(
                    "Minutes must be greater than zero");
        }

        for (Bike bike : bikes) {
            if (memberId.equals(bike.getMemberId())) {
                MemberType type = findMemberType(memberId);
                Member member = new Member(memberId, type);
                double fee = member.getRatePerMinute() * minutes;

                bike.returnBike(station);

                Ride ride = new Ride(
                        bike.getId(), memberId, minutes,
                        fee, station.name());

                rideRepository.save(ride);
                return ride;
            }
        }

        throw new IllegalStateException(
                "No rented bike found for member " + memberId);
    }

    private MemberType findMemberType(String memberId) {
        MemberType type = memberTypes.get(memberId);
        if (type == null) {
            throw new IllegalStateException(
                    "Member type not found for " + memberId);
        }
        return type;
    }

    private final java.util.Map<String, MemberType> memberTypes =
            new java.util.HashMap<>();

    public void registerMember(String memberId, MemberType type) {
        memberTypes.put(memberId, type);
    }

    public List<Bike> getBikes() {
        return List.copyOf(bikes);
    }
}
