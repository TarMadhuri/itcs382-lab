
package bikeshare;

import bikeshare.model.Bike;
import bikeshare.model.Ride;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            BikeShareService service =
                    new BikeShareService(new CsvRideRepository());

            System.out.println("BikeShare commands:");
            System.out.println("rent <memberId> <student|staff> <station>");
            System.out.println("return <memberId> <station> <minutes>");
            System.out.println("list");
            System.out.println("quit");

            while (true) {
                System.out.print("> ");
                if (!scanner.hasNextLine()) {
    break;
}
String line = scanner.nextLine().trim();

                if (line.equalsIgnoreCase("quit")) {
                    break;
                }

                String[] parts = line.split("\\s+");

                try {
                    switch (parts[0].toLowerCase()) {
                        case "rent" -> {
                            if (parts.length != 4) {
                                System.out.println(
                                        "Usage: rent <memberId> <student|staff> <station>");
                                break;
                            }

                            String memberId = parts[1];
                            MemberType type = MemberType.valueOf(
                                    parts[2].toUpperCase());
                            Station station = Station.fromString(parts[3]);

                            service.registerMember(memberId, type);
                            Bike bike = service.rent(
                                    memberId, type, station);

                            System.out.println("Member " + memberId
                                    + " rented bike " + bike.getId()
                                    + " at " + station);
                        }
                        case "return" -> {
                            if (parts.length != 4) {
                                System.out.println(
                                        "Usage: return <memberId> <station> <minutes>");
                                break;
                            }

                            String memberId = parts[1];
                            Station station = Station.fromString(parts[2]);
                            int minutes = Integer.parseInt(parts[3]);

                            Ride ride = service.returnBike(
                                    memberId, station, minutes);

                            System.out.println("Bike " + ride.getBikeId()
                                    + ", Member " + ride.getMemberId()
                                    + ", Minutes " + ride.getMinutes()
                                    + ", Fee " + ride.getFee()
                                    + " baht, Returned at "
                                    + ride.getReturnStation());
                        }
                        case "list" -> {
                            for (Bike bike : service.getBikes()) {
                                System.out.println("Bike " + bike.getId()
                                        + " at " + bike.getStation()
                                        + ": " + (bike.isAvailable()
                                        ? "AVAILABLE" : "RENTED"));
                            }
                        }
                        default ->
                                System.out.println("Unknown command");
                    }
                } catch (IllegalArgumentException
                         | IllegalStateException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
    }
}
