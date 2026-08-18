package model;

public class SquadRules {
    private final int requiredSquadSize;
    private final int requiredGoalkeepers;
    private final int minimumDefenders;
    private final int minimumForwards;
    private final int maximumYear2CohortSize;
    private final int maximumYear3CohortSize;

    public SquadRules(int requiredSquadSize,
                      int requiredGoalkeepers,
                      int minimumDefenders,
                      int minimumForwards,
                      int maximumYear2CohortSize,
                      int maximumYear3CohortSize) {
        this.requiredSquadSize = requiredSquadSize;
        this.requiredGoalkeepers = requiredGoalkeepers;
        this.minimumDefenders = minimumDefenders;
        this.minimumForwards = minimumForwards;
        this.maximumYear2CohortSize = maximumYear2CohortSize;
        this.maximumYear3CohortSize = maximumYear3CohortSize;
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

    public int getMaximumYear2CohortSize() {
        return maximumYear2CohortSize;
    }

    public int getMaximumYear3CohortSize() {
        return maximumYear3CohortSize;
    }
}
