
package bikeshare;

import bikeshare.model.Ride;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class CsvRideRepository implements RideRepository {
    private final Path file = Path.of("rides.csv");

    @Override
    public void save(Ride ride) {
        try (BufferedWriter writer = Files.newBufferedWriter(
                file,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND)) {
            writer.write(ride.toCsv());
            writer.newLine();
        } catch (IOException e) {
            throw new IllegalStateException(
                    "Could not save rides.csv", e);
        }
    }
}
