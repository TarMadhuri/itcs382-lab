
package bikeshare;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static List<Bike> bikes = new ArrayList<>();
    public static List<String> rides = new ArrayList<>();
    public static int nextBikeId = 1;

    static class Bike {
        int id;
        String station;
        String memberId;
        int memberType;

        Bike(int id, String station) {
            this.id = id;
            this.station = station;
            this.memberId = null;
            this.memberType = -1;
        }
    }

    public static void main(String[] args) {
        String[] stations = {"Library", "Canteen", "Dorm"};

        for (String station : stations) {
            bikes.add(new Bike(nextBikeId++, station));
            bikes.add(new Bike(nextBikeId++, station));
        }

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("BikeShare commands:");
            System.out.println("rent <memberId> <student|staff> <station>");
            System.out.println("return <memberId> <station> <minutes>");
            System.out.println("list");
            System.out.println("quit");

            while (true) {
                System.out.print("> ");
                String line = scanner.nextLine().trim();

                if (line.equalsIgnoreCase("quit")) {
                    break;
                }

                String[] parts = line.split("\\s+");

                try {
                    if (parts[0].equalsIgnoreCase("rent") && parts.length == 4) {
                        int type;

                        if (parts[2].equalsIgnoreCase("student")) {
                            type = 1;
                        } else if (parts[2].equalsIgnoreCase("staff")) {
                            type = 2;
                        } else {
                            System.out.println("Invalid member type.");
                            continue;
                        }

                        process(parts[1], type, parts[3], 0, true);

                    } else if (parts[0].equalsIgnoreCase("return") && parts.length == 4) {
                        process(parts[1], -1, parts[2],
                                Integer.parseInt(parts[3]), false);

                    } else if (parts[0].equalsIgnoreCase("list") && parts.length == 1) {
                        for (Bike bike : bikes) {
                            String status = bike.memberId == null
                                    ? "AVAILABLE"
                                    : "RENTED by " + bike.memberId;
                            System.out.println("Bike " + bike.id + " at "
                                    + bike.station + ": " + status);
                        }
                    } else {
                        System.out.println("Invalid command.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Minutes must be a number.");
                }
            }
        }
    }

    public static void process(String memberId, int memberType,
                               String station, int minutes, boolean renting) {
        if (renting) {
            for (Bike bike : bikes) {
                if (bike.station.equalsIgnoreCase(station)
                        && bike.memberId == null) {
                    bike.memberId = memberId;
                    bike.memberType = memberType;
                    System.out.println("Member " + memberId
                            + " rented bike " + bike.id + " at " + station);
                    return;
                }
            }
            System.out.println("No available bike at " + station);
        } else {
            for (Bike bike : bikes) {
                if (memberId.equals(bike.memberId)) {
                    double fee = minutes * (bike.memberType == 1 ? 2 : 3);

                    bike.station = station;
                    bike.memberId = null;

                    String receipt = "Bike " + bike.id + ", Member "
                            + memberId + ", Minutes " + minutes
                            + ", Fee " + fee + " baht, Returned at " + station;

                    System.out.println(receipt);
                    rides.add(receipt);

                    try (FileWriter writer = new FileWriter("rides.csv", true)) {
                        writer.write(receipt + System.lineSeparator());
                    } catch (IOException e) {
                        System.err.println("Could not save ride: " + e.getMessage());
                    }
                    return;
                }
            }
            System.out.println("No bike found for member " + memberId);
        }
    }
}
