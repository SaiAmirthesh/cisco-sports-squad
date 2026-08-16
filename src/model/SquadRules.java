package model;

public class SquadRules {
    private final int requiredSquadSize;
    private final int requiredGoalkeepers;
    private final int minimumDefenders;
    private final int minimumForwards;
    private final int maximumCohortSize;

    public SquadRules(int requiredSquadSize,
                      int requiredGoalkeepers,
                      int minimumDefenders,
                      int minimumForwards,
                      int maximumCohortSize) {
        this.requiredSquadSize = requiredSquadSize;
        this.requiredGoalkeepers = requiredGoalkeepers;
        this.minimumDefenders = minimumDefenders;
        this.minimumForwards = minimumForwards;
        this.maximumCohortSize = maximumCohortSize;
    }

    public int getRequiredSquadSize() {
        return requiredSquadSize;
    }

    public int getRequiredGoalkeepers() {
        return requiredGoalkeepers;
    }

    public int getMinimumDefenders() {
        return minimumDefenders;
    }

    public int getMinimumForwards() {
        return minimumForwards;
    }

    public int getMaximumCohortSize() {
        return maximumCohortSize;
    }
}
