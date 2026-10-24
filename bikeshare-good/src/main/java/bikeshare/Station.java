
package bikeshare;

public enum Station {
    LIBRARY,
    CANTEEN,
    DORM;

    public static Station fromString(String name) {
        return Station.valueOf(name.toUpperCase());
    }
}
