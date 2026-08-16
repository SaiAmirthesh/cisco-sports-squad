package model;

public class Player {
    private final String id;
    private final String name;
    private final Position position;
    private final Cohort cohort;
    private final Availability availability;

    public Player(String id, String name, Position position,
                  Cohort cohort, Availability availability) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.cohort = cohort;
        this.availability = availability;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public Cohort getCohort() {
        return cohort;
    }

    public Availability getAvailability() {
        return availability;
    }
}
